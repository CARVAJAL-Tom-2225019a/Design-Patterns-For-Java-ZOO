package creaturesImplemente;

import base.Creature;
import base.Vivipare;
import references.Enum_Especes;

public class Embryon {
    private final Vivipare parent1;
    private final Vivipare parent2;

    private Enum_Especes espece;
    private int dureeGestation;
    private int dureeAvantNaissance;

    public Embryon(Vivipare parent1, Vivipare parent2) {
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.espece = parent1.getNomEspece();
        this.dureeGestation = parent1.getDureeGestation();
        this.dureeAvantNaissance = parent1.getDureeGestation();

    }
    public void DecrementerDureeIncubationRestante() {
        dureeAvantNaissance--;
        if (dureeAvantNaissance<0)
            dureeAvantNaissance=0;
    }
}
