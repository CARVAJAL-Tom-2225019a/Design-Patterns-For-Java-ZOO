package testModele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.Creature;
import creaturesImplemente.FactoryCreature;
import enclosImplemente.EnclosClassique;
import references.Enum_Especes;
import references.Enum_Sexe;
import references.Enum_DegrePropreteEnclos;

class TestEnclos {
	
	EnclosClassique enclos;
	EnclosClassique enclosVide;
	
	@BeforeEach
	void construction() throws Exception {
		enclos = new EnclosClassique("enclosClassique", 1000);
		for (int i=0; i<10; i++) {
			Creature d = FactoryCreature.newCreature(Enum_Especes.Licorne);
			enclos.ajouterCreature(d);
		}
		enclos.reorganiserCles();
		
		enclosVide = new EnclosClassique("enclosVide", 1000);
		enclosVide.degradationDegreProprete();
		enclosVide.degradationDegreProprete();
	}

	@Test
	void TestMethodeAjouterCreature() {
		assertTrue(enclos.getListeCreatures().size()==10);
	}
	
	@Test
	void TestMethodeAjouterCreatureAvecException() throws Exception {
		Creature d = FactoryCreature.newCreature(Enum_Especes.Sirene);
		Exception thrown = assertThrows(
                Exception.class,
                () -> enclos.ajouterCreature(d),
                "Un enclos classique ne peut contenir que des créatures terrestres");
        assertTrue(thrown.getMessage().contains("Un enclos classique ne peut contenir que des créatures terrestres"));
	}
	
	@Test
	void TestMethodeVoirCreaturesAyantUnBesoin() throws Exception {
		for (int i=0; i<10; i++)
			enclos.getListeCreatures().get(1).perdreNourriture();
		assertTrue(enclos.getCreaturesAyantUnBesoin() == null);
	}
	
	@Test
	void TestMethodeSupprimerCreature() throws Exception {
		int sizeListe = enclos.getListeCreatures().size();
		Creature d = enclos.getListeCreatures().get(2);
		enclos.supprimerCreature(d);
		assertEquals(sizeListe - 1, enclos.getListeCreatures().size());
	}
	
	@Test
	void TestMethodeSupprimerCreatureAvecException() throws Exception {
		Creature d = FactoryCreature.newCreature(Enum_Especes.Dragon);
		Exception thrown = assertThrows(
                Exception.class,
                () -> enclos.supprimerCreature(d),
                "Creature introuvable");
        assertTrue(thrown.getMessage().contains("Creature introuvable"));
	}
	
	@Test
	void TestMethodeDegradationDegreProprete() {
		assertEquals (Enum_DegrePropreteEnclos.mauvais, enclosVide.getDegreProprete() );
	}
	
	@Test
	void TestMethodeEntretenirEnclos() throws Exception {
		enclosVide.entretenirEnclos();
		assertEquals (Enum_DegrePropreteEnclos.bon, enclosVide.getDegreProprete() );
	}
	
	@Test
	void TestMethodeEntretenirEnclosAvecException() {
		Exception thrown = assertThrows(
                Exception.class,
                () -> enclos.entretenirEnclos(),
                "Enclos "+enclos.getNom()+" n'a pas besoin d etre nettoye");
        assertTrue(thrown.getMessage().contains("Enclos "+enclos.getNom()+" n'a pas besoin d etre nettoye"));
	}
	
	@Test
	void TestMethodeTrouverCleParCreature() throws Exception {
		int key = 3;
		Creature d = enclos.getListeCreatures().get(key);
		assertEquals(3, enclos.trouverCleParCreature(d));
	}
	
	@Test
	void TestMethodeSelectionnerCreatureAleatoireParSexe () throws Exception {
		Creature creatureFemelle = enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Femelle);
		assertEquals (Enum_Sexe.Femelle, creatureFemelle.getSexe());
	}

}
