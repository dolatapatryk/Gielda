package main;

import java.io.Serializable;

/** Klasa posiadaj�ca metod�, kt�ra generuje losowy �ancuch znak�w
 * 
 * @author Patryk
 *
 */


public class GeneratorStringow implements Serializable {
	private static final String litery = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/** Metoda generuj�ca, zmienna count m�wi nam o d�ugo�ci �a�cucha znak�w
	 * 
	 * @param count
	 * @return String
	 */
	public static String generujStringa(int count) {
	StringBuilder builder = new StringBuilder();
	while (count-- != 0) {
	int character = (int)(Math.random()*litery.length());
	builder.append(litery.charAt(character));
	}
	return builder.toString();
	}
}
