package com.example.API_POKEMON.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table (name = "pokemons")
public class Pokemon {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private Integer idade;
    private Double peso;
    private Double altura;

    @ManyToOne
    @JoinColumn (name = "treinador_id")
    private Treinador treinador;

    @JsonManagedReference
    @ManyToMany (targetEntity = TipoPokemon.class, cascade = CascadeType.ALL)
    private List<TipoPokemon> tipoPokemon = new ArrayList<TipoPokemon>();

    @JsonManagedReference
    @ManyToMany (targetEntity = Habilidade.class, cascade = CascadeType.ALL)
    private List<Habilidade> habilidades = new ArrayList<Habilidade>();

    @JsonManagedReference
    @ManyToMany (targetEntity = Fraqueza.class, cascade = CascadeType.ALL)
    private List<Fraqueza> fraquezas = new ArrayList<Fraqueza>();
}
