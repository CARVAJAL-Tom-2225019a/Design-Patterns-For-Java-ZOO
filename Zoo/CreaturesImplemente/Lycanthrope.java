package CreaturesImplemente;

import java.time.*;

import base.*;
import enums.*;
import insterfaces.*;

public class Lycanthrope extends Vivipare implements CreatureTerrestre {
	
    private Duration dureeGestation;

    /**
     * Constructeur de la classe Lycanthrope.
     * 
     * @param nomEspece       L'espèce du lycanthrope.
     * @param sexe            Le sexe du lycanthrope.
     * @param poids           Le poids du lycanthrope.
     * @param taille          La taille du lycanthrope.
     * @param bruit           Le bruit que fait le lycanthrope.
     * @param dureeGestation  La durée de gestation spécifique pour les lycanthrope.
     */
    public Lycanthrope(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeGestation) {
        super(nomEspece, sexe, poids, taille, bruit);
        this.dureeGestation = dureeGestation;
    }
    

    /**
     * Méthode de l'interface CreatureTerrestre : Courrir.
     * Permet au lycanthrope de courir sur terre.
     * 
     * @return Un message indiquant que le lycanthrope court.
     *         Note : Dans cet exemple, la méthode ne fait rien (retourne null),
     *         car la logique spécifique de course du lycanthrope n'est pas encore implémentée.
     *         Vous devriez remplacer le retour null par la logique réelle de course du lycanthrope.
     */
    @Override
    public String Courrir() {
        // TODO: Implémentez la logique spécifique de course du lycanthrope
        return "Le lycanthrope court";
    }
}
