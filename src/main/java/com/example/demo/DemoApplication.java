package com.example.demo;

import com.example.demo.model.Pokemon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
// =====================================================================
		// ÉTAPE 1 : SETUP & MODERN JAVA
		// Concepts : Text Blocks ("""), inférence de type (var), Record
		// =====================================================================
		System.out.println("""
                ==============================
                  DÉMARRAGE DU POKÉDEX
                ==============================
                """);

		var pokedex = List.of(
				new Pokemon(1, "Bulbizarre", "Plante", 7, 45),
				new Pokemon(4, "Salamèche", "Feu", 9, 39),
				new Pokemon(6, "Dracaufeu", "Feu", 90, 78),
				new Pokemon(7, "Carapuce", "Eau", 9, 44),
				new Pokemon(25, "Pikachu", "Électrik", 6, 35),
				new Pokemon(130, "Léviator", "Eau", 235, 95),
				new Pokemon(143, "Ronflex", "Normal", 460, 160)
		);

		System.out.println("Faiblesse de Salamèche : " + getElementWeakness(pokedex.get(1)));


		// =====================================================================
		// ÉTAPE 2 : FONDATIONS FONCTIONNELLES & OPTIONAL
		// Concepts : Démystification des NullPointerException
		// =====================================================================
		System.out.println("\n--- Recherche avec Optional ---");
		Optional<Pokemon> result = findPokemonById(pokedex, 130);

		result.ifPresentOrElse(
				p -> System.out.println("Trouvé : " + p.name()),
				() -> System.out.println("Pokémon introuvable")
		);


		// =====================================================================
		// ÉTAPE 3 : LES STREAMS
		// Concepts : filter, map, max, collect
		// =====================================================================

		// Niveau 1 : Filtrer
		System.out.println("\n--- Niveau 1 : Filtrer (Type Feu) ---");
		pokedex.stream()
				.filter(p -> p.type().equals("Feu"))
				.forEach(p -> System.out.println(p.name()));

		// Niveau 2 : Transformer
		System.out.println("\n--- Niveau 2 : Transformer (Noms des +50kg en majuscules) ---");
		List<String> heavyPokemons = pokedex.stream()
				.filter(p -> p.weight() > 50)
				.map(p -> p.name().toUpperCase())
				.toList(); // .toList() remplace .collect(Collectors.toList()) depuis Java 16
		System.out.println(heavyPokemons);

		// Niveau 3 : Réduire
		System.out.println("\n--- Niveau 3 : Réduction (Le plus de HP) ---");
		pokedex.stream()
				.max((p1, p2) -> Integer.compare(p1.hp(), p2.hp()))
				.ifPresent(p -> System.out.println("Plus de HP : " + p.name() + " avec " + p.hp() + " HP"));
/*		pokedex.stream()
				.max(Comparator.comparingInt(Pokemon::hp))
				.ifPresent(p -> System.out.println("Plus de HP : " + p.name() + " avec " + p.hp() + " HP"));*/

		// Niveau 4 : Grouper
		System.out.println("\n--- Niveau 4 : Groupement par Type ---");
		Map<String, List<Pokemon>> byType = pokedex.stream()
				.collect(Collectors.groupingBy(Pokemon::type));

		byType.forEach((type, list) -> {
			// Affichage propre pour lier la clé à la liste des noms
			System.out.println(type + " -> " + list.stream().map(Pokemon::name).toList());
		});
	}

	// -------------------------------------------------------------------------
	// MÉTHODES UTILITAIRES
	// -------------------------------------------------------------------------

	/**
	 * Illustre le Pattern Matching du Switch (Java 21).
	 * Obligation d'exhaustivité et suppression des "break".
	 */
	private static String getElementWeakness(Pokemon pokemon) {
		return switch (pokemon.type()) {
			case "Feu" -> "Eau";
			case "Eau" -> "Plante";
			case "Plante", "Insecte" -> "Feu"; // Regroupement de cas
			case "Électrik" -> "Sol";
			default -> "Inconnue";
		};
	}

	/**
	 * Illustre la transition entre l'approche impérative classique (for loop)
	 * et la sécurisation des retours avec Optional.
	 */
	private static Optional<Pokemon> findPokemonById(List<Pokemon> list, int id) {
		for (Pokemon p : list) {
			if (p.id() == id) {
				return Optional.of(p);
			}
		}
		return Optional.empty();
	}

}
