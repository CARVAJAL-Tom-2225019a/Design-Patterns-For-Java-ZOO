package base;

import references.*;

import java.util.*;
import java.util.function.Function;

/**
 * Cette classe abstraite correspond a un enclos
 *
 */
public abstract class Enclos {
	
	private Enum_Especes nomEspece;
	
	private final String nom;
	private final double superficie;
	private int nbCreatures;
	private TreeMap<Integer, Creature> listeCreatures;
	private Enum_DegrePropreteEnclos degreProprete;
	
	
	/**
	 * CONSTRUCTEUR
	 * @param nom de l'enclos
	 * @param superficie de l'enclos
	 */
	public Enclos(String nom, double superficie) {
		this.nomEspece = null;
		this.nom = nom;
		this.superficie = superficie;
		this.nbCreatures = 0;
		// Tri des creatures par age
		this.listeCreatures = new TreeMap<>();
		this.degreProprete = Enum_DegrePropreteEnclos.bon;
	}

	
	/**
	 * Recuperer le nom de l'espece contenu dans l'enclos
	 * @return le nom de l'espece
	 */
	public Enum_Especes getNomEspece() {
		return nomEspece;
	}
	/**
	 * Recuperer le nom de l'enclos
	 * @return le nom de l'enclos
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Recuperer la superficie de l'enclos
	 * @return la superficie en mètres²
	 */
	public double getSuperficie() {
		return superficie;
	}
	/**
	 * Recuperer le nombre maximum de creature qu'il peut y avoir dans l'enclos
	 * @return le nombre max de créatures
	 */
	public int getNbMaxCreatures() {
		return CONSTANTES.NB_CREATURE_PAR_ENCLOS_MAX;
	}
	/**
	 * Recuperer le nombre de creature qu'il y a actuellement dans l'enclos
	 * @return le nombre de créatures présentent
	 */
	public int getNbCreatures() {
		return nbCreatures;
	}
	/**
	 * Recuperer la liste des créatures présentent dans l'enclos
	 * @return un dictionnaire contenant les créatures et leurs index
	 */
	public TreeMap<Integer, Creature> getListeCreatures() {
		return listeCreatures;
	}
	/**
	 * Recuperer le degré de propreté de l'enclos
	 * @return le degré de propreté de l'enclos
	 */
	public Enum_DegrePropreteEnclos getDegreProprete() {
		return degreProprete;
	}
	/**
	 * Modification de l'etat de l'enclos
	 * Methode seravnt pour les tests unitaires
	 * @param etat de l'enclos
	 */
	public void setEtatEnclos(Enum_DegrePropreteEnclos etat) {
		degreProprete = etat;
	}
	
	
	/**
	 * Methode permettant d'afficher les caracteristiques de l'enclos 
	 * et les creatures qu'il contient
	 * 
	 * @return la chaine de caractère contenant les informations
	 */
	public String toString() {
		StringBuilder chaine = new StringBuilder("Enclos " + nom + " de superficie " + superficie + " m^2 pouvant contenir au "
                + "plus " + CONSTANTES.NB_CREATURE_PAR_ENCLOS_MAX + " creatures.\n Il y a actuellement " + nbCreatures + " creatures.\n"
                + "Degre de proprete : " + degreProprete + "\n");
		for (Creature creature : listeCreatures.values()) {
			// si la creature est vivante
			if (creature.isVivant()) {
				chaine.append("Index : ").append(trouverCleParCreature(creature)).append("\n");
				chaine.append(creature);
			}

		}
		return chaine.toString();
	}


