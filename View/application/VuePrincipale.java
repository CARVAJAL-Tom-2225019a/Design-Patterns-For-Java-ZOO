package application;

import java.util.Scanner;
import ControllerApplication.ControllerPrincipal;
import maitreZoo.MaitreZoo;
import references.*;

public class VuePrincipale {
    // Scanner pour la saisie utilisateur
    private Scanner scanner = new Scanner(System.in);
    // Instance de CONSTANTES pour les références constantes
    private CONSTANTES constantes = new CONSTANTES();
    // Instance du contrôleur principal
    private static ControllerPrincipal control = new ControllerPrincipal();

    
    /**
     * Méthode pour afficher le message de bienvenue et initialiser le gestionnaire du zoo
     */
    public void Bienvenue() {
        System.out.println("======  BIENVENUE DANS VOTRE ZOO FANTASTIQUE  ======");

        // Saisie des informations du joueur
        String nom = DemandeUtilisateur("Votre nom : ");
        String s = DemandeUtilisateur("Votre sexe (M ou F) : ");
        Enum_Sexe sexe = (s.equals("M")) ? Enum_Sexe.Male : Enum_Sexe.Femelle;
        int age = Integer.parseInt(DemandeUtilisateur("Votre age : "));

        // Initialisation du gestionnaire du zoo
        MaitreZoo.getInstance(nom, sexe, age);

        // Message de bienvenue
        System.out.println("Vous êtes désormais maître de votre zoo. \nJe suis sûr que"
                + " vous serez un très bon gestionnaire ! Bonne chance " + nom);
        System.out.println("\n INFORMATION : La durée de vie d'une créature est de "
                + constantes.MAX_AGE);
    }

    
    /**
     * Méthode pour afficher le message de passage à la nouvelle année
     * et apeller le controlleur qui effectuera les actions
     */
    public void PassageAnnee() throws Exception {
        System.out.println("\n ====== FIN ANNEE ====== \n");
        System.out.println(control.NouvelleAnnee());
        System.out.println("\n ====== NOUVELLE ANNEE ====== \n");
    }

    
    /**
     * Méthode pour afficher les actions disponibles à l'utilisateur
     */
    public void proposerAction(int annee, int actionRestante) {
        System.out.println("\nLES ACTIONS DISPONIBLES (année " + annee + "):"
                + "\n  0 : Pas d'action"
                + "\n  1 : Voir les enclos"
                + "\n  2 : Voir le nombre de créatures totales"
                + "\n  3 : Examiner un enclos"
                + "\n  4 : Nettoyer un enclos"
                + "\n  5 : Nourrir les créatures d'un enclos"
                + "\n  6 : Transférer une créature"
                + "\n  99 : Exit"
                + "\n\n Il vous reste " + actionRestante + " action(s) à effectuer avant de changer d'année"
                + "\n\n Votre choix = ");
    }

    
    /**
     * Méthode pour récupérer le choix de l'utilisateur
     */
    public int RecupererChoixAction() {
        String s = scanner.nextLine();
        return Integer.parseInt(s);
    }

    
    /**
     * Méthode pour demander une saisie utilisateur avec un message donné
     */
    public String DemandeUtilisateur(String chaine) {
        System.out.println(chaine);
        return scanner.nextLine();
    }
}
