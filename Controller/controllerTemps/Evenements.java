package controllerTemps;

import java.util.Map;
import java.util.Random;

import base.*;
import enclosImplemente.*;
import references.Enum_DegrePropreteEnclos;
import zoo.ZooFantastique;

public class Evenements {
	GestionnaireTemps temps = GestionnaireTemps.getInstance();
	static ZooFantastique zoo = ZooFantastique.getInstance();
	
	public static void evenementAnnuel() throws Exception {
		// Verification de l'etat des enclos --> degradation etat creature
		degradationSanteCreatureSelonEtatEnclos();
		// Modification aleatoire de l'etat des creatures
		modifAleatoireStatutCreature();
		// Modification aleatoire de l'etat des enclos
    	modifAleatoireEtatEnclos();
    	// Verification etat creature
    	verificationEtatCreature();
	}
    
    
    /**
     * Méthode pour modifier aléatoirement l'état des créatures dans le zoo.
     * @throws Exception 
     */
    public static void modifAleatoireStatutCreature() throws Exception {
    	Random random = new Random(System.currentTimeMillis());
        // Nombre aléatoire d'enclos à modifier
        int nombreEnclosAModifier = random.nextInt(zoo.getListeEnclos().size()-3) + 2;
        // Parcourir un nombre aléatoire d'enclos
        for (int i = 0; i < nombreEnclosAModifier; i++) {
            // Sélectionner un enclos au hasard
            int randomIndexEnclos = random.nextInt(zoo.getListeEnclos().size());
            Enclos enclos = (Enclos) zoo.getListeEnclos().toArray()[randomIndexEnclos];
         // Nombre aléatoire de créatures à modifier dans cet enclos
            int maxCreaturesAModifier = Math.max(0, enclos.getNbCreatures() - 2);
            int nombreCreaturesAModifier = (maxCreaturesAModifier >= 2) ? random.nextInt(maxCreaturesAModifier) + 2 : 0;

            // Obtenir la liste des créatures dans cet enclos
            Map<Integer, Creature> creatures = enclos.getListeCreatures();
            // Modifier aléatoirement l'état du nombre aléatoire de créatures
            for (int j = 0; j < nombreCreaturesAModifier; j++) {
                // Sélectionner une clé au hasard dans la Map
                int randomKeyCreature = (int) creatures.keySet().toArray()[random.nextInt(creatures.size())];
                Creature creature = creatures.get(randomKeyCreature);
                // Choisir aléatoirement le critère (sommeil, sante ou faim)
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
    }

    
    /**
     * Méthode pour effectuer des modifications aléatoires sur l'état des enclos
     */
    public static void modifAleatoireEtatEnclos() {
    	Random random = new Random(System.currentTimeMillis());
        // Nombre aléatoire d'enclos à modifier
        int nombreEnclosAModifier = random.nextInt(zoo.getListeEnclos().size()-3) + 2;
        // Parcourir un nombre aléatoire d'enclos
        for (int i = 0; i < nombreEnclosAModifier; i++) {
            // Sélectionner un enclos au hasard
            int randomIndexEnclos = random.nextInt(zoo.getListeEnclos().size());
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
     * Methode permettant de degarder la sante des creatures si l'enclos est en mauvais etat
     * @throws Exception 
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
     * Mort d'une creature si elle ne va pas bien 
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
