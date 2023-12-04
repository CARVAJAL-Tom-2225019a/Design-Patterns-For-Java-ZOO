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
 * Classe représentant le contrôleur principal de l'application
 */
public class ControllerPrincipal {
	private int numPourNom;
	VueGlobale vueGlobale;
	VueUtilisateur vueUtilisateur;
    // Instance du zoo fantastique (Singleton)
    private static ZooFantastique zoo = ZooFantastique.getInstance();
    private GestionnaireTemps temps = GestionnaireTemps.getInstance();
    
    
    /**
     * Constructeur
     */
    public ControllerPrincipal() {
    	numPourNom = 0;
    	vueGlobale = new VueGlobale();
    	vueUtilisateur = new VueUtilisateur();
    }
    
    /**
     * Méthode pour passer à la nouvelle année dans le zoo
     *
     */
    public void nouvelleAnnee() {
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
                    for (Creature creatMorte : enclos.creaturesMortes())
                    	vueGlobale.afficherCreature(creatMorte, -1);;
            	}
            }
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }
    
    
    /**
     * Méthodes permettant de vérifier les enfants qui doivent naître
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
    
    
    /**
     * Méthode pour vérifier les œufs et les faire éclore
     */
    public void VerificationOeufs() {
    	try {
    		for (Oeuf o : zoo.getlLsteOeufs()) {
        		if (o.getDureeIncubationRestante() == 0) {
        			Creature enfant = o.eclore();
        			vueGlobale.afficher("NAISSANCE OVIPARE");
        			vueGlobale.afficherCreature(enfant, -1);
        			rangerCreature(enfant);
        			zoo.removeOeuf(o);
        		}
        	}
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    	
    }
    
    
    /**
     * Méthode pour vérifier les femelles enceintes et faire naître les enfants
     *
     * @throws Exception En cas d'erreur lors de la vérification des enfants
     */
    public void verificationEnfants() throws Exception {
    	int nbJour;
    	try {
    		for (Creature c : zoo.getListeFemelleEnceinte()) {
        		nbJour = ((Vivipare)c).decrementerNombreJourRestantAvantNaissance();
        		if (nbJour == 0) {
        			Creature enfant = ((Vivipare)c).mettreBas();
        			vueGlobale.afficher("NAISSANCE VIVIPARE");
        			vueGlobale.afficherCreature(enfant, -1);
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
     * Méthode permettant de mettre la nouvelle créature dans un enclos
     *
     * @param c La créature à ranger dans un enclos
     * @throws Exception En cas d'erreur lors du rangement de la créature
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
     * Méthode permettant d'obtenir les éléments importants au début du jeu
     *
     * @return Une chaîne d'informations importantes au début du jeu
     */
    public String getInformationsDebut(){
    	return "\n INFORMATION : "
    			+ "\n- La duree de vie d'une creature est de " + CONSTANTES.MAX_AGE+" "+ "ans. "
    					+ "\n"
    					+ "\n - Nous somme le "+temps.getDateActuelle()
    					+ "\n - La duree de vie du zoo est de "+CONSTANTES.DUREE_VIE_ZOO
    					+ "\n"
    					+ "\n - Chaque annee, l'etat des enclos et des creatures se degradent "
    					+ "\n - Plus un enclos est en mauvais etat, plus les creatures iront mal"
    					+ "\n - Une creature qui va trop mal risque de mourrir "
    					+ "\n - Pour nettoyer un enclos, ce dernier doit etre vide"
    					+ "\n"
						+ "\n Pour profiter d'une expérience immersive, veuillez ACTIVER VOTRE SON"
						+ "\n et AGGRANDIR LE TERMINAL"		
						+ "\n"
    					+ "\n Bon courage :)\n\n";
    	
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
    		ControllerGestionAuto.run(true);
    	}
    	catch (Exception e) {
			vueGlobale.afficher(e.getMessage());
		}
    }

}
