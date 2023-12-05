package testModele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import creaturesImplemente.Sirene;
import creaturesImplemente.FactoryCreature;
import references.Enum_Especes;
import references.Enum_Sexe;

/**
 * Classe de test pour la onception d'enfants chez les vivipare
 */
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
	void TestMethodeConcevoirUnEnfantAvecException() throws Exception {
		Exception thrown = assertThrows(
                Exception.class,
                () -> sireneMale.concevoirUnEnfant(sireneMale, 2),
                "La nature autorise seulement une femelle de concevoir un enfant avec un male de la meme espece");
        assertTrue(thrown.getMessage().contains("La nature autorise seulement une femelle de concevoir un enfant avec un male de la meme espece"));
	}
	
	@Test
	void TestMethodeVerificationEnfantEnConception() throws Exception {
		sireneFemelle.concevoirUnEnfant(sireneMale, 2);
		sireneFemelle.decrementerNombreJourRestantAvantNaissance();
		assertEquals(null, sireneFemelle.verificationEnfantEnConception());
		
	}
	
	@Test
	void TestMethodeMettreBas() throws Exception {
		sireneFemelle.concevoirUnEnfant(sireneMale, 1);
		sireneFemelle.decrementerNombreJourRestantAvantNaissance();
		assertNotNull(sireneFemelle.verificationEnfantEnConception());
	}
}
