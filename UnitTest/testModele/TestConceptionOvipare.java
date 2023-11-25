package testModele;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.Creature;
import creaturesImplemente.FactoryCreature;
import creaturesImplemente.Megalodon;
import creaturesImplemente.Oeuf;
import references.Enum_Especes;
import references.Enum_Sexe;

class TestConceptionOvipare {

	Megalodon MegalodonFemelle;
	Megalodon MegalodonMale;
	
	
	@BeforeEach
	void construction() throws Exception {
		MegalodonFemelle = FactoryCreature.newCreature(Enum_Especes.Megalodon);
		MegalodonFemelle.setSexe(Enum_Sexe.Femelle);
		MegalodonMale = FactoryCreature.newCreature(Enum_Especes.Megalodon);
		MegalodonMale.setSexe(Enum_Sexe.Male);
	}
	
	@Test
	void TestMethodeCreerBebe() throws Exception {
		MegalodonFemelle.creerBebe(MegalodonMale);
		boolean result = MegalodonFemelle.getVentre().size() > 0;
		assertTrue(result);
	}
	
	@Test
	void TestMethodeCreerBebeAvecException() throws Exception {
		Exception thrown = assertThrows(
                Exception.class,
                () -> MegalodonMale.creerBebe(MegalodonMale),
                "Statut creature invalide");
        assertTrue(thrown.getMessage().contains("Statut creature invalide"));
	}
	
	@Test
	void TestMethodePondreOeuf() throws Exception {
		MegalodonFemelle.creerBebe(MegalodonMale);
		ArrayList<Oeuf> oeuf = MegalodonFemelle.pondreOeuf();
		assertNotNull(oeuf);
	}
	
	@Test
	void TestMethodeEclore() throws Exception {
		MegalodonFemelle.creerBebe(MegalodonMale);
		ArrayList<Oeuf> oeufs = MegalodonFemelle.pondreOeuf();
		ArrayList<Creature> creatures = new ArrayList<Creature>();
		for (Oeuf o : oeufs) {
			for (int i=0; i<o.getDureeIncubation(); i++) {
				o.decrementerDureeIncubationRestante();
			}
			if (o.getDureeIncubationRestante()==0) {
				Creature c = o.eclore(null, 0, 0);
				creatures.add(c);
			}
		}
		assertEquals(Enum_Especes.Megalodon, creatures.get(0).getNomEspece());
	}

}
