package base;

import creaturesImplemente.*;
import references.*;

public abstract class Ovipare extends Creature {

    /**
     * Constructeur de la classe Ovipare.
     * 
     * @param nomEspece L'espèce de la créature.
     * @param sexe      Le sexe de la créature.
     * @param poids     Le poids de la créature.
     * @param taille    La taille de la créature.
     * @param bruit     Le bruit que fait la créature.
     */
    public Ovipare(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, int duree) {
        super(nomEspece, sexe, poids, taille, bruit, duree);
    }
    
    
    /**
     * Méthode pour pondre un œuf.
     * 
     * @param dateNaissance     La date de naissance de l'œuf.
     * @param dureeIncubation   La durée d'incubation spécifique.
     * @return Une instance de la classe Oeuf pondue par l'ovipare.
     * @throws Exception Si la créature n'est pas vivante ou si elle n'est pas de sexe femelle.
     */
    public Oeuf PondreOeuf(Creature papa, int duree) throws Exception {
        if (super.isVivant() && super.getSexe() == Enum_Sexe.Femelle && papa.isVivant() && papa.getSexe() == Enum_Sexe.Male && super.getNomEspece()==papa.getNomEspece()) {
            return new Oeuf(super.getNomEspece(), duree);
        } else {
            throw new Exception("Statut creature invalide");
        }
    }
}
