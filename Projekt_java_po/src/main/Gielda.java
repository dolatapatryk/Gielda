package main;

import java.util.LinkedList;
import java.util.List;
/** Klasa odpowiadaj¹ca za gie³de
 * 
 * @author Patryk
 *
 */
public class Gielda extends Rynek  {
	
	private String kraj;
	private String miasto;
	private String adresSiedziby;
	private Waluta waluta;
	private List<Spolka> listaSpolek;
	private String[] tablicaMiast = {"Pacanowo","Toronto","Miami","Nowy York","Londyn","Poznañ","Kowno","Kolejowo","San Francisco","Antananarywa",
			"Sri DŸajawardanapura Kotte"};
	
	private String[] tablicaUlic = {"Wilczak","Wojciechowskiego","Strzelecka","Pleszewska","D³uga","Krajowa","Krótka","Brzozowa","Klonowa",
			"Zielona","Czerwona","Jab³kowa","Kolorowa","Ptasia","Wroniecka"
	};
	/**Konstruktor klasy
	 * 
	 * @param nazwa
	 * @param waluta
	 */
	public Gielda(String nazwa, Waluta waluta){
		super(nazwa);
		this.waluta = waluta;;
		this.listaSpolek=new LinkedList<>();
		this.adresSiedziby = "ul. "+tablicaUlic[r.nextInt(tablicaUlic.length)]+" "+(r.nextInt(78)+1);
		this.kraj = Waluta.getPanstwa()[r.nextInt(Waluta.getPanstwa().length)];
		this.miasto = tablicaMiast[r.nextInt(tablicaMiast.length)];
	}
	
	public void dodajSpolke(Spolka spolka){
		listaSpolek.add(spolka);
	}
	

	public String getKraj() {
		return kraj;
	}
	public void setKraj(String kraj) {
		this.kraj = kraj;
	}
	
	public String getMiasto() {
		return miasto;
	}
	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}
	public String getAdres_siedziby() {
		return adresSiedziby;
	}
	public void setAdres_siedziby(String adres_siedziby) {
		this.adresSiedziby = adres_siedziby;
	}


	public List<Spolka> getLista_spolek() {
		return listaSpolek;
	}


	public void setLista_spolek(List<Spolka> lista_spolek) {
		this.listaSpolek = lista_spolek;
	}

	public Waluta getWaluta() {
		return waluta;
	}

	public void setWaluta(Waluta waluta) {
		this.waluta = waluta;
	}

	public String toString() {
		return this.getNazwa()+" - Gie³da";
	}
	

}
