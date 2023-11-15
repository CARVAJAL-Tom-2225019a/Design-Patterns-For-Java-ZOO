package controllerApplication;

import zoo.ZooFantastique;

public class ControllerGestionAuto {
	//private static final int NB_ACTION_MAX_AUTO = 20;
	//private int nbAction = 0;
	private ZooFantastique zoo = ZooFantastique.getInstance();
	ControllerPrincipal control = new ControllerPrincipal();
	
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
	
}
