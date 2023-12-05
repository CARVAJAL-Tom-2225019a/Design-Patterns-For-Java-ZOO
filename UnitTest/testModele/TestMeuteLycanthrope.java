package testModele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import creaturesImplemente.FactoryCreature;
import creaturesImplemente.Lycanthrope;
import meuteLycanthrope.Meute;
import references.CONSTANTES;
import references.Enum_ActionHurlement;
import references.Enum_Especes;
import references.Enum_RangDomination;
import references.Enum_Sexe;

/**
 * Classe de test pour les méthodes d'une meute de lycanthrope
 */
class TestMeuteLycanthrope {
	
	Meute meute1;
	Meute meute2;
	Lycanthrope femelleAlphaMeute1;
	Lycanthrope femelleAlphaMeute2;
	Lycanthrope maleAlpha;
	Lycanthrope loupFort;
	Lycanthrope loupFaible;

	@BeforeEach
	void construction() throws Exception {
		// Meute 1
		femelleAlphaMeute1 = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
		femelleAlphaMeute1.setSexe(Enum_Sexe.Femelle);
		maleAlpha = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
		maleAlpha.setSexe(Enum_Sexe.Male);
		Set<Enum_RangDomination> rangPossible = new HashSet <Enum_RangDomination>();
		rangPossible.add(Enum_RangDomination.ALPHA);
		rangPossible.add(Enum_RangDomination.BETA);
		rangPossible.add(Enum_RangDomination.DELTA);
		rangPossible.add(Enum_RangDomination.CHI);
		rangPossible.add(Enum_RangDomination.OMEGA);
		meute1 = new Meute ("meute1", femelleAlphaMeute1, maleAlpha, 20, rangPossible);
		for (int i=0; i<10; i++) {
			Lycanthrope c = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
			meute1.addLoup(c);
		}
		// Meute 2
		femelleAlphaMeute2 = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
		femelleAlphaMeute2.setSexe(Enum_Sexe.Femelle);
		maleAlpha = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
		maleAlpha.setSexe(Enum_Sexe.Male);
		rangPossible = new HashSet <Enum_RangDomination>();
		rangPossible.add(Enum_RangDomination.ALPHA);
		rangPossible.add(Enum_RangDomination.BETA);
		rangPossible.add(Enum_RangDomination.OMEGA);
		meute2 = new Meute ("meute2", femelleAlphaMeute2, maleAlpha, 20, rangPossible);
		for (int i=0; i<5; i++) {
			Lycanthrope c = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
			meute2.addLoup(c);
		}
		loupFort = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
		loupFort.setSexe(Enum_Sexe.Male);
		meute2.addLoup(loupFort);
		loupFort.setRangDomination(Enum_RangDomination.ALPHA);
		loupFaible = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
		meute2.addLoup(loupFaible);
		loupFaible.setRangDomination(Enum_RangDomination.OMEGA);
	}

	
	@Test
	void testAffecterRang() {
		int nbrDelta = 0;
		for (Lycanthrope l : meute1.getListeLoup()) {
			if (l.getRangDomination() == Enum_RangDomination.DELTA)
				nbrDelta++;
		}
		assertTrue(nbrDelta>0);
	}
	
	
	@Test
	void testRemoveLoup() throws Exception {
	    List<Lycanthrope> listeLoup = new ArrayList<>(meute1.getListeLoup());
	    assertTrue(!listeLoup.isEmpty());
	    int indexToRemove = 0;
	    Lycanthrope loupToRemove = null;
	    while (loupToRemove==null || loupToRemove==meute1.getCoupleAlpha().getFemelleAlpha()
	    		|| loupToRemove==meute1.getCoupleAlpha().getMaleAlpha()) {
	    	indexToRemove++;
	    	loupToRemove = listeLoup.get(indexToRemove);
	    }
	    meute1.removeLoup(loupToRemove);
	    assertFalse(meute1.getListeLoup().contains(loupToRemove));
	    assertEquals(Enum_RangDomination.OMEGA, loupToRemove.getRangDomination());
	}
	
	
	@Test
	void testDefierMaleAlpha() throws Exception {
		Lycanthrope ancienMaleAlpha = meute1.getCoupleAlpha().getMaleAlpha();
		Lycanthrope loup = FactoryCreature.newCreature(Enum_Especes.Lycanthrope);
		loup.setSexe(Enum_Sexe.Male);
		meute1.addLoup(loup);
		loup.setRangDomination(Enum_RangDomination.ALPHA);
		loup.setForce(1000000000);
		meute1.defierMaleAlpha(loup);
		assertTrue(ancienMaleAlpha != meute1.getCoupleAlpha().getMaleAlpha());
	}
	
	
	@Test
	void testVoirOmega() {
		Set<Lycanthrope> liste = meute2.voirOmega();
		assertTrue(liste.size() > 0);
	}
	
	
	@Test
	void testHurlementAppartenance() throws Exception {
		String resultatAttendu = loupFort.getPrenom()+" : Ma meute, la meilleure.\n"
				+loupFaible.getPrenom()+" :  Je ne te crois pas superieur a moi "+loupFort.getPrenom()+" !\n";
		String resultat = loupFort.hurler(Enum_ActionHurlement.Appartenance, loupFaible);
		assertEquals(resultatAttendu, resultat);
	}
	
	@Test
	void testHurlementDomination() throws Exception {
		loupFaible.setRangDomination(Enum_RangDomination.OMEGA);
	    String resultatAttendu = String.format(
	            "%s : Je suis un %s, et je te domine toi %s %s\n" +
	            "%s : Je suis agressif d'un niveau de %d/%d\n" +
	            "ATTAQUE DU LYCANTHROPE %s !\n" +
	            "%s : Tu ne me fais pas peur %s !\n" +
	            "%s : Je suis un %s, et je me soumet a toi %s %s\n",
	            loupFort.getPrenom(), loupFort.getRangDomination().getDescription(), loupFaible.getPrenom(), loupFaible.getRangDomination().getDescription(),
	            loupFort.getPrenom(), loupFort.getFacteurImpetuosite(), CONSTANTES.MAX_FACTEUR_IMPETUOSITE,
	            loupFort.getPrenom(),
	            loupFaible.getPrenom(), loupFort.getPrenom(),
	            loupFaible.getPrenom(), loupFaible.getRangDomination().getRangInferieur().getDescription(), loupFort.getPrenom(), loupFort.getRangDomination().getDescription());
	    String resultat = loupFort.hurler(Enum_ActionHurlement.Domination, loupFaible);
	    assertEquals(resultatAttendu, resultat);
	}
	
}
