package testModele;

import base.Creature;
import creaturesImplemente.FactoryCreature;
import creaturesImplemente.Megalodon;
import creaturesImplemente.Oeuf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import references.Enum_Especes;
import references.Enum_Sexe;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la onception d'enfants chez les ovipares
 */
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
		boolean result = !MegalodonFemelle.getVentre().isEmpty();
		assertTrue(result);
	}
	
	@Test
	void TestMethodeCreerBebeAvecException() {
		Exception thrown = assertThrows(
                Exception.class,
                () -> MegalodonMale.creerBebe(MegalodonMale),
                "Statut de la creature "+MegalodonMale.getNomEspece()+" "+MegalodonMale.getPrenom()+" invalide");
        assertTrue(thrown.getMessage().contains("Statut de la creature "+MegalodonMale.getNomEspece()+" "+MegalodonMale.getPrenom()+" invalide"));
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
	    ArrayList<Creature> creatures = new ArrayList<>();
	    //log pour voir combien d'œufs sont présents initialement
	    for (Oeuf o : oeufs) {
	        for (int i = 0; i < o.getDureeIncubation(); i++) {
	            o.decrementerDureeIncubationRestante();
	        }
	        if (o.getDureeIncubationRestante() == 0) {
	            Creature c = o.eclore();
	            creatures.add(c);
	        }
	    }
	    // Ajoutez un log pour voir combien de créatures ont été ajoutées à la liste
	    if (!creatures.isEmpty()) {
	        assertEquals(Enum_Especes.Megalodon, creatures.get(0).getNomEspece());
	    } else {
	        fail("La liste de créatures est vide");
	    }
	}

}
