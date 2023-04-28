package com.example.API_POKEMON.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.API_POKEMON.models.entity.TipoPokemon;

public interface TipoPokemonRepository extends CrudRepository<TipoPokemon, Integer > {
    
}
