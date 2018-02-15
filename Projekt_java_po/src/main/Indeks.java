package main;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
/**Klasa reprezentuj¹ca Indeks
	 * 
	 */
public class Indeks implements Serializable {
	
	
	
	private String nazwa;
	private double wartosc;
	private List<Spolka> lista;
	GeneratorStringow g = new GeneratorStringow();
	
	public Indeks(){
		this.nazwa = g.generujStringa(5);
		this.wartosc=0;
		lista = new LinkedList<>();
		Main.listaIndeksow.add(this);
	}
	
	public void dodajSpolke(Spolka spolka){
		lista.add(spolka);
	}

	public List<Spolka> getLista() {
		return lista;
	}

	public void setLista(List<Spolka> lista) {
		this.lista = lista;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public String toString() {
		return this.nazwa+" "+Main.round(this.wartosc, 2);
	}

	public double getWartosc() {
		return wartosc;
	}
	/**Metoda ta ustawia wartoœæ indeksu, czyli dodaje notowania ka¿dej ze spó³ek nale¿¹cej do indeksu
	 * 
	 */
	public void setWartosc() {
		synchronized(Main.monitor) {
		this.wartosc=0;
		List<Spolka> tmpLista = new LinkedList<>();
		for(int i=0;i<Main.listaSpolek.size();i++) {
			for(int j=0;j<this.lista.size();j++) {
				if(Main.listaSpolek.get(i).getNazwa().equals(this.lista.get(j).getNazwa())) {
					tmpLista.add(Main.listaSpolek.get(i));
					break;
				}
			}
		}
		for(int i=0;i<tmpLista.size();i++) {
			this.wartosc+=tmpLista.get(i).getAktualnyKurs();
		}
		}
	}

}
