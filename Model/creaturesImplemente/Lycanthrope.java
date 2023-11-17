package creaturesImplemente;

import java.util.Random;

import base.*;
import interfaces.*;
import references.*;

public class Lycanthrope extends Vivipare implements CreatureTerrestre {

	private Enum_CategorieAge categorieAge;
	private int force;
	
	private int dominationsExercercees;
	private int dominationsSubies;
	
	private int rangDomination;
	private int niveau;
	private int facteurImpetuosite;
	
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
        rangDomination=getIntAleatoire(CONSTANTES.MAX_RANG_DOMINATION);
        niveau = calculNiveau();
        facteurImpetuosite=getIntAleatoire(CONSTANTES.MAX_FACTEUR_IMPETUOSITE);
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
    public int getRangDomination() {
    	return rangDomination;
    }
    public int getNiveau() {
    	return niveau;
    }
    public int getFacteurImpetuosite() {
    	return facteurImpetuosite;
    }
    
    
    
    private int getIntAleatoire(int max) {
    	Random random = new Random();
    	return random.nextInt(max);
    }
    
    
    private int calculNiveau() {
    	//TODO : calcul niveau
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
     */
    @Override
    public String Courrir() {
        // TODO: Implémentez la logique spécifique de course du lycanthrope
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
}
