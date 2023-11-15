package viewApplication;

import controllerApplication.ControllerPrincipal;
import maitreZoo.MaitreZoo;
import references.CONSTANTES;
import references.Enum_Sexe;

public class VueAutomatique {
	private static final CONSTANTES constantes = new CONSTANTES();
	// Instance du contrôleur principal
	private static final ControllerPrincipal control = new ControllerPrincipal();
	
	public MaitreZoo Bienvenue() {
		System.out.println("======  BIENVENUE DANS VOTRE ZOO FANTASTIQUE  ======");
		System.out.println("Vous avez choisi une gestion automatique du Zoo\n"
				+ "Le zoo va exister pour une durée de 10 ans");
		System.out.println("INFORMATION : La duree de vie d'une creature est de " + constantes.MAX_AGE+"\n");
		// Initialisation du gestionnaire du zoo
		return MaitreZoo.getInstance("Maitre", Enum_Sexe.Male, 20);
	}
	
	
	/**
	 * Méthode pour afficher le message de passage à la nouvelle année et apeller le
	 * controlleur qui effectuera les actions
	 */
	public void PassageAnnee() throws Exception {
		System.out.println("\n ====== FIN ANNEE ====== \n");
		System.out.println(control.NouvelleAnnee());
		System.out.println("\n ====== NOUVELLE ANNEE ====== \n");
	}
		
	public void run() {
		//TODO
		//point entree gestion automatique
	}
}
