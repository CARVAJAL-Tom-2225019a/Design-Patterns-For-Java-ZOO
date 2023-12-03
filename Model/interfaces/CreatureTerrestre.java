package interfaces;

/**
 * Interface pour les créatures de type terrestre
 */
public interface CreatureTerrestre {
	
	/**
	 * Methode permettant à une créature terrestre de courrir
	 * @return Une chaine de caractère indiquant que la créature est en mouvement
	 * @throws Exception Si la créature n'est pas en état de courrir
	 */
	public String courrir () throws Exception ;

}
