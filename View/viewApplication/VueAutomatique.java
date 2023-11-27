package viewApplication;

import maitreZoo.MaitreZoo;
import references.Enum_Sexe;

/**
 * Classe permettant l'affichage concernant la gestion
 * automatique du zoo
 */
public class VueAutomatique {

	public MaitreZoo bienvenue() {
		System.out.println("======  BIENVENUE DANS LE ZOO FANTASTIQUE  ======");
		System.out.println("Vous avez choisi une gestion automatique du Zoo\n");
		// Initialisation du gestionnaire du zoo
		return MaitreZoo.getInstance("Maitre", Enum_Sexe.Male, 20);
	}
	
}
