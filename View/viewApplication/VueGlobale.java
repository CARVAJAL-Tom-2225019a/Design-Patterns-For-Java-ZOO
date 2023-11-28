package viewApplication;

import base.Creature;
import base.Enclos;
import base.Vivipare;
import creaturesImplemente.*;
import enclosImplemente.Aquarium;
import enclosImplemente.Voliere;
import references.CONSTANTES;
import references.Enum_CategorieAge;
import references.Enum_Sexe;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.min;

/**
 * Classe permettant l'affichage d'informations
 */
public class VueGlobale {

    /**
     * Méthode pour afficher le message de passage à la nouvelle année et apeller le
     * controlleur qui effectuera les actions
     */

    /**
     * Méthode pour afficher un texte a utilisateur
     */
    public void afficher(String texte) {
        System.out.println(texte);
    }
    
    @SuppressWarnings("serial")
    public void afficherCreature(Creature creature) {
        VueCreature vueCreature;
        VueCreature parchemin = VueCreature.PARCHEMIN;
        ArrayList<String> strResultat = new ArrayList<>();

        if (creature instanceof Dragon) {
            vueCreature = VueCreature.DRAGON;
        } else if (creature instanceof Kraken) {
            vueCreature = VueCreature.KRAKEN;
        } else if (creature instanceof Licorne) {
            vueCreature = VueCreature.LICORNE;
        } else if (creature instanceof Lycanthrope) {
            vueCreature = VueCreature.LYCANTHROPE;
        } else if (creature instanceof Megalodon) {
            vueCreature = VueCreature.MEGALODON;
        } else if (creature instanceof Nymphe) {
            vueCreature = VueCreature.NYMPHE;
        } else if (creature instanceof Phenix) {
            vueCreature = VueCreature.PHENIX;
        } else if (creature instanceof Sirene) {
            vueCreature = VueCreature.SIRENE;
        } else {
            vueCreature = VueCreature.HUMAIN;
        }

        for (int i = 0; i < (parchemin.getHauteur()); i++) { // temporairement  + vueCreature.getHauteur() -1) ; i++){

            strResultat.add(parchemin.getLignes().get(i));

            if (i == 2) {
                int nbOfX = (int) parchemin.getLignes().get(i).chars().filter(c -> c == 'X').count();
                // une fois qu'on connais le nombre D'X (le place holder)
                // on va mettre au millieu des X le prenom de l'animal et fill avec des espaces.
                String prenom = creature.getPrenom();
                StringBuilder xString = new StringBuilder();
                xString.append(" ".repeat(Math.max(0, nbOfX)));
                int startIndex = (nbOfX - prenom.length()) / 2;
                xString.replace(startIndex, startIndex + prenom.length(), prenom);
                strResultat.set(i, strResultat.get(i).replace("X".repeat(nbOfX), xString.toString()));
            }
            if (i == 3) {
                int nbOfZ = (int) parchemin.getLignes().get(i).chars().filter(c -> c == 'Z').count();
                // une fois qu'on connais le nombre D'Z (le place holder)
                // on va mettre au millieu des Z l'espece de l'animal et fill avec des espaces.
                String espece = creature.getNomEspece().name();
                StringBuilder zString = new StringBuilder();
                zString.append(" ".repeat(Math.max(0, nbOfZ)));
                int startIndex = (nbOfZ - espece.length()) / 2;
                zString.replace(startIndex, startIndex + espece.length(), espece);
                strResultat.set(i, strResultat.get(i).replace("Z".repeat(nbOfZ), zString.toString()));
            }

            if (i == 5) {
                // on remplace les C avec l'ascii art de la créature.
                // a ajouter n hauteur de creature la ligne courante.

                for (int j = 1; j < vueCreature.getHauteur() + 1; j++) {
                    int nbOfC = (int) parchemin.getLignes().get(i).chars().filter(c -> c == 'C').count();
                    StringBuilder cString = new StringBuilder();

                    if (j == vueCreature.getHauteur()) {
                        String ligneAsci = vueCreature.getLignes().get(0);
                        cString.append(" ".repeat(Math.max(0, nbOfC)));
                        int startIndex = (nbOfC - (ligneAsci.length()-16)) / 2;
                        cString.replace(startIndex, startIndex + ligneAsci.length(), ligneAsci);
                        strResultat.set(i, strResultat.get(i).replace("C".repeat(nbOfC), cString.toString()));
                    } else {
                        String ligneAsci = vueCreature.getLignes().get(j);
                        cString.append(" ".repeat(Math.max(0, nbOfC)));
                        int startIndex = (nbOfC - (ligneAsci.length()-16)) / 2;
                        cString.replace(startIndex, startIndex + ligneAsci.length(), ligneAsci);
                        strResultat.add(i + j, strResultat.get(i).replace("C".repeat(nbOfC), cString.toString()));
                    }
                }
                if (creature instanceof Lycanthrope) {
                    strResultat.replaceAll(s -> s.replace('Ω', ((Lycanthrope) creature).getRangDomination().getChar()));

                }
            }
        }
        ArrayList<String> complementLigne = new ArrayList<>() {
            {
                add("  # Sexe  : \033[32m" + creature.getSexe() + "\033[0m         Age : \033[32m" + creature.getAge() + "ans \033[0m ");
                add("  # Poids : \033[32m" + creature.getPoids() + " kg \033[0m    Taille : \033[32m" + creature.getTaille() + " m \033[0m ");
                add("  #");
                add("  # Agressivité        : \033[32m" + creature.getAgressivite() + "\033[0m ");
                add("  # Force              : \033[32m" + creature.getForce()+ "\033[0m ");
                add("  # Combat(s) gagné(s) : \033[32m" + creature.getCombatVaincu()+ "\033[0m ");
                if (!creature.getListeParents().isEmpty()) {
                    add("  # Pere & Mere        : \033[32m" + creature.getListeParents().get(0).getPrenom() + " & " + creature.getListeParents().get(1).getPrenom() + "\033[0m ");
                } else {
                    add("  # Pere & Mere        : \033[32m" + "Orphelin \033[0m");
                }
                if (!creature.getListeEnfants().isEmpty()) {
                    add("  # Premier Enfant     : \033[32m" + creature.getListeEnfants().get(0).getPrenom() + "\033[0m ");
                } else {
                    add("  # Enfant             : \033[32m" + "Aucun  \033[0m");
                }
                add("  # Bonheur            : \033[32m" + afficherStatBar(creature.getBonheur(), CONSTANTES.MAX_INDICATEUR) + " " + (creature.getBonheur() * 100 / CONSTANTES.MAX_INDICATEUR ) + "% \033[0m ");
                add("  # Faim               : \033[32m" + afficherStatBar(creature.getIndicateurFaim(), CONSTANTES.MAX_INDICATEUR) + " " + (creature.getIndicateurFaim()* 100 / CONSTANTES.MAX_INDICATEUR ) + "% \033[0m ");
                add("  # Santé              : \033[32m" + afficherStatBar(creature.getIndicateurSante(), CONSTANTES.MAX_INDICATEUR) + " " + (creature.getIndicateurSante() * 100/ CONSTANTES.MAX_INDICATEUR ) + "% \033[0m ");
                add("  # Sommeil            : \033[32m" + afficherStatBar(creature.getIndicateurSommeil(), CONSTANTES.MAX_INDICATEUR) + " " + (creature.getIndicateurSommeil() * 100/ CONSTANTES.MAX_INDICATEUR ) + "% \033[0m ");

                StringBuilder Str = new StringBuilder();
                for (Class<?> iface : creature.getClass().getInterfaces() ){
                    Str.append(iface.getSimpleName()).append(" ");
                }
                add("  # Infos Types        : \033[32m" + Str + " \033[0m ");

                if (creature instanceof Vivipare) {
                    if (creature.getSexe() == Enum_Sexe.Femelle) {
                        add("  # Enceinte           : \033[32m" + ((Vivipare) creature).isEnceinte()+ " \033[0m ");
                    }
                }
            }
        };
        int ligneFinal = 0;
        System.out.println("\n#=============================< \033[32m" + creature.getPrenom() + "\033[0m - \033[32m" + creature.getNomEspece() + "\033[0m >==============================#\n");
        for (int i = 0 ; i < min(strResultat.size(),complementLigne.size()) ; i++) {
            System.out.println(strResultat.get(i) + complementLigne.get(i));
            ligneFinal = i;
        }
        if (ligneFinal+1 < complementLigne.size()) {
            for (String complement : complementLigne.subList(ligneFinal + 1, complementLigne.size())) {
                System.out.println(" ".repeat(parchemin.getLargeur()) + complement);
            }
        } else if (ligneFinal+1 < strResultat.size()) {
            for (String ligne : strResultat.subList(ligneFinal + 1, strResultat.size())) {
                System.out.println(ligne);
            }
        }
    }


