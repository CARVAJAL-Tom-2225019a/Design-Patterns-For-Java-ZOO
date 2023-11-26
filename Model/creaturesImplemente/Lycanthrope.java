package creaturesImplemente;

import base.*;
import interfaces.*;
import meuteLycanthrope.Meute;
import references.*;
import zoo.ZooFantastique;

/**
 * Cette classe correspond à la crature lycanthrope (loup-garou)
 * qui est un vivipare
 * et qui est terrestre
 *
 */
public class Lycanthrope extends Vivipare implements CreatureTerrestre {
	private final int dureeGestation = 1;
	
	/**
	 * Le lycanthrope va heriter de Creatures les attributs suivants :
	 * sexe, categorie d'age, force...
	 */
	
	
	//TODO : agresser, selon niveau de sante... + conséquences

	private int facteurDomination;
	
	private Enum_RangDomination rangDomination;
	private int niveau;
	private int facteurImpetuosite;
	
	private Meute meute;
	
	
    /**
     * Constructeur de la classe Lycanthrope.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param bruit           Le bruit que fait le lycanthrope.
     */


	protected Lycanthrope(Licorne parent1,Licorne parent2, String bruit) {
		super(parent1, parent2, parent1.getDureeGestation());
		this.setAgressivite(Enum_Aggressivite.pacifique);
		this.setNomEspece(Enum_Especes.Licorne);
		this.setDureeGestation(dureeGestation);
		this.setBruit( bruit);
		this.calculerForce();
		facteurDomination = 0;
		rangDomination=null;
		niveau = 0;
		facteurImpetuosite=ZooFantastique.getIntAleatoire(CONSTANTES.MAX_FACTEUR_IMPETUOSITE);
		meute = null;
	}
	protected Lycanthrope(  String bruit) {
		super();
		this.setAgressivite(Enum_Aggressivite.pacifique);
		this.setNomEspece(Enum_Especes.Licorne);
		this.setDureeGestation(dureeGestation);
		this.setBruit( bruit);
		this.calculerForce();
		facteurDomination = 0;
		rangDomination=null;
		niveau = 0;
		facteurImpetuosite=ZooFantastique.getIntAleatoire(CONSTANTES.MAX_FACTEUR_IMPETUOSITE);
		meute = null;
	}
    
    
    /**
     * Getters
     */
    public int getFacteurDomination() {
    	return facteurDomination;
    }
    public Enum_RangDomination getRangDomination() {
    	return rangDomination;
    }
    public void setRangDomination(Enum_RangDomination rang) {
    	rangDomination=rang;;
    }
    public int getNiveau() {
    	return niveau;
    }
    public int getFacteurImpetuosite() {
    	return facteurImpetuosite;
    }
    public Meute getMeute() {
    	return meute;
    }
    
    
    
    public void calculNiveau() {
    	niveau = 0;
    	//facteur domination, rang
    	if (super.getCategorieAge() == Enum_CategorieAge.BEBE) {
    		niveau += 1;
    	}
    	else if (super.getCategorieAge() == Enum_CategorieAge.ENFANT) {
    		niveau += 2;
    	}
    	else if (super.getCategorieAge() == Enum_CategorieAge.JEUNE) {
    		niveau += 3;
    	}
    	else if (super.getCategorieAge() == Enum_CategorieAge.ADULTE) {
    		niveau += 4;
    	}
    	else if (super.getCategorieAge() == Enum_CategorieAge.VIEUX) {
    		niveau += 5;
    	}
    	niveau+=super.getForce();
    	niveau+=facteurDomination;
    	niveau+=rangDomination.getValeur();
    }
    
    
    public boolean seuilFacteurDominationAtteint () {
    	if (facteurDomination < CONSTANTES.SEUIL_FACTEUR_DOMINATION) {
    		return true;
    	}
    	else
    		return false;
    }

    
    /**
     * Méthode de l'interface CreatureTerrestre : Courrir.
     * Permet au lycanthrope de courir sur terre.
     * 
     * @return Un message indiquant que le lycanthrope court.
     * @throws Exception 
     */
    @Override
    public String courrir() throws Exception {
        super.perdreNourriture();
        super.perdreSommeil();
        return "Le lycanthrope court";
    }
    
    
    /**
     * Méthode pour mettre bas une nouvelle créature
     * 
     * @return Une instance de la classe Creature qui né.
     */
    public Creature mettreBas() throws Exception {
    	return super.mettreBas();
    }
    
    
    /**
     * Methode renvoyant les informations pour l'affichage d'une creature
     * 
     * @return la chaine de caractere contenant les informations
     */
    public String toString() {
    	return "-- Lycanthrope ="
    				+"\n   sexe : "+getSexe()
    				+"\n   age : "+getAge()
    				+"\n   vivant : "+isVivant()
    				+"\n   dort : "+isEnTrainDeDormir()
    				+"\n   faim : "+getIndicateurFaim()+"/"+CONSTANTES.MAX_INDICATEUR
    				+"\n   fatigue : "+getIndicateurSommeil()+"/"+CONSTANTES.MAX_INDICATEUR
    				+"\n   sante : "+getIndicateurSante()+"/"+CONSTANTES.MAX_INDICATEUR
    				+"\n"
    				+"\n   categorie age :"+getCategorieAge()
    				+"\n   force : "+getForce()
    				+"\n   facteur domination"+getFacteurDomination()
    				+"\n   rang domination : "+rangDomination.getDescription()
    				+"\n   niveau : "+niveau
    				+"\n   facteur impetuosite : "+facteurImpetuosite
    				+"\n\n";
    }
    
