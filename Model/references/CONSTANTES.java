package references;

/**
 * Classe contenant l'ensemble des constantes du programme
 * 
 * Constitue les pramaètres de la simulation qui peuvent être changé facilement
 */
public class CONSTANTES {

	public static int TEMPS_APPLICATION_SLEEP = 6000;
	
	public static int ANNEE_DEPART=2024;
	public static int MOIS_DEPART=1;
	public static int JOUR_DEPART=1;
	
	public static int DUREE_VIE_ZOO = 15;
	
	public static int NUM_CHOIX_MAX = 21;
	
	public static int NB_MAX_ENCLOS = 30;
	public static int NB_CREATURE_PAR_ENCLOS = 6;
	public static int NB_CREATURE_PAR_ENCLOS_MAX = 15;

	
	public static int MAX_INDICATEUR = 100;
	public static int VALEUR_PERTE_INDICATEUR = 15;
	public static int VALEUR_INDICATEUR_MAUVAIS = 5;
	
	public static int MAX_AGE = 100;
	
	public static int SALINITE_CORRECT = 5;
	
	public static int TAILLE_ENCLOS = 1000;
	public static int TAILLE_MAX_CREATURE=50;
	public static final int MAX_POIDS = 1000;
	public static final int MAX_TAILLE = 1000;
	public static final int MAX_AGE_BEBE = 5 ; // toute espece confondu un bébé est maximum de 5 ans
	
	// Pour lycanthrope
	public static int MAX_FORCE = 100;
	public static int MAX_RANG_DOMINATION = 5;
	public static int MAX_FACTEUR_IMPETUOSITE=5;
	public static int SEUIL_FACTEUR_DOMINATION = -5;
	
	
	/**
	 * Constructeur
	 */
	public CONSTANTES() { }
}
