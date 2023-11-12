package ControllerApplication;

import application.VuePrincipale;
import enclosImplemente.Enclos;
import maitreZoo.MaitreZoo;
import references.CONSTANTES;
import zoo.ZooFantastique;

public class ControllerMaitreZoo {
    // Instance de CONSTANTES pour les références constantes
    CONSTANTES constantes = new CONSTANTES();
    // Instance de VuePrincipale pour l'interaction avec l'utilisateur
    VuePrincipale vue = new VuePrincipale();
    // Instance du gestionnaire du zoo (Singleton)
    MaitreZoo maitreZoo = MaitreZoo.getInstance();
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
                System.out.println("\n ---- Vous avez choisi de ne pas effectuer d'action ---- ");
                return true;

            // Voir les enclos
            case 1:
                System.out.println("\n ---- Vous avez choisi de voir les enclos existants ---- ");
                System.out.println(zoo.AfficherEnsembleZoo());
                return true;

            // Voir nombre total de créatures
            case 2:
                System.out.println("\n ---- Vous avez choisi de voir le nombre de créatures totales ---- ");
                System.out.println("Il y a " + zoo.getNbCreaturesTotales() + " créatures.");
                return true;

            // Examiner un enclos
            case 3:
                System.out.println("\n ---- Vous avez choisi d'examiner un enclos ---- ");
                nomEnclos = vue.DemandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
                System.out.println(maitreZoo.ExaminerEnclos(enclos));
                return true;

            // Nettoyer un enclos
            case 4:
                System.out.println("\n ---- Vous avez choisi de nettoyer un enclos ---- ");
                nomEnclos = vue.DemandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
                maitreZoo.NettoyerEnclos(enclos);
                System.out.println("NETTOYAGE FAIT\n" + enclos);
                return true;

            // Nourrir les créatures d'un enclos
            case 5:
                System.out.println("\n ---- Vous avez choisi de nourrir les créatures d'un enclos ---- ");
                nomEnclos = vue.DemandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
                maitreZoo.NourrirCreaturesEnclos(enclos);
                System.out.println("CRÉATURES ONT ÉTÉ NOURRIES");
                return true;

            // Transférer une créature
            case 6:
                System.out.println("\n ---- Vous avez choisi de transférer une créature ---- ");
                // TODO: Ajouter la logique de transfert
                return true;

            // Exit
            case 99:
                return false;

            default:
                System.out.println("Choix invalide");
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
        int actionRestante = 0;
        this.zoo = zoo;
        int choix = 0;
        boolean run = true;

        // Affiche le message de bienvenue
        vue.Bienvenue();
        // Boucle principale du menu utilisateur
        while (run) {
            actionRestante = nbActionMax - nbAction;
            vue.proposerAction(annee, actionRestante);
            choix = vue.RecupererChoixAction();
            run = effectuerAction(choix);
            nbAction++;

            // Vérification pour passage à l'année suivante
            if (nbAction == nbActionMax) {
                annee++;
                nbAction = 0;
                vue.PassageAnnee();
            }
        }
    }
}
