package ControllerApplication;

import application.VueUtilisateur;
import base.Creature;
import enclosImplemente.Enclos;
import maitreZoo.MaitreZoo;
import zoo.ZooFantastique;

public class ControllerZoo {
	// Instance de VuePrincipale pour l'interaction avec l'utilisateur
    private static VueUtilisateur vue;
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
        ControllerZoo.vue = new VueUtilisateur();
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
     * @throws Exception si une erreur survient pendant l'initialisation.
     */
    public void init() throws Exception {
        // Initialisation des variables
    	NB_ACTION_MAX = 6;
        nbAction = 0;
        annee = 1;

        // Affiche le message de bienvenue
        maitreZoo = vue.Bienvenue();
    }

    
    /**
     * Effectue l'action correspondant au choix de l'utilisateur.
     *
     * @param choix Le choix de l'utilisateur.
     * @return true si l'action a été effectuée avec succès, false sinon.
     * @throws Exception si une erreur survient pendant l'exécution de l'action.
     */
    public boolean effectuerAction(int choix) throws Exception {
        String nomEnclos;
        Enclos enclos;

        switch (choix) {
        // Ne pas effectuer d'action
        case 0:
        	vue.Afficher("\n ---- Vous avez choisi de ne pas effectuer d'action ---- ");
            return true;

        // Voir les enclos
        case 1:
        	vue.Afficher("\n ---- Vous avez choisi de voir les enclos existants ---- ");
        	vue.Afficher(zoo.AfficherEnsembleZoo());
            return true;

        // Voir nombre total de créatures
        case 2:
        	vue.Afficher("\n ---- Vous avez choisi de voir le nombre de creatures totales ---- ");
        	vue.Afficher("Il y a " + zoo.getNbCreaturesTotales() + " créatures.");
            return true;

        // Examiner un enclos
        case 3:
        	vue.Afficher("\n ---- Vous avez choisi d'examiner un enclos ---- ");
            nomEnclos = vue.DemandeUtilisateur("Nom de l'enclos : ");
            enclos = zoo.trouverEnclosParNom(nomEnclos);
            vue.Afficher(maitreZoo.ExaminerEnclos(enclos));
            return true;

        // Nettoyer un enclos
        case 4:
        	vue.Afficher("\n ---- Vous avez choisi de nettoyer un enclos ---- ");
            nomEnclos = vue.DemandeUtilisateur("Nom de l'enclos : ");
            enclos = zoo.trouverEnclosParNom(nomEnclos);
            maitreZoo.NettoyerEnclos(enclos);
            vue.Afficher("NETTOYAGE FAIT\n" + enclos);
            return true;

        // Nourrir les créatures d'un enclos
        case 5:
        	vue.Afficher("\n ---- Vous avez choisi de nourrir les creatures d'un enclos ---- ");
            nomEnclos = vue.DemandeUtilisateur("Nom de l'enclos : ");
            enclos = zoo.trouverEnclosParNom(nomEnclos);
            maitreZoo.NourrirCreaturesEnclos(enclos);
            vue.Afficher("CRÉATURES ONT ÉTÉ NOURRIES");
            return true;

        // Transférer une créature
        case 6:
        	vue.Afficher("\n ---- Vous avez choisi de transferer une creature ---- ");
        	String nomE = vue.DemandeUtilisateur("Nom enclos source : ");
        	Enclos enclosSource = zoo.trouverEnclosParNom(nomE);
        	vue.Afficher(enclosSource.toString());
        	String indexCreatureString = vue.DemandeUtilisateur("Index creature : ");
        	int indexCreature = Integer.parseInt(indexCreatureString);
        	Creature creature = enclosSource.getListeCreatures().get(indexCreature);
        	nomE = vue.DemandeUtilisateur("Nom enclos destination : ");
        	Enclos enclosDest = zoo.trouverEnclosParNom(nomE);
        	maitreZoo.TransfererCreature(creature, enclosSource, enclosDest);
        	enclosSource.reorganiserCles();
        	enclosDest.reorganiserCles();
            return true;
        	
        // Exit
        case 99:
            return false;

        default:
        	vue.Afficher("Choix invalide");
            return true;
        }
    }

    
    /**
     * Passe à la nouvelle année si le nombre d'actions atteint le maximum.
     *
     * @throws Exception si une erreur survient pendant le passage à la nouvelle année.
     */
    public void PassageAnnee() throws Exception {
        vue.PassageAnnee();
        zoo.ModificationEtatAleatoire();
    }

    
    /**
     * Exécute une année du jeu en incrémentant le nombre d'actions.
     *
     * @throws Exception si une erreur survient pendant l'exécution de l'année.
     */
    public void runYear() throws Exception {
        nbAction++;
        if (nbAction == NB_ACTION_MAX) {
            annee++;
            nbAction = 0;
            PassageAnnee();
        }
    }
}
