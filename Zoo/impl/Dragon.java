package impl;

import java.time.Duration;

import base.*;
import enums.*;
import insterfaces.*;

public class Dragon extends Ovipare implements CreatureTerrestre, CreatureMarine, CreatureVolante, CreatureImmortel {
	private Duration dureeIncubation;
	
	public Dragon(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeIncubation) {
		super(nomEspece, sexe, poids, taille, bruit);
		this.dureeIncubation = dureeIncubation;
	}

	@Override
	public String Courrir() {
		super.PerdreNourriture();
		super.PerdreSommeil();
		return "Le dragon est en mouvement";
	}

	@Override
	public String Nager() {
		super.PerdreNourriture();
		super.PerdreSommeil();
		return "Le dragon nage";
	}

	@Override
	public String Voler() {
		super.PerdreNourriture();
		super.PerdreSommeil();
		return "Le dragon vole";
	}

	@Override
	public void Renaitre() {
		// TODO Auto-generated method stub
		
	}

}
