package creaturesImplemente;

import base.*;
import interfaces.*;
import references.*;

/**
 * Cette classe correspond à la créature megalodon,
 * qui est un ovipare et qui est aquatique
 */
public class Megalodon extends Ovipare implements CreatureMarine {
	
    private final int dureeIncubation = 1;
    
    /**
     * Constructeur de la classe Megalodon
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param parent1 Parent 1 pour la reproduction
     * @param parent2 Parent 2 pour la reproduction
     * @param bruit Le bruit que fait le Megalodon
     */
    protected Megalodon(Kraken parent1,Kraken parent2, String bruit) {
        super(parent1, parent2);
        this.setAgressivite(Enum_Aggressivite.curieux);
        this.setNomEspece(Enum_Especes.Megalodon);
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }
    
    /**
     * Constructeur de la classe Megalodon avec un seul parent
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param bruit Le bruit que fait le Megalodon
     */
    protected Megalodon( String bruit) {
        super();
        this.setAgressivite(Enum_Aggressivite.curieux);
        this.setNomEspece(Enum_Especes.Megalodon);
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }

    
    /**
     * Méthode de l'interface CreatureMarine : Nager
     * Permet au Megalodon de nager dans l'eau
     * 
     * @return Un message indiquant que le Megalodon nage
     * @throws Exception Si le megalodon n'est pas en etat de nager
     */
    @Override
    public String nager() throws Exception {
    	if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.perdreNourriture();
            super.perdreSommeil();
            return "Le megalodon "+getPrenom()+" nage";
        } else {
            throw new Exception("Megalodon "+getPrenom()+" pas en etat de nager");
        }
    }
}
