package viewApplication;

import java.util.ArrayList;

/**
 * Classe représentant la vue d'un enclos
 */
@SuppressWarnings("serial")
public enum VueEnclos {
  
	TERRAIN(new ArrayList<>() {
        {
            add("+---------------------------------------+");
            add("|\033[30;42m     *                   *             \033[39;49m|");
            add("|\033[30;42m      *   _\\/_  _\\|/_                  \033[39;49m|");
            add("|\033[30;42m/_        //o\\             _  *   *    \033[39;49m|");
            add("|\033[30;42m            |               *  * /     \033[39;49m|");
            add("|\033[30;42m      *  ___|__    ___       \\ *| *    \033[39;49m|");
            add("|\033[30;42m _\\|/_                                 \033[39;49m|");
            add("|\033[30;42m       _\\/_   _\\|/_    @          .    \033[39;49m|");
            add("|\033[30;42m       /o\\\\          _\\|_  .           \033[39;49m|");
            add("|\033[30;42m  *     |      *               .   .  ~\033[39;49m|");
            add("|\033[30;42m      __|__             .       .   ~ ~\033[39;49m|");
            add("|\033[30;42m         *        .     __  .     ~ ~~~\033[39;49m|");
            add("|\033[30;42m                     __/  \\   .  ~~~ ~~\033[39;49m|");
            add("|\033[30;42m  *   _\\|/_         /_ \\__ \\   ~~ ~~   \033[39;49m|");
            add("|\033[30;42m             .       .     ~~ ~~~~   ≈≈\033[39;49m|");
            add("|\033[30;42m         .       .        ~~~~ ~   ≈≈  \033[39;49m|");
            add("|\033[30;42m ______              .  ~~ ~~~  ≈    ≈≈\033[39;49m|");
            add("+---------------------------------------+");
        }
    }),


    AQUARIUM(new ArrayList<>() {
        {
            add("+---------------------------------------+");
            add("|\033[30;44m'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.\033[39;49m|");
            add("|\033[30;44m                                       \033[39;49m|");
            add("|\033[30;44m,__,.-=~__,.-=~'`^`'~=-.,.-=~,__,.-=~'`\033[39;49m|");
            add("|\033[30;44m                                       \033[39;49m|");
            add("|\033[30;44m'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.\033[39;49m|");
            add("|\033[30;44m                                       \033[39;49m|");
            add("|\033[30;44m,__,.-=~__,.-=~'`^`'~=-.,.-=~,__,.-=~'`\033[39;49m|");
            add("|\033[30;44m                                       \033[39;49m|");
            add("|\033[30;44m'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.\033[39;49m|");
            add("|\033[30;44m                                       \033[39;49m|");
            add("|\033[30;44m,__,.-=~__,.-=~'`^`'~=-.,.-=~,__,.-=~'`\033[39;49m|");
            add("|\033[30;44m                                       \033[39;49m|");
            add("|\033[30;44m'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.\033[39;49m|");
            add("|\033[30;44m                                       \033[39;49m|");
            add("|\033[30;44m,__,.-=~__,.-=~'`^`'~=-.,.-=~,__,.-=~'`\033[39;49m|");
            add("|\033[30;44m                                       \033[39;49m|");
            add("+---------------------------------------+");
        }
    }),

    VOLIERE(new ArrayList<>() {
        {
            add("+---------------------------------------+");
            add("|\033[37;104m                        (   (   ))     \033[39;49m|");
            add("|\033[37;104m                _        `- __.'       \033[39;49m|");
            add("|\033[37;104m              (`  ).                   \033[39;49m|");
            add("|\033[37;104m             (     ).              .:(`\033[39;49m|");
            add("|\033[37;104m)           _(       '`.          :(   \033[39;49m|");
            add("|\033[37;104m        .=(`(      .   )     .--  `.  (\033[39;49m|");
            add("|\033[37;104m       ((    (..__.:'-'   .+(   )   ` _\033[39;49m|");
            add("|\033[37;104m`.     `(       ) )       (   .  )     \033[39;49m|");
            add("|\033[37;104m  )      ` __.:'   )     (   (   ))    \033[39;49m|");
            add("|\033[37;104m)  )  ( )       --'       `- __.'      \033[39;49m|");
            add("|\033[37;104m.-'  (_.'          .')                 \033[39;49m|");
            add("|\033[37;104m           ._     (_  )                \033[39;49m|");
            add("|\033[37;104m        .-(`  )                        \033[39;49m|");
            add("|\033[37;104m       :(      ))                      \033[39;49m|");
            add("|\033[37;104m       `(    )  ))                .--  \033[39;49m|");
            add("|\033[37;104m         ` __.:'               .+(   ) \033[39;49m|");
            add("+---------------------------------------+");
        }
    }),


