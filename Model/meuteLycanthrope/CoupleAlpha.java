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
	 * Recuperer la femelle alpha du couple
	 * @return le lycanthrope qui est la femelle alpha
	 */
	public Lycanthrope getFemelleAlpha() {
		return femelleAlpha;
	}
	/**
	 * Recuperer le male alpha du couple
	 * @return le lycanthrope qui est le male alpha
	 */
	public Lycanthrope getMaleAlpha() {
		return maleAlpha;
	}
	
	
	/**
	 * Modifier la femelle alpha du couple
	 * @param l la nouvelle femelle alpha
	 */
	public void setFemelleAlpha(Lycanthrope l) {
		femelleAlpha=l;
	}
	
	/**
	 * Modifier le male alpha du couple
	 * @param l le nouveau male alpha du couple
	 */
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
