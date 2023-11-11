package maitreZoo;

import base.*;
import enclosImplemente.*;
import references.*;

public class MaitreZoo {
    // Instance unique de la classe MaitreZoo
    private static MaitreZoo instance;

    private String nom;
    private Enum_Sexe sexe;
    private int age;

    // Constructeur privé pour empêcher l'instanciation directe
    private MaitreZoo(String nom, Enum_Sexe sexe, int age) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
    }

    /**
     * Méthode statique pour obtenir l'instance unique de la classe MaitreZoo.
     *
     * @return L'instance unique de la classe MaitreZoo.
     */
    public static synchronized MaitreZoo getInstance(String nom, Enum_Sexe sexe, int age) {
        if (instance == null) {
            instance = new MaitreZoo(nom, sexe, age);
        }
        return instance;
    }
    
    
    /**
     * Methode pour examiner un enclos
     * 
     * @param enclos	Enclos a examiner
     * @return La chaine de caractere contenant les informations de l'enclos
     */
    public String ExaminerEnclos (Enclos enclos) {
    	return enclos.toString();
    }
    
    
    /**
     * Methode pour nettoyer un enclos
     * La methode appellerra la methode specifique au type d'enclos
     * 
     * @param enclos	Enclos a nettoyer
     * @throws Exception 
     */
    public void NettoyerEnclos (Enclos enclos) throws Exception {
    	enclos.EntretenirEnclos();
    }
    
    
    /**
     * Methode pour nourrir les creatures d'un enclos
     * @throws Exception 
     */
    public void NourrirCreaturesEnclos (Enclos enclos) throws Exception {
    	enclos.NourrirCreatures();
    }
    
    
    /**
     * Methode pour transferer une creature d'un enclos a un autre
     * 
     * @param creature		creature a transferer
     * @param enclosSource	enclos de depart
     * @param enclosDest	enclos de destination
     * @throws Exception 	si l'enclos de destination est plein
     */
    public void TransfererCreature (Creature creature, Enclos enclosSource, Enclos enclosDest) throws Exception {
    	// verification place dans enclos destination
    	if (enclosDest.getNbCreatures() < enclosDest.getNbMaxCreatures()) {
    		enclosSource.SupprimerCreature(creature);
        	enclosDest.AjouterCreature(creature);
    	}
    	else
    		throw new Exception ("Enclos de destination est plein");
    	
    }

}

