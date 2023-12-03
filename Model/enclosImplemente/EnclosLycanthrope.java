package enclosImplemente;

import java.util.HashSet;
import java.util.Set;

import base.Creature;
import base.Enclos;
import base.Vivipare;
import creaturesImplemente.Lycanthrope;
import meuteLycanthrope.Meute;
import references.CONSTANTES;
import references.Enum_ActionHurlement;
import references.Enum_RangDomination;
import references.Enum_Sexe;


/**
 * La classe EnclosLycanthrope représente un enclos spécifique destiné à accueillir des lycanthropes
 */
public class EnclosLycanthrope extends Enclos {
	
	
	private Meute meutePresente;
	
	/**
	 * Constructeur de la classe EnclosLycanthrope.
	 *
	 * @param nom        Le nom de l'enclos 
	 * @param superficie La superficie de l'enclos
	 */
	public EnclosLycanthrope(String nom, double superficie) {
		super(nom, superficie);
		meutePresente = null;
	}
	
	
	/**
	 * Getters
	 */
	public Meute getMeutePresente () {
		return meutePresente;
	}
	
	
	/**
	 * Setters
	 */
	public void setMeutePresente (Meute m) {
		this.meutePresente = m;
	}
	
	
	/**
	 * Méthode permettant de passer à l'année pour les lycanthropes
	 * @throws Exception 
	 */
	public void passageAnneLycanthrope() throws Exception {
		if (meutePresente != null)
			meutePresente.verificationSeuilFacteurDominationMeute();
		isNecessiteNouvelleMeute();
	}
	
	
	/**
	 * Méthode permettant de vérifier s'il est nécessaire de créer une nouvelle meute
	 * @return La nouvelle meute créée, ou null s'il n'y a pas de besoin
	 * @throws Exception 
	 */
	public Meute isNecessiteNouvelleMeute() throws Exception {
		Lycanthrope maleA = null;
		Lycanthrope femelleA = null;
		if (meutePresente == null) {
			for (Creature l : super.getListeCreatures().values()) {
				if ( ((Lycanthrope)l).getMeute() == null  && maleA==null && ((Lycanthrope)l).getSexe() == Enum_Sexe.Male )
					maleA = (Lycanthrope) l;
				else if ( ((Lycanthrope)l).getMeute() == null  && femelleA==null && ((Lycanthrope)l).getSexe() == Enum_Sexe.Femelle )
					femelleA = (Lycanthrope) l;
			}
			if (maleA!=null && femelleA!= null) {
				Set<Enum_RangDomination> rangPossible = new HashSet<Enum_RangDomination>();
				rangPossible.add(Enum_RangDomination.ALPHA);
				rangPossible.add(Enum_RangDomination.BETA);
				rangPossible.add(Enum_RangDomination.GAMMA);
				rangPossible.add(Enum_RangDomination.OMEGA);
				Meute m = new Meute(femelleA, maleA, CONSTANTES.NB_CREATURE_PAR_ENCLOS_MAX, rangPossible);
				meutePresente = m;
				m.setEnclosReference(this);
				return m;
			}
		}
		return null;
	}


	/**
	 * Méthode permettant d'exprimer l'appartenance collective des lycanthropes
	 * @return Une chaîne de caractères représentant l'expression d'appartenance
	 * @throws Exception Si une erreur survient lors de l'hurlement
	 */
	public String expressionAppartenanceCollective () throws Exception {
		String chaine = "";
		for (Creature l : super.getListeCreatures().values()) {
			chaine += ((Lycanthrope)l).hurler(Enum_ActionHurlement.Appartenance, (Lycanthrope) l);
		}
		return chaine;
	}
	
	
	/**
     * Méthode permettant de concevoir un enfant pour les lycanthropes
     *
     * @param femelle La femelle lycanthrope
     * @param male    Le mâle lycanthrope
     * @return 1 si la conception réussit, -1 sinon
     * @throws Exception Si une erreur survient pendant la conception
     */
    public int concevoirEnfant(Creature femelle, Creature male) throws Exception {
    	if (meutePresente!=null && femelle == meutePresente.getCoupleAlpha().getFemelleAlpha() && male != meutePresente.getCoupleAlpha().getMaleAlpha()) {
    		if (femelle.isVivant() && femelle instanceof Vivipare) {
        		((Vivipare)femelle).concevoirUnEnfant((Vivipare)male, femelle.getDureeGestation());
        		return 1;
        	}
    	}
    	else throw new Exception ("Chez les lycanthropes, seul le couple alpha peut se reproduire");

    	return -1;
    }
	
	
}
