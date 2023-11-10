package base;

import java.time.Duration;
import java.util.Map;

import enums.*;
import impl.*;

public class CreateCreature {
	// Méthode statique pour créer une instance en fonction du type d'espèce et des paramètres
    public Creature createCreature(Enum_Especes nomEspece, Enum_Sexe sexe, double poids, double taille, String bruit, Duration duree) throws Exception {
        switch (nomEspece) {
            case Dragon:
                return new Dragon(nomEspece, sexe, poids, taille, bruit, duree);
            case Kraken:
                return new Kraken(nomEspece, sexe, poids, taille, bruit, duree);
            case Licorne :
            	return new Licorne(nomEspece, sexe, poids, taille, bruit, duree);
            case Lycanthrope :
            	return new Lycanthrope(nomEspece, sexe, poids, taille, bruit, duree);
            case Megalodon :
            	return new Megalodon(nomEspece, sexe, poids, taille, bruit, duree);
            case Nymphe:
            	return new Nymphe(nomEspece, sexe, poids, taille, bruit, duree);
            case Phenix :
            	return new Phenix(nomEspece, sexe, poids, taille, bruit, duree);
            case Sirene :
            	return new Sirene(nomEspece, sexe, poids, taille, bruit, duree);
            default:
                throw new Exception("Espèce inconnue");
        }
    }
}
