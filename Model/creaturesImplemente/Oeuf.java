package creaturesImplemente;

import base.Creature;
import base.Ovipare;
import references.Enum_Especes;

/**
 * Cette classe correspond a un oeuf qui sera pondu par un ovipare
 *
 */
public class Oeuf {
	
	//TODO : gestion genealogie
	
	private final Ovipare parent1;
    private final Ovipare parent2;
    private final Enum_Especes espece;
    private final int dureeIncubation;
    private int dureeIncubationRestante;
    private boolean isOpen;

    
    /**
     * Constructeur de la classe Oeuf.
     * 
     * @param parent1 Parent 1 pour la reproduction.
     * @param parent2 Parent 2 pour la reproduction.
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
     * Methode permettant de récupérer l'espèce de la créature qui est dans l'oeuf
     * @return l'espèce selon l'énumération
     */
    public Enum_Especes getEspece() {
		return espece;
	}
    
    /**
     * Méthode permettant de récupérer la durée d'incubation totale
     * @return la durée d'incubation totale (en jours)
     */
	public int getDureeIncubation() {
		return dureeIncubation;
	}
	
	/**
	 * Méthode permettant de récupérer la durée d'incubation restante
	 * avant que l'enfant naisse
	 * @return la durée d'incubation restante (en jours)
	 */
	public int getDureeIncubationRestante() {
		return dureeIncubationRestante;
	}
	
	/**
	 * Méthode permettant de savoir si un oeuf est ouvert
	 * ou s'il y a encore la créature à l'intérieur
	 * @return true si l'oeuf est ouvert et vide, sinon false
	 */
	public boolean isOpen() {
		return isOpen;
	}
	
	/**
     * Méthode permettant de récupérer le premier parent de l'embryon
     * @return la créature correspondant au premier parent
     */
    public Creature getParent1() {
    	return parent1;
    }
    
    /**
     * Méthode permettant de récupérer le deuxième parent de l'embryon
     * @return la créature correspondant au deuxième parent
     */
    public Creature getParent2() {
    	return parent2;
    }

	
	/**
	 * Methode permettant de decrementer la duree d'incubation restante
	 */
	public void decrementerDureeIncubationRestante() {
		dureeIncubationRestante--;
		if (dureeIncubationRestante<0)
			dureeIncubationRestante=0;
	}


	/**
     * Méthode pour faire éclore l'œuf
     * 
     * @return Une instance de la classe Creature
     * @throws Exception Si la durée d'incubation n'est pas terminée ou si l'œuf est déjà éclos
     */
    public Creature eclore() throws Exception {
        if (!isOpen) {
            // Vérification si la durée d'incubation est dépassée
            if (dureeIncubationRestante == 0 ) {
                // Changement d'état de l'oeuf
                isOpen = true;
                // Création de la créature
                return FactoryCreature.newCreature(espece);
            } else {
                throw new Exception("Duree d'incubation non terminee");
            }
        } else {
            throw new Exception("Oeuf deje eclos");
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
