package Test1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import CreaturesImplemente.Dragon;
import CreaturesImplemente.Phenix;
import enums.Enum_Especes;
import enums.Enum_Sexe;
import base.CONSTANTES;

class TestCreature1 {

    CONSTANTES constantes = new CONSTANTES();

    Dragon Krokmou = new Dragon(Enum_Especes.Dragon, Enum_Sexe.Femelle, 20, 50, "Grrrr", Duration.parse("P0DT0H0M0.20S"));
    Phenix Pepito = new Phenix(Enum_Especes.Phenix, Enum_Sexe.Male, 3, 10, "Aaii", Duration.parse("P0DT0H0M0.60S"));

    
    /**
     * Teste la méthode PerdreSante() de la classe Phenix.
     * Vérifie si la santé diminue correctement après l'appel de la méthode.
     */
    @Test
    void testPerdreSante() throws Exception {
        Pepito.PerdreSante();
        assertEquals(constantes.MAX_INDICATEUR - constantes.VALEUR_PERTE_INDICATEUR, Pepito.getIndicateurSante());
    }
    
    
    /**
     * Teste la méthode PerdreSante() de la classe Phenix.
     * Vérifie si une exception est envoyé une fois la barre de santé vide.
     */
    @Test
    void testPerdreSanteAvecException() throws Exception {
    	int nbrTour = constantes.MAX_INDICATEUR / constantes.VALEUR_PERTE_INDICATEUR;
        for (int i = 0; i < nbrTour; i++)
            Pepito.PerdreSante();
        Exception thrown = assertThrows(
                Exception.class,
                () -> Pepito.PerdreSante(),
                "Creature pas en etat de realiser action");
        assertTrue(thrown.getMessage().contains("Creature pas en etat de realiser action"));
    }
    
    
    /**
     * Teste la méthode Deplacement() de la classe Dragon.
     * Vérifie si la méthode renvoie le mouvement.
     */
    @Test
    void testDeplacement() throws Exception {
        assertEquals("Le dragon est en mouvement", Krokmou.Courrir());
    }

    
    /**
     * Teste la méthode Manger() de la classe Phenix.
     * Vérifie si l'indicateur de faim est bien implémenté.
     */
    @Test
    void testManger() throws Exception {
    	Pepito.PerdreNourriture();
    	Pepito.Manger((int)constantes.VALEUR_PERTE_INDICATEUR);
    	assertEquals(constantes.MAX_INDICATEUR, Pepito.getIndicateurFaim());
    }
    
    
    /**
     * Teste la méthode Manger() de la classe Phenix.
     * Vérifie si l'indicateur de faim ne dépasse pas le maximum autorisé.
     */
    @Test
    void testMangerQuandPlusFaim() throws Exception {
    	Pepito.Manger((int)constantes.VALEUR_PERTE_INDICATEUR);
    	assertEquals(constantes.MAX_INDICATEUR, Pepito.getIndicateurFaim());
    }
    
    
    /**
     * Teste la méthode Mourrir() et Nager() de la classe Dragon.
     * Vérifie qu'une fois mort, la créature ne peut plus réaliser d'action.
     */
    @Test
    void testMourrir() throws Exception {
    	Krokmou.Mourir();
    	assertEquals(constantes.MAX_INDICATEUR, Pepito.getIndicateurFaim());
    	Exception thrown = assertThrows(
                Exception.class,
                () -> Krokmou.Nager(),
                "Dragon pas en etat de nager");
        assertTrue(thrown.getMessage().contains("Dragon pas en etat de nager"));
    }
}
