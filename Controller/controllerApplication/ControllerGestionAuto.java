package controllerApplication;

import zoo.ZooFantastique;

import java.util.Random;

import enclosImplemente.Enclos;
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
	public void ChoixActionAleatoire () throws Exception {
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
        	ChoixActionAleatoire ();
        	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        	zooController.runYear();
        	
        	// Si plus de creature
            if (zoo.getNbCreaturesTotales() == 0)
            	run = false;
            // Si duree de vie zoo fini
            if (zooController.getAnnee() == CONSTANTES.DUREE_VIE_ZOO)
            	run = false;
            
        }
        VueGlobale.Afficher("\n =====  FIN DE LA SIMULATION  ======\n");
	}
	
	
	/**
     * Méthode pour récupérer un enclos aléatoire dans le zoo.
     *
     * @return Un enclos choisi au hasard dans la liste des enclos du zoo.
     */
	public Enclos RecuperationEnclosAleatoire() {
        // Vérifier s'il y a des enclos disponibles
        if (zoo.GetListeEnclos().isEmpty()) {
            return null;
        }
        // Obtenez un indice aléatoire
        int indiceAleatoire = new Random().nextInt(zoo.GetListeEnclos().size());
        // Retournez l'enclos correspondant à l'indice aléatoire
        return (Enclos) zoo.GetListeEnclos().toArray()[indiceAleatoire];
	}
	
}