    ARENE(new ArrayList<>() {{
        add("+-----------------------------------------------------------------------------------------+");
        add("|          <|  *         *    *              *                     *      ---.   *        |");
        add("|  *        A                    *      *           *                      \\   \\      *   |");
        add("|          /.\\        *                                                     )^  |         |");
        add("|     <|  [\"\"M#                          *                                 <_,  |         |");
        add("|      A   | #                                              *              ./  /    *     |");
        add("|     /.\\ [\"\"M#            *                                              ---'            |");
        add("|    [\"\"M# | #  U\"U#U                *                 *                                  |");
        add("|     | #  | #  \\ .:/                                                    *                |");
        add("|   * | #  | #___| #                                                                      |");
        add("|     | \"--'     .-\"                   WWWWWWWWWWWWWWWWWWWW  YYYYYYYYYYYYYYYYYYYY         |");
        add("|   |\"-\"-\"-\"-\"-#-#-##                  WWWWWWWWWWWWWWWWWWWW  YYYYYYYYYYYYYYYYYYYY         |");
        add("|   |     # ## ######                  WWWWWWWWWWWWWWWWWWWW *YYYYYYYYYYYYYYYYYYYY         |");
        add("|    \\       .::::'/                   WWWWWWWWWWWWWWWWWWWW  YYYYYYYYYYYYYYYYYYYY         |");
        add("|     \\      ::::'/      *             WWWWWWWWWWWWWWWWWWWW  YYYYYYYYYYYYYYYYYYYY         |");
        add("|   :8a|    # # ##                     WWWWWWWWWWWWWWWWWWWW  YYYYYYYYYYYYYYYYYYYY         |");
        add("|   ::88a      ###                     WWWWWWWWWWWWWWWWWWWW  YYYYYYYYYYYYYYYYYYYY         |");
        add("|  ::::888a  8a ##::.                  WWWWWWWWWWWWWWWWWWWW  YYYYYYYYYYYYYYYYYYYY         |");
        add("|  ::::::888a88a[]::::                 WWWWWWWWWWWWWWWWWWWW  YYYYYYYYYYYYYYYYYYYY         |");
        add("| :::::::::FLOUKSAC8a::::. ..          WWWWWWWWWWWWWWWWWWWW  YYYYYYYYYYYYYYYYYYYY         |");
        add("| :::::8::::888:V8888:::::::::...      WWWWWWWWWWWWWWWWWWWW  YYYYYYYYYYYYYYYYYYYY         |");
        add("|::':::88::::888::V88a        .-~~-.                       _______________________________|");
        add("|:: ::::88a::::88a:V88a      :     (.                  _-''         __---__-- __          |");
        add("|' .: ::Y88a:::::8a:V88a      \"._(  !    E====3      .'          _----_-- -------_-__     |");
        add("|  :' ::::8P::::::::::88aa.       `uu              .'       ___-  -__--- ____  --   ---_  |");
        add("| ::: :::::P:::::::::::888aaa                     /          _  --_ --- __  --- __--      |");
        add("|.::  :::::::::::::::::::V88as88a...s88aa.       /       ___------- ______ --__ ----__    |");
        add("+-----------------------------------------------------------------------------------------+");


    }})
    ;

    private final ArrayList<String> lignes;


    /**
     * Constructeur
     * @param lignes liste des lignes pour l'affichage de l'enclos
     */
    VueEnclos(ArrayList<String> lignes) {
        this.lignes = lignes;
    }

    /**
     * Methode permettant de recuperer l'ensemble des lignes pour l'affichage de l'enclos
     * @return uune liste contenant les lignes
     */
    public ArrayList<String> getLignes() {
        return lignes;
    }
}
