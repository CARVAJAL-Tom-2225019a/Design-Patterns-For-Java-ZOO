package controllerApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import applicationRun.Run;
import base.Creature;
import base.Enclos;
import base.Ovipare;
import controllerTemps.Enum_ActionsPossibles;
import controllerTemps.GestionnaireTemps;
import creaturesImplemente.Lycanthrope;
import creaturesImplemente.Oeuf;
import enclosImplemente.Aquarium;
import enclosImplemente.EnclosClassique;
import enclosImplemente.EnclosLycanthrope;
import enclosImplemente.Voliere;
import interfaces.CreatureMarine;
import interfaces.CreatureTerrestre;
import interfaces.CreatureVolante;
import maitreZoo.MaitreZoo;
import meuteLycanthrope.ColonieLycanthrope;
import references.CONSTANTES;
import references.Enum_Sexe;
import viewApplication.VueGlobale;
import viewApplication.VueUtilisateur;
import zoo.ZooFantastique;

public class ControllerActions {
	private static ControllerUserInterface controlUser = new ControllerUserInterface();
	private static ControllerGestionAuto controllerGestionAuto = new ControllerGestionAuto();
	// Instance de Vues
	private static VueGlobale vueGlobale;
    private static VueUtilisateur vueUtilisateur;
    // Instance du gestionnaire du zoo (Singleton)
    private MaitreZoo maitreZoo = MaitreZoo.getInstance();
    private ColonieLycanthrope colonie = ColonieLycanthrope.getInstance();
    // Instance du zoo fantastique (Singleton)
    private ZooFantastique zoo = ZooFantastique.getInstance();
    // Instance du temps
    private GestionnaireTemps temps = GestionnaireTemps.getInstance();
    
    
    public ControllerActions() {
    	vueGlobale = new VueGlobale();
    	vueUtilisateur = new VueUtilisateur();
    }

	/**
     * Methodes appellé par le switch case
     */
    protected void casExaminerEnclos() {
    	Enclos enclos;
    	try {
    		vueGlobale.afficher("\n ---- Examiner un enclos ("+Enum_ActionsPossibles.EXAMINER_ENCLOS.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.recupererEnclosParNom();
        	}
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        	}	
            vueGlobale.afficher(maitreZoo.examinerEnclos(enclos));
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }
    
    protected void casNettoyerEnclos() {
    	Enclos enclos;
    	try {
    		vueGlobale.afficher("\n ---- Nettoyer un enclos ("+Enum_ActionsPossibles.NETTOYER_ENCLOS.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.recupererEnclosParNom();
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        	}
            maitreZoo.nettoyerEnclos(enclos);
            vueGlobale.afficher("Nettoyage fait dans " + enclos+"\n");
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}	
    }
    
