package controllerApplication;

import applicationRun.Run;
import base.Creature;
import base.Enclos;
import base.Ovipare;
import controllerTemps.Enum_ActionsPossibles;
import controllerTemps.GestionnaireTemps;
import creaturesImplemente.*;
import enclosImplemente.Aquarium;
import enclosImplemente.EnclosClassique;
import enclosImplemente.EnclosLycanthrope;
import enclosImplemente.Voliere;
import interfaces.CreatureMarine;
import interfaces.CreatureTerrestre;
import interfaces.CreatureVolante;
import maitreZoo.MaitreZoo;
import meuteLycanthrope.ColonieLycanthrope;
import meuteLycanthrope.Meute;
import references.CONSTANTES;
import references.Enum_ActionHurlement;
import references.Enum_Sexe;
import viewApplication.Son;
import viewApplication.VueGlobale;
import viewApplication.VueUtilisateur;
import zoo.ZooFantastique;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * La classe Actions contient la liste des différentes actions possibles
 * dans le zoo fantastique
 */
public class Actions {
    private static final ControllerUserInterface controlUser = new ControllerUserInterface();
    private static final ControllerGestionAuto controllerGestionAuto = new ControllerGestionAuto();
    // Instance de Vues
    private static VueGlobale vueGlobale;
    private static VueUtilisateur vueUtilisateur;
    // Instance du gestionnaire du zoo (Singleton)
    private final MaitreZoo maitreZoo = MaitreZoo.getInstance();
    private final ColonieLycanthrope colonie = ColonieLycanthrope.getInstance();
    // Instance du zoo fantastique (Singleton)
    private final ZooFantastique zoo = ZooFantastique.getInstance();
    // Instance du temps
    private final GestionnaireTemps temps = GestionnaireTemps.getInstance();


    /**
     * Constructeur de la classe Actions
     */
    public Actions() {
        vueGlobale = new VueGlobale();
        vueUtilisateur = new VueUtilisateur();
    }


