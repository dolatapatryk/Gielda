package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
/** Klasa odpowiadaj¹ca za Walute
 * 
 * @author Patryk
 *
 */
public class Waluta extends SkladnikAktywow {
	
	
	private Set<String> listaKrajow;
	private static String[] panstwa = {"Polska","Niemcy","Wielka Brytania", "Rosja", "Brazylia", "Ekwador", "USA","Kanada","Mo³dawia",
			"Hiszpania","Francja","Portugalia","W³ochy","Etiopia","RPA","NRD","RFN","ZSRR","Czechos³owacja","Babilon","Mezopotamia",
			"Jugos³awia","Chiny","Indie","Maroko","Austrowêgry","Egipt","Rzeczpospolita Obojga Narodów","Trynidad i Tobago"
	};
	
	/**Konstruktor klasy
	 * 
	 * @param nazwa
	 */
	public Waluta(String nazwa){
		super(nazwa);
		listaKrajow = new HashSet<>();
		int liczba = r.nextInt(4)+1;
		for(int i=0;i<liczba;i++) {
			int index = r.nextInt(panstwa.length);
			listaKrajow.add(panstwa[index]);
		}
			
			
		}
	
	/** Konstruktor dla najwa¿niejszej waluty - PLN
	 * 
	 */
	public Waluta() {
		this.setNazwa("PLN");
		this.setWartosc(0);
		listaKrajow = new HashSet<>();
		Main.getListaAktywow().add(this);
		listaKrajow.add("Polska");
	}

	
	public String toString() {
		return this.getNazwa() + " - Waluta " + Main.round(this.getWartosc(), 2);
	}

	public Set<String> getListaKrajow() {
		return listaKrajow;
	}

	public void setListaKrajow(Set<String> listaKrajow) {
		this.listaKrajow = listaKrajow;
	}

	public static String[] getPanstwa() {
		return panstwa;
	}

	public static void setPanstwa(String[] panstwa) {
		Waluta.panstwa = panstwa;
	}
}
