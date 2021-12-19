package jeu;
/**
 * Gère les exceptions 
 */
@SuppressWarnings("serial")
public class jeuException extends Exception{
	public jeuException(String error) {
		super(error);
	}
}
