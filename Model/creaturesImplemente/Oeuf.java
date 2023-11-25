package creaturesImplemente;

import base.*;
import references.*;

/**
 * Cette classe correspond a un oeuf qui sera pondu par un ovipare
 *
 */
public class Oeuf {
	
    private Enum_Especes espece;
    private int dureeIncubation;
    private int dureeIncubationRestante;
    private boolean isOpen;

    
    /**
     * Constructeur de la classe Oeuf.
     * 
     * @param espece          L'espèce de l'œuf.
     * @param dureeIncubation La durée d'incubation spécifique de l'espèce.
     */
    public Oeuf(Enum_Especes espece, int dureeIncubation) {
        this.espece = espece;
        this.dureeIncubation = dureeIncubation;
        this.dureeIncubationRestante = dureeIncubation;
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
                return FactoryCreature.newCreature(espece, sexe, poids, taille);
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
