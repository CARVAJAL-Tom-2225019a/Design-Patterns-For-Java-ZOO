package enclosImplemente;

import interfaces.*;
import base.Creature;
import base.Enclos;


/**
 * La classe EnclosClassique représente un enclos classique,
 * une extension spécifique de la classe Enclos,
 * destinée à accueillir des créatures terrestres
 */
public class EnclosClassique extends Enclos {

	/**
     * Constructeur de la classe EnclosClassique
     *
     * @param nom        Le nom de l'enclos classique
     * @param superficie La superficie de l'enclos classique
     */
	public EnclosClassique(String nom, double superficie) {
		super(nom, superficie);
	}
	
	
	/**
     * Ajoute une créature dans l'enclos classique en vérifiant si elle est terrestre et si sa taille est
     * compatible avec la superficie de l'enclos
     *
     * @param creature La créature à ajouter dans l'enclos classique
     * @throws Exception Si la créature n'est pas terrestre ou si elle est trop grande pour l'enclos
     */
	public void ajouterCreature(Creature creature) throws Exception {
        // Vérification si la créature est une créature marine
        if (creature instanceof CreatureTerrestre) {
            // Vérification si la créature n'est pas trop grande pour le bassin de l'aquarium
            if (creature.getTaille() < getSuperficie())
                super.ajouterCreature(creature);
            else
                throw new Exception("Creature "+creature.getPrenom()+" trop grande pour "+getNom());
        } else {
            throw new Exception("Un enclos classique ne peut contenir que des créatures terrestres ("+getNom()+")");
        }
    }

}
