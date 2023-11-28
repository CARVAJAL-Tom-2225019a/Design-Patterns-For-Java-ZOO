package base;

import java.util.*;

import references.*;

/**
 * Cette classe correspond a un enclos classique
 *
 */
public class Enclos {
	
	private Enum_Especes nomEspece;
	
	private String nom;
	private double superficie;
	private int nbCreatures;
	private TreeMap<Integer, Creature> listeCreatures;
	private Enum_DegrePropreteEnclos degreProprete;
	
	
	/**
	 * CONSTRUCTEUR
	 * @param nom
	 * @param superficie
	 * @param nbMaxCreatures
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
	 * Getters
	 */
	public Enum_Especes getNomEspece() {
		return nomEspece;
	}
	public String getNom() {
		return nom;
	}
	public double getSuperficie() {
		return superficie;
	}
	public int getNbMaxCreatures() {
		return CONSTANTES.NB_CREATURE_PAR_ENCLOS_MAX;
	}
	public int getNbCreatures() {
		return nbCreatures;
	}
	public TreeMap<Integer, Creature> getListeCreatures() {
		return listeCreatures;
	}
	public Enum_DegrePropreteEnclos getDegreProprete() {
		return degreProprete;
	}
	
	
	/**
	 * Methode permettant d'afficher les caracteristiques de l'enclos 
	 * et les creatures qu'il contient
	 * 
	 * @return la chaine de caractère contenant les informations
	 */
	public String toString() {
		String chaine = "Enclos "+nom+" de superficie "+superficie+" m^2 pouvant contenir au "
				+ "plus "+CONSTANTES.NB_CREATURE_PAR_ENCLOS_MAX+" creatures.\n Il y a actuellement "+nbCreatures+" creatures.\n"
				+ "Degre de proprete : "+degreProprete+"\n";
		for (Creature creature : listeCreatures.values()) {
			// si la creature est vivante
			if (creature.isVivant())
				chaine+="Index : "+ trouverCleParCreature(creature)+"\n";
				chaine+= creature.toString();
		}
		return chaine;
	}
	
	
	/**
	 * Methode pour avoir le nom de l'enclos et son etat
	 */
	public String voirInfoEnclos() {
		return " -Enclos "+getNom()+" avec "+getNbCreatures()
        +" creatures.\n          Degre proprete : "+getDegreProprete()+"\n";
	}
	
	
	/**
	 * Methode permettant de recuperer les creatures qui ont besoin de quelquechose
	 */
	public String voirCreaturesAyantUnBesoin() {
		String chaine = "";
		boolean isValue = false;
		String temp = voirCreaturesMauvaiseSante();
		if (temp != null) {
			chaine+= temp+"\n";
			isValue = true;
		}
		temp = voirCreaturesSommeil();
		if (temp != null) {
			chaine+= temp+"\n";
			isValue = true;
		}
		temp = voirCreaturesFaim();
		if (temp != null) {
			chaine+= temp+"\n";
			isValue = true;
		}
		if (isValue)
			return chaine+"\n";
		else
			return null;
	}	
	
	
	/**
	 * Methode permettant de recuperer les creatures qui ont faim
	 */
	private String voirCreaturesFaim() {
		boolean isValue = false;
		String chaine = "Les creatures qui ont faim dans "+nom+" :\n";
		for (Map.Entry<Integer, Creature> entry : listeCreatures.entrySet()) {
			if (entry.getValue().getIndicateurFaim() < 5) {
				chaine += "  - index "+entry.getKey()+" = "+entry.getValue().getIndicateurFaim()+"/"+CONSTANTES.MAX_INDICATEUR+"\n";
				isValue = true;
			}
		}
		if (isValue)
			return chaine;
		else
			return null;
	}
	
	
	/**
	 * Methode permettant de recuperer les creatures qui ont sommeil
	 */
	private String voirCreaturesSommeil() {
		boolean isValue = false;
		String chaine = "Les creatures qui ont sommeil dans "+nom+" :\n";
		for (Map.Entry<Integer, Creature> entry : listeCreatures.entrySet()) {
			if (entry.getValue().getIndicateurSommeil() < 5) {
				chaine += "  - index "+entry.getKey()+" = "+entry.getValue().getIndicateurSommeil()+"/"+CONSTANTES.MAX_INDICATEUR+"\n";
				isValue = true;
			}
		}
		if (isValue)
			return chaine;
		else
			return null;
	}
	
	
	/**
	 * Methode permettant de recuperer les creatures qui sont en mauavaise sante
	 */
	private String voirCreaturesMauvaiseSante() {
		boolean isValue = false;
		String chaine = "Les creatures qui sont en mauvaise sante dans "+nom+" :\n";
		for (Map.Entry<Integer, Creature> entry : listeCreatures.entrySet()) {
			if (entry.getValue().getIndicateurSante() < 5) {
				chaine += "  - index "+entry.getKey()+" = "+entry.getValue().getIndicateurSante()+"/"+CONSTANTES.MAX_INDICATEUR+"\n";
				isValue = true;
			}
		}
		if (isValue)
			return chaine;
		else
			return null;
	}
	
	
	/**
	 * Methode permettant d'afficher les creatures qui ne sont plus en vie
	 * et de les supprimer de l'enclos
	 * 
	 * @return la chaine de caractère contenant les informations
	 * @throws Exception 
	 */
	public String creaturesMortes() throws Exception {
	    String chaine = "Les créatures mortes dans " + nom + " :\n";
	    // Utilisation d'un itérateur pour éviter les modifications concurrentes
	    Iterator<Map.Entry<Integer, Creature>> iterator = listeCreatures.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry<Integer, Creature> entry = iterator.next();
	        if (!entry.getValue().isVivant()) {
	            chaine+= entry.getValue().toString();
	            iterator.remove(); // Suppression après la boucle
	        }
	    }
	    return chaine.toString();
	}
	
	
	/**
	 * Methode pour ajouter une creature dans l'enclos
	 * 
	 * @param creature	la creature a ajouter
	 * @throws Exception si l'enclos est plein
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
			throw new Exception ("Ajout impossible si enclos plein ou si une autre espece est presente");
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
			throw new Exception ("Creature introuvable");
	}
	
	
	/**
	 * Methode pour nourrir les creatures
	 * 
	 */
	public void nourrirCreatures () throws Exception {
		for (Creature creature : listeCreatures.values()) {
			creature.manger(CONSTANTES.MAX_INDICATEUR);
		}
	}
	
	
	/**
	 * Methode pour soigner les creatures d'un enclos
	 * @throws Exception 
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
        Collections.sort(creaturesList, Comparator.comparingInt(Creature::getAge));
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
     */
    public Creature selectionnerCreatureAleatoireParSexe(Enum_Sexe sexe) {
        // Créer une liste pour stocker les créatures correspondant au sexe spécifié
        List<Creature> creaturesDuSexe = new ArrayList<>();
        // Filtrer les créatures par sexe
        for (Creature creature : listeCreatures.values()) {
            if (creature.getSexe() == sexe) {
                creaturesDuSexe.add(creature);
            }
        }
        // Vérifier s'il y a des créatures du sexe spécifié
        if (!creaturesDuSexe.isEmpty()) {
            // Sélectionner aléatoirement une créature
            Random random = new Random();
            int indexAleatoire = random.nextInt(creaturesDuSexe.size());
            return creaturesDuSexe.get(indexAleatoire);
        } else {
            // Aucune créature du sexe spécifié trouvée
            return null;
        }
    }
    
    
    /**
     * Methode permettant de concevoir un enfant selon le type de creature
     * @throws Exception 
     */
    public int concevoirEnfant(Creature femelle, Creature male) throws Exception {
    	if (femelle.isVivant() && femelle instanceof Vivipare) {
    		((Vivipare)femelle).concevoirUnEnfant((Vivipare)male, femelle.getDureeGestation());
    		return 1;
    	}
    	else if (femelle.isVivant() && femelle instanceof Ovipare) {
    		
    		// TODO : eclore
    		return 2;
    	}
    	return -1;
    }


	public void reveillerCreatures() throws Exception {
		for (Creature e : listeCreatures.values()) {
			if (e.isVivant() && e.isEnTrainDeDormir())
				e.seReveiller();
			else
				throw new Exception ("La creature "+e.getPrenom()+" ne se reveillera jamais... elle est morte");
		}
	}


	public void faireDormirCreatures() throws Exception {
		for (Creature e : listeCreatures.values()) {
			if (e.isVivant()) {
				if (e.getIndicateurSommeil()<CONSTANTES.MAX_INDICATEUR)
					e.dormir();
				else
					throw new Exception ("Creature "+e.getPrenom()+" pas fatigue et fait une insomnie");
			}
			else
				throw new Exception ("Creature "+e.getPrenom()+" dort a jamais... elle est morte");
		}
	}
    
	
	/**
	 * Methode pour degrader l'etat de l'ensemble des creatures de l'enclos
	 * @throws Exception 
	 */
	public void degradationEtatCreatures() throws Exception {
		for (Creature c : listeCreatures.values()) {
			c.perdreNourriture();
			c.perdreSommeil();
			c.perdreSante();
		}
	}


	public Creature getCreatureDominante() throws Exception {
		if (!listeCreatures.isEmpty()) {
			for (Map.Entry<Integer, Creature> entry : listeCreatures.entrySet()) {
				if (entry.getValue().getStatus() == Enum_RangDomination.ALPHA)
					return entry.getValue();
			}
		}
		else
			throw new Exception ("L'enclos est vide");
		return null;
	}

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
	public int getBonheurMoyen(){ // todo Factoriser getter Moyen
		int valBonheurMoyen = 0;
		for (Creature creature : listeCreatures.values()){
			valBonheurMoyen += creature.getBonheur();
		}
		if (!listeCreatures.isEmpty()) return valBonheurMoyen/listeCreatures.size();
		return 0;
	}

	public int getAgeMoyen() {
		int valAgeMoyen = 0;
		for (Creature creature : listeCreatures.values()){
			valAgeMoyen += creature.getAge();
		}
		if (!listeCreatures.isEmpty()) return valAgeMoyen/listeCreatures.size();
		return 0 ;
	}
	public int getFaimMoyen() {
		int valFaimMoyen = 0;
		for (Creature creature : listeCreatures.values()){
			valFaimMoyen += creature.getIndicateurFaim();
		}
		if (!listeCreatures.isEmpty()) return valFaimMoyen/listeCreatures.size();
		return 0 ;
	}

	public int getSommeilMoyen() {
		int valSommeilMoyen = 0;
		for (Creature creature : listeCreatures.values()){
			valSommeilMoyen += creature.getIndicateurSommeil();
		}
		if (!listeCreatures.isEmpty()) return valSommeilMoyen/listeCreatures.size();
		return 0 ;
	}

	public int getSanteMoyen() {
		int valSanteMoyen = 0;
		for (Creature creature : listeCreatures.values()){
			valSanteMoyen += creature.getIndicateurSante();
		}
		if (!listeCreatures.isEmpty()) return valSanteMoyen/listeCreatures.size();
		return 0 ;
	}

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
}