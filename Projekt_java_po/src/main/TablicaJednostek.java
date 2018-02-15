package main;

import java.io.Serializable;
import java.util.Random;
/** Klasa zawieraj¹ca tablice jednostek potrzebn¹ do losowania jednostki dla Surowca
 * 
 * @author Patryk
 *
 */
public class TablicaJednostek implements Serializable {
	private String[] tablica = {"kilogram","uncja","funt","kwintal","metr","radian","stopieñ Fahrenheita","niuton","mol",
			"amperogodzina","kiloWatoGodzina"};
	private Random r = new Random();
	
	/** Metoda losuj¹ca
	 * 
	 * @return
	 */
	public String wylosuj(){
		int x = r.nextInt(tablica.length);
		return tablica[x];
	}
	
	
	public String[] getTablica() {
		return tablica;
	}

	public void setTablica(String[] tablica) {
		this.tablica = tablica;
	}

}
