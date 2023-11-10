package base;

import java.time.Duration;

import enums.*;
import impl.*;

public abstract class Ovipare extends Creature {

	public Ovipare(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit) {
		super(nomEspece, sexe, poids, taille, bruit);
	}
	
	public Oeuf PondreOeuf (String dateNaissance, String bruit ,Duration dureeIncubation) throws Exception {
		if (super.isEstVivant() && super.getSexe() == Enum_Sexe.Femelle) {
			return new Oeuf(super.getNomEspece(), bruit, dureeIncubation);
		}
		else {
			throw new Exception ("Statut creature invalide");
		}
	}

}
