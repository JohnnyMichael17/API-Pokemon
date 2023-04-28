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

import com.example.API_POKEMON.models.dto.FraquezaDTO;
import com.example.API_POKEMON.models.entity.Fraqueza;
import com.example.API_POKEMON.models.repository.FraquezaRepository;

@RestController
@CrossOrigin("*")
@RequestMapping ("/fraquezas")
public class FraquezaController {

    @Autowired
    FraquezaRepository fraquezaRepository;

    // Listar todas as Fraquezas
    @GetMapping
    public ResponseEntity<Object> listarFraquezas(){
        return ResponseEntity.status(HttpStatus.OK).body(fraquezaRepository.findAll());
    }

    // Criar uma fraqueza
    @PostMapping
    public ResponseEntity<Object> criarFraqueza(@RequestBody FraquezaDTO fraquezaDTO){
        Fraqueza fraquezaEntity = new Fraqueza();
        BeanUtils.copyProperties(fraquezaDTO, fraquezaEntity);
        
        return ResponseEntity.status(HttpStatus.OK).body(fraquezaRepository.save(fraquezaEntity));
    }
    
    // Editar uma fraqueza
    @PutMapping ("/{id}")
    public ResponseEntity<Object> editarFraqueza ( @PathVariable Integer id, @RequestBody FraquezaDTO fraquezaDTO ){
        Optional<Fraqueza> fraquezaExist = fraquezaRepository.findById(id);
        
        if(!fraquezaExist.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fraqueza não encontrada!");

        Fraqueza fraquezaEntity = fraquezaExist.get();

        BeanUtils.copyProperties(fraquezaDTO, fraquezaEntity);

        return ResponseEntity.status(HttpStatus.OK).body(fraquezaRepository.save(fraquezaEntity));
    }
    
    // Excluir uma fraqueza
    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deleteFraqueza ( @PathVariable Integer id ){
        Optional<Fraqueza> fraquezaExists = fraquezaRepository.findById(id);
        
        if(!fraquezaExists.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fraqueza não encontrada!");

        fraquezaRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Fraqueza excluída!");
    } 
}
