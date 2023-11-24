package controllerApplication;

import java.util.Random;

import base.*;
import creaturesImplemente.*;
import enclosImplemente.*;
import main.Run;
import references.*;
import viewApplication.*;
import zoo.ZooFantastique;

/**
 * Classe representant le controleur principal de l'application
 */
public class ControllerPrincipal {
	private int numPourNom = 0;
	VueGlobale vueGlobale = new VueGlobale();
	VueUtilisateur vueUtilisateur = new VueUtilisateur();
    // Instance du zoo fantastique (Singleton)
    private static ZooFantastique zoo = ZooFantastique.getInstance();
    // Âge maximum aléatoire pour la création d'une créature
    private static int MAX_AGE_ALEATOIRE = CONSTANTES.MAX_AGE / 2;
    
    
    /**
     * Méthode pour passer à la nouvelle année dans le zoo
     */
    public String nouvelleAnnee() {
    	String chaine = "";
    	try {
            // Pour chaque enclos
            for (Enclos enclos : zoo.getListeEnclos()) {
            	if (!enclos.getListeCreatures().isEmpty()) {
            		// Pour chaque créature
                    for (Creature creature : enclos.getListeCreatures().values()) {
                        // Passage d'une année
                        creature.vieillir();
                    }
                    // Ajoute les informations sur les créatures mortes à la chaîne
                    chaine+=enclos.creaturesMortes();
            	}
            }
            return chaine;
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    	return "erreur";
    }
    
    
    /**
     * Methodes permettant de verifier les enfants qui doivent naitre
     */
    public void verificationNaissances () {
    	try {
    		vueGlobale.Afficher("VERIFICATION DES NAISSANCES : ");
        	VerificationOeufs();
        	verificationEnfants();
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}	
    }
    public void VerificationOeufs() {
    	Random random = new Random(System.currentTimeMillis());
    	try {
    		for (Oeuf o : zoo.getlLsteOeufs()) {
        		if (o.getDureeIncubationRestante() == 0) {
        			double poids = 1 + (random.nextDouble() * CONSTANTES.MAX_TAILLE);
        			double taille = 1 + (random.nextDouble() * CONSTANTES.MAX_POIDS);
        			Creature enfant = o.eclore(Creature.sexeAleatoire(), poids, taille);
        			vueGlobale.Afficher("Naissance "+enfant.getNomEspece());
        			rangerCreature(enfant);
        			zoo.removeOeuf(o);
        		}
        	}
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    	
    }
    public void verificationEnfants() throws Exception {
    	int nbJour;
    	try {
    		for (Creature c : zoo.getListeFemelleEnceinte()) {
        		nbJour = ((Vivipare)c).decrementerNombreJourRestantAvantNaissance();
        		if (nbJour == 0) {
        			Creature enfant = ((Vivipare)c).mettreBas();
        			vueGlobale.Afficher("Naissance "+enfant.getNomEspece());
        			rangerCreature(enfant);
        			zoo.addFemelleEnceinte(c);
        		}
        	}
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    }
    
    
    
    /** 
     * Methode permettant de mettre la nouvelle creature dans enclos
     */
    public void rangerCreature (Creature c) throws Exception {
    	String nom;
    	try {
    		//S'il y a de la place dans un enclos
        	for (Enclos e : zoo.getListeEnclos()) {
        		if (e.getNbCreatures()<e.getNbMaxCreatures() && e.getNomEspece()==c.getNomEspece()) {
        			e.ajouterCreature(c);
        			e.reorganiserCles();
        			return;
        		}
        	}
        	// Si aucun enclos pret a acceuiller, nouvel enclos
        	if (Run.utilisateurControle)
        		nom = vueUtilisateur.DemandeUtilisateur("Nom pour nouvel enclos :");
        	else {
        		numPourNom++;
        		nom = "Enclos"+numPourNom;
        	}
        	Enclos newEnclos = new EnclosClassique (nom, CONSTANTES.TAILLE_ENCLOS);
        	zoo.addEnclos(newEnclos);
        	newEnclos.ajouterCreature(c);
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    	
    }
    

    /**
     * Methode pour remplir un enclos
     */
    public void remplirEnclos(Enclos enclos, Enum_Especes espece) {
    	Random random = new Random();
		int age;
		try {
			for (int i=0; i<CONSTANTES.NB_CREATURE_PAR_ENCLOS; i++) {
				Creature d = FactoryCreature.newCreature(espece);
				enclos.ajouterCreature(d);
				// age aleatoire
				age = 1 + random.nextInt(MAX_AGE_ALEATOIRE);
				for (int y=0; y<age; y++)
					d.vieillir();
			}
			enclos.reorganiserCles();
		}
		catch (Exception e) {
			vueGlobale.Afficher(e.getMessage());
		}
    }

    
    /**
     * Méthode pour créer les données initiales du jeu
     */
	public void creerDonneesJeu() {
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
			EnclosClassique enclosLycanthrope = new EnclosClassique("LycanthropeLand", CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosLycanthrope, Enum_Especes.Lycanthrope);
			zoo.addEnclos(enclosLycanthrope);
			
			// Megalodon
			Aquarium enclosMegalodon = new Aquarium("MegalodonLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosMegalodon, Enum_Especes.Megalodon);
			zoo.addEnclos(enclosMegalodon);
			
			// Phenix
			Voliere enclosPhenix = new Voliere("PhenixLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosPhenix, Enum_Especes.Phenix);
			zoo.addEnclos(enclosPhenix);
			
			//Sirene
			Aquarium enclosSirene = new Aquarium("SireneLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosSirene, Enum_Especes.Sirene);
			zoo.addEnclos(enclosSirene);
			
			//Enclos vide
			Enclos enclosClassiqueVide = new EnclosClassique("ClassiqueLand", CONSTANTES.TAILLE_ENCLOS);
			zoo.addEnclos(enclosClassiqueVide);
			
			//Enclos vide
			Aquarium aquariumVide = new Aquarium("AquariumLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			zoo.addEnclos(aquariumVide);
			
			//Enclos vide
			Voliere voliereVide = new Voliere("VoliereLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			zoo.addEnclos(voliereVide);
		}
		catch (Exception e) {
			vueGlobale.Afficher(e.getMessage());
		}
	}
	
	
	/**
     * Méthode pour passer la main à l'utilisateur
     */
    public void passerLaMainUtilisateur() {
    	try {
    		ControllerUserInterface menuUtilisateur = new ControllerUserInterface();
            menuUtilisateur.runUserMenu();
    	}
    	catch (Exception e) {
			vueGlobale.Afficher(e.getMessage());
		}
    }
    
    /**
     * Méthode pour lancer la gestion automatique
     */
    public void gestionAuto() {
    	try {
    		ControllerGestionAuto menuAuto = new ControllerGestionAuto();
        	menuAuto.run();
    	}
    	catch (Exception e) {
			vueGlobale.Afficher(e.getMessage());
		}
    }

}
