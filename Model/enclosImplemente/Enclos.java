package enclosImplemente;

import java.util.*;

import base.*;
import references.*;

public class Enclos {
	
	private Enum_Especes nomEspece;
	
	private String nom;
	private double superficie;
	private int nbMaxCreatures;
	private int nbCreatures;
	private TreeMap<Integer, Creature> listeCreatures;
	private Enum_DegrePropreteEnclos degreProprete;
	
	
	/**
	 * CONSTRUCTEUR
	 * @param nom
	 * @param superficie
	 * @param nbMaxCreatures
	 */
	public Enclos(String nom, double superficie, int nbMaxCreatures) {
		this.nomEspece = null;
		this.nom = nom;
		this.superficie = superficie;
		this.nbMaxCreatures = nbMaxCreatures;
		this.nbCreatures = 0;
		// Tri des creatures par age
		this.listeCreatures = new TreeMap<>();
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
	public TreeMap<Integer, Creature> getListeCreatures() {
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
	 * @throws Exception 
	 */
	public String creaturesMortes() throws Exception {
        String chaine = "Les creatures mortes dans " + nom + " :\n";
        Iterator<Map.Entry<Integer, Creature>> iterator = listeCreatures.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Creature> entry = iterator.next();
            Creature creature = entry.getValue();
            // Si la créature est morte
            if (!creature.isVivant()) {
                chaine += creature.toString();
                SupprimerCreature(creature);
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
			creature.Manger(CONSTANTES.MAX_INDICATEUR);
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
		// Copier les créatures dans une liste
        List<Creature> creaturesList = new ArrayList<>(listeCreatures.values());
        // Trier la liste par âge
        Collections.sort(creaturesList, Comparator.comparingInt(Creature::getAge));
        // Remettre les créatures triées dans la TreeMap
        TreeMap<Integer, Creature> nouvelleMap = new TreeMap<>();
        int nouvelleCle = 1;
        for (Creature creature : creaturesList) {
            nouvelleMap.put(nouvelleCle, creature);
            nouvelleCle++;
        }
        // Mettre à jour la listeCreatures et nbCreatures
        listeCreatures = nouvelleMap;
        nbCreatures = nouvelleCle - 1;
	}
	
	
	public void DegradationDegreProprete() {
		if (degreProprete == Enum_DegrePropreteEnclos.bon)
			degreProprete = Enum_DegrePropreteEnclos.correct;
		else if (degreProprete == Enum_DegrePropreteEnclos.correct)
			degreProprete = Enum_DegrePropreteEnclos.mauvais;
	}
	
	
	/**
     * Méthode pour sélectionner aléatoirement une créature en fonction du sexe.
     * 
     * @param sexe Le sexe de la créature souhaitée
     * @return La créature sélectionnée aléatoirement
     */
    public Creature selectionnerCreatureAleatoireParSexe(Enum_Sexe sexe) {
        // Créer une liste pour stocker les créatures correspondant au sexe spécifié
        List<Creature> creaturesDuSexe = new ArrayList<>();
        // Filtrer les créatures par sexe
        for (Creature creature : listeCreatures.values()) {
            if (creature.getSexe() == sexe) {
                creaturesDuSexe.add(creature);
            }
        }
        // Vérifier s'il y a des créatures du sexe spécifié
        if (!creaturesDuSexe.isEmpty()) {
            // Sélectionner aléatoirement une créature
            Random random = new Random();
            int indexAleatoire = random.nextInt(creaturesDuSexe.size());
            return creaturesDuSexe.get(indexAleatoire);
        } else {
            // Aucune créature du sexe spécifié trouvée
            return null;
        }
    }
    
    
    /**
     * Methode permettant de concevoir un enfant selon le type de creature
     * @throws Exception 
     */
    public int ConcevoirEnfant(Creature femelle, Creature male) throws Exception {
    	if (femelle.isVivant() && femelle instanceof Vivipare) {
    		((Vivipare)femelle).concevoirUnEnfant(male, femelle.getDureePourEnfant());
    		return 1;
    	}
    	else if (femelle.isVivant() && femelle instanceof Ovipare) {
    		
    		// TODO : eclore
    		return 2;
    	}
    	return -1;
    }
    
	
}
