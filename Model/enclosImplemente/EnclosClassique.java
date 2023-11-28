package enclosImplemente;

import interfaces.*;
import base.Creature;
import base.Enclos;

public class EnclosClassique extends Enclos {

	public EnclosClassique(String nom, double superficie) {
		super(nom, superficie);
	}
	
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
