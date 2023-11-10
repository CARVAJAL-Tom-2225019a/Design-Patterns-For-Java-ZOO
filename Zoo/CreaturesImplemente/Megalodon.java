package CreaturesImplemente;

import java.time.Duration;

import base.*;
import enums.*;
import insterfaces.*;

public class Megalodon extends Ovipare implements CreatureMarine {
	
    private Duration dureeIncubation;

    /**
     * Constructeur de la classe Megalodon.
     * 
     * @param nomEspece        L'espèce du Megalodon.
     * @param sexe             Le sexe du Megalodon.
     * @param poids            Le poids du Megalodon.
     * @param taille           La taille du Megalodon.
     * @param bruit            Le bruit que fait le Megalodon.
     * @param dureeIncubation  La durée d'incubation spécifique pour les megalodons.
     */
    public Megalodon(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeIncubation) {
        super(nomEspece, sexe, poids, taille, bruit);
        this.dureeIncubation = dureeIncubation;
    }

    
    /**
     * Méthode de l'interface CreatureMarine : Nager.
     * Permet au Megalodon de nager dans l'eau.
     * 
     * @return Un message indiquant que le Megalodon nage.
     *        
     */
    @Override
    public String Nager() {
        // TODO: Implémentez la logique spécifique de nage du Megalodon
        return "Le Megalodon nage";
    }
}
