package main;

import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;
/** G³owna klasa projektu, posiadaj¹ca metode main
 * 
 * @author Patryk
 *
 */


public class Main {

	public static List<Spolka> listaSpolek = new LinkedList<>();
	public static List<SkladnikAktywow> listaAktywow = new LinkedList<>();
	public static List<Inwestor> listaInwestorow = new LinkedList<>();
	public static List<Inwestor> listaFunduszy = new LinkedList<>();
	public static List<Rynek> listaRynkow = new LinkedList<>();
	public static List<Akcja> listaAkcji = new LinkedList<>();
	public static List<Indeks> listaIndeksow = new LinkedList<>();
	private static  boolean  zakonczWatek = false;
	public static Object monitor = new Object();
	
	/** Metoda main tworz¹ca g³owne okno
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			
			@Override
			public void run(){
				
				new MyFrame();
				
				WatekMonitorujacyWartoscAktywowOrazLiczbeInwestorow w = new WatekMonitorujacyWartoscAktywowOrazLiczbeInwestorow();
				Thread w1 = new Thread(w); w1.setName("w1");
				
				RynekWalut rynekWalut = new RynekWalut("Rynek Walut");
				RynekSurowcow rynekSurowcow = new RynekSurowcow("Rynek Surowców");
				Waluta pln = new Waluta(); 
				Gielda gielda = new Gielda("Gie³da",pln); gielda.setKraj("Polska"); gielda.setMiasto("Poznañ");
				Surowiec surowiec = new Surowiec("Z³oto");
				Spolka mpw = new Spolka("MPW", gielda);
				Spolka xd = new Spolka("XD", gielda);
				InwestorIndywidualny inwestor = new InwestorIndywidualny();
				Fundusz fundusz = new Fundusz("Fundusz");
				Thread f = new Thread(fundusz); f.setName(fundusz.getNazwa()); 
				Thread t = new Thread(inwestor); t.setName(inwestor.getPesel()); 
				Thread s = new Thread(mpw); s.setName(mpw.getNazwa()); 
				Thread s1 = new Thread(xd); s1.setName(xd.getNazwa()); 
				t.start(); 
				w1.start();
				s.start(); 
				s1.start(); 
				f.start();
				MyFrame.btnStart.doClick();
				MyFrame.aktywnyRynek = "Gie³da";
				MyFrame.btnPoka.doClick();
				
				
			}
		});
		
	}

	/** Metoda zaokraglajaca liczbe double do okreslonej liczby miejsc po przecinku
	 * 
	 * @param liczba
	 * @param miejscaPoPrzecinku
	 * @return
	 */
	 public static float round(double liczba, int miejscaPoPrzecinku) //metoda zaokraglajaca liczbe double do dwoch miejsc po przecinku

	   {  float temp = (float)(liczba*(Math.pow(10, miejscaPoPrzecinku)));

	          temp = (Math.round(temp));

	          temp = temp/(int)(Math.pow(10, miejscaPoPrzecinku));

	          return temp;

	   }


	public static List<Spolka> getListaSpolek() {
		return listaSpolek;
	}


	public static void setListaSpolek(List<Spolka> listaSpolek) {
		Main.listaSpolek = listaSpolek;
	}


	public static List<SkladnikAktywow> getListaAktywow() {
		return listaAktywow;
	}


	public static void setListaAktywow(List<SkladnikAktywow> listaAktywow) {
		Main.listaAktywow = listaAktywow;
	}


	public static List<Inwestor> getListaInwestorow() {
		return listaInwestorow;
	}


	public static void setListaInwestorow(List<Inwestor> listaInwestorow) {
		Main.listaInwestorow = listaInwestorow;
	}


	public static List<Rynek> getListaRynkow() {
		return listaRynkow;
	}


	public static void setListaRynkow(List<Rynek> listaRynkow) {
		Main.listaRynkow = listaRynkow;
	}


	public static List<Akcja> getListaAkcji() {
		return listaAkcji;
	}


	public static void setListaAkcji(List<Akcja> listaAkcji) {
		Main.listaAkcji = listaAkcji;
	}


	public static List<Inwestor> getListaFunduszy() {
		return listaFunduszy;
	}


	public static void setListaFunduszy(List<Inwestor> listaFunduszy) {
		Main.listaFunduszy = listaFunduszy;
	}


	public static boolean isZakonczWatek() {
		return zakonczWatek;
	}


	public static void setZakonczWatek(boolean zakonczWatek) {
		Main.zakonczWatek = zakonczWatek;
	}
}
