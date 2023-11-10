package base;

import java.time.*;

public abstract class Vivipare extends Creature {

	public Vivipare(String nomEspece, String sexe, double poids, double taille, String bruit, Duration dureeGestation) {
		super(nomEspece, sexe, poids, taille, bruit);
	}
	
	public String MettreBas() throws Exception {
		if (super.isEstVivant() && super.getSexe() == "F") {
			// TODO : creation new creature
		}
		else {
			throw new Exception ("Statut creature invalide");
		}
	}

}
