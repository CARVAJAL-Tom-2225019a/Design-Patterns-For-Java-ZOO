package references;

public enum Enum_PrenomMasculin {
    Didier,George,JeanEude, Patrick, Gerard, Jean, Pierre, Paul, Jacques, Michel, Andre, Alain, Bernard, Robert, Serge, Christian, Daniel, Claude, Henri, Gilles, Philippe, Yves;

    public static Enum_PrenomMasculin getRandomName(){
        return Enum_PrenomMasculin.values()[(int) (Math.random() * Enum_PrenomMasculin.values().length)];
    }
}
