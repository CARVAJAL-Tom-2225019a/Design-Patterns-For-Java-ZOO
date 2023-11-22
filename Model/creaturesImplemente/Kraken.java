package creaturesImplemente;

import base.*;
import interfaces.*;
import references.*;

/**
 * Cette classe correspond à la crature kraken
 * qui est un ovipare
 * et qui est aquatique
 *
 */
public class Kraken extends Ovipare implements CreatureMarine {

    private final int dureeIncubation = 1;
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
    protected Kraken(Kraken parent1,Kraken parent2, String bruit) {
        super(parent1, parent2);
        this.setAgressivite(Enum_Agressivite.agressif);
        this.setNomEspece(Enum_Especes.Kraken);
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }
    protected Kraken( String bruit) {
        super();
        this.setAgressivite(Enum_Agressivite.agressif);
        this.setNomEspece(Enum_Especes.Kraken);
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }



    /**
     * Méthode de l'interface CreatureMarine : Nager.
     * Permet au kraken de nager dans l'eau.
     * 
     * @return Un message indiquant que le kraken nage.
     * @throws Exception 
     *        
     */
    @Override
    public String Nager() throws Exception {
    	if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.PerdreNourriture();
            super.PerdreSommeil();
            return "Le Kraken nage";
        } else {
            throw new Exception("Kraken pas en etat de nager");
        }
    }

}
