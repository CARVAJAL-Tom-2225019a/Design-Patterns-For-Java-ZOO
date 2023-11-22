package references;

public enum Enum_PrenomFeminin {
    Marie, Anne, Sophie, Isabelle, Catherine, Nathalie, Francoise, Martine, Valerie, Veronique, Sylvie, Christine,
    Monique, Chantal, Helene, Laurence, Virginie, Sandrine, Patricia, Annie, Brigitte, Pascale, Corinne,
    AnneMarie, Elisabeth, Michele, Jacqueline, Carole, Beatrice;
    public static Enum_PrenomFeminin getRandomName(){
        return Enum_PrenomFeminin.values()[(int) (Math.random() * Enum_PrenomFeminin.values().length)];
    }
}
