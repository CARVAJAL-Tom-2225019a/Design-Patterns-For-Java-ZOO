package creaturesImplemente;

import base.Creature;
import references.*;

public class FactoryCreature {
	// Méthode statique pour créer une instance en fonction du type d'espèce
	@SuppressWarnings("unchecked")
	public static <T extends Creature> T newCreature(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille) throws Exception {
        return switch (nomEspece) {
            case Dragon -> (T) new Dragon( "Grrroooaaarrr");
            case Kraken -> (T) new Kraken( "Glooouuuglooouuu");
            case Licorne -> (T) new Licorne("Hiiihiiiinnn");
            case Lycanthrope -> (T) new Lycanthrope( "Aaahooouu");
            case Megalodon -> (T) new Megalodon( "Grrruuulll");
            case Nymphe -> (T) new Nymphe( "Laalaaalaaa");
            case Phenix -> (T) new Phenix( "Craaaack-flaaaame" );
            case Sirene -> (T) new Sirene( "Aaaahh-ooohh");
            default -> throw new Exception("Espèce inconnue");
        };
    }
}
