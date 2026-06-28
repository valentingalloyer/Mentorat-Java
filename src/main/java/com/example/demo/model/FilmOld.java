package com.example.demo.model;

public class FilmOld {
    private String titre;
    private int annee;

    public FilmOld(String titre, int annee) {
        this.titre = titre;
        this.annee = annee;
    }

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @Override
    public String toString() {
        return "Film{" + "titre=" + titre + ", annee=" + annee + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return false;
    }
}