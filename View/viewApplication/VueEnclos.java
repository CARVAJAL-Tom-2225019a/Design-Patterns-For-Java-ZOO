package viewApplication;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum VueEnclos {
  
	TERRAIN("Terrain", new ArrayList<String>() {
        {
            add("+---------------------------------------+");
            add("|     *                   *             |");
            add("|      *   _\\/_  _\\|/_                  |");
            add("|/_        //o\\             _  *   *    |");
            add("|            |               *  * /     |");
            add("|      *  ___|__    ___       \\ *| *    |");
            add("| _\\|/_                                 |");
            add("|       _\\/_   _\\|/_    @          .    |");
            add("|       /o\\\\          _\\|_  .           |");
            add("|  *     |      *               .   .  ~|");
            add("|      __|__             .       .   ~ ~|");
            add("|         *        .     __  .     ~ ~~~|");
            add("|                     __/  \\   .  ~~~ ~~|");
            add("|  *   _\\|/_         /_ \\__ \\   ~~ ~~   |");
            add("|             .       .     ~~ ~~~~   ≈≈|");
            add("|         .       .        ~~~~ ~   ≈≈  |");
            add("| ______              .  ~~ ~~~  ≈    ≈≈|");
            add("+---------------------------------------+");
        }
    }),


    AQUARIUM("Aquarium", new ArrayList<String>(){
        {
            add("+---------------------------------------+");
            add("|'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.|");
            add("|                                       |");
            add("|,__,.-=~__,.-=~'`^`'~=-.,.-=~,__,.-=~'`|");
            add("|                                       |");
            add("|'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.|");
            add("|                                       |");
            add("|,__,.-=~__,.-=~'`^`'~=-.,.-=~,__,.-=~'`|");
            add("|                                       |");
            add("|'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.|");
            add("|                                       |");
            add("|,__,.-=~__,.-=~'`^`'~=-.,.-=~,__,.-=~'`|");
            add("|                                       |");
            add("|'~=-.,__,.-=~'`^`'~=-.,__,.-=~'`^`'~=-.|");
            add("|                                       |");
            add("|,__,.-=~__,.-=~'`^`'~=-.,.-=~,__,.-=~'`|");
            add("|                                       |");
            add("+---------------------------------------+");
        }
    }),

    VOLIERE("Voliere", new ArrayList<String>(){
        {
            add("+---------------------------------------+");
            add("|                        (   (   ))     |");
            add("|                _        `- __.'       |");
            add("|              (`  ).                   |");
            add("|             (     ).              .:(`|");
            add("|)           _(       '`.          :(   |");
            add("|        .=(`(      .   )     .--  `.  (|");
            add("|       ((    (..__.:'-'   .+(   )   ` _|");
            add("|`.     `(       ) )       (   .  )     |");
            add("|  )      ` __.:'   )     (   (   ))    |");
            add("|)  )  ( )       --'       `- __.'      |");
            add("|.-'  (_.'          .')                 |");
            add("|           ._     (_  )                |");
            add("|        .-(`  )                        |");
            add("|       :(      ))                      |");
            add("|       `(    )  ))                .--  |");
            add("|         ` __.:'               .+(   ) |");
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
