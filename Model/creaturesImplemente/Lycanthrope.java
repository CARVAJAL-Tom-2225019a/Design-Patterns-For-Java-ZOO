package creaturesImplemente;

import java.util.Random;

import base.*;
import interfaces.*;
import meuteLycanthrope.Meute;
import references.*;

/**
 * Cette classe correspond à la crature lycanthrope (loup-garou)
 * qui est un vivipare
 * et qui est terrestre
 *
 */
public class Lycanthrope extends Vivipare implements CreatureTerrestre {
	//TODO : calcul niveau
	
	//TODO : se battre
	
	// TODO : a partir du 3.3

	private Enum_CategorieAge categorieAge;
	private int force;
	
	private int dominationsExercercees;
	private int dominationsSubies;
	
	private Enum_RangDomination rangDomination;
	private int niveau;
	private int facteurImpetuosite;
	
	private Meute meute;
	
	
    /**
     * Constructeur de la classe Lycanthrope.
     * Protected afin que la création se fasse essentiellement depuis le factory
     * 
     * @param nomEspece       L'espèce du lycanthrope.
     * @param sexe            Le sexe du lycanthrope.
     * @param poids           Le poids du lycanthrope.
     * @param taille          La taille du lycanthrope.
     * @param bruit           Le bruit que fait le lycanthrope.
     * @param dureeGestation  La durée de gestation spécifique pour les lycanthrope.
     */
    protected Lycanthrope(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, int dureeGestation) {
        super(nomEspece, sexe, poids, taille, bruit, dureeGestation);
        categorieAge = Enum_CategorieAge.jeune;
        this.force = getIntAleatoire(CONSTANTES.MAX_FORCE);
        dominationsExercercees=0;
        dominationsSubies=0;
        rangDomination=Enum_RangDomination.OMEGA;
        niveau = calculNiveau();
        facteurImpetuosite=getIntAleatoire(CONSTANTES.MAX_FACTEUR_IMPETUOSITE);
        meute = null;
    } 
    
    
    /**
     * Getters
     */
    public Enum_CategorieAge getCategorieAge() {
    	return categorieAge;
    }
    public int getForce() {
    	return force;
    }
    public int getDominationsExercercees() {
    	return dominationsExercercees;
    }
    public int getDominationsSubies() {
    	return dominationsSubies;
    }
    public int getFacteurDomination() {
    	return dominationsExercercees-dominationsSubies;
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
    
    
    /**
     * Methode permetant de generer un entier aleatoire entre 0 et max
     * @param max	Entier maximal
     * @return	Le nombre aleatoire
     */
    private int getIntAleatoire(int max) {
    	Random random = new Random();
    	return random.nextInt(max);
    }
    
    
    private int calculNiveau() {
    	return 0;
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
    public Creature MettreBas(Enum_Sexe sexe, double poids, double taille) throws Exception {
    	return super.MettreBas(sexe, poids, taille);
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
        int curseur = CONSTANTES.MAX_AGE/3;
        // changement categorie age
        if (super.getAge()>0 && super.getAge()<curseur) {
        	categorieAge = Enum_CategorieAge.jeune;
        }
        else if (super.getAge()>=curseur && super.getAge()<curseur*2) {
        	categorieAge = Enum_CategorieAge.adulte;
        }
        else if (super.getAge()>=curseur*2 && curseur*2<=CONSTANTES.MAX_AGE) {
        	categorieAge = Enum_CategorieAge.vieux;
        }
        if (super.isVivant()){
        	categorieAge = Enum_CategorieAge.mort;
        }
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
    			chaine =  super.getBruit()+"\n"+ExprimerAppartenance();
    		}
    		// Exprimer domination
    		else if (action == Enum_ActionHurlement.Domination) {
    			chaine = super.getBruit()+"\n"+ExprimerDomination (loup);
    		}
        	// Exprimer soumission
    		else if (action == Enum_ActionHurlement.Soumission) {
    			chaine = super.getBruit()+"\n"+ExprimerSoumission (loup);
    		}
        	// Exprimer aggresivite
    		else if (action == Enum_ActionHurlement.Agressivite) {
    			chaine = super.getBruit()+"\n"+ExprimerAgressivite ();
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
    			dominationsExercercees++;
    			// Echange de rang ??
    			//Enum_RangDomination temp = loup.getRangDomination();
    			//loup.setRangDomination(this.getRangDomination());
    			//this.setRangDomination(temp);
            	return "Je suis un "+rangDomination.getDescription()+", et je te domine toi "+loup.getRangDomination().getDescription()+"\n";
    		}
    		else
    			throw new Exception ("Domination impossible");
    	}
    	else {
    		return ExprimerSoumission(loup);
    	}
    }
    
    private String ExprimerSoumission (Lycanthrope loup) {
    	dominationsSubies++;
    	// rang inferieur
    	rangDomination = rangDomination.getRangInferieur();
    	return "Je suis un "+rangDomination.getDescription()+", et je me soumet a toi "+loup.getRangDomination().getDescription()+"\n";
    }
    
    private String ExprimerAgressivite () {
    	return "Je suis agressif d'un niveau de "+facteurImpetuosite+"/"+CONSTANTES.MAX_FACTEUR_IMPETUOSITE+", alors"
    			+ "ne me defie pas !\n";
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

enum Enum_ActionHurlement {
	Appartenance, Domination, Soumission, Agressivite
}
