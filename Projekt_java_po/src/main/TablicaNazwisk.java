package main;

import java.io.Serializable;
import java.util.Random;
/** Klasa zawieraj¹ca tablice nazwisk, potrzebn¹ do losowania nazwiska dla inwestora
 * 
 * @author Patryk
 *
 */
public class TablicaNazwisk implements Serializable {
	
	private String[] tablica = {"Brzeziñski","Belfort","McKwacz","Scrooge","Gates","Trump","Buffett","Rockefeller","Zuckerberg","Azoff","Dolata","Kukie³a","Czarnecki","Kolasiñski",
			"Wiœniewski","Grzelak","Nowak","Kowalski","Jankowski","Marchlewicz","Z³otopolski","Mostowiak","Sparrow","Potter","Skywalker","Simpson",
			"Pi³sudki","Donald","Baggins","Buczowski","Costner","Chan","Seagal","Norris","Statham","Wahlberg","Diesel","Johnson","Rolson"};
	Random r = new Random();
	
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
