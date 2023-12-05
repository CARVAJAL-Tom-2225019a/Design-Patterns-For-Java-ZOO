package testModele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import creaturesImplemente.Dragon;
import creaturesImplemente.FactoryCreature;
import references.CONSTANTES;
import references.Enum_Especes;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour les méthodes d'une créature
 */
class TestCreature {
	
	Dragon dragon1;
	Dragon dragon2;
	
	
	@BeforeEach
	void construction() throws Exception {
		dragon1 = FactoryCreature.newCreature(Enum_Especes.Dragon);
		dragon1.perdreNourriture();
		dragon1.perdreSante();
		dragon1.perdreSante();
		for (int  i=0; i<20; i++) {
			dragon1.perdreSommeil();
		}
		dragon2 = FactoryCreature.newCreature(Enum_Especes.Dragon);
	}
	
	
	@Test
	void testMethodePerdreNourriture() throws Exception {
		dragon2.perdreNourriture();
		double resultatReal = CONSTANTES.MAX_INDICATEUR - CONSTANTES.VALEUR_PERTE_INDICATEUR;
		assertEquals(resultatReal, dragon2.getIndicateurFaim());
	}
	
	
	@Test
	void testMethodePerdreSante() throws Exception {
		dragon2.perdreSante();
		dragon2.perdreSante();
		double resultatReal = CONSTANTES.MAX_INDICATEUR - (CONSTANTES.VALEUR_PERTE_INDICATEUR*2);
		assertEquals(resultatReal, dragon2.getIndicateurSante());
	}
	
	
	@Test
	void testMethodePerdreSommeil() throws Exception {
		for (int  i=0; i<20; i++) {
			dragon2.perdreSommeil();
		}
		assertEquals(0, dragon2.getIndicateurSommeil());
	}
	
	
	@Test
	void testMethodeSoigner() throws Exception {
		dragon1.soigner();
		assertEquals(CONSTANTES.MAX_INDICATEUR, dragon1.getIndicateurSante());
	}
	
	
	@Test
	void testMethodeManger() throws Exception {
		dragon1.manger(CONSTANTES.VALEUR_PERTE_INDICATEUR);
		assertEquals(CONSTANTES.MAX_INDICATEUR, dragon1.getIndicateurFaim());
	}
	
	
	@Test
	void testMethodeMangerAvecException() throws Exception {
		dragon1.dormir();
		Exception thrown = assertThrows(
                Exception.class,
                () -> dragon1.manger(CONSTANTES.VALEUR_PERTE_INDICATEUR),
                "Etat du "+dragon1.getNomEspece()+" "+dragon1.getPrenom()+" invalide, impossible de manger");
        assertTrue(thrown.getMessage().contains("Etat du "+dragon1.getNomEspece()+" "+dragon1.getPrenom()+" invalide, impossible de manger"));
	}
	
	
	@Test
	void testMethodeSeReveiller() throws Exception {
		dragon1.dormir();
		dragon1.seReveiller();
        assertFalse(dragon1.isEnTrainDeDormir());
	}
	
	
	@Test
	void testMethodeFaireBruit() throws Exception {
		String bruit = dragon1.getBruit();
		assertEquals(bruit, dragon1.faireBruit());
	}
	
	
	@Test
	void testMethodeVieillir() throws Exception {
		int ageAttendu = dragon1.getAge()+1;
		dragon1.vieillir();
		assertEquals (ageAttendu, dragon1.getAge());
	}
	
	
	@Test
	void testMethodeCombattre() throws Exception {
		Dragon gagnant = (Dragon) dragon1.combattre(dragon2);
		 if (dragon1.getForce() > dragon2.getForce())
			 assertEquals(gagnant, dragon1);
		 else
			 assertEquals(gagnant, dragon2);
	}

	
	@Test
	void testMethodeVieillirAvecMort() throws Exception {
		int annee = CONSTANTES.MAX_AGE - dragon1.getAge();
		for (int i = 0; i<=annee; i++) {
			dragon1.vieillir();
		}
        assertFalse(dragon1.isVivant());
	}
	
	
	@Test
	void testMethodeReinitialiserCreature() {
		dragon1.reinitialiserCreature();
		assertTrue(dragon1.isVivant());
	}
}
