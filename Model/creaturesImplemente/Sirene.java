package creaturesImplemente;

import base.*;
import interfaces.*;
import references.*;

/**
 * Cette classe correspond à la crature sirene
 * qui est un vivipare
 * et qui est aquatique
 *
 */
public class Sirene extends Vivipare implements CreatureMarine {

    /**
     * Constructeur de la classe Sirene.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param bruit           Le bruit que fait la sirène.
     */

    protected Sirene(Sirene parent1,Sirene parent2, String bruit) {
        super(parent1, parent2, parent1.getDureeGestation());
        this.setAgressivite(Enum_Agressivite.agressif);
        this.setNomEspece(Enum_Especes.Kraken);
        this.setDureeGestation(getDureeGestation());
        this.setBruit( bruit);
    }
    protected Sirene( String bruit) {
        super();
        this.setAgressivite(Enum_Agressivite.agressif);
        this.setNomEspece(Enum_Especes.Kraken);
        this.setDureeGestation(getDureeGestation());
        this.setBruit( bruit);
    }

    
    /**
     * Méthode de l'interface CreatureMarine : Nager.
     * Permet à la sirène de nager dans l'eau.
     * 
     * @return Un message indiquant que la sirène nage.
     * @throws Exception 
     * 
     */
    @Override
    public String Nager() throws Exception {
    	if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.PerdreNourriture();
            super.PerdreSommeil();
            return "La sirene nage";
        } else {
            throw new Exception("Sirene pas en etat de nager");
        }
    }
    
    
    /**
     * Méthode pour mettre bas une nouvelle créature
     * 
     * @return Une instance de la classe Creature qui né.
     */
    public Creature MettreBas(Enum_Sexe sexe, double poids, double taille) throws Exception {
    	return super.MettreBas(sexe, poids, taille);
    }
}
