package base;

import java.time.*;

import enums.*;

public abstract class Vivipare extends Creature {

	public Vivipare(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration dureeGestation) {
		super(nomEspece, sexe, poids, taille, bruit);
	}
	
	public Creature MettreBas(Enum_Sexe sexe, double poids, double taille, Duration duree) throws Exception {
		if (super.isEstVivant() && super.getSexe() == Enum_Sexe.Femelle) {
			// creation creature
			CreateCreature fabriqueCreature = new CreateCreature();
			return fabriqueCreature.createCreature(super.getNomEspece(), sexe, poids, taille, super.getBruit(), duree);
		}
		else {
			throw new Exception ("Statut creature invalide");
		}
	}

}
