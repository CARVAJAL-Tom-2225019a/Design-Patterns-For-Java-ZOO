package maitreZoo;

import base.Creature;
import base.Enclos;
import creaturesImplemente.Humain;
import references.Enum_Sexe;

/**
 * Classe représentant l'unique maître du zoo (singleton)
 */
public class MaitreZoo extends Humain{
    // Instance unique de la classe MaitreZoo
    private static MaitreZoo instance;

    /**
     * Constructeur privé pour empêcher l'instanciation directe
     * @param nom	du maitre du zoo
     * @param sexe	du maitre du zoo
     * @param age	du maitre du zoo
     */
    private MaitreZoo (String nom, Enum_Sexe sexe, int age) {
    	super(nom, sexe, age);
    }
    

    /**
     * Méthode statique pour obtenir l'instance unique de la classe MaitreZoo
     *
     * @param nom  Le nom du maître du zoo
     * @param sexe Le sexe du maître du zoo
     * @param age  L'âge du maître du zoo
     * @return L'instance unique de la classe MaitreZoo
     */
    public static synchronized MaitreZoo getInstance(String nom, Enum_Sexe sexe, int age) {
        if (instance == null) {
            instance = new MaitreZoo(nom, sexe, age);
        }
        return instance;
    }
    
    /**
     * Méthode statique pour obtenir l'instance unique de la classe MaitreZoo avec des valeurs par défaut
     *
     * @return L'instance unique de la classe MaitreZoo
     */
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
     * @return l'enclos du maitre du zoo si il est pas null
     * @throws Exception 
     */
    public Enclos examinerEnclos (Enclos enclos) throws Exception {
    	if (enclos != null)
    		return enclos;
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
     * Methode pour soigner un enclos
     * @throws Exception 
     */
    public void soignerCreaturesEnclos (Enclos enclos) throws Exception {
    	enclos.soignerCreatures();
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
    		throw new Exception ("Enclos de destination est plein ("+enclosDest.getNom()+")");
    	
    }

    
    /**
     * Méthode pour lancer un combat entre deux créatures
     *
     * @param creature1 La première créature
     * @param creature2 La deuxième créature
     * @return La créature gagnante du combat
     * @throws Exception Si une erreur survient pendant le combat
     */
    public Creature lancerCombat(Creature creature1, Creature creature2) throws Exception {
    	return creature1.combattre(creature2);
    }
}

