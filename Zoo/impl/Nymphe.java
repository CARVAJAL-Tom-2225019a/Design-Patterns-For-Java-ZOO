package impl;

import java.time.*;

import base.*;
import enums.*;
import insterfaces.*;

public class Nymphe extends Vivipare implements CreatureImmortel {
	private Duration dureeGestation;

	public Nymphe(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeGestation) {
		super(nomEspece, sexe, poids, taille, bruit, dureeGestation);
		this.dureeGestation = dureeGestation;
	}

	@Override
	public void Renaitre() {
		// TODO Auto-generated method stub
		
	}

}
