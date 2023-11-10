package impl;

import java.time.*;

import base.*;
import insterfaces.*;

public class Nymphe extends Vivipare implements CreatureImmortel {
	private Duration dureeGestation;

	public Nymphe(String nomEspece, String sexe, double poids, double taille, String bruit, Duration dureeGestation) {
		super(nomEspece, sexe, poids, taille, bruit, dureeGestation);
		this.dureeGestation = dureeGestation;
	}

	@Override
	public void Renaitre() {
		// TODO Auto-generated method stub
		
	}

}
