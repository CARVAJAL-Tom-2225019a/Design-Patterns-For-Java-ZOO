package main;

import ControllerApplication.ControllerPrincipal;
import application.VueAutomatique;
import zoo.ZooFantastique;

	//TODO : voir en cas d'erreur d'entree pour chaque
	//TODO : ajout de fonctionnalités (enfant, creation enclos...)
	//TODO : gestion automatique
	//TODO : mort si etat enclos plus adapte ou etat cretaure


public class Run {
    // Le contrôleur principal de l'application
    static ControllerPrincipal control = new ControllerPrincipal();
    
    static VueAutomatique vueAutomatique = new VueAutomatique();

    // L'instance unique du zoo fantastique (utilisation du pattern Singleton)
    static ZooFantastique zoo = ZooFantastique.getInstance();
    
    static boolean UtilisateurChoisi = true;

    public static void main(String[] args) throws Exception {
        // Point d'entrée de la simulation
    	
        // TODO : gestion manuel ou automatique

        // Crée les données de jeu nécessaires à la simulation
        control.creerDonneesJeu();
        
        if (UtilisateurChoisi == true)
	        // Passe le contrôle de l'application à l'utilisateur
	        zoo.PasserLaMainUtilisateur();
        else
        	vueAutomatique.run();
    }
}
