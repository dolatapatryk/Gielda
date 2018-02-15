package main;

import java.io.Serializable;
import java.util.Random;
/**Klasa zawieraj�ca tablice imion, potrzebn� do wylosowania imienia inwestora
 * 
 * @author Patryk
 *
 */
public class TablicaImion implements Serializable {

	private String[] tablica = {"Jordan","Sknerus","Donald","Warren","John","Ebenezer","Mark","Donnie","Patryk","Dariusz","Jerzy","Micha�","Bill",
			"Kevin","Maciej","J�zef","Jason","John","Steven","Vin","Dwayne","Jackie","Mateusz","Dominik","Bilbo","Luke","Frodo","Jakub",
			"Piotr","Han","Edward","Karol","W�adys�aw","Pawe�","Wiktor","J�drzej","George","Abraham","Barrack"};
	private Random r = new Random();
	
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
