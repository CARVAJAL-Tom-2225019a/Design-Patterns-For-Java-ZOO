package base;

import java.util.Random;

import creaturesImplemente.FactoryCreature;
import references.*;

public abstract class Vivipare extends Creature {
	
	private  int nbJourConceptionRestant;
	
    /**
     * Constructeur de la classe Vivipare.
     * 
     * @param nomEspece      L'espèce de la créature.
     * @param sexe           Le sexe de la créature.
     * @param poids          Le poids de la créature.
     * @param taille         La taille de la créature.
     * @param bruit          Le bruit que fait la créature.
     * @param duree			 Duree de conception pour un enfant
     */
    public Vivipare(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, int duree) {
        super(nomEspece, sexe, poids, taille, bruit, duree);
        nbJourConceptionRestant=0;
    }
    
    /**
     * Getters
     */
    public int getNbJourConceptionRestant() {
		return nbJourConceptionRestant;
	}
    
    public void RemiseAZeroApresNaissance() {
    	nbJourConceptionRestant=0;
    }
    

    /**
     * Méthode pour construire un enfant
     * 
     * @param papa	correspond a la deuxieme creature qui permettra de concevoir enfant
     * @return String qui permet de notifier que l'enfant a ete concu
     * 
     */
    // TODO : gestion sauvegarde généalogie
    public void concevoirUnEnfant (Creature papa, int duree) throws Exception {
    	// Verification du statut des creatures
    	if (super.isVivant() && !super.isEnTrainDeDormir() && papa.isVivant() && !papa.isEnTrainDeDormir()) {
    		// Verification femelle et male, et meme espece
    		if (super.getSexe() == Enum_Sexe.Femelle  &&  papa.getSexe() == Enum_Sexe.Male  && super.getNomEspece()==papa.getNomEspece()) {
        		// Verification qu'un enfant n'est pas deja en cours
    			if (getNbJourConceptionRestant()==0) {
    				nbJourConceptionRestant = duree;
    			}
    			else
    				throw new Exception ("Un ou plusieurs enfants sont deja en construction");
        	}
        	else
        		throw new Exception ("La nature autorise seulement une femelle de concevoir un enfant avec un male de la meme espece");
    	}
    	else 
    		throw new Exception ("Les deux creatures doivent etre vivante et reveille pour concevoir un enfant");
    	
    }
    
    public Creature VerificationEnfantEnConception() throws Exception {
    	Random random = new Random(System.currentTimeMillis());
    	double poids = 1 + (random.nextDouble() * 49);
		double taille = 1 + (random.nextDouble() * 49);
    	if (DecrementerNombreJourRestantAvantNaissance() == 0)
			return MettreBas(Creature.SexeAleatoire(), poids, taille);
		return null;
    }
    

	/**
     * Méthode pour mettre bas une nouvelle créature vivipare.
     * 
     * @param sexe   Le sexe de la nouvelle créature.
     * @param poids  Le poids de la nouvelle créature.
     * @param taille La taille de la nouvelle créature.
     * @param duree  La durée de gestation spécifique de l'espèce.
     * @return Une instance de la classe Creature qui né.
     * @throws Exception Si le vivipare n'est pas vivant ou s'il n'est pas de sexe femelle.
     */
    public Creature MettreBas(Enum_Sexe sexe, double poids, double taille) throws Exception {
    	return FactoryCreature.newCreature(super.getNomEspece(), sexe, poids, taille);
    }
    
    
    /**
     * Methode pour decrementer le nombre de jour restant
     * @throws Exception 
     */
    public int DecrementerNombreJourRestantAvantNaissance (){
    	if (nbJourConceptionRestant > 0) {
    		return nbJourConceptionRestant--;
    	}
    	else
    		return 0;
    }
}
