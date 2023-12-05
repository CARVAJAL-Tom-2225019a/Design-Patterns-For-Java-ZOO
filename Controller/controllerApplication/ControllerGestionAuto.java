package controllerApplication;

import base.Enclos;
import controllerTemps.GestionnaireTemps;
import creaturesImplemente.Lycanthrope;
import meuteLycanthrope.ColonieLycanthrope;
import meuteLycanthrope.Meute;
import references.CONSTANTES;
import references.Enum_RangDomination;
import references.Enum_Sexe;
import viewApplication.VueAutomatique;
import viewApplication.VueGlobale;
import zoo.ZooFantastique;

import java.util.Random;

/**
 * Classe représentant le contrôleur de la partie
 * gestion automatique du zoo
 */
public class ControllerGestionAuto {
	// Instance du contrôleur du zoo (où les différentes actions possibles sont stockées)
	private static final ControllerActions zooController = new ControllerActions();
	// Instance de la vue globale
	private static VueGlobale VueGlobale;
	// Instance unique du zoo fantastique
	private static final ZooFantastique zoo = ZooFantastique.getInstance();
	// Instance de la colonie de lycanthrope
	private static final ColonieLycanthrope colonie = ColonieLycanthrope.getInstance();
	// Instance unique de la classe permettant la gestion du temp
	private static final GestionnaireTemps temps = GestionnaireTemps.getInstance();
	
	
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
	public static void choixActionAleatoire ()  {
		Random random = new Random();
		int choix = random.nextInt(CONSTANTES.NUM_CHOIX_MAX); 
		ControllerActions.effectuerAction(choix);
	}
	*/

	public static void choixActionAleatoire() throws Exception {
		String[] listeChoix = {"sante", "faim", "sommeil","hygene"};
		String enclosChoisi = "";
		int choixCase = 0;
		for (int i = 0; i < listeChoix.length; ++i) {
			enclosChoisi = getCreaturePlusGrandBesoin(listeChoix[i]);
			if (!enclosChoisi.equals("Enclos")) {
				if (i == 0) {
					choixCase = 7;
					break;
				} else if (i == 1) {
					choixCase = 6;
					break;
				} else if (i == 2) {
					choixCase = 8;
					break;
				} else if(i == 3) {
					choixCase = 5;
					break;
				}
			}
		}
		if (enclosChoisi.equals("Enclos")) {
			Random random = new Random();
			choixCase = random.nextInt(9, 21);
		}
		ControllerActions.effectuerAction(choixCase);
	}

