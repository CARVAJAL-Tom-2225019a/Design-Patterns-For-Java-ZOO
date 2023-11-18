package creaturesImplemente;

import base.*;
import interfaces.*;
import references.*;

public class Megalodon extends Ovipare implements CreatureMarine {

    /**
     * Constructeur de la classe Megalodon.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param nomEspece        L'espèce du Megalodon.
     * @param sexe             Le sexe du Megalodon.
     * @param poids            Le poids du Megalodon.
     * @param taille           La taille du Megalodon.
     * @param bruit            Le bruit que fait le Megalodon.
     * @param dureeIncubation  La durée d'incubation spécifique pour les megalodons.
     */
    protected Megalodon(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, int dureeIncubation) {
        super(nomEspece, sexe, poids, taille, bruit, dureeIncubation);
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
