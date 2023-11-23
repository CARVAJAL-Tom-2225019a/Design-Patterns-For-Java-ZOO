package creaturesImplemente;

import base.*;
import interfaces.*;
import references.*;

/**
 * Cette classe correspond à la crature nymphe
 * qui est un vivipare
 * et qui est immortel
 *
 */
public class Nymphe extends Vivipare implements CreatureImmortel {

    private final int dureeGestation = 1;

    /**
     * Constructeur de la classe Nymphe.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param nomEspece       L'espèce de la Nymphe.
     * @param sexe            Le sexe de la Nymphe.
     * @param poids           Le poids de la Nymphe.
     * @param taille          La taille de la Nymphe.
     * @param bruit           Le bruit que fait la Nymphe.
     * @param dureeGestation  La durée de gestation spécifique pour les nymphes.
     */
    protected Nymphe(Nymphe parent1,Nymphe parent2, String bruit) {
        super(parent1, parent2, parent1.getDureeGestation());
        this.setAgressivite(Enum_Aggressivite.defensif);
        this.setNomEspece(Enum_Especes.Nymphe);
        this.setDureeGestation(dureeGestation);
        this.setBruit( bruit);
    }
    protected Nymphe( String bruit) {
        super();
        this.setAgressivite(Enum_Aggressivite.defensif);
        this.setNomEspece(Enum_Especes.Nymphe);
        this.setDureeGestation(dureeGestation);
        this.setBruit( bruit);
    }
    
    /**
     * Méthode de l'interface CreatureImmortel : Mourrir.
     * Implémente la logique de renaissance de la Nymphe.
     */
    @Override
    public void Mourrir() {
        	ReinitialiserCreature();
    }
    
    
    /**
     * Méthode pour mettre bas une nouvelle créature
     * 
     * @return Une instance de la classe Creature qui né.
     */
    public Creature MettreBas() throws Exception {
    	return super.MettreBas();
    }
}
