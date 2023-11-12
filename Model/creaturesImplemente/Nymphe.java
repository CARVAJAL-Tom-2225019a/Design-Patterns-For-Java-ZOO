package creaturesImplemente;

import java.time.*;

import base.*;
import interfaces.*;
import references.*;

public class Nymphe extends Vivipare implements CreatureImmortel {
	
    private Duration dureeGestation;

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
    protected Nymphe(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeGestation) {
        super(nomEspece, sexe, poids, taille, bruit);
        this.dureeGestation = dureeGestation;
    }

    
    /**
     * Méthode de l'interface CreatureImmortel : Renaitre.
     * Implémente la logique de renaissance de la Nymphe.
     */
    @Override
    public void Renaitre() {
        // TODO: Implémentez la logique de renaissance de la Nymphe
    }
    
    
    /**
     * Méthode pour mettre bas une nouvelle créature
     * 
     * @return Une instance de la classe Creature qui né.
     */
    public Creature MettreBas(Enum_Sexe sexe, double poids, double taille) throws Exception {
    	return super.MettreBas(sexe, poids, taille, dureeGestation);
    }
}