package controllerApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import base.Creature;
import base.Ovipare;
import creaturesImplemente.*;
import enclosImplemente.Aquarium;
import enclosImplemente.Enclos;
import enclosImplemente.EnclosClassique;
import enclosImplemente.Voliere;
import interfaces.CreatureMarine;
import interfaces.CreatureTerrestre;
import interfaces.CreatureVolante;
import main.Run;
import maitreZoo.MaitreZoo;
import references.CONSTANTES;
import references.Enum_Sexe;
import viewApplication.*;
import zoo.ZooFantastique;

/**
 * Classe representant le controleur du zoo
 * Permet d'effectuer toutes les actions relatives a vie du zoo
 */
public class ControllerZoo {
	private static ControllerPrincipal controlPrincipal = new ControllerPrincipal();
	private static ControllerUserInterface controlUser = new ControllerUserInterface();
	private static ControllerGestionAuto ControllerGestionAuto = new ControllerGestionAuto();
	// Instance de Vues
	private static VueGlobale VueGlobale;
    private static VueUtilisateur VueUtilisateur;
    private static VueAutomatique VueAutomatique;
    // Instance du gestionnaire du zoo (Singleton)
    private MaitreZoo maitreZoo = MaitreZoo.getInstance();
    // Instance du zoo fantastique (Singleton)
    private ZooFantastique zoo = ZooFantastique.getInstance();
    private int nbAction ;
    private int annee;
    
    
    /**
     * Constrcuteur
     */
    public ControllerZoo() {
    	VueGlobale = new VueGlobale();
    	VueUtilisateur = new VueUtilisateur();
    	VueAutomatique = new VueAutomatique();
        new ControllerPrincipal();
    }
    
    
    /**
     * Getters
     */
    public int getNbActionMax() {
        return CONSTANTES.NB_ACTION_MAX_USER;
    }
    public int getNbAction() {
        return nbAction;
    }
    public int getAnnee() {
        return annee;
    }
    public int getActionRestante() {
        return CONSTANTES.NB_ACTION_MAX_USER - nbAction;
    }

    
    /**
     * Initialise les variables du jeu.
     *
     */
    public void init() {
        // Initialisation des variables
        nbAction = 0;
        annee = 1;
        try {
        	// Affiche le message de bienvenue
            if (Run.UtilisateurControle) // gestion manuel
            	maitreZoo = VueUtilisateur.Bienvenue();
            else
            	maitreZoo = VueAutomatique.Bienvenue();
        }
        catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }

    
    /**
     * Effectue l'action correspondant au choix de l'utilisateur.
     *
     * @param choix Le choix de l'utilisateur.
     * @return true si l'action a été effectuée avec succès, false sinon.
     */
    public boolean effectuerAction(int choix) {
        boolean retour = false;
        try {
        	switch (choix) {
            // Ne pas effectuer d'action
            case 0:
            	VueGlobale.Afficher("\n ---- Pas d'action effectue ---- ");
            	retour= true;
            	break;
            // Voir les enclos
            case 1:
            	VueGlobale.Afficher("\n ---- Voir les enclos existants ---- ");
            	VueGlobale.Afficher(zoo.AfficherEnsembleZoo());
            	retour= true;
            	break;
            // Voir nombre total de créatures
            case 2:
            	VueGlobale.Afficher("\n ---- Voir le nombre de creatures totales ---- ");
            	VueGlobale.Afficher("Il y a " + zoo.getNbCreaturesTotales() + " creatures.");
            	retour= true;
            	break;
            // Creer enclos
            case 3 : 
            	CasCreerEnclos();
            	retour= true;
            	break;
            	
            // Examiner un enclos
            case 4:
            	CasExaminerEnclos();
                retour= true;
                break;
            // Nettoyer un enclos
            case 5:
            	CasNettoyerEnclos();
                retour= true;
                break;
            // Nourrir les créatures d'un enclos
            case 6:
            	CasNourrirEnclos();
                retour= true;
                break;
            // Transférer une créature
            case 7:
            	CasTransfererCreature();
            	retour= true;
            	break;
           // Transferer un enclos
            case 8 : 
            	CasTransfererEnclos();
            	retour = true;
            	break;
           // Concevoir un enfant
            case 9 :
            	CasConcevoirEnfant();
            	retour= true;
            	break;
            // Voir liste bebes en cosntruction
            case 10 :
            	VueGlobale.Afficher("\n ---- Voir les enfants qui vont bientot naitre ---- ");
            	VueGlobale.Afficher(zoo.AfficherFemellesEnceinte());
            	VueGlobale.Afficher(zoo.AfficherOeufs());
            	retour= true;
            	break;
            // Mettre un enclos en mouvement
            case 11 :
            	CasEnclosEnMouvement();
            	retour= true;
            	break;
            // Faire chanter un enclos
            case 12 : 
            	CasChanterEnclos();
            	retour= true;
            	break;
            // Exit
            case 99:
                retour= false;
                break;
            default:
            	VueGlobale.Afficher("Choix invalide");
            	retour= true;
            }
        }
        catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
        return retour;
    }

    
    /**
     * Passe à la nouvelle année si le nombre d'actions atteint le maximum.
     */
    public void PassageAnnee() {
    	String temp = null;
    	try {
    		VueGlobale.PassageAnnee();
            controlPrincipal.VerificationNaissances();
            zoo.ModificationEtatAleatoire();
            // Tri des clés
            for (Enclos e : zoo.GetListeEnclos())
            	e.reorganiserCles();
            VueGlobale.Afficher("\n ====== NOUVELLE ANNEE ====== \n");
            //Affichage des informations préoccupantes
            VueGlobale.Afficher(zoo.AfficherEnclosMauvaisEtat());
            for (Enclos e : zoo.GetListeEnclos())
            	temp = e.voirCreaturesAyantUnBesoin();
            if (temp != null)
            	 VueGlobale.Afficher(temp);
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }

    
    /**
     * Exécute une année du jeu en incrémentant le nombre d'actions.
     *
     */
    public void runYear(){
    	try {
    		nbAction++;
            if (nbAction == CONSTANTES.NB_ACTION_MAX_USER) {
                annee++;
                nbAction = 0;	
                PassageAnnee();
                //Voir liste enclors apres chaque annee si gestion auto
                if (!Run.UtilisateurControle) {
                	Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
	            	effectuerAction(1);
                }
            }
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
        
    }
    
    
    /**
     * Methodes appellé par le switch case
     */
    private void CasExaminerEnclos() {
    	Enclos enclos;
    	String nomEnclos;
    	try {
    		VueGlobale.Afficher("\n ---- Examiner un enclos ---- ");
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
        	}
        	else {
        		enclos = ControllerGestionAuto.RecuperationEnclosAleatoire();
        	}	
            VueGlobale.Afficher(maitreZoo.ExaminerEnclos(enclos));
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
    private void CasNettoyerEnclos() {
    	Enclos enclos;
    	String nomEnclos;
    	try {
    		VueGlobale.Afficher("\n ---- Nettoyer un enclos ---- ");
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = ControllerGestionAuto.RecuperationEnclosAleatoire();
        	}
            maitreZoo.NettoyerEnclos(enclos);
            VueGlobale.Afficher("Nettoyage fait dans " + enclos+"\n");
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}	
    }
    
    private void CasNourrirEnclos() {
    	Enclos enclos;
    	String nomEnclos;
    	try {
    		VueGlobale.Afficher("\n ---- Nourrir les creatures d'un enclos ---- ");
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom de l'enclos : ");
                enclos = zoo.trouverEnclosParNom(nomEnclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = ControllerGestionAuto.RecuperationEnclosAleatoire();
        		nomEnclos = enclos.getNom();
        	}
            maitreZoo.NourrirCreaturesEnclos(enclos);
            VueGlobale.Afficher("Les creatures ont ete nourries dans "+nomEnclos);
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
    private void CasTransfererCreature() {
    	Enclos enclos;
    	Enclos enclosDest;
    	Creature creature;
    	String nomEnclos;
    	try {
    		VueGlobale.Afficher("\n ---- Transferer une creature ---- ");
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom enclos source : ");
            	enclos = zoo.trouverEnclosParNom(nomEnclos);
        		creature = controlUser.SelectionCreatureDansEnclos(enclos);
            	nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom enclos destination : ");
            	enclosDest = zoo.trouverEnclosParNom(nomEnclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = ControllerGestionAuto.RecuperationEnclosAleatoire();
        		enclosDest = ControllerGestionAuto.RecuperationEnclosAleatoire();
        		creature = enclos.selectionnerCreatureAleatoireParSexe(Creature.SexeAleatoire());
        	}
        	maitreZoo.TransfererCreature(creature, enclos, enclosDest);
        	VueGlobale.Afficher("Transfert de "+enclos.getNom()+" a "+enclosDest.getNom()+
        			"pour \n"+creature);
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
    private void CasCreerEnclos() {
        String nomEnclos;
        String typeEnclos = "Classique";
        boolean valeurOk = false;

        try {
            VueGlobale.Afficher("\n ---- Creation d'un enclos ---- ");
            // GESTION MANUEL
            if (Run.UtilisateurControle) {
                nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom enclos : ");
                while (!valeurOk) {
                    typeEnclos = VueUtilisateur.DemandeUtilisateur("Type enclos (Classique, Aquatique, Voliere) : ");
                    if ("Classique".equals(typeEnclos) || "Aquatique".equals(typeEnclos) || "Voliere".equals(typeEnclos)) {
                        valeurOk = true;
                    } 
                    else {
                        VueGlobale.Afficher("Veuillez entrer Classique, Voliere ou Aquatique");
                    }
                }
            }
            // GESTION AUTOMATIQUE
            else {
                int i = zoo.GetListeEnclos().size() + 1;
                nomEnclos = "Enclos" + i;
            }

            // creation enclos
            if ("Classique".equals(typeEnclos)) {
                Enclos e = new EnclosClassique(nomEnclos, CONSTANTES.TAILLE_ENCLOS);
                zoo.AddEnclos(e);
            } 
            else if ("Voliere".equals(typeEnclos)) {
                Voliere e = new Voliere(nomEnclos, CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
                zoo.AddEnclos(e);
            } 
            else if ("Aquatique".equals(typeEnclos)) {
                Aquarium e = new Aquarium(nomEnclos, CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
                zoo.AddEnclos(e);
            } 
            else {
                throw new Exception("Erreur type enclos lors de la creation");
            }
        } catch (Exception e) {
            VueGlobale.Afficher(e.getMessage());
        }
    }

    
    private void CasTransfererEnclos() {
    	Enclos enclos;
    	Enclos enclosDest;
    	String nomEnclos;
    	try {
    		VueGlobale.Afficher("\n ---- Transferer un enclos ---- ");
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom enclos source : ");
            	enclos = zoo.trouverEnclosParNom(nomEnclos);
            	nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom enclos destination : ");
            	enclosDest = zoo.trouverEnclosParNom(nomEnclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = ControllerGestionAuto.RecuperationEnclosAleatoire();
        		int i = zoo.GetListeEnclos().size() + 1;
        		nomEnclos="Enclos"+i;
        		Enclos e = new EnclosClassique (nomEnclos, CONSTANTES.TAILLE_ENCLOS);
        		zoo.AddEnclos(e);
        		enclosDest = e;
        	}
        	 // Créez une copie de la liste des créatures
    	    Set<Creature> creaturesACopier = new HashSet<>(enclos.getListeCreatures().values());
    	    // Transférez les créatures de la copie vers enclos2
    	    for (Creature c : creaturesACopier) {
    	        maitreZoo.TransfererCreature(c, enclos, enclosDest);
    	    }
        	VueGlobale.Afficher("Transfert de "+enclos.getNom()+" a "+enclosDest.getNom()+
        			" effectue\n");
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
    private void CasConcevoirEnfant() {
    	Enclos enclos;
    	Creature femelle;
    	Creature male;
    	String nomEnclos;
    	try {
    		VueGlobale.Afficher("\n ---- Concevoir un enfant ---- ");
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom enclos : ");
            	enclos = zoo.trouverEnclosParNom(nomEnclos);
            	VueGlobale.Afficher(enclos.toString() + "\n\nVeuillez selectionner une femelle puis un male\n");
            	// Femelle
            	femelle = controlUser.SelectionCreatureDansEnclos(enclos);
            	// Male
            	male = controlUser.SelectionCreatureDansEnclos(enclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = ControllerGestionAuto.RecuperationEnclosAleatoire();
        		femelle = enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Femelle);
        		male = enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Male);
        	}
        	//Conception
        	int naitre = enclos.ConcevoirEnfant(femelle, male);
        	if (naitre==1) {
        		zoo.AddFemelleEnceinte(femelle);
        		VueGlobale.Afficher("Enfant en cours de type "+femelle.getNomEspece());
        	}
        	else if (naitre==2) {
        		ArrayList<Oeuf> oeufs = ((Ovipare)femelle).PondreOeuf();
        		for (Oeuf o : oeufs) {
					zoo.AddOeuf(o);
        		VueGlobale.Afficher("Oeuf pondu de type "+o.getEspece());
				}
        	}
        	else if (naitre==-1)
        		VueGlobale.Afficher("Impossible de concevoir");
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
    public void CasEnclosEnMouvement() {
    	String nomEnclos;
    	Enclos enclos;
    	boolean peutCourrir = false;
    	boolean peutNager = false;
    	boolean peutVoler = false;
    	boolean ActionEffectue = false;
    	String choix = "";
    	String chaine = "";
    	try {
    		VueGlobale.Afficher("\n ---- Seance de sport pour un enclos ---- ");
    		
    		// Choix enclos
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom enclos : ");
            	enclos = zoo.trouverEnclosParNom(nomEnclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = ControllerGestionAuto.RecuperationEnclosAleatoire();
        	}
        	
    		// Proposition des actions selon type creature
        	if (enclos.getListeCreatures().get(1) instanceof CreatureMarine && enclos instanceof Aquarium) {
        		VueGlobale.Afficher("Les creature peuvent nager");
        		peutNager = true;
        	}
        	if (enclos.getListeCreatures().get(1) instanceof CreatureTerrestre) {
        		VueGlobale.Afficher("Les creature peuvent courir");
        		peutCourrir = true;
        	}
        	if (enclos.getListeCreatures().get(1) instanceof CreatureVolante && enclos instanceof Voliere) {
        		VueGlobale.Afficher("Les creature peuvent voler");
        		peutVoler = true;
        	}
        	
        	// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		while (!"nager".equals(choix) && !"courir".equals(choix) && !"voler".equals(choix))
        			choix = VueUtilisateur.DemandeUtilisateur("Veuillez entrer votre choix (nager, courir, voler): ");
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
    				throw new Exception ("Probleme choix automatique sport");
        	}
    		// Mouvement de l'enclos
        	if ("nager".equals(choix) && peutNager == true) {
        		VueGlobale.Afficher("ALLEZ ! On nage les "+enclos.getNomEspece()+"s. ALLEZ ! \n");
        		for (Creature c : enclos.getListeCreatures().values()) {
        			Thread.sleep(1000);
        			chaine = ((CreatureMarine)c).Nager();
        			VueGlobale.Afficher(chaine);
        		}
        	}
        	else if ("courir".equals(choix) && peutCourrir == true) {
        		VueGlobale.Afficher("ALLEZ ! On court les "+enclos.getNomEspece()+"s. ALLEZ !\n");
        		for (Creature c : enclos.getListeCreatures().values()) {
        			Thread.sleep(1000);
        			chaine = ((CreatureTerrestre)c).Courrir();
        			VueGlobale.Afficher(chaine);
        		}
        	}
        	else if ("voler".equals(choix) && peutVoler == true) {
        		VueGlobale.Afficher("ALLEZ ! On vole les "+enclos.getNomEspece()+"s. ALLEZ ! \n");
        		for (Creature c : enclos.getListeCreatures().values()) {
        			Thread.sleep(1000);
        			chaine = ((CreatureVolante)c).Voler();
        			VueGlobale.Afficher(chaine);
        		}
        	}
        	else
        		throw new Exception ("Assurez vous que l'enclos soit adapte et que les"
        				+ " animaux ont la bonne categorie pour faire cette action");
        	VueGlobale.Afficher("\nFelicitation mes petites creatures !");
        	Thread.sleep(1000);
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
    
    public void CasChanterEnclos() {
    	String nomEnclos;
    	Enclos enclos;
    	try {
    		VueGlobale.Afficher("\n ---- Concert prive pour un enclos ---- ");
    		// Choix enclos
    		// GESTION MANUEL
        	if (Run.UtilisateurControle) {
        		nomEnclos = VueUtilisateur.DemandeUtilisateur("Nom enclos : ");
            	enclos = zoo.trouverEnclosParNom(nomEnclos);
        	}
        	// GESTION AUTOMATIQUE
        	else {
        		enclos = ControllerGestionAuto.RecuperationEnclosAleatoire();
        	}
        	VueGlobale.Afficher("\nAllez les "+enclos.getNomEspece()+"s.\n"
        			+ "On chante l'un apres l'autre !\n");
        	Thread.sleep(1000);
        	for (Creature c : enclos.getListeCreatures().values()) {
        		VueGlobale.Afficher(c.FaireBruit());
        		Thread.sleep(1000);
        	}
        	VueGlobale.Afficher("\nBon.. Il y a encore du travail, mais c'est un debut...\n");
        	Thread.sleep(1000);
    	}
    	catch (Exception e) {
    		VueGlobale.Afficher(e.getMessage());
    	}
    }
    
}
