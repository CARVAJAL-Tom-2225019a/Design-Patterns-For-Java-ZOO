package base;

import enums.*;

public abstract class Creature {
	// Constantes
	int MAX_INDICATEUR = 10;
	int MAX_AGE = 5;
	
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
		this.indicateurFaim = MAX_INDICATEUR;
		this.indicateurSommeil = MAX_INDICATEUR;
		this.indicateurSante = MAX_INDICATEUR;
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
		else
			throw new Exception ("Etat de la creature invalide");
	}
	
	
	public void Dormir () throws Exception {
		if (estVivant && !estEnTrainDeDormir && indicateurSommeil<MAX_INDICATEUR)
			estEnTrainDeDormir = true;
		else
			throw new Exception ("Etat de la creature invalide");
	}
	
	
	public void SeReveiller () throws Exception {
		if (estVivant && estEnTrainDeDormir) {
			estEnTrainDeDormir = true;
			indicateurSommeil = MAX_INDICATEUR;
		}
		else
			throw new Exception ("Etat de la creature invalide");
	}
	
	
	public void Vieillir () throws Exception {
		if (estVivant && age < MAX_AGE)
			age++;
		else
			Mourrir();
	}
	
	
	public void Mourrir() {
		estVivant = false;
	}
}
