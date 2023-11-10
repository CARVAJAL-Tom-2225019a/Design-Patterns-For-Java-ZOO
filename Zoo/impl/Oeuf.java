package impl;

import java.time.*;

import base.*;
import enums.*;

public class Oeuf {
	private Enum_Especes espece;
	private LocalDate dateNaissance;
	private Duration dureeIncubation;
	private String bruit;
	private boolean isOpen;
	
	public Oeuf (Enum_Especes espece, String bruit, Duration dureeIncubation) {
		this.espece = espece;
		this.bruit = bruit;
		this.dateNaissance = LocalDate.now();
		this.dureeIncubation = dureeIncubation;
		isOpen = false;
	}
	
	
	public Creature Eclore (Enum_Sexe sexe, double poids, double taille) throws Exception {
		if (!isOpen) {
			// recuperation temps entre date ponte et aurjoud'hui
			Duration tempsPasse = Duration.between(dateNaissance, LocalDate.now());
			// verification dureeIncubation depasse
			if (tempsPasse.compareTo(dureeIncubation) > 0 ) {
				// changement etat
				isOpen = true;
				// creation creature
				CreateCreature fabriqueCreature = new CreateCreature();
				return fabriqueCreature.createCreature(espece, sexe, poids, taille, bruit, dureeIncubation);
			}
			else {
				throw new Exception ("Duree incubation non termine");
			}
		}
		else {
			throw new Exception ("Oeuf vide");
		}
		
	}

}
