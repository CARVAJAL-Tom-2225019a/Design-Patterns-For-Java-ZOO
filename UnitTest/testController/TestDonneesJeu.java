package testController;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import applicationRun.Donnees;
import base.Creature;
import base.Enclos;
import meuteLycanthrope.ColonieLycanthrope;
import meuteLycanthrope.Meute;
import references.CONSTANTES;
import references.Enum_Sexe;
import zoo.ZooFantastique;

/**
 * Classe permettant de tester la creation de donnees de l'application
 */
class TestDonneesJeu {
	
	ZooFantastique zoo = ZooFantastique.getInstance();
	ColonieLycanthrope colonie = ColonieLycanthrope.getInstance();

	@BeforeEach
	void constructor() {
		zoo.clear();
		colonie.clear();
		Donnees.creerDonneesJeu();
	}
	
	@Test
	void testNombreEnclos() {
		assertEquals(12, zoo.getListeEnclos().size());
	}
	
	@Test
	void testNombreCreatureDansEnclos() throws Exception {
		assertEquals(CONSTANTES.NB_CREATURE_PAR_ENCLOS, zoo.trouverEnclosParNom("LicorneLand").getListeCreatures().size());
	}
	
	@Test
	void testNombreMeute() {
		for (Meute m : colonie.getListeMeutes())
			System.out.println("MEUTE "+m.getEnclosReference().getNom()+"\n");
		assertEquals(2, colonie.getListeMeutes().size());
	}
	
	@Test
	void testMaleEtFemelleDansChaqueEnclos() {
		System.out.println("TEST MALE ET FEMELLE DANS CHAQUE ENCLOS");
		boolean ok = true;
	    for (Enclos enclos : zoo.getListeEnclos()) {
	    	if (!enclos.getListeCreatures().isEmpty()) {
	    		boolean isFemelle = false;
		        boolean isMale = false;
		        for (Creature creat : enclos.getListeCreatures().values()) {
		            if (creat.getSexe() == Enum_Sexe.Femelle) {
		                isFemelle = true;
		            } else if (creat.getSexe() == Enum_Sexe.Male) {
		                isMale = true;
		            }
		        }
		        if (!isFemelle || !isMale) {
		            ok = false;
		            System.out.println("Enclos " + enclos.getNom() + " ne contient pas une paire de Male et Femelle");
		        }
	    	}
	    }
	    assertTrue(ok);
	}

}
