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

import java.io.Serial;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.Math.min;

/**
 * Classe permettant l'affichage d'informations (à la fois pour la gestion auto ou manuelle)
 */
public class VueGlobale {

	/**
	 * Constructeur
	 */
	public VueGlobale() { }
	
    /**
     * Méthode pour afficher un texte a utilisateur
     * 
     * @param texte Le texte à afficher
     */
    public void afficher(String texte) {
        System.out.println(texte);
    }
    
    /**
     * Methode permettant d'afficher l'ensemble des creatures d'un enclos
     * @param e enclos à afficher
     */
    public void afficherCreatureEnclos(Enclos e) {
    	 for (Map.Entry<Integer, Creature> entry : e.getListeCreatures().entrySet()) {
    		afficherCreature(entry.getValue(), entry.getKey());
    	}
    }
    
    /**
     * Méthode pour afficher les détails d'une créature
     *
     * @param creature La créature à afficher
     */
    public void afficherCreature(Creature creature, int index) {
        VueCreature parchemin = VueCreature.PARCHEMIN;
        ArrayList<String> strResultat = new ArrayList<>();
        VueCreature vueCreature = getVueCreature(creature);

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
            /**
			 * 
			 */
			@Serial
            private static final long serialVersionUID = 1L;

			{
                add("  # Sexe  : \033[32m" + creature.getSexe() + "\033[0m         Age : \033[32m" + creature.getAge() + "ans \033[0m ");
                add("  # Poids : \033[32m" + creature.getPoids() + " kg \033[0m    Taille : \033[32m" + creature.getTaille() + " m \033[0m ");
                add("  # Vivant : \033[32m" + creature.isVivant() + "\033[0m       ");
                add("  # Agressivite        : \033[32m" + creature.getAgressivite() + "\033[0m ");
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
                if (index>=0) {
                	add("  # Index : \033[32m" + index +" \033[0m ");
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


    /**
     * Méthode pour afficher les détails d'un enclos
     *
     * @param enclos          L'enclos à afficher
     * @param besoinCreature  Indique s'il est nécessaire d'afficher les créatures dans l'enclos
     * @throws Exception      si problème lors de la récupération d'une valeur
     */
    @SuppressWarnings("serial")
	public void afficherEnclos(Enclos enclos, boolean besoinCreature) throws Exception {
        VueEnclos vueEnclos;
        ArrayList<String> complementLignes;
        if (enclos instanceof Voliere) {
            vueEnclos = VueEnclos.VOLIERE;
            complementLignes = new ArrayList<>() {

                @Serial
                private static final long serialVersionUID = 1L;

                {
                    add("  #============= \033[32m" + enclos.getNom() + "\033[0m =============#  ");
                    add("  #");
                    add("  # \033[32m" + vueEnclos + "\033[0m contient : \033[32m" + enclos.getNbCreatures() + " " + enclos.getNomEspece() + "\033[0m");
                    add("  # Il peut encore accueillir \033[32m" + (enclos.getNbMaxCreatures() - enclos.getNbCreatures()) + "\033[0m/\033[32m" + enclos.getNbMaxCreatures() + "\033[0m créature(s) ");
                    add("  # L'enclos a une superficie de : \033[32m" + enclos.getSuperficie() + " m²\033[0m");
                    add("  # La hauteur de l'enclos est de : \033[32m" + ((Voliere) enclos).getHauteur() + "m\033[0m");
                    if (!enclos.getListeCreatures().values().isEmpty()) {
                        add("  # La créature dominante de l'enclos est \033[32m: " + enclos.getCreatureDominante().getPrenom() + "\033[0m");
                        add("  # L'ambiance globale est : \033[32m" + enclos.getAmbiance().name() + "\033[0m");
                        add("  # Age moyen       : \033[32m" + afficherStatBar(enclos.getAgeMoyen(), CONSTANTES.MAX_AGE) + " " + enclos.getAgeMoyen() + "ans\033[0m");
                        add("  # Bonheur moyen   : \033[32m" + afficherStatBar(enclos.getBonheurMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getBonheurMoyen() + "%\033[0m"); //TODO envisager changement maxindicateur
                        add("  # Faim moyenne    : \033[32m" + afficherStatBar(enclos.getFaimMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getFaimMoyen() + "%\033[0m");
                        add("  # Sommeil moyen   : \033[32m" + afficherStatBar(enclos.getSommeilMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSommeilMoyen() + "%\033[0m");
                        add("  # Sante moyenne   : \033[32m" + afficherStatBar(enclos.getSanteMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSanteMoyen() + "%\033[0m");
                    } else {
                        add("  #");
                        add("  #");
                        add("  #");
                        add("  #");
                        add("  #");
                        add("  #");
                        add("  #");
                    }
                    add("  # Proprete enclos : \033[32m" + afficherStatBar(enclos.getDegrePropreteNumber(), 3) + " " + enclos.getDegreProprete() + "\033[0m");
                    add("  # Etat toit       : \033[32m" + afficherStatBar(((Voliere) enclos).getEtatToitNumber(), 3) + " " + ((Voliere) enclos).getEtatToit() + "\033[0m");
                    add("  # ");
                    add("  # Nombre individu par classe d'ages :");
                    add("  # " + afficherClassesAgesEnclos(enclos)); // # Oeuf(s) 1 | nouveau né 2 | enfant 3 | Jeune 4 | Adulte 5 | Vieux 9
                }
            };

        } else if (enclos instanceof Aquarium) {
            vueEnclos = VueEnclos.AQUARIUM;

            complementLignes = new ArrayList<>() {
                /**
                 *
                 */
                @Serial
                private static final long serialVersionUID = 1L;

                {
                    add("  #========== \033[32m" + enclos.getNom() + "\033[0m ==========  ");
                    add("  #");
                    add("  # \033[32m" + vueEnclos + "\033[0m" + " contient : \033[32m" + enclos.getNbCreatures() + " " + enclos.getNomEspece() + "\033[0m");
                    add("  # Il peut encore accueillir \033[32m" + (enclos.getNbMaxCreatures() - enclos.getNbCreatures()) + "\033[0m" + "/\033[32m" + enclos.getNbMaxCreatures() + "\033[0m creature(s) ");
                    add("  # L'enclos a une superficie de : \033[32m" + enclos.getSuperficie() + " m²\033[0m");
                    add("  # La Salinite est de : \033[32m" + ((Aquarium) enclos).getSaliniteEau() + "\033[0m & La profondeur est de : \033[32m" + ((Aquarium) enclos).getProfondeurBassin() + " m\033[0m");
                    if (!enclos.getListeCreatures().values().isEmpty()) {
                        add("  # La creature dominante de l'enclos est : \033[32m" + enclos.getCreatureDominante().getPrenom() + "\033[0m");
                        add("  # L'ambiance globale est : \033[32m" + enclos.getAmbiance().name() + "\033[0m");
                        add("  #");
                        add("  # Age moyen       : \033[32m" + afficherStatBar(enclos.getAgeMoyen(), CONSTANTES.MAX_AGE) + " " + enclos.getAgeMoyen() + " ans" + "\033[0m");
                        ;
                        add("  # Bonheur moyen   : \033[32m" + afficherStatBar(enclos.getBonheurMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getBonheurMoyen() + "%" + "\033[0m"); //TODO envisager changement maxindicateur
                        add("  # Faim moyenne    : \033[32m" + afficherStatBar(enclos.getFaimMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getFaimMoyen() + "%" + "\033[0m");
                        add("  # Sommeil moyen   : \033[32m" + afficherStatBar(enclos.getSommeilMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSommeilMoyen() + "%" + "\033[0m");
                        add("  # Sante moyenne   : \033[32m" + afficherStatBar(enclos.getSanteMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSanteMoyen() + "%" + "\033[0m");
                    } else {
                        add("  #");
                        add("  #");
                        add("  #");
                        add("  #");
                        add("  #");
                        add("  #");
                        add("  #");
                        add("  #");
                    }
                    add("  #");
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
                    if (!enclos.getListeCreatures().values().isEmpty()) {
                    	add("  # La creature dominante de l'enclos est : \033[32m" + enclos.getCreatureDominante().getPrenom()+"\033[0m");
                        add("  #");
                        add("  # L'ambiance globale est : \033[32m" + enclos.getAmbiance().name()+"\033[0m");
                        add("  # Age moyen       : \033[32m" + afficherStatBar(enclos.getAgeMoyen(), CONSTANTES.MAX_AGE) + " " + enclos.getAgeMoyen() + " ans"+"\033[0m");;
                        add("  # Bonheur moyen   : \033[32m" + afficherStatBar(enclos.getBonheurMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getBonheurMoyen() + "%"+"\033[0m"); //TODO envisager changement maxindicateur
                        add("  # Faim moyenne    : \033[32m" + afficherStatBar(enclos.getFaimMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getFaimMoyen() + "%"+"\033[0m");
                        add("  # Sommeil moyen   : \033[32m" + afficherStatBar(enclos.getSommeilMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSommeilMoyen() + "%"+"\033[0m");
                        add("  # Sante moyenne   : \033[32m" + afficherStatBar(enclos.getSanteMoyen(), CONSTANTES.MAX_INDICATEUR) + " " + enclos.getSanteMoyen() + "%"+"\033[0m");
                    }
                    else {
                    	add("  #");
                    	add("  #");
                    	add("  #");
                    	add("  #");
                    	add("  #");
                    	add("  #");
                    	add("  #");
                    	add("  #");
                    }
                    add("  # Proprete enclos : \033[32m" + afficherStatBar(enclos.getDegrePropreteNumber(), 3) + " " + enclos.getDegreProprete()+"\033[0m");
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
        
        if (besoinCreature) {
        	if (!enclos.getListeCreatures().isEmpty()) {
        		afficherCreatureEnclos(enclos);
        	}
        }  
    }

    
    /**
     * Méthode pour afficher les sexes des créatures dans un enclos
     *
     * @param enclos L'enclos à considérer
     * @return Une chaîne de caractères représentant le nombre de créatures par sexe
     */
    private String afficherSexesEnclos(Enclos enclos) {
    	if (enclos.getListeCreatures().isEmpty()) {
        	return "Pas de creature";
        }
        int[] counts = new int[enclos.getListeCreatures().values().size()];
        for (Creature creature : enclos.getListeCreatures().values()) {
            Enum_Sexe sexe = creature.getSexe();
            counts[sexe.ordinal()]++;
        }
        StringBuilder str = new StringBuilder();
        for (Enum_Sexe sexe : Enum_Sexe.values()) {
            str.append(sexe.name()).append(" ").append("\033[32m").append(counts[sexe.ordinal()]).append("\033[0m").append(" | ");

        }
        if (!str.isEmpty()) {
            str.setLength(str.length() - 2);
        }

        return str.toString();
    }

    
    /**
     * Méthode pour afficher les classes d'âges des créatures dans un enclos
     *
     * @param enclos L'enclos à considérer
     * @return Une chaîne de caractères représentant le nombre de créatures par classe d'âge
     */
    public String afficherClassesAgesEnclos(Enclos enclos) {
    	if (enclos.getListeCreatures().isEmpty()) {
        	return "Pas de creature";
        }
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


    /**
     * Méthode pour afficher une barre de statistiques
     *
     * @param val           La valeur actuelle
     * @param denominateur  La valeur maximale possible
     * @return Une chaîne de caractères représentant la barre de statistiques
     */
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


    /**
     * Méthode pour afficher un combat entre deux créatures
     *
     * @param c1 La première créature
     * @param c2 La deuxième créature
     */
    public void afficherCombat(Creature c1, Creature c2) {
        ArrayList<String> strResultat = new ArrayList<>();
        VueCreature vueCreature1 = getVueCreature(c1);
        VueCreature vueCreature2 = getVueCreature(c2);

        // on remplace les C avec l'ascii art de la créature.
            // a ajouter n hauteur de creature la ligne courante.
        int j = 0 ;
        for (int i = VueEnclos.ARENE.getLignes().size() ; i >= 1 ; i--) {
            strResultat.add(VueEnclos.ARENE.getLignes().get(i-1)); // IL FAUDRA INVERSE LA LISTE AVANT L'AFFICHAGE

            if ( i <= 21 && i >= 11 ) { // lignes auquelles on va ajouter les creatures en ascii  a la place des W et Y
                int nbOfW = (int) VueEnclos.ARENE.getLignes().get(i-1).chars().filter(c -> c == 'W').count();
                int nbOfY = (int) VueEnclos.ARENE.getLignes().get(i-1).chars().filter(c -> c == 'Y').count();
                // une fois qu'on connais le nombre D'W (le place holder)
                // on va mettre au millieu des W le caracter ascii de l'animal et fill avec des espaces.
                StringBuilder wString = new StringBuilder();
                StringBuilder yString = new StringBuilder();
                String ligneAsci1;
                String ligneAsci2;
                int wstartIndex = 0;
                int ystartIndex = 0;
                if ( vueCreature1.getHauteur()-j > 0 ){
                    ligneAsci1 = vueCreature1.getLignes().get(vueCreature1.getHauteur()-j-1);
                    wstartIndex = (nbOfW - (ligneAsci1.length()-16)) / 2;

                } else {
                    ligneAsci1 = " ".repeat(nbOfW);
                    wstartIndex = (nbOfW - (ligneAsci1.length())) / 2;
                }
                if ( vueCreature2.getHauteur()-j > 0 ){
                    ligneAsci2 = vueCreature2.getLignes().get(vueCreature2.getHauteur()-j-1);
                    ystartIndex = (nbOfY - (ligneAsci2.length()-16)) / 2;

                } else {
                    ligneAsci2 =  " ".repeat(nbOfY);
                    ystartIndex = (nbOfY - (ligneAsci2.length())) / 2;

                }
                j ++ ;
                wString.append(" ".repeat(Math.max(0, nbOfW)));
                yString.append(" ".repeat(Math.max(0, nbOfY)));
                wString.replace(wstartIndex, wstartIndex + ligneAsci1.length(), ligneAsci1);
                yString.replace(ystartIndex, ystartIndex + ligneAsci2.length(), ligneAsci2);
                strResultat.set(strResultat.size()-1, strResultat.get(strResultat.size()-1).replace("W".repeat(nbOfW), wString.toString()));
                strResultat.set(strResultat.size()-1, strResultat.get(strResultat.size()-1).replace("Y".repeat(nbOfW), yString.toString()));

            }

        }

        // on inverse strResultat avant de l'affiché :
        for (int i = strResultat.size()-1 ; i >= 0 ; i--) {
            System.out.println(strResultat.get(i));
        }

    }

    private VueCreature getVueCreature(Creature c2) {
        VueCreature vueCreature2;
        if (c2 instanceof Dragon){
            vueCreature2 = VueCreature.DRAGON;
        } else if (c2 instanceof Kraken){
            vueCreature2 = VueCreature.KRAKEN;
        } else if (c2 instanceof Licorne){
            vueCreature2 = VueCreature.LICORNE;
        } else if (c2 instanceof Lycanthrope){
            vueCreature2 = VueCreature.LYCANTHROPE;
        } else if (c2 instanceof Megalodon){
            vueCreature2 = VueCreature.MEGALODON;
        } else if (c2 instanceof Nymphe){
            vueCreature2 = VueCreature.NYMPHE;
        } else if (c2 instanceof Phenix){
            vueCreature2 = VueCreature.PHENIX;
        } else if (c2 instanceof Sirene){
            vueCreature2 = VueCreature.SIRENE;
        } else {
            vueCreature2 = VueCreature.HUMAIN;
        }
        return vueCreature2;
    }

    /**
     * Methode permettant d'afficher un oeuf
     * @param o oeuf que l'on veut afficher
     */
	public void afficherOeuf(Oeuf o) {
		System.out.println(o);
	}
}
