package base;

import java.time.*;
import enums.*;

public abstract class Vivipare extends Creature {

    /**
     * Constructeur de la classe Vivipare.
     * 
     * @param nomEspece      L'espèce de la créature.
     * @param sexe           Le sexe de la créature.
     * @param poids          Le poids de la créature.
     * @param taille         La taille de la créature.
     * @param bruit          Le bruit que fait la créature.
     */
    public Vivipare(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit) {
        super(nomEspece, sexe, poids, taille, bruit);
    }

    
    /**
     * Méthode pour mettre bas une nouvelle créature vivipare.
     * 
     * @param sexe   Le sexe de la nouvelle créature.
     * @param poids  Le poids de la nouvelle créature.
     * @param taille La taille de la nouvelle créature.
     * @param duree  La durée de gestation spécifique de l'espèce.
     * @return Une instance de la classe Creature qui né.
     * @throws Exception Si le vivipare n'est pas vivant ou s'il n'est pas de sexe femelle.
     */
    public Creature MettreBas(Enum_Sexe sexe, double poids, double taille, Duration duree) throws Exception {
        if (super.isVivant() && super.getSexe() == Enum_Sexe.Femelle) {
            // Création de la nouvelle créature
            CreateCreature fabriqueCreature = new CreateCreature();
            return fabriqueCreature.createCreature(super.getNomEspece(), sexe, poids, taille, super.getBruit(), duree);
        } else {
            throw new Exception("Statut du vivipare invalide");
        }
    }
}
