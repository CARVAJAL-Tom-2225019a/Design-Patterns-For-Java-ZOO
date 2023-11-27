package controllerApplication;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import applicationRun.Run;
import base.*;
import creaturesImplemente.*;
import enclosImplemente.*;
import meuteLycanthrope.ColonieLycanthrope;
import meuteLycanthrope.Meute;
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
    private ColonieLycanthrope colonie = ColonieLycanthrope.getInstance();
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
                    //Passage annee pour lycanthrope
                    if (enclos instanceof EnclosLycanthrope) 
                    	((EnclosLycanthrope) enclos).passageAnneLycanthrope();
                    // Ajoute les informations sur les créatures mortes à la chaîne
                    chaine+=enclos.creaturesMortes();
            	}
            }
            return chaine;
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    	return "erreur";
    }
    
    
    /**
     * Methodes permettant de verifier les enfants qui doivent naitre
     */
    public void verificationNaissances () {
    	try {
    		vueGlobale.afficher("VERIFICATION DES NAISSANCES : ");
        	VerificationOeufs();
        	verificationEnfants();
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}	
    }
    public void VerificationOeufs() {
    	try {
    		for (Oeuf o : zoo.getlLsteOeufs()) {
        		if (o.getDureeIncubationRestante() == 0) {
        			Creature enfant = o.eclore();
        			vueGlobale.afficher("Naissance "+enfant.getNomEspece());
        			rangerCreature(enfant);
        			zoo.removeOeuf(o);
        		}
        	}
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    	
    }
    public void verificationEnfants() throws Exception {
    	int nbJour;
    	try {
    		for (Creature c : zoo.getListeFemelleEnceinte()) {
        		nbJour = ((Vivipare)c).decrementerNombreJourRestantAvantNaissance();
        		if (nbJour == 0) {
        			Creature enfant = ((Vivipare)c).mettreBas();
        			vueGlobale.afficher("Naissance "+enfant.getNomEspece());
        			rangerCreature(enfant);
        			zoo.addFemelleEnceinte(c);
        		}
        	}
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
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
        		nom = vueUtilisateur.demandeUtilisateur("Nom pour nouvel enclos :");
        	else {
        		numPourNom++;
        		nom = "Enclos"+numPourNom;
        	}
        	Enclos newEnclos = new EnclosClassique (nom, CONSTANTES.TAILLE_ENCLOS);
        	zoo.addEnclos(newEnclos);
        	newEnclos.ajouterCreature(c);
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
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
			vueGlobale.afficher(e.getMessage());
		}
    }
    
    /**
     * Methode pour creer les meutes
     * @throws Exception 
     */
    public void creerMeute(EnclosLycanthrope enclos) throws Exception {
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
    	Meute m = new Meute (femelle, male, CONSTANTES.NB_CREATURE_PAR_ENCLOS_MAX, rangPossible);
    	// ajout creatures dans meute
    	for (Creature l : enclos.getListeCreatures().values()) {
    		m.addLoup((Lycanthrope)l);
    	}
    	colonie.addMeute(m);
    	m.setEnclosReference(enclos);
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
			EnclosLycanthrope enclosLycanthrope = new EnclosLycanthrope("LycanthropeLand", CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosLycanthrope, Enum_Especes.Lycanthrope);
			zoo.addEnclos(enclosLycanthrope);
			colonie.addEnclos(enclosLycanthrope);
			creerMeute(enclosLycanthrope);
			
			EnclosLycanthrope enclosLycanthrope2 = new EnclosLycanthrope("LycanthropeLandBis", CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosLycanthrope2, Enum_Especes.Lycanthrope);
			zoo.addEnclos(enclosLycanthrope2);
			colonie.addEnclos(enclosLycanthrope2);
			
			// Megalodon
			Aquarium enclosMegalodon = new Aquarium("MegalodonLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
			remplirEnclos(enclosMegalodon, Enum_Especes.Megalodon);
			zoo.addEnclos(enclosMegalodon);
			creerMeute(enclosLycanthrope2);
			
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
			vueGlobale.afficher(e.getMessage());
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
			vueGlobale.afficher(e.getMessage());
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
			vueGlobale.afficher(e.getMessage());
		}
    }

}
