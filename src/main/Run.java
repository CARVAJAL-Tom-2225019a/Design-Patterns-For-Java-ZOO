package main;

import controllerApplication.ControllerPrincipal;
import controllerApplication.ControllerUserInterface;
import controllerApplication.ControllerZoo;
import viewApplication.VueGlobale;
import viewApplication.VueUtilisateur;
import zoo.ZooFantastique;

	//TODO : ajout de fonctionnalités 
	//TODO : mort si etat enclos plus adapte ou etat cretaure


/**
 * Point d'entree de l'application
 */
public class Run {
    // Le contrôleur principal de l'application
	static VueUtilisateur vueUtilisateur = new VueUtilisateur();
	static VueGlobale vueGlobale = new VueGlobale();
    static ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
    ControllerZoo zooController = new ControllerZoo();
    ControllerUserInterface userInterfaceController = new ControllerUserInterface();

    // L'instance unique du zoo fantastique (utilisation du pattern Singleton)
    static ZooFantastique zoo = ZooFantastique.getInstance();
    
    public static boolean utilisateurControle;

    public static void main(String[] args) throws Exception {
        // Point d'entrée de la simulation

        // Crée les données de jeu nécessaires à la simulation
    	controllerPrincipal.creerDonneesJeu();
    	
    	//Choix du mode de simulation
    	int choix = 0;
		while (true) {
		    try {
		        String input = vueUtilisateur.DemandeUtilisateur("CHOIX MODE SIMULATION\n 1 : Automatique\n"
		    			+ " 2 : Manuel\n");
		        choix = Integer.parseInt(input);
		        // Si la conversion en entier réussit, sortir de la boucle
		        if (choix==1) {
		        	utilisateurControle=false;
		    		break;
		        }
		    	else if (choix==2) {
		    		utilisateurControle=true;
		    		break;
		    	}     
		    } catch (NumberFormatException e) {
		        // Si la conversion échoue, afficher un message d'erreur et continuer la boucle
		        System.out.println("Veuillez entrer 1 ou 2");
		    }
		}
        if (utilisateurControle)
	        // Passe le contrôle de l'application à l'utilisateur
        	controllerPrincipal.passerLaMainUtilisateur();
        else
        	controllerPrincipal.gestionAuto();
        	return;
    }
}
