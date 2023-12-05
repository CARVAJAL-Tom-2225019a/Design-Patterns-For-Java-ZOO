package interfaces;

/**
 * Interface d'une créature de type aquatique
 */
public interface CreatureMarine {
	
	/**
	 * Methode permettant à une créature marine de nager
	 * @return une chaine de caractère indiquant que la créature nage
	 * @throws Exception si la créature n'est pas en état de nager
	 */
    String nager() throws Exception;
	
}
