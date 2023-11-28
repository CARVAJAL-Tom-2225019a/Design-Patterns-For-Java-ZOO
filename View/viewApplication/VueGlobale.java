package viewApplication;

import base.Creature;
import base.Enclos;
import enclosImplemente.Aquarium;
import enclosImplemente.Voliere;
import references.CONSTANTES;
import references.Enum_CategorieAge;
import references.Enum_Sexe;

import java.util.ArrayList;

/**
 * Classe permettant l'affichage d'informations
 */
public class VueGlobale {

	/**
	 * Méthode pour afficher le message de passage à la nouvelle année et apeller le
	 * controlleur qui effectuera les actions
	 */
	
	/**
	 * Méthode pour afficher un texte a utilisateur
	 */
	public void afficher(String texte) {
		System.out.println(texte);
	}


	public void afficherEnclos(Enclos enclos) {
		VueEnclos vueEnclos;
		ArrayList<String> complementLignes ;
		if (enclos instanceof Voliere) {
			vueEnclos = VueEnclos.VOLIERE;
			complementLignes = new ArrayList<String>() {
				{
					add("  #========== "+enclos.getNom()+" ==========  ");
					add("  #");
					add("  # "+vueEnclos+ " contient :" + enclos.getNbCreatures() + " " + enclos.getNomEspece() );
					add("  # Il peut encore accueillir "+ (enclos.getNbMaxCreatures()-enclos.getNbCreatures()) +"/"+enclos.getNbMaxCreatures()+" créature(s) ");
					add("  # L'enclos a une superficie de : "+enclos.getSuperficie()+" m²" );
					add("  # La hauteur de l'enclos est de : "+ (   (Voliere) enclos).getHauteur() + "m" );
					add("  # La créature dominante de l'enclos est : "+enclos.getCreatureDominante().getPrenom() );
					add("  # L'ambiance globale est : "+enclos.getAmbiance().name() );
					add("  # Age moyen       : " + afficherStatBar(enclos.getAgeMoyen(), CONSTANTES.MAX_AGE)+" "+enclos.getAgeMoyen()+" ans");;
					add("  # Bonheur moyen   : " + afficherStatBar(enclos.getBonheurMoyen(), CONSTANTES.MAX_INDICATEUR) +" "+enclos.getBonheurMoyen() + "%"); //TODO envisager changement maxindicateur
					add("  # Faim moyenne    : " + afficherStatBar(enclos.getFaimMoyen(),CONSTANTES.MAX_INDICATEUR) +" "+enclos.getFaimMoyen()+"%");
					add("  # Sommeil moyen   : " + afficherStatBar(enclos.getSommeilMoyen(),CONSTANTES.MAX_INDICATEUR)+" "+enclos.getSommeilMoyen()+"%");
					add("  # Santé moyenne   : " + afficherStatBar(enclos.getSanteMoyen(),CONSTANTES.MAX_INDICATEUR)+" "+enclos.getSanteMoyen()+"%");
					add("  # Propreté enclos : " + afficherStatBar(enclos.getDegrePropreteNumber(), 3) + " " + enclos.getDegreProprete());
					add("  # Etat toit       : " + afficherStatBar(((Voliere)enclos).getEtatToitNumber(),3)+ " " + ((Voliere)enclos).getEtatToit());
					add("  #");
					add("  # Nombre individu par classe d'ages :");
					add("  # "+afficherClassesAgesEnclos(enclos)); // # Oeuf(s) 1 | nouveau né 2 | enfant 3 | Jeune 4 | Adulte 5 | Vieux 9
				}
			};

		} else if (enclos instanceof Aquarium) {
			vueEnclos = VueEnclos.AQUARIUM;
			complementLignes = new ArrayList<String>() {
				{
					add("  #========== "+enclos.getNom()+" ==========  ");
					add("  #");
					add("  # "+vueEnclos+ " contient :" + enclos.getNbCreatures() + " " + enclos.getNomEspece() );
					add("  # Il peut encore accueillir "+ (enclos.getNbMaxCreatures()-enclos.getNbCreatures()) +"/"+enclos.getNbMaxCreatures()+" créature(s) ");
					add("  # L'enclos a une superficie de : "+enclos.getSuperficie()+" m²" );
					add("  # La Salinité est de : "+ (   (Aquarium) enclos).getSaliniteEau() + " & La profondeur est de : " +((Aquarium)enclos).getProfondeurBassin());
					add("  # La créature dominante de l'enclos est : "+enclos.getCreatureDominante().getPrenom() );
					add("  # L'ambiance globale est : "+enclos.getAmbiance().name() );
					add("  #");
					add("  # Age moyen       : " + afficherStatBar(enclos.getAgeMoyen(),CONSTANTES.MAX_AGE)+" "+enclos.getAgeMoyen()+" ans");;
					add("  # Bonheur moyen   : " + afficherStatBar(enclos.getBonheurMoyen(), CONSTANTES.MAX_INDICATEUR) +" "+enclos.getBonheurMoyen() + "%"); //TODO envisager changement maxindicateur
					add("  # Faim moyenne    : " + afficherStatBar(enclos.getFaimMoyen(),CONSTANTES.MAX_INDICATEUR) +" "+enclos.getFaimMoyen()+"%");
					add("  # Sommeil moyen   : " + afficherStatBar(enclos.getSommeilMoyen(),CONSTANTES.MAX_INDICATEUR)+" "+enclos.getSommeilMoyen()+"%");
					add("  # Santé moyenne   : " + afficherStatBar(enclos.getSanteMoyen(),CONSTANTES.MAX_INDICATEUR)+" "+enclos.getSanteMoyen()+"%");
					add("  # Propreté enclos : " + afficherStatBar(enclos.getDegrePropreteNumber(), 3) + " " + enclos.getDegreProprete());
					add("  #");
					add("  # Nombre individu par classe d'ages :");
					add("  # "+afficherClassesAgesEnclos(enclos)); // # Oeuf(s) 1 | nouveau né 2 | enfant 3 | Jeune 4 | Adulte 5 | Vieux 9
				}
			};
		} else {
			vueEnclos = VueEnclos.TERRAIN;
			complementLignes = new ArrayList<String>() {
				{
					add("  #========== "+enclos.getNom()+" ==========  ");
					add("  #");
					add("  # "+vueEnclos+ " contient :" + enclos.getNbCreatures() + " " + enclos.getNomEspece() );
					add("  # Il peut encore accueillir "+ (enclos.getNbMaxCreatures()-enclos.getNbCreatures()) +"/"+enclos.getNbMaxCreatures()+" créature(s) ");
					add("  # L'enclos a une superficie de : "+enclos.getSuperficie()+" m²" );
					add("  # La créature dominante de l'enclos est : "+enclos.getCreatureDominante().getPrenom() );
					add("  #");
					add("  # L'ambiance globale est : "+enclos.getAmbiance().name() );
					add("  # Age moyen       : " + afficherStatBar(enclos.getAgeMoyen(),CONSTANTES.MAX_AGE)+" "+enclos.getAgeMoyen()+" ans");;
					add("  # Bonheur moyen   : " + afficherStatBar(enclos.getBonheurMoyen(), CONSTANTES.MAX_INDICATEUR) +" "+enclos.getBonheurMoyen() + "%"); //TODO envisager changement maxindicateur
					add("  # Faim moyenne    : " + afficherStatBar(enclos.getFaimMoyen(),CONSTANTES.MAX_INDICATEUR) +" "+enclos.getFaimMoyen()+"%");
					add("  # Sommeil moyen   : " + afficherStatBar(enclos.getSommeilMoyen(),CONSTANTES.MAX_INDICATEUR)+" "+enclos.getSommeilMoyen()+"%");
					add("  # Santé moyenne   : " + afficherStatBar(enclos.getSanteMoyen(),CONSTANTES.MAX_INDICATEUR)+" "+enclos.getSanteMoyen()+"%");
					add("  # Propreté enclos : " + afficherStatBar(enclos.getDegrePropreteNumber(), 3) + " " + enclos.getDegreProprete());
					add("  #");
					add("  # Nombre individu par classe d'ages :");
					add("  # "+afficherClassesAgesEnclos(enclos)); // # Oeuf(s) 1 | nouveau né 2 | enfant 3 | Jeune 4 | Adulte 5 | Vieux 9
					add("  # "+afficherSexesEnclos(enclos)); // # 4 Femmelles | 4 males
				}
			};
		}
		int i = 0;
		for (String ligne : vueEnclos.getLignes()) {
			//System.out.println(ligne);

			String ligneComplete = ligne + complementLignes.get(i)+ "\n" ;// ajouter les attributs
			System.out.print(ligneComplete);
			i++ ;
		}
	}

