package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "films")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String genre;
    private int annee;

    // JPA exige un constructeur vide
    public FilmEntity() {}

    public FilmEntity(String titre, String genre, int annee) {
        this.titre = titre;
        this.genre = genre;
        this.annee = annee;
    }

    // Getters et Setters pour permettre à Hibernate de manipuler les données
    public Long getId() { return id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
}