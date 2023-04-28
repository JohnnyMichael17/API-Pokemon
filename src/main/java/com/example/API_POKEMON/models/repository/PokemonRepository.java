package com.example.API_POKEMON.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.API_POKEMON.models.entity.Pokemon;
import com.example.API_POKEMON.models.entity.TipoPokemon;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer > {

    List<Pokemon> findByTipoPokemonIn(List<TipoPokemon> tipoPokemon);

}
