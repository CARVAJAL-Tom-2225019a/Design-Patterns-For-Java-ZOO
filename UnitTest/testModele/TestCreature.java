package testModele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import creaturesImplemente.Dragon;
import creaturesImplemente.FactoryCreature;
import references.CONSTANTES;
import references.Enum_Especes;

class TestCreature {
	
	Dragon dragon1;
	Dragon dragon2;
	
	
	@BeforeEach
	void construction() throws Exception {
		dragon1 = FactoryCreature.newCreature(Enum_Especes.Dragon);
		dragon1.PerdreNourriture();
		dragon1.PerdreSante();
		dragon1.PerdreSante();
		for (int  i=0; i<20; i++) {
			dragon1.PerdreSommeil();
		}
		dragon2 = FactoryCreature.newCreature(Enum_Especes.Dragon);
	}
	
	
	@Test
	void testMethodePerdreNourriture() throws Exception {
		dragon2.PerdreNourriture();
		double resultatReal = CONSTANTES.MAX_INDICATEUR - CONSTANTES.VALEUR_PERTE_INDICATEUR;
		assertEquals(resultatReal, dragon2.getIndicateurFaim());
	}
	
	
	@Test
	void testMethodePerdreSante() throws Exception {
		dragon2.PerdreSante();
		dragon2.PerdreSante();
		double resultatReal = CONSTANTES.MAX_INDICATEUR - (CONSTANTES.VALEUR_PERTE_INDICATEUR*2);
		assertEquals(resultatReal, dragon2.getIndicateurSante());
	}
	
	
	@Test
	void testMethodePerdreSommeil() throws Exception {
		for (int  i=0; i<20; i++) {
			dragon2.PerdreSommeil();
		}
		assertEquals(0, dragon2.getIndicateurSommeil());
	}
	
	
	@Test
	void testMethodeSoigner() throws Exception {
		dragon1.Soigner();
		assertEquals(CONSTANTES.MAX_INDICATEUR, dragon1.getIndicateurSante());
	}
	
	
	@Test
	void testMethodeManger() throws Exception {
		dragon1.Manger(CONSTANTES.VALEUR_PERTE_INDICATEUR);
		assertEquals(CONSTANTES.MAX_INDICATEUR, dragon1.getIndicateurFaim());
	}
	
	
	@Test
	void testMethodeMangerAvecException() throws Exception {
		dragon1.Dormir();
		Exception thrown = assertThrows(
                Exception.class,
                () -> dragon1.Manger(CONSTANTES.VALEUR_PERTE_INDICATEUR),
                "Etat de la creature invalide, impossible de manger");
        assertTrue(thrown.getMessage().contains("Etat de la creature invalide, impossible de manger"));
	}
	
	
	@Test
	void testMethodeSeReveiller() throws Exception {
		dragon1.Dormir();
		dragon1.SeReveiller();
		assertEquals(false, dragon1.isEnTrainDeDormir());
	}
	
	
	@Test
	void testMethodeFaireBruit() throws Exception {
		String bruit = dragon1.getBruit();
		assertEquals(bruit, dragon1.FaireBruit());
	}
	
	
	@Test
	void testMethodeVieillir() throws Exception {
		int ageAttendu = dragon1.getAge()+1;
		dragon1.Vieillir();
		assertEquals (ageAttendu, dragon1.getAge());
	}
	
	
	@Test
	void testMethodeCombattre() {
		Dragon gagnant = (Dragon) dragon1.Combattre(dragon2);
		 if (dragon1.getForce() > dragon2.getForce())
			 assertEquals(gagnant, dragon1);
		 else
			 assertEquals(gagnant, dragon2);
	}
	
	
	@Test
	void testMethodeVieillirAvecMort() throws Exception {
		int annee = CONSTANTES.MAX_AGE - dragon1.getAge();
		for (int i = 0; i<=annee; i++) {
			dragon1.Vieillir();
		}
		assertEquals(false, dragon1.isVivant());
	}
	
	
	@Test
	void testMethodeReinitialiserCreature() {
		dragon1.ReinitialiserCreature();
		assertTrue(dragon1.isVivant());
	}
}
