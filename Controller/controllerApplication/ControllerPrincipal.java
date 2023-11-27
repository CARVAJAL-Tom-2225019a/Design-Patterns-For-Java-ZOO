package controllerApplication;

import applicationRun.Run;
import base.*;
import controllerTemps.GestionnaireTemps;
import creaturesImplemente.*;
import enclosImplemente.*;
import references.*;
import viewApplication.*;
import zoo.ZooFantastique;

/**
 * Classe representant le controleur principal de l'application
 */
public class ControllerPrincipal {
	private int numPourNom = 0;
	VueGlobale vueGlobale = new VueGlobale();
	VueUtilisateur vueUtilisateur = new VueUtilisateur();
    // Instance du zoo fantastique (Singleton)
    private static ZooFantastique zoo = ZooFantastique.getInstance();
    private GestionnaireTemps temps = GestionnaireTemps.getInstance();
    
    /**
     * Méthode pour passer à la nouvelle année dans le zoo
     */
    public String nouvelleAnnee() {
    	String chaine = "";
    	try {
            // Pour chaque enclos
            for (Enclos enclos : zoo.getListeEnclos()) {
            	if (!enclos.getListeCreatures().isEmpty()) {
            		// Pour chaque créature
                    for (Creature creature : enclos.getListeCreatures().values()) {
                        // Passage d'une année
                        creature.vieillir();
                    }
                    //Passage annee pour lycanthrope
                    if (enclos instanceof EnclosLycanthrope) 
                    	((EnclosLycanthrope) enclos).passageAnneLycanthrope();
                    // Ajoute les informations sur les créatures mortes à la chaîne
                    chaine+=enclos.creaturesMortes();
            	}
            }
            return chaine;
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    	return "erreur";
    }
    
    
    /**
     * Methodes permettant de verifier les enfants qui doivent naitre
     */
    public void verificationNaissances () {
    	try {
    		vueGlobale.afficher("VERIFICATION DES NAISSANCES : ");
        	VerificationOeufs();
        	verificationEnfants();
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}	
    }
    public void VerificationOeufs() {
    	try {
    		for (Oeuf o : zoo.getlLsteOeufs()) {
        		if (o.getDureeIncubationRestante() == 0) {
        			Creature enfant = o.eclore();
        			vueGlobale.afficher("Naissance "+enfant.getNomEspece());
        			rangerCreature(enfant);
        			zoo.removeOeuf(o);
        		}
        	}
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    	
    }
    public void verificationEnfants() throws Exception {
    	int nbJour;
    	try {
    		for (Creature c : zoo.getListeFemelleEnceinte()) {
        		nbJour = ((Vivipare)c).decrementerNombreJourRestantAvantNaissance();
        		if (nbJour == 0) {
        			Creature enfant = ((Vivipare)c).mettreBas();
        			vueGlobale.afficher("Naissance "+enfant.getNomEspece());
        			rangerCreature(enfant);
        			zoo.addFemelleEnceinte(c);
        		}
        	}
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }
    
    
    
    /** 
     * Methode permettant de mettre la nouvelle creature dans enclos
     */
    public void rangerCreature (Creature c) throws Exception {
    	String nom;
    	try {
    		//S'il y a de la place dans un enclos
        	for (Enclos e : zoo.getListeEnclos()) {
        		if (e.getNbCreatures()<e.getNbMaxCreatures() && e.getNomEspece()==c.getNomEspece()) {
        			e.ajouterCreature(c);
        			e.reorganiserCles();
        			return;
        		}
        	}
        	// Si aucun enclos pret a acceuiller, nouvel enclos
        	if (Run.utilisateurControle)
        		nom = vueUtilisateur.demandeUtilisateur("Nom pour nouvel enclos :");
        	else {
        		numPourNom++;
        		nom = "Enclos"+numPourNom;
        	}
        	Enclos newEnclos = new EnclosClassique (nom, CONSTANTES.TAILLE_ENCLOS);
        	zoo.addEnclos(newEnclos);
        	newEnclos.ajouterCreature(c);
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    	
    }
    
    
    /**
     * Methode permettant d'obtenir les elements importants au debut du jeu
     */
    public String getInformationsDebut(){
    	return "\n INFORMATION : \n- La duree de vie d'une creature est de " + CONSTANTES.MAX_AGE+" "
    			+ "ans. \n - Nous somme le "+temps.getDateActuelle()+" \n- Chaque annee, l'etat des enclos et des creatures "
    					+ "se degradent \n- Plus un enclos est en mauvais etat, plus les creatures iront mal"
    					+ " \n - Une creature qui va trop mal risque de mourrir \n - Pour nettoyer un enclos, ce dernier doit etre vide"
    					+ "\n\n Bon courage :)\n\n";
    	
    }

    
	/**
     * Méthode pour passer la main à l'utilisateur
     */
    public void passerLaMainUtilisateur() {
    	try {
    		ControllerUserInterface menuUtilisateur = new ControllerUserInterface();
            menuUtilisateur.runUserMenu();
    	}
    	catch (Exception e) {
			vueGlobale.afficher(e.getMessage());
		}
    }
    
    /**
     * Méthode pour lancer la gestion automatique
     */
    public void gestionAuto() {
    	try {
    		ControllerGestionAuto menuAuto = new ControllerGestionAuto();
        	menuAuto.run();
    	}
    	catch (Exception e) {
			vueGlobale.afficher(e.getMessage());
		}
    }

}
