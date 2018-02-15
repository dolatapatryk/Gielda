package main;

import java.util.List;
/** Klasa odpowiadaj¹ca za Rynek Surowców
 * 
 * @author Patryk
 *
 */
public class RynekSurowcow extends Rynek {
	private List<Surowiec> listaSurowcow;

	/** Konstruktor klasy
	 * 
	 * @param nazwa
	 */
	public RynekSurowcow(String nazwa) {
		super(nazwa);
	}
	
	public void dodajSurowiec(Surowiec surowiec){
		listaSurowcow.add(surowiec);
	}

	public List<Surowiec> getListaSurowcow() {
		return listaSurowcow;
	}

	public void setListaSurowcow(List<Surowiec> listaSurowcow) {
		this.listaSurowcow = listaSurowcow;
	}

	public String toString() {
		return this.getNazwa()+" - Rynek Surowców";
	}
}
