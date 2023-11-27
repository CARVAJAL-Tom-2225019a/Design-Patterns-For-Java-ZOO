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

    private final int dureeIncubation = 1;

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
    public Phenix(Phenix parent1,Phenix parent2,String bruit) {
        super(parent1, parent2);
        this.setAgressivite(Enum_Aggressivite.farouche);
        this.setNomEspece(parent1.getNomEspece());
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }

    public Phenix (String bruit) {
        super();
        this.setAgressivite(Enum_Aggressivite.farouche);
        this.setNomEspece(Enum_Especes.Phenix);
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
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
    public String voler() throws Exception {
    	if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.perdreNourriture();
            super.perdreSommeil();
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
    public void mourrir() {
        reinitialiserCreature();
    }
    
    

}
