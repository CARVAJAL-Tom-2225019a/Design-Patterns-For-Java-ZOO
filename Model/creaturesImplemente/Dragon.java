package creaturesImplemente;

import java.time.Duration;

import base.*;
import interfaces.*;
import references.*;

public class Dragon extends Ovipare implements CreatureTerrestre, CreatureMarine, CreatureVolante, CreatureImmortel {

	private Duration dureeIncubation;

    /**
     * Constructeur de la classe Dragon.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param nomEspece        L'espèce du dragon.
     * @param sexe             Le sexe du dragon.
     * @param poids            Le poids du dragon.
     * @param taille           La taille du dragon.
     */
    protected Dragon(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeIncubation) {
        super(nomEspece, sexe, poids, taille, bruit);
        this.dureeIncubation = dureeIncubation;
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
    public void Renaitre() {
        // TODO: Implémentez la logique de renaissance du dragon
    }
    
    
    /**
     * Méthode pour pondre un œuf.
     * 
     * @param dateNaissance     La date de naissance de l'œuf.
     * @return Une instance de la classe Oeuf pondue par l'ovipare.
     */
    public Oeuf PondreOeuf(Creature papa) throws Exception {
    	return super.PondreOeuf(papa, dureeIncubation);
    }
}
