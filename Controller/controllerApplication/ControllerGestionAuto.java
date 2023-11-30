package controllerApplication;

import zoo.ZooFantastique;

import java.util.Random;

import base.Enclos;
import controllerTemps.GestionnaireTemps;
import references.CONSTANTES;
import viewApplication.*;

/**
 * Classe représentant le contrôleur de la partie
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
	
	//TODO : actions appropriés selon etat du zoo
	
	/**
     * Constructeur de la classe ControllerGestionAuto
     */
    public ControllerGestionAuto() {
        VueGlobale = new VueGlobale();
        new VueAutomatique();
        new ControllerPrincipal();
    }
	

    /**
     * Méthode permettant d'effectuer un choix aléatoire
     *
     * @throws Exception En cas d'erreur lors de l'exécution de l'action aléatoire
     */
	public static void choixActionAleatoire () throws Exception {
		Random random = new Random();
		int choix = random.nextInt(CONSTANTES.NUM_CHOIX_MAX); 
		zooController.effectuerAction(choix);
	}
	
	
	/**
     * Point d'entrée de la gestion automatique
     *
     * @param debut true si c'est le début de la gestion automatique, false sinon
     * @throws Exception En cas d'erreur lors de l'exécution de l'action aléatoire
     */
	public static void run(boolean debut) throws Exception {
		boolean run = true;
		if (debut) {
			zooController.init();
			//zooController.effectuerAction(1);
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
     * Méthode pour récupérer un enclos aléatoire dans le zoo
     *
     * @return Un enclos choisi au hasard dans la liste des enclos du zoo
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
     * Méthode permettant de récupérer le premier enclos en mauvais état
     *
     * @return Le premier enclos en mauvais état, ou null s'il n'y en a aucun
     * @throws Exception En cas d'erreur lors de la récupération de l'enclos
     */
	public Enclos getFirstEnclosMauvaisEtat() throws Exception {
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isEnclosMauvaisEtat())
				return e;
		}
		return null;
	}
	
	
	/**
     * Méthode permettant de récupérer le premier enclos où les créatures ont faim
     *
     * @return Le premier enclos où les créatures ont faim, ou null s'il n'y en a aucun
     */
	public Enclos getFirstEnclosCreatureFaim() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isCreatureOntFaim())
				return e;
		}
		return null;
	}
	
	
	/**
     * Méthode permettant de récupérer le premier enclos où les créatures ont sommeil
     *
     * @return Le premier enclos où les créatures ont sommeil, ou null s'il n'y en a aucun
     */
	public Enclos getFirstEnclosCreatureSommeil() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isCreatureOntSommeil())
				return e;
		}
		return null;
	}
	
	
	/**
     * Méthode permettant de récupérer le premier enclos où les créatures ont une mauvaise santé
     *
     * @return Le premier enclos où les créatures ont une mauvaise santé, ou null s'il n'y en a aucun
     */
	public Enclos getFirstEnclosCreatureMauvaiseSante() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isCreatureOntSommeil())
				return e;
		}
		return null;
	}
	
	
	/**
     * Méthode permettant de récupérer le premier enclos où les créatures dorment
     *
     * @return Le premier enclos où les créatures dorment, ou null s'il n'y en a aucun
     */
	public Enclos getFirstEnclosCreatureDort() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isCreaturesDorment())
				return e;
		}
		return null;
	}
	
}
