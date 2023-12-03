package meuteLycanthrope;

import java.util.HashSet;
import java.util.Iterator;
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
     * Constructor privé pour le singleton
     */
    private ColonieLycanthrope() {
        listeMeutes = new HashSet<Meute>();
        listeEnclos = new HashSet<EnclosLycanthrope>();
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
     */
    public void addEnclos(EnclosLycanthrope e) {
        listeEnclos.add(e);
    }

    
    /**
     * Supprimer un enclos
     * 
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
        String chaine = "VOICI LES MEUTES :\n";
        for (Meute m : listeMeutes) {
            chaine += "   - "+m.getNomMeute()+"\n";
        }
        return chaine;
    }

    
    /**
     * Verification si besoin nouvelle meute
     * @return La chaine contenant les meutes créées
     * @throws Exception 
     */
    public String verificationBesoinNouvelleMeute() throws Exception {
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
     * @return La liste des femelles encceintes
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
		Iterator<Meute> iterator = listeMeutes.iterator();
        while (iterator.hasNext()) {
            Meute meute = iterator.next();
            if (meute.getNomMeute().equals(nomRecherche)) {
                return meute; // On a trouvé l'enclos
            }
        }
        throw new Exception ("Nom meute inccorrect");
	}
}
