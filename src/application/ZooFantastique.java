package application;

import java.util.HashSet;
import java.util.Set;

import base.*;
import enclosImplemente.*;
import maitreZoo.*;

public class ZooFantastique {
	MenuMaitreZoo menuUtilisateur = new MenuMaitreZoo();
	
	private String nom;
	private MaitreZoo maitreZoo;
	private int nbMaxEnclos;
	private Set<Enclos> listeEnclos;
	
	

	public ZooFantastique() {
		this.nom = "Le Zoo Fantastique";
		this.maitreZoo = null;
		this.nbMaxEnclos = 10;
		this.listeEnclos = new HashSet<>();
	}
	
	public ZooFantastique(String nom) {
		this.nom = nom;
		this.maitreZoo = null;
		this.nbMaxEnclos = 10;
		this.listeEnclos = new HashSet<>();
	}
	
	
	public void AddEnclos (Enclos enclos) {
		if (listeEnclos.size() < nbMaxEnclos) {
			listeEnclos.add(enclos);
		}
	}
	
	public int getNbCreaturesTotales () {
		int somme = 0;
		for (Enclos e : listeEnclos) {
			somme += e.getNbCreatures();
		}
		return somme;
	}
	
	
	public String AfficherEnsembleZoo () {
		String chaine = "**VOICI LE ZOO **\n";
		for (Enclos e : listeEnclos) {
			chaine += e.toString();
		}
		return chaine;
	}
	

	public static void main(String[] args) {
		// Point d'entree de la simulation
		//TODO

	}
	
	private void PasserLaMainUtilisateur () {
		//TODO
		// nbr action par an
	}
	
	private void ModififAleatoireStatutCreature () {
		// TODO
		// intervalle régulier
	}
	
	private void ModifAleatoireEtatEnclos () {
		//TODO
	}

}
