package com.example.API_POKEMON.controllers;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API_POKEMON.models.dto.TipoPokemonDTO;
import com.example.API_POKEMON.models.entity.TipoPokemon;
import com.example.API_POKEMON.models.repository.PokemonRepository;
import com.example.API_POKEMON.models.repository.TipoPokemonRepository;

@RestController
@CrossOrigin("*")
@RequestMapping ("/tipo_pokemon")
public class TipoPokemonController {

    @Autowired
    TipoPokemonRepository tipoPokemonRepository;

    @Autowired
    PokemonRepository pokemonRepository;

    // Listar todos os Tipos de Pokémon
    @GetMapping
    public ResponseEntity<Object> listarTipos(){
        return ResponseEntity.status(HttpStatus.OK).body(tipoPokemonRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> criarTipo(@RequestBody TipoPokemonDTO tipoPokemonDTO){
        TipoPokemon tipoPokemonEntity = new TipoPokemon();
        BeanUtils.copyProperties(tipoPokemonDTO, tipoPokemonEntity);
        
        return ResponseEntity.status(HttpStatus.OK).body(tipoPokemonRepository.save(tipoPokemonEntity));
    }
    
    // Editar Tipos de Pokémon
    @PutMapping ("/{id}")
    public ResponseEntity<Object> editarTipo ( @PathVariable Integer id, @RequestBody TipoPokemonDTO tipoPokemonDTO ){
        Optional<TipoPokemon> tipoPokemonExists = tipoPokemonRepository.findById(id);
        
        if(!tipoPokemonExists.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Pokémon não encontrado!");
        
        TipoPokemon tipoPokemonEntity = tipoPokemonExists.get();

        BeanUtils.copyProperties(tipoPokemonDTO, tipoPokemonEntity);

        return ResponseEntity.status(HttpStatus.OK).body(tipoPokemonRepository.save(tipoPokemonEntity));
    }
    
    // Excluir um Tipo de Pokémon
    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deleteTipo ( @PathVariable Integer id ){
        Optional<TipoPokemon> tipoPokemonExists = tipoPokemonRepository.findById(id);
        
        if(!tipoPokemonExists.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Pokémon não encontrado!");
        
        tipoPokemonRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Tipo de Pokémon excluído!");
    }   
}
