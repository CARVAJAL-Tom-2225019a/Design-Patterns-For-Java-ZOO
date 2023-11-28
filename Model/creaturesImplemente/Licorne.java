package creaturesImplemente;

import base.*;
import interfaces.*;
import references.*;

/**
 * Cette classe correspond à la crature licorne
 * qui est un vivipare
 * et qui est terrestre
 *
 */
public class Licorne extends Vivipare implements CreatureTerrestre {

    private final int dureeGestation = 1;
    /**
     * Constructeur de la classe Licorne.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param nomEspece       L'espèce de la licorne.
     * @param sexe            Le sexe de la licorne.
     * @param poids           Le poids de la licorne.
     * @param taille          La taille de la licorne.
     * @param bruit           Le bruit que fait la licorne.
     * @param dureeGestation  La durée de gestation spécifique pour les licornes.
     */


    protected Licorne(Licorne parent1,Licorne parent2, String bruit) {
        super(parent1, parent2, parent1.getDureeGestation());
        this.setAgressivite(Enum_Aggressivite.pacifique);
        this.setNomEspece(Enum_Especes.Licorne);
        this.setDureeGestation(dureeGestation);
        this.setBruit( bruit);
    }
    protected Licorne( String bruit) {
        super();
        this.setAgressivite(Enum_Aggressivite.pacifique);
        this.setNomEspece(Enum_Especes.Licorne);
        this.setDureeGestation(dureeGestation);
        this.setBruit( bruit);
    }
    
    /**
     * Méthode de l'interface CreatureTerrestre : Courrir.
     * Permet à la licorne de courir sur terre.
     * 
     * @return Un message indiquant que la licorne court.
     * @throws Exception 
     * 
     */
    @Override
    public String courrir() throws Exception {
    	if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.perdreNourriture();
            super.perdreSommeil();
            return "La "+super.getPrenom()+" est en mouvement";
        } else {
            throw new Exception(super.getPrenom()+" pas en etat de courir, la créature est trop fatigué ou a faim");
        }
    }
    
    
    
    /**
     * Méthode pour mettre bas une nouvelle créature
     * 
     * @return Une instance de la classe Creature qui né.
     */
    public Creature mettreBas() throws Exception {
    	return super.mettreBas();
    }

}
