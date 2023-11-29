package testModele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import creaturesImplemente.FactoryCreature;
import creaturesImplemente.Humain;
import creaturesImplemente.Lycanthrope;
import meuteLycanthrope.Meute;
import references.Enum_ActionHurlement;
import references.Enum_Especes;
import references.Enum_RangDomination;
import references.Enum_Sexe;

class TestLycanthrope {
	Lycanthrope femelleA;
	Lycanthrope maleA;
	Lycanthrope loup1;
	Lycanthrope loup2;
	Meute meute;
	
	@BeforeEach
	void construction() throws Exception {
		femelleA = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
		femelleA.setSexe(Enum_Sexe.Femelle);
		maleA = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
		maleA.setSexe(Enum_Sexe.Male);
		loup1 = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
		loup2 = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
		
		Set<Enum_RangDomination> rangPossible = new HashSet<Enum_RangDomination>();
		rangPossible.add(Enum_RangDomination.ALPHA);
		rangPossible.add(Enum_RangDomination.BETA);
		rangPossible.add(Enum_RangDomination.GAMMA);
		rangPossible.add(Enum_RangDomination.OMEGA);
		
		meute = new Meute(femelleA, maleA, 10, rangPossible);
	}
	
	@Test
	void testHurlerAppartenance() throws Exception {
		String chaineAttendue = "Je suis "+loup1.getPrenom()+", un "
				+ "loup solitaire, et je n'ai peur peur de rien\n"
				+"Je suis "+loup2.getPrenom()+", et je ne te crois pas superieur a moi !\n";
		String result = loup1.hurler(Enum_ActionHurlement.Appartenance, loup2);
		assertEquals(chaineAttendue, result);
	}
	
	@Test
	void testRejoindreMeute() throws Exception {
		loup1.rejoindreMeute(meute);
		assertTrue(loup1.getMeute() == meute);
	}

	
	@Test
	void testSeTransformerEnHumain() {
		int ageLoup = loup1.getAge();
		Humain humain1 = loup1.seTransformerEnHumain();
		int ageHumain = humain1.getAge();
		assertEquals (ageLoup, ageHumain);
	}

}
