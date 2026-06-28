package com.example.demo.repository;

import com.example.demo.model.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    // Spring implémente automatiquement :
    List<FilmEntity> findByGenre(String genre);
    List<FilmEntity> findByAnneeGreaterThan(int annee);
}