package maitreZoo;

import base.*;
import creaturesImplemente.Humain;
import references.*;

/**
 * Classe representant l'unique maitre Zoo (singleton)
 */
public class MaitreZoo extends Humain{
	
	// TODO : si échec defier male alpha, maitre zoo aura 
		// alors la possibilité de les déplacer individuellement dans un autre enclot
	
	
    // Instance unique de la classe MaitreZoo
    private static MaitreZoo instance;

    // Constructeur privé pour empêcher l'instanciation directe
    private MaitreZoo (String nom, Enum_Sexe sexe, int age) {
    	super(nom, sexe, age);
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
    public static synchronized MaitreZoo getInstance() {
    	if (instance == null) {
    		instance = new MaitreZoo("nom", Enum_Sexe.Male, 20);
        }
        return instance;
    }
    
    
    /**
     * Methode pour examiner un enclos
     * 
     * @param enclos	Enclos a examiner
     * @return La chaine de caractere contenant les informations de l'enclos
     * @throws Exception 
     */
    public String examinerEnclos (Enclos enclos) throws Exception {
    	if (enclos != null)
    		return enclos.toString();
    	else
    		throw new Exception ("Erreur enclos");
    }
    
    
    /**
     * Methode pour nettoyer un enclos
     * La methode appellerra la methode specifique au type d'enclos
     * 
     * @param enclos	Enclos a nettoyer
     * @throws Exception 
     */
    public void nettoyerEnclos (Enclos enclos) throws Exception {
    	enclos.entretenirEnclos();
    }
    
    
    /**
     * Methode pour nourrir les creatures d'un enclos
     * @throws Exception 
     */
    public void nourrirCreaturesEnclos (Enclos enclos) throws Exception {
    	enclos.nourrirCreatures();
    }
    
    
    /**
     * Methode pour transferer une creature d'un enclos a un autre
     * 
     * @param creature		creature a transferer
     * @param enclosSource	enclos de depart
     * @param enclosDest	enclos de destination
     * @throws Exception 	si l'enclos de destination est plein
     */
    public void transfererCreature (Creature creature, Enclos enclosSource, Enclos enclosDest) throws Exception {
    	// verification place dans enclos destination
    	if (enclosDest.getNbCreatures() < enclosDest.getNbMaxCreatures()) {
    		// Verification creature existe
    		if (enclosSource.getListeCreatures().containsValue(creature)) {
    			enclosSource.supprimerCreature(creature);
            	enclosDest.ajouterCreature(creature);
            	enclosDest.reorganiserCles();
    		}
    		else
        		throw new Exception ("Creature introuvable");
    	}
    	else
    		throw new Exception ("Enclos de destination est plein");
    	
    }
}

