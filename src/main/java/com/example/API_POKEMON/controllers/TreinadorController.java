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

import com.example.API_POKEMON.models.dto.TreinadorDTO;
import com.example.API_POKEMON.models.entity.Treinador;
import com.example.API_POKEMON.models.repository.TreinadorRepository;

@RestController
@CrossOrigin("*")
@RequestMapping ("/treinadores")
public class TreinadorController {

    @Autowired
    TreinadorRepository treinadorRepository;

    // Listar todos os Treinadores
    @GetMapping
    public ResponseEntity<Object> listarTreinadores(){
        return ResponseEntity.status(HttpStatus.OK).body(treinadorRepository.findAll());
    }

    // Cadastrar um novo Treinador
    @PostMapping
    public ResponseEntity<Object> criarTreinador(@RequestBody TreinadorDTO treinadorDTO){
        Treinador treinadorEntity = new Treinador();
        BeanUtils.copyProperties(treinadorDTO, treinadorEntity);
        
        return ResponseEntity.status(HttpStatus.OK).body(treinadorRepository.save(treinadorEntity));
    }
    
    // Editar as informações do Treinador selecionado
    @PutMapping ("/{id}")
    public ResponseEntity<Object> editarHabilidade ( @PathVariable Integer id, @RequestBody TreinadorDTO treinadorDTO ){
        Optional<Treinador> treinadorExists = treinadorRepository.findById(id);
        
        if(!treinadorExists.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treinador não existe!");
        
        Treinador treinadorEntity = treinadorExists.get();

        BeanUtils.copyProperties(treinadorDTO, treinadorEntity);

        return ResponseEntity.status(HttpStatus.OK).body(treinadorRepository.save(treinadorEntity));
    }
    
    // Excluir os dados de um Treinador selecionado
    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deleteHabilidade ( @PathVariable Integer id ){
        Optional<Treinador> treinadorExists = treinadorRepository.findById(id);
        
        if(!treinadorExists.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treinador não existe!");

        treinadorRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Treinador excluído!");
    }
    
    
}
