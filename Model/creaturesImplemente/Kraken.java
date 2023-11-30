package creaturesImplemente;

import base.*;
import interfaces.*;
import references.*;


/**
 * Cette classe correspond à la créature Kraken, qui est un ovipare et aquatique
 */
public class Kraken extends Ovipare implements CreatureMarine {

    private final int dureeIncubation = 1;
    
    /**
     * Constructeur de la classe Kraken
     * Protected afin que la création se fasse essentiellement depuis le factory
     *
     * @param parent1          Le premier parent du Kraken
     * @param parent2          Le deuxième parent du Kraken
     * @param bruit            Le bruit que fait le Kraken.
     */
    protected Kraken(Kraken parent1,Kraken parent2, String bruit) {
        super(parent1, parent2);
        this.setAgressivite(Enum_Aggressivite.agressif);
        this.setNomEspece(Enum_Especes.Kraken);
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }
    
    /**
     * Constructeur de la classe Kraken
     * Protected afin que la création se fasse essentiellement depuis le factory
     *
     * @param bruit            Le bruit que fait le Kraken
     */
    protected Kraken(String bruit) {
        super();
        this.setAgressivite(Enum_Aggressivite.agressif);
        this.setNomEspece(Enum_Especes.Kraken);
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }



    /**
     * Méthode de l'interface CreatureMarine : Nager
     * Permet au kraken de nager dans l'eau
     * 
     * @return Un message indiquant que le kraken nage
     * @throws Exception si le kraken n'est pas en etat de nager
     *        
     */
    @Override
    public String nager() throws Exception {
    	if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.perdreNourriture();
            super.perdreSommeil();
            return "Le Kraken nage";
        } else {
            throw new Exception("Kraken "+getPrenom()+" pas en etat de nager");
        }
    }

}
