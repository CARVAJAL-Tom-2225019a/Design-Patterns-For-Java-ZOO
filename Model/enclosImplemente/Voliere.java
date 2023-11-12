package enclosImplemente;

import base.*;
import interfaces.CreatureVolante;
import references.*;

/**
 * La classe Voliere représente une volière, une extension spécifique de la classe Enclos,
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
    public Voliere(String nom, double superficie, int nbMaxCreatures, double hauteur) {
        super(nom, superficie, nbMaxCreatures);
        this.hauteur = hauteur;
        etatToit = Enum_DegrePropreteEnclos.bon;
    }

    /**
     * Ajoute une créature à la volière en vérifiant si elle est volante et si sa taille est
     * compatible avec la hauteur de la volière.
     *
     * @param creature La créature à ajouter à la volière.
     * @throws Exception Si la créature n'est pas volante ou si elle est trop grande pour la volière.
     */
    public void AjouterCreature(Creature creature) throws Exception {
        // Vérification si la créature est une créature volante
        if (creature instanceof CreatureVolante) {
            // Vérification si la créature n'est pas trop grande pour la volière
            if (creature.getTaille() < hauteur)
                super.AjouterCreature(creature);
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
    public void EntretenirEnclos() throws Exception {
        // Vérification de l'état du toit
        if (super.getNbCreatures() == 0 && etatToit != Enum_DegrePropreteEnclos.bon)
            etatToit = Enum_DegrePropreteEnclos.bon;
        // Entretien classique de l'enclos
        super.EntretenirEnclos();
    }
    
    
    /**
	 * Methode permettant d'afficher les caracteristiques de l'enclos 
	 * et les creatures qu'il contient
	 * 
	 * @return la chaine de caractère contenant les informations
	 */
	public String toString() {
		String chaine = "Voliere "+super.getNom()+" de superficie "+super.getSuperficie()+" pouvant contenir au "
				+ "plus "+super.getNbCreatures()+".\n Il y a actuellement "+super.getNbCreatures()+" creatures :\n";
		for (Creature creature : super.getListeCreatures()) {
			chaine+= creature.toString();
		}
		return chaine;
	}
}
