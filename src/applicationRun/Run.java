package applicationRun;

import controllerApplication.ControllerPrincipal;
import viewApplication.VueUtilisateur;
/**
 * Point d'entree de l'application
 */
public class Run {
    // Le contrôleur principal de l'application
	static VueUtilisateur vueUtilisateur = new VueUtilisateur();
	static ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
    // L'instance unique du zoo fantastique (utilisation du pattern Singleton)
    public static boolean utilisateurControle;
    
    /**
     * Constructeur
     */
    public Run() { }

    /**
     * Point d'entrée de l'application
     * @param args vide
     * @throws Exception si une erreur survient lors de l'éxécution de l'application
     */
    public static void main(String[] args) throws Exception {
        // Point d'entrée de la simulation

        // Crée les données de jeu nécessaires à la simulation
    	Donnees.creerDonneesJeu();
    	
    	//Choix du mode de simulation
    	int choix;
		while (true) {
		    try {
		        String input = vueUtilisateur.demandeUtilisateur("""
                        CHOIX MODE SIMULATION
                         1 : Automatique
                         2 : Manuel
                        """);
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
    }
}