package enclosImplemente;

import base.Creature;
import base.Enclos;
import interfaces.*;
import references.*;

/**
 * La classe Aquarium représente un aquarium, 
 * une extension spécifique de la classe Enclos,
 * destinée à accueillir des créatures marines.
 */
public class Aquarium extends Enclos {

    private double profondeurBassin; // Profondeur de l'aquarium.
    private double niveauEau; // Niveau actuel de l'eau dans l'aquarium.
    private double saliniteEau; // Niveau de salinité de l'eau dans l'aquarium.

    
    /**
     * Constructeur de la classe Aquarium.
     *
     * @param nom             Le nom de l'aquarium.
     * @param superficie      La superficie de l'aquarium.
     * @param nbMaxCreatures  Le nombre maximum de créatures que l'aquarium peut accueillir.
     * @param profondeurBassin La profondeur du bassin de l'aquarium.
     * @param saliniteEau     La salinité initiale de l'eau dans l'aquarium.
     */
    public Aquarium(String nom, double superficie, double profondeurBassin) {
        super(nom, superficie);
        this.profondeurBassin = profondeurBassin;
        niveauEau = profondeurBassin;
        this.saliniteEau = CONSTANTES.SALINITE_CORRECT;
    }
    
    
    /**
     * Getters
     */
    public double getSaliniteEau() {
    	return saliniteEau;
    }
    public double getNiveauEau() {
    	return niveauEau;
    }
    public double getProfondeurBassin () {
    	return profondeurBassin;
    }
    

    /**
     * Ajoute une créature dans l'aquarium en vérifiant si elle est aquatique et si sa taille est
     * compatible avec la profondeur du bassin.
     *
     * @param creature La créature à ajouter dans l'aquarium.
     * @throws Exception Si la créature n'est pas aquatique ou si elle est trop grande pour le bassin.
     */
    public void ajouterCreature(Creature creature) throws Exception {
        // Vérification si la créature est une créature marine
        if (creature instanceof CreatureMarine) {
            // Vérification si la créature n'est pas trop grande pour le bassin de l'aquarium
            if (creature.getTaille() < profondeurBassin)
                super.ajouterCreature(creature);
            else
                throw new Exception("Creature trop grande");
        } else {
            throw new Exception("Un aquarium ne peut contenir que des créatures aquatiques");
        }
    }

    
    /**
     * Effectue l'entretien de l'aquarium en vérifiant le niveau d'eau et l'équilibre de la salinité.
     *
     * @throws Exception Si le niveau d'eau est inférieur à la profondeur du bassin.
     */
    public void entretenirEnclos() throws Exception {
        // Vérification du niveau d'eau
        if (niveauEau < profondeurBassin)
            // Ajout d'eau pour atteindre la profondeur du bassin
            niveauEau = profondeurBassin;

        // Vérification de la salinité de l'eau
        double diff = saliniteEau - CONSTANTES.SALINITE_CORRECT;
        if (diff > 1 || diff < -1) {
            // Rééquilibrage de la salinité de l'eau pour atteindre le niveau correct
            saliniteEau = CONSTANTES.SALINITE_CORRECT;
        }
    }
    
    
    /**
	 * Methode permettant d'afficher les caracteristiques de l'enclos 
	 * et les creatures qu'il contient
	 * 
	 * @return la chaine de caractère contenant les informations
	 */
	public String toString() {
		String chaine = "Aquarium "+super.getNom()+" de superficie "+super.getSuperficie()+" pouvant contenir au "
				+ "plus "+super.getNbMaxCreatures()+" creatures.\n Il y a actuellement "+super.getNbCreatures()+" creatures.\n"
				+ "Salinite eau : "+saliniteEau+"/"+CONSTANTES.SALINITE_CORRECT+"\n"
				+ "Niveau eau : "+niveauEau+"/"+profondeurBassin+"\n";
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
		return " -Aquarium "+getNom()+" avec "+getNbCreatures()
        +" creatures.\n          Niveau eau : "+niveauEau+"/"+profondeurBassin
        +"\n          Salinite eau : "+saliniteEau+"/"+CONSTANTES.SALINITE_CORRECT+"\n";
	}
	
	
	/**
	 * Methode permettant la degradation du niveau d'eau
	 */
	public void degradationNiveauEau () {
		double perte = profondeurBassin/10;
		niveauEau -= perte;
		if (niveauEau<= 0)
			niveauEau=1;
	}
	
	
	/**
	 * Methode permettant la degradation de la salinite de l'eau
	 */
	public void degradationSaliniteEau () {
		saliniteEau--;
		if (saliniteEau<=0)
			saliniteEau=1;
	}
}
