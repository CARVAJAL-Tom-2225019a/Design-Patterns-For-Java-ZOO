package base;

import java.util.Random;

import references.*;

public abstract class Creature {
    CONSTANTES constantes = new CONSTANTES();
    static Random random = new Random(System.currentTimeMillis());

    private Enum_Especes nomEspece;
    private Enum_Sexe sexe;
    private double poids;
    private double taille;
    private int age;

    private int indicateurFaim; // Indice de satiété
    private int indicateurSommeil;
    private int indicateurSante;

    private boolean enTrainDeDormir;
    private boolean vivant;

    private String bruit;
    
    private int dureePourEnfant;


    /**
     * Constructeur de la classe Creature.
     * 
     * @param nomEspece L'espèce de la créature.
     * @param sexe      Le sexe de la créature.
     * @param poids     Le poids de la créature.
     * @param taille    La taille de la créature.
     * @param bruit     Le bruit que fait la créature.
     */
    public Creature(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, int dureePourEnfant) {
        this.nomEspece = nomEspece;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = 1;
        this.indicateurFaim = constantes.MAX_INDICATEUR;
        this.indicateurSommeil = constantes.MAX_INDICATEUR;
        this.indicateurSante = constantes.MAX_INDICATEUR;
        this.enTrainDeDormir = false;
        this.vivant = true;
        this.bruit = bruit;
        this.dureePourEnfant = dureePourEnfant;
    }
    
    /**
     * Getters
     */
    public int getDureePourEnfant() {
    	return dureePourEnfant;
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
            if (indicateurFaim > constantes.MAX_INDICATEUR)
                indicateurFaim = constantes.MAX_INDICATEUR;
        } else {
            throw new Exception("Etat de la créature invalide, impossible de manger");
        }
    }

    
    /**
     * Méthode pour que la créature fasse du bruit.
     * 
     * @return Le bruit de la créature.
     * @throws Exception Si la créature n'est pas vivante.
     */
    public String FaireBruit() throws Exception {
        if (vivant)
            return bruit;
        else
            throw new Exception("Etat de la créature invalide, impossible de faire un bruit");
    }

    
    /**
     * Méthode pour soigner la créature.
     * 
     * @param num Le nombre de points de santé que la créature gagne en se soignant.
     * @throws Exception Si la créature n'est pas vivante.
     */
    public void Soigner(int num) throws Exception {
        if (vivant) {
            indicateurSante += num;
            // Vérification pas de dépassement
            if (indicateurSante > constantes.MAX_INDICATEUR)
                indicateurSante = constantes.MAX_INDICATEUR;
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
        if (vivant && !enTrainDeDormir && indicateurSommeil < constantes.MAX_INDICATEUR)
            enTrainDeDormir = true;
        else
            throw new Exception("État de la créature invalide, impossible de dormir");
    }

    /**
     * Méthode pour que la créature se réveille.
     * 
     * @throws Exception Si la créature n'est pas vivante ou n'est pas en train de dormir.
     */
    public void SeReveiller() throws Exception {
        if (vivant && enTrainDeDormir) {
            enTrainDeDormir = false;
            indicateurSommeil = constantes.MAX_INDICATEUR;
        } else {
            throw new Exception("Etat de la créature invalide, impossible de se reveiller");
        }
    }

    
    /**
     * Méthode pour faire vieillir la créature d'un an.
     *
     */
    public void Vieillir() throws Exception {
        if (vivant && age < constantes.MAX_AGE)
            age++;
        else if (vivant && age == constantes.MAX_AGE)
            Mourir();
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
            indicateurSommeil -= constantes.VALEUR_PERTE_INDICATEUR;
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
            indicateurFaim -= constantes.VALEUR_PERTE_INDICATEUR;
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
            indicateurSante -= constantes.VALEUR_PERTE_INDICATEUR;
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
    				+"\n   sexe : "+sexe
    				+"\n   age : "+age
    				+"\n   vivant : "+vivant
    				+"\n   dort : "+enTrainDeDormir
    				+"\n   faim : "+indicateurFaim+"/"+constantes.MAX_INDICATEUR
    				+"\n   fatigue : "+indicateurSommeil+"/"+constantes.MAX_INDICATEUR
    				+"\n   sante : "+indicateurSante+"/"+constantes.MAX_INDICATEUR
    				+"\n\n";
    }
    
    /**
     * Methode pour remettre a 0 les proprietes d'une creature
     */
    public void ReinitialiserCreature() {
        age = 1;
        this.indicateurFaim = constantes.MAX_INDICATEUR;
        this.indicateurSommeil = constantes.MAX_INDICATEUR;
        this.indicateurSante = constantes.MAX_INDICATEUR;
        this.enTrainDeDormir = false;
        this.vivant = true;
    }
    
    
    /**
     * Méthode pour générer un sexe aléatoire
     */
    public static Enum_Sexe SexeAleatoire() {
        int r = random.nextInt(2) + 1;
        if (r == 1)
            return Enum_Sexe.Male;
        else
            return Enum_Sexe.Femelle;
    }
      

}
