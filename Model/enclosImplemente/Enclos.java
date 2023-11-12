package enclosImplemente;

import java.util.*;

import base.*;
import references.*;

public class Enclos {
	CONSTANTES constantes = new CONSTANTES();
	
	private Enum_Especes nomEspece;
	
	private String nom;
	private double superficie;
	private int nbMaxCreatures;
	private int nbCreatures;
	private Map<Integer, Creature> listeCreatures;
	private Enum_DegrePropreteEnclos degreProprete;
	
	
	public Enclos(String nom, double superficie, int nbMaxCreatures) {
		this.nomEspece = null;
		this.nom = nom;
		this.superficie = superficie;
		this.nbMaxCreatures = nbMaxCreatures;
		this.nbCreatures = 0;
		this.listeCreatures = new HashMap<>();
		this.degreProprete = Enum_DegrePropreteEnclos.bon;
	}

	/**
	 * Getters
	 */
	public Enum_Especes getNomEspece() {
		return nomEspece;
	}
	public String getNom() {
		return nom;
	}
	public double getSuperficie() {
		return superficie;
	}
	public int getNbMaxCreatures() {
		return nbMaxCreatures;
	}
	public int getNbCreatures() {
		return nbCreatures;
	}
	public Map<Integer, Creature> getListeCreatures() {
		return listeCreatures;
	}
	public Enum_DegrePropreteEnclos getDegreProprete() {
		return degreProprete;
	}
	
	
	/**
	 * Methode permettant d'afficher les caracteristiques de l'enclos 
	 * et les creatures qu'il contient
	 * 
	 * @return la chaine de caractère contenant les informations
	 */
	public String toString() {
		String chaine = "Enclos "+nom+" de superficie "+superficie+" pouvant contenir au "
				+ "plus "+nbMaxCreatures+".\n Il y a actuellement "+nbCreatures+" creatures.\n"
				+ "Degre de proprete : "+degreProprete+"\n";
		for (Creature creature : listeCreatures.values()) {
			// si la creature est vivante
			if (creature.isVivant())
				chaine+="Index : "+ trouverCleParCreature(creature)+"\n";
				chaine+= creature.toString();
		}
		return chaine;
	}
	
	/**
	 * Methode permettant d'afficher les creatures qui ne sont plus en vie
	 * et de les supprimer de l'enclos
	 * 
	 * @return la chaine de caractère contenant les informations
	 */
	public String creaturesMortes() {
        String chaine = "Les creatures mortes dans " + nom + " :\n";
        Iterator<Map.Entry<Integer, Creature>> iterator = listeCreatures.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Creature> entry = iterator.next();
            Creature creature = entry.getValue();
            // Si la créature est morte
            if (!creature.isVivant()) {
                chaine += creature.toString();
                iterator.remove();
            }
        }
        return chaine;
    }	
	
	/**
	 * Methode pour ajouter une creature dans l'enclos
	 * 
	 * @param creature	la creature a ajouter
	 * @throws Exception si l'enclos est plein
	 * 
	 */
	public void AjouterCreature (Creature creature) throws Exception {
		// si 1er animal
		if (nbCreatures==0)
			nomEspece = creature.getNomEspece();
		// Verification qu'il reste de la place, et que meme espece
		if (nbCreatures < nbMaxCreatures && nomEspece==creature.getNomEspece()) {
			nbCreatures++;
			listeCreatures.put(nbCreatures, creature);
			
		}
		else 
			throw new Exception ("Ajout impossible si enclos plein ou si une autre espece est presente");
	}
	
	
	/**
	 * Methode pour supprimer une creature dans l'enclos
	 * 
	 * @param creature	la creature a supprimer
	 * @throws Exception si la creature n'est pas trouve
	 * 
	 */
	public void SupprimerCreature (Creature creature) throws Exception {
		// Vérification de la présence de la creature
        if (listeCreatures.containsValue(creature)) {
        	listeCreatures.remove(trouverCleParCreature(creature));
        	nbCreatures--;
        	
        	// si enclos vide
        	if(nbCreatures==0)
        		nomEspece = null;
		}
		else 
			throw new Exception ("Creature introuvable");
	}
	
	
	/**
	 * Methode pour nourrir les creatures
	 * 
	 */
	public void NourrirCreatures () throws Exception {
		for (Creature creature : listeCreatures.values()) {
			creature.Manger(constantes.MAX_INDICATEUR);
		}
	}
	
	
	/**
	 * Methode pour entrenir l'enclos
	 * 
	 * @throws Exception si l'enclos n'est pas sale ou s'il est vide
	 * 
	 */
	public void EntretenirEnclos () throws Exception {
		// Verification enclos sale
		if (degreProprete != Enum_DegrePropreteEnclos.bon) {
			// Verification enclos vide
			if (nbCreatures == 0) {
				// Nettoyage
				degreProprete = Enum_DegrePropreteEnclos.bon;
			}
			else
				throw new Exception ("Il y a des creatures dans l'enclos, impossible de le nettoyer");
		}
		else 
			throw new Exception ("Enclos n'a pas besoin d etre nettoye");
	}
	
	
	
	public int trouverCleParCreature(Creature creature) {
        for (Map.Entry<Integer, Creature> entry : listeCreatures.entrySet()) {
            if (entry.getValue().equals(creature)) {
                return entry.getKey(); // Clé trouvée
            }
        }
        return -1; // Aucune clé trouvée pour la créature spécifiée
    }
	
	
	public void reorganiserCles() {
        Map<Integer, Creature> nouvelleMap = new HashMap<>();
        int nouvelleCle = 1;

        for (Creature creature : listeCreatures.values()) {
            nouvelleMap.put(nouvelleCle, creature);
            nouvelleCle++;
        }

        listeCreatures = nouvelleMap;
        nbCreatures = nouvelleCle-1; // Mettre à jour la prochaine clé disponible
    }
	
	
	public void DegradationDegreProprete() {
		if (degreProprete == Enum_DegrePropreteEnclos.bon)
			degreProprete = Enum_DegrePropreteEnclos.correct;
		else if (degreProprete == Enum_DegrePropreteEnclos.correct)
			degreProprete = Enum_DegrePropreteEnclos.mauvais;
	}
	
}
