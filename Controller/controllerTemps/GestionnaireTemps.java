package controllerTemps;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Cette classe implémente un gestionnaire de temps permettant de manipuler des dates.
 */
public class GestionnaireTemps {
    private Calendar calendrier;

    private static GestionnaireTemps instance;

    /**
     * Constructeur privé pour créer une instance du gestionnaire de temps.
     * @param annee L'année initiale.
     * @param mois Le mois initial.
     * @param jour Le jour initial.
     */
    private GestionnaireTemps(int annee, int mois, int jour) {
        calendrier = Calendar.getInstance();
        setDate(annee, mois, jour);
    }

    /**
     * Méthode statique pour obtenir une instance unique du gestionnaire de temps.
     * @param annee L'année initiale.
     * @param mois Le mois initial.
     * @param jour Le jour initial.
     * @return L'instance du gestionnaire de temps.
     */
    public static GestionnaireTemps getInstance() {
        if (instance == null) {
            instance = new GestionnaireTemps(2022, 21, 1);
        }
        return instance;
    }

    /**
     * Avance le temps de la quantité spécifiée de jours.
     * @param jours Le nombre de jours à avancer.
     * @return Vrai si l'année a changé après le passage du temps, sinon faux.
     */
    public boolean passerLeTemps(int jours) {
        int oldAnnee = getAnnee();
        calendrier.add(Calendar.DAY_OF_MONTH, jours);
        return verifierChangementAnnee(oldAnnee);
    }

    /**
     * Ajoute la quantité spécifiée d'années, de mois et de jours à la date actuelle.
     * @param annees Le nombre d'années à ajouter.
     * @param mois Le nombre de mois à ajouter.
     * @param jours Le nombre de jours à ajouter.
     * @return Vrai si l'année a changé après l'ajout du temps, sinon faux.
     */
    public boolean ajouterTemps(int annees, int mois, int jours) {
        int oldAnnee = getAnnee();
        calendrier.add(Calendar.YEAR, annees);
        calendrier.add(Calendar.MONTH, mois);
        calendrier.add(Calendar.DAY_OF_MONTH, jours);
        return verifierChangementAnnee(oldAnnee);
    }
    
    /**
     * Ajoute la quantite d'année, de mois et de jours selon l'action choisi
     * @param action
     */
    public boolean incrementerTemps(Enum_ActionsPossibles action) {
    	int oldAnnee = getAnnee();
        int dureeEnAnnees = action.getDureeEnAnnees();
        int dureeEnMois = action.getDureeEnMois();
        int dureeEnJours = action.getDureeEnJours();

        calendrier.add(Calendar.YEAR, dureeEnAnnees);
        calendrier.add(Calendar.MONTH, dureeEnMois);
        calendrier.add(Calendar.DAY_OF_MONTH, dureeEnJours);
        return verifierChangementAnnee(oldAnnee);
    }

    /**
     * Vérifie si l'année a changé par rapport à l'année précédente.
     * @param oldAnnee L'année précédente.
     * @return Vrai si l'année a changé, sinon faux.
     */
    public boolean verifierChangementAnnee(int oldAnnee) {
        return oldAnnee != getAnnee();
    }

    /**
     * Obtenir la date actuelle formatée selon le modèle "yyyy-MM-dd".
     * @return La date actuelle formatée.
     */
    public String getDateActuelle() {
        Date dateActuelle = calendrier.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(dateActuelle);
    }

    /**
     * Setters pour changer la date
     * @param annee L'année.
     * @param mois Le mois.
     * @param jour Le jour.
     */
    public void setDate(int annee, int mois, int jour) {
        calendrier.set(annee, mois - 1, jour); // Attention : le mois commence à 0 dans Calendar
    }

    /**
     * Methode pour obtenir l'année actuelle.
     * @return L'année actuelle.
     */
    public int getAnnee() {
        return calendrier.get(Calendar.YEAR);
    }

    /**
     * Methode pour obtenir le mois actuel.
     * @return Le mois actuel.
     */
    public int getMois() {
        return calendrier.get(Calendar.MONTH) + 1; // Ajoute 1 car le mois commence à 0
    }

    /**
     * Methode pour obtenir le jour actuel
     * @return Le jour actuel.
     */
    public int getJour() {
        return calendrier.get(Calendar.DAY_OF_MONTH);
    }
}
