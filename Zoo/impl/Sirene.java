package impl;

import java.time.Duration;

import base.*;
import insterfaces.*;

public class Sirene extends Vivipare implements CreatureMarine {
	private Duration dureeGestation;

	public Sirene(String nomEspece, String sexe, double poids, double taille, String bruit, Duration dureeGestation) {
		super(nomEspece, sexe, poids, taille, bruit, dureeGestation);
		this.dureeGestation = dureeGestation;
	}

	@Override
	public String Nager() {
		// TODO Auto-generated method stub
		return null;
	}

}
