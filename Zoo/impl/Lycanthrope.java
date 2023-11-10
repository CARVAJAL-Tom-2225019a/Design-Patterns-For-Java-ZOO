package impl;

import java.time.*;

import base.*;
import enums.*;
import insterfaces.*;

public class Lycanthrope extends Vivipare implements CreatureTerrestre {
	private Duration dureeGestation;

	public Lycanthrope(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeGestation) {
		super(nomEspece, sexe, poids, taille, bruit, dureeGestation);
		this.dureeGestation = dureeGestation;
	}

	@Override
	public String Courrir() {
		// TODO Auto-generated method stub
		return null;
	}

}
