package meuteLycanthrope;

import creaturesImplemente.Lycanthrope;
import enclosImplemente.EnclosLycanthrope;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Classe représentatant l'unique colonie de lycanthrope du zoo
 */
public class ColonieLycanthrope {
	
	private static ColonieLycanthrope instance;

    // TODO : faire évoluer hiérarchie des meutes

    // TODO : générer hurlements aléatoires entre lycanthropes

    // TODO : faire vieillir certains lycanthropes

    // TODO : transformer quelques lycanthropes en humain

    private Set<Meute> listeMeutes;
    private Set<EnclosLycanthrope> listeEnclos;

    /**
     * Constructor privé pour le singleton
     */
    private ColonieLycanthrope() {
        listeMeutes = new HashSet<>();
        listeEnclos = new HashSet<>();
    }

    /**
     * Méthode statique pour obtenir l'instance unique de ColonieLycanthrope
     * 
     * @return L'instance de ColonieLycanthrope
     */
    public static ColonieLycanthrope getInstance() {
        if (instance == null) {
            instance = new ColonieLycanthrope();
        }
        return instance;
    }

    /**
     * Recuperer la liste des meutes de la colonie
     * @return un ensemble contenant les meutes
     */
    public Set<Meute> getListeMeutes() {
        return listeMeutes;
    }

    
    /**
     * Ajouter une meute à la colonie
     * 
     * @param m la meute à ajouter
     */
    public void addMeute(Meute m) {
        listeMeutes.add(m);
    }

    
    /**
     * Supprimer une meute
     * 
     * @param m la meute à supprimer
     * @throws Exception si la meute n'existe pas
     */
    public void removeMeute(Meute m) throws Exception {
        if (listeMeutes.contains(m)) {
            listeMeutes.remove(m);
        } else
            throw new Exception("Impossible de trouver la meute dans la colonnie");
    }
    
    /**
     * Ajouter un enclos
     * 
     * @param e L'enclos à ajouter à la colonie
     */
    public void addEnclos(EnclosLycanthrope e) {
        listeEnclos.add(e);
    }

    
    /**
     * Supprimer un enclos
     * 
     * @param e l'enclos à supprimer de la colonie
     * @throws Exception si l'enclos n'existe pas
     */
    public void removeEnclos(EnclosLycanthrope e) throws Exception {
        if (listeEnclos.contains(e)) {
            listeEnclos.remove(e);
        } else
            throw new Exception("Impossible de trouver enclos dans la colonnie");
    }

    
    /**
     * Voir les meutes
     * @return la liste des meutes
     */
    public String voirMeutes() {
        StringBuilder chaine = new StringBuilder("VOICI LES MEUTES :\n");
        for (Meute m : listeMeutes) {
            chaine.append("   - ").append(m.getNomMeute()).append("\n");
        }
        return chaine.toString();
    }

    
    /**
     * Verification si besoin nouvelle meute
     * @return La chaine contenant les meutes créées
     * @throws Exception  si problème lors de la recherche
     */
    public String verificationBesoinNouvelleMeute() throws Exception {
        StringBuilder chaine = new StringBuilder();
        for (EnclosLycanthrope e : listeEnclos) {
        	Meute m = e.isNecessiteNouvelleMeute();
            if (m!=null) {
            	addMeute(m);
                chaine.append("Nouvelle meute dans ").append(e.getNom()).append("\n");
            }
        }
        return chaine.toString();
    }
    

    /**
     * Verification saison des amours
     * 
     * @param dateActuelle sous forme de chaine de caractère (YYY-MM-JJ)
     * @return La liste des femelles encceintes
     * @throws Exception si problème lors de la vérification
     */
    public Set<Lycanthrope> verificationSaisonAmour(String dateActuelle) throws Exception {
        Set<Lycanthrope> listeFemelleEnceinte = new HashSet<>();
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

    
    /**
     * Méthode pour vider la colonie.
     */
	public void clear() {
		listeMeutes.clear();
		listeEnclos.clear();
	}

	
	/**
	 * Methode permettant de chercher une meute grace à son nom
	 * @param nomRecherche	Le nom de la meute recherché
	 * @return	La meute trouvé
	 * @throws Exception	Si la meute n'existe pas sous ce nom
	 */
	public Meute trouverMeuteParNom(String nomRecherche) throws Exception {
        for (Meute meute : listeMeutes) {
            if (meute.getNomMeute().equals(nomRecherche)) {
                return meute; // On a trouvé l'enclos
            }
        }
        throw new Exception ("Nom meute inccorrect");
	}
}
