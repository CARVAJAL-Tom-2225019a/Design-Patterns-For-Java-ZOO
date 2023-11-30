package zoo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import base.*;
import creaturesImplemente.Oeuf;
import meuteLycanthrope.ColonieLycanthrope;
import references.CONSTANTES;

/**
 * Classe representant l'instance unique du zoo (singleton)
 */
public class ZooFantastique {

    // Instance singleton du zoo fantastique
    private static ZooFantastique instance;
    // Instance singleton de la colonie de Lycanthrope
    private ColonieLycanthrope colonie = ColonieLycanthrope.getInstance();
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
    public String getNom() {
    	return nom;
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
    public HashSet<Enclos> getEnclosMauvaisEtat() {
    	HashSet<Enclos> listeEnclosBesoinNettoyage = new HashSet<Enclos>();
    	for (Enclos e : listeEnclos) {
    		if (e.isEnclosMauvaisEtat())
    			listeEnclosBesoinNettoyage.add(e);
    	}
    	return listeEnclosBesoinNettoyage;
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
     * Methode permetant de generer un entier aleatoire entre 0 et max
     * @param max	Entier maximal
     * @return	Le nombre aleatoire
     */
    public static int getIntAleatoire(int max) {
    	Random random = new Random();
    	return random.nextInt(max);
    }
    
    
    /**
     * Methode pour vider le zoo
     */
    public void clear() {
    	this.listeEnclos = new HashSet<>();
        listeOeufs = new HashSet<>();
        listeFemelleEnceinte = new HashSet<>();
        colonie.clear();
    }

}
