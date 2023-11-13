package Test1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ControllerApplication.ControllerPrincipal;
import application.*;
import base.Creature;
import creaturesImplemente.*;
import enclosImplemente.Aquarium;
import enclosImplemente.Enclos;
import enclosImplemente.Voliere;
import maitreZoo.MaitreZoo;
import references.*;
import zoo.ZooFantastique;

class TestEnclos {
	VueUtilisateur begin = new VueUtilisateur();
	ControllerPrincipal control = new ControllerPrincipal();
	Random random = new Random();
	CONSTANTES constantes = new CONSTANTES();
	ZooFantastique zoo = ZooFantastique.getInstance();
	MaitreZoo maitre = MaitreZoo.getInstance("Pepito", Enum_Sexe.Male, 20);
	FactoryCreature factory = new FactoryCreature();
	Aquarium enclosKraken = new Aquarium("KrakenLand", 20, 5, 100);
	Enclos enclosVide = new Enclos("EnclosVide", 20, 5);
	
	Enum_Sexe sexe;
	double poids;
	double taille;
	int age;
	
	@BeforeEach
    void construction() throws Exception {
		// Dragons
		Voliere enclosDragons = new Voliere("DragonLand", 20, 5, 100);
		for (int i=0; i<5; i++) {
			sexe = Creature.SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Dragon d = FactoryCreature.newCreature(Enum_Especes.Dragon, sexe, poids, taille);
			enclosDragons.AjouterCreature(d);
			// age aleatoire
			age = 1 + (random.nextInt() * constantes.MAX_AGE-1);
			for (int y=0; y<age; y++)
				d.Vieillir();
		}
		zoo.AddEnclos(enclosDragons);

		// Kraken
		for (int i=0; i<5; i++) {
			sexe = Creature.SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Kraken k = FactoryCreature.newCreature(Enum_Especes.Kraken, sexe, poids, taille);
			enclosKraken.AjouterCreature(k);
			// age aleatoire
			age = 1 + (random.nextInt() * constantes.MAX_AGE-1);
			for (int y=0; y<age; y++)
				k.Vieillir();
			
		}
		zoo.AddEnclos(enclosKraken);
		
		zoo.AddEnclos(enclosVide);
    }

	@Test
	void testTrouverCreature() {
		Enclos enclos = zoo.trouverEnclosParNom("KrakenLand");
		assertNotNull(enclos);
	}
	
	@Test
	void testChoixCreatureAleatoire() throws Exception {
		// Dragons
		Enclos enclos = new Enclos("Enclos", 20, 5);
		for (int i=0; i<5; i++) {
			sexe = Creature.SexeAleatoire();
			poids = 1 + (random.nextDouble() * 49);
			taille = 1 + (random.nextDouble() * 49);
			Phenix d = FactoryCreature.newCreature(Enum_Especes.Phenix, sexe, poids, taille);
			enclos.AjouterCreature(d);
			// age aleatoire
			age = 1 + (random.nextInt() * constantes.MAX_AGE-1);
			for (int y=0; y<age; y++)
				d.Vieillir();
		}
		Creature femelle = enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Femelle);
		assertNotNull(femelle);
	}
	
	@Test
	void testEnfantsAleatoires() throws Exception {
		int nbCreatureDebut = zoo.getNbCreaturesTotales();
		for (int i=0; i<3; i++)
			control.CreerEnfantAleatoirement();
		int nbCreatureFin = zoo.getNbCreaturesTotales();
		assertTrue (nbCreatureFin > nbCreatureDebut);
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
	    	enclosSource.reorganiserCles();
	    	enclosDest.reorganiserCles();
		}
    	
    	assertEquals(2, enclosDest.getNbCreatures());
	}

}