    public String toStringReduit() {
    	return "-- Lycanthrope "+getPrenom()+", sexe : "+getSexe()
				+", age : "+getAge()+"\n";  
    }
    
    
    /**
     * Methode permettant d'hurler
     * @param action	que le lycanthrope veut effectuer par son hurlement
     * @return
     * @throws Exception
     */
    public String hurler(Enum_ActionHurlement action, Lycanthrope loup) throws Exception {
    	String chaine = "";
    	if (super.isVivant() && !super.isEnTrainDeDormir() && super.getIndicateurSante()>CONSTANTES.VALEUR_INDICATEUR_MAUVAIS) {
    		// Exprimer appartenance
    		if (action == Enum_ActionHurlement.Appartenance) {
    			chaine =  super.toString()+" \n "+exprimerAppartenance();
    		}
    		// Exprimer domination
    		else if (action == Enum_ActionHurlement.Domination) {
    			chaine = super.toString()+"\n"+exprimerDomination(loup);
    			chaine +="\n"+hurler(Enum_ActionHurlement.Agressivite, loup);
    		}
        	// Exprimer soumission
    		else if (action == Enum_ActionHurlement.Soumission) {
    			chaine = super.toString()+"\n"+exprimerSoumission (loup);
    		}
        	// Exprimer aggresivite
    		else if (action == Enum_ActionHurlement.Agressivite) {
    			// TODO : if souffre douleur
    			chaine = super.toString()+"\n"+exprimerAgressivite (loup);
    		}
    		else
    			throw new Exception ("Choix hurlement lycanthrope invalide\n");
    		chaine += loup.entendreHurlement(action, this);
    		return chaine;
    	}
    	else
    		throw new Exception("Le lycanthrope n'est pas en etat d'hurler\n");
    }
    
    
    /**
     * Methodes permettant d'effectuer une action correspondant a un hurlement
     */
    private String exprimerAppartenance() {
    	if (meute == null)
    		return "Je suis un loup solitaire, et je n'ai peur peur de rien\n";
    	else
    		return "Ma meute, la meilleure, est "+meute+"\n";
    }
    
    private String exprimerDomination (Lycanthrope loup) throws Exception {
    	if (rangDomination.getValeur() >= loup.getRangDomination().getValeur()) {
    		if (meute==null || meute.getCoupleAlpha().getFemelleAlpha()!= loup) {
    			facteurDomination++;
            	return "Je suis un "+rangDomination.getDescription()+", et je te domine toi "+loup.getRangDomination().getDescription()+"\n";
    		}
    		else
    			throw new Exception ("Domination impossible");
    	}
    	else {
    		loup.facteurDomination++;
    		return exprimerSoumission(loup);
    	}
    }
    
