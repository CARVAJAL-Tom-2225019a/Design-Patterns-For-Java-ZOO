package controllerApplication;

import base.Creature;
import base.Ovipare;
import creaturesImplemente.*;
import enclosImplemente.Enclos;
import main.Run;
import maitreZoo.MaitreZoo;
import references.CONSTANTES;
import viewApplication.*;
import zoo.ZooFantastique;

public class ControllerZoo {
	private static ControllerPrincipal controlPrincipal = new ControllerPrincipal();
	private static ControllerUserInterface controlUser = new ControllerUserInterface();
	// Instance de Vues
	private static VueGlobale VueGlobale;
    private static VueUtilisateur VueUtilisateur;
    private static VueAutomatique VueAutomatique;
    // Instance du gestionnaire du zoo (Singleton)
    private MaitreZoo maitreZoo = MaitreZoo.getInstance();
    // Instance du zoo fantastique (Singleton)
    private ZooFantastique zoo = ZooFantastique.getInstance();
    private int NB_ACTION_MAX ;
    private int nbAction ;
    private int annee;
    
    
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
        return NB_ACTION_MAX;
    }
    public int getNbAction() {
        return nbAction;
    }
    public int getAnnee() {
        return annee;
    }
    public int getActionRestante() {
        return NB_ACTION_MAX - nbAction;
    }

    
    /**
     * Initialise les variables du jeu.
     *
     */
    public void init() {
        // Initialisation des variables
    	NB_ACTION_MAX = CONSTANTES.NB_ACTION_MAX_USER;
        nbAction = 0;
        annee = 1;
        try {
        	// Affiche le message de bienvenue
            if (Run.UtilisateurControle) // gestion manuel
            	maitreZoo = VueUtilisateur.Bienvenue();
            else
            	maitreZoo = VueAutomatique.Bienvenue();
        }
        catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
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
            	VueGlobale.Afficher("\n ---- Pas d'action effectue ---- ");
            	retour= true;
            	break;
            // Voir les enclos
            case 1:
            	VueGlobale.Afficher("\n ---- Vous avez choisi de voir les enclos existants ---- ");
            	VueGlobale.Afficher(zoo.AfficherEnsembleZoo());
            	retour= true;
            	break;
            // Voir nombre total de créatures
            case 2:
            	VueGlobale.Afficher("\n ---- Voir le nombre de creatures totales ---- ");
            	VueGlobale.Afficher("Il y a " + zoo.getNbCreaturesTotales() + " créatures.");
            	retour= true;
            	break;
            // Examiner un enclos
            case 3:
            	CasExaminerEnclos();
                retour= true;
                break;
            // Nettoyer un enclos
            case 4:
            	CasNettoyerEnclos();
                retour= true;
                break;
            // Nourrir les créatures d'un enclos
            case 5:
            	CasNourrirEnclos();
                retour= true;
                break;
            // Transférer une créature
            case 6:
            	CasTransfererCreature();
            	retour= true;
            	break;
           // Concevoir un enfant
            case 7 :
            	CasConcevoirEnfant();
            	retour= true;
            	break;
            // Voir liste bebes en cosntruction
            case 8 :
            	VueGlobale.Afficher("\n ---- Voir les enfants qui vont bientot naitre ---- ");
            	VueGlobale.Afficher(zoo.AfficherFemellesEnceinte());
            	VueGlobale.Afficher(zoo.AfficherOeufs());
            	retour= true;
            	break;
            // Exit
            case 99:
                retour= false;
                break;
            default:
            	VueGlobale.Afficher("Choix invalide");
            	retour= true;
            }
        }
        catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
        return retour;
    }

    
    /**
     * Passe à la nouvelle année si le nombre d'actions atteint le maximum.
     */
    public void PassageAnnee() {
    	try {
    		VueGlobale.PassageAnnee();
            controlPrincipal.VerificationNaissances();
            zoo.ModificationEtatAleatoire();
            // Tri des clés
            for (Enclos e : zoo.GetListeEnclos())
            	e.reorganiserCles();
            VueGlobale.Afficher("\n ====== NOUVELLE ANNEE ====== \n");
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }

    
    /**
     * Exécute une année du jeu en incrémentant le nombre d'actions.
     *
     */
    public void runYear(){
    	try {
    		nbAction++;
            if (nbAction == NB_ACTION_MAX) {
                annee++;
                nbAction = 0;
                PassageAnnee();
            }
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
        
    }
    
    
    /**
     * Methodes appellé par le switch case
     */
    private void CasExaminerEnclos() {
    	Enclos enclos;
    	String nomEnclos;
    	try {
    		VueGlobale.Afficher("\n ---- Examiner un enclos ---- ");
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
        	}
        	else {
        		//TODO : gestion auto (recuperation info aleatoire)
        		enclos = new Enclos("afaire", 0, 0);
        	}	
            VueGlobale.Afficher(maitreZoo.ExaminerEnclos(enclos));
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
    private void CasNettoyerEnclos() {
    	Enclos enclos;
    	String nomEnclos;
    	try {
    		VueGlobale.Afficher("\n ---- Nettoyer un enclos ---- ");
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		//TODO : gestion auto (recuperation info aleatoire)
        		enclos = new Enclos("afaire", 0, 0);
        	}
            maitreZoo.NettoyerEnclos(enclos);
            VueGlobale.Afficher("NETTOYAGE FAIT\n" + enclos);
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}	
    }
    
    private void CasNourrirEnclos() {
    	Enclos enclos;
    	String nomEnclos;
    	try {
    		VueGlobale.Afficher("\n ---- Nourrir les creatures d'un enclos ---- ");
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		//TODO : gestion auto (recuperation info aleatoire)
        		enclos = new Enclos("afaire", 0, 0);
        	}
            maitreZoo.NourrirCreaturesEnclos(enclos);
            VueGlobale.Afficher("CRÉATURES ONT ÉTÉ NOURRIES");
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
    private void CasTransfererCreature() {
    	Enclos enclos;
    	Enclos enclosDest;
    	Creature creature;
    	String nomEnclos;
    	try {
    		VueGlobale.Afficher("\n ---- Transferer une creature ---- ");
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom enclos source : ");
            	enclos = zoo.trouverEnclosParNom(nomEnclos);
        		creature = controlUser.SelectionCreatureDansEnclos(enclos);
            	nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom enclos destination : ");
            	enclosDest = zoo.trouverEnclosParNom(nomEnclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		//TODO : gestion auto (recuperation info aleatoire)
        		enclos = new Enclos("afaire", 0, 0);
        		enclosDest = new Enclos("afaire", 0, 0);
        		creature = FactoryCreature.newCreature(null, null, 0, 0);
        	}
        	maitreZoo.TransfererCreature(creature, enclos, enclosDest);
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
    private void CasConcevoirEnfant() {
    	Enclos enclos;
    	Creature femelle;
    	Creature male;
    	String nomEnclos;
    	try {
    		VueGlobale.Afficher("\n ---- Concevoir un enfant ---- ");
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom enclos : ");
            	enclos = zoo.trouverEnclosParNom(nomEnclos);
            	VueGlobale.Afficher(enclos.toString() + "\n\nVeuillez selectionner une femelle puis un male\n");
            	// Femelle
            	femelle = controlUser.SelectionCreatureDansEnclos(enclos);
            	// Male
            	male = controlUser.SelectionCreatureDansEnclos(enclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		//TODO : gestion auto (recuperation info aleatoire)
        		enclos = new Enclos("afaire", 0, 0);
        		femelle = FactoryCreature.newCreature(null, null, 0, 0);
        		male = FactoryCreature.newCreature(null, null, 0, 0);
        	}
        	//Conception
        	int naitre = enclos.ConcevoirEnfant(femelle, male);
        	if (naitre==1) {
        		zoo.AddFemelleEnceinte(femelle);
        		VueGlobale.Afficher("Enfant en cours");
        	}
        	else if (naitre==2) {
        		Oeuf o = ((Ovipare)femelle).PondreOeuf(male, femelle.getDureePourEnfant());
        		zoo.AddOeuf(o);
        		VueGlobale.Afficher("Oeuf pondu");
        	}
        	else if (naitre==-1)
        		VueGlobale.Afficher("Impossible de concevoir");
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
}
