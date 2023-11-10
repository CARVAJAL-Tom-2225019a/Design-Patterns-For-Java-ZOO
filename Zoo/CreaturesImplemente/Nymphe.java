package CreaturesImplemente;

import java.time.*;

import base.*;
import enums.*;
import insterfaces.*;

public class Nymphe extends Vivipare implements CreatureImmortel {
	
    private Duration dureeGestation;

    /**
     * Constructeur de la classe Nymphe.
     * 
     * @param nomEspece       L'espèce de la Nymphe.
     * @param sexe            Le sexe de la Nymphe.
     * @param poids           Le poids de la Nymphe.
     * @param taille          La taille de la Nymphe.
     * @param bruit           Le bruit que fait la Nymphe.
     * @param dureeGestation  La durée de gestation spécifique pour les nymphes.
     */
    public Nymphe(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeGestation) {
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
}
