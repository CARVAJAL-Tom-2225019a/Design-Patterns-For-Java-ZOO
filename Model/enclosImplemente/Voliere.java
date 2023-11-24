package enclosImplemente;

import base.*;
import interfaces.CreatureVolante;
import references.*;

/**
 * La classe Voliere représente une volière, 
 * une extension spécifique de la classe Enclos,
 * destinée à accueillir des créatures volantes.
 */
public class Voliere extends Enclos {

    private double hauteur;
    private Enum_DegrePropreteEnclos etatToit;

    
    /**
     * Constructeur de la classe Voliere.
     *
     * @param nomEspece       Le nom de l'espèce hébergée dans la volière.
     * @param nom             Le nom de la volière.
     * @param superficie      La superficie de la volière.
     * @param nbMaxCreatures  Le nombre maximum de créatures que la volière peut accueillir.
     * @param hauteur         La hauteur de la volière.
     */
    public Voliere(String nom, double superficie, double hauteur) {
        super(nom, superficie);
        this.hauteur = hauteur;
        etatToit = Enum_DegrePropreteEnclos.bon;
    }
    
    
    /*
     * Getters
     */
    public double getHauteur() {
    	return hauteur;
    }
    public Enum_DegrePropreteEnclos getEtatToit() {
    	return etatToit;
    }
    
    
    /**
     * Ajoute une créature à la volière en vérifiant si elle est volante et si sa taille est
     * compatible avec la hauteur de la volière.
     *
     * @param creature La créature à ajouter à la volière.
     * @throws Exception Si la créature n'est pas volante ou si elle est trop grande pour la volière.
     */
    public void ajouterCreature(Creature creature) throws Exception {
        // Vérification si la créature est une créature volante
        if (creature instanceof CreatureVolante) {
            // Vérification si la créature n'est pas trop grande pour la volière
            if (creature.getTaille() < hauteur)
                super.ajouterCreature(creature);
            else
                throw new Exception("Creature trop grande pour enclos");
        } else {
            throw new Exception("Une voliere ne peut contenir que des cretaures volantes");
        }
    }

    
    /**
     * Effectue l'entretien de la volière en vérifiant l'état du toit et en réalisant l'entretien
     * classique de l'enclos.
     *
     * @throws Exception Si l'état du toit est bon et il y a créature dans la volière.
     */
    public void entretenirEnclos() throws Exception {
        // Vérification de l'état du toit
        if (super.getNbCreatures() == 0 && etatToit != Enum_DegrePropreteEnclos.bon)
            etatToit = Enum_DegrePropreteEnclos.bon;
        // Entretien classique de l'enclos
        super.entretenirEnclos();
    }
    
    
    /**
	 * Methode permettant d'afficher les caracteristiques de l'enclos 
	 * et les creatures qu'il contient
	 * 
	 * @return la chaine de caractère contenant les informations
	 */
	public String toString() {
		String chaine = "Voliere "+super.getNom()+" de superficie "+super.getSuperficie()+" m^2 pouvant contenir au "
				+ "plus "+super.getNbCreatures()+" creatures.\n Il y a actuellement "+super.getNbCreatures()+" creatures\n"
				+ "Degre de proprete : "+super.getDegreProprete()+"\n"
				+ "Etat toit : "+etatToit+"\n";
		for (Creature creature : super.getListeCreatures().values()) {
			chaine+="Index : "+ trouverCleParCreature(creature)+"\n";
			chaine+= creature.toString();
		}
		return chaine;
	}
	
	
	/**
	 * Methode pour avoir le nom de l'enclos et son etat
	 */
	public String voirInfoEnclos() {
		return " -Voliere "+getNom()+" avec "+getNbCreatures()
        +" creatures.\n          Degre proprete : "+getDegreProprete()
        +"\n          Etat toit : "+etatToit+"\n";
	}
	
	
	/**
	 * Methode permettant de degrader l'etat du toit de la voliere
	 */
	public void degradationEtatToit() {
		if (etatToit == Enum_DegrePropreteEnclos.bon)
			etatToit = Enum_DegrePropreteEnclos.correct;
		else if (etatToit == Enum_DegrePropreteEnclos.correct)
			etatToit = Enum_DegrePropreteEnclos.mauvais;
	}
}
