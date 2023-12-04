package zoo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import base.*;
import creaturesImplemente.Oeuf;
import enclosImplemente.EnclosLycanthrope;
import meuteLycanthrope.ColonieLycanthrope;
import references.CONSTANTES;

/**
 * Classe représentant l'instance unique du zoo fantastique (Singleton)
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

    
    /**
     * Constructeur privé pour empêcher l'instanciation directe
     */
    private ZooFantastique() {
        this.nom = "Le Zoo Fantastique";
        this.listeEnclos = new HashSet<>();
        listeOeufs = new HashSet<>();
        listeFemelleEnceinte = new HashSet<>();
    }
    
    /**
     *  Méthode pour obtenir l'instance unique du zoo fantastique (Singleton)
     * @return l'instance du zoo
     */
    public static ZooFantastique getInstance() {
        if (instance == null) {
            instance = new ZooFantastique();
        }
        return instance;
    }

    
    /**
     * Methode permettant de recuperer la liste des enclos du zoo
     * @return un ensemble contenant les enclos
     */
    public Set<Enclos> getListeEnclos() {
        return listeEnclos;
    }
    /**
     * Methode permettant de recuperer la liste des oeufs qui vont bientot eclore
     * @return un ensemble contenant les oeufs
     */
    public Set<Oeuf> getlLsteOeufs() {
        return listeOeufs;
    }
    /**
     * Methode permettant de recuperer les femelles enceintes
     * @return un ensemble contenant les femelles
     */
    public Set<Creature> getListeFemelleEnceinte() {
        return listeFemelleEnceinte;
    }
    /**
     * Methode permettant de recuperer le nom du zoo
     * @return le nom du zoo
     */
    public String getNom() {
    	return nom;
    }

    
    /**
     * Méthode pour ajouter un enclos au zoo
     * @throws Exception si le nombre d'enclos max a ete atteind
     */
    public void addEnclos(Enclos enclos) throws Exception {
        if (listeEnclos.size() < CONSTANTES.NB_MAX_ENCLOS) {
            listeEnclos.add(enclos);
        }
        else
        	throw new Exception ("Le zoo ne peut plus avoir d'enclos, max atteint");
    }
    
    
    /**
     * Methodes pour ajouter une femelle enciente dans la liste
     * @param c la femelle a ajouter dans la liste
     */
    public void addFemelleEnceinte(Creature c) {
    	listeFemelleEnceinte.add(c);
    }
    
    /**
     * Methodes pour ajouterun oeuf dans la liste
     * @param o l'oeuf à ajouter
     */
    public void addOeuf(Oeuf o) {
    	listeOeufs.add(o);
    }
    
    
    /**
     * Methodes pour supprimer une femelle enceinte apres la naissance
     */
    public void removeFemelleEnceinte (Creature c) {
    	listeFemelleEnceinte.remove(c);
    }
    
    /**
     * Methode pour supprimer l'oeuf de la lsite apres la naissance de la creature
     * @param o
     */
    public void removeOeuf (Oeuf o) {
    	listeOeufs.remove(o);
    }

    
    /**
     * Méthode pour obtenir le nombre total de créatures dans le zoo
     * @return le nombre de creatures dans le zoo
     */
    public int getNbCreaturesTotales() {
        int somme = 0;
        for (Enclos e : listeEnclos) {
            somme += e.getNbCreatures();
        }
        return somme;
    }

    
    /**
     * Méthode pour récupérer la liste des enclos en mauvais état
     *
     * @return Ensemble d'enclos qui sont en mauvais état et nécessitent un nettoyage
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
     *
     * @param nomRecherche Nom de l'enclos à rechercher
     * @return L'enclos trouvé
     * @throws Exception Si le nom de l'enclos est incorrect
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
     * Méthode permettant de générer un entier aléatoire entre 0 et max
     *
     * @param max Entier maximal
     * @return Le nombre aléatoire
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
    
    
    /**
     * Methode permettant de voir la liste de nom des enclos
     * qui ne sont pas vide
     * 
     * @return La chaine contenant les informations
     */
    public String voirNomsEnclosPasVide() {
    	String chaine = "LES ENCLOS QUI NE SONT PAS VIDE : \n";
    	for (Enclos e : listeEnclos) {
    		if (e.getListeCreatures().size() > 0)
    			chaine+="  - "+e.getNom()+"\n";
    	}
    	return chaine;
    }
    
    
    /**
     * Methode permettant de voir la liste de nom de tous les enclos
     * 
     * @return La chaine contenant les informations
     */
    public String voirNomsEnclos() {
    	String chaine = "LES ENCLOS : \n";
    	for (Enclos e : listeEnclos) {
    		chaine+="  - "+e.getNom()+"\n";
    	}
    	return chaine;
    }
    
    
    /**
     * Methode permettant de voir la liste de nom des enclos à
     * lycanthropes
     * 
     * @return La chaine contenant les informations
     */
    public String voirNomEnclosLycanthropes() {
    	String chaine = "LES ENCLOS DE LYCANTHROPE : \n";
    	for (Enclos e : listeEnclos) {
    		if (e instanceof EnclosLycanthrope)
    			chaine+="  - "+e.getNom()+"\n";
    	}
    	return chaine;
    }

}
