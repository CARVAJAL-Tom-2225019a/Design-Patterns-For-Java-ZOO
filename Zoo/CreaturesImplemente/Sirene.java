package CreaturesImplemente;

import java.time.Duration;

import base.*;
import enums.*;
import interfaces.*;

public class Sirene extends Vivipare implements CreatureMarine {
	
    private Duration dureeGestation;

    /**
     * Constructeur de la classe Sirene.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param nomEspece       L'espèce de la sirène.
     * @param sexe            Le sexe de la sirène.
     * @param poids           Le poids de la sirène.
     * @param taille          La taille de la sirène.
     * @param bruit           Le bruit que fait la sirène.
     * @param dureeGestation  La durée de gestation spécifique pour les sirènes.
     */
    protected Sirene(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeGestation) {
        super(nomEspece, sexe, poids, taille, bruit);
        this.dureeGestation = dureeGestation;
    }

    
    /**
     * Méthode de l'interface CreatureMarine : Nager.
     * Permet à la sirène de nager dans l'eau.
     * 
     * @return Un message indiquant que la sirène nage.
     * 
     */
    @Override
    public String Nager() {
        // TODO: Implémentez la logique spécifique de nage de la sirène
        return "La sirène nage";
    }
    
    
    /**
     * Méthode pour mettre bas une nouvelle créature
     * 
     * @return Une instance de la classe Creature qui né.
     */
    public Creature MettreBas(Enum_Sexe sexe, double poids, double taille) throws Exception {
    	return super.MettreBas(sexe, poids, taille, dureeGestation);
    }
}
