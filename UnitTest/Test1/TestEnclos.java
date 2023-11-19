package Test1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.Creature;
import base.Ovipare;
import controllerApplication.ControllerPrincipal;
import creaturesImplemente.*;
import enclosImplemente.Aquarium;
import enclosImplemente.Enclos;
import enclosImplemente.Voliere;
import maitreZoo.MaitreZoo;
import references.*;
import viewApplication.*;
import zoo.ZooFantastique;

class TestEnclos {
	VueUtilisateur begin = new VueUtilisateur();
	ControllerPrincipal control = new ControllerPrincipal();
	Random random = new Random();
	ZooFantastique zoo = ZooFantastique.getInstance();
	MaitreZoo maitre = MaitreZoo.getInstance("Pepito", Enum_Sexe.Male, 20);
	FactoryCreature factory = new FactoryCreature();
	Voliere enclosDragons = new Voliere("DragonLand", 20, 100);
	Aquarium enclosKraken = new Aquarium("KrakenLand", 20, 100);
	Enclos enclosSirene = new Enclos("SireneLand", 20);
	Enclos enclosNymphe = new Enclos("NympheLand", 20);
	Enclos enclosVide = new Enclos("EnclosVide", 20);
	
	Enum_Sexe sexe;
	double poids;
	double taille;
	int age;
	
	@BeforeEach
    void construction() throws Exception {
		// Dragons
		for (int i=0; i<10; i++) {
			sexe = Creature.SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Dragon d = FactoryCreature.newCreature(Enum_Especes.Dragon, sexe, poids, taille);
			enclosDragons.AjouterCreature(d);
			// age aleatoire
			age = 1 + (random.nextInt() * CONSTANTES.MAX_AGE-1);
			for (int y=0; y<age; y++)
				d.Vieillir();
		}
		zoo.AddEnclos(enclosDragons);

		// Kraken
		for (int i=0; i<10; i++) {
			sexe = Creature.SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Kraken k = FactoryCreature.newCreature(Enum_Especes.Kraken, sexe, poids, taille);
			enclosKraken.AjouterCreature(k);
			// age aleatoire
			age = 1 + (random.nextInt() * CONSTANTES.MAX_AGE-1);
			for (int y=0; y<age; y++)
				k.Vieillir();
			
		}
		zoo.AddEnclos(enclosKraken);
		
		// Sirene
		for (int i=0; i<10; i++) {
			sexe = Creature.SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Sirene s = FactoryCreature.newCreature(Enum_Especes.Sirene, sexe, poids, taille);
			enclosSirene.AjouterCreature(s);
			// age aleatoire
			age = 1 + (random.nextInt() * CONSTANTES.MAX_AGE-1);
			for (int y=0; y<age; y++)
				s.Vieillir();
			
		}
		zoo.AddEnclos(enclosSirene);
		
		// Nymphe
		for (int i=0; i<10; i++) {
			sexe = Creature.SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Nymphe s = FactoryCreature.newCreature(Enum_Especes.Nymphe, sexe, poids, taille);
			enclosNymphe.AjouterCreature(s);
			// age aleatoire
			age = 1 + (random.nextInt() * CONSTANTES.MAX_AGE-1);
			for (int y=0; y<age; y++)
				s.Vieillir();
			
		}
		zoo.AddEnclos(enclosNymphe);
		
		zoo.AddEnclos(enclosVide);
    }

	@Test
	void testTrouverCreature() throws Exception {
		Enclos enclos = zoo.trouverEnclosParNom("KrakenLand");
		assertNotNull(enclos);
	}
	
	@Test
	void testChoixCreatureAleatoire() throws Exception {
		Enclos enclos = new Enclos("Enclos", 20);
		for (int i=0; i<10; i++) {
			sexe = Creature.SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Phenix d = FactoryCreature.newCreature(Enum_Especes.Phenix, sexe, poids, taille);
			enclos.AjouterCreature(d);
			// age aleatoire
			age = 1 + (random.nextInt(CONSTANTES.MAX_AGE-1));
			for (int y=0; y<age; y++)
				d.Vieillir();
		}
		Creature femelle = enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Femelle);
		assertEquals(Enum_Sexe.Femelle, femelle.getSexe());
	}
	
	@Test
	void testPondreOeuf() throws Exception {
		Creature femelle = FactoryCreature.newCreature(Enum_Especes.Dragon, Enum_Sexe.Femelle, 10, 10);
		Creature male = FactoryCreature.newCreature(Enum_Especes.Dragon, Enum_Sexe.Male, 10, 10);
		Oeuf oeuf = ((Ovipare)femelle).PondreOeuf(male, femelle.getDureePourEnfant());
		assertNotNull(oeuf);
	}
	
	@Test
	void testTransfertCreature() throws Exception {
		String nomE = "KrakenLand";
		Enclos enclosSource = zoo.trouverEnclosParNom(nomE);
		nomE = "EnclosVide";
		Enclos enclosDest = zoo.trouverEnclosParNom(nomE);
		String indexCreatureString = "1";
    	int indexCreature = Integer.parseInt(indexCreatureString);
    	
		for (int i=0; i<2; i++ ) {
	    	Creature creature = enclosSource.getListeCreatures().get(indexCreature);
	    	maitre.TransfererCreature(creature, enclosSource, enclosDest);
		}
    	
    	assertEquals(2, enclosDest.getNbCreatures());
	}
	
	@Test
	void testTriParAge() throws Exception {
		Nymphe n = FactoryCreature.newCreature(Enum_Especes.Nymphe, sexe, poids, taille);
		enclosNymphe.AjouterCreature(n);
		enclosNymphe.reorganiserCles();
		assertEquals(1, enclosNymphe.getListeCreatures().get(1).getAge());
	}
	
	@Test
	void testTransfertEnclos() throws Exception {
		Enclos enclos1 = new Enclos("enclosSource", 20);
	    for (int i = 0; i < 10; i++) {
	        Dragon d = FactoryCreature.newCreature(Enum_Especes.Dragon, sexe, poids, taille);
	        enclos1.AjouterCreature(d);
	    }
	    Enclos enclos2 = new Enclos("enclosDest", 20);
	    // Créez une copie de la liste des créatures
	    Set<Creature> creaturesACopier = new HashSet<>(enclos1.getListeCreatures().values());
	    // Transférez les créatures de la copie vers enclos2
	    for (Creature c : creaturesACopier) {
	        maitre.TransfererCreature(c, enclos1, enclos2);
	    }
	    assertEquals(0, enclos1.getNbCreatures());
	}

}
