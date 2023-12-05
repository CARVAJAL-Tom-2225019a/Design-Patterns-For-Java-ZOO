package testController;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import applicationRun.Donnees;
import base.Creature;
import base.Enclos;
import controllerTemps.Evenements;
import meuteLycanthrope.ColonieLycanthrope;
import references.CONSTANTES;
import references.Enum_DegrePropreteEnclos;
import zoo.ZooFantastique;

class TestEvenements {
	
	ZooFantastique zoo = ZooFantastique.getInstance();
	ColonieLycanthrope colonie = ColonieLycanthrope.getInstance();
	
	@BeforeEach
	void construction() {
		zoo.clear();
		colonie.clear();
		Donnees.creerDonneesJeu();
	}

	@Test
	void testModifAleatoireStatutCreature() throws Exception {
		System.out.println("\n\nTEST MODIF ALEATOIRE STATUT CREATURE");
		boolean fait = false;
		Evenements.modifAleatoireStatutCreature();
		for (Enclos e : zoo.getListeEnclos()) {
			for (Creature c : e.getListeCreatures().values()) {
				if (c.getIndicateurFaim()<CONSTANTES.MAX_INDICATEUR
						|| c.getIndicateurSante()<CONSTANTES.MAX_INDICATEUR
						|| c.getIndicateurSommeil()<CONSTANTES.MAX_INDICATEUR) {
					fait = true;
					break;
				}
			}
		}
		assertTrue(fait);
	}
	
	@Test
	void testGetRandomEnclosWithCreatures() {
		Enclos enclos = Evenements.getRandomEnclosWithCreatures();
		assertTrue (enclos.getNbCreatures() > 0);
	}
	
	@Test
	void testModifAleatoireEtatEnclos() {
		System.out.println("\n\nTEST MODIFICATION ALEATOIRE ETAT ENCLOS");
		boolean fait = false;
		for (int i=0; i<10; i++)
			Evenements.modifAleatoireEtatEnclos();
		for (Enclos e : zoo.getListeEnclos()) {
			if (e.isEnclosMauvaisEtat()) {
				fait = true;
				break;
			}
		}
		assertTrue(fait);
	}
	
	@Test
	void testDegradationSanteCreatureSelonEtatEnclos() throws Exception {
		System.out.println("\n\nTEST DEGRDATION SANTE SELON ETAT ENCLOS");
		boolean fait = false;
		Enclos enclos = zoo.trouverEnclosParNom("LicorneLand");
		enclos.setEtatEnclos(Enum_DegrePropreteEnclos.mauvais);
		Evenements.degradationSanteCreatureSelonEtatEnclos();
		for (Creature c : enclos.getListeCreatures().values()) {
			if (c.getIndicateurFaim()<CONSTANTES.MAX_INDICATEUR
					|| c.getIndicateurSante()<CONSTANTES.MAX_INDICATEUR
					|| c.getIndicateurSommeil()<CONSTANTES.MAX_INDICATEUR) {
				fait = true;
				break;
			}
		}
		assertTrue(fait);
	}
	
	@Test
	void testVerificationEtatCreature() throws Exception {
		System.out.println("\n\nTEST VERIFICATION ETAT CREATURE");
		Enclos enclos = zoo.trouverEnclosParNom("LicorneLand");
		Creature creat = enclos.getCreatureDominante();
		for (int i=0; i<20; i++) {
			creat.perdreNourriture();
			creat.perdreSante();
		}
		System.out.println("sante : "+creat.getIndicateurSante()+"/"+CONSTANTES.MAX_INDICATEUR);
		System.out.println("faim : "+creat.getIndicateurFaim()+"/"+CONSTANTES.MAX_INDICATEUR);
		Evenements.verificationEtatCreature();
		assertFalse(creat.isVivant());
	}
	
	@Test
	void testChangementAnneeCreaturesMortes() throws Exception {
		System.out.println("\n\nTEST CREATURES MORTES");
		int compteur = 0;
		Enclos enclos = zoo.trouverEnclosParNom("MegalodonLand");
		enclos.getCreatureDominante().mourir();
		for (Creature creatMorte : enclos.creaturesMortes()) {
			System.out.println(creatMorte.getPrenom());
			compteur++;
		}
		assertEquals(1, compteur);
	}
	
	@Test
	void testCreaturesAyantBesoin() throws Exception {
		System.out.println("\n\nTEST CREATURES AYANT BESOIN");
		int compteur = 0;
		HashSet<Creature> temp = new HashSet<>();
		Enclos enclos = zoo.trouverEnclosParNom("DragonLand");
		Creature creat = enclos.getCreatureDominante();
		for (int i=0; i<20; i++) {
			creat.perdreNourriture();
			creat.perdreSante();
		}
		System.out.println("sante : "+creat.getIndicateurSante()+"/"+CONSTANTES.MAX_INDICATEUR);
		System.out.println("faim : "+creat.getIndicateurFaim()+"/"+CONSTANTES.MAX_INDICATEUR);
		for (Enclos e : zoo.getListeEnclos()) {
		    temp.addAll(e.getCreaturesAyantUnBesoin());
		}
		System.out.println("taille liste : "+temp.size());
        if (!temp.isEmpty()) {
        	for (Creature c : temp) {
        		compteur++;
        		System.out.println(c.getPrenom());
        	}
        }
        assertEquals(1, compteur);
	}

}
