package controllerTemps;

import base.Creature;
import base.Enclos;
import controllerApplication.ControllerGestionAuto;
import controllerApplication.ControllerPrincipal;
import creaturesImplemente.Lycanthrope;
import enclosImplemente.Aquarium;
import enclosImplemente.EnclosClassique;
import enclosImplemente.EnclosLycanthrope;
import enclosImplemente.Voliere;
import meuteLycanthrope.ColonieLycanthrope;
import meuteLycanthrope.Meute;
import references.CONSTANTES;
import references.Enum_ActionHurlement;
import references.Enum_DegrePropreteEnclos;
import viewApplication.Son;
import viewApplication.VueGlobale;
import zoo.ZooFantastique;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

/**
 * Classe représentant les événements liés au temps dans le zoo
 */
public class Evenements {
	private static ZooFantastique zoo = ZooFantastique.getInstance();
	private static ControllerGestionAuto controllerGestionAuto = new ControllerGestionAuto();
	private static VueGlobale vueGlobale = new VueGlobale();
	private static ColonieLycanthrope colonie = ColonieLycanthrope.getInstance();
	
	/**
	 * Constructeur
	 */
	public Evenements() { }
	
	/**
     * Méthode pour déclencher les événements annuels dans le zoo
     *
     * @throws Exception En cas d'erreur lors de l'exécution des événements
     */
	public static void evenementAnnuel() throws Exception {
		// Verification de l'etat des enclos --> degradation etat creature
		degradationSanteCreatureSelonEtatEnclos();
		// Modification aleatoire de l'etat des creatures
		modifAleatoireStatutCreature();
		// Modification aleatoire de l'etat des enclos
    	modifAleatoireEtatEnclos();
    	// Verification etat creature
    	verificationEtatCreature();
    	// Recalculer force des creatures
    	recalculerForceCreatures();
    	// Hurlement lycanthrope aleatoire
    	 if (Math.random() < 0.5) hurlementAleatoire();
    	 // Transformation lycanthrope en humain aleatoire
    	 if (Math.random() < 0.5) transformationLycanthropeHumain();
	}
    
    /**
     * Methode permettant de recalculer la force de toutes les creatures de l'enlos
     * chaque année
     */
	private static void recalculerForceCreatures() {
		for (Enclos enclos : zoo.getListeEnclos() ) {
			for (Creature creat : enclos.getListeCreatures().values()) {
				creat.calculerForce();
			}
		}
	}


