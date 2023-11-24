package controllerApplication;

import base.Enclos;
import controllerTemps.Enum_ActionsPossibles;
import controllerTemps.GestionnaireTemps;
import main.Run;
import maitreZoo.MaitreZoo;
import viewApplication.*;
import zoo.ZooFantastique;

/**
 * Classe representant le controleur du zoo
 * Permet d'effectuer toutes les actions relatives a vie du zoo
 */
public class ControllerZoo {
	private static ControllerPrincipal controlPrincipal = new ControllerPrincipal();
	private ControllerActions action;
	// Instance de Vues
	private static VueGlobale vueGlobale;
    private static VueUtilisateur vueUtilisateur;
    private static VueAutomatique vueAutomatique;
    // Instance du zoo fantastique (Singleton)
    private ZooFantastique zoo = ZooFantastique.getInstance();
    // Instance pour la gestion du temps
	private GestionnaireTemps temps = GestionnaireTemps.getInstance();
    
    
    /**
     * Constrcuteur
     */
    public ControllerZoo() {
    	vueGlobale = new VueGlobale();
    	vueUtilisateur = new VueUtilisateur();
    	vueAutomatique = new VueAutomatique();
    	action = new ControllerActions();
        new ControllerPrincipal();
    }

    
    /**
     * Initialise les variables du jeu.
     *
     */
    @SuppressWarnings("unused")
	public void init() {
        try {
        	MaitreZoo maitreZoo = MaitreZoo.getInstance();
			// Affiche le message de bienvenue
            if (Run.utilisateurControle) // gestion manuel
            	maitreZoo  = vueUtilisateur.Bienvenue();
            else
            	maitreZoo = vueAutomatique.Bienvenue();
        }
        catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    }
    
    public void isChangementAnnee(boolean changement) {
    	if(changement)
    		passageAnnee();
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
            	vueGlobale.Afficher("\n ---- Pas d'action effectue ("+Enum_ActionsPossibles.PAS_D_ACTION.getDureeTotale()+") ---- ");
            	if (temps.incrementerTemps(Enum_ActionsPossibles.PAS_D_ACTION))
        			passageAnnee();
            	retour= true;
            	break;
            // Voir les enclos
            case 1:
            	vueGlobale.Afficher("\n ---- Voir les enclos existants ("+Enum_ActionsPossibles.VOIR_ENCLOS_EXISTANTS.getDureeTotale()+") ---- ");
            	vueGlobale.Afficher(zoo.afficherEnsembleZoo());
            	if (temps.incrementerTemps(Enum_ActionsPossibles.VOIR_ENCLOS_EXISTANTS))
        			passageAnnee();
            	retour= true;
            	break;
            // Voir nombre total de créatures
            case 2:
            	vueGlobale.Afficher("\n ---- Voir le nombre de creatures totales ("+Enum_ActionsPossibles.VOIR_NOMBRE_CREATURES_TOTALES.getDureeTotale()+") ---- ");
            	vueGlobale.Afficher("Il y a " + zoo.getNbCreaturesTotales() + " creatures.");
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
            	vueGlobale.Afficher("\n ---- Voir les enfants qui vont bientot naitre ---- ");
            	vueGlobale.Afficher(zoo.afficherFemellesEnceinte());
            	vueGlobale.Afficher(zoo.afficherOeufs());
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
            // Faire dormir un enclos
            case 13 :
            	action.casDormirEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.DORMIR_ENCLOS))
            		passageAnnee();
            	retour= true;
            	break;
            // Reveiller un enclos
            case 14 :
            	action.casReveillerEnclos();
            	if (temps.incrementerTemps(Enum_ActionsPossibles.REVEILLER_ENCLOS))
            		passageAnnee();
            	retour= true;
            	break;
            // Exit
            case 99:
                retour= false;
                break;
            default:
            	vueGlobale.Afficher("Choix invalide");
            	retour= true;
            }
        }
        catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
        return retour;
    }

    
    /**
     * Informations quand changement annee
     */
    public void passageAnnee() {
    	String temp = null;
    	try {
    		vueGlobale.PassageAnnee();
            controlPrincipal.verificationNaissances();
            zoo.modificationEtatAleatoire();
            // Tri des clés
            for (Enclos e : zoo.getListeEnclos())
            	e.reorganiserCles();
            vueGlobale.Afficher("\n ====== NOUVELLE ANNEE ====== \n");
            //Affichage des informations préoccupantes
            vueGlobale.Afficher(zoo.afficherEnclosMauvaisEtat());
            for (Enclos e : zoo.getListeEnclos())
            	temp = e.voirCreaturesAyantUnBesoin();
            if (temp != null)
            	 vueGlobale.Afficher(temp);
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    }
    
}
