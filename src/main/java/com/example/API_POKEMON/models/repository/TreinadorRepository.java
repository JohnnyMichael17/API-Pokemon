package com.example.API_POKEMON.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.API_POKEMON.models.entity.Treinador;

public interface TreinadorRepository extends CrudRepository<Treinador, Integer > {
    
}
