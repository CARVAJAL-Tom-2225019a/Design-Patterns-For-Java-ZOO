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
	//TODO : calcul niveau
	
	//TODO : agresser, selon niveau de sante... + conséquences
	
	
	//TODO : Les lycanthropes communiquent au moyen 
	// de hurlements (qui sont perçus depuis n’importe quel 
	// enclos dans un zoo fantastique) */

	
	// TODO : a partir du 3.5

	private Enum_CategorieAge categorieAge;
	private int force;
	
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
		this.CalculerForce();
		facteurDomination = 0;
		rangDomination=Enum_RangDomination.OMEGA;
		niveau = calculNiveau();
		facteurImpetuosite=ZooFantastique.getIntAleatoire(CONSTANTES.MAX_FACTEUR_IMPETUOSITE);
		meute = null;
	}
	protected Lycanthrope(  String bruit) {
		super();
		this.setAgressivite(Enum_Aggressivite.pacifique);
		this.setNomEspece(Enum_Especes.Licorne);
		this.setBruit( bruit);
		this.CalculerForce();
		this.setDureeGestation(dureeGestation);

		facteurDomination = 0;
		rangDomination=Enum_RangDomination.OMEGA;
		niveau = calculNiveau();
		facteurImpetuosite=ZooFantastique.getIntAleatoire(CONSTANTES.MAX_FACTEUR_IMPETUOSITE);
		meute = null;
	}
    
    
    /**
     * Getters
     */
    public Enum_CategorieAge getCategorieAge() {
    	return categorieAge;
    }
    public double getForce() {
    	return force;
    }
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
    
    
    
    private int calculNiveau() {
    	return 0;
    }
    
    
    public boolean SeuilFacteurDominationAtteint () {
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
     *         Note : Dans cet exemple, la méthode ne fait rien (retourne null),
     *         car la logique spécifique de course du lycanthrope n'est pas encore implémentée.
     *         Vous devriez remplacer le retour null par la logique réelle de course du lycanthrope.
     * @throws Exception 
     */
    @Override
    public String Courrir() throws Exception {
        super.PerdreNourriture();
        super.PerdreSommeil();
        return "Le lycanthrope court";
    }
    
    
    /**
     * Méthode pour mettre bas une nouvelle créature
     * 
     * @return Une instance de la classe Creature qui né.
     */
    public Creature MettreBas() throws Exception {
    	return super.MettreBas();
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
    				+"\n   categorie age :"+categorieAge
    				+"\n   force : "+force
    				+"\n   facteur domination"+getFacteurDomination()
    				+"\n   rang domination : "+rangDomination.getDescription()
    				+"\n   niveau : "+niveau
    				+"\n   facteur impetuosite : "+facteurImpetuosite
    				+"\n\n";
    }
    
    
    /**
     * Méthode pour faire vieillir la créature d'un an
     *
     */
    public void Vieillir() throws Exception {
        super.Vieillir();
        // changement categorie age
        categorieAge = Enum_CategorieAge.getCategorieByAge(this.getAge());
    }
    
    
    /**
     * Methode permettant d'hurler
     * @param action	que le lycanthrope veut effectuer par son hurlement
     * @return
     * @throws Exception
     */
    public String Hurler(Enum_ActionHurlement action, Lycanthrope loup) throws Exception {
    	String chaine = "";
    	if (super.isVivant() && !super.isEnTrainDeDormir() && super.getIndicateurSante()>CONSTANTES.VALEUR_INDICATEUR_MAUVAIS) {
    		// Exprimer appartenance
    		if (action == Enum_ActionHurlement.Appartenance) {
    			chaine =  super.toString()+" \n "+ExprimerAppartenance();
    		}
    		// Exprimer domination
    		else if (action == Enum_ActionHurlement.Domination) {
    			chaine = super.toString()+"\n"+ExprimerDomination(loup);
    			chaine +="\n"+Hurler(Enum_ActionHurlement.Agressivite, loup);
    		}
        	// Exprimer soumission
    		else if (action == Enum_ActionHurlement.Soumission) {
    			chaine = super.toString()+"\n"+ExprimerSoumission (loup);
    		}
        	// Exprimer aggresivite
    		else if (action == Enum_ActionHurlement.Agressivite) {
    			// TODO : if souffre douleur
    			chaine = super.toString()+"\n"+ExprimerAgressivite (loup);
    		}
    		else
    			throw new Exception ("Choix hurlement lycanthrope invalide\n");
    		chaine += loup.EntendreHurlement(action, this);
    		return chaine;
    	}
    	else
    		throw new Exception("Le lycanthrope n'est pas en etat d'hurler\n");
    }
    
    
    /**
     * Methodes permettant d'effectuer une action correspondant a un hurlement
     */
    private String ExprimerAppartenance() {
    	if (meute == null)
    		return "Je suis un loup solitaire, et je n'ai peur peur de rien\n";
    	else
    		return "Ma meute, la meilleure, est "+meute+"\n";
    }
    
    private String ExprimerDomination (Lycanthrope loup) throws Exception {
    	if (rangDomination.getValeur() >= loup.getRangDomination().getValeur()) {
    		if (meute==null || meute.getFemelleAlpha()!= loup) {
    			facteurDomination++;
            	return "Je suis un "+rangDomination.getDescription()+", et je te domine toi "+loup.getRangDomination().getDescription()+"\n";
    		}
    		else
    			throw new Exception ("Domination impossible");
    	}
    	else {
    		loup.facteurDomination++;
    		return ExprimerSoumission(loup);
    	}
    }
    
    private String ExprimerSoumission (Lycanthrope loup) {
    	facteurDomination--;
    	// rang inferieur
    	rangDomination = rangDomination.getRangInferieur();
    	return "Je suis un "+rangDomination.getDescription()+", et je me soumet a toi "+loup.getRangDomination().getDescription()+"\n";
    }
    
    private String ExprimerAgressivite (Lycanthrope loup) throws Exception {
    	return "Je suis agressif d'un niveau de "+facteurImpetuosite+"/"+CONSTANTES.MAX_FACTEUR_IMPETUOSITE+", "
    			+agresser(loup);
    }
    
    
    /**
     * Methode simulant une agression d'un loup vers un autre loup
     * @param loup
     * @return
     * @throws Exception
     */
    private String agresser (Lycanthrope loup) throws Exception {
    	if (loup != meute.getFemelleAlpha()) {
    		if (loup.getRangDomination() == Enum_RangDomination.OMEGA || loup == meute.getMaleAlpha()) {
    			loup.PerdreSommeil();
            	loup.PerdreSante();
            	loup.PerdreNourriture();
            	loup.facteurDomination--;
            	this.PerdreNourriture();
            	this.PerdreSommeil();
            	this.facteurDomination++;
            	return "ATTAQUE DU LYCANTHROPE !\n";
    		}
    		else
    			throw new Exception ("Impossible d'attaquer ce lycanthrope");
    	}
    	else
    		throw new Exception ("Impossible d'attaquer la femelle alpha\n");
    }
    
    
    /**
     * Methode permettant a un loup d'entendre un hurlement
     * @throws Exception 
     */
    private String EntendreHurlement(Enum_ActionHurlement action, Lycanthrope loupOrigine) throws Exception {
    	if (super.isVivant() && !super.isEnTrainDeDormir()) {
    		if (action == Enum_ActionHurlement.Appartenance) {
    			return "Je ne te crois pas superieur a moi !\n";
    		}
    		// Exprimer domination
    		else if (action == Enum_ActionHurlement.Domination) {
    			// se soumet a la domination
    			return ExprimerSoumission(loupOrigine);
    		}
        	// Exprimer soumission
    		else if (action == Enum_ActionHurlement.Soumission) {
    			// domine
    			return ExprimerDomination(loupOrigine);
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
    public void RejoindreMeute(Meute m) throws Exception {
    	if (meute.AddLoup(this))
    		meute = m;
    	else
    		throw new Exception ("Impossible de rejoindre cette meute");
    }
    
    
    /**
     * Methode permeyttant a un lycanthrope de quitter sa meute et
     * devenir solitaire
     * @throws Exception
     */
    public void SeSeparerDeSaMeute() throws Exception {
    	if(meute.RemoveLoup(this))
    		meute = null;
    	else
    		throw new Exception ("Le loup ne peut pas quitter sa meute");
    }
    
    
    /**
     * Methode permettant au loup de se transofrmer en humain
     * @return l'instance de l'humain créé
     */
    public Humain SeTransformerEnHumain() {
    	//TODO : changer pour le nom de l'humain
    	return new Humain("Humain", super.getSexe(), super.getAge());
    }
    
}
