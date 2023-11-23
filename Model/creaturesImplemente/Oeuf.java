package creaturesImplemente;

import base.*;
import references.*;

/**
 * Cette classe correspond a un oeuf qui sera pondu par un ovipare
 *
 */
public class Oeuf {
	private Ovipare parent1;
    private Ovipare parent2;
    private Enum_Especes espece;
    private int dureeIncubation;
    private int dureeIncubationRestante;
    private boolean isOpen;

    
    /**
     * Constructeur de la classe Oeuf.
     */
    public Oeuf(Ovipare parent1, Ovipare parent2) {
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.espece = parent1.getNomEspece();
        this.dureeIncubation = parent1.getDureeGestation();
        this.dureeIncubationRestante =  parent1.getDureeGestation();
        isOpen = false;
    }

    /**
     * Getters
     */
    public Enum_Especes getEspece() {
		return espece;
	}
	public int getDureeIncubation() {
		return dureeIncubation;
	}
	public int getDureeIncubationRestante() {
		return dureeIncubationRestante;
	}
	public boolean isOpen() {
		return isOpen;
	}

	
	/**
	 * Methode permettant de decrementer la duree d'incubation restante
	 */
	public void DecrementerDureeIncubationRestante() {
		dureeIncubationRestante--;
		if (dureeIncubationRestante<0)
			dureeIncubationRestante=0;
	}


	/**
     * Méthode pour faire éclore l'œuf.
     * 
     * @param sexe   Le sexe de la créature qui éclore.
     * @param poids  Le poids de la créature qui éclore.
     * @param taille La taille de la créature qui éclore.
     * @return Une instance de la classe Creature.
     * @throws Exception Si la durée d'incubation n'est pas terminée ou si l'œuf est déjà éclos.
     */
    public Creature Eclore(Enum_Sexe sexe, double poids, double taille) throws Exception {
        if (!isOpen) {
            // Vérification si la durée d'incubation est dépassée
            if (dureeIncubationRestante == 0 ) {
                // Changement d'état de l'oeuf
                isOpen = true;

                // Création de la créature
                return FactoryCreature.newCreature(espece);
            } else {
                throw new Exception("Durée d'incubation non terminée");
            }
        } else {
            throw new Exception("Oeuf déjà éclos");
        }
    }
    
    
    /**
     * Methode pour afficher les informations sur l'oeuf
     * @return la chaine contenant les informations
     */
    public String toString() {
    	return "Oeuf contenant un(e) "+ espece + " qui va eclore dans "+dureeIncubationRestante+" ans\n";
    }
}
