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
     * Méthode pour générer un sexe aléatoire
     */
    public static Enum_Sexe SexeAleatoire() {
        int r = random.nextInt(2) + 1;
        if (r == 1)
            return Enum_Sexe.Male;
        else
            return Enum_Sexe.Femelle;
    }

    
    /**
     * Méthode pour passer à la nouvelle année dans le zoo
     */
    public String NouvelleAnnee() throws Exception {
        StringBuilder chaine = new StringBuilder();
        
        // Pour chaque enclos
        for (Enclos enclos : zoo.GetListeEnclos()) {
            // Pour chaque créature
            for (Creature creature : enclos.getListeCreatures()) {
                // Passage d'une année
                creature.Vieillir();
            }
            // Ajoute les informations sur les créatures mortes à la chaîne
            chaine.append(enclos.creaturesMortes());
        }
        return chaine.toString();
    }

    
    /**
     * Méthode pour créer les données initiales du jeu
     */
	public void creerDonneesJeu() throws Exception {
		Enum_Sexe sexe;
		double poids;
		double taille;
		int age;
		
		
		// Dragons
		Voliere enclosDragons = new Voliere("DragonLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS, constantes.TAILLE_ENCLOS);
		for (int i=0; i<5; i++) {
			sexe = SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Dragon d = FactoryCreature.newCreature(Enum_Especes.Dragon, sexe, poids, taille);
			enclosDragons.AjouterCreature(d);
			// age aleatoire
			age = 1 + (random.nextInt() * MaxAgeAleatoire);
			for (int y=0; y<age; y++)
				d.Vieillir();
		}
		zoo.AddEnclos(enclosDragons);

		// Kraken
		Aquarium enclosKraken = new Aquarium("KrakenLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS, constantes.TAILLE_ENCLOS);
		for (int i=0; i<5; i++) {
			sexe = SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Kraken k = FactoryCreature.newCreature(Enum_Especes.Kraken, sexe, poids, taille);
			enclosKraken.AjouterCreature(k);
			// age aleatoire
			age = 1 + (random.nextInt() * MaxAgeAleatoire);
			for (int y=0; y<age; y++)
				k.Vieillir();
		}
		zoo.AddEnclos(enclosKraken);
		
		// Licorne
		Enclos enclosLicorne = new Enclos("LicorneLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS);
		for (int i=0; i<5; i++) {
			sexe = SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Licorne l = FactoryCreature.newCreature(Enum_Especes.Licorne, sexe, poids, taille);
			enclosLicorne.AjouterCreature(l);
			// age aleatoire
			age = random.nextInt(MaxAgeAleatoire) + 1;
			for (int y=0; y<age; y++)
				l.Vieillir();
		}
		zoo.AddEnclos(enclosLicorne);
		
		// Lycanthrope
		Enclos enclosLycanthrope = new Enclos("LycanthropeLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS);
		for (int i=0; i<5; i++) {
			sexe = SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Lycanthrope l = FactoryCreature.newCreature(Enum_Especes.Lycanthrope, sexe, poids, taille);
			enclosLycanthrope.AjouterCreature(l);
			// age aleatoire
			age = random.nextInt(MaxAgeAleatoire) + 1;
			for (int y=0; y<age; y++)
				l.Vieillir();
		}
		zoo.AddEnclos(enclosLycanthrope);
		
		// Megalodon
		Aquarium enclosMegalodon = new Aquarium("MegalodonLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS, constantes.TAILLE_ENCLOS);
		for (int i=0; i<5; i++) {
			sexe = SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Megalodon m = FactoryCreature.newCreature(Enum_Especes.Megalodon, sexe, poids, taille);
			enclosMegalodon.AjouterCreature(m);
			// age aleatoire
			age = random.nextInt(MaxAgeAleatoire) + 1;
			for (int y=0; y<age; y++)
				m.Vieillir();
		}
		zoo.AddEnclos(enclosMegalodon);
		
		// Nymphe
		Enclos enclosNymphe = new Enclos("NympheLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS);
		for (int i=0; i<5; i++) {
			sexe = SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Nymphe n = FactoryCreature.newCreature(Enum_Especes.Nymphe, sexe, poids, taille);
			enclosNymphe.AjouterCreature(n);
			// age aleatoire
			age = random.nextInt(MaxAgeAleatoire) + 1;
			for (int y=0; y<age; y++)
				n.Vieillir();
		}
		zoo.AddEnclos(enclosNymphe);
		
		// Phenix
		Voliere enclosPhenix = new Voliere("PhenixLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS, constantes.TAILLE_ENCLOS);
		for (int i=0; i<5; i++) {
			sexe = SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Phenix p = FactoryCreature.newCreature(Enum_Especes.Phenix, sexe, poids, taille);
			enclosPhenix.AjouterCreature(p);
			// age aleatoire
			age = random.nextInt(MaxAgeAleatoire) + 1;
			for (int y=0; y<age; y++)
				p.Vieillir();
		}
		zoo.AddEnclos(enclosPhenix);
		
		//Sirene
		Aquarium enclosSirene = new Aquarium("SireneLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS, constantes.TAILLE_ENCLOS);
		for (int i=0; i<5; i++) {
			sexe = SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Sirene s = FactoryCreature.newCreature(Enum_Especes.Sirene, sexe, poids, taille);
			enclosSirene.AjouterCreature(s);
			// age aleatoire
			age = random.nextInt(MaxAgeAleatoire) + 1;
			for (int y=0; y<age; y++)
				s.Vieillir();
		}
		zoo.AddEnclos(enclosSirene);
	}
}
