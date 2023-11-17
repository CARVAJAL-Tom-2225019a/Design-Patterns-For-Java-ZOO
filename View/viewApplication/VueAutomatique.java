package viewApplication;

import maitreZoo.MaitreZoo;
import references.CONSTANTES;
import references.Enum_Sexe;

public class VueAutomatique {

	public MaitreZoo Bienvenue() {
		System.out.println("======  BIENVENUE DANS LE ZOO FANTASTIQUE  ======");
		System.out.println("Vous avez choisi une gestion automatique du Zoo\n"
				+ "Le zoo va exister pour une durée de"+CONSTANTES.DUREE_VIE_ZOO+" ans");
		System.out.println("INFORMATION : La duree de vie d'une creature est de " + CONSTANTES.MAX_AGE+"\n");
		// Initialisation du gestionnaire du zoo
		return MaitreZoo.getInstance("Maitre", Enum_Sexe.Male, 20);
	}
	
}