	/**
	 * Méthode pour récupérer l'enclos avec la créature ayant le besoin passé en parametre le plus faible
	 *
	 * @param besoin prend le besoin a étudier dans les enclos. Prend la valeur de String "sante", "faim", "sommeil" ou "hygene"
	 * @throws Exception En cas d'erreur lors de l'exécution de l'action aléatoire
	 * @return Un enclos
	 */
	public static Enclos getCreaturePlusGrandBesoin(String besoin) {
		Enclos enclos = null;
		int valeurMoinsEleve = 100;
		int indicateurParam = 0;
		for (Enclos e : zoo.getListeEnclos()) {
			for (Creature c: e.getListeCreatures().values()){
				if(besoin == "sante") {
					indicateurParam = c.getIndicateurSante();
					if (indicateurParam < valeurMoinsEleve && indicateurParam < CONSTANTES.MAX_INDICATEUR*20/100) {
						valeurMoinsEleve = indicateurParam;
						enclos.getNom();
					}
				}else if (besoin == "faim"){
					indicateurParam = c.getIndicateurFaim();
					if (indicateurParam < valeurMoinsEleve && indicateurParam < CONSTANTES.MAX_INDICATEUR*60/100) {
						valeurMoinsEleve = indicateurParam;
						enclos = e;
					}
				}else if(besoin == "sommeil") {
					indicateurParam = c.getIndicateurSommeil();
					if (indicateurParam < valeurMoinsEleve&& indicateurParam < CONSTANTES.MAX_INDICATEUR*40/100) {
						valeurMoinsEleve = indicateurParam;
						enclos = e;
					}
				}
			}
		}
		return enclos;
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
        Enclos enclos = null;
        while (enclos == null) {
        	 // Obtenez un enclos aléatoire
            int indiceAleatoire = new Random().nextInt(zoo.getListeEnclos().size());
            enclos = (Enclos) zoo.getListeEnclos().toArray()[indiceAleatoire];
        }
        // Retournez l'enclos correspondant à l'indice aléatoire
        return enclos;
	}
	
	
	/**
	 * Méthode permettant de récupérer un enclos de lycannthrope aléatoire qui
	 * n'est pas vide
	 * @return l'enclos sélectionné aléatoirement
	 */
	public Enclos recuperationEnclosAleatoireNonVideLycanthrope() {
		// Vérifier s'il y a des enclos disponibles
        if (colonie.getListeMeutes().isEmpty()) {
            return null;
        }
        Enclos enclos=null;
        Meute meute;
        while (enclos == null) {
        	 // Obtenez un enclos aléatoire
            int indiceAleatoire = new Random().nextInt(colonie.getListeMeutes().size());
            meute = (Meute) colonie.getListeMeutes().toArray()[indiceAleatoire];
            enclos = meute.getEnclosReference();
        }
        // Retournez l'enclos correspondant à l'indice aléatoire
        return enclos;
	}
	
	
	/**
     * Méthode pour récupérer un enclos aléatoire dans le zoo qui n'est pas vide
     *
     * @return Un enclos choisi au hasard dans la liste des enclos du zoo
     */
	public Enclos recuperationEnclosAleatoireNonVide() {
        // Vérifier s'il y a des enclos disponibles
        if (zoo.getListeEnclos().isEmpty()) {
            return null;
        }
        Enclos enclos=null;
        while (enclos == null || enclos.getNbCreatures()==0) {
        	 // Obtenez un enclos aléatoire
            int indiceAleatoire = new Random().nextInt(zoo.getListeEnclos().size());
            enclos = (Enclos) zoo.getListeEnclos().toArray()[indiceAleatoire];
        }
        // Retournez l'enclos correspondant à l'indice aléatoire
        return enclos;
	}
	
	
	/**
     * Méthode pour récupérer un enclos aléatoire dans le zoo qui est vide
     *
     * @return Un enclos choisi au hasard dans la liste des enclos du zoo
     */
	public Enclos recuperationEnclosAleatoireVide() {
        // Vérifier s'il y a des enclos disponibles
        if (zoo.getListeEnclos().isEmpty()) {
            return null;
        }
        Enclos enclos=null;
        while (enclos == null || enclos.getNbCreatures()>0) {
        	 // Obtenez un enclos aléatoire
            int indiceAleatoire = new Random().nextInt(zoo.getListeEnclos().size());
            enclos = (Enclos) zoo.getListeEnclos().toArray()[indiceAleatoire];
        }
        // Retournez l'enclos correspondant à l'indice aléatoire
        return enclos;
	}
	
	
	/**
     * Méthode permettant de récupérer le premier enclos en mauvais état
     *
     * @return Le premier enclos en mauvais état, ou null s'il n'y en a aucun
	 */
	public Enclos getFirstEnclosMauvaisEtat() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isEnclosMauvaisEtat())
				return e;
		}
		return recuperationEnclosAleatoire();
	}
	
	
	/**
     * Méthode permettant de récupérer le premier enclos où les créatures ont faim
     *
     * @return Le premier enclos où les créatures ont faim, ou null s'il n'y en a aucun
     */
	public Enclos getFirstEnclosCreatureFaim() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (!e.getListeCreatures().isEmpty() && e.isCreatureOntFaim())
				return e;
		}
		return recuperationEnclosAleatoire();
	}
	
	
	/**
     * Méthode permettant de récupérer le premier enclos où les créatures ont sommeil
     *
     * @return Le premier enclos où les créatures ont sommeil, ou null s'il n'y en a aucun
     */
	public Enclos getFirstEnclosCreatureSommeil() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (!e.getListeCreatures().isEmpty() && e.isCreatureOntSommeil())
				return e;
		}
		return recuperationEnclosAleatoire();
	}
	
	
	/**
     * Méthode permettant de récupérer le premier enclos où les créatures ont une mauvaise santé
     *
     * @return Le premier enclos où les créatures ont une mauvaise santé, ou null s'il n'y en a aucun
     */
	public Enclos getFirstEnclosCreatureMauvaiseSante() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (!e.getListeCreatures().isEmpty() && e.isCreatureSontMalade())
				return e;
		}
		return recuperationEnclosAleatoire();
	}
	
	
	/**
     * Méthode permettant de récupérer le premier enclos où les créatures dorment
     *
     * @return Le premier enclos où les créatures dorment, ou null s'il n'y en a aucun
     */
	public Enclos getFirstEnclosCreatureDort() {
		for (Enclos e : zoo.getListeEnclos()) {
			if (!e.getListeCreatures().isEmpty() && e.isCreaturesDorment())
				return e;
		}
		return recuperationEnclosAleatoire();
	}
	
	
	/**
	 * Methode permettant de choisir une meute de maniere aleatoire
	 * @return La meute selectionnée
	 */
	public Meute choixMeuteAleatoire() {
		// Vérifier s'il y a des meutes 
        if (colonie.getListeMeutes().isEmpty()) {
            return null;
        }
        Meute meute=null;
        while (meute == null) {
        	 // Obtenez une meute aléatoire
            int indiceAleatoire = new Random().nextInt(colonie.getListeMeutes().size());
            meute = (Meute) colonie.getListeMeutes().toArray()[indiceAleatoire];
        }
        // Retournez l'enclos correspondant à l'indice aléatoire
        return meute;
	}
	
	
	/**
	 * Methode permettant de recuperer le loup le plus fort dans la meute
	 * en dehors du couple alpha
	 * @param m meute où l'on cherche le loup
	 * @return le loup le plus fort en dehors du couple alpha
	 * @throws Exception si la meute ne contient que le couple alpha
	 */
	public Lycanthrope choixPlusFortLoupDansMeute(Meute m) throws Exception {
		Lycanthrope loupPlusFort = m.choixPremierLoupPasCoupleAlpha();
		if (loupPlusFort==null)
			throw new Exception ("Il n'y a que le couple alpha dans la meute "+m.getNomMeute());
		for (Lycanthrope l : m.getListeLoup()) {
			if (l!=m.getCoupleAlpha().getFemelleAlpha() && l!=m.getCoupleAlpha().getMaleAlpha() && l.getSexe()==Enum_Sexe.Male && l.getRangDomination().getValeur()>loupPlusFort.getRangDomination().getValeur()) {
				if (l.getRangDomination()!=Enum_RangDomination.ALPHA) {
						loupPlusFort=l;
				}
				else {
					if (l.getNiveau()>loupPlusFort.getNiveau())
						loupPlusFort=l;
				}
			}
		}
		return loupPlusFort;
	}
	
}
