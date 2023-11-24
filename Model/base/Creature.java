package base;

import java.util.ArrayList;
import java.util.Random;
import references.*;
import zoo.ZooFantastique;


/**
 * Cette classe abstraite représente une entité vivante dans le zoo fantastique
 *
 */
public abstract class Creature {

    private Enum_Especes nomEspece; // TODO a defenir dans chaque constructeur
    private Enum_Sexe sexe;
    private double poids;
    private double taille;

    private int age;
    private Enum_CategorieAge categorieAge;


    private int indicateurFaim; // Indice de satiété
    private int indicateurSommeil;
    private int indicateurSante;

    private boolean enTrainDeDormir;
    private boolean vivant;
    private String bruit;
    private int dureeGestation; // ans

    private int bonheur;
    private Enum_Aggressivite agressivite;
    private ArrayList<Creature> listeEnfants;
    private ArrayList<Creature> listeParents; // doit en avoir max 2.
    private String prenom;
    private double force ;//calculé sur status (grand age + grand poid + grande taille + grande santé +
                         // grande faim + male (plus agressif) + bien dormis +
                        // status chef d'enclos (alpha) + nb comats vaincu
    private int combatVaincu;
    private double valeurNutrionelle;

    private Enum_RangDomination status ;


    /**
     * Constructeur de la classe Creature par defaut. créature completement aléatoire.
     */
    public Creature (){
        this.sexe= SexeAleatoire();
        this.age = ZooFantastique.getIntAleatoire(CONSTANTES.MAX_AGE/2);
        this.categorieAge = Enum_CategorieAge.getCategorieByAge(age);
        this.bonheur = ZooFantastique.getIntAleatoire(100);
        this.poids = ZooFantastique.getIntAleatoire(CONSTANTES.MAX_POIDS);
        this.taille = ZooFantastique.getIntAleatoire(CONSTANTES.MAX_TAILLE);
        this.indicateurFaim = CONSTANTES.MAX_INDICATEUR;
        this.indicateurSommeil = CONSTANTES.MAX_INDICATEUR;
        this.indicateurSante = CONSTANTES.MAX_INDICATEUR;
        this.enTrainDeDormir = false;
        this.vivant = true;
        this.listeEnfants= new ArrayList<Creature>();
        this.listeParents= new ArrayList<Creature>();
        this.valeurNutrionelle = poids * taille;
        this.combatVaincu = 0;
        if (sexe == Enum_Sexe.Male){
            this.prenom = Enum_PrenomMasculin.getRandomName().name() ;
        }
        else
            this.prenom = Enum_PrenomFeminin.getRandomName().name() ;
        this.force = CalculerForce();
        
        // c'est abstrait comme classe donc pas grave si : 
        this.status = Enum_RangDomination.ALPHA ; //a surcharger plus tard
        this.bruit = ""; // a sucharger plus tard
        this.nomEspece = null; // a surcharger plus tard
        this.agressivite = Enum_Aggressivite.pacifique; // par default, pourra changer selon l'espece par surcharge
        this.dureeGestation = 1;// sera surcharger plus tard

    }
    
    /**
     * Constructeur de la classe Creature a partir de deux parents.
     * @param Parent1
     * @param Parent2
     */
    public Creature ( Creature Parent1, Creature Parent2) {
        this();
        this.nomEspece = Parent1.getNomEspece();
        int defaultValue = 1; // grandi avec le temps
        this.poids = defaultValue ;
        this.taille = defaultValue ;
        this.age = defaultValue ;
        this.valeurNutrionelle = poids * taille;
        this.categorieAge = Enum_CategorieAge.getCategorieByAge(age);
        this.dureeGestation = Parent1.getDureeGestation();
        this.listeParents.add(Parent1);
        this.listeParents.add(Parent2);
        this.force = CalculerForce();
    }
    
    
    /**
     * Getters
     */
    protected void setDureeGestation(int dureeGestation) {
        this.dureeGestation = dureeGestation;
    }
    public int getDureeGestation() {
    	return dureeGestation;
    }
    public Enum_Especes getNomEspece() {
        return nomEspece;
    }
    public Enum_Sexe getSexe() {
        return sexe;
    }
    public double getPoids() {
        return poids;
    }
    public double getTaille() {
        return taille;
    }
    public int getAge() {
        return age;
    }
    public int getIndicateurFaim() {
        return indicateurFaim;
    }
    public int getIndicateurSommeil() {
        return indicateurSommeil;
    }
    public int getIndicateurSante() {
        return indicateurSante;
    }
    public boolean isEnTrainDeDormir() {
        return enTrainDeDormir;
    }
    public boolean isVivant() {
        return vivant;
    }
    public String getBruit() {
        return bruit;
    }
    public Enum_CategorieAge getCategorieAge() {
        return categorieAge;
    }
    public int getBonheur() {
        return bonheur;
    }
    public Enum_Aggressivite getAgressivite() {
        return agressivite;
    }
    public ArrayList<Creature> getListeEnfants() {
        return listeEnfants;
    }
    public ArrayList<Creature> getListeParents() {
        return listeParents;
    }
    public String getPrenom() {
        return prenom;
    }
    public double getForce() {
        return force;
    }
    public int getCombatVaincu() {
        return combatVaincu;
    }
    public double getValeurNutrionelle() {
        return valeurNutrionelle;
    }
    public Enum_RangDomination getStatus() {
        return status;
    }
    public void setBonheur(int bonheur) {
        this.bonheur = bonheur;
    }
    public void setAgressivite(Enum_Aggressivite agressivite) {
        this.agressivite = agressivite;
    }
    public void setCombatVaincu(int combatVaincu) {
        this.combatVaincu = combatVaincu;
    }
    public void setStatus(Enum_RangDomination status) {
        this.status = status;
    }
    
