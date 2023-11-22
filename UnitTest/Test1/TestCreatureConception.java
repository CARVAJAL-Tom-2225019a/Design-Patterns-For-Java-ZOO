package Test1;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import base.Vivipare;
import org.junit.jupiter.api.*;

import base.Creature;
import base.Ovipare;
import creaturesImplemente.*;
import enclosImplemente.Enclos;
import references.*;
import zoo.ZooFantastique;

import java.util.ArrayList;

class TestCreatureConception {

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
                () -> Polochon.PondreOeuf(),
                "Statut creature invalide");
        assertTrue(thrown.getMessage().contains("Statut creature invalide"));
	}
	
	@Test
	void testOviparePondreOeuf() throws Exception {
		ArrayList<Oeuf> coussins = Couverture.PondreOeuf();
		assertEquals(Enum_Especes.Megalodon, coussins.get(0).getEspece());
	}
	
	/**
	@Test
	void testVivipareMaleFail() throws Exception {
		Exception thrown = assertThrows(
                Exception.class,
                () -> PrinceEric.concevoirUnEnfant( Couverture,1),
                "La nature autorise seulement une femelle de concevoir un enfant avec un male de la meme espece");
        assertTrue(thrown.getMessage().contains("La nature autorise seulement une femelle de concevoir un enfant avec un male de la meme espece"));
	}*/
	
	@Test
	void testConcevoirEnfantOvipare() throws Exception {
		ZooFantastique zoo = ZooFantastique.getInstance();
    	Enclos enclos = new Enclos ("TestE", 100);
    	zoo.AddEnclos(enclos);
    	enclos.AjouterCreature(FactoryCreature.newCreature(Enum_Especes.Dragon, Enum_Sexe.Femelle, 10, 10));
    	enclos.AjouterCreature(FactoryCreature.newCreature(Enum_Especes.Dragon, Enum_Sexe.Male, 10, 10));
    	// Femelle
    	int indexCreature = 1;
    	Creature femelle = enclos.getListeCreatures().get(indexCreature);
    	// Femelle
    	indexCreature = 2;
    	Creature male = enclos.getListeCreatures().get(indexCreature);
    	//Conception
    	int naitre = enclos.ConcevoirEnfant(femelle, male);
    	if (naitre==2) {
    		ArrayList<Oeuf> oeufs = ((Ovipare)femelle).PondreOeuf();
    		for (Oeuf o : oeufs) {
				zoo.AddOeuf(o);
			}
    	}
    	assertEquals(1, zoo.GetlLsteOeufs().size());
	}
	
	@Test
	void testConcevoirEnfantVivipare() throws Exception {
		ZooFantastique zoo = ZooFantastique.getInstance();
    	Enclos enclos = new Enclos ("TestE", 100);
    	zoo.AddEnclos(enclos);
    	enclos.AjouterCreature(FactoryCreature.newCreature(Enum_Especes.Sirene, Enum_Sexe.Femelle, 10, 10));
    	enclos.AjouterCreature(FactoryCreature.newCreature(Enum_Especes.Sirene, Enum_Sexe.Male, 10, 10));
    	// Femelle
    	int indexCreature = 1;
    	Creature femelle = enclos.getListeCreatures().get(indexCreature);
    	// Femelle
    	indexCreature = 2;
    	Creature male = enclos.getListeCreatures().get(indexCreature);
    	//Conception
    	int naitre = enclos.ConcevoirEnfant(femelle, male);
    	if (naitre==1)
    		zoo.AddFemelleEnceinte(femelle);
    	assertEquals(1, zoo.GetListeFemelleEnceinte().size());
	}

}
