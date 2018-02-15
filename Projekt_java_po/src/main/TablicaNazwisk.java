package main;

import java.io.Serializable;
import java.util.Random;
/** Klasa zawieraj�ca tablice nazwisk, potrzebn� do losowania nazwiska dla inwestora
 * 
 * @author Patryk
 *
 */
public class TablicaNazwisk implements Serializable {
	
	private String[] tablica = {"Brzezi�ski","Belfort","McKwacz","Scrooge","Gates","Trump","Buffett","Rockefeller","Zuckerberg","Azoff","Dolata","Kukie�a","Czarnecki","Kolasi�ski",
			"Wi�niewski","Grzelak","Nowak","Kowalski","Jankowski","Marchlewicz","Z�otopolski","Mostowiak","Sparrow","Potter","Skywalker","Simpson",
			"Pi�sudki","Donald","Baggins","Buczowski","Costner","Chan","Seagal","Norris","Statham","Wahlberg","Diesel","Johnson","Rolson"};
	Random r = new Random();
	
	/** Metoda losuj�ca
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
