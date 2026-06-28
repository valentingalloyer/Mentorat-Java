package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.model.FilmDto;
import com.example.demo.model.FilmEntity;
import com.example.demo.repository.FilmRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    private final RestClient restClient = RestClient.create("https://ghibliapi.vercel.app");


    private static List<Film> films = List.of(
            new Film(1, "Avatar", "Sci-Fi", 2009, 8.0),
            new Film(2, "Inception", "Sci-Fi", 2010, 8.8),
            new Film(3, "Gladiator", "Action", 2000, 8.5)
    );

    public List<Film> getAll() { return films; }

    public Optional<Film> findById(int id) {
        return films.stream().filter(f -> f.id() == id).findFirst();
    }

    public List<FilmDto> getGhibliFilms() {
        return restClient.get()
                .uri("/films")
                .retrieve()
                .body(new ParameterizedTypeReference<List<FilmDto>>() {}); // ParameterizedTypeReference pour qu'il comprenne que c'est une liste
    }

    public FilmDto getFilmById(String id) {
        return restClient.get()
                .uri("/films/{id}", id)
                .retrieve()
                .body(FilmDto.class);
    }



    private final FilmRepository repository;

    public FilmService(FilmRepository repository) {
        this.repository = repository;
    }

    public List<FilmEntity> getFilmsParGenre(String genre) {
        return repository.findByGenre(genre);
    }

    public void ajouterFilm(FilmEntity film) {
        repository.save(film);
    }
}