package meuteLycanthrope;

import creaturesImplemente.Lycanthrope;

/**
 * Classe correspondant à un couple alpha d'une meute
 * Comprend un male alpha et une femelle alpha
 */
public class CoupleAlpha {
	
	private Lycanthrope femelleAlpha;
	private Lycanthrope maleAlpha;
	
	/**
	 * Constructeur
	 * 
	 * @param femelle La lycanthrope femelle du couple.
	 * @param male    La lycanthrope mâle du couple.
	 */
	public CoupleAlpha (Lycanthrope femelle, Lycanthrope male) {
		femelleAlpha = femelle;
		maleAlpha = male;
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
	
	
	/**
	 * Setters
	 */
	public void setFemelleAlpha(Lycanthrope l) {
		femelleAlpha=l;
	}
	public void setMaleAlpha(Lycanthrope l) {
		maleAlpha=l;
	}
	
	
	/**
	 * Methode permettant d'afficher les informations du couple
	 * 
	 * @return Une chaine de caractere comprenant les informations
	 */
	public String toString() {
		return "COUPLE ALPHA :\n\nFEMELLE :\n"+femelleAlpha.toString()
				+"\nMALE :\n"+maleAlpha.toString();
	}
	
	
	/**
	 * Methode pour se reproduire
	 * @throws Exception 
	 */
	public void seReproduire() throws Exception {
		femelleAlpha.concevoirUnEnfant(maleAlpha, femelleAlpha.getDureeGestation());
	}
	
}