    /**
     * Setters
     */
    protected void setNomEspece(Enum_Especes enumEspeces) {
        this.nomEspece = enumEspeces;
    }

    protected void setBruit(String bruit) {
        this.bruit = bruit;
    }
    

    /**
     * Méthode pour que la créature mange.
     * 
     * @param num Le nombre de points de faim que la créature gagne en mangeant.
     * @throws Exception Si la créature n'est pas vivante ou est en train de dormir.
     */
    public void Manger(int num) throws Exception {
        if (vivant && !enTrainDeDormir) {
            indicateurFaim += num;
            // Vérification pas de dépassement
            if (indicateurFaim > CONSTANTES.MAX_INDICATEUR)
                indicateurFaim = CONSTANTES.MAX_INDICATEUR;
        } else {
            throw new Exception("Etat de la creature invalide, impossible de manger");
        }
    }



    
    /**
     * Méthode pour que la créature fasse du bruit.
     * 
     * @return Le bruit de la créature.
     * @throws Exception Si la créature n'est pas vivante.
     */
    public String FaireBruit() throws Exception {
        if (vivant) {
        	PerdreNourriture();
        	PerdreSommeil();
            return bruit;
        }
        else
            throw new Exception("Etat de la creature invalide, impossible de faire un bruit");
    }

    
    /**
     * Méthode pour soigner la créature.
     * 
     * @throws Exception Si la créature n'est pas vivante.
     */
    public void Soigner() throws Exception {
        if (vivant) {
            indicateurSante = CONSTANTES.MAX_INDICATEUR;
        } else {
            throw new Exception("La creature n'est plus vivante, impossible de la soigner");
        }
    }

    
    /**
     * Méthode pour que la créature dorme.
     * 
     * @throws Exception Si la créature n'est pas vivante ou est déjà en train de dormir.
     */
    public void Dormir() throws Exception {
        if (vivant) {
        	if (!enTrainDeDormir) {
        		if (indicateurSommeil < CONSTANTES.MAX_INDICATEUR) {
        			enTrainDeDormir = true;
        		}
        		else
        			throw new Exception("Insomnie, impossible de dormir");
        	}
        	else
        		throw new Exception ("La creature est deja en train de dormir");
        }
        else
            throw new Exception("Creature morte, impossible de dormir");
    }

    
    /**
     * Méthode pour que la créature se réveille.
     * 
     * @throws Exception Si la créature n'est pas vivante ou n'est pas en train de dormir.
     */
    public void SeReveiller() throws Exception {
        if (vivant) {
        	if (enTrainDeDormir) {
        		enTrainDeDormir = false;
                indicateurSommeil = CONSTANTES.MAX_INDICATEUR;
        	}
        	else
        		throw new Exception("La creature ne dort pas");
        } 
        else
            throw new Exception("La creature ne peut pas se reveiller, elle est morte a jamais");
    }

    
    /**
     * Méthode pour faire vieillir la créature d'un an.
     *
     */
    public void Vieillir() throws Exception {
        if (vivant && age < CONSTANTES.MAX_AGE) {
        	PerdreNourriture();
        	PerdreSommeil();
        	age++;
        }
        else if (vivant && age == CONSTANTES.MAX_AGE) {
            Mourir();
        }
    }

    
    /**
     * Méthode pour faire mourir la créature.
     */
    public void Mourir() {
        vivant = false;
    }

    
    /**
     * Méthode pour que la créature perde du sommeil.
     * 
     * @throws Exception Si la créature n'est pas vivante ou est dans un état invalide.
     */
    public void PerdreSommeil() throws Exception {
        // Vérification de l'état de la créature
        if (vivant && indicateurSante > 0 && indicateurFaim > 0 && indicateurSommeil > 0)
            indicateurSommeil -= CONSTANTES.VALEUR_PERTE_INDICATEUR;
        // Vérification que la valeur reste positive
        if (indicateurSommeil < 0)
            indicateurSommeil = 0;
    }

    
    /**
     * Méthode pour que la créature perde de la nourriture.
     * 
     * @throws Exception Si la créature n'est pas vivante ou est dans un état invalide.
     */
    public void PerdreNourriture() throws Exception {
        // Vérification de l'état de la créature
        if (vivant && indicateurSante > 0 && indicateurFaim > 0 && indicateurSommeil > 0)
            indicateurFaim -= CONSTANTES.VALEUR_PERTE_INDICATEUR;
        // Vérification que la valeur reste positive
        if (indicateurFaim < 0)
            indicateurFaim = 0;
    }

    
    /**
     * Méthode pour que la créature perde de la santé.
     * 
     * @throws Exception Si la créature n'est pas vivante ou est dans un état invalide.
     */
    public void PerdreSante() throws Exception {
        // Vérification de l'état de la créature
        if (vivant && indicateurSante > 0 && indicateurFaim > 0 && indicateurSommeil > 0)
            indicateurSante -= CONSTANTES.VALEUR_PERTE_INDICATEUR;
        // Vérification que la valeur reste positive
        if (indicateurSante < 0)
            indicateurSante = 0;
    }
    
    
    /**
     * Methode renvoyant les informations pour l'affichage d'une creature
     * 
     * @return la chaine de caractere contenant les informations
     */
    public String toString() {
    	return "-- Creature de type "+nomEspece+" ="
    				+"\n   prenom : "+prenom
    				+"\n   sexe : "+sexe
    				+"\n   age : "+age
    				+"\n   vivant : "+vivant
    				+"\n   dort : "+enTrainDeDormir
    				+"\n   faim : "+indicateurFaim+"/"+CONSTANTES.MAX_INDICATEUR
    				+"\n   fatigue : "+indicateurSommeil+"/"+CONSTANTES.MAX_INDICATEUR
    				+"\n   sante : "+indicateurSante+"/"+CONSTANTES.MAX_INDICATEUR
    				+"\n\n";
    }
    
