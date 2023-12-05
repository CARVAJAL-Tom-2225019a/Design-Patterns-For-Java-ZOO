package creaturesImplemente;

import base.Creature;
import base.Vivipare;
import interfaces.CreatureTerrestre;
import references.Enum_Aggressivite;
import references.Enum_Especes;

/**
 * Cette classe correspond à la créature Licorne, qui est un vivipare et terrestre.
 */
public class Licorne extends Vivipare implements CreatureTerrestre {

    private final int dureeGestation = 1;

    /**
     * Constructeur de la classe Licorne
     * Protected afin que la création se fasse essentiellement depuis le factory
     *
     * @param parent1          Le premier parent de la Licorne
     * @param parent2          Le deuxième parent de la Licorne
     * @param bruit            Le bruit que fait la Licorne
     */
    protected Licorne(Licorne parent1,Licorne parent2, String bruit) {
        super(parent1, parent2, parent1.getDureeGestation());
        this.setAgressivite(Enum_Aggressivite.pacifique);
        this.setNomEspece(Enum_Especes.Licorne);
        this.setDureeGestation(dureeGestation);
        this.setBruit( bruit);
    }
    
    /**
     * Constructeur de la classe Licorne
     * Protected afin que la création se fasse essentiellement depuis le factory
     *
     * @param bruit            Le bruit que fait la Licorne
     * @throws Exception       Si la création de la Licorne échoue
     */
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
            return "La licorne "+ getPrenom()+" est en mouvement";
        } else {
            throw new Exception("Licorne pas en etat de courir, la créature est trop fatigué ou a faim\"");
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
