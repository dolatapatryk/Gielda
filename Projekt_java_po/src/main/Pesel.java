package main;

import java.io.Serializable;
import java.util.Random;
/** Klasa generuj¹ca pesel
 * 
 * @author Patryk
 *
 */
public class Pesel implements Serializable {

	private String s="";
	private int x;
	
	
	Random r = new Random();
	String liczbaString;
	StringBuilder s1 = new StringBuilder(s);
	
	
	/** Metoda losuj¹ca pesel
	 * 
	 * @return
	 */
	public String wylosuj(){
	for(int i=0; i < 11; i++){
		x = r.nextInt(10);
		liczbaString = Integer.toString(x);
		s1.append(liczbaString);
	}
	s = s1.toString();
	return s;
	}
	
	
}
