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

import com.example.API_POKEMON.models.dto.HabilidadeDTO;
import com.example.API_POKEMON.models.entity.Habilidade;
import com.example.API_POKEMON.models.repository.HabilidadeRepository;

@RestController
@CrossOrigin("*")
@RequestMapping ("/habilidades")
public class HabilidadeController {

    @Autowired
    HabilidadeRepository habilidadeRepository;

    // Listar todos as Habilidade
    @GetMapping
    public ResponseEntity<Object> listarHabilidades(){
        return ResponseEntity.status(HttpStatus.OK).body(habilidadeRepository.findAll());
    }

    // Criar uma Habilidade
    @PostMapping
    public ResponseEntity<Object> criarHabilidade(@RequestBody HabilidadeDTO habilidadeDTO){
        Habilidade habilidadeEntity = new Habilidade();
        BeanUtils.copyProperties(habilidadeDTO, habilidadeEntity);
        
        return ResponseEntity.status(HttpStatus.OK).body(habilidadeRepository.save(habilidadeEntity));
    }
    
    // Editar uma Habilidade
    @PutMapping ("/{id}")
    public ResponseEntity<Object> editarHabilidade ( @PathVariable Integer id, @RequestBody HabilidadeDTO habilidadeDTO ){
        Optional<Habilidade> habilidadeExists = habilidadeRepository.findById(id);
        
        if(!habilidadeExists.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Habilidade não encontrada!");
        
        Habilidade habilidadeEntity = habilidadeExists.get();

        BeanUtils.copyProperties(habilidadeDTO, habilidadeEntity);

        return ResponseEntity.status(HttpStatus.OK).body(habilidadeRepository.save(habilidadeEntity));
    }
    
    // Excluir uma Habilidade
    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deleteHabilidade ( @PathVariable Integer id ){
        Optional<Habilidade> habilidadeExists = habilidadeRepository.findById(id);
        
        if(!habilidadeExists.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Habilidade não encontrada!");
        
        habilidadeRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Habilidade excluída!");
    }
    
    
}
