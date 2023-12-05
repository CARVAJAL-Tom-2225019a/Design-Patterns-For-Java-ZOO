package references;

/**
 * Enumeration des prénoms masculins que peuvent prendre les créatures
 */
public enum Enum_PrenomMasculin {
	
    Didier,George,JeanEude, Patrick, Gerard, pepito, Jean, Pierre, Paul, Jacques, Michel, Andre, Alain, Bernard, Robert, Serge, Christian, Daniel, Claude, Henri, Gilles, Philippe, Yves;

	/**
	 * Méthode permettant de récupérer un prénom masculin de manière aléatoire
	 * @return le prénom sélectionné
	 */
    public static Enum_PrenomMasculin getRandomName(){
        return Enum_PrenomMasculin.values()[(int) (Math.random() * Enum_PrenomMasculin.values().length)];
    }
}
