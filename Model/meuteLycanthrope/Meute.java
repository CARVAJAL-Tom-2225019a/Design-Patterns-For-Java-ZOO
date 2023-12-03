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
	
	private CoupleAlpha coupleAlpha;
	
	private int capaciteMeute; 
	private Set<Lycanthrope> listeLoup;
	private Set<Enum_RangDomination> rangPossible;
	
	private Enclos enclosReference;
	
	
	/**
	 * Constructeur de la meute
	 * 
	 * @param femelleAlpha La lycanthrope femelle alpha du couple
	 * @param maleAlpha    La lycanthrope mâle alpha du couple
	 * @param capaciteMeute La capacité maximale de la meute
	 * @param rangPossible Les rangs de domination possibles au sein de la meute
	 * @throws Exception 
	 */
	public Meute(Lycanthrope femelleAlpha, Lycanthrope maleAlpha, int CapaciteMeute, 
			Set<Enum_RangDomination> rangPossible) throws Exception {
		this.rangPossible=rangPossible; 
		femelleAlpha.setRangDomination(Enum_RangDomination.ALPHA);
		femelleAlpha.calculerForce();
		maleAlpha.setRangDomination(Enum_RangDomination.ALPHA);
		maleAlpha.calculerForce();
		coupleAlpha = new CoupleAlpha(femelleAlpha, maleAlpha);
		this.capaciteMeute = CapaciteMeute;
		this.listeLoup = new HashSet<Lycanthrope>();
		addLoup(femelleAlpha);
		addLoup(maleAlpha);
		this.enclosReference = null;
	}
	
	
	/**
	 * Getters
	 */
	public CoupleAlpha getCoupleAlpha() {
		return coupleAlpha;
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
	 * Méthode permettant d'ajouter un lycanthrope à la meute
	 * 
	 * @param loup Le lycanthrope à ajouter
	 * @return true si l'ajout est réussi, false sinon
	 * @throws Exception Si le lycanthrope a déjà une meute ou s'il n'y a plus de place dans la meute
	 */
	public boolean addLoup(Lycanthrope loup) throws Exception {
	    if (listeLoup.size() < capaciteMeute) {
	    		loup.calculerForce();
	        	loup.rejoindreMeute(this);
	            listeLoup.add(loup);
	            affecterRang(loup);
	            return true;  
	    } else
	        throw new Exception("La meute est pleine, impossible de rajouter loup " + loup.getPrenom());
	}
	
	
	/**
	 * Méthode permettant d'affecter un rang de domination à un lycanthrope en fonction de sa force par rapport
	 * aux autres membres de la meute
	 * 
	 * @param loup Le lycanthrope
	 */
	public void affecterRang(Lycanthrope loup) {
	    double forceLoup = loup.getForce();
	    double forceAlpha = coupleAlpha.getMaleAlpha().getForce();
	    // Si la liste de loups est vide, le loup devient OMEGA
	    if (listeLoup.isEmpty()) {
	        loup.setRangDomination(Enum_RangDomination.OMEGA);
	        return;
	    }
	    // Si le loup a une force supérieure au male alpha, il devient ALPHA
	    if (forceLoup > forceAlpha) {
	        loup.setRangDomination(Enum_RangDomination.ALPHA);
	        return;
	    }
	    // Vérifier les rangs déjà attribués et attribuer le premier rang disponible
	    for (Enum_RangDomination rang : rangPossible) {
	        boolean rangPris = false;
	        for (Lycanthrope l : listeLoup) {
	            if (l.getRangDomination() == rang) {
	                rangPris = true;
	                break;
	            }
	        }
	        if (!rangPris) {
	            loup.setRangDomination(rang);
	            return;
	        }
	    }
	    // Si tous les rangs sont pris, comparer la force et attribuer le rang en conséquence
	    Enum_RangDomination rangAttribue = Enum_RangDomination.OMEGA;
	    for (Lycanthrope l : listeLoup) {
	        if (forceLoup > l.getForce()) {
	            rangAttribue = l.getRangDomination();
	        }
	    }
	    // Attribution du rang après avoir parcouru la liste des loups
	    loup.setRangDomination(rangAttribue);
	}
	

	

	/**
	 * Méthode permettant de supprimer un lycanthrope de la meute
	 * 
	 * @param loup Le lycanthrope à supprimer
	 * @return true si la suppression est réussie, false sinon
	 * @throws Exception Si le lycanthrope fait partie du couple alpha ou n'est pas dans la meute
	 */
	public boolean removeLoup(Lycanthrope loup) throws Exception {
		if (listeLoup.contains(loup)) {	
			if (loup != coupleAlpha.getFemelleAlpha() && loup !=coupleAlpha.getMaleAlpha()) {
				listeLoup.remove(loup);
				loup.seSeparerDeSaMeute();
				return true;
			}
			else
				throw new Exception ("Un membre du couple alpha ne peut pas quitter la meute");
		}
		else
			throw new Exception ("Le loup "+loup.getPrenom()+" n'appartient pas a cette meute");
	}
	
	
	/**
	 * Méthode permettant à un lycanthrope de défier le mâle alpha de la meute
	 * 
	 * @param loup1 Le lycanthrope défiant le mâle alpha
	 * @return Une chaîne de caractères représentant le résultat du défi
	 * @throws Exception Si le loup1 est plus fort que le mâle alpha
	 */
	public String defierMaleAlpha (Lycanthrope loup1) throws Exception {
		boolean reussite = false;
		if (loup1.isPlusFort(coupleAlpha.getMaleAlpha()))
			reussite=true;
		String chaine = loup1.hurler(Enum_ActionHurlement.Agressivite, coupleAlpha.getMaleAlpha());
		// reussite
		if (reussite) {
			Lycanthrope ancienAlpha = coupleAlpha.getMaleAlpha();
			loup1.setRangDomination(Enum_RangDomination.ALPHA);
			coupleAlpha.setMaleAlpha(loup1);
			removeLoup(ancienAlpha);
			chaine+= "\n"+loup1.getPrenom()+"est maintenant le male alpha\n";
		}
		return chaine;
	}
	
	
	/**
	 * Méthode de vérification du seuil de facteur de domination de la meute
	 * 
	 * @return Une chaîne de caractères représentant les loups qui perdent un rang de domination
	 */
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
	
	
	/**
	 * Méthode permettant de vérifier si un lycanthrope est le dernier de son rang
	 * au sein de la meute
	 * 
	 * @param rang Le rang de domination à vérifier
	 * @return true si le lycanthrope est le dernier de sa rangée, false sinon
	 */
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
	 * Méthode pour obtenir les lycanthropes ayant le rang de domination OMEGA au sein de la meute
	 * (souffre douleur)
	 * 
	 * @return Un ensemble de lycanthropes ayant le rang de domination OMEGA
	 */
	public Set<Lycanthrope> voirOmega() {
		Set<Lycanthrope> listeOmega = new HashSet<Lycanthrope>();
		for (Lycanthrope l : listeLoup) {
			if (l.getRangDomination() == Enum_RangDomination.OMEGA)
				listeOmega.add(l);
		}
		return listeOmega;
	}
	
	
	
	/**
	 * Méthode permettant de récupérer les informations sur une meute sous forme de chaîne de caractères
	 * 
	 * @return Une chaîne de caractères représentant la meute
	 */
	public String toString() {
		return "Meute se trouvant dans "+enclosReference
				+" avec "+listeLoup.size()+"/"+capaciteMeute+"\n";
	}
	
	
	/**
	 * Méthode permettant de voir les caractéristiques des lycanthropes de la meute sous 
	 * forme de chaîne de caractères
	 * 
	 * @return Une chaîne de caractères représentant les lycanthropes de la meute
	 */
	public String voirLycanthropesMeute() {
		String chaine = "LES LYCANTHROPES DE LA MEUTE : \n";
		for (Lycanthrope l : listeLoup) {
			chaine+=l.toString()+"\n";
		}
		return chaine;
	}
}
