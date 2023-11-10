package base;

import java.util.Map;

import enums.*;

public abstract class Creature {
	CONSTANTES constantes = new CONSTANTES();
	
	// Attributs
	private Enum_Especes nomEspece;
	private Enum_Sexe sexe;
	private double poids;
	private double taille;
	private int age;
	
	private int indicateurFaim; // indice de satiété
	private int indicateurSommeil; // si 0, gros dodo
	private int indicateurSante;
	
	private boolean estEnTrainDeDormir;
	private boolean estVivant;
	
	private String bruit;
	
	
	// Constructeur
	public Creature(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit) {
		this.nomEspece = nomEspece;
		this.sexe = sexe;
		this.poids = poids;
		this.taille = taille;
		this.age = 0;
		this.indicateurFaim = constantes.MAX_INDICATEUR;
		this.indicateurSommeil = constantes.MAX_INDICATEUR;
		this.indicateurSante = constantes.MAX_INDICATEUR;
		this.estEnTrainDeDormir = false;
		this.estVivant = true;
		this.bruit = bruit;
	}
	
	
	// Getter
	public Enum_Especes getNomEspece() {
		return nomEspece;
	}
	public Enum_Sexe getSexe() {
		return sexe;
	}
	public double getPoids() {
		return poids;
	}
	public double getTaille() {
		return taille;
	}
	public int getAge() {
		return age;
	}
	public int getIndicateurFaim() {
		return indicateurFaim;
	}
	public int getIndicateurSommeil() {
		return indicateurSommeil;
	}
	public int getIndicateurSante() {
		return indicateurSante;
	}
	public boolean isEstEnTrainDeDormir() {
		return estEnTrainDeDormir;
	}
	public boolean isEstVivant() {
		return estVivant;
	}
	public String getBruit() {
		return bruit;
	}


	// Methodes
	
	
	public void Manger(int num) throws Exception {
		if (estVivant && !estEnTrainDeDormir)
			indicateurFaim+=num;
			// Verification pas de dépassement
			if (indicateurFaim > constantes.MAX_INDICATEUR)
				indicateurFaim = constantes.MAX_INDICATEUR;
		else 
			throw new Exception ("Etat de la creture invalide");
			
	}
	
	
	public String FaireBruit() throws Exception {
		if (estVivant)
			return bruit;
		else
			throw new Exception ("Etat de la creature invalide");
	}
	
	
	public void Soigner (int num) throws Exception {
		if (estVivant)
			indicateurSante+=num;
			// Verification pas de dépassement
			if (indicateurSante > constantes.MAX_INDICATEUR)
				indicateurSante = constantes.MAX_INDICATEUR;
		else
			throw new Exception ("Etat de la creature invalide");
	}
	
	
	public void Dormir () throws Exception {
		if (estVivant && !estEnTrainDeDormir && indicateurSommeil<constantes.MAX_INDICATEUR)
			estEnTrainDeDormir = true;
		else
			throw new Exception ("Etat de la creature invalide");
	}
	
	
	public void SeReveiller () throws Exception {
		if (estVivant && estEnTrainDeDormir) {
			estEnTrainDeDormir = true;
			indicateurSommeil = constantes.MAX_INDICATEUR;
		}
		else
			throw new Exception ("Etat de la creature invalide");
	}
	
	
	public void Vieillir () throws Exception {
		if (estVivant && age < constantes.MAX_AGE)
			age++;
		else
			Mourrir();
	}
	
	
	public void Mourrir() {
		estVivant = false;
	}
	
	
	public void PerdreSommeil () throws Exception {
		// verfication etat creature
		if (estVivant && indicateurSante>0 && indicateurFaim>0 && indicateurSommeil>0)
			indicateurSommeil -= constantes.VALEUR_PERTE_INDICATEUR;
		else
			throw new Exception ("Creature pas en etat de realiser action");
		// verification valeur positive
		if (indicateurSommeil < 0)
			indicateurSommeil = 0;
	}
	
	public void PerdreNourriture () throws Exception {
		// verfication etat creature
		if (estVivant && indicateurSante>0 && indicateurFaim>0 && indicateurSommeil>0)
			indicateurFaim -= constantes.VALEUR_PERTE_INDICATEUR;
		else
			throw new Exception ("Creature pas en etat de realiser action");
		// verification valeur positive
		if (indicateurFaim < 0)
			indicateurFaim = 0;
	}
	
	public void PerdreSante () throws Exception {
		// verfication etat creature
		if (estVivant && indicateurSante>0 && indicateurFaim>0 && indicateurSommeil>0)
			indicateurSante -= constantes.VALEUR_PERTE_INDICATEUR;
		else
			throw new Exception ("Creature pas en etat de realiser action");
		// verification valeur positive
		if (indicateurSante < 0)
			indicateurSante = 0;
	}
	
}
