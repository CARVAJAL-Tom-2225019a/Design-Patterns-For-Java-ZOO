package base;

import java.time.Duration;

import impl.*;

public abstract class Ovipare extends Creature {

	public Ovipare(String nomEspece, String sexe, double poids, double taille, String bruit) {
		super(nomEspece, sexe, poids, taille, bruit);
	}
	
	public Oeuf PondreOeuf (String dateNaissance, Duration dureeIncubation) throws Exception {
		if (super.isEstVivant() && super.getSexe() == "F") {
			return new Oeuf(super.getNomEspece(), dureeIncubation);
		}
		else {
			throw new Exception ("Statut creature invalide");
		}
	}

}
