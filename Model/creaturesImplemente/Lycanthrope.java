package creaturesImplemente;

import java.util.Random;

import base.*;
import interfaces.*;
import meuteLycanthrope.Meute;
import references.*;

public class Lycanthrope extends Vivipare implements CreatureTerrestre {
	//TODO : calcul niveau
	
	//TODO : selon hurlement, action
	// permet de communiquer avec les autres loups
	
	// TODO : se separer de sa meute
	
	//TODO : entendre hurlement d'un autre loup
	
	//TODO : rejoindre meute
	
	//TODO : se battre
	
	// TODO : dominer un autre pair (selon rang, echec ou reussite)
	// pas femelle alpha
	// si reussite : echange rang
	// si echec : perte d'un rang
	
	// TODO : a partir du 3.3

	private Enum_CategorieAge categorieAge;
	private int force;
	
	private int dominationsExercercees;
	private int dominationsSubies;
	
<<<<<<< HEAD
	private int rangDomination;
	private int niveau;
	private int facteurImpetuosite;
	
=======
	private Enum_RangDomination rangDomination;
	private int niveau;
	private int facteurImpetuosite;
	
	private Meute meute;
	
>>>>>>> ad06737 (Debut Lycanthrope ameliore, ajout choix actions (sport et chant))
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
<<<<<<< HEAD
        rangDomination=getIntAleatoire(CONSTANTES.MAX_RANG_DOMINATION);
        niveau = calculNiveau();
        facteurImpetuosite=getIntAleatoire(CONSTANTES.MAX_FACTEUR_IMPETUOSITE);
=======
        //TODO : rang domination dans enum
        rangDomination=null;
        niveau = calculNiveau();
        facteurImpetuosite=getIntAleatoire(CONSTANTES.MAX_FACTEUR_IMPETUOSITE);
        meute = null;
>>>>>>> ad06737 (Debut Lycanthrope ameliore, ajout choix actions (sport et chant))
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
<<<<<<< HEAD
    public int getRangDomination() {
=======
    public Enum_RangDomination getRangDomination() {
>>>>>>> ad06737 (Debut Lycanthrope ameliore, ajout choix actions (sport et chant))
    	return rangDomination;
    }
    public int getNiveau() {
    	return niveau;
    }
    public int getFacteurImpetuosite() {
    	return facteurImpetuosite;
    }
<<<<<<< HEAD
    
=======
    public Meute getMeute() {
    	return meute;
    }
>>>>>>> ad06737 (Debut Lycanthrope ameliore, ajout choix actions (sport et chant))
    
    
    private int getIntAleatoire(int max) {
    	Random random = new Random();
    	return random.nextInt(max);
    }
    
    
<<<<<<< HEAD
    private int calculNiveau() {
    	//TODO : calcul niveau
=======
    
    private void RejoindreMeute (Meute m) {
    	return;
    }
    
    private int calculNiveau() {
>>>>>>> ad06737 (Debut Lycanthrope ameliore, ajout choix actions (sport et chant))
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
    				+"\n   rang domination : "+rangDomination
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
<<<<<<< HEAD
=======
    
    
    public void Hurler() {

    }
    
    public void EntendreHurlement() {
    	if (super.isVivant() && !super.isEnTrainDeDormir() && super.getIndicateurSante()>CONSTANTES.VALEUR_INDICATEUR_MAUVAIS) {
    		
    	}
    }
    
    public void SeSeparerDeSaMeute() {
    	
    }
    
    public Humain SeTransformerEnHumain() {
    	//TODO : changer pour le nom de l'humain
    	return new Humain("Humain", super.getSexe(), super.getAge());
    }
    
>>>>>>> ad06737 (Debut Lycanthrope ameliore, ajout choix actions (sport et chant))
}
