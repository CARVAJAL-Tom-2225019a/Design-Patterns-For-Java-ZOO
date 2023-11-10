package CreaturesImplemente;

import java.time.*;

import base.*;
import enums.*;
import insterfaces.*;

public class Licorne extends Vivipare implements CreatureTerrestre {
	
    private Duration dureeGestation;

    /**
     * Constructeur de la classe Licorne.
     * 
     * @param nomEspece       L'espèce de la licorne.
     * @param sexe            Le sexe de la licorne.
     * @param poids           Le poids de la licorne.
     * @param taille          La taille de la licorne.
     * @param bruit           Le bruit que fait la licorne.
     * @param dureeGestation  La durée de gestation spécifique pour les licornes.
     */
    public Licorne(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeGestation) {
        super(nomEspece, sexe, poids, taille, bruit);
        this.dureeGestation = dureeGestation;
    }

    
    /**
     * Méthode de l'interface CreatureTerrestre : Courrir.
     * Permet à la licorne de courir sur terre.
     * 
     * @return Un message indiquant que la licorne court.
     * 
     */
    @Override
    public String Courrir() {
        // TODO: Implémentez la logique spécifique de course de la licorne
        return "La licorne court";
    }
}
