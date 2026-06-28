package com.example.demo.repository;

import com.example.demo.model.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spring génère tout le SQL tout seul, juste en étendant cette interface !
public interface PokemonRepository extends JpaRepository<PokemonEntity, Integer> {

    List<PokemonEntity> findByType(String type);
}