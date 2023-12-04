package controllerTemps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import base.*;
import enclosImplemente.*;
import references.Enum_DegrePropreteEnclos;
import zoo.ZooFantastique;

/**
 * Classe représentant les événements liés au temps dans le zoo
 */
public class Evenements {
	GestionnaireTemps temps = GestionnaireTemps.getInstance();
	static ZooFantastique zoo = ZooFantastique.getInstance();
	
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
	private static Enclos getRandomEnclosWithCreatures() {
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
            if (enclos instanceof Aquarium) {
                // Si c'est un Aquarium, modifier NiveauEau et SaliniteEau
                Aquarium aquarium = (Aquarium) enclos;
                aquarium.degradationNiveauEau();
                aquarium.degradationSaliniteEau();
            } else if (enclos instanceof Voliere) {
                // Si c'est une Voliere, modifier degreProprete et etatToit
                Voliere voliere = (Voliere) enclos;
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
    private static void degradationSanteCreatureSelonEtatEnclos () throws Exception {
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
    private static void verificationEtatCreature () {
    	int compteurIndicateurMauvais = 0;
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
    
}
