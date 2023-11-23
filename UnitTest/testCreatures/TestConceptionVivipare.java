package testCreatures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import creaturesImplemente.Sirene;
import creaturesImplemente.FactoryCreature;
import references.Enum_Especes;
import references.Enum_Sexe;

class TestConceptionVivipare {

	Sirene sireneFemelle;
	Sirene sireneMale;
	
	
	@BeforeEach
	void construction() throws Exception {
		sireneFemelle = FactoryCreature.newCreature(Enum_Especes.Sirene);
		sireneFemelle.setSexe(Enum_Sexe.Femelle);
		sireneMale = FactoryCreature.newCreature(Enum_Especes.Sirene);
		sireneMale.setSexe(Enum_Sexe.Male);
	}

	@Test
	void TestMethodeConcevoirUnEnfant() throws Exception {
		sireneFemelle.concevoirUnEnfant(sireneMale, 2);
		assertEquals(2, sireneFemelle.getNbJourConceptionRestantAvantMiseABas());
	}
	
	@Test
	void TestMethodeVerificationEnfantEnConception() throws Exception {
		sireneFemelle.concevoirUnEnfant(sireneMale, 2);
		assertEquals(null, sireneFemelle.VerificationEnfantEnConception());
		
	}
	
	@Test
	void TestMethodeMettreBas() throws Exception {
		sireneFemelle.concevoirUnEnfant(sireneMale, 1);
		sireneFemelle.DecrementerNombreJourRestantAvantNaissance();
		assertNotNull(sireneFemelle.VerificationEnfantEnConception());
	}
}