	/**
	 * Methode permettant de récupérer l'ensemble des créatures qui ont un besoin
	 * @return un ensemble contenant les créatures qui ont un besoib
	 */
	public HashSet<Creature> getCreaturesAyantUnBesoin() {
	    HashSet<Creature> listeCreatureAvecBesoin = new HashSet<>();
	    int seuil = 5;

	    ajouterCreaturesAvecBesoin(listeCreatureAvecBesoin, "faim", Creature::getIndicateurFaim, seuil);
	    ajouterCreaturesAvecBesoin(listeCreatureAvecBesoin, "sommeil", Creature::getIndicateurSommeil, seuil);
	    ajouterCreaturesAvecBesoin(listeCreatureAvecBesoin, "sante", Creature::getIndicateurSante, seuil);

	    return listeCreatureAvecBesoin;
	}

	/**
	 * Méthode permettant de rechercher les créatures qui ont un besoin selon l'indicateur
	 * @param liste contenant l'ensemble des creatures qui ont un besoin
	 * @param besoin que l'on souhaite vérifier (faim, sommeil ou sante)
	 * @param indicateurFunction La fonction permettant de recuperer la valeur de l'indicateur sélectionné
	 * @param seuil pour savoir si une creature est en mauvais etat
	 */
	private void ajouterCreaturesAvecBesoin(HashSet<Creature> liste, String besoin, Function<Creature, Integer> indicateurFunction, int seuil) {
	    for (Map.Entry<Integer, Creature> entry : listeCreatures.entrySet()) {
	        if (indicateurFunction.apply(entry.getValue()) <= seuil) {
	            liste.add(entry.getValue());
	        }
	    }
	}
	
	
	/**
	 * Methode permettant de recuperer les creatures qui ne sont plus en vie
	 * et de les supprimer de l'enclos
	 * 
	 * @return l'ensemble des creatures qui sont mortes
	 * @throws Exception si problème lors de la recherche des créatures mortes
	 */
	public Set<Creature> creaturesMortes() throws Exception {
		Set<Creature> liste = new HashSet<>();
	    // Utilisation d'un itérateur pour éviter les modifications concurrentes
	    Iterator<Map.Entry<Integer, Creature>> iterator = listeCreatures.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry<Integer, Creature> entry = iterator.next();
	        if (!entry.getValue().isVivant()) {
	        	liste.add(entry.getValue());
	            iterator.remove(); // Suppression après la boucle
	        }
	    }
	    return liste;
	}
	
	
	/**
	 * Methode pour ajouter une creature dans l'enclos
	 * 
	 * @param creature	la creature a ajouter
	 * @throws Exception si l'enclos est plein ou si il y a deja une autre espèce
	 * 
	 */
	public void ajouterCreature (Creature creature) throws Exception {
		// si 1er animal
		if (nbCreatures==0)
			nomEspece = creature.getNomEspece();
		// Verification qu'il reste de la place, et que meme espece
		if (nbCreatures < CONSTANTES.NB_CREATURE_PAR_ENCLOS_MAX && nomEspece==creature.getNomEspece()) {
			nbCreatures++;
			listeCreatures.put(nbCreatures, creature);
		}
		else 
			throw new Exception ("Ajout impossible dans "+nom+" : enclos plein ou si une autre espece est presente");
	}
	
	
	/**
	 * Methode pour supprimer une creature dans l'enclos
	 * 
	 * @param creature	la creature a supprimer
	 * @throws Exception si la creature n'est pas trouve
	 * 
	 */
	public void supprimerCreature (Creature creature) throws Exception {
		// Vérification de la présence de la creature
        if (listeCreatures.containsValue(creature)) {
        	listeCreatures.remove(trouverCleParCreature(creature));
        	nbCreatures--;
        	// si enclos vide
        	if(nbCreatures==0)
        		nomEspece = null;
        	else
        		reorganiserCles();
		}
		else 
			throw new Exception ("Creature introuvable dans "+nom);
	}
	
	
	/**
	 * Methode pour nourrir les creatures
	 * 
	 * @throws Exception si il y a une ou plusieurs creatures mortes qui ne peuvent pas manger
	 */
	public void nourrirCreatures () throws Exception {
		for (Creature creature : listeCreatures.values()) {
			creature.manger(CONSTANTES.MAX_INDICATEUR);
		}
	}
	
	
	/**
	 * Methode pour soigner les creatures d'un enclos
	 * @throws Exception si une ou plusieurs créatures sont mortes
	 */
	public void soignerCreatures() throws Exception {
		for (Creature creature : listeCreatures.values()) {
			creature.soigner();
		}
	}
	
	
	/**
	 * Methode pour entrenir l'enclos
	 * 
	 * @throws Exception si l'enclos n'est pas sale ou s'il est vide
	 * 
	 */
	public void entretenirEnclos () throws Exception {
		// Verification enclos sale
		if (degreProprete != Enum_DegrePropreteEnclos.bon) {
			// Verification enclos vide
			if (nbCreatures == 0) {
				// Nettoyage
				degreProprete = Enum_DegrePropreteEnclos.bon;
			}
			else
				throw new Exception ("Il y a des creatures dans "+nom+", impossible de le nettoyer");
		}
		else 
			throw new Exception ("Enclos "+nom+" n'a pas besoin d etre nettoye");
	}
	
	
	/**
	 * Methode permettant de recuperer une creature selon sa cle
	 * dans la liste des creatures de l'enclos
	 * @param creature	L'instance de la creature recherche
	 * @return	La cle de la creature trouve, sinon -1
	 */
	public int trouverCleParCreature(Creature creature) {
        for (Map.Entry<Integer, Creature> entry : listeCreatures.entrySet()) {
            if (entry.getValue().equals(creature)) {
                return entry.getKey(); // Clé trouvée
            }
        }
        return -1; // Aucune clé trouvée pour la créature spécifiée
    }
	
	
	/**
	 * Methode permettant de reorganiser les cles de la liste des
	 * creatures de l'enclos
	 * Tri des creatures selon leur age (ordre croissant)
	 */
	public void reorganiserCles() {
		// Copier les créatures dans une liste
        List<Creature> creaturesList = new ArrayList<>(listeCreatures.values());
        // Trier la liste par âge
        creaturesList.sort(Comparator.comparingInt(Creature::getAge));
        // Remettre les créatures triées dans la TreeMap
        TreeMap<Integer, Creature> nouvelleMap = new TreeMap<>();
        int nouvelleCle = 1;
        for (Creature creature : creaturesList) {
            nouvelleMap.put(nouvelleCle, creature);
            nouvelleCle++;
        }
        // Mettre à jour la listeCreatures et nbCreatures
        listeCreatures = nouvelleMap;
        nbCreatures = nouvelleMap.size();
	}
	
	
	/**
	 * Methode permettant de degrader la proprete de l'enclos
	 */
	public void degradationDegreProprete() {
		if (degreProprete == Enum_DegrePropreteEnclos.bon)
			degreProprete = Enum_DegrePropreteEnclos.correct;
		else if (degreProprete == Enum_DegrePropreteEnclos.correct)
			degreProprete = Enum_DegrePropreteEnclos.mauvais;
	}
	
	
	/**
     * Méthode pour sélectionner aléatoirement une créature en fonction du sexe.
     * 
     * @param sexe Le sexe de la créature souhaitée
     * @return La créature sélectionnée aléatoirement
	 * @throws Exception s'il y a un problème lors de la recherche de créature
     */
    public Creature selectionnerCreatureAleatoireParSexe(Enum_Sexe sexe) throws Exception {
        // Créer une liste pour stocker les créatures correspondant au sexe spécifié
        List<Creature> creaturesDuSexe = new ArrayList<>();
        // Filtrer les créatures par sexe
        for (Creature creature : listeCreatures.values()) {
            if (creature.getSexe() == sexe) {
                creaturesDuSexe.add(creature);
            }
        }
        // Vérifier s'il y a des créatures du sexe spécifié
        Creature creat = null;
        if (!creaturesDuSexe.isEmpty()) {
            // Sélectionner aléatoirement une créature
        	while(creat==null) {
        		Random random = new Random();
                int indexAleatoire = random.nextInt(creaturesDuSexe.size());
                creat = creaturesDuSexe.get(indexAleatoire);
        	}
        	return creat;
        } 
        else 
            throw new Exception ("Pas de creature  "+sexe+" dans l'enclos "+nom);
    }
    
    
    /**
     * Methode permettant de concevoir un enfant selon le type de creature
     * 
     * @param femelle La mère de l'enfant qui va être créé
     * @param male Le père de l'enfant qui va être créé
     * @return un entier correspondant au type de la creature (1 pour Vivipare, 2 pour Ovipare)
     * @throws Exception s'il y a un problème lors de la conception de l'enfant
     */
    public int concevoirEnfant(Creature femelle, Creature male) throws Exception {
    	if (femelle.isVivant() && femelle instanceof Vivipare) {
    		((Vivipare)femelle).concevoirUnEnfant((Vivipare)male, femelle.getDureeGestation());
    		return 1;
    	}
    	else if (femelle.isVivant() && femelle instanceof Ovipare) {
    		return 2;
    	}
    	return -1;
    }


    /**
     * Réveille les créatures endormies dans l'enclos. 
     * Les créatures mortes sont identifiées et ne se reveillent pas
     *
     * @throws Exception Si des créatures ne peuvent pas se réveiller, généralement
     *                   en raison de leur état (vivant ou mort)
     */
	public void reveillerCreatures() throws Exception {
		List<String> creaturesMortes = new ArrayList<>();
	    for (Creature e : listeCreatures.values()) {
	        if (e.isVivant() && e.isEnTrainDeDormir()) {
	            e.seReveiller();
	        } else if (!e.isVivant()) {
	            creaturesMortes.add(e.getPrenom());
	        }
	    }
	    if (!creaturesMortes.isEmpty()) {
	        String message = "Les créatures suivantes ne se réveilleront jamais, car elles sont mortes : " 
	        		+ String.join("\n - ", creaturesMortes);
	        throw new Exception(message+"\n");
	    }
	}


	/**
	 * Fait dormir les créatures dans l'enclos. L
	 * Les insomniaques et les créatures mortes sont identifiés
	 *
	 * @throws Exception Si des créatures ne peuvent pas dormir( mortes ou pas fatigues)
	 */
	public void faireDormirCreatures() throws Exception {
	    List<String> creaturesInsomniaques = new ArrayList<>();
	    List<String> creaturesMortes = new ArrayList<>();
	    for (Creature e : listeCreatures.values()) {
	        if (e.isVivant()) {
	            if (e.getIndicateurSommeil() < CONSTANTES.MAX_INDICATEUR) {
	                e.dormir();
	            } else {
	                creaturesInsomniaques.add(e.getPrenom());
	            }
	        } else {
	            creaturesMortes.add(e.getPrenom());
	        }
	    }
	    if (!creaturesInsomniaques.isEmpty() || !creaturesMortes.isEmpty()) {
	        StringBuilder message = new StringBuilder();
	        if (!creaturesInsomniaques.isEmpty()) {
	            message.append("\nInsomniaques : \n").append(String.join("\n", creaturesInsomniaques));
	        }
	        if (!creaturesMortes.isEmpty()) {
	            message.append("\nMortes : \n").append(String.join("\n", creaturesMortes));
	        }
	        throw new Exception(message.toString());
	    }
	}
    
	
	/**
	 * Methode pour degrader l'etat de l'ensemble des creatures de l'enclos
	 * @throws Exception si l'une des créatures est morte dans l'enclos
	 */
	public void degradationEtatCreatures() throws Exception {
		for (Creature c : listeCreatures.values()) {
			c.perdreNourriture();
			c.perdreSommeil();
			c.perdreSante();
		}
	}


	/**
	 * Récupère la créature dominante dans l'enclos en se basant sur leur force
	 *
	 * @return La créature dominante ou null si l'enclos est vide
	 * @throws Exception Si une erreur survient lors de la recherche de la créature dominante
	 */
	public Creature getCreatureDominante() throws Exception {
		Creature creatureDominante = null;
		if (listeCreatures.entrySet().isEmpty()) {
			return null;
		}
		for (Map.Entry<Integer, Creature> entry : listeCreatures.entrySet()) {
			// si l'enclos est vide
			Creature currentCreature = entry.getValue();
			// Si aucune créature dominante n'est définie ou si la force de la créature actuelle est plus grande
			if (creatureDominante == null || currentCreature.getForce() > creatureDominante.getForce()) {
				creatureDominante = currentCreature;
			}
		}

		return creatureDominante;
	}

	
	/**
	 * Récupère l'ambiance dominante dans l'enclos en se basant sur l'agressivité des créatures
	 *
	 * @return L'ambiance dominante ou null si l'enclos est vide
	 */
	public Enum_Aggressivite getAmbiance() {
		// on cherche l'ambiance qui apparait le plus dans la liste de creature
		Map<Enum_Aggressivite, Integer> ambianceOccurrences = new TreeMap<>();

		// Parcourir les valeurs de la TreeMap et compter les occurrences d'ambiance
		for (Creature creature : listeCreatures.values()) {
			Enum_Aggressivite ambiance = creature.getAgressivite();
			ambianceOccurrences.put(ambiance, ambianceOccurrences.getOrDefault(ambiance, 0) + 1);
		}
		// Trouver l'ambiance avec le plus d'occurrences
		Enum_Aggressivite mostFrequentAmbiance = null;
		int maxOccurrences = 0;

		for (Map.Entry<Enum_Aggressivite, Integer> entry : ambianceOccurrences.entrySet()) {
			if (entry.getValue() > maxOccurrences) {
				mostFrequentAmbiance = entry.getKey();
				maxOccurrences = entry.getValue();
			}
		}

		return mostFrequentAmbiance;
	}
	
	
	/**
	 * Calcule la valeur moyenne du bonheur de toutes les créatures dans l'enclos
	 *
	 * @return La valeur moyenne du bonheur des créatures ou 0 si l'enclos est vide
	 */
	public int getBonheurMoyen(){ // todo Factoriser getter Moyen
		int valBonheurMoyen = 0;
		for (Creature creature : listeCreatures.values()){
			valBonheurMoyen += creature.getBonheur();
		}
		if (!listeCreatures.isEmpty()) return valBonheurMoyen/listeCreatures.size();
		return 0;
	}

	
	/**
	 * Calcule la valeur moyenne de l'âge de toutes les créatures dans l'enclos
	 *
	 * @return La valeur moyenne de l'âge des créatures ou 0 si l'enclos est vide
	 */
	public int getAgeMoyen() {
		int valAgeMoyen = 0;
		for (Creature creature : listeCreatures.values()){
			valAgeMoyen += creature.getAge();
		}
		if (!listeCreatures.isEmpty()) return valAgeMoyen/listeCreatures.size();
		return 0 ;
	}
	
	
	/**
	 * Calcule la valeur moyenne de l'indicateur de faim de toutes les créatures dans l'enclos
	 *
	 * @return La valeur moyenne de l'indicateur de faim des créatures ou 0 si l'enclos est vide
	 */
	public int getFaimMoyen() {
		int valFaimMoyen = 0;
		for (Creature creature : listeCreatures.values()){
			valFaimMoyen += creature.getIndicateurFaim();
		}
		if (!listeCreatures.isEmpty()) return valFaimMoyen/listeCreatures.size();
		return 0 ;
	}

	
	/**
	 * Calcule la valeur moyenne de l'indicateur de sommeil de toutes les créatures dans l'enclos
	 *
	 * @return La valeur moyenne de l'indicateur de sommeil des créatures ou 0 si l'enclos est vide
	 */
	public int getSommeilMoyen() {
		int valSommeilMoyen = 0;
		for (Creature creature : listeCreatures.values()){
			valSommeilMoyen += creature.getIndicateurSommeil();
		}
		if (!listeCreatures.isEmpty()) return valSommeilMoyen/listeCreatures.size();
		return 0 ;
	}

	
	/**
	 * Calcule la valeur moyenne de l'indicateur de santé de toutes les créatures dans l'enclos
	 *
	 * @return La valeur moyenne de l'indicateur de santé des créatures ou 0 si l'enclos est vide
	 */
	public int getSanteMoyen() {
		int valSanteMoyen = 0;
		for (Creature creature : listeCreatures.values()){
			valSanteMoyen += creature.getIndicateurSante();
		}
		if (!listeCreatures.isEmpty()) return valSanteMoyen/listeCreatures.size();
		return 0 ;
	}

	
	/**
	 * Obtient le niveau de propreté de l'enclos transforme en un entier
	 *
	 * @return 2 pour "bon", 1 pour "correct", 0 pour "mauvais".
	 */
	public int getDegrePropreteNumber(){
		if (degreProprete == Enum_DegrePropreteEnclos.bon) {
			return 2;
		}
		if (degreProprete == Enum_DegrePropreteEnclos.correct) {
			return 1;
		}
		if (degreProprete == Enum_DegrePropreteEnclos.mauvais) {
			return 0;
		}
		return 0;
	}
	
	
	/**
	 * Vérifie si au moins la moitié des créatures dans l'enclos ont faim
	 * Utilisation pour la gestion automatique
	 *
	 * @return true si au moins la moitié des créatures ont faim, sinon false
	 */
	public boolean isCreatureOntFaim() {
		int compteur = 0;
		for (Creature c : listeCreatures.values()) {
			if (c.getIndicateurFaim() < CONSTANTES.VALEUR_INDICATEUR_MAUVAIS) {
				compteur++;
			}
		}
        return compteur >= listeCreatures.size() / 2;
	}

	
	/**
	 * Vérifie si au moins la moitié des créatures dans l'enclos ont besoin de sommeil
	 * Utilisation pour la gestion automatique
	 *
	 * @return true si au moins la moitié des créatures ont besoin de sommeil, sinon false
	 */
	public boolean isCreatureOntSommeil() {
		int compteur = 0;
		for (Creature c : listeCreatures.values()) {
			if (c.getIndicateurSommeil() < CONSTANTES.VALEUR_INDICATEUR_MAUVAIS) {
				compteur++;
			}
		}
        return compteur >= listeCreatures.size() / 2;
	}

	
	/**
	 * Vérifie si au moins la moitié des créatures dans l'enclos sont en mauvaise sante
	 * Utilisation pour la gestion automatique
	 *
	 * @return true si au moins la moitié des créatures sont malades, sinon false
	 */
	public boolean isCreatureSontMalade() {
		int compteur = 0;
		for (Creature c : listeCreatures.values()) {
			if (c.getIndicateurSante() < CONSTANTES.VALEUR_INDICATEUR_MAUVAIS) {
				compteur++;
			}
		}
        return compteur >= listeCreatures.size() / 2;
	}

	
	/**
	 * Vérifie si l'enclos est en mauvais état de propreté
	 *
	 * @return true si l'enclos est en mauvais état de propreté, sinon false
	 */
	public boolean isEnclosMauvaisEtat () {
        return degreProprete == Enum_DegrePropreteEnclos.mauvais;
	}

	
	/**
	 * Vérifie si au moins la moitié des créatures dans l'enclos sont en train de dormir
	 * Utilisation pour la gestion automatique
	 *
	 * @return true si au moins la moitié des créatures sont en train de dormir, sinon false
	 */
	public boolean isCreaturesDorment () {
		int compteur = 0;
		for (Creature c : listeCreatures.values()) {
			if (c.isEnTrainDeDormir()) {
				compteur++;
			}
		}
        return compteur >= listeCreatures.size() / 2;
	}

}