package base;

import java.util.ArrayList;
import java.util.Random;

import creaturesImplemente.Embryon;
import creaturesImplemente.FactoryCreature;
import references.*;

/**
 * Cette classe abstraite représente une créature vivipare
 *
 */
public abstract class Vivipare extends Creature {

	private ArrayList<Embryon> ventre;

	private int nbJourConceptionRestantAvantMiseABas;
	
	
    /**
     * Constructeur de la classe Vivipare.
     * 
     * @param parent1 Le premiere parent de la créature (celui qui lui a donné naissance)
	 * @param parent2 le deuxieme parent de la créature
     */
    public Vivipare(Vivipare parent1, Vivipare parent2, int dureeGestation) {
        super(parent1, parent2);
		this.ventre = new ArrayList<Embryon>();

        nbJourConceptionRestantAvantMiseABas = dureeGestation;
    }

	public Vivipare() {
		super();
		this.ventre = new ArrayList<Embryon>();
		nbJourConceptionRestantAvantMiseABas = getDureeGestation();
	}
    
    /**
     * Getters
     */
    public int getNbJourConceptionRestantAvantMiseABas() {
		return nbJourConceptionRestantAvantMiseABas;
	}
    
    public void RemiseAZeroApresNaissance() {
    	nbJourConceptionRestantAvantMiseABas =0;
    	ventre.clear();
    }
    

    /**
     * Méthode pour construire un enfant
     * 
     * @param papa	correspond a la deuxieme creature qui permettra de concevoir enfant
     * @return String qui permet de notifier que l'enfant a ete concu
     * 
     */
    // TODO : gestion sauvegarde généalogie
    public void concevoirUnEnfant (Vivipare papa, int duree) throws Exception {
    	// Verification du statut des creatures
    	if (super.isVivant() && !super.isEnTrainDeDormir() && papa.isVivant() && !papa.isEnTrainDeDormir()) {
    		// Verification femelle et male, et meme espece
    		if (super.getSexe() == Enum_Sexe.Femelle  &&  papa.getSexe() == Enum_Sexe.Male  && super.getNomEspece()==papa.getNomEspece()) {
        		// Verification qu'un enfant n'est pas deja en cours
    			if (ventre.isEmpty()) {
					// ajout de n embryon dans le ventre avec n entre 0 et 10
					Random random = new Random(System.currentTimeMillis());
					int nbEmbryon = random.nextInt(3);
					for (int i = 0; i < nbEmbryon; i++) {
						ventre.add(new Embryon(this,papa)); }
    				nbJourConceptionRestantAvantMiseABas = duree;
    			}
    			else
    				throw new Exception ("Un ou plusieurs enfants sont deja en construction");
        	}
        	else
        		throw new Exception ("La nature autorise seulement une femelle de concevoir un enfant avec un male de la meme espece");
    	}
    	else 
    		throw new Exception ("Les deux creatures doivent etre vivante et reveille pour concevoir un enfant");
    	
    }
    
    
    /**
     * Methode permettant de decrementer le nombre de jour avant la naissance
     * du bebe et d'apeller la methode pour mettre bas dans ce cas
     * @return La creature mise au monde ou null
     * @throws Exception
     */
    public Creature VerificationEnfantEnConception() throws Exception {
    	if (DecrementerNombreJourRestantAvantNaissance() == 0)
			return MettreBas();
		return null;
    }
    

	/**
     * Méthode pour mettre bas une nouvelle créature vivipare.
     * 
     * @param sexe   Le sexe de la nouvelle créature.
     * @param poids  Le poids de la nouvelle créature.
     * @param taille La taille de la nouvelle créature.
     * @return Une instance de la classe Creature qui né.
     * @throws Exception Si le vivipare n'est pas vivant ou s'il n'est pas de sexe femelle.
     */
    public Creature MettreBas() throws Exception {
    	return FactoryCreature.newCreature(super.getNomEspece());
    }
    
    
    /**
     * Methode pour decrementer le nombre de jour restant
     * @throws Exception 
     */
    public int DecrementerNombreJourRestantAvantNaissance (){
    	if (nbJourConceptionRestantAvantMiseABas > 0) {
    		return nbJourConceptionRestantAvantMiseABas--;
    	}
    	else
    		return 0;
    }
}
