package com.example.API_POKEMON.models.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table (name = "treinadores")
public class Treinador {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
}