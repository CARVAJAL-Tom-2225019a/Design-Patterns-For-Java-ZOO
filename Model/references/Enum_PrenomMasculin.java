package references;

/**
 * Enumeration des prénoms masculins que peuvent prendre les créatures
 */
public enum Enum_PrenomMasculin {

    Didier,
    George,
    JeanEude,
    Patrick,
    Gerard,
    Pepito,
    Jean,
    Pierre,
    Paul,
    Jacques,
    Michel,
    Andre,
    Alain,
    Bernard,
    Robert,
    Serge,
    Christian,
    Daniel,
    Claude,
    Henri,
    Gilles,
    Philippe,
    Yves,
    Pinpin,
    JeanCharle,
    JeanLuc,
    JeanValJean,
    JeanMichel,
    JeanPierre,
    JeanClaude,
    JeanFrancois,
    JeanMarc,
    JeanPaul,
    JeanJacques,
    JeanLouis,
    JeanSebastien,
    JeanYves,
    JeanGuy,
    JeanMarie,
    JeanChristophe,
    JeanPhilippe,
    JeanRene;


    /**
     * Méthode permettant de récupérer un prénom masculin de manière aléatoire
     *
     * @return le prénom sélectionné
     */
    public static Enum_PrenomMasculin getRandomName() {
        return Enum_PrenomMasculin.values()[(int) (Math.random() * Enum_PrenomMasculin.values().length)];
    }
}
