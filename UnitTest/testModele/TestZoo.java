package testModele;

import base.Creature;
import base.Enclos;
import controllerTemps.Evenements;
import creaturesImplemente.FactoryCreature;
import enclosImplemente.Aquarium;
import enclosImplemente.Voliere;
import maitreZoo.MaitreZoo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import references.CONSTANTES;
import references.Enum_DegrePropreteEnclos;
import references.Enum_Especes;
import zoo.ZooFantastique;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestZoo {
	
	ZooFantastique zoo = ZooFantastique.getInstance();;
	Aquarium aquariumVide;
	Aquarium enclosKraken;
	Aquarium enclosMegalodon;
	Aquarium enclosSirene;
	
	private void remplirEnclos(Enclos enclos, Enum_Especes espece) throws Exception {
		for (int i=0; i<10; i++) {
			Creature d = FactoryCreature.newCreature(espece);
			enclos.ajouterCreature(d);
		}
		enclos.reorganiserCles();
    }
	
	private void creer() throws Exception {
		// Kraken
		enclosKraken = new Aquarium("KrakenLand", 15000, 15000);
		remplirEnclos(enclosKraken, Enum_Especes.Kraken);
		zoo.addEnclos(enclosKraken);
		
		// Megalodon
		enclosMegalodon = new Aquarium("MegalodonLand", 15000, 15000);
		remplirEnclos(enclosMegalodon, Enum_Especes.Megalodon);
		zoo.addEnclos(enclosMegalodon);
		
		//Sirene
		enclosSirene = new Aquarium("SireneLand", 15000, 15000);
		remplirEnclos(enclosSirene, Enum_Especes.Sirene);
		zoo.addEnclos(enclosSirene);
		
		//Enclos vide
		aquariumVide = new Aquarium("AquariumLand", 15000, 15000);
		zoo.addEnclos(aquariumVide);
	}

	@BeforeEach
	void construction() throws Exception {
		zoo.clear();
		creer();
	}
	
	@Test
	void TestMethodeTrouverEnclosParNom() throws Exception {
		assertEquals(aquariumVide, zoo.trouverEnclosParNom("AquariumLand"));
	}
	
	@Test
	void TestMethodeModifAleatoireEtatEnclos() throws Exception {
		boolean fait = false;
		Evenements.modifAleatoireEtatEnclos();
		for (Enclos e : zoo.getListeEnclos()) {
			if ( e instanceof Aquarium && (((Aquarium)e).getNiveauEau()<((Aquarium)e).getProfondeurBassin() 
					|| ((Aquarium)e).getSaliniteEau()<CONSTANTES.SALINITE_CORRECT)) {
				fait = true;
				break;
			}
			else if ( e instanceof Voliere && ((Voliere)e).getEtatToit()!=Enum_DegrePropreteEnclos.bon) {
				fait = true;
				break;
			}
			else if (e.getDegreProprete()!=Enum_DegrePropreteEnclos.bon) {
				fait = true;
				break;
			}
		}
		assertTrue(fait);
	}
	
	@Test
	void TestMethodeTransfererCreature() throws Exception {
		Creature d = FactoryCreature.newCreature(Enum_Especes.Kraken);
		zoo.trouverEnclosParNom("KrakenLand").ajouterCreature(d);
		MaitreZoo maitre = MaitreZoo.getInstance();
		maitre.transfererCreature(d, zoo.trouverEnclosParNom("KrakenLand"), aquariumVide);
		assertEquals(1, aquariumVide.getNbCreatures());
	}

}
