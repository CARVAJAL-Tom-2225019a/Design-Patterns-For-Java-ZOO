package creaturesImplemente;

import java.time.*;
import base.*;
import references.*;

public class Oeuf {
	
    private Enum_Especes espece;
    private LocalDate dateNaissance;
    private Duration dureeIncubation;
    private boolean isOpen;

    
    /**
     * Getters
     */
    public Enum_Especes getEspece() {
		return espece;
	}
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	public Duration getDureeIncubation() {
		return dureeIncubation;
	}
	public boolean isOpen() {
		return isOpen;
	}

    
    /**
     * Constructeur de la classe Oeuf.
     * 
     * @param espece          L'espèce de l'œuf.
     * @param dureeIncubation La durée d'incubation spécifique de l'espèce.
     */
    public Oeuf(Enum_Especes espece, Duration dureeIncubation) {
        this.espece = espece;
        this.dateNaissance = LocalDate.now();
        this.dureeIncubation = dureeIncubation;
        isOpen = false;
    }


	/**
     * Méthode pour faire éclore l'œuf.
     * 
     * @param sexe   Le sexe de la créature qui éclore.
     * @param poids  Le poids de la créature qui éclore.
     * @param taille La taille de la créature qui éclore.
     * @return Une instance de la classe Creature.
     * @throws Exception Si la durée d'incubation n'est pas terminée ou si l'œuf est déjà éclos.
     */
    public Creature Eclore(Enum_Sexe sexe, double poids, double taille) throws Exception {
        if (!isOpen) {
            // Récupération du temps entre la date de ponte et aujourd'hui
            Duration tempsPasse = Duration.between(dateNaissance, LocalDate.now());

            // Vérification si la durée d'incubation est dépassée
            if (tempsPasse.compareTo(dureeIncubation) > 0) {
                // Changement d'état de l'oeuf
                isOpen = true;

                // Création de la créature
                return FactoryCreature.newCreature(espece, sexe, poids, taille);
            } else {
                throw new Exception("Durée d'incubation non terminée");
            }
        } else {
            throw new Exception("Oeuf déjà éclos");
        }
    }
}
