package meuteLycanthrope;

import creaturesImplemente.Lycanthrope;
import references.Enum_RangDomination;

public class CoupleAlpha {
	private Lycanthrope femelleAlpha;
	private Lycanthrope maleAlpha;
	
	/**
	 * Constructor
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
