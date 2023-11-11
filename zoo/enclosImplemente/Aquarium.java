package enclosImplemente;

import base.Creature;
import interfaces.*;
import references.*;

/**
 * La classe Aquarium représente un aquarium, une extension spécifique de la classe Enclos,
 * destinée à accueillir des créatures marines.
 */
public class Aquarium extends Enclos {
    CONSTANTES constantes = new CONSTANTES(); // Instance de la classe CONSTANTES pour les constantes du programme.

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
    public Aquarium(String nom, double superficie, int nbMaxCreatures, double profondeurBassin, double saliniteEau) {
        super(nom, superficie, nbMaxCreatures);
        this.profondeurBassin = profondeurBassin;
        niveauEau = profondeurBassin;
        this.saliniteEau = saliniteEau;
    }

    /**
     * Ajoute une créature dans l'aquarium en vérifiant si elle est aquatique et si sa taille est
     * compatible avec la profondeur du bassin.
     *
     * @param creature La créature à ajouter dans l'aquarium.
     * @throws Exception Si la créature n'est pas aquatique ou si elle est trop grande pour le bassin.
     */
    public void AjouterCreature(Creature creature) throws Exception {
        // Vérification si la créature est une créature marine
        if (creature instanceof CreatureMarine) {
            // Vérification si la créature n'est pas trop grande pour le bassin de l'aquarium
            if (creature.getTaille() < profondeurBassin)
                super.AjouterCreature(creature);
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
    public void EntretenirEnclos() throws Exception {
        // Vérification du niveau d'eau
        if (niveauEau < profondeurBassin)
            // Ajout d'eau pour atteindre la profondeur du bassin
            niveauEau = profondeurBassin;

        // Vérification de la salinité de l'eau
        double diff = saliniteEau - constantes.SALINITE_CORRECT;
        if (diff > 1 || diff < -1) {
            // Rééquilibrage de la salinité de l'eau pour atteindre le niveau correct
            saliniteEau = constantes.SALINITE_CORRECT;
        }
    }
}
