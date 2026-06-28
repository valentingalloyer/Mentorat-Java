package com.example.demo;

import com.example.demo.model.Film;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cours {

    public static void main(String[] args) {
        /**
         * Instancier un record
         */
        Film film = new Film(1, "Avatar", "Action", 2012, 8.5);
        /**
         * return switch
         */
        System.out.println(getAgeMinimum(film.genre()));

        /**
         * Optional
         */
        Film resultat = chercherFilmOld("Avatar");
        if (resultat != null) {
            System.out.println(resultat.titre()); // Sans le if, c'est l'explosion (NullPointerException)
        }

        Optional<Film> resultat2 = chercherFilm("Avatar");
        // On ne peut plus "oublier" de vérifier si l'objet est là :
        resultat2.ifPresentOrElse(
                f -> System.out.println("Film trouvé : " + f.titre()),
                () -> System.out.println("Aucun film à ce nom")
        );

        List<String> blockbusters = films.stream()
                .filter(f -> f.genre().equals("Sci-Fi"))  // 1. Je garde la Sci-Fi
                .filter(f -> f.annee() > 2010)            // 2. Je garde les récents
                .map(f -> f.titre().toUpperCase())        // 3. J'extrais le titre et je le transforme
                .toList();                                // 4. Je récupère la liste finale
    }

    static List<Film> films = List.of(
            new Film(1, "Avatar", "Sci-Fi", 2009, 8.0),
            new Film(2, "Inception", "Sci-Fi", 2010, 8.8),
            new Film(3, "Gladiator", "Action", 2000, 8.5)
    );

    public static int getAgeMinimumOld(String genre) {
        int ageMinimum = 0;
        switch (genre) {
            case "Horreur":
                ageMinimum = 16;
                break; // Et si j'oublie ce break ? Bug !
            // ...
        }
        return ageMinimum;
    }

    public static int getAgeMinimum(String genre) {
        int ageMinimum = switch (genre) {
            case "Horreur" -> 16;
            case "Action", "Thriller" -> 12; // Regroupement natif
            case "Animation", "Comédie" -> 0;
            default -> 10;
        };
        return ageMinimum;
/*        return switch (genre) {
            case "Horreur" -> 16;
            case "Action", "Thriller" -> 12; // Regroupement natif
            case "Animation", "Comédie" -> 0;
            default -> 10;
        };*/
    }

    // ==========================================
    // Démonstration "Avant" : La méthode à l'ancienne
    // ==========================================
    public static Film chercherFilmOld(String titreRecherche) {
        for (Film f : films) {
            if (f.titre().equalsIgnoreCase(titreRecherche)) {
                return f; // On retourne l'objet directement
            }
        }
        return null; // Le danger : rien n'avertit le développeur que ça peut être null
    }

    // ==========================================
    // Démonstration "Après" : La méthode moderne
    // ==========================================
    public static Optional<Film> chercherFilm(String titreRecherche) {
        for (Film f : films) {
            if (f.titre().equalsIgnoreCase(titreRecherche)) {
                return Optional.of(f); // On "emballe" le film trouvé dans la boîte
            }
        }
        return Optional.empty(); // On renvoie une boîte explicitement vide
    }


    // ==========================================
    // Démonstration "Avant" : L'approche impérative
    // ==========================================
    public static void demontrerBoucleClassique() {
        List<String> blockbusters = new ArrayList<>(); // 1. Initialisation d'un état mutable

        for (Film f : films) {
            // 2. Complexité (conditions imbriquées)
            if ("Sci-Fi".equals(f.genre()) && f.annee() > 2010) {
                blockbusters.add(f.titre().toUpperCase()); // 3. Mutation de la liste externe
            }
        }

        System.out.println("Classique : " + blockbusters);
    }

    // ==========================================
    // Démonstration "Après" : L'approche déclarative (Streams)
    // ==========================================
    public static void demontrerStreams() {
        List<String> blockbusters = films.stream()
                .filter(f -> "Sci-Fi".equals(f.genre())) // Étape 1 : Isolation du genre
                .filter(f -> f.annee() > 2010)           // Étape 2 : Isolation de l'année
                .map(f -> f.titre().toUpperCase())       // Étape 3 : Extraction et transformation
                .toList();                                    // Étape 4 : Terminaison et collecte

        System.out.println("Stream : " + blockbusters);
    }

}
