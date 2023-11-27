package controllerApplication;

import viewApplication.VueGlobale;
import viewApplication.VueUtilisateur;
import base.*;
import maitreZoo.MaitreZoo;
import references.CONSTANTES;
import zoo.ZooFantastique;

public class ControllerMaitreZoo {
    // Instance de CONSTANTES pour les références constantes
    CONSTANTES constantes = new CONSTANTES();
    // Instance de VuePrincipale pour l'interaction avec l'utilisateur
    VueGlobale vue = new VueGlobale();
    VueUtilisateur vueUtilisateur = new VueUtilisateur();
    // Instance du gestionnaire du zoo (Singleton)
    MaitreZoo maitreZoo;
    // Instance du zoo fantastique (Singleton)
    ZooFantastique zoo;

    private int nbActionMax;
    private int nbAction;
    private int annee;

    /**
     * Getters
     */
    public int getNbActionMax() {
        return nbActionMax;
    }
    public int getNbAction() {
        return nbAction;
    }
    public int getAnnee() {
        return annee;
    }

    
    /**
     * Méthode pour effectuer une action en fonction du choix de l'utilisateur
     */
    public boolean effectuerAction(int choix) throws Exception {
        String nomEnclos;
        Enclos enclos;

        switch (choix) {
            // Ne pas effectuer d'action
            case 0:
            	vue.afficher("\n ---- Vous avez choisi de ne pas effectuer d'action ---- ");
                return true;

            // Voir les enclos
            case 1:
            	vue.afficher("\n ---- Vous avez choisi de voir les enclos existants ---- ");
            	vue.afficher(zoo.afficherEnsembleZoo());
                return true;

            // Voir nombre total de créatures
            case 2:
            	vue.afficher("\n ---- Vous avez choisi de voir le nombre de creatures totales ---- ");
            	vue.afficher("Il y a " + zoo.getNbCreaturesTotales() + " créatures.");
                return true;

            // Examiner un enclos
            case 3:
            	vue.afficher("\n ---- Vous avez choisi d'examiner un enclos ---- ");
                nomEnclos = vueUtilisateur.demandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
                vue.afficher(maitreZoo.examinerEnclos(enclos));
                return true;

            // Nettoyer un enclos
            case 4:
            	vue.afficher("\n ---- Vous avez choisi de nettoyer un enclos ---- ");
                nomEnclos = vueUtilisateur.demandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
                maitreZoo.nettoyerEnclos(enclos);
                vue.afficher("NETTOYAGE FAIT\n" + enclos);
                return true;

            // Nourrir les créatures d'un enclos
            case 5:
            	vue.afficher("\n ---- Vous avez choisi de nourrir les creatures d'un enclos ---- ");
                nomEnclos = vueUtilisateur.demandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
                maitreZoo.nourrirCreaturesEnclos(enclos);
                vue.afficher("CRÉATURES ONT ÉTÉ NOURRIES");
                return true;

            // Transférer une créature
            case 6:
            	vue.afficher("\n ---- Vous avez choisi de transferer une creature ---- ");
            	String nomE = vueUtilisateur.demandeUtilisateur("Nom enclos source : ");
            	Enclos enclosSource = zoo.trouverEnclosParNom(nomE);
            	vue.afficher(enclosSource.toString());
            	String indexCreatureString = vueUtilisateur.demandeUtilisateur("Index creature : ");
            	int indexCreature = Integer.parseInt(indexCreatureString);
            	Creature creature = enclosSource.getListeCreatures().get(indexCreature);
            	nomE = vueUtilisateur.demandeUtilisateur("Nom enclos destination : ");
            	Enclos enclosDest = zoo.trouverEnclosParNom(nomE);
            	maitreZoo.transfererCreature(creature, enclosSource, enclosDest);
            	enclosSource.reorganiserCles();
            	enclosDest.reorganiserCles();
                return true;

            // Exit
            case 99:
                return false;

            default:
            	vue.afficher("Choix invalide");
                return true;
        }
    }

    
    /**
     * Méthode principale pour exécuter le menu utilisateur
     */
    public void runUserMenu(ZooFantastique zoo) throws Exception {
        // Initialisation des variables
        nbActionMax = 6;
        nbAction = 0;
        annee = 1;
        this.zoo = zoo;
        int choix = 0;
        boolean run = true;

        // Affiche le message de bienvenue
        maitreZoo = vueUtilisateur.bienvenue();
        // Boucle principale du menu utilisateur
        while (run) {
            vueUtilisateur.proposerAction();
            choix = vueUtilisateur.recupererChoixAction();
            run = effectuerAction(choix);
            nbAction++;

            // Vérification pour passage à l'année suivante
            if (nbAction == nbActionMax) {
                annee++;
                nbAction = 0;
                ControllerZoo.passageAnnee();
                zoo.ModificationEtatAleatoire();
            }
        }
    }
}
