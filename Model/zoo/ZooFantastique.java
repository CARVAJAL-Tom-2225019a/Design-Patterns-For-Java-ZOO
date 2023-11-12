package zoo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ControllerApplication.ControllerMaitreZoo;
import enclosImplemente.Enclos;
import maitreZoo.MaitreZoo;

public class ZooFantastique {

    // Instance singleton du zoo fantastique
    private static ZooFantastique instance;
    // Nom du zoo
    private String nom;
    // Gestionnaire du zoo (MaitreZoo)
    private MaitreZoo maitreZoo;
    // Nombre maximal d'enclos dans le zoo
    private int nbMaxEnclos;
    // Liste des enclos dans le zoo
    private Set<Enclos> listeEnclos;

    // Constructeur privé pour empêcher l'instanciation directe
    private ZooFantastique() {
        this.nom = "Le Zoo Fantastique";
        this.maitreZoo = null;
        this.nbMaxEnclos = 10;
        this.listeEnclos = new HashSet<>();
    }
    // Méthode pour obtenir l'instance unique du zoo fantastique (Singleton)
    public static ZooFantastique getInstance() {
        if (instance == null) {
            instance = new ZooFantastique();
        }
        return instance;
    }

    
    /**
     * Getters
     */
    public Set<Enclos> GetListeEnclos() {
        return listeEnclos;
    }

    
    /**
     * Méthode pour ajouter un enclos au zoo
     */
    public void AddEnclos(Enclos enclos) {
        if (listeEnclos.size() < nbMaxEnclos) {
            listeEnclos.add(enclos);
        }
    }

    
    /**
     * Méthode pour obtenir le nombre total de créatures dans le zoo
     */
    public int getNbCreaturesTotales() {
        int somme = 0;
        for (Enclos e : listeEnclos) {
            somme += e.getNbCreatures();
        }
        return somme;
    }

    
    /**
     * Méthode pour afficher les informations sur l'ensemble du zoo
     */
    public String AfficherEnsembleZoo() {
        StringBuilder chaine = new StringBuilder("\n==== VOICI " + nom + " ==== \n\n");
        for (Enclos e : listeEnclos) {
            chaine.append(" - ").append(e.getNom()).append(" avec ").append(e.getNbCreatures())
                    .append(" creatures. Degre proprete : ").append(e.getDegreProprete()).append("\n");
        }
        return chaine.toString();
    }

    
    /**
     * Méthode pour trouver un enclos par son nom
     */
    public Enclos trouverEnclosParNom(String nomRecherche) {
        Iterator<Enclos> iterator = listeEnclos.iterator();
        while (iterator.hasNext()) {
            Enclos enclos = iterator.next();
            if (enclos.getNom().equals(nomRecherche)) {
                return enclos; // On a trouvé l'enclos
            }
        }
        return null; // Aucun enclos trouvé avec le nom spécifié
    }

    
    /**
     * Méthode pour passer la main à l'utilisateur via le contrôleur MaitreZoo
     */
    public void PasserLaMainUtilisateur() throws Exception {
        ControllerMaitreZoo menuUtilisateur = new ControllerMaitreZoo();
        menuUtilisateur.runUserMenu(this);
    }

    /**
     * Methode pour modification aleatoire a intervalles
     * regulires de l'etat du zoo
     * 
     */
    //TODO : a utiliser
    public void ModificationEtat() {
    	ModifAleatoireStatutCreature();
    	ModifAleatoireEtatEnclos();
    }
    
    /**
     * Méthode pour effectuer des modifications aléatoires sur le statut des créatures
     */
    private void ModifAleatoireStatutCreature() {
        // TODO: Implémenter les modifications aléatoires sur le statut des créatures
        // à un intervalle régulier
    }

    
    /**
     * Méthode pour effectuer des modifications aléatoires sur l'état des enclos
     */
    private void ModifAleatoireEtatEnclos() {
        // TODO: Implémenter les modifications aléatoires sur l'état des enclos
    }
}
