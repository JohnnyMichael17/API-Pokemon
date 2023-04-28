package com.example.API_POKEMON.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 

public class PokemonDTO {
    
    private String nome;
    private Integer idade;
    private Double peso;
    private Double altura;
    private Integer treinadorId;
    private List<Integer> tipo;
    private List<Integer> habilidades;
    private List<Integer> fraquezas;

}
