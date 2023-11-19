package creaturesImplemente;

import base.*;
import interfaces.*;
import references.*;

/**
 * Cette classe correspond à la crature phenix
 * qui est un ovipare
 * et qui est aerien et immortel
 *
 */
public class Phenix extends Ovipare implements CreatureVolante, CreatureImmortel {
	
    /**
     * Constructeur de la classe Phenix.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param nomEspece        L'espèce du phénix.
     * @param sexe             Le sexe du phénix.
     * @param poids            Le poids du phénix.
     * @param taille           La taille du phénix.
     * @param bruit            Le bruit que fait le phénix.
     * @param dureeIncubation  La durée d'incubation spécifique pour les pehnixs.
     */
    protected Phenix(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, int dureeIncubation) {
        super(nomEspece, sexe, poids, taille, bruit, dureeIncubation);
    }


    /**
     * Méthode de l'interface CreatureVolante : Voler.
     * Permet au phénix de voler dans les airs.
     * 
     * @return Un message indiquant que le phénix vole.
     * @throws Exception 
     * 
     */
    @Override
    public String Voler() throws Exception {
    	if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.PerdreNourriture();
            super.PerdreSommeil();
            return "Le phenix vole";
        } else {
            throw new Exception("Phenix pas en etat de voler");
        }
    }

    
    /**
     * Méthode de l'interface CreatureImmortel : Mourrir.
     * Implémente la logique de renaissance du phénix.
     * 
     */
    @Override
    public void Mourrir() {
        ReinitialiserCreature();
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
