package com.example.API_POKEMON.models.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table (name = "tipo_pokemon")
public class TipoPokemon {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable = false)
    private String nome;

    @JsonBackReference
    @ManyToMany
    @JoinTable (name = "pokemon_tipo",
    joinColumns = @JoinColumn (name = "tipo_pokemon_id"),
    inverseJoinColumns = @JoinColumn(name = "pokemon_id"))
    private List<Pokemon> pokemons;
}
