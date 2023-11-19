package viewApplication;

import java.util.Scanner;

import maitreZoo.MaitreZoo;
import references.*;

/**
 * Classe permettant l'affichage et la recuperation d'infomration
 * pour la gestion manuel du zoo
 */
public class VueUtilisateur {
	// Scanner pour la saisie utilisateur
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Méthode pour afficher le message de bienvenue et initialiser le gestionnaire
	 * du zoo lorsque l'utilisateur a le controle
	 */
	public MaitreZoo Bienvenue() {
		System.out.println("======  BIENVENUE DANS VOTRE ZOO FANTASTIQUE  ======");

		// Saisie des informations du joueur
		String nom = DemandeUtilisateur("Votre nom : ");
		// Recuperation sexe
		Enum_Sexe sexe = RecupererSexe();
		// Recuperation age
		int age = RecupererAge();
		// Message de bienvenue
		System.out.println("\nVous etes desormais maitre de votre zoo. \nJe suis sur que"
				+ " vous serez un tres bon gestionnaire ! \nBonne chance " + nom);
		System.out.println("\n INFORMATION : La duree de vie d'une creature est de " + CONSTANTES.MAX_AGE+" ans.");
		// Initialisation du gestionnaire du zoo
		return MaitreZoo.getInstance(nom, sexe, age);
	}
	
	
	/**
	 * Methode permettant de recuperer l'age tant que ce n'est pas un entier
	 * @return entier entre par l'utilisateur
	 */
	private int RecupererAge() {
		int age;
		while (true) {
		    try {
		        String input = DemandeUtilisateur("Votre age : ");
		        age = Integer.parseInt(input);
		        // Si la conversion en entier réussit, sortir de la boucle
		        break;
		    } catch (NumberFormatException e) {
		        // Si la conversion échoue, afficher un message d'erreur et continuer la boucle
		        System.out.println("Veuillez entrer un nombre entier valide");
		    }
		}
		return age;
	}
	
	
	/**
	 * Methode permettant de recuperer F ou M
	 * et de traduire selon l'enumeration sexe
	 * @return	le sexe choisi par l'utilisateur
	 */
	private Enum_Sexe RecupererSexe() {
		Enum_Sexe sexe = null;
		while (sexe == null) {
		    try {
		        String input = DemandeUtilisateur("Votre sexe (F ou M) : ");
		        if (input.equalsIgnoreCase("F")) {
		            sexe = Enum_Sexe.Femelle;
		        } else if (input.equalsIgnoreCase("M")) {
		            sexe = Enum_Sexe.Male;
		        } else {
		            System.out.println("Veuillez entrer F ou M.");
		        }
		    } catch (Exception e) {
		        // Gérer l'exception si nécessaire
		        System.out.println("Erreur lors de la saisie");
		    }
		}
		return sexe;
	}


	/**
	 * Méthode pour afficher les actions disponibles à l'utilisateur
	 */
	public void proposerAction(int annee, int actionRestante) {
		System.out.println(
				"\nLES ACTIONS DISPONIBLES (annee " + annee + "):" 
						+ "\n\n  0 : Pas d'action" 
						+ "\n"
						+ "\n  1 : Voir les enclos"
						+ "\n  2 : Voir le nombre de creatures totales"
						+ "\n  3 : Creer un nouvel enclos" 
						+ "\n  4 : Examiner un enclos"
						+ "\n"
						+ "\n  5 : Nettoyer un enclos" 
						+ "\n  6 : Nourrir les creatures d'un enclos"
						+ "\n"
						+ "\n  7 : Transferer une creature"
						+ "\n  8 : Transferer un enclos"
						+ "\n"
						+ "\n  9 : Concevoir un enfant"
						+ "\n  10 : Voir la liste des creatures qui vont bientot naitre"
						+ "\n"
						+ "\n  11 : Organiser une seance de sport pour un enclos"
						+ "\n  12 : Organiser un concert prive avec les creatures"
						+ "\n"
						+ "\n  99 : Exit" 
						+ "\n\n Il vous reste " + actionRestante+" action(s) a effectuer avant de changer d'annee" 
						+ "\n\n Votre choix = ");
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

}
