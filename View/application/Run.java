package application;

import ControllerApplication.ControllerPrincipal;
import zoo.ZooFantastique;

public class Run {
    // Le contrôleur principal de l'application
    static ControllerPrincipal control = new ControllerPrincipal();

    // L'instance unique du zoo fantastique (utilisation du pattern Singleton)
    static ZooFantastique zoo = ZooFantastique.getInstance();

    public static void main(String[] args) throws Exception {
        // Point d'entrée de la simulation
    	
        // TODO : gestion manuel ou automatique

        // Crée les données de jeu nécessaires à la simulation
        control.creerDonneesJeu();

        // Passe le contrôle de l'application à l'utilisateur
        zoo.PasserLaMainUtilisateur();
    }
}
