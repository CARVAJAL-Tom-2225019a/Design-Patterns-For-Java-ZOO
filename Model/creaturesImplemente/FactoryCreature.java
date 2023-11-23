package creaturesImplemente;

import base.Creature;
import references.*;

public class FactoryCreature {
	// Méthode statique pour créer une instance en fonction du type d'espèce
	@SuppressWarnings("unchecked")
	public static <T extends Creature> T newCreature(Enum_Especes nomEspece) throws Exception {
        switch (nomEspece) {
            case Dragon : 
            	return (T) new Dragon( "Grrroooaaarrr");
            case Kraken : 
            	return (T) new Kraken( "Glooouuuglooouuu");
            case Licorne :
            	return (T) new Licorne("Hiiihiiiinnn");
            case Lycanthrope :
            	return (T) new Lycanthrope( "Aaahooouu");
            case Megalodon :
            	return (T) new Megalodon( "Grrruuulll");
            case Nymphe :
            	return (T) new Nymphe( "Laalaaalaaa");
            case Phenix :
            	return (T) new Phenix( "Craaaack-flaaaame" );
            case Sirene :
            	return (T) new Sirene( "Aaaahh-ooohh");
            default :
            	throw new Exception("Espèce inconnue");
        }
    }
}
