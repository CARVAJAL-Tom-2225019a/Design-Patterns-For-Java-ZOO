package enclosImplemente;

import java.util.HashSet;
import java.util.Set;

import base.Creature;
import base.Enclos;
import creaturesImplemente.Lycanthrope;
import meuteLycanthrope.Meute;
import references.CONSTANTES;
import references.Enum_ActionHurlement;
import references.Enum_RangDomination;
import references.Enum_Sexe;

public class EnclosLycanthrope extends Enclos {
	
	// TODO : passage année lycanthrope
	
	
	private Meute meutePresente;
	
	
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
	
	
	public void PassageAnneLycanthrope() {
		if (meutePresente != null)
			meutePresente.VerificationSeuilFacteurDominationMeute();
		isNecessiteNouvelleMeute();
	}
	
	
	private boolean isNecessiteNouvelleMeute() {
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
				return true;
			}
		}
		return false;
	}


	
	public String ExpressionAppartenanceCollective () throws Exception {
		String chaine = "";
		for (Creature l : super.getListeCreatures().values()) {
			chaine += ((Lycanthrope)l).Hurler(Enum_ActionHurlement.Appartenance, (Lycanthrope) l);
		}
		return chaine;
	}
	
	
}
