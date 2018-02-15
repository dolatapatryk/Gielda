package main;

import java.io.Serializable;
/**Klasa odpowiadaj¹ca za Akcje
	 * 
	 */
public class Akcja extends SkladnikAktywow  {

	
	private static final long serialVersionUID = 5031907960137039201L;
	private Spolka spolka;
	
	/** Konstruktor klasy
	 * 
	 * @param spolka
	 * @param gielda
	 */
	public Akcja(Spolka spolka, Gielda gielda) {
		super(spolka.getNazwa());
		this.spolka = spolka;
		this.setWartosc(spolka.getAktualnyKurs());
		gielda.getListaAktywow().add(this);
		Main.getListaAkcji().add(this);
		
		
	}
	public Spolka getSpolka() {
		return spolka;
	}
	public void setSpolka(Spolka spolka) {
		this.spolka = spolka;
	}
		
	public String toString() {
		return this.getNazwa() + " - Akcja " +Main.round(this.getWartosc(), 2);
	}

}
