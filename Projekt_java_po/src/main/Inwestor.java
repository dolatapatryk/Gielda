package main;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/** Klasa nadrzedna dla indywidualnego inwestora i funduszu
 * 
 * @author Patryk
 *
 */
public class Inwestor implements Serializable {
		
	
	private static final long serialVersionUID = 6149415694686026249L;
	private String imie;
	private String nazwisko;
	private volatile double budzet;
	private Map<SkladnikAktywow, Integer> lista; //key - nazwa aktywa, value - ilosc zakupionego
	private boolean zakonczWatek = false;
	
	TablicaImion i = new TablicaImion();
	TablicaNazwisk n = new TablicaNazwisk();
	Random r = new Random();
	
	/** Konstruktor klasy
	 * 
	 */
	public Inwestor(){
		this.imie = i.wylosuj();
		this.nazwisko = n.wylosuj();
		this.lista = new HashMap<>();
		if(this.imie=="Dariusz" && this.nazwisko=="Brzeziñski") {
			this.budzet = 1000000;
		}else {
		this.budzet = r.nextInt(35000)+1000;
		}
		
	}
	/** Metoda odpowiadajaca za kupno Akcji/Waluty/Surowca
	 * 
	 * @param skladnik
	 * @param marza
	 * @param ilosc
	 */
	public void kupAktywa(SkladnikAktywow skladnik, double marza, int ilosc){
		synchronized(Main.monitor) {
		if(skladnik instanceof Akcja) {
		if(ilosc> ((Akcja)skladnik).getSpolka().getLiczbAkcji() ) {
			System.out.println("za malo akcji");
		}else {
		if((budzet=budzet-(skladnik.getWartosc()*ilosc + (marza*skladnik.getWartosc())*ilosc)) < 0){
			System.out.println("za malo pieniedzy");
		}else {
		if(lista.containsKey(skladnik)) {
			int tmp = lista.get(skladnik);
			lista.remove(skladnik);
			ilosc+=tmp;
			lista.put(skladnik,ilosc);
		}else{
			lista.put(skladnik,ilosc);
			}
		}
		budzet=budzet- (skladnik.getWartosc()*ilosc + (marza*skladnik.getWartosc())*ilosc);
		((Akcja) skladnik).getSpolka().zmniejszLiczbAkcji(ilosc);
		((Akcja)skladnik).getSpolka().setWolumen(ilosc);
		}
		}else if(skladnik instanceof Surowiec) {
			if(((Surowiec) skladnik).getWaluta().getNazwa().equals("PLN")) {
				if((budzet=budzet-(skladnik.getWartosc()*ilosc+(marza*skladnik.getWartosc()*ilosc)))<0){
					System.out.println("za malo pieniedzy");
				}else {
					if(lista.containsKey(skladnik)) {
						int tmp = lista.get(skladnik);
						lista.remove(skladnik);
						ilosc+=tmp;
						lista.put(skladnik, ilosc);
					}else {
						lista.put(skladnik, ilosc);
					}
				budzet=budzet-(skladnik.getWartosc()*ilosc+(marza*skladnik.getWartosc()*ilosc));
				}
			}else {
			if((budzet=budzet-(skladnik.getWartosc()*((Surowiec)skladnik).getWaluta().getWartosc()*ilosc)+(marza*skladnik.getWartosc()*ilosc))<0){
				System.out.println("za malo pieniedzy");
			}else {
				if(lista.containsKey(skladnik)) {
					int tmp = lista.get(skladnik);
					lista.remove(skladnik);
					ilosc+=tmp;
					lista.put(skladnik, ilosc);
				}else {
					lista.put(skladnik, ilosc);
				}
			budzet=budzet-(skladnik.getWartosc()*((Surowiec)skladnik).getWaluta().getWartosc()*ilosc)+(marza*skladnik.getWartosc()*ilosc);
			}
			}
		}else if(skladnik instanceof Waluta) {
			if(skladnik.getNazwa().equals("PLN")) {
				System.out.println("nie kupie pln-ow");
			}else {
			if((budzet=budzet-(skladnik.getWartosc()*ilosc+(marza*skladnik.getWartosc()*ilosc)))<0) {
				System.out.println("za malo pieniedzy");
			}else {
				if(lista.containsKey(skladnik)) {
					int tmp = lista.get(skladnik);
					lista.remove(skladnik);
					ilosc+=tmp;
					lista.put(skladnik, ilosc);
				}else {
					lista.put(skladnik, ilosc);
				}
			}
		}
		}
		}
	}
	/** Metoda odpowiadaj¹ca za sprzedaz Akcji/Surowca/Waluty
	 * 
	 * @param skladnik
	 * @param ilosc
	 */
	public void sprzedajAktywa(SkladnikAktywow skladnik, int ilosc){
		synchronized(Main.monitor) {
		if(lista.get(skladnik)<ilosc) {
			System.out.println("nie mozna sprzedac takiej ilosci");
		}else {
			int tmp = lista.get(skladnik);
			lista.remove(skladnik);
			tmp-=ilosc;
			lista.put(skladnik, tmp);
			budzet+=skladnik.getWartosc()*ilosc;
		}
		}
	}
	
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public double getBudzet() {
		return budzet;
	}

	public void setBudzet(double budzet) {
		this.budzet = budzet;
	}

	public Map<SkladnikAktywow, Integer> getLista() {
		return lista;
	}

	public void setLista(Map<SkladnikAktywow, Integer> lista) {
		this.lista = lista;
	}
	public boolean isZakonczWatek() {
		return zakonczWatek;
	}
	public void setZakonczWatek(boolean zakonczWatek) {
		this.zakonczWatek = zakonczWatek;
	}

	
}
