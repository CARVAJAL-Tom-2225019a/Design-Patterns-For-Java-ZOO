package ControllerApplication;

import java.time.Duration;
import java.util.Random;

import application.VueUtilisateur;
import base.Creature;
import base.Ovipare;
import base.Vivipare;
import creaturesImplemente.*;
import enclosImplemente.*;
import references.*;
import zoo.ZooFantastique;

public class ControllerPrincipal {
	VueUtilisateur vue = new VueUtilisateur();
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
     * Méthode pour créer les données initiales du jeu
     */
	public void creerDonneesJeu() throws Exception {
		Enum_Sexe sexe;
		double poids;
		double taille;
		int age;
		
		
		// Dragons
		Voliere enclosDragons = new Voliere("DragonLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX, constantes.TAILLE_ENCLOS);
		for (int i=0; i<constantes.NB_CREATURE_PAR_ENCLOS; i++) {
			sexe = Creature.SexeAleatoire();
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
		Aquarium enclosKraken = new Aquarium("KrakenLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX, constantes.TAILLE_ENCLOS);
		for (int i=0; i<constantes.NB_CREATURE_PAR_ENCLOS; i++) {
			sexe = Creature.SexeAleatoire();
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
		Enclos enclosLicorne = new Enclos("LicorneLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX);
		for (int i=0; i<constantes.NB_CREATURE_PAR_ENCLOS; i++) {
			sexe = Creature.SexeAleatoire();
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
		Enclos enclosLycanthrope = new Enclos("LycanthropeLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX);
		for (int i=0; i<constantes.NB_CREATURE_PAR_ENCLOS; i++) {
			sexe = Creature.SexeAleatoire();
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
		Aquarium enclosMegalodon = new Aquarium("MegalodonLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX, constantes.TAILLE_ENCLOS);
		for (int i=0; i<constantes.NB_CREATURE_PAR_ENCLOS; i++) {
			sexe = Creature.SexeAleatoire();
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
		Enclos enclosNymphe = new Enclos("NympheLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX);
		for (int i=0; i<constantes.NB_CREATURE_PAR_ENCLOS; i++) {
			sexe = Creature.SexeAleatoire();
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
		Voliere enclosPhenix = new Voliere("PhenixLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX, constantes.TAILLE_ENCLOS);
		for (int i=0; i<constantes.NB_CREATURE_PAR_ENCLOS; i++) {
			sexe = Creature.SexeAleatoire();
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
		Aquarium enclosSirene = new Aquarium("SireneLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX, constantes.TAILLE_ENCLOS);
		for (int i=0; i<constantes.NB_CREATURE_PAR_ENCLOS; i++) {
			sexe = Creature.SexeAleatoire();
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
		
		//Enclos vide
		Enclos enclosVide = new Enclos("OtherLand", constantes.TAILLE_ENCLOS, constantes.NB_CREATURE_PAR_ENCLOS_MAX);
		zoo.AddEnclos(enclosVide);
	}
	
	
	/**
     * Methode permettant de creer enfant aleatoirement dans le zoo
     * @throws Exception 
     */
    public void CreerEnfantAleatoirement() throws Exception {
    	Random random = new Random();
    	Oeuf oeuf = null;
        // Nombre aléatoire d'enfant a creer
        int nombreEnclosAModifier = random.nextInt(6) + 1;
        // Parcourir un nombre aléatoire d'enclos
        for (int i = 0; i < nombreEnclosAModifier; i++) {
            // Sélectionner un enclos au hasard
            int randomIndexEnclos = random.nextInt(zoo.GetListeEnclos().size());
            Enclos enclos = (Enclos) zoo.GetListeEnclos().toArray()[randomIndexEnclos];
            Creature femelle = enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Femelle);
            Creature male = enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Male);
            
            if (femelle!=null && male!=null && femelle instanceof Ovipare) {
            	oeuf = ((Ovipare)femelle).PondreOeuf(male, Duration.ofSeconds(1));
            	vue.Afficher("     NAISSANCE EN COURS - VEUILLEZ PATIENTER");
            	Thread.sleep(10000);
            	vue.Afficher("     TEMPS D'ATTENTE TERMINE - NAISSANCE ");
            	double poids = 1 + (random.nextDouble() * 49);
    			double taille = 1 + (random.nextDouble() * 49);
            	Creature c = oeuf.Eclore(Creature.SexeAleatoire(), poids, taille);
            	if (enclos.getNbCreatures() < enclos.getNbMaxCreatures())
            		enclos.AjouterCreature(c);
            	else {
            		Enclos e = new Enclos(vue.DemandeUtilisateur("Nom pour le nouvel enclos : "), 100, constantes.NB_CREATURE_PAR_ENCLOS_MAX);
            		e.AjouterCreature(c);
            		zoo.AddEnclos(e);
            	}
            }	
            else if (femelle!=null && male!=null && femelle instanceof Vivipare ){
            	((Vivipare)femelle).concevoirUnEnfant(male);
            	vue.Afficher("     NAISSANCE EN COURS - VEUILLEZ PATIENTER");
            	Thread.sleep(10000);
            	vue.Afficher("     TEMPS D'ATTENTE TERMINE - NAISSANCE ");
            	double poids = 1 + (random.nextDouble() * 49);
    			double taille = 1 + (random.nextDouble() * 49);
            	Creature c = ((Vivipare)femelle).MettreBas(Creature.SexeAleatoire(), poids, taille, Duration.ofSeconds(1));
            	if (enclos.getNbCreatures() < enclos.getNbMaxCreatures())
            		enclos.AjouterCreature(c);
            	else {
            		Enclos e = new Enclos(vue.DemandeUtilisateur("Nom pour le nouvel enclos : "), 100, constantes.NB_CREATURE_PAR_ENCLOS_MAX);
            		e.AjouterCreature(c);
            		zoo.AddEnclos(e);
            	}
            }
            	
            	
        }
    }
}
