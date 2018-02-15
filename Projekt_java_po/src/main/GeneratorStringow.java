package main;

import java.io.Serializable;

/** Klasa posiadaj¹ca metodê, która generuje losowy ³ancuch znaków
 * 
 * @author Patryk
 *
 */


public class GeneratorStringow implements Serializable {
	private static final String litery = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/** Metoda generuj¹ca, zmienna count mówi nam o d³ugoœci ³añcucha znaków
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
