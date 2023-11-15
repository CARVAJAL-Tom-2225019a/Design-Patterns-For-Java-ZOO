package controllerApplication;

import java.util.Random;

import base.*;
import creaturesImplemente.*;
import enclosImplemente.*;
import main.Run;
import references.*;
import viewApplication.*;
import zoo.ZooFantastique;

public class ControllerPrincipal {
	private int numPourNom = 0;
	VueGlobale VueGlobale = new VueGlobale();
	VueUtilisateur vueUtilisateur = new VueUtilisateur();
    // Instance du zoo fantastique (Singleton)
    private static ZooFantastique zoo = ZooFantastique.getInstance();
    // Instance de CONSTANTES pour les références constantes
    private static CONSTANTES constantes = new CONSTANTES();
    // Âge maximum aléatoire pour la création d'une créature
    private static int MaxAgeAleatoire = constantes.MAX_AGE / 2;
    
    /**
     * Méthode pour passer à la nouvelle année dans le zoo
     */
    public String NouvelleAnnee() {
    	StringBuilder chaine = new StringBuilder();
    	try {
            // Pour chaque enclos
            for (Enclos enclos : zoo.GetListeEnclos()) {
                // Pour chaque créature
                for (Creature creature : enclos.getListeCreatures().values()) {
                    // Passage d'une année
                    creature.Vieillir();
                }
                // Ajoute les informations sur les créatures mortes à la chaîne
                chaine.append(enclos.creaturesMortes());
                // Mise a jour clés des creatures
                enclos.reorganiserCles();
            }
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    	return chaine.toString();
    }
    
    /**
     * Methodes permettant de verifier les enfants qui doivent naitre
     */
    public void VerificationNaissances () {
    	try {
    		VueGlobale.Afficher("VERIFICATION DES NAISSANCES : ");
        	VerificationOeufs();
        	VerificationEnfants();
        	VueGlobale.Afficher("  ================== ");
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}	
    }
    public void VerificationOeufs() {
    	Random random = new Random(System.currentTimeMillis());
    	try {
    		for (Oeuf o : zoo.GetlLsteOeufs()) {
        		if (o.getDureeIncubationRestante() == 0) {
        			double poids = 1 + (random.nextDouble() * constantes.TAILLE_MAX_CREATURE);
        			double taille = 1 + (random.nextDouble() * constantes.TAILLE_MAX_CREATURE);
        			Creature enfant = o.Eclore(Creature.SexeAleatoire(), poids, taille);
        			VueGlobale.Afficher("Naissance "+enfant.getNomEspece());
        			rangerCreature(enfant);
        			zoo.RemoveOeuf(o);
        		}
        	}
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    	
    }
    public void VerificationEnfants() throws Exception {
    	Random random = new Random(System.currentTimeMillis());
    	int nbJour;
    	try {
    		for (Creature c : zoo.GetListeFemelleEnceinte()) {
        		nbJour = ((Vivipare)c).DecrementerNombreJourRestantAvantNaissance();
        		if (nbJour == 0) {
        			double poids = 1 + (random.nextDouble() * constantes.TAILLE_MAX_CREATURE);
        			double taille = 1 + (random.nextDouble() * constantes.TAILLE_MAX_CREATURE);
        			Creature enfant = ((Vivipare)c).MettreBas(Creature.SexeAleatoire(), poids, taille);
        			VueGlobale.Afficher("Naissance "+enfant.getNomEspece());
        			rangerCreature(enfant);
        			zoo.AddFemelleEnceinte(c);
        		}
        	}
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
    
    
    /** 
     * Methode permettant de mettre la nouvelle creature dans enclos
     */
    public void rangerCreature (Creature c) throws Exception {
    	String nom;
    	try {
    		//S'il y a de la place dans un enclos
        	for (Enclos e : zoo.GetListeEnclos()) {
        		if (e.getNbCreatures()<e.getNbMaxCreatures() && e.getNomEspece()==c.getNomEspece()) {
        			e.AjouterCreature(c);
        			return;
        		}
        	}
        	// Si aucun enclos pret a acceuiller, nouvel enclos
        	if (Run.UtilisateurControle)
        		nom = vueUtilisateur.DemandeUtilisateur("Nom pour nouvel enclos :");
        	else {
        		numPourNom++;
        		nom = "Enclos"+numPourNom;
        	}
        	Enclos newEnclos = new Enclos (nom, constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX);
        	zoo.AddEnclos(newEnclos);
        	newEnclos.AjouterCreature(c);
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    	
    }
    

    /**
     * Methode pour remplir un enclos
     */
    public void remplirEnclos(Enclos enclos, Enum_Especes espece) {
    	Random random = new Random(System.currentTimeMillis());
    	Enum_Sexe sexe;
		double poids;
		double taille;
		int age;
		try {
			for (int i=0; i<constantes.NB_CREATURE_PAR_ENCLOS; i++) {
				sexe = Creature.SexeAleatoire();
				poids = 1 + (random.nextDouble() * constantes.TAILLE_MAX_CREATURE);
				taille = 1 + (random.nextDouble() * constantes.TAILLE_MAX_CREATURE);
				Creature d = FactoryCreature.newCreature(espece, sexe, poids, taille);
				enclos.AjouterCreature(d);
				// age aleatoire
				age = 1 + (random.nextInt() * MaxAgeAleatoire);
				for (int y=0; y<age; y++)
					d.Vieillir();
			}
		}
		catch (Exception e) {
			VueGlobale.Afficher(e.getMessage());
		}
    }

    
    /**
     * Méthode pour créer les données initiales du jeu
     */
	public void creerDonneesJeu() {
		try {
			// Dragons
			Voliere enclosDragons = new Voliere("DragonLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX, constantes.TAILLE_ENCLOS);
			remplirEnclos(enclosDragons, Enum_Especes.Dragon);
			zoo.AddEnclos(enclosDragons);

			// Kraken
			Aquarium enclosKraken = new Aquarium("KrakenLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX, constantes.TAILLE_ENCLOS);
			remplirEnclos(enclosKraken, Enum_Especes.Kraken);
			zoo.AddEnclos(enclosKraken);
			
			// Licorne
			Enclos enclosLicorne = new Enclos("LicorneLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX);
			remplirEnclos(enclosLicorne, Enum_Especes.Licorne);
			zoo.AddEnclos(enclosLicorne);
			
			// Lycanthrope
			Enclos enclosLycanthrope = new Enclos("LycanthropeLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX);
			remplirEnclos(enclosLycanthrope, Enum_Especes.Lycanthrope);
			zoo.AddEnclos(enclosLycanthrope);
			
			// Megalodon
			Aquarium enclosMegalodon = new Aquarium("MegalodonLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX, constantes.TAILLE_ENCLOS);
			remplirEnclos(enclosMegalodon, Enum_Especes.Megalodon);
			zoo.AddEnclos(enclosMegalodon);
			
			// Nymphe
			Enclos enclosNymphe = new Enclos("NympheLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX);
			remplirEnclos(enclosNymphe, Enum_Especes.Nymphe);
			zoo.AddEnclos(enclosNymphe);
			
			// Phenix
			Voliere enclosPhenix = new Voliere("PhenixLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX, constantes.TAILLE_ENCLOS);
			remplirEnclos(enclosPhenix, Enum_Especes.Phenix);
			zoo.AddEnclos(enclosPhenix);
			
			//Sirene
			Aquarium enclosSirene = new Aquarium("SireneLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX, constantes.TAILLE_ENCLOS);
			remplirEnclos(enclosSirene, Enum_Especes.Sirene);
			zoo.AddEnclos(enclosSirene);
			
			//Enclos vide
			Enclos enclosVide = new Enclos("OtherLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX);
			zoo.AddEnclos(enclosVide);
		}
		catch (Exception e) {
			VueGlobale.Afficher(e.getMessage());
		}
	}
	
	
	/**
     * Méthode pour passer la main à l'utilisateur
     */
    public void PasserLaMainUtilisateur() {
    	try {
    		ControllerUserInterface menuUtilisateur = new ControllerUserInterface();
            menuUtilisateur.runUserMenu();
    	}
    	catch (Exception e) {
			VueGlobale.Afficher(e.getMessage());
		}
    }
    
    /**
     * Méthode pour lancer la gestion automatique
     */
    public void GestionAuto() {
    	try {
    		ControllerGestionAuto menuAuto = new ControllerGestionAuto();
        	menuAuto.run();
    	}
    	catch (Exception e) {
			VueGlobale.Afficher(e.getMessage());
		}
    }

}
