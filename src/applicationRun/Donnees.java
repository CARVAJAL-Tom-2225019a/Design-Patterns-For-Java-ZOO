package applicationRun;

import java.util.HashSet;
import java.util.Set;

import base.Creature;
import base.Enclos;
import creaturesImplemente.FactoryCreature;
import creaturesImplemente.Lycanthrope;
import enclosImplemente.Aquarium;
import enclosImplemente.EnclosClassique;
import enclosImplemente.EnclosLycanthrope;
import enclosImplemente.Voliere;
import meuteLycanthrope.ColonieLycanthrope;
import meuteLycanthrope.Meute;
import references.CONSTANTES;
import references.Enum_Especes;
import references.Enum_RangDomination;
import references.Enum_Sexe;
import viewApplication.VueGlobale;
import zoo.ZooFantastique;

/**
 * Classe permettant de creer les donnees de l'application
 */
public class Donnees {
	static VueGlobale vueGlobale = new VueGlobale();
	static private final ColonieLycanthrope colonie = ColonieLycanthrope.getInstance();
	static private final ZooFantastique zoo = ZooFantastique.getInstance();
	
	/**
	 * Constructeur
	 */
	public Donnees() { }
	
	/**
	 * Methode pour remplir un enclos
	 * @param enclos à remplir
	 * @param espece à mettre dans l'enclos
	 */
    public static void remplirEnclos(Enclos enclos, Enum_Especes espece) {
		try {
			for (int i=0; i<CONSTANTES.NB_CREATURE_PAR_ENCLOS; i++) {
				Creature d = FactoryCreature.newCreature(espece);
				enclos.ajouterCreature(d);
			}
			enclos.reorganiserCles();
		}
		catch (Exception e) {
			vueGlobale.afficher(e.getMessage());
		}
    }
    
    
    /**
     * Methode permettant d'ajouter des loups solitaire à un enclos de lycanthrope
     * @param enclos dans lequel il faut ajouter les loups
     * @throws Exception si erreur lors de la création de loups
     */
    private static void creationLoupsSolitaire(Enclos enclos) throws Exception {
    	for (int i=0; i<2; i++) {
    		Lycanthrope loupSolitaire = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
        	enclos.ajouterCreature(loupSolitaire);
    	}
    }
    
    /**
     * Methode pour creer les meutes
     * 
     * @param nomMeute le nom de la meute qui va être créée
     * @param enclos l'enclos où va se trouver la meute
     * @throws Exception si probleme lors de la creation
     */
    public static void creerMeute(String nomMeute, EnclosLycanthrope enclos) throws Exception {
    	// rangs possibles
    	Set<Enum_RangDomination> rangPossible = new HashSet<Enum_RangDomination>();
    	rangPossible.add(Enum_RangDomination.ALPHA);
    	rangPossible.add(Enum_RangDomination.BETA);
    	rangPossible.add(Enum_RangDomination.DELTA);
    	rangPossible.add(Enum_RangDomination.GAMMA);
    	rangPossible.add(Enum_RangDomination.OMEGA);
    	//recuperation d'une femelle
    	Lycanthrope femelle = (Lycanthrope) enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Femelle);
    	//recuperation d'un male
    	Lycanthrope male = (Lycanthrope) enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Male);
    	Meute m = new Meute (nomMeute ,femelle, male, CONSTANTES.NB_CREATURE_PAR_ENCLOS_MAX, rangPossible);
    	// ajout creatures dans meute
    	for (Creature l : enclos.getListeCreatures().values()) {
    		m.addLoup((Lycanthrope)l);
    	}
    	colonie.addMeute(m);
    	m.setEnclosReference(enclos);
    	// Ajout loups solitaire
    	creationLoupsSolitaire(enclos);
    }
    
    
	/**
     * Méthode pour créer les données initiales du jeu
     */
	public static void creerDonneesJeu() {
		try {
			// Dragons
			Voliere enclosDragons = new Voliere("DragonLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosDragons, Enum_Especes.Dragon);
			zoo.addEnclos(enclosDragons);

			// Kraken
			Aquarium enclosKraken = new Aquarium("KrakenLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosKraken, Enum_Especes.Kraken);
			zoo.addEnclos(enclosKraken);
			
			// Licorne
			EnclosClassique enclosLicorne = new EnclosClassique("LicorneLand", CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosLicorne, Enum_Especes.Licorne);
			zoo.addEnclos(enclosLicorne);
			
			// Lycanthrope
			EnclosLycanthrope enclosLycanthrope = new EnclosLycanthrope("LycanthropeLand", CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosLycanthrope, Enum_Especes.Lycanthrope);
			zoo.addEnclos(enclosLycanthrope);
			colonie.addEnclos(enclosLycanthrope);
			creerMeute("Les Fourrures", enclosLycanthrope);
			
			EnclosLycanthrope enclosLycanthrope2 = new EnclosLycanthrope("LycanthropeLand2", CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosLycanthrope2, Enum_Especes.Lycanthrope);
			zoo.addEnclos(enclosLycanthrope2);
			colonie.addEnclos(enclosLycanthrope2);
			
			// Megalodon
			Aquarium enclosMegalodon = new Aquarium("MegalodonLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosMegalodon, Enum_Especes.Megalodon);
			zoo.addEnclos(enclosMegalodon);
			creerMeute("Les Griffes", enclosLycanthrope2);
			
			// Phenix
			Voliere enclosPhenix = new Voliere("PhenixLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosPhenix, Enum_Especes.Phenix);
			zoo.addEnclos(enclosPhenix);
			
			//Sirene
			Aquarium enclosSirene = new Aquarium("SireneLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosSirene, Enum_Especes.Sirene);
			zoo.addEnclos(enclosSirene);
			
			//Nymphe
			EnclosClassique enclosNymphe = new EnclosClassique ("NympheLand", CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosNymphe, Enum_Especes.Nymphe);
			zoo.addEnclos(enclosNymphe);
			
			//Enclos vide
			EnclosClassique enclosClassiqueVide = new EnclosClassique("ClassiqueLand", CONSTANTES.TAILLE_ENCLOS);
			zoo.addEnclos(enclosClassiqueVide);
			
			//Enclos vide
			Aquarium aquariumVide = new Aquarium("AquariumLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			zoo.addEnclos(aquariumVide);
			
			//Enclos vide
			Voliere voliereVide = new Voliere("VoliereLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			zoo.addEnclos(voliereVide);
		}
		catch (Exception e) {
			vueGlobale.afficher(e.getMessage());
		}
	}
}
