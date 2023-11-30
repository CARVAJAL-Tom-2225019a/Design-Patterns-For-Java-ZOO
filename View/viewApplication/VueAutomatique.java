package viewApplication;

import maitreZoo.MaitreZoo;
import references.Enum_Sexe;

/**
 * La classe VueAutomatique gère l'affichage lié à la gestion automatique du zoo
 */
public class VueAutomatique {

	/**
     * Affiche un message de bienvenue pour la gestion automatique du zoo.
     *
     * @return L'instance de MaitreZoo initialisée avec des valeurs par défaut.
     */
	public MaitreZoo bienvenue() {
		System.out.println("======  BIENVENUE DANS LE ZOO FANTASTIQUE  ======");
		System.out.println("Vous avez choisi une gestion automatique du Zoo\n");
		// Initialisation du gestionnaire du zoo
		return MaitreZoo.getInstance("Maitre", Enum_Sexe.Male, 20);
	}
	
}
