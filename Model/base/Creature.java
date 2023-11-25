package base;

import java.util.Random;

import references.*;


/**
 * Cette classe abstraite représente une entité vivante dans le zoo fantastique
 *
 */
public abstract class Creature {

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
     * @param nomEspece         L'espèce de la créature.
     * @param sexe              Le sexe de la créature.
     * @param poids             Le poids de la créature.
     * @param taille            La taille de la créature.
     * @param bruit             Le bruit que fait la créature.
     * @param dureePourEnfant   La durée d'incubation / de gestation
     */
    public Creature(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, int dureePourEnfant) {
        this.nomEspece = nomEspece;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = 1;
        this.indicateurFaim = CONSTANTES.MAX_INDICATEUR;
        this.indicateurSommeil = CONSTANTES.MAX_INDICATEUR;
        this.indicateurSante = CONSTANTES.MAX_INDICATEUR;
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
     * @param num Le nombre de points de santé que la créature gagne en se soignant.
     * @throws Exception Si la créature n'est pas vivante.
     */
    public void Soigner() throws Exception {
        if (vivant) {
            indicateurSante += CONSTANTES.MAX_INDICATEUR;
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
        else if (vivant && age == CONSTANTES.MAX_AGE)
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
      

}
