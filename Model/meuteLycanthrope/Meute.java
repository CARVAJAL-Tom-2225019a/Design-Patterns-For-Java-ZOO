package meuteLycanthrope;

import java.util.HashSet;
import java.util.Set;

import creaturesImplemente.Lycanthrope;
import enclosImplemente.Enclos;
import references.*;

/**
 * Classe d'une meute de lycanthrope (loup-garou)
 */
public class Meute {
	//TODO :transformation enclosLycanthrope si espece presente
	
	// TODO : donner rang a nouveau loup
	
	// TODO : couple reste uni tant que le mâle α n’est pas dominé par un autre mâle
	
	// TODO : (n’importe quel mâle adulte considérant être à la hauteur peut 
	// déclencher un conflit avec le mâle alpha
	
	// un nouveau couple sera alors constitué avec la femelle adulte ayant le plus haut niveau à ce
	// moment-là (cela peut être l’ancienne femelle α). Une femelle déchue du couple α prend le même 
	// rang de domination que son ancien conjoint
	
	// TODO : il arrive que les lycanthropes ω et les lycanthropes ayant échoués lors d’un 
	// conflit pour devenir mâle α se dispersent pour devenir solitaires (un maître de zoo fantastique aura 
	// alors la possibilité de les déplacer individuellement dans un autre enclot

	
	private Lycanthrope femelleAlpha;
	private Lycanthrope maleAlpha;
	
	private int CapaciteMeute; 
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
		this.CapaciteMeute = CapaciteMeute;
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
	public boolean AddLoup(Lycanthrope loup) throws Exception {
		if (listeLoup.size() < CapaciteMeute) {
			if (loup.getMeute() == null ) {
				listeLoup.add(loup);
				return true;
			}
			else 
				throw new Exception ("Le loup a deja une meute");
		}
		else
			throw new Exception ("La meute est pleine, impossible de rajouter un loup");
	}
	

	/**
	 * Methode permettant de supprimer un loup de la meute
	 * @param loup	Le loup a supprimer
	 * @throws Exception	SI le loup fait partie du couple alpha ou n'est pas dans la meute
	 */
	public boolean RemoveLoup(Lycanthrope loup) throws Exception {
		if (listeLoup.contains(loup)) {	
			if (loup != femelleAlpha && loup !=maleAlpha) {
				listeLoup.remove(loup);
				loup.SeSeparerDeSaMeute();
				return true;
			}
			else
				throw new Exception ("Un membre du couple alpha ne peut pas quitter la meute");
		}
		else
			throw new Exception ("Le loup n'appartient pas a cette meute");
	}
	
	
	public boolean DefierMaleAlpha (Lycanthrope l) {
		boolean reussite = false;
		//TODO : tenter aggression
		// reussite
		if (reussite) {
			return true;
		}
		// echec
		else {
			return false;
		}
	}
	
	
	public String VerificationSeuilFacteurDominationMeute() {
		String chaine = "LES LOUPS QUI PERDENT UN RANG :\n";
		for (Lycanthrope l : listeLoup) {
			if (l.SeuilFacteurDominationAtteint()) {
				if (!IsDernierDuRang(l.getRangDomination())) {
					l.setRangDomination(l.getRangDomination().getRangInferieur());
					chaine += "   - "+l+"\n";
				}	
			}
		}
		return chaine;
	}
	
	
	private boolean IsDernierDuRang(Enum_RangDomination rang) {
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
		return "Meute se trouvant dans "+enclosReference+" avec "+listeLoup.size()+"/"+CapaciteMeute+"\n"
				+ "  * Male Alpha : "+maleAlpha+"\n"
				+ "  * Femelle Alpha : "+femelleAlpha+"\n\n";
	}
}
