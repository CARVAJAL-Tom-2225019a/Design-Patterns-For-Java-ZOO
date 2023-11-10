package impl;

import java.time.Duration;

import base.*;
import enums.*;
import insterfaces.*;

public class Kraken extends Ovipare implements CreatureMarine {
	private Duration dureeIncubation;

	public Kraken(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeIncubation) {
		super(nomEspece, sexe, poids, taille, bruit);
		this.dureeIncubation = dureeIncubation;
	}

	@Override
	public String Nager() {
		// TODO Auto-generated method stub
		return null;
	}

}
