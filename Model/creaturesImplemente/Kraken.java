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
        super(nomEspece, sexe, poids, taille, bruit, dureeIncubation);
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
    
    
    /**
     * Méthode pour pondre un œuf.
     * 
     * @param dateNaissance     La date de naissance de l'œuf.
     * @return Une instance de la classe Oeuf pondue par l'ovipare.
     */
    public Oeuf PondreOeuf(Creature papa) throws Exception {
    	return super.PondreOeuf(papa, super.getDureePourEnfant());
    }
}
