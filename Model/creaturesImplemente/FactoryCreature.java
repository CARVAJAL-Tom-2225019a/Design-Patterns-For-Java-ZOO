package creaturesImplemente;

import java.time.Duration;

import base.Creature;
import references.*;

public class FactoryCreature {
	// Méthode statique pour créer une instance en fonction du type d'espèce et des paramètres
    @SuppressWarnings("unchecked")
	public static <T extends Creature> T newCreature(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille) throws Exception {
        switch (nomEspece) {
            case Dragon:
            	return (T) new Dragon(nomEspece, sexe, poids, taille, "Grrroooaaarrr", 5);
            case Kraken:
            	return (T) new Kraken(nomEspece, sexe, poids, taille, "Glooouuuglooouuu", 4);
            case Licorne :
            	return (T) new Licorne(nomEspece, sexe, poids, taille, "Hiiihiiiinnn", 1);
            case Lycanthrope :
            	return (T) new Lycanthrope(nomEspece, sexe, poids, taille, "Aaahooouu", 2);
            case Megalodon :
            	return (T) new Megalodon(nomEspece, sexe, poids, taille, "Grrruuulll", 4);
            case Nymphe:
            	return (T) new Nymphe(nomEspece, sexe, poids, taille, "Laalaaalaaa", 5);
            case Phenix :
            	return (T) new Phenix(nomEspece, sexe, poids, taille, "Craaaack-flaaaame", 2));
            case Sirene :
            	return (T) new Sirene(nomEspece, sexe, poids, taille, "Aaaahh-ooohh", 1);
            default:
                throw new Exception("Espèce inconnue");
        }
    }
}
