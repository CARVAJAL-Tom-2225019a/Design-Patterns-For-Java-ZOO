package viewApplication;

import controllerApplication.ControllerPrincipal;

public class VueGlobale {
	// Instance du contrôleur principal
	private static ControllerPrincipal control = new ControllerPrincipal();
	
	
	/**
	 * Méthode pour afficher le message de passage à la nouvelle année et apeller le
	 * controlleur qui effectuera les actions
	 */
	public void PassageAnnee() {
		try {
			System.out.println("\n ====== FIN ANNEE ====== \n");
			System.out.println(control.NouvelleAnnee());
		}
		 catch (Exception e) {
			 Afficher(e.getMessage());
		 }
	}
	
	/**
	 * Méthode pour afficher un texte a utilisateur
	 */
	public void Afficher(String texte) {
		System.out.println(texte);
	}

}