	private String afficherSexesEnclos(Enclos enclos) {
		int[] counts = new int[enclos.getListeCreatures().values().size()];
		for (Creature creature : enclos.getListeCreatures().values()){
			Enum_Sexe sexe = creature.getSexe();
			counts[sexe.ordinal()]++;
		}
		StringBuilder str = new StringBuilder();
		for (Enum_Sexe sexe : Enum_Sexe.values()){
			str.append(sexe.name()).append(" ").append(counts[sexe.ordinal()]).append(" | ");

		}
		if (!str.isEmpty()) {
			str.setLength(str.length() - 2);
		}

		return str.toString();
	}

	public String afficherClassesAgesEnclos(Enclos enclos) {
		int[] counts = new int[Enum_CategorieAge.values().length];

		for (Creature creature : enclos.getListeCreatures().values()) {
			Enum_CategorieAge categorieAge = creature.getCategorieAge();
			counts[categorieAge.ordinal()]++;
		}

		StringBuilder str = new StringBuilder();

		// Construire la chaîne résultante en utilisant les valeurs de l'énumération
		for (Enum_CategorieAge categorieAge : Enum_CategorieAge.values()) {
			str.append(categorieAge.getLibelle()).append(" ").append(counts[categorieAge.ordinal()]).append(" | ");
		}

		// Supprimer le dernier séparateur "|"
		if (!str.isEmpty()) {
			str.setLength(str.length() - 2);
		}

		return str.toString();
	}


	public String afficherStatBar(int val, int denominateur) {
		StringBuilder statBar = new StringBuilder("[");
		int tailleStatBar = 10;

		// Calcul du pourcentage résultant
		double pourcentage = (double) val / denominateur;

		// Calcul du nombre d'égalités à afficher en fonction du pourcentage
		int nbEgalites = (int) (tailleStatBar * pourcentage);

		// Limiter le nombre d'égalités à la taille maximale
		nbEgalites = Math.min(nbEgalites, tailleStatBar);

		// Affichage des égalités
		statBar.append("=".repeat(Math.max(0, nbEgalites)));

		// Remplissage du reste avec des espaces
		statBar.append(" ".repeat(Math.max(0, tailleStatBar - nbEgalites)));

		statBar.append("]");
		return statBar.toString();
	}

}
