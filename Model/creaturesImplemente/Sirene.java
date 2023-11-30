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
     * Constructeur de la classe Sirene
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param parent1          Parent 1
     * @param parent2          Parent 2
     * @param bruit            Le bruit que fait la sirène
     */
    protected Sirene(Sirene parent1,Sirene parent2, String bruit) {
        super(parent1, parent2, parent1.getDureeGestation());
        this.setAgressivite(Enum_Aggressivite.curieux);
        this.setNomEspece(Enum_Especes.Sirene);
        this.setDureeGestation(getDureeGestation());
        this.setBruit( bruit);
    }
    
    /**
     * Constructeur de la classe Sirene
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param bruit            Le bruit que fait la sirène
     */
    protected Sirene( String bruit) {
        super();
        this.setAgressivite(Enum_Aggressivite.curieux);
        this.setNomEspece(Enum_Especes.Sirene);
        this.setDureeGestation(getDureeGestation());
        this.setBruit( bruit);
    }

    
    /**
     * Méthode de l'interface CreatureMarine : Nager
     * Permet à la sirène de nager
     * 
     * @return Un message indiquant que la sirène nage
     * @throws Exception Si la sirène n'est pas en état de nager
     */
    @Override
    public String nager() throws Exception {
    	if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.perdreNourriture();
            super.perdreSommeil();
            return "La sirene "+getPrenom()+" nage";
        } else {
            throw new Exception("Sirene "+getPrenom()+" pas en etat de nager");
        }
    }
    
    
    /**
     * Méthode pour mettre bas une nouvelle créature
     * 
     * @return Une instance de la classe Creature qui naît
     * @throws Exception Si la sirène n'est pas en etat de mettre bas / pas d'enfant
     */
    public Creature mettreBas() throws Exception {
    	return super.mettreBas();
    }
}
