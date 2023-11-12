package application;

import java.util.Scanner;
import ControllerApplication.ControllerPrincipal;
import maitreZoo.MaitreZoo;
import references.*;

public class VuePrincipale {
	// Scanner pour la saisie utilisateur
	private Scanner scanner = new Scanner(System.in);
	// Instance de CONSTANTES pour les références constantes
	private CONSTANTES constantes = new CONSTANTES();
	// Instance du contrôleur principal
	private static ControllerPrincipal control = new ControllerPrincipal();

	/**
	 * Méthode pour afficher le message de bienvenue et initialiser le gestionnaire
	 * du zoo
	 */
	public MaitreZoo Bienvenue() {
		System.out.println("======  BIENVENUE DANS VOTRE ZOO FANTASTIQUE  ======");

		// Saisie des informations du joueur
		String nom = DemandeUtilisateur("Votre nom : ");
		String s = DemandeUtilisateur("Votre sexe (M ou F) : ");
		Enum_Sexe sexe = (s.equals("M")) ? Enum_Sexe.Male : Enum_Sexe.Femelle;
		int age = Integer.parseInt(DemandeUtilisateur("Votre age : "));

		// Message de bienvenue
		System.out.println("Vous etes desormais maître de votre zoo. \nJe suis sur que"
				+ " vous serez un tres bon gestionnaire ! Bonne chance " + nom);
		System.out.println("\n INFORMATION : La duree de vie d'une creature est de " + constantes.MAX_AGE);
		// Initialisation du gestionnaire du zoo
		return MaitreZoo.getInstance(nom, sexe, age);
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

	/**
	 * Méthode pour afficher les actions disponibles à l'utilisateur
	 */
	public void proposerAction(int annee, int actionRestante) {
		System.out.println(
				"\nLES ACTIONS DISPONIBLES (annee " + annee + "):" + "\n  0 : Pas d'action" + "\n  1 : Voir les enclos"
						+ "\n  2 : Voir le nombre de creatures totales" 
						+ "\n  3 : Examiner un enclos"
						+ "\n  4 : Nettoyer un enclos" 
						+ "\n  5 : Nourrir les créatures d'un enclos"
						+ "\n  6 : Transférer une créature" 
						+ "\n  99 : Exit" + "\n\n Il vous reste " + actionRestante
						+ " action(s) a effectuer avant de changer d'annee" + "\n\n Votre choix = ");
	}

	/**
	 * Méthode pour récupérer le choix de l'utilisateur
	 */
	public int RecupererChoixAction() {
		int choix = -1; // Valeur par défaut ou valeur impossible pour indiquer une erreur
	    do {
	        try {
	            String s = scanner.nextLine();
	            choix = Integer.parseInt(s);
	        } catch (NumberFormatException e) {
	            System.out.println("Erreur : Veuillez entrer un nombre entier valide.");
	        }
	    } while (choix == -1);
	    return choix;
	}

	/**
	 * Méthode pour demander une saisie utilisateur avec un message donné
	 */
	public String DemandeUtilisateur(String chaine) {
		System.out.println(chaine);
		return scanner.nextLine();
	}

	/**
	 * Méthode pour afficher un texte a utilisateur
	 */
	public void Afficher(String texte) {
		System.out.println(texte);
	}
}
