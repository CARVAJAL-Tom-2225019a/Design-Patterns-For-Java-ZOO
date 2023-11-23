package creaturesImplemente;

import base.*;
import interfaces.*;
import references.*;

/**
 * Cette classe correspond à la crature megalodon
 * qui est un ovipare
 * et qui est aquatique
 *
 */
public class Megalodon extends Ovipare implements CreatureMarine {
    private final int dureeIncubation = 1;
    /**
     * Constructeur de la classe Megalodon.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param bruit            Le bruit que fait le Megalodon.
     */
    protected Megalodon(Kraken parent1,Kraken parent2, String bruit) {
        super(parent1, parent2);
        this.setAgressivite(Enum_Aggressivite.curieux);
        this.setNomEspece(Enum_Especes.Megalodon);
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }
    protected Megalodon( String bruit) {
        super();
        this.setAgressivite(Enum_Aggressivite.curieux);
        this.setNomEspece(Enum_Especes.Megalodon);
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }

    
    /**
     * Méthode de l'interface CreatureMarine : Nager.
     * Permet au Megalodon de nager dans l'eau.
     * 
     * @return Un message indiquant que le Megalodon nage.
     * @throws Exception 
     *        
     */
    @Override
    public String Nager() throws Exception {
    	if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.PerdreNourriture();
            super.PerdreSommeil();
            return "Le megalodon nage";
        } else {
            throw new Exception("Megalodon pas en etat de nager");
        }
    }
}
