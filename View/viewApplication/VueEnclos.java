package viewApplication;

import java.util.ArrayList;

public enum VueEnclos {
    TERRAIN("Terrain", new ArrayList<String>() {
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


    AQUARIUM("Aquarium", new ArrayList<String>(){
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

    VOLIERE("Voliere", new ArrayList<String>(){
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
    });

    private final String libelle;
    private final ArrayList<String> lignes;


    VueEnclos(String libelle, ArrayList<String> lignes) {
        this.libelle = libelle;
        this.lignes = lignes;
    }

    public String getLibelle() {
        return libelle;
    }

    public ArrayList<String> getLignes() {
        return lignes;
    }
}
