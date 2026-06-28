package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity // Indique à JPA que cette classe doit être une table en base
public class PokemonEntity {

    @Id // Clé primaire obligatoire pour JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String type;
    private int weight;
    private int hp;

    // JPA a ABSOLUMENT BESOIN d'un constructeur vide
    public PokemonEntity() {}

    // Constructeur complet pour faciliter l'instanciation
    public PokemonEntity(String name, String type, int weight, int hp) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.hp = hp;
    }

    // Getters et Setters nécessaires à JPA pour manipuler les données
    public Integer getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}