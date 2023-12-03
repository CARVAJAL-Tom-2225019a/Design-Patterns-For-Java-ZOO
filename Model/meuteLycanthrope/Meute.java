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
	
	private String nomMeute;
	
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
	public Meute(String nomMeute, Lycanthrope femelleAlpha, Lycanthrope maleAlpha, int capaciteMeute, 
			Set<Enum_RangDomination> rangPossible) throws Exception {
		this.nomMeute = nomMeute;
		this.rangPossible=rangPossible; 
		if (rangPossible.contains(Enum_RangDomination.OMEGA)) {
			rangPossible.add(Enum_RangDomination.OMEGA);
		}
		femelleAlpha.setRangDomination(Enum_RangDomination.ALPHA);
		femelleAlpha.calculerForce();
		maleAlpha.setRangDomination(Enum_RangDomination.ALPHA);
		maleAlpha.calculerForce();
		coupleAlpha = new CoupleAlpha(femelleAlpha, maleAlpha);
		this.capaciteMeute = capaciteMeute;
		this.listeLoup = new HashSet<Lycanthrope>();
		addLoup(femelleAlpha);
		addLoup(maleAlpha);
		this.enclosReference = null;
	}
	
	
	/**
	 * Methode permettant de recuperer le couple alpha de la meute
	 * @return le couple alpha de la meute
	 */
	public CoupleAlpha getCoupleAlpha() {
		return coupleAlpha;
	}
	/**
	 * Methode permettant de recuperer la capacite de la meute
	 * @return la capacite de la meute
	 */
	public int getCapaciteMeute() {
		return capaciteMeute;
	}
	/**
	 * Methode permettant de recuperer les loups appartenant a la meute
	 * @return un ensemble de loups de la meute
	 */
	public Set<Lycanthrope> getListeLoup() {
		return listeLoup;
	}
	/**
	 * Methode permettant de recuperer les rangs que peuvent prendre les loups dans la meute
	 * @return un ensemble contenant les rangs possibles
	 */
	public Set<Enum_RangDomination> getRangPossible() {
		return rangPossible;
	}
	/**
	 * Methode permettant de recuperer l'enclos où se trouve la meute
	 * @return l'enclos où se trouve la meute
	 */
	public Enclos getEnclosReference() {
		return enclosReference;
	}
	/**
	 * Methode permettant de recuperer le nom de la meute
	 * @return le nom de la meute
	 */
	public String getNomMeute() {
		return nomMeute;
	}
	
	
	/**
	 * Modifier l'enclos de reference (là où se trouve la meute)
	 * @param e l'enclos
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
	 * @param loup Le lycanthrope auquel il faut attribuer le rang
	 */
	public void affecterRang(Lycanthrope loup) {
		double forceLoup = loup.getForce();
        double forceAlpha = coupleAlpha.getMaleAlpha().getForce();
        // Si la liste de loups est vide, le loup devient OMEGA
        if (listeLoup.isEmpty()) {
            loup.setRangDomination(Enum_RangDomination.OMEGA);
            listeLoup.add(loup);
            return;
        }
        // Si le loup a une force supérieure au male alpha, il devient ALPHA
        if (forceLoup > forceAlpha) {
            loup.setRangDomination(Enum_RangDomination.ALPHA);
            listeLoup.add(loup);
            return;
        }
        // Initialisation d'un HashSet pour suivre les rangs déjà attribués
        Set<Enum_RangDomination> rangsAttribues = new HashSet<>();
        for (Lycanthrope l : listeLoup) {
            rangsAttribues.add(l.getRangDomination());
        }
        // Vérifier les rangs déjà attribués et attribuer le premier rang disponible
        for (Enum_RangDomination rang : rangPossible) {
            if (!rangsAttribues.contains(rang)) {
                loup.setRangDomination(rang);
                listeLoup.add(loup);
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
        listeLoup.add(loup);
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
		return "Meute "+nomMeute+" se trouvant dans "+enclosReference.getNom()
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
	
	
	/**
	 * Methode permettant de recuperer le premier loup de la meute
	 * qui ne fait pas partie du couple alpha
	 * @return Le loup en question, sinon null
	 */
	public Lycanthrope choixPremierLoupPasCoupleAlpha() {
		for (Lycanthrope l : listeLoup) {
			if (l!=coupleAlpha.getFemelleAlpha() && l!=coupleAlpha.getMaleAlpha())
				return l;
		}
		return null;
	}
}
