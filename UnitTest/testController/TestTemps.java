package testController;


import controllerTemps.GestionnaireTemps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Assertions.assertFalse(gestionnaireTemps.passerLeTemps(5));
        Assertions.assertEquals("2000-10-15", gestionnaireTemps.getDateActuelle());
    }

    @Test
    public void testAjouterTemps() {
    	gestionnaireTemps.setDate(2000, 10, 10);
    	gestionnaireTemps.ajouterTemps(2, 1, 10); // Ajouter 2 années, 1 mois, 10 jours
        Assertions.assertEquals("2002-11-20", gestionnaireTemps.getDateActuelle());
        // Ajouter 1 jour et vérifier si l'année a changé
        gestionnaireTemps.ajouterTemps(0, 0, 1);
        Assertions.assertTrue(gestionnaireTemps.verifierChangementAnnee(2001));
    }

    @Test
    public void testGetDateEnregistree() {
    	gestionnaireTemps.setDate(2023, 11, 24);
        Assertions.assertEquals("2023-11-24", gestionnaireTemps.getDateActuelle());
    }

    @Test
    public void testGetDateActuelle() {
        Assertions.assertEquals("2023-11-24", gestionnaireTemps.getDateActuelle());
    }

    @Test
    public void testGetAnnee() {
        Assertions.assertEquals(2023, gestionnaireTemps.getAnnee());
    }

    @Test
    public void testGetMois() {
        Assertions.assertEquals(11, gestionnaireTemps.getMois());
    }

    @Test
    public void testGetJour() {
        Assertions.assertEquals(24, gestionnaireTemps.getJour());
    }

    @Test
    public void testVeriferChangementAnnee() {
        Assertions.assertFalse(gestionnaireTemps.verifierChangementAnnee(2023));
        gestionnaireTemps.ajouterTemps(0, 1, 0); // Ajouter 1 mois
        Assertions.assertFalse(gestionnaireTemps.verifierChangementAnnee(2023));
        gestionnaireTemps.ajouterTemps(1, 0, 0); // Ajouter 1 an
        Assertions.assertTrue(gestionnaireTemps.verifierChangementAnnee(2023));
    }
}
