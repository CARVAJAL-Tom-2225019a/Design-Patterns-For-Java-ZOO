package creaturesImplemente;

import base.Vivipare;
import references.Enum_Especes;


/**
 * Cette classe représente un embryon issu de l'union de deux créatures vivipares
 * Elle contient des informations telles que l'espèce, la durée de gestation, et la
 * durée avant la naissance
 */
public class Embryon {
	
	// TODO : gestion genealogie
	
    private final Vivipare parent1;
    private final Vivipare parent2;

    private Enum_Especes espece;
    private int dureeGestation;
    private int dureeAvantNaissance;

    
    /**
     * Constructeur de la classe Embryon
     * 
     * @param parent1 Le premier parent vivipare
     * @param parent2 Le deuxième parent vivipare
     */
    public Embryon(Vivipare parent1, Vivipare parent2) {
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.espece = parent1.getNomEspece();
        this.dureeGestation = parent1.getDureeGestation();
        this.dureeAvantNaissance = parent1.getDureeGestation();

    }
    
    
    /**
     * Méthode pour décrémenter la durée d'incubation restante de l'embryon
     */
    public void decrementerDureeIncubationRestante() {
        dureeAvantNaissance--;
        if (dureeAvantNaissance<0)
            dureeAvantNaissance=0;
    }
}
