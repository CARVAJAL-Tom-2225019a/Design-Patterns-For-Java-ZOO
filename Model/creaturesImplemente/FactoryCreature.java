package creaturesImplemente;

import base.Creature;
import references.Enum_Especes;

/**
 * Cette classe implémente une fabrique de créatures, permettant de créer des instances
 * en fonction du type d'espèce spécifié
 */
public class FactoryCreature {

	/**
     * Méthode statique pour créer une nouvelle instance de créature en fonction du type d'espèce
     *
     * @param nomEspece l'espèce de la créature à créer
     * @return Une nouvelle instance de la creature correspondant à l'espèce spécifiée
     * @throws Exception si l'espèce spécifiée est inconnue
     */
	@SuppressWarnings("unchecked")
	public static <T extends Creature> T newCreature(Enum_Especes nomEspece) throws Exception {
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
