package references;

public enum Enum_RangDomination {
	// Liste des rangs de domination avec leur valeur associe
	ALPHA("Alpha",23),
    BETA("Beta",22),
    GAMMA("Gamma",21),
    DELTA("Delta",20),
    EPSILON("Epsilon",19),
    ZETA("Zeta",18),
    ETA("Eta",17),
    THETA("Theta",16),
    IOTA("Iota",15),
    KAPPA("Kappa",14),
    LAMBDA("Lambda",13),
    MU("Mu",12),
    NU("Nu",11),
    XI("Xi",10),
    OMICRON("Omicron",9),
    PI("Pi",8),
    RHO("Rho",7),
    SIGMA("Sigma",6),
    TAU("Tau",5),
    UPSILON("Upsilon",4),
    PHI("Phi",3),
    CHI("Chi",2),
    PSI("Psi",1),
    OMEGA("Omega",0);
	
	private String description;
    private int valeur;

    // Constructeur de l'énumération avec des paramètres
    Enum_RangDomination(String description, int valeur) {
        this.description = description;
        this.valeur = valeur;
    }

    // Méthode pour obtenir la description associée à un rang
    public String getDescription() {
        return description;
    }

    // Méthode pour obtenir la valeur associée à un rang
    public int getValeur() {
        return valeur;
    }
    
    // Methode pour avoirLeRangInferieur
    public Enum_RangDomination getRangInferieur() {
    	// Obtenir la liste des valeurs de l'énumération
        Enum_RangDomination[] rangs = Enum_RangDomination.values();
        // Trouvez l'index du rang courant
        int index = this.ordinal();
        // Vérifiez si l'index est valide et renvoyez le rang inférieur
        if (index > 0 && index < rangs.length) {
            return rangs[index - 1];
        } else {
            // Si le rang courant est déjà le plus bas, vous pouvez décider de renvoyer null ou une valeur spéciale.
            // Dans cet exemple, je choisis de renvoyer null.
            return this;
        }
    }
}
