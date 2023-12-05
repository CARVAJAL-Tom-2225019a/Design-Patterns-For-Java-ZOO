package references;

/**
 * Enumération des prénoms que peuvent prendre les créatures femelles
 */
public enum Enum_PrenomFeminin {
    Marie,
    Anne,
    Sophie,
    Isabelle,
    Catherine,
    Nathalie,
    Francoise,
    Martine,
    Valerie,
    Veronique,
    Sylvie,
    Ginette,
    Christine,
    Monique,
    Jiclette,
    Chantal,
    Helene,
    Laurence,
    Virginie,
    Sandrine,
    Patricia,
    Annie,
    Brigitte,
    Pascale,
    Corinne,
    AnneMarie,
    Elisabeth,
    Michele,
    Georgette,
    Jacqueline,
    Carole,
    Beatrice,
    Pepita;
	
	/**
	 * Méthode permettant de récupérer aléatoirement un prénom féminin
	 * @return le prénom féminin aléatoire
	 */
    public static Enum_PrenomFeminin getRandomName(){
        return Enum_PrenomFeminin.values()[(int) (Math.random() * Enum_PrenomFeminin.values().length)];
    }
}