    protected void casNourrirEnclos() {
    	Enclos enclos;
    	try {
    		vueGlobale.afficher("\n ---- Nourrir les creatures d'un enclos ("+Enum_ActionsPossibles.NOURRIR_CREATURES.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.recupererEnclosParNom();
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        	}
            maitreZoo.nourrirCreaturesEnclos(enclos);
            vueGlobale.afficher("Les creatures ont ete nourries dans "+enclos.getNom());
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }
    
    protected void casTransfererCreature() {
    	Enclos enclos;
    	Enclos enclosDest;
    	Creature creature;
    	try {
    		vueGlobale.afficher("\n ---- Transferer une creature ("+Enum_ActionsPossibles.TRANSFERER_CREATURE.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.recupererEnclosParNom();
        		creature = controlUser.selectionCreatureDansEnclos(enclos);
        		enclosDest = controlUser.recupererEnclosParNom();
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        		enclosDest = controllerGestionAuto.recuperationEnclosAleatoire();
        		creature = enclos.selectionnerCreatureAleatoireParSexe(Creature.sexeAleatoire());
        	}
        	maitreZoo.transfererCreature(creature, enclos, enclosDest);
        	vueGlobale.afficher("Transfert de "+enclos.getNom()+" a "+enclosDest.getNom()+
        			"pour \n"+creature);
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }
    
    protected void casCreerEnclos() {
        String nomEnclos;
        String typeEnclos = "Classique";
        boolean valeurOk = false;

        try {
            vueGlobale.afficher("\n ---- Creation d'un enclos ("+Enum_ActionsPossibles.CREER_ENCLOS.getDureeTotale()+") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                nomEnclos = vueUtilisateur.demandeUtilisateur("Nom enclos : ");
                while (!valeurOk) {
                    typeEnclos = vueUtilisateur.demandeUtilisateur("Type enclos (Classique, Aquatique, Voliere, Lycanthrope) : ");
                    if ("Classique".equals(typeEnclos) || "Aquatique".equals(typeEnclos) || "Voliere".equals(typeEnclos) || "Lycanthrope".equals(typeEnclos)) {
                        valeurOk = true;
                    } 
                    else {
                        vueGlobale.afficher("Veuillez entrer Classique, Voliere, Aquatique ou Lycanthrope");
                    }
                }
            }
            // GESTION AUTOMATIQUE
            else {
                int i = zoo.getListeEnclos().size() + 1;
                nomEnclos = "Enclos" + i;
            }

            // creation enclos
            if ("Classique".equals(typeEnclos)) {
                Enclos e = new EnclosClassique(nomEnclos, CONSTANTES.TAILLE_ENCLOS);
                zoo.addEnclos(e);
            } 
            else if ("Voliere".equals(typeEnclos)) {
                Voliere e = new Voliere(nomEnclos, CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
                zoo.addEnclos(e);
            } 
            else if ("Aquatique".equals(typeEnclos)) {
                Aquarium e = new Aquarium(nomEnclos, CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
                zoo.addEnclos(e);
            }
            else if ("Lycanthrope".equals(typeEnclos)) {
            	EnclosLycanthrope e = new EnclosLycanthrope(nomEnclos, CONSTANTES.TAILLE_ENCLOS);
            	zoo.addEnclos(e);
            }
            else {
                throw new Exception("Erreur type enclos lors de la creation");
            }
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }

    
    protected void casTransfererEnclos() {
    	Enclos enclos;
    	Enclos enclosDest;
    	String nomEnclos;
    	try {
    		vueGlobale.afficher("\n ---- Transferer un enclos ("+Enum_ActionsPossibles.TRANSFERER_ENCLOS.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.recupererEnclosParNom();
        		enclosDest = controlUser.recupererEnclosParNom();
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        		int i = zoo.getListeEnclos().size() + 1;
        		nomEnclos="Enclos"+i;
        		Enclos e = new EnclosClassique (nomEnclos, CONSTANTES.TAILLE_ENCLOS);
        		zoo.addEnclos(e);
        		enclosDest = e;
        	}
        	 // Créez une copie de la liste des créatures
    	    Set<Creature> creaturesACopier = new HashSet<>(enclos.getListeCreatures().values());
    	    // Transférez les créatures de la copie vers enclos2
    	    for (Creature c : creaturesACopier) {
    	        maitreZoo.transfererCreature(c, enclos, enclosDest);
    	    }
        	vueGlobale.afficher("Transfert de "+enclos.getNom()+" a "+enclosDest.getNom()+
        			" effectue\n");
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }
    
    protected void casConcevoirEnfant() {
    	Enclos enclos;
    	Creature femelle;
    	Creature male;
    	try {
    		vueGlobale.afficher("\n ---- Concevoir un enfant ("+Enum_ActionsPossibles.CONCEVOIR_ENFANT.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.recupererEnclosParNom();
            	vueGlobale.afficher(enclos.toString() + "\n\nVeuillez selectionner une femelle puis un male\n");
            	// Femelle
            	femelle = controlUser.selectionCreatureDansEnclos(enclos);
            	// Male
            	male = controlUser.selectionCreatureDansEnclos(enclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        		femelle = enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Femelle);
        		male = enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Male);
        	}
        	//Conception
        	int naitre = enclos.concevoirEnfant(femelle, male);
        	if (naitre==1) {
        		zoo.addFemelleEnceinte(femelle);
        		vueGlobale.afficher("Enfant en cours de type "+femelle.getNomEspece());
        	}
        	else if (naitre==2) {
        		ArrayList<Oeuf> oeufs = ((Ovipare)femelle).pondreOeuf();
        		for (Oeuf o : oeufs) {
					zoo.addOeuf(o);
        		vueGlobale.afficher("Oeuf(s) pondu de type "+o.getEspece());
				}
        	}
        	else if (naitre==-1)
        		vueGlobale.afficher("Impossible de concevoir");
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }
    
    protected void casEnclosEnMouvement() {
    	Enclos enclos;
    	boolean peutCourrir = false;
    	boolean peutNager = false;
    	boolean peutVoler = false;
    	boolean ActionEffectue = false;
    	String choix = "";
    	String chaine = "";
    	try {
    		vueGlobale.afficher("\n ---- Seance de sport pour un enclos ("+Enum_ActionsPossibles.METTRE_ENCLOS_EN_MOUVEMENT.getDureeTotale()+") ---- ");
    		
    		// Choix enclos
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.recupererEnclosParNom();
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        	}
        	
    		// Proposition des actions selon type creature
        	if (enclos.getListeCreatures().get(1) instanceof CreatureMarine && enclos instanceof Aquarium) {
        		vueGlobale.afficher("Les creature peuvent nager");
        		peutNager = true;
        	}
        	if (enclos.getListeCreatures().get(1) instanceof CreatureTerrestre) {
        		vueGlobale.afficher("Les creature peuvent courir");
        		peutCourrir = true;
        	}
        	if (enclos.getListeCreatures().get(1) instanceof CreatureVolante && enclos instanceof Voliere) {
        		vueGlobale.afficher("Les creature peuvent voler");
        		peutVoler = true;
        	}
        	
        	// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		while (!"nager".equals(choix) && !"courir".equals(choix) && !"voler".equals(choix))
        			choix = vueUtilisateur.demandeUtilisateur("Veuillez entrer votre choix (nager, courir, voler): ");
        	}
        	// GESTION AUTOMATIQUE
        	else {
    			if (peutNager) {
    				choix = "nager";
    				ActionEffectue=true;
    			}
    			if (peutCourrir && !ActionEffectue) {
    				choix = "courir";
    				ActionEffectue=true;
    			}
    			if (peutVoler && !ActionEffectue) {
    				choix = "voler";
    				ActionEffectue=true;
    			}
    			if (!ActionEffectue)
    				throw new Exception ("Impossible de faire du sport pour eux... en esperant qu'ils ne grossisent pas trop du coup\n");
        	}
    		// Mouvement de l'enclos
        	if ("nager".equals(choix) && peutNager == true) {
        		vueGlobale.afficher("ALLEZ ! On nage les "+enclos.getNomEspece()+"s. ALLEZ ! \n");
        		for (Creature c : enclos.getListeCreatures().values()) {
        			Thread.sleep(1000);
        			if (!c.isEnTrainDeDormir() && c.isVivant())
        				chaine = ((CreatureMarine)c).nager();
        			vueGlobale.afficher(chaine);
        		}
        	}
        	else if ("courir".equals(choix) && peutCourrir == true) {
        		vueGlobale.afficher("ALLEZ ! On court les "+enclos.getNomEspece()+"s. ALLEZ !\n");
        		for (Creature c : enclos.getListeCreatures().values()) {
        			Thread.sleep(1000);
        			if (!c.isEnTrainDeDormir() && c.isVivant())
        				chaine = ((CreatureTerrestre)c).courrir();
        			vueGlobale.afficher(chaine);
        		}
        	}
        	else if ("voler".equals(choix) && peutVoler == true) {
        		vueGlobale.afficher("ALLEZ ! On vole les "+enclos.getNomEspece()+"s. ALLEZ ! \n");
        		for (Creature c : enclos.getListeCreatures().values()) {
        			Thread.sleep(1000);
        			if (!c.isEnTrainDeDormir() && c.isVivant())
        				chaine = ((CreatureVolante)c).voler();
        			vueGlobale.afficher(chaine);
        		}
        	}
        	else
        		throw new Exception ("Assurez vous que l'enclos soit adapte et que les"
        				+ " animaux ont la bonne categorie pour faire cette action");
        	vueGlobale.afficher("\nFelicitation mes petites creatures !");
        	Thread.sleep(1000);
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }
    
    
    protected void casChanterEnclos() {
    	Enclos enclos;
    	try {
    		vueGlobale.afficher("\n ---- Concert prive pour un enclos ("+Enum_ActionsPossibles.FAIRE_CHANTER_ENCLOS.getDureeTotale()+") ---- ");
    		// Choix enclos
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.recupererEnclosParNom();
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        	}
        	vueGlobale.afficher("\nAllez les "+enclos.getNomEspece()+"s.\n"
        			+ "On chante l'un apres l'autre !\n");
        	Thread.sleep(1000);
        	for (Creature c : enclos.getListeCreatures().values()) {
        		if (!c.isEnTrainDeDormir() && c.isVivant())
        			vueGlobale.afficher(c.faireBruit());
        		Thread.sleep(1000);
        	}
        	vueGlobale.afficher("\nBon.. Il y a encore du travail, mais c'est un debut...\n");
        	Thread.sleep(1000);
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }
    
    protected void casDormirEnclos() {
    	Enclos enclos;
    	try {
	    	vueGlobale.afficher("\n ---- Dodo Party pour un enclos ("+Enum_ActionsPossibles.DORMIR_ENCLOS.getDureeTotale()+") ---- ");
			// Choix enclos
			// GESTION MANUEL
	    	if (Run.utilisateurControle) {
	    		enclos = controlUser.recupererEnclosParNom();
	    	}
	    	// GESTION AUTOMATIQUE
	    	else {
	    		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
	    	}
	    	enclos.faireDormirCreatures();
	    	vueGlobale.afficher("Les creatures se sont endormis dans "+enclos.getNom()+"\n ATTENTION DE NE PAS LES REVEILLER...\n");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }
    
    protected void casReveillerEnclos() {
    	Enclos enclos;
    	try {
	    	vueGlobale.afficher("\n ---- Dodo Party pour un enclos ("+Enum_ActionsPossibles.REVEILLER_ENCLOS.getDureeTotale()+") ---- ");
			// Choix enclos
			// GESTION MANUEL
	    	if (Run.utilisateurControle) {
	    		enclos = controlUser.recupererEnclosParNom();
	    	}
	    	// GESTION AUTOMATIQUE
	    	else {
	    		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
	    	}
	    	enclos.reveillerCreatures();
	    	vueGlobale.afficher("Les creatures se reveillent dans "+enclos.getNom()+"\n J'espere qu'elles sont de bonne humeur...\n");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
    }

	public void casVoirLycanthropes() {
		try {
	    	vueGlobale.afficher("\n ---- Voir les lycanthropes ("+Enum_ActionsPossibles.VOIR_LOUPS.getDureeTotale()+") ---- ");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
	    	vueGlobale.afficher(colonie.voirLycanthropes());
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
	}

	public void casSaisonAmourLycanthropes() {
		try {
	    	vueGlobale.afficher("\n ---- Verification saison amour pour lycanthropes ("+Enum_ActionsPossibles.SAISON_AMOUR_LOUPS.getDureeTotale()+") ---- ");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
	    	Set<Lycanthrope> listeFemelleEnceinte =  colonie.verificationSaisonAmour(temps.getDateActuelle());
	    	if (!listeFemelleEnceinte.isEmpty()) {
	    		for (Lycanthrope l : listeFemelleEnceinte) {
	    			zoo.addFemelleEnceinte(l);
		    		vueGlobale.afficher(l.getPrenom()+" est enceinte\n");
	    		}
	    	}
	    	else
	    		vueGlobale.afficher("Pas de bebe pour le moment... \n(saison des amours est de mai a juin)");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
	}

	public void casVoirMeutes() {
		try {
	    	vueGlobale.afficher("\n ---- Voir les meutes ("+Enum_ActionsPossibles.VOIR_MEUTES.getDureeTotale()+") ---- ");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
	    	vueGlobale.afficher(colonie.voirMeutes());
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	}
    	catch (Exception e) {
    		vueGlobale.afficher(e.getMessage());
    	}
	}
}
