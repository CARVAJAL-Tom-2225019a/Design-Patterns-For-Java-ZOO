package impl;

import java.time.Duration;

import base.*;
import insterfaces.*;

public class Dragon extends Ovipare implements CreatureTerrestre, CreatureMarine, CreatureVolante, CreatureImmortel {
	private Duration dureeIncubation;
	
	public Dragon(String nomEspece, String sexe, double poids, double taille, String bruit, Duration dureeIncubation) {
		super(nomEspece, sexe, poids, taille, bruit);
		this.dureeIncubation = dureeIncubation;
	}

	@Override
	public String Courrir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Nager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Voler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Renaitre() {
		// TODO Auto-generated method stub
		
	}

}
