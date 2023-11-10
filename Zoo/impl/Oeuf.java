package impl;

import java.time.*;

import base.*;
import enums.Enum_Especes;

public class Oeuf {
	private Enum_Especes espece;
	private LocalDate dateNaissance;
	private Duration dureeIncubation;
	private boolean isOpen;
	
	public Oeuf (Enum_Especes espece, Duration dureeIncubation) {
		this.espece = espece;
		this.dateNaissance = LocalDate.now();
		this.dureeIncubation = dureeIncubation;
		isOpen = false;
	}
	
	
	public Creature Eclore () throws Exception {
		if (!isOpen) {
			// recuperation temps entre date ponte et aurjoud'hui
			Duration tempsPasse = Duration.between(dateNaissance, LocalDate.now());
			// verification dureeIncubation depasse
			if (tempsPasse.compareTo(dureeIncubation) > 0 ) {
				// TODO : retrouner creature
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
