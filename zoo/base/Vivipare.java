package base;

import java.time.*;

import creaturesImplemente.FactoryCreature;
import references.*;

public abstract class Vivipare extends Creature {
	LocalDate dateConceptionEnfant;
	

    /**
     * Constructeur de la classe Vivipare.
     * 
     * @param nomEspece      L'espèce de la créature.
     * @param sexe           Le sexe de la créature.
     * @param poids          Le poids de la créature.
     * @param taille         La taille de la créature.
     * @param bruit          Le bruit que fait la créature.
     */
    public Vivipare(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit) {
        super(nomEspece, sexe, poids, taille, bruit);
        dateConceptionEnfant = null;
    }
    
    /**
     * Getters
     */
    public LocalDate getDateConceptionEnfant() {
		return dateConceptionEnfant;
	}
    

    
    /**
     * Méthode pour construire un enfant
     * 
     * @param papa	correspond a la deuxieme creature qui permettra de concevoir enfant
     * @return String qui permet de notifier que l'enfant a ete concu
     * 
     */
    // TODO : gestion sauvegarde généalogie
    public String concevoirUnEnfant (Creature papa) throws Exception {
    	// Verification du statut des creatures
    	if (super.isVivant() && !super.isEnTrainDeDormir() && papa.isVivant() && !papa.isEnTrainDeDormir()) {
    		// Verification femelle et male, et meme espece
    		if (super.getSexe() == Enum_Sexe.Femelle  &&  papa.getSexe() == Enum_Sexe.Male  && super.getNomEspece()==papa.getNomEspece()) {
        		// Verification qu'un enfant n'est pas deja en cours
    			if (dateConceptionEnfant==null) {
    				dateConceptionEnfant = LocalDate.now();
    				return "un enfant a ete conçu";
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
    public Creature MettreBas(Enum_Sexe sexe, double poids, double taille, Duration dureeGestation) throws Exception {
        if (super.isVivant() && super.getSexe() == Enum_Sexe.Femelle) {
        	// si temps de gestation passé
        	Duration tempsPasse = Duration.between(dateConceptionEnfant, LocalDate.now());
        	if (tempsPasse.compareTo(dureeGestation) > 0) {
        		// Création de la nouvelle créature
        		dateConceptionEnfant = null;
                return FactoryCreature.newCreature(super.getNomEspece(), sexe, poids, taille);
        	}
        	else 
        		throw new Exception ("Le bebe n'est pas pret");
        } else {
            throw new Exception("Statut du vivipare invalide");
        }
    }
}
