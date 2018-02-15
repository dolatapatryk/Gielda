package main;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/** Klasa reprezentuj�ca rynek. Klasa nadrz�dna dla Rynku Walut, Rynku Surowc�w i Gie�dy
 * 
 * @author Patryk
 *
 */
public class Rynek implements Serializable {
	
	private String nazwa;
	private double marza;
	private List<SkladnikAktywow> listaAktywow;
	
	Random r = new Random();
	
	/**Konstruktor klasy
	 * 
	 */
	public Rynek(String nazwa){
		this.setNazwa(nazwa);
		this.setMarza(marza);
		this.marza = Main.round(r.nextDouble()/4, 2);
		listaAktywow = new LinkedList<>();
		Main.getListaRynkow().add(this);
	}
	
	

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}


	public double getMarza() {
		return marza;
	}

	public void setMarza(double marza) {
		this.marza = marza;
	}

	public List<SkladnikAktywow> getListaAktywow() {
		return listaAktywow;
	}

	public void setListaAktywow(List<SkladnikAktywow> listaAktywow) {
		this.listaAktywow = listaAktywow;
	}


}
