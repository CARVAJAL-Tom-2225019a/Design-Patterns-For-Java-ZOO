package zoo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import base.*;
import creaturesImplemente.Oeuf;
import enclosImplemente.*;
import references.CONSTANTES;
import references.Enum_DegrePropreteEnclos;

/**
 * Classe representant l'instance unique du zoo (singleton)
 */
public class ZooFantastique {

    // Instance singleton du zoo fantastique
    private static ZooFantastique instance;
    // Nom du zoo
    private String nom;
    // Liste des enclos dans le zoo
    private Set<Enclos> listeEnclos;
    //Liste Oeuf et Enfant à naitre
    private Set<Oeuf> listeOeufs;
    private Set<Creature> listeFemelleEnceinte;

    
    // Constructeur privé pour empêcher l'instanciation directe
    private ZooFantastique() {
        this.nom = "Le Zoo Fantastique";
        this.listeEnclos = new HashSet<>();
        listeOeufs = new HashSet<>();
        listeFemelleEnceinte = new HashSet<>();
    }
    // Méthode pour obtenir l'instance unique du zoo fantastique (Singleton)
    public static ZooFantastique getInstance() {
        if (instance == null) {
            instance = new ZooFantastique();
        }
        return instance;
    }

    
    /**
     * Getters
     */
    public Set<Enclos> getListeEnclos() {
        return listeEnclos;
    }
    public Set<Oeuf> getlLsteOeufs() {
        return listeOeufs;
    }
    public Set<Creature> getListeFemelleEnceinte() {
        return listeFemelleEnceinte;
    }

    
    /**
     * Méthode pour ajouter un enclos au zoo
     * @throws Exception 
     */
    public void addEnclos(Enclos enclos) throws Exception {
        if (listeEnclos.size() < CONSTANTES.NB_MAX_ENCLOS) {
            listeEnclos.add(enclos);
        }
        else
        	throw new Exception ("Le zoo ne peut plus avoir d'enclos, max atteint");
    }
    
    
    /**
     * Methodes pour ajouter un enfant dans la liste
     */
    public void addFemelleEnceinte(Creature c) {
    	listeFemelleEnceinte.add(c);
    }
    public void addOeuf(Oeuf o) {
    	listeOeufs.add(o);
    }
    
    
    /**
     * Methodes pour supprimer oeuf ou enfant apres sa naissance
     */
    public void removeFemelleEnceinte (Creature c) {
    	listeFemelleEnceinte.remove(c);
    }
    public void removeOeuf (Oeuf o) {
    	listeOeufs.remove(o);
    }

    
    /**
     * Méthode pour obtenir le nombre total de créatures dans le zoo
     */
    public int getNbCreaturesTotales() {
        int somme = 0;
        for (Enclos e : listeEnclos) {
            somme += e.getNbCreatures();
        }
        return somme;
    }

    
    /**
     * Méthode pour afficher les informations sur l'ensemble du zoo
     */
    public String afficherEnsembleZoo() {
        String chaine ="\n==== VOICI " + nom + " ==== \n\n";
        for (Enclos e : listeEnclos) {
        		chaine+=e.voirInfoEnclos();
        }
        return chaine;
    }
    
    
    /**
     * Methode pour afficher la liste des femelles qui attendent un bebe
     */
    public String afficherFemellesEnceinte() {
    	String chaine = "LES FEMELLES ENCEINTES :\n";
    	for (Creature c : listeFemelleEnceinte) {
    		chaine += c.toString() + "temps restant : "+((Vivipare) c).getNbJourConceptionRestantAvantMiseABas();
    	}
    	return chaine;
    }
    
    
    /**
     * Methode pour afficher la liste des oeufs
     */
    public String afficherOeufs() {
    	String chaine = "LES OEUFS :\n";
    	for (Oeuf o : listeOeufs) {
    		chaine += o.toString();
    	}
    	return chaine;
    }
    
    
    /**
     * Methode pour recuperer la liste des enclos en mauvais etat
     */
    public String AfficherEnclosMauvaisEtat() {
    	String chaine = "LES ENCLOS A NETTOYER : \n";
    	for (Enclos e : listeEnclos) {
    		if (e instanceof Voliere) {
    			if ( ((Voliere) e).getEtatToit()==Enum_DegrePropreteEnclos.mauvais
    					|| e.getDegreProprete()==Enum_DegrePropreteEnclos.mauvais)
    				chaine+="  - "+e.getNom()+"\n";
    		}
    		else if (e instanceof Aquarium) {
    			if ( ((Aquarium) e).getNiveauEau()< ((Aquarium) e).getProfondeurBassin()/3 ||
    					((Aquarium) e).getSaliniteEau() < 2 )
    				chaine+="  - "+e.getNom()+"\n";
    		}
    		else {
    			if (e.getDegreProprete()==Enum_DegrePropreteEnclos.mauvais)
    				chaine+="  - "+e.getNom()+"\n";
    		}
    	}
    	return chaine+"\n";
    }

    
    /**
     * Méthode pour trouver un enclos par son nom
     * @throws Exception 
     */
    public Enclos trouverEnclosParNom(String nomRecherche) throws Exception {
        Iterator<Enclos> iterator = listeEnclos.iterator();
        while (iterator.hasNext()) {
            Enclos enclos = iterator.next();
            if (enclos.getNom().equals(nomRecherche)) {
                return enclos; // On a trouvé l'enclos
            }
        }
        throw new Exception ("Nom enclos inccorrect");
    }
    

    /**
     * Methode pour modification aleatoire a intervalles
     * regulires de l'etat du zoo
     * @throws Exception 
     * 
     */
    public void ModificationEtatAleatoire() throws Exception {
    	ModifAleatoireStatutCreature();
    	ModifAleatoireEtatEnclos();
    }
    
    
    /**
     * Méthode pour modifier aléatoirement l'état des créatures dans le zoo.
     * @throws Exception 
     */
    public void ModifAleatoireStatutCreature() throws Exception {
    	Random random = new Random(System.currentTimeMillis());
        // Nombre aléatoire d'enclos à modifier
        int nombreEnclosAModifier = random.nextInt(listeEnclos.size()-3) + 2;
        // Parcourir un nombre aléatoire d'enclos
        for (int i = 0; i < nombreEnclosAModifier; i++) {
            // Sélectionner un enclos au hasard
            int randomIndexEnclos = random.nextInt(listeEnclos.size());
            Enclos enclos = (Enclos) listeEnclos.toArray()[randomIndexEnclos];
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
    private void ModifAleatoireEtatEnclos() {
    	Random random = new Random(System.currentTimeMillis());
        // Nombre aléatoire d'enclos à modifier
        int nombreEnclosAModifier = random.nextInt(listeEnclos.size()-3) + 2;
        // Parcourir un nombre aléatoire d'enclos
        for (int i = 0; i < nombreEnclosAModifier; i++) {
            // Sélectionner un enclos au hasard
            int randomIndexEnclos = random.nextInt(listeEnclos.size());
            Enclos enclos = (Enclos) listeEnclos.toArray()[randomIndexEnclos];
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
                // Sinon (enclos classique), modifier degreProprete
                enclos.degradationDegreProprete();
            }
        }
    }
    
    
    /**
     * Methode permetant de generer un entier aleatoire entre 0 et max
     * @param max	Entier maximal
     * @return	Le nombre aleatoire
     */
    public static int getIntAleatoire(int max) {
    	Random random = new Random();
    	return random.nextInt(max);
    }

}
