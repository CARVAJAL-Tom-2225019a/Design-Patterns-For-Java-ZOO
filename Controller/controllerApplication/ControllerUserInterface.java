package controllerApplication;

import base.Creature;
import enclosImplemente.Enclos;
import viewApplication.VueGlobale;
import viewApplication.VueUtilisateur;
import zoo.ZooFantastique;

/**
 * Classe representant le controleur de la gestion manuel du zoo
 */
public class ControllerUserInterface {
	private ZooFantastique zoo = ZooFantastique.getInstance();
    private final VueGlobale VueGlobale;
    private final VueUtilisateur VueUtilisateur;
    private final ControllerZoo zooController;

    
    /**
     * Constructeur
     */
    public ControllerUserInterface() {
        this.VueGlobale = new VueGlobale();
        this.VueUtilisateur = new VueUtilisateur();
        this.zooController = new ControllerZoo();
    }

    
    /**
     * Methode permeyttant de generer le menu utilisateur et de gerer l'application
     */
    public void runUserMenu() {
        int choix;
        boolean run = true;
        try {
        	zooController.init();
            while (run) {
            	VueUtilisateur.proposerAction(zooController.getAnnee(), zooController.getActionRestante());
                choix = VueUtilisateur.RecupererChoixAction();
                run = zooController.effectuerAction(choix);
                // Appel de la méthode run pour gérer le passage d'année
                zooController.runYear();
                // Si plus de creature
                if (zoo.getNbCreaturesTotales() == 0)
                	run = false;
            }
            VueGlobale.Afficher("\n =====  FIN DE LA SIMULATION  ======\n");
        }
        catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
    
    /**
     * Methode permettant de selectionner une creature dans un enclos
     */
    public Creature SelectionCreatureDansEnclos(Enclos enclos) {
    	String indexCreatureString;
    	int indexCreature;
    	VueGlobale.Afficher(enclos.toString());
    	indexCreatureString = VueUtilisateur.DemandeUtilisateur("Index creature : ");
    	indexCreature = Integer.parseInt(indexCreatureString);
    	return enclos.getListeCreatures().get(indexCreature);
    }


}
