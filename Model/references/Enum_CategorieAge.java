package references;


public enum Enum_CategorieAge {
	BEBE("Bébé", 0, CONSTANTES.MAX_AGE_BEBE), // De 1 an à l'infini
	ENFANT("Enfant", BEBE.getAgeMax() + 1, BEBE.getAgeMax() + 5),
	JEUNE("Jeune", ENFANT.getAgeMax() + 1, ENFANT.getAgeMax() + 10),
	ADULTE("Adulte", JEUNE.getAgeMax() + 1, JEUNE.getAgeMax() + 20),
	VIEUX("Vieux", ADULTE.getAgeMax() + 1, CONSTANTES.MAX_AGE),
	MORT("Mort", CONSTANTES.MAX_AGE +1, Integer.MAX_VALUE);

	private final String libelle;
	private final int ageMin;
	private final int ageMax;

	Enum_CategorieAge(String libelle, int ageMin, int ageMax) {
		this.libelle = libelle;
		this.ageMin = ageMin;
		this.ageMax = ageMax;
	}

	public String getLibelle() {
		return libelle;
	}

	public int getAgeMin() {
		return ageMin;
	}

	public int getAgeMax() {
		return ageMax;
	}

	public static Enum_CategorieAge getCategorieByAge(int age) {
		for (Enum_CategorieAge categorie : values()) {
			if (age >= categorie.getAgeMin() && age <= categorie.getAgeMax()) {
				return categorie;
			}
		}
		return null; // Peut-être gérer le cas où aucune catégorie ne correspond à l'âge donné
	}
}