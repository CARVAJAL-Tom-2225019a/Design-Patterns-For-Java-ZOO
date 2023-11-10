package CreaturesImplemente;

import java.time.Duration;

import base.*;
import enums.*;
import insterfaces.*;

public class Kraken extends Ovipare implements CreatureMarine {
	
    private Duration dureeIncubation;

    /**
     * Constructeur de la classe Kraken.
     * 
     * @param nomEspece        L'espèce du kraken.
     * @param sexe             Le sexe du kraken.
     * @param poids            Le poids du kraken.
     * @param taille           La taille du kraken.
     * @param bruit            Le bruit que fait le kraken.
     * @param dureeIncubation  La durée d'incubation spécifique du Kraken.
     */
    public Kraken(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeIncubation) {
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
}
