package controllerApplication;

import base.Creature;
import base.Enclos;
import meuteLycanthrope.ColonieLycanthrope;
import meuteLycanthrope.Meute;
import references.CONSTANTES;
import viewApplication.VueGlobale;
import viewApplication.VueUtilisateur;
import zoo.ZooFantastique;

/**
 * Classe representant le controleur de la gestion manuel du zoo
 */
public class ControllerUserInterface {
	private ZooFantastique zoo = ZooFantastique.getInstance();
	private ColonieLycanthrope colonie = ColonieLycanthrope.getInstance();
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
     * Méthode permettant de générer le menu utilisateur et de gérer l'application
     */
    public void runUserMenu() {
        int choix;
        boolean run = true;
        try {
        	zooController.init();
            while (run) {
            	vueUtilisateur.proposerAction();
                choix = vueUtilisateur.recupererChoixAction();
                run = ControllerActions.effectuerAction(choix);
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
     * Méthode permettant de sélectionner une créature dans un enclos
     *
     * @param enclos L'enclos à partir duquel sélectionner une créature
     * @return La créature sélectionnée
     */
    public Creature selectionCreatureDansEnclos(Enclos enclos) {
    	String indexCreatureString;
    	int indexCreature;
    	indexCreatureString = vueUtilisateur.demandeUtilisateur("Index creature : ");
    	indexCreature = Integer.parseInt(indexCreatureString);
    	return enclos.getListeCreatures().get(indexCreature);
    }
    
    
    /**
     * Méthode permettant de récupérer un enclos par son nom
     *
     * @return L'enclos trouvé
     * @throws Exception En cas d'erreur lors de la recherche de l'enclos
     */
    public Enclos recupererEnclosParNom() throws Exception {
    	String nomEnclos = vueUtilisateur.demandeUtilisateur("Nom de l'enclos : ");
        return zoo.trouverEnclosParNom(nomEnclos);
    }
    
    
    /**
     * Methode permettant de recuperer une meutr par son nom
     * @return La meute trouvé
     * @throws Exception En cas d'erreur lors de la recherche de la meute
     */
    public Meute recupererMeuteParNom() throws Exception {
    	String nomMeute = vueUtilisateur.demandeUtilisateur("Nom de la meute : ");
        return colonie.trouverMeuteParNom(nomMeute);
    }
    
    
    /**
     * Methode permettant de selectionner une creature dans une meute
     * @param meute dans laquelle on cherche la creature
     * @return La creature sélectionnée
     * @throws InterruptedException si problème de thread
     */
    public Creature selectionCreatureDansMeute(Meute meute) throws InterruptedException {
    	// Recuperation enclos où il y a la meute
    	Enclos enclos = meute.getEnclosReference();
    	// Choix creature
    	vueGlobale.afficher("La meute "+meute.getNomMeute()+" se trouve dans l'enclos suivant.");
    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	return selectionCreatureDansEnclos(enclos);
    }


}
