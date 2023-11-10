package impl;

import java.time.*;

import base.*;
import insterfaces.*;

public class Lycanthrope extends Vivipare implements CreatureTerrestre {
	private Duration dureeGestation;

	public Lycanthrope(String nomEspece, String sexe, double poids, double taille, String bruit, Duration dureeGestation) {
		super(nomEspece, sexe, poids, taille, bruit, dureeGestation);
		this.dureeGestation = dureeGestation;
	}

	@Override
	public String Courrir() {
		// TODO Auto-generated method stub
		return null;
	}

}
