package controllerApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
import main.Run;
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
    		vueGlobale.Afficher("\n ---- Examiner un enclos ("+Enum_ActionsPossibles.EXAMINER_ENCLOS.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.RecupererEnclosParNom();
        	}
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        	}	
            vueGlobale.Afficher(maitreZoo.examinerEnclos(enclos));
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    }
    
    protected void casNettoyerEnclos() {
    	Enclos enclos;
    	try {
    		vueGlobale.Afficher("\n ---- Nettoyer un enclos ("+Enum_ActionsPossibles.NETTOYER_ENCLOS.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.RecupererEnclosParNom();
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        	}
            maitreZoo.nettoyerEnclos(enclos);
            vueGlobale.Afficher("Nettoyage fait dans " + enclos+"\n");
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}	
    }
    
    protected void casNourrirEnclos() {
    	Enclos enclos;
    	try {
    		vueGlobale.Afficher("\n ---- Nourrir les creatures d'un enclos ("+Enum_ActionsPossibles.NOURRIR_CREATURES.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.RecupererEnclosParNom();
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        	}
            maitreZoo.nourrirCreaturesEnclos(enclos);
            vueGlobale.Afficher("Les creatures ont ete nourries dans "+enclos.getNom());
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    }
    
    protected void casTransfererCreature() {
    	Enclos enclos;
    	Enclos enclosDest;
    	Creature creature;
    	try {
    		vueGlobale.Afficher("\n ---- Transferer une creature ("+Enum_ActionsPossibles.TRANSFERER_CREATURE.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.RecupererEnclosParNom();
        		creature = controlUser.selectionCreatureDansEnclos(enclos);
        		enclosDest = controlUser.RecupererEnclosParNom();
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        		enclosDest = controllerGestionAuto.recuperationEnclosAleatoire();
        		creature = enclos.selectionnerCreatureAleatoireParSexe(Creature.sexeAleatoire());
        	}
        	maitreZoo.transfererCreature(creature, enclos, enclosDest);
        	vueGlobale.Afficher("Transfert de "+enclos.getNom()+" a "+enclosDest.getNom()+
        			"pour \n"+creature);
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    }
    
    protected void casCreerEnclos() {
        String nomEnclos;
        String typeEnclos = "Classique";
        boolean valeurOk = false;

        try {
            vueGlobale.Afficher("\n ---- Creation d'un enclos ("+Enum_ActionsPossibles.CREER_ENCLOS.getDureeTotale()+") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                nomEnclos = vueUtilisateur.DemandeUtilisateur("Nom enclos : ");
                while (!valeurOk) {
                    typeEnclos = vueUtilisateur.DemandeUtilisateur("Type enclos (Classique, Aquatique, Voliere, Lycanthrope) : ");
                    if ("Classique".equals(typeEnclos) || "Aquatique".equals(typeEnclos) || "Voliere".equals(typeEnclos) || "Lycanthrope".equals(typeEnclos)) {
                        valeurOk = true;
                    } 
                    else {
                        vueGlobale.Afficher("Veuillez entrer Classique, Voliere, Aquatique ou Lycanthrope");
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
            vueGlobale.Afficher(e.getMessage());
        }
    }

    
    protected void casTransfererEnclos() {
    	Enclos enclos;
    	Enclos enclosDest;
    	String nomEnclos;
    	try {
    		vueGlobale.Afficher("\n ---- Transferer un enclos ("+Enum_ActionsPossibles.TRANSFERER_ENCLOS.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.RecupererEnclosParNom();
        		enclosDest = controlUser.RecupererEnclosParNom();
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
        	vueGlobale.Afficher("Transfert de "+enclos.getNom()+" a "+enclosDest.getNom()+
        			" effectue\n");
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    }
    
    protected void casConcevoirEnfant() {
    	Enclos enclos;
    	Creature femelle;
    	Creature male;
    	try {
    		vueGlobale.Afficher("\n ---- Concevoir un enfant ("+Enum_ActionsPossibles.CONCEVOIR_ENFANT.getDureeTotale()+") ---- ");
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.RecupererEnclosParNom();
            	vueGlobale.Afficher(enclos.toString() + "\n\nVeuillez selectionner une femelle puis un male\n");
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
        		vueGlobale.Afficher("Enfant en cours de type "+femelle.getNomEspece());
        	}
        	else if (naitre==2) {
        		ArrayList<Oeuf> oeufs = ((Ovipare)femelle).pondreOeuf();
        		for (Oeuf o : oeufs) {
					zoo.addOeuf(o);
        		vueGlobale.Afficher("Oeuf(s) pondu de type "+o.getEspece());
				}
        	}
        	else if (naitre==-1)
        		vueGlobale.Afficher("Impossible de concevoir");
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
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
    		vueGlobale.Afficher("\n ---- Seance de sport pour un enclos ("+Enum_ActionsPossibles.METTRE_ENCLOS_EN_MOUVEMENT.getDureeTotale()+") ---- ");
    		
    		// Choix enclos
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.RecupererEnclosParNom();
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        	}
        	
    		// Proposition des actions selon type creature
        	if (enclos.getListeCreatures().get(1) instanceof CreatureMarine && enclos instanceof Aquarium) {
        		vueGlobale.Afficher("Les creature peuvent nager");
        		peutNager = true;
        	}
        	if (enclos.getListeCreatures().get(1) instanceof CreatureTerrestre) {
        		vueGlobale.Afficher("Les creature peuvent courir");
        		peutCourrir = true;
        	}
        	if (enclos.getListeCreatures().get(1) instanceof CreatureVolante && enclos instanceof Voliere) {
        		vueGlobale.Afficher("Les creature peuvent voler");
        		peutVoler = true;
        	}
        	
        	// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		while (!"nager".equals(choix) && !"courir".equals(choix) && !"voler".equals(choix))
        			choix = vueUtilisateur.DemandeUtilisateur("Veuillez entrer votre choix (nager, courir, voler): ");
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
        		vueGlobale.Afficher("ALLEZ ! On nage les "+enclos.getNomEspece()+"s. ALLEZ ! \n");
        		for (Creature c : enclos.getListeCreatures().values()) {
        			Thread.sleep(1000);
        			if (!c.isEnTrainDeDormir() && c.isVivant())
        				chaine = ((CreatureMarine)c).nager();
        			vueGlobale.Afficher(chaine);
        		}
        	}
        	else if ("courir".equals(choix) && peutCourrir == true) {
        		vueGlobale.Afficher("ALLEZ ! On court les "+enclos.getNomEspece()+"s. ALLEZ !\n");
        		for (Creature c : enclos.getListeCreatures().values()) {
        			Thread.sleep(1000);
        			if (!c.isEnTrainDeDormir() && c.isVivant())
        				chaine = ((CreatureTerrestre)c).courrir();
        			vueGlobale.Afficher(chaine);
        		}
        	}
        	else if ("voler".equals(choix) && peutVoler == true) {
        		vueGlobale.Afficher("ALLEZ ! On vole les "+enclos.getNomEspece()+"s. ALLEZ ! \n");
        		for (Creature c : enclos.getListeCreatures().values()) {
        			Thread.sleep(1000);
        			if (!c.isEnTrainDeDormir() && c.isVivant())
        				chaine = ((CreatureVolante)c).voler();
        			vueGlobale.Afficher(chaine);
        		}
        	}
        	else
        		throw new Exception ("Assurez vous que l'enclos soit adapte et que les"
        				+ " animaux ont la bonne categorie pour faire cette action");
        	vueGlobale.Afficher("\nFelicitation mes petites creatures !");
        	Thread.sleep(1000);
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    }
    
    
    protected void casChanterEnclos() {
    	Enclos enclos;
    	try {
    		vueGlobale.Afficher("\n ---- Concert prive pour un enclos ("+Enum_ActionsPossibles.FAIRE_CHANTER_ENCLOS.getDureeTotale()+") ---- ");
    		// Choix enclos
    		// GESTION MANUEL
        	if (Run.utilisateurControle) {
        		enclos = controlUser.RecupererEnclosParNom();
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
        	}
        	vueGlobale.Afficher("\nAllez les "+enclos.getNomEspece()+"s.\n"
        			+ "On chante l'un apres l'autre !\n");
        	Thread.sleep(1000);
        	for (Creature c : enclos.getListeCreatures().values()) {
        		if (!c.isEnTrainDeDormir() && c.isVivant())
        			vueGlobale.Afficher(c.faireBruit());
        		Thread.sleep(1000);
        	}
        	vueGlobale.Afficher("\nBon.. Il y a encore du travail, mais c'est un debut...\n");
        	Thread.sleep(1000);
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    }
    
    protected void casDormirEnclos() {
    	Enclos enclos;
    	try {
	    	vueGlobale.Afficher("\n ---- Dodo Party pour un enclos ("+Enum_ActionsPossibles.DORMIR_ENCLOS.getDureeTotale()+") ---- ");
			// Choix enclos
			// GESTION MANUEL
	    	if (Run.utilisateurControle) {
	    		enclos = controlUser.RecupererEnclosParNom();
	    	}
	    	// GESTION AUTOMATIQUE
	    	else {
	    		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
	    	}
	    	enclos.faireDormirCreatures();
	    	vueGlobale.Afficher("Les creatures se sont endormis dans "+enclos.getNom()+"\n ATTENTION DE NE PAS LES REVEILLER...\n");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    }
    
    protected void casReveillerEnclos() {
    	Enclos enclos;
    	try {
	    	vueGlobale.Afficher("\n ---- Dodo Party pour un enclos ("+Enum_ActionsPossibles.REVEILLER_ENCLOS.getDureeTotale()+") ---- ");
			// Choix enclos
			// GESTION MANUEL
	    	if (Run.utilisateurControle) {
	    		enclos = controlUser.RecupererEnclosParNom();
	    	}
	    	// GESTION AUTOMATIQUE
	    	else {
	    		enclos = controllerGestionAuto.recuperationEnclosAleatoire();
	    	}
	    	enclos.reveillerCreatures();
	    	vueGlobale.Afficher("Les creatures se reveillent dans "+enclos.getNom()+"\n J'espere qu'elles sont de bonne humeur...\n");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
    }

	public void casVoirLycanthropes() {
		try {
	    	vueGlobale.Afficher("\n ---- Voir les lycanthropes ("+Enum_ActionsPossibles.VOIR_LOUPS.getDureeTotale()+") ---- ");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
	    	vueGlobale.Afficher(colonie.voirLycanthropes());
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
	}

	public void casSaisonAmourLycanthropes() {
		try {
	    	vueGlobale.Afficher("\n ---- Verification saison amour pour lycanthropes ("+Enum_ActionsPossibles.SAISON_AMOUR_LOUPS.getDureeTotale()+") ---- ");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
	    	Set<Lycanthrope> listeFemelleEnceinte =  colonie.verificationSaisonAmour(temps.getDateActuelle());
	    	if (!listeFemelleEnceinte.isEmpty()) {
	    		for (Lycanthrope l : listeFemelleEnceinte) {
	    			zoo.addFemelleEnceinte(l);
		    		vueGlobale.Afficher(l.getPrenom()+" est enceinte\n");
	    		}
	    	}
	    	else
	    		vueGlobale.Afficher("Pas de bebe pour le moment...");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
	}

	public void casVoirMeutes() {
		try {
	    	vueGlobale.Afficher("\n ---- Voir les meutes ("+Enum_ActionsPossibles.VOIR_MEUTES.getDureeTotale()+") ---- ");
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP/2);
	    	vueGlobale.Afficher(colonie.voirMeutes());
	    	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
    	}
    	catch (Exception e) {
    		vueGlobale.Afficher(e.getMessage());
    	}
	}
}
