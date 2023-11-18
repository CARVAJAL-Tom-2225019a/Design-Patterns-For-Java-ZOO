package meuteLycanthrope;

import java.util.HashSet;
import java.util.Set;

import creaturesImplemente.Lycanthrope;
import enclosImplemente.Enclos;
import references.*;

public class Meute {
	
	// TODO : go to un enclos
	// TODO : donner rang a nouveau loup
	
	private Lycanthrope femelleAlpha;
	private Lycanthrope maleAlpha;
	
	private int CapaciteMeute; 
	private Set<Lycanthrope> listeLoup;
	//TODO : remplir liste
	private Set<Enum_RangDomination> rangPossible;
	
	// TODO : une seule meute par enclos
	private Enclos enclosReference;
	
	
	/**
	 * Constructeur
	 * @param femelleAlpha
	 * @param maleAlpha
	 * @param CapaciteMeute
	 * @param rangPossible
	 */
	public Meute(Lycanthrope femelleAlpha, Lycanthrope maleAlpha, int CapaciteMeute, 
			Set<Enum_RangDomination> rangPossible) {
		this.femelleAlpha = femelleAlpha;
		this.maleAlpha = maleAlpha;
		this.CapaciteMeute = CapaciteMeute;
		this.listeLoup = new HashSet<Lycanthrope>();
		this.rangPossible = rangPossible;
		this.enclosReference = null;
	}
	
	/**
	 * Getters
	 */
	public Lycanthrope getFemelleAlpha() {
		return femelleAlpha;
	}
	public Lycanthrope getMaleAlpha() {
		return maleAlpha;
	}
	public int getCapaciteMeute() {
		return CapaciteMeute;
	}
	public Set<Lycanthrope> getListeLoup() {
		return listeLoup;
	}
	public Set<Enum_RangDomination> getRangPossible() {
		return rangPossible;
	}
	public Enclos getEnclosReference() {
		return enclosReference;
	}
	
	
	public void AddLoup(Lycanthrope loup) throws Exception {
		if (listeLoup.size() < CapaciteMeute) {
			if (loup.getMeute() == null ) {
				listeLoup.add(loup);
			}
			else 
				throw new Exception ("Le loup a deja une meute");
		}
		else
			throw new Exception ("La meute est pleine, impossible de rajouer un loup");
	}
	


	public void RemoveLoup(Lycanthrope loup) throws Exception {
		if (loup != femelleAlpha && loup !=maleAlpha) {
			listeLoup.remove(loup);
			loup.SeSeparerDeSaMeute();
		}
		else
			throw new Exception ("Un membre du couple alpha ne peut pas quitter la meute");
	}
	
}
