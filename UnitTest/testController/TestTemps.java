package testController;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import controllerTemps.GestionnaireTemps;
import static org.junit.Assert.*;

/**
 * Classe de test pour la gestion du temps
 */
public class TestTemps {
	
	GestionnaireTemps gestionnaireTemps;

    @BeforeEach
    public void setUp() {
        gestionnaireTemps = GestionnaireTemps.getInstance();
        gestionnaireTemps.setDate(2023, 11, 24);
    }

    @Test
    public void testPasserLeTemps() {
    	gestionnaireTemps.setDate(2000, 10, 10);
        assertFalse(gestionnaireTemps.passerLeTemps(5));
        assertEquals("2000-10-15", gestionnaireTemps.getDateActuelle());
    }

    @Test
    public void testAjouterTemps() {
    	gestionnaireTemps.setDate(2000, 10, 10);
    	gestionnaireTemps.ajouterTemps(2, 1, 10); // Ajouter 2 années, 1 mois, 10 jours
        assertEquals("2002-11-20", gestionnaireTemps.getDateActuelle());
        // Ajouter 1 jour et vérifier si l'année a changé
        gestionnaireTemps.ajouterTemps(0, 0, 1);
        assertTrue(gestionnaireTemps.verifierChangementAnnee(2001));
    }

    @Test
    public void testGetDateEnregistree() {
    	gestionnaireTemps.setDate(2023, 11, 24);
        assertEquals("2023-11-24", gestionnaireTemps.getDateActuelle());
    }

    @Test
    public void testGetDateActuelle() {
        assertEquals("2023-11-24", gestionnaireTemps.getDateActuelle());
    }

    @Test
    public void testGetAnnee() {
        assertEquals(2023, gestionnaireTemps.getAnnee());
    }

    @Test
    public void testGetMois() {
        assertEquals(11, gestionnaireTemps.getMois());
    }

    @Test
    public void testGetJour() {
        assertEquals(24, gestionnaireTemps.getJour());
    }

    @Test
    public void testVeriferChangementAnnee() {
        assertFalse(gestionnaireTemps.verifierChangementAnnee(2023));
        gestionnaireTemps.ajouterTemps(0, 1, 0); // Ajouter 1 mois
        assertFalse(gestionnaireTemps.verifierChangementAnnee(2023));
        gestionnaireTemps.ajouterTemps(1, 0, 0); // Ajouter 1 an
        assertTrue(gestionnaireTemps.verifierChangementAnnee(2023));
    }
}
