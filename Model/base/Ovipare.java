package base;

import creaturesImplemente.Oeuf;
import references.Enum_Sexe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

/**
 * Cette classe abstraite représente une créature de type ovipare
 */
public abstract class Ovipare extends Creature {

    private final ArrayList<Oeuf> ventre; // dans le ventre il y a n oeufs avec potentiellement aucun oeuf
    //TODO add gestion oeuf dans le ventre
    private final int dureeAvantEclosion = 1; // un an avant de pondre un oeuf

    
    /**
     * Constructeur de la classe Ovipare.
     *
     * @param parent1 Le premier parent ovipare.
     * @param parent2 Le deuxième parent ovipare.
     */
    public Ovipare(Ovipare parent1, Ovipare parent2) {
        super(parent1, parent2);
        this.ventre = new ArrayList<>();
        this.setDureeGestation(dureeAvantEclosion);
    }

    /**
     * Constructeur par défaut de la classe Ovipare.
     */
    public Ovipare(){
        super();
        this.ventre = new ArrayList<>();
        this.setDureeGestation(dureeAvantEclosion);
    }
    
    
    /**
     * Obtient la liste des œufs dans le ventre de l'ovipare.
     *
     * @return La liste des œufs dans le ventre.
     */
    public ArrayList<Oeuf> getVentre() {
    	return ventre;
    }

    
    /**
     * Crée un bébé ovipare en fonction d'un partenaire male
     *
     * @param partenaire Le partenaire ovipare.
     * @throws Exception Si une ou deux creatures ne sont pas en etat
     */
    public void creerBebe(Ovipare partenaire) throws Exception {
        if ( (this.isVivant() && this.getSexe() == Enum_Sexe.Femelle) && (partenaire.isVivant() && partenaire.getSexe() == Enum_Sexe.Male) && this.getNomEspece().equals(partenaire.getNomEspece())) {
            // Création de n œufs dans le ventre
            Random random = new Random(System.currentTimeMillis());
            int nbOeuf = 1+ random.nextInt(3);
            for (int i = 0; i < nbOeuf; i++) {
                this.ventre.add(new Oeuf(this, Objects.requireNonNull(partenaire, "Le partenaire ne peut pas etre null")));
            }
        } else {
            throw new Exception("Statut de la creature "+getNomEspece()+" "+getPrenom()+" invalide");
        }
    }

    
    /**
     * Pond les œufs qui sont prêts à éclore.
     *
     * @return Une liste des œufs pondus par l'ovipare
     * @throws Exception Si l'ovipare femelle n'attend pas d'enfant
     */
    public ArrayList<Oeuf> pondreOeuf() throws Exception {
    	if (!ventre.isEmpty()) {
            ArrayList<Oeuf> oeufs = new ArrayList<>();
            // Utilisation d'un itérateur pour éviter les modifications concurrentes
            Iterator<Oeuf> iterator = this.ventre.iterator();
            while (iterator.hasNext()) {
                Oeuf oeuf = iterator.next();
                if (oeuf.getDureeIncubationRestante() == 1) {
                    oeufs.add(oeuf);
                    iterator.remove(); // Suppression après l'itération
                }
            }
            ventre.clear();
            return oeufs;
        } else {
            throw new Exception("Ventre de "+getNomEspece()+" "+getPrenom()+" vide");
        }
    }
}
