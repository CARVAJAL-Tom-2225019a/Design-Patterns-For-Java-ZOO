package creaturesImplemente;

import references.Enum_Sexe;

/**
 * Cette classe correspond a un humain
 * soit pour le maitre zoo
 * soit pour la transformation d'un lycanthrope
 *
 */
public class Humain {
	
	private final String nom;
    private final Enum_Sexe sexe;
    private final int age;
    
    /**
     * Constructeur
     * @param nom
     * @param sexe
     * @param age
     */
    public Humain(String nom, Enum_Sexe sexe, int age) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
    }
    
    
    /**
     * Getters
     */
    public String getNom() {
    	return nom;
    }
    public Enum_Sexe getSexe() {
    	return sexe;
    }
    public int getAge() {
    	return age;
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
