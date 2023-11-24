package viewApplication;

import maitreZoo.MaitreZoo;
import references.CONSTANTES;
import references.Enum_Sexe;

/**
 * Classe permettant l'affichage concernant la gestion
 * automatique du zoo
 */
public class VueAutomatique {

	public MaitreZoo Bienvenue() {
		System.out.println("======  BIENVENUE DANS LE ZOO FANTASTIQUE  ======");
		System.out.println("Vous avez choisi une gestion automatique du Zoo\n");
		System.out.println("INFORMATION : La duree de vie d'une creature est de " + CONSTANTES.MAX_AGE+" ans\n");
		// Initialisation du gestionnaire du zoo
		return MaitreZoo.getInstance("Maitre", Enum_Sexe.Male, 20);
	}
	
}
