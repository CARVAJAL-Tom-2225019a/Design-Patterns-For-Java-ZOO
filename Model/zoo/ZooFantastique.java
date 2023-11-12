package zoo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import ControllerApplication.ControllerMaitreZoo;
import base.Creature;
import enclosImplemente.Aquarium;
import enclosImplemente.Enclos;
import enclosImplemente.Voliere;
import maitreZoo.MaitreZoo;
import references.Enum_DegrePropreteEnclos;

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
     * @throws Exception 
     * 
     */
    public void ModificationEtatAleatoire() throws Exception {
    	ModifAleatoireStatutCreature();
    	ModifAleatoireEtatEnclos();
    }
    
    
    /**
     * Méthode pour modifier aléatoirement l'état des créatures dans le zoo.
     * @throws Exception 
     */
    public void ModifAleatoireStatutCreature() throws Exception {
        Random random = new Random();
        // Nombre aléatoire d'enclos à modifier
        int nombreEnclosAModifier = random.nextInt(listeEnclos.size()-3) + 2;
        // Parcourir un nombre aléatoire d'enclos
        for (int i = 0; i < nombreEnclosAModifier; i++) {
            // Sélectionner un enclos au hasard
            int randomIndexEnclos = random.nextInt(listeEnclos.size());
            Enclos enclos = (Enclos) listeEnclos.toArray()[randomIndexEnclos];
         // Nombre aléatoire de créatures à modifier dans cet enclos
            int maxCreaturesAModifier = Math.max(0, enclos.getNbCreatures() - 2);
            int nombreCreaturesAModifier = (maxCreaturesAModifier >= 2) ? random.nextInt(maxCreaturesAModifier) + 2 : 0;

            // Obtenir la liste des créatures dans cet enclos
            Map<Integer, Creature> creatures = enclos.getListeCreatures();
            // Modifier aléatoirement l'état du nombre aléatoire de créatures
            for (int j = 0; j < nombreCreaturesAModifier; j++) {
                // Sélectionner une clé au hasard dans la Map
                int randomKeyCreature = (int) creatures.keySet().toArray()[random.nextInt(creatures.size())];
                Creature creature = creatures.get(randomKeyCreature);
                // Choisir aléatoirement le critère (sommeil, sante ou faim)
                int critereAleatoire = random.nextInt(3);
                switch (critereAleatoire) {
                    case 0:
                        creature.PerdreSommeil();
                        break;
                    case 1:
                        creature.PerdreSante();
                        break;
                    case 2:
                        creature.PerdreNourriture();
                        break;
                    // Ajoutez d'autres cas selon vos besoins
                }
            }
        }
    }

    
    /**
     * Méthode pour effectuer des modifications aléatoires sur l'état des enclos
     */
    private void ModifAleatoireEtatEnclos() {
        Random random = new Random();

        // Parcourir la liste des enclos
        for (Enclos enclos : listeEnclos) {
            if (enclos instanceof Aquarium) {
                // Si c'est un Aquarium, modifier NiveauEau et SaliniteEau
                Aquarium aquarium = (Aquarium) enclos;
                aquarium.DegradationNiveauEau();
                aquarium.DegradationSaliniteEau();
            } else if (enclos instanceof Voliere) {
                // Si c'est une Voliere, modifier degreProprete et etatToit
                Voliere voliere = (Voliere) enclos;
                voliere.DegradationDegreProprete();
                voliere.DegradationEtatToit();
            } else {
                // Sinon (enclos classique), modifier degreProprete
                enclos.DegradationDegreProprete();
            }
        }
    }

}
