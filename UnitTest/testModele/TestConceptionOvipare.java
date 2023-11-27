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
                "Statut de la creature invalide");
        assertTrue(thrown.getMessage().contains("Statut de la creature invalide"));
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
	    System.out.println("Nombre d'œufs dans le ventre avant : " + MegalodonFemelle.getVentre().size());
	    ArrayList<Oeuf> oeufs = MegalodonFemelle.pondreOeuf();
	    System.out.println("Nombre d'œufs dans le ventre apres : " + MegalodonFemelle.getVentre().size());
	    ArrayList<Creature> creatures = new ArrayList<Creature>();
	    //log pour voir combien d'œufs sont présents initialement
	    System.out.println("Nombre d'œufs initiaux : " + oeufs.size());
	    for (Oeuf o : oeufs) {
	        for (int i = 0; i < o.getDureeIncubation(); i++) {
	            o.decrementerDureeIncubationRestante();
	        }
	        //log pour voir la durée d'incubation restante après la boucle
	        System.out.println("Duree d'incubation restante : " + o.getDureeIncubationRestante());
	        if (o.getDureeIncubationRestante() == 0) {
	            Creature c = o.eclore();
	            creatures.add(c);
	        }
	    }

	    // Ajoutez un log pour voir combien de créatures ont été ajoutées à la liste
	    System.out.println("Nombre de creatures ajoutees : " + creatures.size());
	    if (!creatures.isEmpty()) {
	        assertEquals(Enum_Especes.Megalodon, creatures.get(0).getNomEspece());
	    } else {
	        fail("La liste de créatures est vide");
	    }
	}

}
