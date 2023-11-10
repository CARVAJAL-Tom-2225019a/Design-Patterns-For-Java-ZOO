package impl;

import java.time.Duration;

import base.*;
import insterfaces.*;

public class Megalodon extends Ovipare implements CreatureMarine {
	private Duration dureeIncubation;

	public Megalodon(String nomEspece, String sexe, double poids, double taille, String bruit, Duration dureeIncubation) {
		super(nomEspece, sexe, poids, taille, bruit);
		this.dureeIncubation = dureeIncubation;
	}

	@Override
	public String Nager() {
		// TODO Auto-generated method stub
		return null;
	}

}
