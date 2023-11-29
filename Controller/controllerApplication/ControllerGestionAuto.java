package controllerApplication;

import zoo.ZooFantastique;

import java.util.Random;

import base.Enclos;
import controllerTemps.GestionnaireTemps;
import references.CONSTANTES;
import viewApplication.*;

/**
 * Classe representant le controleur de la partie
 * gestion automatique du zoo
 */
public class ControllerGestionAuto {
	// Instance du contrôleur du zoo (où les différentes actions possibles sont stockées)
	private static ControllerActions zooController = new ControllerActions();
	// Instance de la vue globale
	private static VueGlobale VueGlobale;
	// Instance unique du zoo fantastique
	private static ZooFantastique zoo = ZooFantastique.getInstance();
	// Instance unique de la classe permettant la gestion du temp
	private static GestionnaireTemps temps = GestionnaireTemps.getInstance();
	
	
	/**
     * Constructeur
     */
    public ControllerGestionAuto() {
        VueGlobale = new VueGlobale();
        new VueAutomatique();
        new ControllerPrincipal();
    }
	
    
	//TODO : actions appropriés selon etat du zoo
	
    
    /**
     * Methode permettant d'effectuer un choix aleatoire 
     * @throws Exception
     */
	public static void choixActionAleatoire () throws Exception {
		Random random = new Random();
		int choix = random.nextInt(CONSTANTES.NUM_CHOIX_MAX); 
		zooController.effectuerAction(choix);
	}
	
	
	/**
	 * Point d'entree de la gestion automatique
	 * @throws Exception
	 */
	public static void run(boolean debut) throws Exception {
		boolean run = true;
		if (debut) {
			zooController.init();
			zooController.effectuerAction(1);
		}
        Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        while (run) {
        	choixActionAleatoire ();
        	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        	VueGlobale.afficher("\nNous sommes le "+temps.getDateActuelle()+"\n");
        	
        	// Si plus de creature
            if (zoo.getNbCreaturesTotales() == 0)
            	run = false;
            
        }
        VueGlobale.afficher("\n =====  FIN DE LA SIMULATION  ======\n");
	}
	
	
	/**
     * Méthode pour récupérer un enclos aléatoire dans le zoo.
     *
     * @return Un enclos choisi au hasard dans la liste des enclos du zoo.
     */
	public Enclos recuperationEnclosAleatoire() {
        // Vérifier s'il y a des enclos disponibles
        if (zoo.getListeEnclos().isEmpty()) {
            return null;
        }
        // Obtenez un indice aléatoire
        int indiceAleatoire = new Random().nextInt(zoo.getListeEnclos().size());
        // Retournez l'enclos correspondant à l'indice aléatoire
        return (Enclos) zoo.getListeEnclos().toArray()[indiceAleatoire];
	}
	
	
	
	/**
	 * Methode permettant de recuperer les enclos qui sont en mauvais etat
	 * @throws Exception 
	 */
	public Enclos getFirstEnclosMauvaisEtat() throws Exception {
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isEnclosMauvaisEtat())
				return e;
		}
		return null;
	}
	
	
	/**
	 * Methode permettant de recuperer les enclos où les creatures ont faim
	 */
	public Enclos getFirstEnclosCreatureFaim() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isCreatureOntFaim())
				return e;
		}
		return null;
	}
	
	
	/**
	 * Methode permettant de recuperer les enclos où les creatures ont sommeil
	 */
	public Enclos getFirstEnclosCreatureSommeil() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isCreatureOntSommeil())
				return e;
		}
		return null;
	}
	
	
	/**
	 * Methode permettant de recuperer les enclos où les creatures sont
	 * en mauvaise sante
	 */
	public Enclos getFirstEnclosCreatureMauvaiseSante() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isCreatureOntSommeil())
				return e;
		}
		return null;
	}
	
	
	/**
	 * Methode permettant de recuperer les enclos où les creatures dorment
	 */
	public Enclos getFirstEnclosCreatureDort() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isCreaturesDorment())
				return e;
		}
		return null;
	}
	
}
