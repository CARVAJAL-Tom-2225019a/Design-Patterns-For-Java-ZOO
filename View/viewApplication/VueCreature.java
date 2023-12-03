package viewApplication;

import java.util.ArrayList;


/*
+---------------+-----------+---------+--------------+
| Lycanthrope : | Licorne : |Licorne :| Nymphes :    |
|  --__         |  \        | \       |  @           |
| |Ω°vv'        | <;\____   |<°\__    | />>          |
| /|\           |  '.____\  |  M=M\   | /*\          |
| /'\           |   <|  ||  |         |              |
+-----------+---+-----------+-------+-+--------------+
| Sirènes : | Mégalodon :           | Kraken :       |
|     ~@    |        __             |     .-.        |
|    /|**   |   ___-/ (__       __  | .-. \   _._    |
| |><=_/    | <" o )))   ""--../ /  | ` /  )_/_  '   |
|           |  'VVV=\_\==-"''"\_(   |  (_ (@@p_) _   |
|           |                       |     (((/  (    |
|           |                       | .   /  '.-'    |
|           |                       |  '-'           |
+-----------+-----+-----------------+----------------+
| Phénix :        | Dragon :                         |
|     .-.         |        ___                       |
|   _%  '"        |        )__\\ >O.__    ZEFSDEF    |
| <°/             |       _ )__\/ /~ X <XOAPZKD SEFE |
|  \\__           |     /( ) )_ \/       EFGES EFESQ |
|  (~\ ""\        | '._/  \\_  \\_           EFEGSQ  |
|   \ '=-"\       |                                  |
|   _/_/   '._.%  |                                  |
+--------+--------+----------------------------------+
| Œuf  : |
| O      |
+--------+
*/

@SuppressWarnings("serial")
public enum VueCreature {
    PARCHEMIN(10,32,new ArrayList<String>(){
        /**
		 * 
		 */

		{
            add("  _________________________     ");
            add(" / \\                       \\.   ");
            add("|   | XXXXXXXXXXXXXXXXXXXX |.   ");  // XXX correspond au place holder du prenom l3
            add(" \\_/| ZZZZZZZZZZZZZZZZZZZZ |.   "); // ZZZ correspond au place holder de l'espece l4
            add("    |                      |.   ");
            add("    | CCCCCCCCCCCCCCCCCCCC |.   "); // CCC correspond au place holder de la créature (l6 a répété hauteur creature fois )
                                                                                                       // entre 1 et 7 fois




            add("    |                      |.   ");
            add("    |   ___________________|___ ");
            add("    |  /                      /.");
            add("    \\_/______________________/. ");








        }
    }),
    DRAGON(5,18,new ArrayList<String>() {
        /**
		 * 
		 */

		{
            add("\033[31;49m        ___         \033[39;49m");
            add("\033[31;49m        )__\\\\ >O.__ \033[39;49m");
            add("\033[31;49m       _ )__\\/ /~ X \033[39;49m");
            add("\033[31;49m     /( ) )_ \\/     \033[39;49m");
            add("\033[31;49m '._/  \\\\_  \\\\_     \033[39;49m");
        }
    }),
    HUMAIN(3,3,new ArrayList<String>() {
        /**
		 * 
		 */

		{
            add(" o ");
            add("/|\\");
            add("/'\\");
        }
    }),
    KRAKEN(7,12,new ArrayList<String>() {
        /**
		 * 
		 */

		{
            add("\033[35;49m         .-.        \033[39;49m");
            add("\033[35;49m     .-. \\   _._    \033[39;49m");
            add("\033[35;49m     ` /  )_/_  '   \033[39;49m");
            add("\033[35;49m      (_ (@@p_) _   \033[39;49m");
            add("\033[35;49m         (((/  (    \033[39;49m");
            add("\033[35;49m     .   /  '.-'    \033[39;49m");
            add("\033[35;49m      '-'           \033[39;49m");
        }                //"                    "
    }),
    LICORNE(3,6,new ArrayList<String>() {
        /**
		 * 
		 */

		{
            add("\033[95;49m        \\           \033[39;49m");
            add("\033[37;49m       <°\\__        \033[39;49m");
            add("\033[37;49m         M=M\\       \033[39;49m");
        }                //"                    "
    }),
    LYCANTHROPE(4,6,new ArrayList<String>() {
        /**
		 * 
		 */

		{
            add("\033[90;49m         --__       \033[39;49m");
            add("\033[90;49m        |Ω°vv'      \033[39;49m");
            add("\033[90;49m        /|\\         \033[39;49m");
            add("\033[90;49m        /'\\         \033[39;49m");
        }                //"                    "
    }),
    MEGALODON(4,20,new ArrayList<String>() {
        /**
		 * 
		 */

		{
            add("\033[90;49m       __           \033[39;49m");
            add("\033[90;49m  ___-/ (__       __\033[39;49m");
            add("\033[90;49m<\" o )))   \"\"--../ /\033[39;49m");
            add("\033[37;49m 'VVV=\\_\\==-\"''\"\\_( \033[39;49m");
        }                //"                    "
    }),
    NYMPHE(3,3 , new ArrayList<String>() {
        /**
		 * 
		 */

		{
            add("\033[32;49m          @         \033[39;49m");
            add("\033[32;49m         />>        \033[39;49m");
            add("\033[32;49m         /*\\       \033[39;49m");
        }                //"                    "
    }),
    OEUF(1,1,new ArrayList<String>() {
        /**
		 * 
		 */

		{
            add("O");
        }
    }),
    
	PHENIX(7,14,new ArrayList<String>() {
        /**
		 * 
		 */

		{
            add("\033[33;49m       .-.          \033[39;49m");
            add("\033[33;49m     _%  '\"         \033[39;49m");
            add("\033[33;49m   <°/             \033[39;49m ");
            add("\033[33;49m    \\\\__            \033[39;49m");
            add("\033[33;49m    (~\\ \"\"\\         \033[39;49m");
            add("\033[33;49m     \\ '=-\"\\        \033[39;49m");
            add("\033[33;49m     _/_/   '._.%   \033[39;49m");
        }                //"                    "
    }),
    SIRENE(3,7,new ArrayList<String>() {
        /**
		 * 
		 */

		{
            add("\033[33;49m          ~@        \033[39;49m");
            add("\033[37;49m         /|**       \033[39;49m");
            add("\033[34;49m      |><=_/        \033[39;49m");
        }                //"                    "
    });

    private int hauteur;
    private int largeur;
    private ArrayList<String> lignes;

    /**
     * Constructeur
     * @param hauteur de la creature à afficher
     * @param largeur de la creature à afficher
     * @param lignes à afficher
     */
    VueCreature( int hauteur,int largeur,ArrayList<String> lignes) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.lignes = lignes;
    }

	/**
	 * Recuperer l'nesemble des lignes à afficher pour la creature
	 * @return une liste contenant les lignes
	 */
    public ArrayList<String> getLignes() {
        return lignes;
    }
    /**
     * Recuperer la largeur de la creature pour son affichage
     * @return la largeur
     */
    public int getLargeur() {
        return largeur;
    }
    /**
     * Recuperer la hauteur de la creature pour son affichage
     * @return la hauteur
     */
    public int getHauteur() {
        return hauteur;
    }
}
