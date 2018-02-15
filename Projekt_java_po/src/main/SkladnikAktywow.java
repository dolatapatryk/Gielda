package main;

import java.io.Serializable;
import java.util.Random;
/** Klasa nadrzêdna dla aktywów: Akcji, Surowca, Waluty
 * 
 * @author Patryk
 *
 */
public class SkladnikAktywow implements Serializable {
	

	private static final long serialVersionUID = -4783567637435617480L;
	private String nazwa; //nazwa Aktywa
	private double wartosc; //wartosc Aktywa
	Random r = new Random();
	
	/**Konstruktor klasy
	 * 
	 * @param nazwa
	 */
	public SkladnikAktywow(String nazwa){
		this.nazwa=nazwa;
		this.wartosc=r.nextDouble()*10;
		Main.getListaAktywow().add(this);
	}
	
	public SkladnikAktywow() {
		
	}
	

	
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public double getWartosc() {
		return wartosc;
	}

	public void setWartosc(double wartosc) {
		this.wartosc = wartosc;
	}
	
	
}
