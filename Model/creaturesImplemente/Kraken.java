package creaturesImplemente;

import java.time.Duration;

import base.*;
import interfaces.*;
import references.*;

public class Kraken extends Ovipare implements CreatureMarine {
	
    private int dureeIncubation;

    /**
     * Constructeur de la classe Kraken.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param nomEspece        L'espèce du kraken.
     * @param sexe             Le sexe du kraken.
     * @param poids            Le poids du kraken.
     * @param taille           La taille du kraken.
     * @param bruit            Le bruit que fait le kraken.
     * @param dureeIncubation  La durée d'incubation spécifique du Kraken.
     */
    protected Kraken(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, int dureeIncubation) {
        super(nomEspece, sexe, poids, taille, bruit);
        this.dureeIncubation = dureeIncubation;
    }
    

    /**
     * Méthode de l'interface CreatureMarine : Nager.
     * Permet au kraken de nager dans l'eau.
     * 
     * @return Un message indiquant que le kraken nage.
     *        
     */
    @Override
    public String Nager() {
    	// TODO: Implémentez la logique spécifique
        return "Le kraken nage";
    }
    
    
    /**
     * Méthode pour pondre un œuf.
     * 
     * @param dateNaissance     La date de naissance de l'œuf.
     * @return Une instance de la classe Oeuf pondue par l'ovipare.
     */
    public Oeuf PondreOeuf(Creature papa) throws Exception {
    	return super.PondreOeuf(papa, dureeIncubation);
    }
}
