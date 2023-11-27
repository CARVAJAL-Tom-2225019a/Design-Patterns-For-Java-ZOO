package controllerApplication;

import base.Creature;
import base.Enclos;
import viewApplication.VueGlobale;
import viewApplication.VueUtilisateur;
import zoo.ZooFantastique;

/**
 * Classe representant le controleur de la gestion manuel du zoo
 */
public class ControllerUserInterface {
	private ZooFantastique zoo = ZooFantastique.getInstance();
    private final VueGlobale vueGlobale;
    private final VueUtilisateur vueUtilisateur;
    private final ControllerActions zooController;

    
    /**
     * Constructeur
     */
    public ControllerUserInterface() {
        this.vueGlobale = new VueGlobale();
        this.vueUtilisateur = new VueUtilisateur();
        this.zooController = new ControllerActions();
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
            	vueUtilisateur.proposerAction();
                choix = vueUtilisateur.recupererChoixAction();
                run = zooController.effectuerAction(choix);
                // Si plus de creature
                if (zoo.getNbCreaturesTotales() == 0)
                	run = false;
            }
            vueGlobale.afficher("\n =====  FIN DE LA SIMULATION  ======\n");
        }
        catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }
    
    
    /**
     * Methode permettant de selectionner une creature dans un enclos
     */
    public Creature selectionCreatureDansEnclos(Enclos enclos) {
    	String indexCreatureString;
    	int indexCreature;
    	vueGlobale.afficher(enclos.toString());
    	indexCreatureString = vueUtilisateur.demandeUtilisateur("Index creature : ");
    	indexCreature = Integer.parseInt(indexCreatureString);
    	return enclos.getListeCreatures().get(indexCreature);
    }
    
    
    public Enclos recupererEnclosParNom() throws Exception {
    	String nomEnclos = vueUtilisateur.demandeUtilisateur("Nom de l'enclos : ");
        return zoo.trouverEnclosParNom(nomEnclos);
    }


}
