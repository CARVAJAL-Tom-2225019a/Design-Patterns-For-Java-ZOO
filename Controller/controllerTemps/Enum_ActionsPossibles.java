package controllerTemps;

public enum Enum_ActionsPossibles {
	PAS_D_ACTION(0, 1, 0),
    VOIR_ENCLOS_EXISTANTS(0, 0, 7),
    VOIR_NOMBRE_CREATURES_TOTALES(0, 0, 14),
    CREER_ENCLOS(0, 1, 14),
    EXAMINER_ENCLOS(0, 1, 0),
    NETTOYER_ENCLOS(0, 4, 0),
    NOURRIR_CREATURES(0, 1, 0),
    SOIGNER_ENCLOS(0,1,0),
    TRANSFERER_CREATURE(0, 0, 14),
    TRANSFERER_ENCLOS(0, 2, 7),
    CONCEVOIR_ENFANT(0, 0, 20),
    VOIR_BEBES_EN_CONSTRUCTION(0, 0, 14),
    METTRE_ENCLOS_EN_MOUVEMENT(0, 1, 0),
    FAIRE_CHANTER_ENCLOS(0, 1, 0),
    COMBAT(0, 1, 0),
	DORMIR_ENCLOS(0,0,7),
	REVEILLER_ENCLOS(0,0,7),
	VOIR_LOUPS(0,0,14),
	SAISON_AMOUR_LOUPS(0,1,0),
	VOIR_MEUTES(0,1,0),
	FAIRE_HURLER_LOUP(0,0,20);
    

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
