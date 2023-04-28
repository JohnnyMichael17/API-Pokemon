package com.example.API_POKEMON.controllers;

import java.util.ArrayList;
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

import com.example.API_POKEMON.models.dto.PokemonDTO;
import com.example.API_POKEMON.models.entity.Fraqueza;
import com.example.API_POKEMON.models.entity.Habilidade;
import com.example.API_POKEMON.models.entity.Pokemon;
import com.example.API_POKEMON.models.entity.TipoPokemon;
import com.example.API_POKEMON.models.entity.Treinador;
import com.example.API_POKEMON.models.repository.FraquezaRepository;
import com.example.API_POKEMON.models.repository.HabilidadeRepository;
import com.example.API_POKEMON.models.repository.PokemonRepository;
import com.example.API_POKEMON.models.repository.TipoPokemonRepository;
import com.example.API_POKEMON.models.repository.TreinadorRepository;

@RestController
@CrossOrigin("*")
@RequestMapping ("/pokemon")
public class PokemonController {

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    TipoPokemonRepository tipoPokemonRepository;

    @Autowired
    HabilidadeRepository habilidadeRepository;

    @Autowired
    TreinadorRepository treinadorRepository;

    @Autowired
    FraquezaRepository fraquezaRepository;

    // Lista todos os Pokémons
    @GetMapping
    public ResponseEntity<Object> listarPokemons(){
        return ResponseEntity.status(HttpStatus.OK).body(pokemonRepository.findAll());
    }

    // Listar as informações do Pokémon solicitado
    @GetMapping ("/{id}")
    public ResponseEntity<Object> listarPokemon( @PathVariable Integer id ){
        return ResponseEntity.status(HttpStatus.OK).body(pokemonRepository.findById(id));
    }

    // Buscar todos os Pokémons do tipo selecionado
    @GetMapping ("/tipo/{id}")
    public ResponseEntity<Object> listarTipos(@PathVariable Integer id){
        
        TipoPokemon tipoPokemon = tipoPokemonRepository.findById(id).get();
        ArrayList<TipoPokemon> tipos = new ArrayList<TipoPokemon>();
        tipos.add(tipoPokemon);
        return ResponseEntity.status(HttpStatus.OK).body(pokemonRepository.findByTipoPokemonIn(tipos)); 
    }

    @PostMapping // Criar um Pokémon
    public ResponseEntity<Object> criarPokemon(@RequestBody PokemonDTO pokemonDTO){
        
        Pokemon pokemonEntity = new Pokemon();
        BeanUtils.copyProperties(pokemonDTO, pokemonEntity);
        
        //Treinador do novo Pokémon
        Optional<Treinador> pokemonTreinador = treinadorRepository.findById(pokemonDTO.getTreinadorId());

        if (!pokemonTreinador.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treinador não existe!");
    
        pokemonEntity.setTreinador(pokemonTreinador.get());

        // Tipo do novo Pokémon
        for(Integer tipoPokemonId: pokemonDTO.getTipo()){
            Optional<TipoPokemon> tipoPokemon = tipoPokemonRepository.findById(tipoPokemonId);
    
            if(!tipoPokemon.isPresent())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Pokémon não existe!");

            pokemonEntity.getTipoPokemon().add(tipoPokemon.get());
        }

        // Habilidades do novo Pokémon
        for(Integer habilidadesId: pokemonDTO.getHabilidades()){
            Optional<Habilidade> pokemonHabilidade = habilidadeRepository.findById(habilidadesId);
            
            if(!pokemonHabilidade.isPresent())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Habilidade não existe!");

            pokemonEntity.getHabilidades().add(pokemonHabilidade.get());
        }

        // Fraquezas do novo Pokémon
        for(Integer fraquezasId: pokemonDTO.getFraquezas()){
            Optional<Fraqueza> pokemonFraqueza = fraquezaRepository.findById(fraquezasId);
            
            if(!pokemonFraqueza.isPresent())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fraqueza não existe!");

            pokemonEntity.getFraquezas().add(pokemonFraqueza.get());
        }
        

        return ResponseEntity.status(HttpStatus.OK).body(pokemonRepository.save(pokemonEntity));
    }
    
    @PutMapping ("/{id}") // Editando as informações de Pokémon solicitado
    public ResponseEntity<Object> editarPokemon ( @PathVariable Integer id, @RequestBody PokemonDTO pokemonDTO ){
        
        Optional<Pokemon> pokemonExists = pokemonRepository.findById(id);

        if(!pokemonExists.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokémon não existe!");
       
        Pokemon pokemonEntity = pokemonExists.get();
        BeanUtils.copyProperties(pokemonDTO, pokemonEntity);

        // Mudando o Treinador do Pokémon solicitado
        Optional<Treinador> pokemonTreinador = treinadorRepository.findById(pokemonDTO.getTreinadorId());

        if(!pokemonTreinador.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treinador não existe!");
        
        pokemonEntity.setTreinador(pokemonTreinador.get());
        
        // Tipo de Pokémon
        for(Integer tipoPokemonId: pokemonDTO.getTipo()){
            Optional<TipoPokemon> tipoPokemon = tipoPokemonRepository.findById(tipoPokemonId);
    
            if(!tipoPokemon.isPresent())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Pokémon não existe!");

            pokemonEntity.getTipoPokemon().add(tipoPokemon.get());
        }

        // Habilidades do Pokémon
        for(Integer habilidadesId: pokemonDTO.getHabilidades()){
            Optional<Habilidade> pokemonHabilidade = habilidadeRepository.findById(habilidadesId);
            
            if(!pokemonHabilidade.isPresent())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Habilidade não existe!");

            pokemonEntity.getHabilidades().add(pokemonHabilidade.get());
        }

        // Fraquezas do Pokémon
        for(Integer fraquezasId: pokemonDTO.getFraquezas()){
            Optional<Fraqueza> pokemonFraqueza = fraquezaRepository.findById(fraquezasId);
            
            if(!pokemonFraqueza.isPresent())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fraqueza não existe!");

            pokemonEntity.getFraquezas().add(pokemonFraqueza.get());
        }

        return ResponseEntity.status(HttpStatus.OK).body(pokemonRepository.save(pokemonEntity));
    }
    
    // Exclui um Pokémon solicitado
    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deletePokemon ( @PathVariable Integer id ){
        Optional<Pokemon> pokemonEntity= pokemonRepository.findById(id);
        
        if(!pokemonEntity.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokémon não existe!");
        
        pokemonRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Pokémon excluído!");
    }
}