    /**
     * Méthode appelée pour examiner un enclos
     */
    protected void casExaminerEnclos() {
        Enclos enclos;
        try {
            vueGlobale.afficher("\n ---- Examiner un enclos (" + Enum_ActionsPossibles.EXAMINER_ENCLOS.getDureeTotale() + ") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                vueGlobale.afficher(zoo.voirNomsEnclos());
                enclos = controlUser.recupererEnclosParNom();
            } else {
                enclos = controllerGestionAuto.recuperationEnclosAleatoire();
            }
            vueGlobale.afficherEnclos(maitreZoo.examinerEnclos(enclos), true);

        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour nettoyer un enclos
     */
    protected void casNettoyerEnclos() {
        Enclos enclos;
        try {
            vueGlobale.afficher("\n ---- Nettoyer un enclos (" + Enum_ActionsPossibles.NETTOYER_ENCLOS.getDureeTotale() + ") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                vueGlobale.afficher(zoo.voirNomsEnclos());
                enclos = controlUser.recupererEnclosParNom();
            }
            // GESTION AUTOMATIQUE
            else {
                enclos = controllerGestionAuto.getFirstEnclosMauvaisEtat();
                if (enclos == null)
                    enclos = controllerGestionAuto.recuperationEnclosAleatoireVide();
            }
            maitreZoo.nettoyerEnclos(enclos);
            vueGlobale.afficher("Nettoyage fait dans " + enclos.getNom() + "\n");
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour nourrir un enclos
     */
    protected void casNourrirEnclos() {
        Enclos enclos;
        try {
            vueGlobale.afficher("\n ---- Nourrir les creatures d'un enclos ("+Enum_ActionsPossibles.NOURRIR_CREATURES.getDureeTotale()+") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                vueGlobale.afficher(zoo.voirNomsEnclosPasVide());
                enclos = controlUser.recupererEnclosParNom();
            }
            // GESTION AUTOMATIQUE
            else {
                enclos = controllerGestionAuto.getFirstEnclosCreatureFaim();
                if (enclos == null)
                    enclos = controllerGestionAuto.recuperationEnclosAleatoireNonVide();
            }
            maitreZoo.nourrirCreaturesEnclos(enclos);
            vueGlobale.afficher("Les creatures ont ete nourries dans " + enclos.getNom());
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour soigner un enclos
     */
    protected void casSoignerEnclos() {
        Enclos enclos;
        try {
            vueGlobale.afficher("\n ---- Soigner enclos (" + Enum_ActionsPossibles.SOIGNER_ENCLOS.getDureeTotale() + ") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                vueGlobale.afficher(zoo.voirNomsEnclosPasVide());
                enclos = controlUser.recupererEnclosParNom();
            }
            // GESTION AUTOMATIQUE
            else {
                enclos = controllerGestionAuto.getFirstEnclosCreatureMauvaiseSante();
                if (enclos == null)
                    enclos = controllerGestionAuto.recuperationEnclosAleatoireNonVide();
            }
            maitreZoo.soignerCreaturesEnclos(enclos);
            vueGlobale.afficher("Les creatures ont ete soignees dans " + enclos.getNom());
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour transférer une créature
     */
    protected void casTransfererCreature() {
        Enclos enclos;
        Enclos enclosDest;
        Creature creature;
        try {
            vueGlobale.afficher("\n ---- Transferer une creature (" + Enum_ActionsPossibles.TRANSFERER_CREATURE.getDureeTotale() + ") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                vueGlobale.afficher(zoo.voirNomsEnclosPasVide());
                enclos = controlUser.recupererEnclosParNom();
                vueGlobale.afficherCreatureEnclos(enclos);
                creature = controlUser.selectionCreatureDansEnclos(enclos);
                vueGlobale.afficher(zoo.voirNomsEnclos());
                enclosDest = controlUser.recupererEnclosParNom();
            }
            // GESTION AUTOMATIQUE
            else {
                enclos = controllerGestionAuto.recuperationEnclosAleatoireNonVide();
                enclosDest = controllerGestionAuto.recuperationEnclosAleatoire();
                creature = enclos.selectionnerCreatureAleatoireParSexe(ControllerPrincipal.sexeAleatoire());
            }
            maitreZoo.transfererCreature(creature, enclos, enclosDest);
            vueGlobale.afficher("Transfert de " + enclos.getNom() + " a " + enclosDest.getNom() +
                    "pour \n" + creature);
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour créer un enclos
     */
    protected void casCreerEnclos() {
        String nomEnclos;
        String typeEnclos = "Classique";
        boolean valeurOk = false;

        try {
            vueGlobale.afficher("\n ---- Creation d'un enclos (" + Enum_ActionsPossibles.CREER_ENCLOS.getDureeTotale() + ") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                nomEnclos = vueUtilisateur.demandeUtilisateur("Nom enclos : ");
                while (!valeurOk) {
                    typeEnclos = vueUtilisateur.demandeUtilisateur("Type enclos (Classique, Aquatique, Voliere, Lycanthrope) : ");
                    if ("Classique".equals(typeEnclos) || "Aquatique".equals(typeEnclos) || "Voliere".equals(typeEnclos) || "Lycanthrope".equals(typeEnclos)) {
                        valeurOk = true;
                    } else {
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
            switch (typeEnclos) {
                case "Classique" -> {
                    Enclos e = new EnclosClassique(nomEnclos, CONSTANTES.TAILLE_ENCLOS);
                    zoo.addEnclos(e);
                }
                case "Voliere" -> {
                    Voliere e = new Voliere(nomEnclos, CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
                    zoo.addEnclos(e);
                }
                case "Aquatique" -> {
                    Aquarium e = new Aquarium(nomEnclos, CONSTANTES.TAILLE_ENCLOS, CONSTANTES.TAILLE_ENCLOS);
                    zoo.addEnclos(e);
                }
                case "Lycanthrope" -> {
                    EnclosLycanthrope e = new EnclosLycanthrope(nomEnclos, CONSTANTES.TAILLE_ENCLOS);
                    zoo.addEnclos(e);
                }
                default -> throw new Exception("Erreur type enclos lors de la creation");
            }
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour transférer un enclos
     */
    protected void casTransfererEnclos() {
        Enclos enclos;
        Enclos enclosDest;
        String nomEnclos;
        try {
            vueGlobale.afficher("\n ---- Transferer un enclos (" + Enum_ActionsPossibles.TRANSFERER_ENCLOS.getDureeTotale() + ") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                vueGlobale.afficher(zoo.voirNomsEnclosPasVide());
                vueGlobale.afficher("\nSource");
                enclos = controlUser.recupererEnclosParNom();
                vueGlobale.afficher("Destination");
                enclosDest = controlUser.recupererEnclosParNom();
            }
            // GESTION AUTOMATIQUE
            else {
                enclos = controllerGestionAuto.recuperationEnclosAleatoireNonVide();
                int i = zoo.getListeEnclos().size() + 1;
                nomEnclos = "Enclos" + i;
                Enclos e = new EnclosClassique(nomEnclos, CONSTANTES.TAILLE_ENCLOS);
                zoo.addEnclos(e);
                enclosDest = e;
            }
            // Créez une copie de la liste des créatures
            Set<Creature> creaturesACopier = new HashSet<>(enclos.getListeCreatures().values());
            // Transférez les créatures de la copie vers enclos2
            for (Creature c : creaturesACopier) {
                maitreZoo.transfererCreature(c, enclos, enclosDest);
            }
            vueGlobale.afficher("Transfert de " + enclos.getNom() + " a " + enclosDest.getNom() +
                    " effectue\n");
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour concevoir un enfant
     */
    protected void casConcevoirEnfant() {
        Enclos enclos;
        Creature femelle;
        Creature male;
        try {
            vueGlobale.afficher("\n ---- Concevoir un enfant (" + Enum_ActionsPossibles.CONCEVOIR_ENFANT.getDureeTotale() + ") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                vueGlobale.afficher(zoo.voirNomsEnclosPasVide());
                enclos = controlUser.recupererEnclosParNom();
                vueGlobale.afficherCreatureEnclos(enclos);
                vueGlobale.afficher("\n\nVeuillez selectionner une femelle puis un male\n");
                // Femelle
                femelle = controlUser.selectionCreatureDansEnclos(enclos);
                // Male
                male = controlUser.selectionCreatureDansEnclos(enclos);
            }
            // GESTION AUTOMATIQUE
            else {
                enclos = controllerGestionAuto.recuperationEnclosAleatoireNonVide();
                femelle = enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Femelle);
                male = enclos.selectionnerCreatureAleatoireParSexe(Enum_Sexe.Male);
                if (femelle == null || male == null)
                    throw new Exception("impossible d'avoir un male et une femelle");
            }
            //Conception
            int naitre = enclos.concevoirEnfant(femelle, male);
            if (naitre == 1) {
                zoo.addFemelleEnceinte(femelle);
                vueGlobale.afficher("Enfant en cours de type " + femelle.getNomEspece());
            } else if (naitre == 2) {
            	((Ovipare)femelle).creerBebe((Ovipare) male);
                ArrayList<Oeuf> oeufs = ((Ovipare) femelle).pondreOeuf();
                for (Oeuf o : oeufs) {
                    zoo.addOeuf(o);
                    vueGlobale.afficher("Oeuf(s) pondu de type " + o.getEspece());
                }
            } else if (naitre == -1)
                vueGlobale.afficher("Impossible de concevoir");
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour mettre un enclos en mouvement
     */
    protected void casEnclosEnMouvement() {
        Enclos enclos;
        boolean peutCourrir = false;
        boolean peutNager = false;
        boolean peutVoler = false;
        boolean ActionEffectue = false;
        String choix = "";
        String chaine = "";
        try {
            vueGlobale.afficher("\n ---- Seance de sport pour un enclos (" + Enum_ActionsPossibles.METTRE_ENCLOS_EN_MOUVEMENT.getDureeTotale() + ") ---- ");

            // Choix enclos
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                vueGlobale.afficher(zoo.voirNomsEnclosPasVide());
                enclos = controlUser.recupererEnclosParNom();
            }
            // GESTION AUTOMATIQUE
            else {
                enclos = controllerGestionAuto.recuperationEnclosAleatoireNonVide();
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
                    ActionEffectue = true;
                }
                if (peutCourrir && !ActionEffectue) {
                    choix = "courir";
                    ActionEffectue = true;
                }
                if (peutVoler && !ActionEffectue) {
                    choix = "voler";
                    ActionEffectue = true;
                }
                if (!ActionEffectue)
                    throw new Exception("Impossible de faire du sport pour eux... en esperant qu'ils ne grossisent pas trop du coup\n");
            }
            // Mouvement de l'enclos
            if ("nager".equals(choix) && peutNager) {
                vueGlobale.afficher("ALLEZ ! On nage les " + enclos.getNomEspece() + "s. ALLEZ ! \n");
                for (Creature c : enclos.getListeCreatures().values()) {
                    Thread.sleep(1000);
                    if (!c.isEnTrainDeDormir() && c.isVivant())
                        chaine = ((CreatureMarine) c).nager();
                    vueGlobale.afficher(chaine);
                }
            } else if ("courir".equals(choix) && peutCourrir) {
                vueGlobale.afficher("ALLEZ ! On court les " + enclos.getNomEspece() + "s. ALLEZ !\n");
                for (Creature c : enclos.getListeCreatures().values()) {
                    Thread.sleep(1000);
                    if (!c.isEnTrainDeDormir() && c.isVivant())
                        chaine = ((CreatureTerrestre) c).courrir();
                    vueGlobale.afficher(chaine);
                }
            } else if ("voler".equals(choix) && peutVoler) {
                vueGlobale.afficher("ALLEZ ! On vole les " + enclos.getNomEspece() + "s. ALLEZ ! \n");
                for (Creature c : enclos.getListeCreatures().values()) {
                    Thread.sleep(1000);
                    if (!c.isEnTrainDeDormir() && c.isVivant())
                        chaine = ((CreatureVolante) c).voler();
                    vueGlobale.afficher(chaine);
                }
            } else
                throw new Exception("Assurez vous que l'enclos soit adapte et que les"
                        + " animaux ont la bonne categorie pour faire cette action");
            vueGlobale.afficher("\nFelicitation mes petites creatures !");
            Thread.sleep(1000);
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Methode pour faire chanter un enclos
     */
    protected void casChanterEnclos() {
        Enclos enclos;
        try {
            vueGlobale.afficher("\n ---- Concert prive pour un enclos (" + Enum_ActionsPossibles.FAIRE_CHANTER_ENCLOS.getDureeTotale() + ") ---- ");
            // Choix enclos
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                vueGlobale.afficher(zoo.voirNomsEnclosPasVide());
                enclos = controlUser.recupererEnclosParNom();
            }
            // GESTION AUTOMATIQUE
            else {
                enclos = controllerGestionAuto.recuperationEnclosAleatoireNonVide();
            }
            vueGlobale.afficher("\nAllez les " + enclos.getNomEspece() + "s.\n"
                    + "On chante l'un apres l'autre !\n");
            Thread.sleep(1000);
            for (Creature c : enclos.getListeCreatures().values()) {
                if (!c.isEnTrainDeDormir() && c.isVivant()) {
                    vueGlobale.afficher(c.getPrenom() + " : " + c.faireBruit());
                    String file = "";

                    if (c instanceof Lycanthrope) {
                        Random r = new Random();
                        int n = r.nextInt(8); // 8 = nombre fichier
                        file = "lycanthrope/loup" + (n + 1) + ".wav";
                    }
					else if (c instanceof Licorne) {
                        Random r = new Random();
                        int n = r.nextInt(9);
                        file = "licorne/licorne" + (n + 1) + ".wav";
                    }
					else if (c instanceof Dragon) {
                        Random r = new Random();
                        int n = r.nextInt(4);
                        file = "dragon/dragon" + (n + 1) + ".wav";
                    }
                    else if (c instanceof Kraken) {
                        Random r = new Random();
                        int n = r.nextInt(3);
                        file = "kraken/kraken" + (n + 1) + ".wav";
                    }
					else if (c instanceof Megalodon) {
                        Random r = new Random();
                        int n = r.nextInt(5);
                        file = "megalodon/megalodon" + (n + 1) + ".wav";
                    }
					else if (c instanceof Sirene) {
                        Random r = new Random();
                        int n = r.nextInt(2);
                        file = "sirene/sirene" + (n + 1) + ".wav";
                    }
					else if (c instanceof Nymphe) {
                        Random r = new Random();
                        int n = r.nextInt(4);
                        file = "nymphe/nymphe" + (n + 1) + ".wav";
                    }
					else if (c instanceof Phenix) {
                        Random r = new Random();
                        int n = r.nextInt(4);
                        file = "phenix/phenix" + (n + 1) + ".wav";
                    }

                    (new Son()).play(file);


                }
                Thread.sleep(1000);
            }
            vueGlobale.afficher("\nBon.. Il y a encore du travail, mais c'est un debut...\n");
            Thread.sleep(1000);
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour endormir un enclos
     */
    protected void casDormirEnclos() {
        Enclos enclos;
        try {
            vueGlobale.afficher("\n ---- Dodo Party pour un enclos (" + Enum_ActionsPossibles.DORMIR_ENCLOS.getDureeTotale() + ") ---- ");
            // Choix enclos
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                vueGlobale.afficher(zoo.voirNomsEnclosPasVide());
                enclos = controlUser.recupererEnclosParNom();
            }
            // GESTION AUTOMATIQUE
            else {
                enclos = controllerGestionAuto.getFirstEnclosCreatureSommeil();
                if (enclos == null)
                    enclos = controllerGestionAuto.recuperationEnclosAleatoire();
            }
            enclos.faireDormirCreatures();
            vueGlobale.afficher("Les creatures se sont endormis dans " + enclos.getNom() + "\n ATTENTION DE NE PAS LES REVEILLER...\n");
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour reveiller un enclos
     */
    protected void casReveillerEnclos() {
        Enclos enclos;
        try {
            vueGlobale.afficher("\n ---- Fin de la Dodo Party pour un enclos (" + Enum_ActionsPossibles.REVEILLER_ENCLOS.getDureeTotale() + ") ---- ");
            // Choix enclos
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                vueGlobale.afficher(zoo.voirNomsEnclosPasVide());
                enclos = controlUser.recupererEnclosParNom();
            }
            // GESTION AUTOMATIQUE
            else {
                enclos = controllerGestionAuto.getFirstEnclosCreatureDort();
                if (enclos == null)
                    enclos = controllerGestionAuto.recuperationEnclosAleatoire();
            }
            enclos.reveillerCreatures();
            vueGlobale.afficher("Les creatures se reveillent dans " + enclos.getNom() + "\n J'espere qu'elles sont de bonne humeur...\n");
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour voir les lycanthropes du zoo
     */
    public void casVoirLycanthropes() {
        try {
            vueGlobale.afficher("\n ---- Voir les lycanthropes (" + Enum_ActionsPossibles.VOIR_LOUPS.getDureeTotale() + ") ---- ");
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP / 2);
            vueGlobale.afficher("VOICI LES LYCANTHROPES :\n");
            for (Enclos e : zoo.getListeEnclos()) {
                if (e instanceof EnclosLycanthrope) {
                    for (Creature l : e.getListeCreatures().values()) {
                        vueGlobale.afficherCreature(l, -1);
                    }
                }
            }
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour lancer la saison des amours des lycanthropes
     */
    public void casSaisonAmourLycanthropes() {
        try {
            vueGlobale.afficher("\n ---- Verification saison amour pour lycanthropes (" + Enum_ActionsPossibles.SAISON_AMOUR_LOUPS.getDureeTotale() + ") ---- ");
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP / 2);
            Set<Lycanthrope> listeFemelleEnceinte = colonie.verificationSaisonAmour(temps.getDateActuelle());
            if (!listeFemelleEnceinte.isEmpty()) {
                for (Lycanthrope l : listeFemelleEnceinte) {
                    zoo.addFemelleEnceinte(l);
                    vueGlobale.afficher(l.getPrenom() + " est enceinte\n");
                }
            } else
                vueGlobale.afficher("Pas de bebe pour le moment... \n(saison des amours est de mai a juin)");
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour voir les meutes du zoo
     */
    public void casVoirMeutes() {
        try {
            vueGlobale.afficher("\n ---- Voir les meutes (" + Enum_ActionsPossibles.VOIR_MEUTES.getDureeTotale() + ") ---- ");
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP / 2);
            for (Meute m : colonie.getListeMeutes()) {
                vueGlobale.afficher("MEUTE " + m.getNomMeute() + " ; \n");
                for (Lycanthrope l : m.getListeLoup()) {
                    vueGlobale.afficherCreature(l, -1);
                }
            }
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour lancer un combat
     */
    public void casCombat() {
        Enclos enclos1;
        Enclos enclos2;
        Creature c1;
        Creature c2;
        try {
            vueGlobale.afficher("\n ---- C'est l'heure de la baston ---- ");
            // Choix enclos
            // GESTION MANUEL
            if (Run.utilisateurControle) {
            	
                vueGlobale.afficher("\nChoisir l'enclos de la premiere créature pour la bagarre : ");
                vueGlobale.afficher(zoo.voirNomsEnclos());
                enclos1 = controlUser.recupererEnclosParNom();
                vueGlobale.afficherCreatureEnclos(enclos1);
                vueGlobale.afficher("\nChoisir la créature 1 pour la bagarre (index) : ");

                c1 = controlUser.selectionCreatureDansEnclos(enclos1);
                vueGlobale.afficher("\nChoisir l'enclos de la seconde créature pour la bagarre : ");
                vueGlobale.afficher(zoo.voirNomsEnclos());
                enclos2 = controlUser.recupererEnclosParNom();
                vueGlobale.afficher("\nChoisir la créature 2 pour la bagarre (index) : ");

                c2 = controlUser.selectionCreatureDansEnclos(enclos2);
            }
            // GESTION AUTOMATIQUE
            else {
                enclos1 = controllerGestionAuto.recuperationEnclosAleatoireNonVide();
                c1 = enclos1.selectionnerCreatureAleatoireParSexe(ControllerPrincipal.sexeAleatoire());

                enclos2 = controllerGestionAuto.recuperationEnclosAleatoireNonVide();
                c2 = enclos2.selectionnerCreatureAleatoireParSexe(ControllerPrincipal.sexeAleatoire());
            }
            vueGlobale.afficher("\n #============= Voici les candidats =================# ");
            vueGlobale.afficherCreature(c1, -1);
            vueGlobale.afficherCreature(c2, -1);
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
            vueGlobale.afficher("\n");
            vueGlobale.afficher("\n#============= Que le combat commence ! =================# ");
            Creature vainqueur = maitreZoo.lancerCombat(c1, c2);
            vueGlobale.afficherCombat(c1, c2);
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
            vueGlobale.afficher("\n");

            vueGlobale.afficher("\n#============= Le Combat est terminé... =================# ");
            vueGlobale.afficher("\nLe vainqueur du combat est : ");
            vueGlobale.afficherCreature(vainqueur, -1);

            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Méthode appelée pour faire hurler un loup
     * à destination d'un autre loup peu importe
     * l'endroit auquel il se toruve
     */
    public void casFaireHurlerLoup() {
        Enum_ActionHurlement action = null;
        boolean choixActionOk = false;
        Enclos enclos1;
        Creature loup1;
        Enclos enclos2;
        Creature loup2;
        try {
            vueGlobale.afficher("\n ---- Faire hurler un loup (" + Enum_ActionsPossibles.FAIRE_HURLER_LOUP.getDureeTotale() + ") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                // choix action
                String choixAction;
                while (!choixActionOk) {
                    vueGlobale.afficher("LES TYPES DE HURLEMENT : appartenance, domination, soumission, agressivite");
                    choixAction = vueUtilisateur.demandeUtilisateur("Quelle action souhaitez vous transmettre : ");
                    // Vérifier si l'action est valide
                    switch (choixAction) {
                        case "appartenance" -> {
                            action = Enum_ActionHurlement.Appartenance;
                            choixActionOk = true;
                        }
                        case "domination" -> {
                            action = Enum_ActionHurlement.Domination;
                            choixActionOk = true;
                        }
                        case "soumission" -> {
                            action = Enum_ActionHurlement.Soumission;
                            choixActionOk = true;
                        }
                        case "agressivite" -> {
                            action = Enum_ActionHurlement.Agressivite;
                            choixActionOk = true;
                        }
                        default -> System.out.println("Action invalide. "
                                + "Veuillez choisir parmi les options : appartenance, domination, soumission, agressivite");
                    }

                }
                // choix loup 1
                vueGlobale.afficher(zoo.voirNomsEnclos());
                vueGlobale.afficher("Choix de l'enclos où le loup doit hurler (nom) : ");
                enclos1 = controlUser.recupererEnclosParNom();
                while (!(enclos1 instanceof EnclosLycanthrope)) {
                    vueGlobale.afficher("Il faut choisir un enclos de Lycanthrope");
                    vueGlobale.afficher("Choix de l'enclos où le loup doit hurler (nom) : ");
                    enclos1 = controlUser.recupererEnclosParNom();
                }
                vueGlobale.afficherCreatureEnclos(enclos1);
                vueGlobale.afficher("\n\nVeuillez selectionner le lycanthrope qui hurlee\n");
                loup1 = controlUser.selectionCreatureDansEnclos(enclos1);
                // choix loup 2
                vueGlobale.afficher("Choix de l'enclos où le loup doit entendre le hurlement (index) : ");
                enclos2 = controlUser.recupererEnclosParNom();
                while (!(enclos2 instanceof EnclosLycanthrope)) {
                    vueGlobale.afficher("Il faut choisir un enclos de Lycanthrope");
                    vueGlobale.afficher("Choix de l'enclos où le loup doit hurler (nom) : ");
                    enclos2 = controlUser.recupererEnclosParNom();
                }
                vueGlobale.afficherCreatureEnclos(enclos2);
                vueGlobale.afficher("\n\nVeuillez selectionner un lycanthrope qui entend (index) : \n");
                loup2 = controlUser.selectionCreatureDansEnclos(enclos2);
            }
            // GESTION AUTOMATIQUE
            else {
                // choix action
                // Définir les actions possibles dans un tableau
                Random random = new Random();
                Enum_ActionHurlement[] listeActions = {Enum_ActionHurlement.Appartenance, Enum_ActionHurlement.Domination, Enum_ActionHurlement.Soumission, Enum_ActionHurlement.Agressivite};
                action = listeActions[random.nextInt(listeActions.length)];
                // choix loup source
                enclos1 = controllerGestionAuto.recuperationEnclosAleatoireNonVideLycanthrope();
                loup1 = enclos1.selectionnerCreatureAleatoireParSexe(ControllerPrincipal.sexeAleatoire());
                // choix loup destination
                enclos2 = controllerGestionAuto.recuperationEnclosAleatoireNonVideLycanthrope();
                loup2 = enclos2.selectionnerCreatureAleatoireParSexe(ControllerPrincipal.sexeAleatoire());
            }
            vueGlobale.afficher(((Lycanthrope) loup1).hurler(action, (Lycanthrope) loup2));

            Random r = new Random();
            int n = r.nextInt(8); // 8 = nombre fichier
            String file = "lycanthrope/loup" + (n + 1) + ".wav";
            (new Son()).play(file);

            // cri du deuxieme loup garou
            r = new Random();
            file = "lycanthrope/loup" + (n + 1) + ".wav";
            (new Son()).play(file);

        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Methode appellé lorsque l'action à effectuer est de defier un male alpha dans une meute
     */
    public void casDefierAlpha() {
        Meute m;
        Lycanthrope loupVolontaire;
        try {
            vueGlobale.afficher("\n ---- Defier un male alpha (" + Enum_ActionsPossibles.DEFIER_MALE_ALPHA.getDureeTotale() + ") ---- ");
            // GESTION MANUEL
            if (Run.utilisateurControle) {
                // Choix meute
                vueGlobale.afficher("Choix de la meute où cela doit se passer");
                vueGlobale.afficher(colonie.voirMeutes());
                m = controlUser.recupererMeuteParNom();
                // Choix loup volontaire
                Enclos enclos = m.getEnclosReference();
                vueGlobale.afficherCreatureEnclos(enclos);
                vueGlobale.afficher("Choix du loup (index) : ");
                loupVolontaire = (Lycanthrope) controlUser.selectionCreatureDansMeute(m);
            }
            // GESTION AUTOMATIQUE
            else {
                // Choix meute
                m = controllerGestionAuto.choixMeuteAleatoire();
                // Choix loup volontaire
                loupVolontaire = m.choixPremierLoupPasCoupleAlpha();
            }
            vueGlobale.afficher(m.defierMaleAlpha(loupVolontaire));
            Thread.sleep(CONSTANTES.TEMPS_APPLICATION_SLEEP);
        } catch (Exception e) {
            vueGlobale.afficher(e.getMessage());
        }
    }


    /**
     * Methode gérant le cas où l'action est de voir les enfants qui sont en construction
     */
    public void casVoirEnfantsEnConstruction() {
        vueGlobale.afficher("\n ---- Voir les enfants qui vont bientot naitre (" + Enum_ActionsPossibles.VOIR_BEBES_EN_CONSTRUCTION.getDureeTotale() + ") ---- ");
        // Affichage des femelles enceinte
        vueGlobale.afficher("LES FEMELLES ENCEINTES : ");
        for (Creature c : zoo.getListeFemelleEnceinte())
            vueGlobale.afficherCreature(c, -1);
        // Affichage des oeufs
        vueGlobale.afficher("LES OEUFS : ");
        for (Oeuf o : zoo.getlLsteOeufs())
            vueGlobale.afficherOeuf(o);
    }
}