    private String exprimerSoumission (Lycanthrope loup) {
    	facteurDomination--;
    	// rang inferieur
    	rangDomination = rangDomination.getRangInferieur();
    	return "Je suis un "+rangDomination.getDescription()+", et je me soumet a toi "+loup.getRangDomination().getDescription()+"\n";
    }
    
    private String exprimerAgressivite (Lycanthrope loup) throws Exception {
    	String chaine =  "Je suis agressif d'un niveau de "+facteurImpetuosite+"/"+CONSTANTES.MAX_FACTEUR_IMPETUOSITE+"\n";
    	if (isPlusFort(loup))
    		chaine+=agresser(loup);
    	else
    		chaine+="Je ne suis pas asses fort pour te defier...\n";
    	return chaine;
    }
    
    
    /**
     * Methode simulant une agression d'un loup vers un autre loup
     * @param loup
     * @return
     * @throws Exception
     */
    private String agresser (Lycanthrope loup) throws Exception {
    	if (loup != meute.getCoupleAlpha().getFemelleAlpha()) {
    		if (loup.getRangDomination() == Enum_RangDomination.OMEGA || loup == meute.getCoupleAlpha().getMaleAlpha()) {
    			loup.perdreSommeil();
            	loup.perdreSante();
            	loup.perdreNourriture();
            	loup.facteurDomination--;
            	this.perdreNourriture();
            	this.perdreSommeil();
            	this.facteurDomination++;
            	return "ATTAQUE DU LYCANTHROPE !\n";
    		}
    		else
    			throw new Exception ("Impossible d'attaquer ce lycanthrope");
    	}
    	else
    		throw new Exception ("Impossible d'attaquer la femelle alpha\n");
    }
    
    public boolean isPlusFort(Lycanthrope loup2) {
    	if (rangDomination.getValeur() > loup2.getRangDomination().getValeur()) {
    		return true;
    	}
    	else if (rangDomination.getValeur() < loup2.getRangDomination().getValeur()) {
    		return false;
    	}
    	else {
    		if (niveau > loup2.getNiveau())
    			return true;
    		else
    			return false;
    	}
    }
    
    
    /**
     * Methode permettant a un loup d'entendre un hurlement
     * @throws Exception 
     */
    private String entendreHurlement(Enum_ActionHurlement action, Lycanthrope loupOrigine) throws Exception {
    	if (super.isVivant() && !super.isEnTrainDeDormir()) {
    		if (action == Enum_ActionHurlement.Appartenance) {
    			return "Je ne te crois pas superieur a moi !\n";
    		}
    		// Exprimer domination
    		else if (action == Enum_ActionHurlement.Domination) {
    			// se soumet a la domination
    			return exprimerSoumission(loupOrigine);
    		}
        	// Exprimer soumission
    		else if (action == Enum_ActionHurlement.Soumission) {
    			// domine
    			return exprimerDomination(loupOrigine);
    		}
        	// Exprimer aggresivite
    		else if (action == Enum_ActionHurlement.Agressivite) {
    			return "Tu ne me fais pas peur !\n";
    		}
    		else
    			throw new Exception ("Choix hurlement lycanthrope invalide\n");
    	}
    	else
    		throw new Exception("Le lycanthrope n'est pas en etat d'entendre le hurlement\n");
    }
    
    
    /**
     * Methode permeyttant a un lycanthrope de rejoindre une meute
     * @throws Exception
     */
    public void rejoindreMeute(Meute m) throws Exception {
    	if (meute.addLoup(this))
    		meute = m;
    	else
    		throw new Exception ("Impossible de rejoindre cette meute");
    }
    
    
    /**
     * Methode permeyttant a un lycanthrope de quitter sa meute et
     * devenir solitaire
     * @throws Exception
     */
    public void seSeparerDeSaMeute() throws Exception {
    	if(meute.removeLoup(this)) {
    		rangDomination = null;
    		meute = null;
    	}
    	else
    		throw new Exception ("Le loup ne peut pas quitter sa meute");
    }
    
    
    /**
     * Methode permettant au loup de se transofrmer en humain
     * @return l'instance de l'humain créé
     */
    public Humain SeTransformerEnHumain() {
    	//TODO : changer pour le nom de l'humain
    	//TODO : selon niveau
    	//TODO : boulverse organisation meute
    	return new Humain("Humain", super.getSexe(), super.getAge());
    }
    
}
