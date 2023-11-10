package CreaturesImplemente;

import java.time.Duration;

import base.Creature;
import enums.*;

public class FactoryCreature {
	// Méthode statique pour créer une instance en fonction du type d'espèce et des paramètres
    @SuppressWarnings("unchecked")
	public static <T extends Creature> T newCreature(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille) throws Exception {
        switch (nomEspece) {
            case Dragon:
            	return (T) new Dragon(nomEspece, sexe, poids, taille, "Grrroooaaarrr", Duration.parse("P15D"));
            case Kraken:
            	return (T) new Kraken(nomEspece, sexe, poids, taille, "Glooouuuglooouuu", Duration.parse("P18D"));
            case Licorne :
            	return (T) new Licorne(nomEspece, sexe, poids, taille, "Hiiihiiiinnn", Duration.parse("P11D"));
            case Lycanthrope :
            	return (T) new Lycanthrope(nomEspece, sexe, poids, taille, "Aaahooouu", Duration.parse("P7D"));
            case Megalodon :
            	return (T) new Megalodon(nomEspece, sexe, poids, taille, "Grrruuulll", Duration.parse("P22D"));
            case Nymphe:
            	return (T) new Nymphe(nomEspece, sexe, poids, taille, "Laalaaalaaa", Duration.parse("P10D"));
            case Phenix :
            	return (T) new Phenix(nomEspece, sexe, poids, taille, "Craaaack-flaaaame", Duration.parse("P4D"));
            case Sirene :
            	return (T) new Sirene(nomEspece, sexe, poids, taille, "Aaaahh-ooohh", Duration.parse("P12D"));
            default:
                throw new Exception("Espèce inconnue");
        }
    }
}
