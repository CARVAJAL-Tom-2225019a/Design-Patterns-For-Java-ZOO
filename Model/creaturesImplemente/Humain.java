package creaturesImplemente;

import references.Enum_Sexe;

public class Humain {
	
	private String nom;
    private Enum_Sexe sexe;
    private int age;
    
    public Humain(String nom, Enum_Sexe sexe, int age) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
    }
    
    /**
     * Methode pour afficher les informations du maitre zoo
     * 
     * @return une chaine de caractere avec les informations
     */
    public String toString() {
    	return "Maitre "+nom+" a "+age+" an(s) et est "+sexe+"\n";
    }

}
