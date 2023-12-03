package interfaces;

/**
 * Interface pour les créatures volantes
 */
public interface CreatureVolante {
	/**
	 * Methode permettant à une creature volante de voler
	 * @return une chaine de caractère indiquant que la creature vole
	 * @throws Exception si elle n'est pas en capacité de voler
	 */
	public String voler () throws Exception;
}
