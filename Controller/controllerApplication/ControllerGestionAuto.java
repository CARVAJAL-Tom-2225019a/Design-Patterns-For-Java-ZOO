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

	private static ControllerZoo zooController;
	private static VueGlobale VueGlobale;
	private ZooFantastique zoo;
	private GestionnaireTemps temps = GestionnaireTemps.getInstance();
	
	
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
	
    
	//TODO : actions appropriés selon etat du zoo
	
    
    /**
     * Methode permettant d'effectuer un choix aleatoire 
     * @throws Exception
     */
	public void choixActionAleatoire () throws Exception {
		Random random = new Random();
		int choix = random.nextInt(CONSTANTES.NUM_CHOIX_MAX);
		zooController.effectuerAction(choix);
	}
	
	
	/**
	 * Point d'entree de la gestion automatique
	 * @throws Exception
	 */
	public void run() throws Exception {
		boolean run = true;
        zooController.init();
        Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        while (run) {
        	choixActionAleatoire ();
        	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        	zooController.runYear();
        	VueGlobale.afficher("\nNous sommes le "+temps.getDateActuelle()+"\n");
        	
        	// Si plus de creature
            if (zoo.getNbCreaturesTotales() == 0)
            	run = false;
            // Si duree de vie zoo fini
            if (zooController.getAnnee() == CONSTANTES.DUREE_VIE_ZOO)
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
	
}
