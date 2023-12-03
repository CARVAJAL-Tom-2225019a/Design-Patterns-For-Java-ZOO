package controllerApplication;

import java.util.HashSet;

import applicationRun.Run;
import controllerTemps.Enum_ActionsPossibles;
import controllerTemps.Evenements;
import controllerTemps.GestionnaireTemps;
import references.CONSTANTES;
import base.Creature;
import base.Enclos;
import viewApplication.*;
import zoo.ZooFantastique;

/**
 * Classe représentant le contrôleur du zoo avec les différentes actions disponibles
 * Permet d'effectuer toutes les actions relatives à la vie du zoo
 */
public class ControllerActions {
	private static ControllerPrincipal controlPrincipal = new ControllerPrincipal();
	private static VueGlobale VueGlobale;
    private static VueUtilisateur VueUtilisateur;
    private static VueAutomatique VueAutomatique;
    // Instance du zoo fantastique (Singleton)
    private static ZooFantastique zoo = ZooFantastique.getInstance();
    private Actions action = new Actions();
    private GestionnaireTemps temps = GestionnaireTemps.getInstance();
    
    
    /**
     * Constructeur de la classe ControllerActions
     */
    public ControllerActions() {
    	VueGlobale = new VueGlobale();
    	VueUtilisateur = new VueUtilisateur();
    	VueAutomatique = new VueAutomatique();
        new ControllerPrincipal();
    }

    
    /**
     * Lancement de la simulation
     */
    public void init() {
        // Initialisation des variables
    	temps.setDate(2024, 1, 1);
        try {
        	// Affiche le message de bienvenue
            if (Run.utilisateurControle) // gestion manuel
            	VueUtilisateur.bienvenue();
            else
            	VueAutomatique.bienvenue();
            VueGlobale.afficher(controlPrincipal.getInformationsDebut());
        }
        catch (Exception e) {
    		VueGlobale.afficher(e.getMessage());
    	}
    }

    
    /**
     * Effectue l'action correspondant au choix de l'action
     *
     * @param choix Le choix de l'action
     * @return true si la simulation doit continuer, sinon false
     */
    public boolean effectuerAction(int choix) {
        boolean retour = false;
        try {
        	switch (choix) {
            // Ne pas effectuer d'action
            case 0:
            	VueGlobale.afficher("\n ---- Pas d'action effectue "+Enum_ActionsPossibles.PAS_D_ACTION.getDureeTotale()+" ---- ");
            	if (temps.incrementerTemps(Enum_ActionsPossibles.PAS_D_ACTION))
            		passageAnnee();
            	retour= true;
            	break;
            // Voir les enclos
            case 1:
            	VueGlobale.afficher("\n ---- Voir les enclos existants "+Enum_ActionsPossibles.VOIR_ENCLOS_EXISTANTS.getDureeTotale()+" ---- ");
            	VueGlobale.afficher("\nIl y a "+zoo.getListeEnclos().size()+" enclos dans le zoo\n");
            	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
            	for (Enclos e : zoo.getListeEnclos()) {
            		VueGlobale.afficherEnclos(e, false);
            		VueGlobale.afficher("\n");
            	}
            	if (temps.incrementerTemps(Enum_ActionsPossibles.VOIR_ENCLOS_EXISTANTS))
            		passageAnnee();
            	retour= true;
            	break;
            // Voir nombre total de créatures
            case 2:
            	VueGlobale.afficher("\n ---- Voir le nombre de creatures totales "+Enum_ActionsPossibles.VOIR_NOMBRE_CREATURES_TOTALES.getDureeTotale()+" ---- ");
            	VueGlobale.afficher("Il y a " + zoo.getNbCreaturesTotales() + " creatures.");
            	if (temps.incrementerTemps(Enum_ActionsPossibles.VOIR_NOMBRE_CREATURES_TOTALES))
            		passageAnnee();
            	retour= true;
            	break;
            // Creer enclos
            case 3 : 
            	action.casCreerEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.CREER_ENCLOS))
            		passageAnnee();
            	retour= true;
            	break;
            	
            // Examiner un enclos
            case 4:
            	action.casExaminerEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.EXAMINER_ENCLOS))
            		passageAnnee();
                retour= true;
                break;
            // Nettoyer un enclos
            case 5:
            	action.casNettoyerEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.NETTOYER_ENCLOS))
            		passageAnnee();
                retour= true;
                break;
            // Nourrir les créatures d'un enclos
            case 6:
            	action.casNourrirEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.NOURRIR_CREATURES))
            		passageAnnee();
                retour= true;
                break;
            // Soigner les creatures d'un enclos
            case 7:
            	action.casSoignerEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.SOIGNER_ENCLOS))
            		passageAnnee();
            	retour= true;
            	break;
            // Faire dormir un enclos
            case 8:
            	action.casDormirEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.DORMIR_ENCLOS))
            		passageAnnee();
            	retour= true;
            	break;
            // Reveiller un enclos
            case 9:
            	action.casReveillerEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.REVEILLER_ENCLOS))
            		passageAnnee();
            	retour= true;
            	break;
            // Transférer une créature
            case 10:
            	action.casTransfererCreature();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.TRANSFERER_CREATURE))
            		passageAnnee();
            	retour= true;
            	break;
           // Transferer un enclos
            case 11 : 
            	action.casTransfererEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.TRANSFERER_ENCLOS))
            		passageAnnee();
            	retour = true;
            	break;
           // Concevoir un enfant
            case 12 :
            	action.casConcevoirEnfant();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.CONCEVOIR_ENFANT))
            		passageAnnee();
            	retour= true;
            	break;
            // Voir liste bebes en cosntruction
            case 13 :
            	VueGlobale.afficher("\n ---- Voir les enfants qui vont bientot naitre "+Enum_ActionsPossibles.VOIR_BEBES_EN_CONSTRUCTION.getDureeTotale()+" ---- ");
            	VueGlobale.afficher(zoo.afficherFemellesEnceinte());
            	VueGlobale.afficher(zoo.afficherOeufs());
            	if (temps.incrementerTemps(Enum_ActionsPossibles.VOIR_BEBES_EN_CONSTRUCTION))
            		passageAnnee();
            	retour= true;
            	break;
            // Mettre un enclos en mouvement
            case 14 :
            	action.casEnclosEnMouvement();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.METTRE_ENCLOS_EN_MOUVEMENT))
            		passageAnnee();
            	retour= true;
            	break;
            // Faire chanter un enclos
            case 15 : 
            	action.casChanterEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.FAIRE_CHANTER_ENCLOS))
            		passageAnnee();
            	retour= true;
            	break;
           // Organiser un combat
           case 16 :
				action.casCombat();
				if (temps.incrementerTemps(Enum_ActionsPossibles.COMBAT))
					passageAnnee();
				retour= true;
				break;
            // Voir les meutes
            case 17 : 
            	action.casVoirMeutes();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.VOIR_MEUTES))
            		passageAnnee();
            	retour= true;
            	break;
            // Voir les lycanthropes
            case 18 : 
            	action.casVoirLycanthropes();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.VOIR_LOUPS))
            		passageAnnee();
            	retour= true;
            	break;
            // Saison amour pour les lycanthropes
            case 19 : 
            	action.casSaisonAmourLycanthropes();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.SAISON_AMOUR_LOUPS))
            		passageAnnee();
            	retour= true;
            	break;
            // Faire hurler loup
            case 20 :
            	action.casFaireHurlerLoup();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.FAIRE_HURLER_LOUP))
            		passageAnnee();
            	retour= true;
            	break;
            // Defier male Alpha dans meute
            case 21 : 	
            	action.casDefierAlpha();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.DEFIER_MALE_ALPHA))
            		passageAnnee();
            	retour= true;
            	break;
            // Passer en mode automatique
            case 98 :
            	VueGlobale.afficher("=== PASSAGE EN MODE AUTOMATIQUE ===");
            	Run.utilisateurControle=false;
            	ControllerGestionAuto.run(false);
            	retour = true;
            	break;
            // Exit
            case 99:
                retour= false;
                break;
            default:
            	VueGlobale.afficher("Choix invalide");
            	retour= true;
            }
        }
        catch (Exception e) {
    		VueGlobale.afficher(e.getMessage());
    	}
        return retour;
    }
    
    
    /**
     * Effectue toutes les actions qui doivent s'effectuer
     * lorsqu'on change d'année
     */
    public static void passageAnnee() {
    	HashSet<Creature> temp = null;
    	try {
    		VueGlobale.afficher("\n ====== FIN ANNEE ====== \n");
    		Evenements.evenementAnnuel();
			String chaine = controlPrincipal.nouvelleAnnee();
			if (chaine != null)
				VueGlobale.afficher(chaine);
            controlPrincipal.verificationEnfants();
            // Tri des clés
            for (Enclos e : zoo.getListeEnclos())
            	e.reorganiserCles();
            VueGlobale.afficher("\n ====== NOUVELLE ANNEE ====== \n");
            //Affichage des informations préoccupantes
            VueGlobale.afficher("LES ENCLOS EN MAUVAIS ETAT :");
            for (Enclos e : zoo.getEnclosMauvaisEtat())
            	VueGlobale.afficherEnclos(e, false);
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
            VueGlobale.afficher("\nLES CREATURES QUI ONT UN BESOIN :");
            for (Enclos e : zoo.getListeEnclos())
            	temp = e.getCreaturesAyantUnBesoin();
            if (temp!=null && temp.size()>0)
            	for (Creature c : temp)
            		VueGlobale.afficherCreature(c);
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	}
    	catch (Exception e) {
    		VueGlobale.afficher(e.getMessage());
    	}
    }

}
