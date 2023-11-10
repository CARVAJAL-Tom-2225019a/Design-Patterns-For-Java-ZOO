package Test1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import enums.*;
import impl.*;
import base.*;

class TestCreature1 {
	
	CONSTANTES constantes = new CONSTANTES();
	
	Dragon Krokmou = new Dragon (Enum_Especes.Dragon, Enum_Sexe.Femelle, 20, 50, "Grrrr", Duration.parse("P0DT0H0M0.20S"));
	Phenix Pepito = new Phenix (Enum_Especes.Phenix, Enum_Sexe.Male, 3, 10, "Aaii", Duration.parse("P0DT0H0M0.60S"));

	@Test
	void testPerdreSommeil() throws Exception {
		Pepito.PerdreSante();
		assertEquals(constantes.MAX_INDICATEUR-constantes.VALEUR_PERTE_INDICATEUR, Pepito.getIndicateurSante());
	}
	@Test
	void testPerdreSommeilException() throws Exception {
		for (int i=0; i<5; i++)
			Pepito.PerdreSante();
		// TODO : verifier expression
	}
	
	@Test
	void testDeplacement () {
		fail("Not yet implemented");
	}
	
	@Test
	void testManger () {
		fail("Not yet implemented");
	}


}
