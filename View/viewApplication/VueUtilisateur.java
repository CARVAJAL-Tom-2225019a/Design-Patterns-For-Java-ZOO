package viewApplication;

import controllerTemps.Enum_ActionsPossibles;
import controllerTemps.GestionnaireTemps;
import maitreZoo.MaitreZoo;
import references.Enum_Sexe;

import java.util.Scanner;

/**
 * Classe permettant l'affichage et la recuperation d'infomration
 * pour la gestion manuelle du zoo
 */
public class VueUtilisateur {
	// Scanner pour la saisie utilisateur
	private final Scanner scanner = new Scanner(System.in);
	// Instance unique du gestionnaire de temps
	private final GestionnaireTemps temps = GestionnaireTemps.getInstance();

	/**
	 * Constructeur
	 */
	public VueUtilisateur() { }
	
	
	/**
	 * Méthode pour afficher le message de bienvenue et initialiser le gestionnaire
	 * du zoo lorsque l'utilisateur a le contrôle
	 */
	public void bienvenue() {
		System.out.println("======  BIENVENUE DANS VOTRE ZOO FANTASTIQUE  ======");

		// Saisie des informations du joueur
		String nom = demandeUtilisateur("Votre nom : ");
		// Recuperation sexe
		Enum_Sexe sexe = recupererSexe();
		// Recuperation age
		int age = recupererAge();
		// Message de bienvenue
		System.out.println("\nVous etes desormais maitre de votre zoo. \nJe suis sur que"
				+ " vous serez un tres bon gestionnaire ! \nBonne chance " + nom);
		// Initialisation du gestionnaire du zoo
		MaitreZoo.getInstance(nom, sexe, age);
	}
	
	
	/**
	 * Methode permettant de recuperer l'age tant que ce n'est pas un entier
	 * @return entier entre par l'utilisateur
	 */
	private int recupererAge() {
		int age;
		while (true) {
		    try {
		        String input = demandeUtilisateur("Votre age : ");
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
	private Enum_Sexe recupererSexe() {
		Enum_Sexe sexe = null;
		while (sexe == null) {
		    try {
		        String input = demandeUtilisateur("Votre sexe (F ou M) : ");
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
	public void proposerAction() {
		System.out.println(
				"\nLES ACTIONS DISPONIBLES (annee " + temps.getAnnee() + "):" 
						+ "\n"
						+ "\n  0 : Pas d'action ("+Enum_ActionsPossibles.PAS_D_ACTION.getDureeTotale()+")"
						+ "\n"
						+ "\n  1 : Voir les enclos ("+Enum_ActionsPossibles.VOIR_ENCLOS_EXISTANTS.getDureeTotale()+")"
						+ "\n  2 : Voir le nombre de creatures totales ("+Enum_ActionsPossibles.VOIR_NOMBRE_CREATURES_TOTALES.getDureeTotale()+")"
						+ "\n  3 : Creer un nouvel enclos ("+Enum_ActionsPossibles.CREER_ENCLOS.getDureeTotale()+")"
						+ "\n  4 : Examiner un enclos ("+Enum_ActionsPossibles.EXAMINER_ENCLOS.getDureeTotale()+")"
						+ "\n"
						+ "\n  5 : Nettoyer un enclos ("+Enum_ActionsPossibles.NETTOYER_ENCLOS.getDureeTotale()+")"
						+ "\n  6 : Nourrir les creatures d'un enclos ("+Enum_ActionsPossibles.NOURRIR_CREATURES.getDureeTotale()+")"
						+ "\n  7 : Soigner un enclos ("+Enum_ActionsPossibles.SOIGNER_ENCLOS.getDureeTotale()+")"
						+ "\n  8 : Endormir un enclos ("+Enum_ActionsPossibles.DORMIR_ENCLOS.getDureeTotale()+")"
						+ "\n  9 : Reveiller un enclos ("+Enum_ActionsPossibles.REVEILLER_ENCLOS.getDureeTotale()+")"
						+ "\n"
						+ "\n  10 : Transferer une creature ("+Enum_ActionsPossibles.TRANSFERER_CREATURE.getDureeTotale()+")"
						+ "\n  11 : Transferer un enclos ("+Enum_ActionsPossibles.TRANSFERER_ENCLOS.getDureeTotale()+")"
						+ "\n"
						+ "\n  12 : Concevoir un enfant ("+Enum_ActionsPossibles.CONCEVOIR_ENFANT.getDureeTotale()+")"
						+ "\n  13 : Voir la liste des creatures qui vont bientot naitre ("+Enum_ActionsPossibles.VOIR_BEBES_EN_CONSTRUCTION.getDureeTotale()+")"
						+ "\n"
						+ "\n  14 : Organiser une seance de sport pour un enclos ("+Enum_ActionsPossibles.METTRE_ENCLOS_EN_MOUVEMENT.getDureeTotale()+")"
						+ "\n  15 : Organiser un concert prive avec les creatures ("+Enum_ActionsPossibles.FAIRE_CHANTER_ENCLOS.getDureeTotale()+")"
						+ "\n  16 : Lancer un combat entre deux Créatures ("+Enum_ActionsPossibles.COMBAT.getDureeTotale()+")"
						+ "\n"
						+ "\n  17 : Voir l'ensemble des meutes ("+Enum_ActionsPossibles.VOIR_MEUTES.getDureeTotale()+")"
						+ "\n  18 : Voir l'ensemble des lycanthropes ("+Enum_ActionsPossibles.VOIR_LOUPS.getDureeTotale()+")"
						+ "\n  19 : Verifier si il est temps pour les lycanthropes de se reproduire ("+Enum_ActionsPossibles.SAISON_AMOUR_LOUPS.getDureeTotale()+")"
						+ "\n  20 : Faire hurler un lycanthrope ("+Enum_ActionsPossibles.FAIRE_HURLER_LOUP.getDureeTotale()+")"
						+ "\n  21 : Tenter de defier le male Alpha dans une meute ("+Enum_ActionsPossibles.DEFIER_MALE_ALPHA.getDureeTotale()+")"
						+ "\n"
						+ "\n  98 : Passer en mode automatique"
						+ "\n  99 : Exit" 
						+ "\n"
						+ "\n   ***  Nous somme le "+temps.getDateActuelle()+"  ***  " 
						+ "\n"
						+ "\n Votre choix = ");
	}

	
	/**
	 * Méthode pour récupérer le choix de l'utilisateur
	 * 
	 * @return un entier correspond à une action
	 */
	public int recupererChoixAction() {
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
	 * 
	 * @param chaine à afficher pour donner des indications à l'utilisateur
	 * @return la chaine de caractère entrée par l'utilisateur
	 */
	public String demandeUtilisateur(String chaine) {
		System.out.println(chaine);
		return scanner.nextLine();
	}

}
