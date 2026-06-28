package com.example.demo.controller;

import com.example.demo.model.Film;
import com.example.demo.model.FilmDto;
import com.example.demo.service.FilmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {
    private final FilmService filmService;

    // Injection par constructeur : le standard pro
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> getAll() {
        return filmService.getAll();
    }

    @GetMapping("/{id}")
    public FilmDto getAllViaApiById(@PathVariable String id) {
        return filmService.getFilmById(id);
    }

    @GetMapping("api")
    public List<FilmDto> getAllViaApi() {
        return filmService.getGhibliFilms();
    }
}
