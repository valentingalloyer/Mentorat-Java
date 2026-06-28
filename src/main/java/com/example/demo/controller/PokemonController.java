package com.example.demo.controller;


import com.example.demo.model.Pokemon;
import com.example.demo.model.PokemonApiDto;
import com.example.demo.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    PokemonService pokemonServiceAncien;

    // Injection de dépendances via constructeur (Bonne pratique)
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getPokemons() {
        return pokemonService.getAllPokemons();
    }

    @GetMapping("/{id}")
    public Optional<Pokemon> getPokemonById(@PathVariable int id) {
        // En conditions réelles, on utiliserait une gestion d'erreur (404 Not Found)
        // si l'Optional est vide. Ici Spring Boot renverra 'null' (200 OK vide).
        return pokemonService.findPokemonById(id);
    }

    @GetMapping("/external/{name}")
    public PokemonApiDto getExternalPokemon(@PathVariable String name) {
        return pokemonService.fetchPokemonFromApi(name);
    }

}