package creaturesImplemente;

import base.Ovipare;
import interfaces.CreatureImmortel;
import interfaces.CreatureMarine;
import interfaces.CreatureTerrestre;
import interfaces.CreatureVolante;
import references.Enum_Aggressivite;
import references.Enum_Especes;

/**
 * Cette classe correspond à la créature du dragon,
 * qui est un ovipare et possède les caractéristiques
 * terrestres, aquatiques, aériennes et l'immortalité
 */
public class Dragon extends Ovipare implements CreatureTerrestre, CreatureMarine, CreatureVolante, CreatureImmortel {

    private final int dureeIncubation = 1;

    /**
     * Constructeur de la classe Dragon.
     * Protected afin que la création se fasse essentiellement depuis la factory
     * 
     * @param parent1 Le premier parent dragon.
     * @param parent2 Le deuxième parent dragon.
     * @param bruit Le bruit émis par le dragon.
     */
    public Dragon(Dragon parent1,Dragon parent2,String bruit) {
        super(parent1, parent2);
        this.setAgressivite(Enum_Aggressivite.cannibale);
        this.setNomEspece(parent1.getNomEspece());
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }

    /**
     * Constructeur par défaut de la classe Dragon.
     * 
     * @param bruit Le bruit émis par le dragon.
     */
    public Dragon (String bruit) {
        super();
        this.setAgressivite(Enum_Aggressivite.cannibale);
        this.setNomEspece(Enum_Especes.Dragon);
        this.setDureeGestation(dureeIncubation);
        this.setBruit( bruit);
    }

    
    /**
     * Méthode de l'interface CreatureTerrestre : Courir
     * Permet au dragon de courir sous certaines conditions
     * 
     * @return Un message indiquant que le dragon est en mouvement
     * @throws Exception Si le dragon n'est pas en état de courir
     */
    @Override
    public String courrir() throws Exception {
        if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.perdreNourriture();
            super.perdreSommeil();
            return "Le dragon "+getPrenom()+" est en mouvement";
        } else {
            throw new Exception("Dragon "+getPrenom()+" pas en etat de courir");
        }
    }

    
    /**
     * Méthode de l'interface CreatureMarine : Nager
     * Permet au dragon de nager sous certaines conditions
     * 
     * @return Un message indiquant que le dragon nage
     * @throws Exception Si le dragon n'est pas en état de nager
     */
    @Override
    public String nager() throws Exception {
        if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.perdreNourriture();
            super.perdreSommeil();
            return "Le dragon "+getPrenom()+" nage";
        } else {
            throw new Exception("Dragon "+getPrenom()+" pas en etat de nager");
        }
    }

    
    /**
     * Méthode de l'interface CreatureVolante : Voler
     * Permet au dragon de voler sous certaines conditions
     * 
     * @return Un message indiquant que le dragon vole
     * @throws Exception Si le dragon n'est pas en état de voler
     */
    @Override
    public String voler() throws Exception {
        if (super.isVivant() && super.getIndicateurSommeil() > 0 && super.getIndicateurSante() > 0 && super.getIndicateurFaim() > 0) {
            super.perdreNourriture();
            super.perdreSommeil();
            return "Le dragon "+getPrenom()+" vole";
        } else {
            throw new Exception("Dragon "+getPrenom()+" pas en etat de voler");
        }
    }

    
    /**
     * Méthode de l'interface CreatureImmortel : Mourir 
     * Surcharge d'une méthode de Créature
     * Implémente la logique de renaissance du dragon
     */
    @Override
    public void mourrir() {
        reinitialiserCreature(); //TODO changer ca en un oeuf
    }

    

}
