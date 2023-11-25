package controllerTemps;

public enum Enum_ActionsPossibles {
	PAS_D_ACTION(0, 0, 1),
    VOIR_ENCLOS_EXISTANTS(0, 0, 3),
    VOIR_NOMBRE_CREATURES_TOTALES(0, 0, 10),
    CREER_ENCLOS(0, 1, 5),
    EXAMINER_ENCLOS(0, 0, 20),
    NETTOYER_ENCLOS(0, 4, 0),
    NOURRIR_CREATURES(0, 1, 0),
    TRANSFERER_CREATURE(0, 0, 20),
    TRANSFERER_ENCLOS(0, 2, 10),
    CONCEVOIR_ENFANT(0, 0, 15),
    VOIR_BEBES_EN_CONSTRUCTION(0, 0, 5),
    METTRE_ENCLOS_EN_MOUVEMENT(0, 1, 0),
    FAIRE_CHANTER_ENCLOS(0, 1, 0),
	DORMIR_ENCLOS(0,0,2),
	REVEILLER_ENCLOS(0,0,2);

    private final int dureeEnAnnees;
    private final int dureeEnMois;
    private final int dureeEnJours;

    Enum_ActionsPossibles(int dureeEnAnnees, int dureeEnMois, int dureeEnJours) {
        this.dureeEnAnnees = dureeEnAnnees;
        this.dureeEnMois = dureeEnMois;
        this.dureeEnJours = dureeEnJours;
    }

    public int getDureeEnAnnees() {
        return dureeEnAnnees;
    }

    public int getDureeEnMois() {
        return dureeEnMois;
    }

    public int getDureeEnJours() {
        return dureeEnJours;
    }
    
    public String getDureeTotale() {
    	String chaine = "";
    	if (dureeEnAnnees>0)
    		chaine+="Y:"+dureeEnAnnees;
    	if (dureeEnMois>0)
    		chaine+= "MOIS:"+dureeEnMois;
    	if (dureeEnJours>0)
    		chaine+=" JOUR:"+dureeEnJours;
    	return chaine;
    }
}
