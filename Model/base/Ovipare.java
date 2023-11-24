package base;

import creaturesImplemente.*;
import references.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Cette classe abstraite représente une créature de type ovipare
 */
public abstract class Ovipare extends Creature {

    private ArrayList<Oeuf> ventre; // dans le ventre il y a n oeufs avec potentiellement aucun oeuf.
    //TODO add gestion oeuf dans le ventre
    private int dureeAvantEclosion = 1; // un an avant de pondre un oeuf

    /**
     * Constructeur de la classe Ovipare.
     */
    public Ovipare(Ovipare parent1, Ovipare parent2) {
        super(parent1, parent2);
        this.ventre = new ArrayList<Oeuf>();
        this.setDureeGestation(dureeAvantEclosion);
    }

    public Ovipare(){
        super();
        this.ventre = new ArrayList<Oeuf>();
        this.setDureeGestation(dureeAvantEclosion);
    }
    
    public ArrayList<Oeuf> getVentre() {
    	return ventre;
    }

    public void CreerBebe(Ovipare partenaire) throws Exception {
        if (this.isVivant() && this.getSexe() == Enum_Sexe.Femelle && partenaire.isVivant() && partenaire.getSexe() == Enum_Sexe.Male && this.getNomEspece() == partenaire.getNomEspece()) {
            // creation n oeuf dans le ventre
            Random random = new Random(System.currentTimeMillis());
            int nbOeuf = 1+random.nextInt(3);
            for (int i = 0; i < nbOeuf; i++) {
                this.ventre.add(new Oeuf(this,partenaire));
            }
        } else {
            throw new Exception("Statut creature invalide");
        }
    }

    /**
     * Méthode pour pondre un œuf.
     *
     * @param dateNaissance   La date de naissance de l'œuf.
     * @param dureeIncubation La durée d'incubation spécifique.
     * @return Une instance de la classe Oeuf pondue par l'ovipare.
     * @throws Exception 
     */
    public ArrayList<Oeuf> PondreOeuf() throws Exception  {
    	if (this.isVivant() && this.getSexe() == Enum_Sexe.Femelle && !this.ventre.isEmpty() && !this.isEnTrainDeDormir()) {
            ArrayList<Oeuf> oeufs = new ArrayList<Oeuf>();
            Iterator<Oeuf> it = this.ventre.iterator();
            while (it.hasNext()) {
                Oeuf oeuf = it.next();
                oeufs.add(oeuf);
                it.remove(); // Utilisez remove() sur l'itérateur pour éviter la ConcurrentModificationException
            }
            return oeufs;
        } else {
            throw new Exception("Ovipare pas en etat de pondre Oeuf");
        }
    }
}
