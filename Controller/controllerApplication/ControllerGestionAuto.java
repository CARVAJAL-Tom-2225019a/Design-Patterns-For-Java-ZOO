package controllerApplication;

import zoo.ZooFantastique;
import viewApplication.*;

public class ControllerGestionAuto {
	//private static final int NB_ACTION_MAX_AUTO = 20;
	//private int nbAction = 0;
	// constantes.DUREE_VIE_ZOO
	private static ControllerZoo zooController;
	private static VueGlobale VueGlobale;
	private ZooFantastique zoo;
	
	
	/**
     * Constructeur
     */
    public ControllerGestionAuto() {
        VueGlobale = new VueGlobale();
        new VueAutomatique();
        new ControllerPrincipal();
        zooController = new ControllerZoo();
        zoo = ZooFantastique.getInstance();
    }
	
	//TODO 
	
	public void ChoixActionAleatoire () throws Exception {
		int choix = 1;
		switch (choix) {
			case 1: 
				zoo.ModificationEtatAleatoire();
				break;
			default :
				break;
		}
	}
	
	public void run() throws Exception {
		boolean run = true;
        zooController.init();
        while (run) {
        	//TODO : suite gestion auto
        	
            // Si plus de creature
            if (zoo.getNbCreaturesTotales() == 0)
            	run = false;
        }
        VueGlobale.Afficher("\n =====  FIN DE LA SIMULATION  ======\n");
		
	}
	
}
