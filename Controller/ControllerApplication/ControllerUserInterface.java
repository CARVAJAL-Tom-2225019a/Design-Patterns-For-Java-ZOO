package ControllerApplication;

import application.VueUtilisateur;
import zoo.ZooFantastique;

public class ControllerUserInterface {
	private ZooFantastique zoo = ZooFantastique.getInstance();
    private final VueUtilisateur vue;
    private final ControllerZoo zooController;

    /**
     * Constructeur
     */
    public ControllerUserInterface() {
        this.vue = new VueUtilisateur();
        this.zooController = new ControllerZoo();
    }

    
    /**
     * Methode permeyttant de generer le menu utilisateur et de gerer l'application
     * @throws Exception
     */
    public void runUserMenu() throws Exception {
        int choix;
        boolean run = true;
        zooController.init();
        while (run) {
            vue.proposerAction(zooController.getAnnee(), zooController.getActionRestante());
            choix = vue.RecupererChoixAction();
            run = zooController.effectuerAction(choix);
            // Appel de la méthode run pour gérer le passage d'année
            zooController.runYear();
            // Si plus de creature
            if (zoo.getNbCreaturesTotales() == 0)
            	run = false;
        }
        vue.Afficher("\n =====  FIN DE LA SIMULATION  ======\n");
    }


}
