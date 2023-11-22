package creaturesImplemente;

import base.*;
import interfaces.*;
import references.*;

import java.util.ArrayList;

/**
 * Cette classe correspond à la crature du dragon
 * qui est un ovipare
 * et qui est terrestre, aquatique, aerien et immortel
 *
 */
public class Dragon extends Ovipare implements CreatureTerrestre, CreatureMarine, CreatureVolante, CreatureImmortel {

    private final int dureeIncubation = 1;

    /**
     * Constructeur de la classe Dragon.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param nomEspece        L'espèce du dragon.
     * @param sexe             Le sexe du dragon.
     * @param poids            Le poids du dragon.
     * @param taille           La taille du dragon.
     */
    public Dragon(Dragon parent1,Dragon parent2,String bruit) {
        super(parent1, parent2);
        this.setAgressivite(Enum_Agressivite.cannibale);
        this.setNomEspece(parent1.getNomEspece());
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }

    public Dragon (String bruit) {
        super();
        this.setAgressivite(Enum_Agressivite.cannibale);
        this.setNomEspece(Enum_Especes.Dragon);
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }

    
    /**
     * Méthode de l'interface CreatureTerrestre : Courrir.
     * Permet au dragon de courir sous certaines conditions.
     * 
     * @return Un message indiquant que le dragon est en mouvement.
     * @throws Exception Si le dragon n'est pas en état de courir.
     */
    @Override
    public String Courrir() throws Exception {
        if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.PerdreNourriture();
            super.PerdreSommeil();
            return "Le dragon est en mouvement";
        } else {
            throw new Exception("Dragon pas en etat de courir");
        }
    }

    
    /**
     * Méthode de l'interface CreatureMarine : Nager.
     * Permet au dragon de nager sous certaines conditions.
     * 
     * @return Un message indiquant que le dragon nage.
     * @throws Exception Si le dragon n'est pas en état de nager.
     */
    @Override
    public String Nager() throws Exception {
        if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.PerdreNourriture();
            super.PerdreSommeil();
            return "Le dragon nage";
        } else {
            throw new Exception("Dragon pas en etat de nager");
        }
    }

    
    /**
     * Méthode de l'interface CreatureVolante : Voler.
     * Permet au dragon de voler sous certaines conditions.
     * 
     * @return Un message indiquant que le dragon vole.
     * @throws Exception Si le dragon n'est pas en état de voler.
     */
    @Override
    public String Voler() throws Exception {
        if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.PerdreNourriture();
            super.PerdreSommeil();
            return "Le dragon vole";
        } else {
            throw new Exception("Dragon pas en etat de voler");
        }
    }

    
    /**
     * Méthode de l'interface CreatureImmortel : Renaitre.
     * Implémente la logique de renaissance du dragon.
     */
    @Override
    public void Mourrir() {
        ReinitialiserCreature(); //TODO changer ca en un oeuf
    }
    

}
