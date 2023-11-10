package CreaturesImplemente;

import java.time.Duration;

import base.*;
import enums.*;
import interfaces.*;

public class Phenix extends Ovipare implements CreatureVolante, CreatureImmortel {
	
    private Duration dureeIncubation;

    /**
     * Constructeur de la classe Phenix.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param nomEspece        L'espèce du phénix.
     * @param sexe             Le sexe du phénix.
     * @param poids            Le poids du phénix.
     * @param taille           La taille du phénix.
     * @param bruit            Le bruit que fait le phénix.
     * @param dureeIncubation  La durée d'incubation spécifique pour les pehnixs.
     */
    protected Phenix(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeIncubation) {
        super(nomEspece, sexe, poids, taille, bruit);
        this.dureeIncubation = dureeIncubation;
    }

    
    /**
     * Méthode de l'interface CreatureVolante : Voler.
     * Permet au phénix de voler dans les airs.
     * 
     * @return Un message indiquant que le phénix vole.
     * 
     */
    @Override
    public String Voler() {
        // TODO: Implémentez la logique spécifique de vol du phénix
        return "Le phénix vole";
    }

    
    /**
     * Méthode de l'interface CreatureImmortel : Renaitre.
     * Implémente la logique de renaissance du phénix.
     * 
     */
    @Override
    public void Renaitre() {
        // TODO: Implémentez la logique de renaissance du phénix
    }
    
    
    /**
     * Méthode pour pondre un œuf.
     * 
     * @param dateNaissance     La date de naissance de l'œuf.
     * @return Une instance de la classe Oeuf pondue par l'ovipare.
     */
    public Oeuf PondreOeuf(Creature papa) throws Exception {
    	return super.PondreOeuf(papa, dureeIncubation);
    }
}
