package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/** Klasa odpowiadaj¹ca za Surowiec
 * 
 * @author Patryk
 *
 */
public class Surowiec extends SkladnikAktywow {
	
	private String jednostka;
	private double minimalnyKurs;
	private double maxymalnyKurs;
	private Waluta waluta;
	
	TablicaJednostek j=new TablicaJednostek();
	Random r = new Random();
	/** Konstruktor klasy
	 * 
	 * @param nazwa
	 */
	public Surowiec(String nazwa) {
		super(nazwa);
		this.jednostka=j.wylosuj();
		this.minimalnyKurs=this.maxymalnyKurs= this.getWartosc();
		List<Waluta> tmp = new LinkedList<>();
		for(int i=0;i<Main.listaAktywow.size();i++) {
			if(Main.listaAktywow.get(i) instanceof Waluta) {
				tmp.add((Waluta) Main.listaAktywow.get(i));
			}
		}
		this.waluta = tmp.get(r.nextInt(tmp.size()));
	}

	public String getJednostka() {
		return jednostka;
	}

	public void setJednostka(String jednostka) {
		this.jednostka = jednostka;
	}


	public double getMinimalnyKurs() {
		return minimalnyKurs;
	}

	public void setMinimalnyKurs(double minimalnyKurs) {
		this.minimalnyKurs = minimalnyKurs;
	}

	public double getMaxymalnyKurs() {
		return maxymalnyKurs;
	}

	public void setMaxymalnyKurs(double maxymalnyKurs) {
		this.maxymalnyKurs = maxymalnyKurs;
	}
	
	public String toString() {
		return this.getNazwa() + " - Surowiec " + Main.round(this.getWartosc(), 2); 
	}

	public Waluta getWaluta() {
		return waluta;
	}

	public void setWaluta(Waluta waluta) {
		this.waluta = waluta;
	}

}
