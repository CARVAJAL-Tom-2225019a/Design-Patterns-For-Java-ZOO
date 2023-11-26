package meuteLycanthrope;

import java.util.HashSet;
import java.util.Set;

import creaturesImplemente.Lycanthrope;
import enclosImplemente.EnclosLycanthrope;

public class ColonieLycanthrope {
	
	private static ColonieLycanthrope instance;

    // TODO : faire évoluer hiérarchie des meutes

    // TODO : générer hurlements aléatoires entre lycanthropes

    // TODO : faire vieillir certains lycanthropes

    // TODO : transformer quelques lycanthropes en humain

    private Set<Meute> listeMeutes;
    private Set<EnclosLycanthrope> listeEnclos;

    /**
     * Constructor privé pour le singleton.
     */
    private ColonieLycanthrope() {
        listeMeutes = new HashSet<Meute>();
        listeEnclos = new HashSet<EnclosLycanthrope>();
    }

    /**
     * Méthode statique pour obtenir l'instance unique de ColonieLycanthrope.
     * 
     * @return L'instance de ColonieLycanthrope.
     */
    public static ColonieLycanthrope getInstance() {
        if (instance == null) {
            instance = new ColonieLycanthrope();
        }
        return instance;
    }

    /**
     * Getters
     */
    public Set<Meute> getListeMeutes() {
        return listeMeutes;
    }

    /**
     * Ajouter une meute
     */
    public void addMeute(Meute m) {
        listeMeutes.add(m);
    }

    /**
     * Supprimer une meute
     * 
     * @throws Exception
     */
    public void removeMeute(Meute m) throws Exception {
        if (listeMeutes.contains(m)) {
            listeMeutes.remove(m);
        } else
            throw new Exception("Impossible de trouver la meute dans la colonnie");
    }

    /**
     * Ajouter un enclos
     */
    public void addEnclos(EnclosLycanthrope e) {
        listeEnclos.add(e);
    }

    /**
     * Supprimer un enclos
     * 
     * @throws Exception
     */
    public void removeEnclos(EnclosLycanthrope e) throws Exception {
        if (listeEnclos.contains(e)) {
            listeEnclos.remove(e);
        } else
            throw new Exception("Impossible de trouver enclos dans la colonnie");
    }

    /**
     * Voir les meutes
     */
    public String voirMeutes() {
        String chaine = "VOICI LES MEUTES :\n";
        for (Meute m : listeMeutes) {
            chaine += m.toString();
        }
        return chaine;
    }

    /**
     * Voir l'ensemble des lycanthropes
     */
    public String voirLycanthropes() {
        String chaine = "VOICI LES LYCANTHROPES :\n";
        for (Meute m : listeMeutes) {
            for (Lycanthrope l : m.getListeLoup())
                chaine += l.toStringReduit();
        }
        return chaine;
    }

    /**
     * Verification si besoin nouvelle meute
     */
    public String verificationBesoinNouvelleMeute() {
        String chaine = "";
        for (EnclosLycanthrope e : listeEnclos) {
        	Meute m = e.isNecessiteNouvelleMeute();
            if (m!=null) {
            	addMeute(m);
                chaine += "Nouvelle meute dans " + e.getNom() + "\n";
            }
        }
        return chaine;
    }

    /**
     * Verification saison des amours
     * 
     * @return
     * @throws Exception
     */
    public Set<Lycanthrope> verificationSaisonAmour(String dateActuelle) throws Exception {
        Set<Lycanthrope> listeFemelleEnceinte = new HashSet<Lycanthrope>();
        int moisActuel = Integer.parseInt(dateActuelle.split("-")[1]);
        // Saison des amours de de mai à juillet
        if (moisActuel >= 5 && moisActuel <= 7) {
            // reproduction couple alpha
            for (Meute m : listeMeutes) {
                m.getCoupleAlpha().seReproduire();
                if (m.getCoupleAlpha().getFemelleAlpha().getNbJourConceptionRestantAvantMiseABas() > 0)
                    listeFemelleEnceinte.add(m.getCoupleAlpha().getFemelleAlpha());
            }
        }
        return listeFemelleEnceinte;
    }
}
