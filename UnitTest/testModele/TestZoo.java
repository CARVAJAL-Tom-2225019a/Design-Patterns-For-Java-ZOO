package testModele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.Creature;
import base.Enclos;
import controllerTemps.Evenements;
import creaturesImplemente.FactoryCreature;
import enclosImplemente.Aquarium;
import maitreZoo.MaitreZoo;
import references.CONSTANTES;
import references.Enum_Especes;
import zoo.ZooFantastique;

class TestZoo {
	
	ZooFantastique zoo;
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
	
	private void CreationDonnes() throws Exception {
		// Kraken
		enclosKraken = new Aquarium("KrakenLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
		remplirEnclos(enclosKraken, Enum_Especes.Kraken);
		zoo.addEnclos(enclosKraken);
		
		// Megalodon
		enclosMegalodon = new Aquarium("MegalodonLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
		remplirEnclos(enclosMegalodon, Enum_Especes.Megalodon);
		zoo.addEnclos(enclosMegalodon);
		
		//Sirene
		enclosSirene = new Aquarium("SireneLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
		remplirEnclos(enclosSirene, Enum_Especes.Sirene);
		zoo.addEnclos(enclosSirene);
		
		//Enclos vide
		aquariumVide = new Aquarium("AquariumLand", CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
		zoo.addEnclos(aquariumVide);
	}

	@BeforeEach
	void construction() throws Exception {
		zoo = ZooFantastique.getInstance();
		CreationDonnes();
	}
	
	@Test
	void TestMethodeTrouverEnclosParNom() throws Exception {
		assertEquals(aquariumVide, zoo.trouverEnclosParNom("AquariumLand"));
	}
	
	@Test
	void TestMethodeModifAleatoireStatutCreature() throws Exception {
		boolean fait = false;
		Evenements.modifAleatoireStatutCreature();
		for (Enclos e : zoo.getListeEnclos()) {
			for (Creature c : e.getListeCreatures().values()) {
				if(c.getIndicateurFaim()<CONSTANTES.MAX_INDICATEUR
						|| c.getIndicateurSante()<CONSTANTES.MAX_INDICATEUR
						|| c.getIndicateurSommeil()<CONSTANTES.MAX_INDICATEUR) {
					fait=true;
					break;
				}
			}
		}
		assertTrue(fait);
	}
	
	@Test
	void TestMethodeModifAleatoireEtatEnclos() throws Exception {
		boolean fait = false;
		Evenements.modifAleatoireStatutCreature();
		Evenements.modifAleatoireEtatEnclos();
		for (Enclos e : zoo.getListeEnclos()) {
			if ( ((Aquarium)e).getNiveauEau()<((Aquarium)e).getProfondeurBassin() 
					|| ((Aquarium)e).getSaliniteEau()<CONSTANTES.SALINITE_CORRECT) {
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
