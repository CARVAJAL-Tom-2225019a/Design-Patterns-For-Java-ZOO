package creaturesImplemente;

import base.*;
import interfaces.*;
import references.*;

public class Licorne extends Vivipare implements CreatureTerrestre {

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
    protected Licorne(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, int dureeGestation) {
        super(nomEspece, sexe, poids, taille, bruit, dureeGestation);
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
    public String Courrir() throws Exception {
    	if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.PerdreNourriture();
            super.PerdreSommeil();
            return "La licorne est en mouvement";
        } else {
            throw new Exception("Licorne pas en etat de courir");
        }
    }
    
    
    
    /**
     * Méthode pour mettre bas une nouvelle créature
     * 
     * @return Une instance de la classe Creature qui né.
     */
    public Creature MettreBas(Enum_Sexe sexe, double poids, double taille) throws Exception {
    	return super.MettreBas(sexe, poids, taille);
    }
}
