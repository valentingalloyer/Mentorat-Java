package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Ignore tous les champs du JSON (stats, abilities) non déclarés ici
@JsonIgnoreProperties(ignoreUnknown = true)
public record PokemonApiDto(int id, String name, int weight) {
}