	/**
     * Méthode pour modifier aléatoirement l'état des créatures dans le zoo
     *
     * @throws Exception En cas d'erreur lors de la modification de l'état des créatures
     */
	public static void modifAleatoireStatutCreature() throws Exception {
	    Random random = new Random(System.currentTimeMillis());

	    // Nombre aléatoire d'enclos à modifier
	    int nombreEnclosAModifier = random.nextInt(zoo.getListeEnclos().size() - 4) + 1;

	    // Parcourir un nombre aléatoire d'enclos
	    for (int i = 0; i < nombreEnclosAModifier; i++) {
	        // Sélectionner un enclos au hasard
	        Enclos enclos = getRandomEnclosWithCreatures();
	        if (enclos != null) {
	            // Nombre aléatoire de créatures à modifier dans cet enclos
	            int maxCreaturesAModifier = Math.max(0, enclos.getNbCreatures() - 2);
	            int nombreCreaturesAModifier = (maxCreaturesAModifier >= 2) ? random.nextInt(maxCreaturesAModifier) + 2 : 0;
	            // Modifier aléatoirement l'état du nombre aléatoire de créatures
	            modifyRandomCreaturesState(enclos.getListeCreatures(), nombreCreaturesAModifier, random);
	        }
	    }
	}

	
	/**
	 * Methode permettant de recuperer un enclos aleatoire contenant des creatures
	 * @return l'enclos sélectionné
	 */
	public static Enclos getRandomEnclosWithCreatures() {
		Random random = new Random(System.currentTimeMillis());
	    Enclos enclos = null;
	    List<Enclos> enclosList = new ArrayList<>(zoo.getListeEnclos());

	    while (enclos == null || enclos.getNbCreatures() == 0) {
	        int randomIndexEnclos = random.nextInt(enclosList.size());
	        enclos = enclosList.get(randomIndexEnclos);
	    }
	    return enclos;
	}

	
	/**
	 * Methode permettant de modifier aleatoirement l'etat d'une liste de créature
	 * @param creatures est un dictionnaire contenant les creatures et leur index
	 * @param numCreatures le nombre de creatures qu'il faut modifier
	 * @param random l'instance aléatoire
	 * @throws Exception si une des creatures est morte
	 */
	private static void modifyRandomCreaturesState(Map<Integer, Creature> creatures, int numCreatures, Random random) throws Exception {
	    for (int j = 0; j < numCreatures; j++) {
	        int randomKeyCreature = new ArrayList<>(creatures.keySet()).get(random.nextInt(creatures.size()));
	        Creature creature = creatures.get(randomKeyCreature);
	        int critereAleatoire = random.nextInt(3);
	        switch (critereAleatoire) {
	            case 0:
	                creature.perdreSommeil();
	                break;
	            case 1:
	                creature.perdreSante();
	                break;
	            case 2:
	                creature.perdreNourriture();
	                break;
	        }
	    }
	}

    
    /**
     * Méthode pour effectuer des modifications aléatoires sur l'état des enclos
     */
    public static void modifAleatoireEtatEnclos() {
    	Random random = new Random(System.currentTimeMillis());
        // Nombre aléatoire d'enclos à modifier
        int nombreEnclosAModifier = 0;
        while (nombreEnclosAModifier<=1)
        	nombreEnclosAModifier = (random.nextInt(zoo.getListeEnclos().size()));
        // Parcourir un nombre aléatoire d'enclos
        for (int i = 0; i < nombreEnclosAModifier; i++) {
            // Sélectionner un enclos au hasard
            int randomIndexEnclos = -1;
            while (randomIndexEnclos<0 || randomIndexEnclos>=zoo.getListeEnclos().size()) {
            	randomIndexEnclos = random.nextInt(zoo.getListeEnclos().size());
            }
            Enclos enclos = (Enclos) zoo.getListeEnclos().toArray()[randomIndexEnclos];
            if (enclos instanceof Aquarium aquarium) {
                // Si c'est un Aquarium, modifier NiveauEau et SaliniteEau
				aquarium.degradationNiveauEau();
                aquarium.degradationSaliniteEau();
            } else if (enclos instanceof Voliere voliere) {
                // Si c'est une Voliere, modifier degreProprete et etatToit
				voliere.degradationDegreProprete();
                voliere.degradationEtatToit();
            } else {
                // Sinon (enclos classique ou lycanthrope), modifier degreProprete
                enclos.degradationDegreProprete();
            }
        }
    }
    
    
    /**
     * Méthode permettant de dégrader la santé des créatures si l'enclos est en mauvais état
     *
     * @throws Exception En cas d'erreur lors de la dégradation de la santé des créatures
     */
    public static void degradationSanteCreatureSelonEtatEnclos () throws Exception {
    	for (Enclos e : zoo.getListeEnclos()) {
    		if (e instanceof EnclosClassique || e instanceof EnclosLycanthrope) {
    			if (e.getDegreProprete() == Enum_DegrePropreteEnclos.mauvais) {
    				e.degradationEtatCreatures();
    			}
    		}
    		else if (e instanceof Voliere) {
    			if (((Voliere)e).getEtatToit() == Enum_DegrePropreteEnclos.mauvais || e.getDegreProprete() == Enum_DegrePropreteEnclos.mauvais) {
    						e.degradationEtatCreatures();	
    			}
    					
    		}
    		else if (e instanceof Aquarium) {
    			if ( ((Aquarium)e).getNiveauEau()<((Aquarium)e).getNiveauEau()/3 || ((Aquarium)e).getSaliniteEau()<2 ) {
    				e.degradationEtatCreatures();
    			}
    		}
    	}
    }
    
    
    /**
     * Méthode pour vérifier l'état des créatures et provoquer la mort d'une créature si elle ne va pas bien
     */
    public static void verificationEtatCreature () {
    	int compteurIndicateurMauvais ;
    	for (Enclos e : zoo.getListeEnclos()) {
    		for (Creature c : e.getListeCreatures().values()) {
    			compteurIndicateurMauvais=0;
    			if (c.getIndicateurFaim()==0)
    				compteurIndicateurMauvais++;
    			if (c.getIndicateurSommeil() == 0)
    				compteurIndicateurMauvais++;
    			if (c.getIndicateurSante()==0)
    				compteurIndicateurMauvais++;
    			if (compteurIndicateurMauvais>1)
    				c.mourir();
    			
    		}
    	}
    }
    
    
	/**
	 * Méthode permettant de faire hurler aleatoirement un lycanthrope
	 * @throws Exception si la créature n'est pas un lycanthrope, ou si l'enclos n'est pas trouvé
	 */
    public static void hurlementAleatoire() throws Exception {
    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
    	Enum_ActionHurlement action;
    	Enclos enclos1;
    	Enclos enclos2;
    	Lycanthrope loup1;
    	Lycanthrope loup2;
    	// choix hurlement aleatoire
        Random random = new Random();
        Enum_ActionHurlement[] listeActions = {Enum_ActionHurlement.Appartenance, Enum_ActionHurlement.Domination, Enum_ActionHurlement.Soumission, Enum_ActionHurlement.Agressivite};
        action = listeActions[random.nextInt(listeActions.length)];
        // choix loup source
        enclos1 = controllerGestionAuto.recuperationEnclosAleatoireNonVideLycanthrope();
        loup1 = (Lycanthrope) enclos1.selectionnerCreatureAleatoireParSexe(ControllerPrincipal.sexeAleatoire());
        // choix loup destination
        enclos2 = controllerGestionAuto.recuperationEnclosAleatoireNonVideLycanthrope();
        loup2 = (Lycanthrope) enclos2.selectionnerCreatureAleatoireParSexe(ControllerPrincipal.sexeAleatoire());
        
        // Hurlement
        vueGlobale.afficher("HURLEMENT LYCANTHROPE ALEATOIRE");
        Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
        // Affichae loup source
        vueGlobale.afficher("LOUP QUI HURLE : ");
        vueGlobale.afficherCreature(loup1, -1);
        Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
        // Affichage loup destination
        vueGlobale.afficher("LOUP QUI ENTEND : ");
        vueGlobale.afficherCreature(loup2, -1);
        Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
        // Hurlement
        vueGlobale.afficher(((Lycanthrope) loup1).hurler(action, (Lycanthrope) loup2));

        Random r = new Random();
        int n = r.nextInt(8); // 8 = nombre fichier
        String file = "lycanthrope/loup" + (n + 1) + ".wav";
        (new Son()).play(file);

        // cri du deuxieme loup garou
        r = new Random();
        file = "lycanthrope/loup" + (n + 1) + ".wav";
        (new Son()).play(file);
    }
    
    
    /**
     * Méthode permettant de transformer un loup aléatoire en humain
     * @throws Exception si problème lors du choix
     */
    public static void transformationLycanthropeHumain() throws Exception {
    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
    	// Choix aléatoire d'une meute parmi colonie.getListeMeutes()
        Set<Meute> listeMeutes = colonie.getListeMeutes();
        Meute meuteChoisie = getRandomElement(listeMeutes);
        // Choix aléatoire d'un lycanthrope parmi meute.getListeLoup()
        Set<Lycanthrope> listeLycanthropes = meuteChoisie.getListeLoup();
        Lycanthrope lycanthropeChoisi = getRandomElement(listeLycanthropes);
        // Transformation en humain
        vueGlobale.afficher("TRANSFORMATION ALEATOIRE D'UN LOUP EN HUMAIN");
        Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
        vueGlobale.afficherCreature(lycanthropeChoisi, -1);
        Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
        vueGlobale.afficher("Transformation en cours");
        Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
        lycanthropeChoisi.seTransformerEnHumain();
        meuteChoisie.removeLoup(lycanthropeChoisi);
        vueGlobale.afficher("Le lycanthrope a rejoint les humains... courage à lui");
        Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
    }
    
    
 	/**
 	 * Méthode générique permettant de choisir un élément aléatoirement parmis un ensemble
 	 * @param <T> générique
 	 * @param set l'ensemble des éléments
 	 * @return un élément aléatoire parmis l'ensemble
 	 */
    private static <T> T getRandomElement(Set<T> set) {
        if (set.isEmpty()) {
            throw new NoSuchElementException("L'ensemble est vide");
        }
        int randomIndex = new Random().nextInt(set.size());
        Iterator<T> iterator = set.iterator();
        for (int i = 0; i < randomIndex; i++) {
            iterator.next();
        }
        return iterator.next();
    }
    
}
