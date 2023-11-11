package maitreZoo;

import java.util.Scanner;

import references.*;

public class MenuMaitreZoo {
	MaitreZoo maitreZoo;
	
	Scanner scanner = new Scanner(System.in);
	
	public void Bienvenue() {
		System.out.println("======  BIENVENUE DANS VOTRE ZOO FANTASTIQUE  ======");
		
		String nom = DemandeUtilisateur("Votre nom : ");
		
		String s = DemandeUtilisateur("Votre sexe (M ou F) : ");
		Enum_Sexe sexe;
		if (s=="M")
			sexe = Enum_Sexe.Male;
		else
			sexe = Enum_Sexe.Femelle;
			
		String a = DemandeUtilisateur("Votre age : ");
		int age = Integer.parseInt(a);
		
		maitreZoo = MaitreZoo.getInstance(nom, sexe, age);
	}
	
	public void proposerAction() {
		System.out.println ("LES ACTIONS DISPONIBLES :"
				+ "\n  0 : Pas d'action"
				+ "\n  1 : Voir les enclos"
				+ "\n  2 : Examiner un enclos"
				+ "\n  3 : Nettoyer un enclos"
				+ "\n  4 : Nourrir les creatues d'un enclos"
				+ "\n  5 : Transferer une creature");
	}
	
	public int RecupererChoixAction() {
		String s = scanner.nextLine();
		return Integer.parseInt(s); 
	}
	
	public String DemandeUtilisateur(String chaine) {
		System.out.println(chaine);
		return scanner.nextLine();
	}
	
	public void effectuerAction (int choix) {
		switch (choix) {
			// Ne pas effectuer d'action
			case 0 :
				System.out.println(" ---- Vous avez choisi de ne pas effectuer d'action ---- ");
				break;
			// Voir les enclos
			case 1 :
				System.out.println(" ---- Vous avez choisi de voir les enclos existants ---- ");
				//TODO
				break;
			// Examiner un enclos
			case 2 :
				System.out.println(" ---- Vous avez choisi d'examiner un enclos ---- ");
				break;
			// Nettoyer un enclos
			case 3 :
				System.out.println(" ---- Vous avez choisi de nettoyer un enclos ---- ");
				break;
			// Nourrir les creatures d'un enclos
			case 4 :
				System.out.println(" ---- Vous avez choisi de nourrir les cretaures d'un enclos ---- ");
				break;
			// Transferer une creature
			case 5 :
				System.out.println(" ---- Vous avez choisi de transferer une creature ---- ");
				break;
			default:
				System.out.println("Choix invalide");
		}
		
	}
}
