package controllerApplication;

import applicationRun.Run;
import controllerTemps.Enum_ActionsPossibles;
import controllerTemps.GestionnaireTemps;
import base.Enclos;
import maitreZoo.MaitreZoo;
import references.CONSTANTES;
import viewApplication.*;
import zoo.ZooFantastique;

/**
 * Classe representant le controleur du zoo
 * Permet d'effectuer toutes les actions relatives a vie du zoo
 */
public class ControllerZoo {
	private static ControllerPrincipal controlPrincipal = new ControllerPrincipal();
	private static VueGlobale VueGlobale;
    private static VueUtilisateur VueUtilisateur;
    private static VueAutomatique VueAutomatique;
    // Instance du gestionnaire du zoo (Singleton)
    private MaitreZoo maitreZoo = MaitreZoo.getInstance();
    // Instance du zoo fantastique (Singleton)
    private static ZooFantastique zoo = ZooFantastique.getInstance();
    private int nbAction ;
    private int annee;
    private ControllerActions action = new ControllerActions();
    private GestionnaireTemps temps = GestionnaireTemps.getInstance();
    
    
    /**
     * Constrcuteur
     */
    public ControllerZoo() {
    	VueGlobale = new VueGlobale();
    	VueUtilisateur = new VueUtilisateur();
    	VueAutomatique = new VueAutomatique();
        new ControllerPrincipal();
    }
    
    
    /**
     * Getters
     */
    public int getNbActionMax() {
        return CONSTANTES.NB_ACTION_MAX_USER;
    }
    public int getNbAction() {
        return nbAction;
    }
    public int getAnnee() {
        return annee;
    }
    public int getActionRestante() {
        return CONSTANTES.NB_ACTION_MAX_USER - nbAction;
    }

    
    /**
     * Initialise les variables du jeu.
     *
     */
    public void init() {
        // Initialisation des variables
        nbAction = 0;
        annee = 1;
        try {
        	// Affiche le message de bienvenue
            if (Run.utilisateurControle) // gestion manuel
            	maitreZoo = VueUtilisateur.bienvenue();
            else
            	maitreZoo = VueAutomatique.bienvenue();
        }
        catch (Exception e) {
    		VueGlobale.afficher(e.getMessage());
    	}
    }

    
    /**
     * Effectue l'action correspondant au choix de l'utilisateur.
     *
     * @param choix Le choix de l'utilisateur.
     * @return true si l'action a été effectuée avec succès, false sinon.
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
            	VueGlobale.afficher(zoo.afficherEnsembleZoo());
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
            // Transférer une créature
            case 7:
            	action.casTransfererCreature();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.TRANSFERER_CREATURE))
            		passageAnnee();
            	retour= true;
            	break;
           // Transferer un enclos
            case 8 : 
            	action.casTransfererEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.TRANSFERER_ENCLOS))
            		passageAnnee();
            	retour = true;
            	break;
           // Concevoir un enfant
            case 9 :
            	action.casConcevoirEnfant();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.CONCEVOIR_ENFANT))
            		passageAnnee();
            	retour= true;
            	break;
            // Voir liste bebes en cosntruction
            case 10 :
            	VueGlobale.afficher("\n ---- Voir les enfants qui vont bientot naitre "+Enum_ActionsPossibles.VOIR_BEBES_EN_CONSTRUCTION.getDureeTotale()+" ---- ");
            	VueGlobale.afficher(zoo.afficherFemellesEnceinte());
            	VueGlobale.afficher(zoo.afficherOeufs());
            	if (temps.incrementerTemps(Enum_ActionsPossibles.VOIR_BEBES_EN_CONSTRUCTION))
            		passageAnnee();
            	retour= true;
            	break;
            // Mettre un enclos en mouvement
            case 11 :
            	action.casEnclosEnMouvement();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.METTRE_ENCLOS_EN_MOUVEMENT))
            		passageAnnee();
            	retour= true;
            	break;
            // Faire chanter un enclos
            case 12 : 
            	action.casChanterEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.FAIRE_CHANTER_ENCLOS))
            		passageAnnee();
            	retour= true;
            	break;
            // Voir les meutes
            case 15 : 
            	action.casVoirMeutes();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.VOIR_MEUTES))
            		passageAnnee();
            	retour= true;
            	break;
            // Voir les lycanthropes
            case 16 : 
            	action.casVoirLycanthropes();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.VOIR_LOUPS))
            		passageAnnee();
            	retour= true;
            	break;
            // Saison amour pour les lycanthropes
            case 17 : 
            	action.casSaisonAmourLycanthropes();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.SAISON_AMOUR_LOUPS))
            		passageAnnee();
            	retour= true;
            	break;
            //TODO : suite actions pour Lycanthropes (defier male alpha, jurler...)
            //TODO : autres actions pour creatures (par rapport sante notamment)
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
     * Passe à la nouvelle année si le nombre d'actions atteint le maximum.
     */
    public static void passageAnnee() {
    	String temp = null;
    	try {
    		VueGlobale.afficher("\n ====== FIN ANNEE ====== \n");
			String chaine = controlPrincipal.nouvelleAnnee();
			if (chaine != null)
				VueGlobale.afficher(chaine);
			
   

            controlPrincipal.verificationEnfants();
            zoo.ModificationEtatAleatoire();
            // Tri des clés
            for (Enclos e : zoo.getListeEnclos())
            	e.reorganiserCles();
            VueGlobale.afficher("\n ====== NOUVELLE ANNEE ====== \n");
            //Affichage des informations préoccupantes
            VueGlobale.afficher(zoo.AfficherEnclosMauvaisEtat());
            for (Enclos e : zoo.getListeEnclos())
            	temp = e.voirCreaturesAyantUnBesoin();
            if (temp != null)
            	 VueGlobale.afficher(temp);
    	}
    	catch (Exception e) {
    		VueGlobale.afficher(e.getMessage());
    	}
    }

    
    /**
     * Exécute une année du jeu en incrémentant le nombre d'actions.
     *
     */
    public void runYear(){
    	try {
    		nbAction++;
            if (nbAction == CONSTANTES.NB_ACTION_MAX_USER) {
                annee++;
                nbAction = 0;	
                passageAnnee();
                //Voir liste enclors apres chaque annee si gestion auto
                if (!Run.utilisateurControle) {
                	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
	            	effectuerAction(1);
                }
            }
    	}
    	catch (Exception e) {
    		VueGlobale.afficher(e.getMessage());
    	}
        
    }
}
