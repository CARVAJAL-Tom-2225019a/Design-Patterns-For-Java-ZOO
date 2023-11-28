package references;

public enum Enum_RangDomination {
	// Liste des rangs de domination avec leur valeur associe
	ALPHA("Alpha",23,'α'),
    BETA("Beta",22,'β'),
    GAMMA("Gamma",21,'γ'),
    DELTA("Delta",20,'δ'),
    EPSILON("Epsilon",19,'ε'),
    ZETA("Zeta",18,'ζ'),
    ETA("Eta",17,'η'),
    THETA("Theta",16,'θ'),
    IOTA("Iota",15,'ι'),
    KAPPA("Kappa",14,'κ'),
    LAMBDA("Lambda",13,'λ'),
    MU("Mu",12,'μ'),
    NU("Nu",11,'ν'),
    XI("Xi",10,'ξ'),
    OMICRON("Omicron",9,'ο'),
    PI("Pi",8,'π'),
    RHO("Rho",7,'ρ'),
    SIGMA("Sigma",6,'σ'),
    TAU("Tau",5,'τ'),
    UPSILON("Upsilon",4,'υ'),
    PHI("Phi",3,'φ'),
    CHI("Chi",2,'χ'),
    PSI("Psi",1,'ψ'),
    OMEGA("Omega",0,'ω');
	
	private String description;
    private int valeur;
    private char charRang;

    // Constructeur de l'énumération avec des paramètres
    Enum_RangDomination(String description, int valeur, char charRang) {
        this.description = description;
        this.valeur = valeur;
        this.charRang = charRang;
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
            // Si le rang courant est déjà le plus bas.
            return OMEGA;
        }
    }

    public char getChar() {
        return charRang;
    }
}
