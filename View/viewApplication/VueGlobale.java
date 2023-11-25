package viewApplication;

import controllerApplication.ControllerPrincipal;

/**
 * Classe permettant l'affichage d'informations
 */
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
			String chaine = control.NouvelleAnnee();
			if (chaine != null)
				System.out.println(chaine);
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
