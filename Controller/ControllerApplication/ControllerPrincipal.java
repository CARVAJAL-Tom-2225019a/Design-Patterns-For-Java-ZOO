package ControllerApplication;

import java.util.Random;

import base.Creature;
import creaturesImplemente.*;
import enclosImplemente.*;
import references.*;
import zoo.ZooFantastique;

public class ControllerPrincipal {
    // Instance du zoo fantastique (Singleton)
    private static ZooFantastique zoo = ZooFantastique.getInstance();
    // Instance de Random pour la génération aléatoire
    private static Random random = new Random(System.currentTimeMillis());
    // Instance de CONSTANTES pour les références constantes
    private static CONSTANTES constantes = new CONSTANTES();
    // Âge maximum aléatoire pour la création d'une créature
    private static int MaxAgeAleatoire = constantes.MAX_AGE / 2;
    
    /**
     * Méthode pour passer à la nouvelle année dans le zoo
     */
    public String NouvelleAnnee() throws Exception {
        StringBuilder chaine = new StringBuilder();
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
        return chaine.toString();
    }
    
    
    /**
     * Methode pour remplir un enclos
     * @throws Exception 
     */
    public void remplirEnclos(Enclos enclos, Enum_Especes espece) throws Exception {
    	Enum_Sexe sexe;
		double poids;
		double taille;
		int age;
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

    
    /**
     * Méthode pour créer les données initiales du jeu
     */
	public void creerDonneesJeu() throws Exception {
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
	
	
	/**
     * Méthode pour passer la main à l'utilisateur via le contrôleur MaitreZoo
     */
    public void PasserLaMainUtilisateur() throws Exception {
    	ControllerUserInterface menuUtilisateur = new ControllerUserInterface();
        menuUtilisateur.runUserMenu();
    }

}
