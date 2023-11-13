package Test1;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import creaturesImplemente.*;
import references.*;

class TestCreatureConception {
	
	CONSTANTES constantes = new CONSTANTES();
    FactoryCreature factory = new FactoryCreature();
    

    Megalodon Couverture; // Ovipare
    Megalodon Polochon;
    Sirene Ariel; // Vivipare
    Sirene PrinceEric;

    @BeforeEach
    void construction() throws Exception {
    	Couverture = FactoryCreature.newCreature(Enum_Especes.Megalodon, Enum_Sexe.Femelle, 20, 50);
    	Polochon = FactoryCreature.newCreature(Enum_Especes.Megalodon, Enum_Sexe.Male, 20, 50);
    	Ariel = FactoryCreature.newCreature(Enum_Especes.Sirene, Enum_Sexe.Femelle, 30, 15);
    	PrinceEric = FactoryCreature.newCreature(Enum_Especes.Sirene, Enum_Sexe.Male, 30, 15);
    }

	@Test
	void testOvipareMaleFail() throws Exception {
		Exception thrown = assertThrows(
                Exception.class,
                () -> Polochon.PondreOeuf(Couverture),
                "Statut creature invalide");
        assertTrue(thrown.getMessage().contains("Statut creature invalide"));
	}
	
	@Test
	void testOviparePondreOeuf() throws Exception {
		Oeuf coussin = Couverture.PondreOeuf(Polochon);
		assertEquals(Enum_Especes.Megalodon, coussin.getEspece());
	}
	
	
	@Test
	void testVivipareMaleFail() throws Exception {
		Exception thrown = assertThrows(
                Exception.class,
                () -> PrinceEric.concevoirUnEnfant(Couverture,1),
                "La nature autorise seulement une femelle de concevoir un enfant avec un male de la meme espece");
        assertTrue(thrown.getMessage().contains("La nature autorise seulement une femelle de concevoir un enfant avec un male de la meme espece"));
	}

}
