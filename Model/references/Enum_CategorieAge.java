package references;

/**
 * Enumeration comprenant les différentes catégories d'age d'une créature
 */
public enum Enum_CategorieAge {
	BEBE("Bebe", 0, CONSTANTES.MAX_AGE_BEBE), // De 1 an à l'infini
	ENFANT("Enfant", BEBE.getAgeMax() + 1, BEBE.getAgeMax() + 5),
	JEUNE("Jeune", ENFANT.getAgeMax() + 1, ENFANT.getAgeMax() + 10),
	ADULTE("Adulte", JEUNE.getAgeMax() + 1, JEUNE.getAgeMax() + 20),
	VIEUX("Vieux", ADULTE.getAgeMax() + 1, CONSTANTES.MAX_AGE),
	MORT("Mort", CONSTANTES.MAX_AGE +1, Integer.MAX_VALUE);

	private final String libelle;
	private final int ageMin;
	private final int ageMax;

	/**
	 * Constructeur
	 * @param libelle de la categorie
	 * @param ageMin de la categorie
	 * @param ageMax de la categorie
	 */
	Enum_CategorieAge(String libelle, int ageMin, int ageMax) {
		this.libelle = libelle;
		this.ageMin = ageMin;
		this.ageMax = ageMax;
	}

	/**
	 * Methode permettant de recuperer le libelle de la categorie d'age
	 * @return le libelle de la categorie
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Methode permettant de recuperer l'age minimal de la categorie
	 * @return l'age minimal de la categorie
	 */
	public int getAgeMin() {
		return ageMin;
	}

	/**
	 * Methode permettant de recuperer l'age maximal de la categorie d'age
	 * @return l'age maximal de la categorie
	 */
	public int getAgeMax() {
		return ageMax;
	}

	/**
	 * Methode permettant de recuperer la categorie correspondante à un age
	 * @param age de la creature
	 * @return la categorie correspondante à l'age
	 */
	public static Enum_CategorieAge getCategorieByAge(int age) {
		for (Enum_CategorieAge categorie : values()) {
			if (age >= categorie.getAgeMin() && age <= categorie.getAgeMax()) {
				return categorie;
			}
		}
		return null; // Peut-être gérer le cas où aucune catégorie ne correspond à l'âge donné
	}
}