    /**
     * Methode pour remettre a 0 les proprietes d'une creature
     */
    public void ReinitialiserCreature() {
        age = 1;
        this.indicateurFaim = CONSTANTES.MAX_INDICATEUR;
        this.indicateurSommeil = CONSTANTES.MAX_INDICATEUR;
        this.indicateurSante = CONSTANTES.MAX_INDICATEUR;
        this.enTrainDeDormir = false;
        this.vivant = true;
    }
    
    
    /**
     * Méthode pour générer un sexe aléatoire
     * @return Le sexe choisi aleatoirement
     */
    public static Enum_Sexe SexeAleatoire() {
    	Random random = new Random();
        int r = 1+ random.nextInt(2);
        if (r == 1)
            return Enum_Sexe.Male;
        else
            return Enum_Sexe.Femelle;
    }



    /**
     * Renvoi le gagnant du combat entre deux creatures this et param,
     * selon la force
     * @return Creature gagnante
     */
    public Creature Combattre(Creature other){
        if (this.force > other.force)
            return this;
        else
            return other;
    }

    public double CalculerForce () {
        int facteurBonus = 0;
        if (sexe == Enum_Sexe.Male){
            facteurBonus += 50;
        }
        if (categorieAge == Enum_CategorieAge.BEBE){
            facteurBonus += 1;
        }
        else if (categorieAge == Enum_CategorieAge.ENFANT){
            facteurBonus += 5;
        }
        else if (categorieAge == Enum_CategorieAge.JEUNE){
            facteurBonus += 20;
        }
        else if (categorieAge == Enum_CategorieAge.ADULTE){
            facteurBonus += 50;
        }
        else if (categorieAge == Enum_CategorieAge.VIEUX){
            facteurBonus += 30;
        }
        if (status == Enum_RangDomination.ALPHA){
            facteurBonus += 100;
        }
        force = age + poids + taille + indicateurSante + indicateurFaim + indicateurSommeil + combatVaincu + facteurBonus;
        return force;
    }
    
    
    
    // METHODE POUR LES TESTS UNITAIRES
    public void setSexe (Enum_Sexe s) {
    	sexe = s;
    }
}