    public void afficherEnclos(Enclos enclos) {
        VueEnclos vueEnclos;
        ArrayList<String> complementLignes;
        if (enclos instanceof Voliere) {
            vueEnclos = VueEnclos.VOLIERE;
            complementLignes = new ArrayList<String>() {
                {
                    add("  #============= \033[32m" + enclos.getNom() + "\033[0m =============#  ");
                    add("  #");
                    add("  # \033[32m" + vueEnclos + "\033[0m contient : \033[32m" + enclos.getNbCreatures() + " " + enclos.getNomEspece() +"\033[0m");
                    add("  # Il peut encore accueillir \033[32m" + (enclos.getNbMaxCreatures() - enclos.getNbCreatures()) + "\033[0m/\033[32m" + enclos.getNbMaxCreatures() + "\033[0m créature(s) ");
                    add("  # L'enclos a une superficie de : \033[32m" + enclos.getSuperficie() + " m²\033[0m");
                    add("  # La hauteur de l'enclos est de : \033[32m" + ((Voliere) enclos).getHauteur() + "m\033[0m");
                    add("  # La créature dominante de l'enclos est \033[32m: " + enclos.getCreatureDominante().getPrenom() + "\033[0m");
                    add("  # L'ambiance globale est : \033[32m" + enclos.getAmbiance().name()+ "\033[0m");
                    add("  # Age moyen       : \033[32m" + afficherStatBar(enclos.getAgeMoyen(), CONSTANTES.MAX_AGE) + " " + enclos.getAgeMoyen() + "ans\033[0m");
                    add("  # Bonheur moyen   : \033[32m" + afficherStatBar(enclos.getBonheurMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getBonheurMoyen() + "%\033[0m"); //TODO envisager changement maxindicateur
                    add("  # Faim moyenne    : \033[32m" + afficherStatBar(enclos.getFaimMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getFaimMoyen() + "%\033[0m");
                    add("  # Sommeil moyen   : \033[32m" + afficherStatBar(enclos.getSommeilMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSommeilMoyen() + "%\033[0m");
                    add("  # Santé moyenne   : \033[32m" + afficherStatBar(enclos.getSanteMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSanteMoyen() + "%\033[0m");
                    add("  # Propreté enclos : \033[32m" + afficherStatBar(enclos.getDegrePropreteNumber(), 3) + " " + enclos.getDegreProprete()+ "\033[0m");
                    add("  # Etat toit       : \033[32m" + afficherStatBar(((Voliere) enclos).getEtatToitNumber(), 3) + " " + ((Voliere) enclos).getEtatToit()+ "\033[0m");
                    add("  # ");
                    add("  # Nombre individu par classe d'ages :");
                    add("  # " + afficherClassesAgesEnclos(enclos)); // # Oeuf(s) 1 | nouveau né 2 | enfant 3 | Jeune 4 | Adulte 5 | Vieux 9
                }
            };

        } else if (enclos instanceof Aquarium) {
            vueEnclos = VueEnclos.AQUARIUM;

            complementLignes = new ArrayList<String>() {
                {
                    add("  #========== \033[32m" + enclos.getNom() + "\033[0m ==========  ");
                    add("  #");
                    add("  # \033[32m" + vueEnclos + "\033[0m"+" contient : \033[32m" + enclos.getNbCreatures()  +" "+ enclos.getNomEspece()+"\033[0m");
                    add("  # Il peut encore accueillir \033[32m" + (enclos.getNbMaxCreatures() - enclos.getNbCreatures()) +"\033[0m"+ "/\033[32m" + enclos.getNbMaxCreatures() + "\033[0m créature(s) ");
                    add("  # L'enclos a une superficie de : \033[32m" + enclos.getSuperficie() + " m²\033[0m");
                    add("  # La Salinité est de : \033[32m" + ((Aquarium) enclos).getSaliniteEau() + "\033[0m & La profondeur est de : \033[32m" + ((Aquarium) enclos).getProfondeurBassin() + " m\033[0m");
                    add("  # La créature dominante de l'enclos est : \033[32m" + enclos.getCreatureDominante().getPrenom() +"\033[0m" );
                    add("  # L'ambiance globale est : \033[32m" + enclos.getAmbiance().name()+"\033[0m");
                    add("  #");
                    add("  # Age moyen       : \033[32m" + afficherStatBar(enclos.getAgeMoyen(), CONSTANTES.MAX_AGE) + " " + enclos.getAgeMoyen() + " ans"+"\033[0m");
                    ;
                    add("  # Bonheur moyen   : \033[32m" + afficherStatBar(enclos.getBonheurMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getBonheurMoyen() + "%"+"\033[0m"); //TODO envisager changement maxindicateur
                    add("  # Faim moyenne    : \033[32m" + afficherStatBar(enclos.getFaimMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getFaimMoyen() + "%"+"\033[0m");
                    add("  # Sommeil moyen   : \033[32m" + afficherStatBar(enclos.getSommeilMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSommeilMoyen() + "%"+"\033[0m");
                    add("  # Santé moyenne   : \033[32m" + afficherStatBar(enclos.getSanteMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSanteMoyen() + "%"+"\033[0m");
                    add("  # Propreté enclos : \033[32m" + afficherStatBar(enclos.getDegrePropreteNumber(), 3) + " " + enclos.getDegreProprete()+"\033[0m");
                    add("  #");
                    add("  # Nombre individu par classe d'ages :");
                    add("  # " + afficherClassesAgesEnclos(enclos)); // # Oeuf(s) 1 | nouveau né 2 | enfant 3 | Jeune 4 | Adulte 5 | Vieux 9
                }
            };
        } else {
            vueEnclos = VueEnclos.TERRAIN;
            complementLignes = new ArrayList<String>() {
                {
                    add("  #========== \033[32m" + enclos.getNom()+"\033[0m" + " ==========  ");
                    add("  #");
                    add("  # \033[32m" + vueEnclos + "\033[0m contient : \033[32m" + enclos.getNbCreatures() + " " + enclos.getNomEspece() +"\033[0m");
                    add("  # Il peut encore accueillir \033[32m" + (enclos.getNbMaxCreatures() - enclos.getNbCreatures())+"\033[0m" + "/\033[32m" + enclos.getNbMaxCreatures() + " créature(s) "+"\033[0m");
                    add("  # L'enclos a une superficie de : \033[32m" + enclos.getSuperficie() + " m²"+"\033[0m");
                    add("  # La créature dominante de l'enclos est : \033[32m" + enclos.getCreatureDominante().getPrenom()+"\033[0m");
                    add("  #");
                    add("  # L'ambiance globale est : \033[32m" + enclos.getAmbiance().name()+"\033[0m");
                    add("  # Age moyen       : \033[32m" + afficherStatBar(enclos.getAgeMoyen(), CONSTANTES.MAX_AGE) + " " + enclos.getAgeMoyen() + " ans"+"\033[0m");
                    ;
                    add("  # Bonheur moyen   : \033[32m" + afficherStatBar(enclos.getBonheurMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getBonheurMoyen() + "%"+"\033[0m"); //TODO envisager changement maxindicateur
                    add("  # Faim moyenne    : \033[32m" + afficherStatBar(enclos.getFaimMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getFaimMoyen() + "%"+"\033[0m");
                    add("  # Sommeil moyen   : \033[32m" + afficherStatBar(enclos.getSommeilMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSommeilMoyen() + "%"+"\033[0m");
                    add("  # Santé moyenne   : \033[32m" + afficherStatBar(enclos.getSanteMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSanteMoyen() + "%"+"\033[0m");
                    add("  # Propreté enclos : \033[32m" + afficherStatBar(enclos.getDegrePropreteNumber(), 3) + " " + enclos.getDegreProprete()+"\033[0m");
                    add("  #");
                    add("  # Nombre individu par classe d'ages :");
                    add("  # " + afficherClassesAgesEnclos(enclos)); // # Oeuf(s) 1 | nouveau né 2 | enfant 3 | Jeune 4 | Adulte 5 | Vieux 9
                    add("  # " + afficherSexesEnclos(enclos)); // # 4 Femmelles | 4 males
                }
            };
        }
        int i = 0;
        for (String ligne : vueEnclos.getLignes()) {
            //System.out.println(ligne);

            String ligneComplete = ligne + complementLignes.get(i) + "\n";// ajouter les attributs
            System.out.print(ligneComplete);
            i++;
        }
        for (Creature creature : enclos.getListeCreatures().values()) {
            afficherCreature(creature);
        }
    }

    private String afficherSexesEnclos(Enclos enclos) {
        int[] counts = new int[enclos.getListeCreatures().values().size()];
        for (Creature creature : enclos.getListeCreatures().values()) {
            Enum_Sexe sexe = creature.getSexe();
            counts[sexe.ordinal()]++;
        }
        StringBuilder str = new StringBuilder();
        for (Enum_Sexe sexe : Enum_Sexe.values()) {
            str.append(sexe.name()).append(" ").append(counts[sexe.ordinal()]).append(" | ");

        }
        if (!str.isEmpty()) {
            str.setLength(str.length() - 2);
        }

        return str.toString();
    }

    public String afficherClassesAgesEnclos(Enclos enclos) {
        int[] counts = new int[Enum_CategorieAge.values().length];

        for (Creature creature : enclos.getListeCreatures().values()) {
            Enum_CategorieAge categorieAge = creature.getCategorieAge();
            counts[categorieAge.ordinal()]++;
        }

        StringBuilder str = new StringBuilder();

        // Construire la chaîne résultante en utilisant les valeurs de l'énumération
        for (Enum_CategorieAge categorieAge : Enum_CategorieAge.values()) {
            str.append(categorieAge.getLibelle()).append(" ").append("\033[32m").append(counts[categorieAge.ordinal()]).append("\033[0m").append(" | ");
        }

        // Supprimer le dernier séparateur "|"
        if (!str.isEmpty()) {
            str.setLength(str.length() - 2);
        }

        return str.toString();
    }


    public String afficherStatBar(int val, int denominateur) {
        StringBuilder statBar = new StringBuilder("[");
        int tailleStatBar = 10;

        // Calcul du pourcentage résultant
        double pourcentage = (double) val / denominateur;

        // Calcul du nombre d'égalités à afficher en fonction du pourcentage
        int nbEgalites = (int) (tailleStatBar * pourcentage);

        // Limiter le nombre d'égalités à la taille maximale
        nbEgalites = min(nbEgalites, tailleStatBar);

        // Affichage des égalités
        statBar.append("=".repeat(Math.max(0, nbEgalites)));

        // Remplissage du reste avec des espaces
        statBar.append(" ".repeat(Math.max(0, tailleStatBar - nbEgalites)));

        statBar.append("]");
        return statBar.toString();
    }

}
