package com.example.demo.service;

import com.example.demo.model.Pokemon;
import com.example.demo.model.PokemonApiDto;
import com.example.demo.model.PokemonEntity;
import com.example.demo.repository.PokemonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    List<Pokemon> pokedex = List.of(
            new Pokemon(1, "Bulbizarre", "Plante", 7, 45),
            new Pokemon(4, "Salamèche", "Feu", 9, 39),
            new Pokemon(6, "Dracaufeu", "Feu", 90, 78),
            new Pokemon(7, "Carapuce", "Eau", 9, 44),
            new Pokemon(25, "Pikachu", "Électrik", 6, 35),
            new Pokemon(130, "Léviator", "Eau", 235, 95),
            new Pokemon(143, "Ronflex", "Normal", 460, 160)
    );

    // Initialisation du client HTTP moderne (Exercice 6)
    private final RestClient restClient;

    private final PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.restClient = RestClient.builder()
                .baseUrl("https://pokeapi.co/api/v2")
                .build();

        this.repository = repository;
    }

    public List<Pokemon> getAllPokemons() {
        return pokedex;
    }

    public Optional<Pokemon> findPokemonById(int id) {
        return pokedex.stream()
                .filter(p -> p.id() == id)
                .findFirst();
    }

    // Appel distant vers la vraie API
    public PokemonApiDto fetchPokemonFromApi(String pokemonName) {
        return restClient.get()
                .uri("/pokemon/{name}", pokemonName.toLowerCase())
                .retrieve()
                .body(PokemonApiDto.class); // Magie : le JSON devient un Record
    }





    // Base de données
//    public List<PokemonEntity> getAllPokemons() {
//        return repository.findAll();
//    }

    public Optional<PokemonEntity> findPokemonById(Integer id) {
        return repository.findById(id);
    }

    public List<PokemonEntity> getPokemonsByType(String type) {
        return repository.findByType(type);
    }

    public void savePokemon(PokemonEntity pokemon) {
        repository.save(pokemon);
    }
}