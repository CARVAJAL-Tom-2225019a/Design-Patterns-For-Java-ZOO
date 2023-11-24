package meuteLycanthrope;

import java.util.HashSet;
import java.util.Set;

import base.Enclos;
import creaturesImplemente.Lycanthrope;
import references.*;

/**
 * Classe d'une meute de lycanthrope (loup-garou)
 */
public class Meute {
	
	private Lycanthrope femelleAlpha;
	private Lycanthrope maleAlpha;
	
	private int capaciteMeute; 
	private Set<Lycanthrope> listeLoup;
	//TODO : verifier rang loup si dans liste
	private Set<Enum_RangDomination> rangPossible;
	
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
		this.capaciteMeute = CapaciteMeute;
		this.listeLoup = new HashSet<Lycanthrope>();
		listeLoup.add(femelleAlpha);
		listeLoup.add(maleAlpha);
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
		return capaciteMeute;
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
	
	/**
	 * Setters
	 */
	public void setEnclosReference (Enclos e) {
		enclosReference = e;
	}
	
	
	/**
	 * Methode permettant d'ajouter un lycanthrope a la meute
	 * @param loup	le lycanthrope a ajouter
	 * @throws Exception si le lycanthrope a deja une meute ou s'il n'y a plus de place
	 */
	public boolean addLoup(Lycanthrope loup) throws Exception {
		if (listeLoup.size() < capaciteMeute) {
			if (loup.getMeute() == null ) {
				listeLoup.add(loup);
				loup.setRangDomination(affecterRang(loup));
				return true;
			}
			else 
				throw new Exception ("Le loup a deja une meute");
		}
		else
			throw new Exception ("La meute est pleine, impossible de rajouter un loup");
	}
	
	
	private Enum_RangDomination affecterRang(Lycanthrope loup) {
		double forceLoup = loup.getForce();
		double forceTemp = femelleAlpha.getForce();
		for (Lycanthrope l : listeLoup) {
			if (forceLoup > forceTemp && forceLoup < l.getForce()) {
				return l.getRangDomination();
			}
			forceTemp = l.getForce();
		}
		return Enum_RangDomination.OMEGA;
	}
	

	/**
	 * Methode permettant de supprimer un loup de la meute
	 * @param loup	Le loup a supprimer
	 * @throws Exception	SI le loup fait partie du couple alpha ou n'est pas dans la meute
	 */
	public boolean removeLoup(Lycanthrope loup) throws Exception {
		if (listeLoup.contains(loup)) {	
			if (loup != femelleAlpha && loup !=maleAlpha) {
				listeLoup.remove(loup);
				loup.seSeparerDeSaMeute();
				return true;
			}
			else
				throw new Exception ("Un membre du couple alpha ne peut pas quitter la meute");
		}
		else
			throw new Exception ("Le loup n'appartient pas a cette meute");
	}
	
	
	public String defierMaleAlpha (Lycanthrope loup1) throws Exception {
		boolean reussite = false;
		if (loup1.isPlusFort(maleAlpha))
			reussite=true;
		String chaine = loup1.hurler(Enum_ActionHurlement.Agressivite, maleAlpha);
		// reussite
		if (reussite) {
			Lycanthrope ancienAlpha = maleAlpha;
			loup1.setRangDomination(Enum_RangDomination.ALPHA);
			maleAlpha = loup1;
			removeLoup(ancienAlpha);
			chaine+= "\n"+loup1.getPrenom()+"est maintenant le male alpha\n";
		}
		return chaine;
	}
	
	
	public String verificationSeuilFacteurDominationMeute() {
		String chaine = "LES LOUPS QUI PERDENT UN RANG :\n";
		for (Lycanthrope l : listeLoup) {
			if (l.seuilFacteurDominationAtteint()) {
				if (!isDernierDuRang(l.getRangDomination())) {
					l.setRangDomination(l.getRangDomination().getRangInferieur());
					chaine += "   - "+l+"\n";
				}	
			}
		}
		return chaine;
	}
	
	
	private boolean isDernierDuRang(Enum_RangDomination rang) {
		int compteur = 0;
		for (Lycanthrope l : listeLoup) {
			if (l.getRangDomination() == rang)
				compteur++;
		}
		if (compteur < 2)
			return true;
		else
			return false;
	}
	
	
	
	/**
	 * Methode permettant de recuperer les informations sur une meute
	 */
	public String toString() {
		return "Meute se trouvant dans "+enclosReference+" avec "+listeLoup.size()+"/"+capaciteMeute+"\n"
				+ "  * Male Alpha : "+maleAlpha+"\n"
				+ "  * Femelle Alpha : "+femelleAlpha+"\n\n";
	}
}
