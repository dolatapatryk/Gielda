package main;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/** Klasa reprezentuj¹ca spó³ke
 * 
 * @author Patryk
 *
 */

public class Spolka implements Runnable, Serializable {
	
	private String nazwa;
	private String data;
	private double kursOtwarcia;
	private volatile double aktualnyKurs;
	private double minKurs;
	private double maxKurs;
	private int poczatkowaLiczbaAkcji;
	private volatile int liczbAkcji;
	private double zysk;
	private double przychod;
	private double kapitalWlasny;
	private int kapitalZakladowy;
	private int wolumen;
	private double obroty;
	
	Random r = new Random();
	private boolean zakonczWatek = false;
	/** Konstruktor klasy
	 * 
	 * @param nazwa
	 * @param gielda
	 */
	public Spolka(String nazwa, Gielda gielda) {
		
		this.nazwa = nazwa;
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		data = dateFormat.format(currentDate);
		this.minKurs = this.maxKurs = this.aktualnyKurs = this.kursOtwarcia = r.nextDouble()*10 +3;
		this.kapitalWlasny = r.nextDouble()*100000+20000;
		this.kapitalZakladowy = (int) (this.kapitalWlasny/10);
		gielda.getLista_spolek().add(this);
		Main.getListaSpolek().add(this);
		this.zysk = this.przychod = this.obroty = 0;
		this.wypuscAkcje(gielda);
		
		
	}
	
	/**Metoda odpowiadaj¹ca za dzia³anie w¹tku
	 * 
	 */
	@Override
	public void run() {
		while(!zakonczWatek) {
			try {
				TimeUnit.SECONDS.sleep(r.nextInt(9)+3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized(Main.monitor) {
				if(this.liczbAkcji<=10) {
					int x = 10 - this.liczbAkcji;
					this.liczbAkcji = this.liczbAkcji + x +r.nextInt(40)+1;
				}
				
				aktualizujSpolke(r.nextDouble()*13);
				generujPrzychod();
				generujZysk();
				liczbAkcji+=r.nextInt(15)+1;
				
				System.out.println("wypuszczono akcje "+this.getNazwa());
				MyFrame.textField.setText("wypuszczono akcje "+this.getNazwa());
			}
		
		}
	}
	
	public String toString() {
		return nazwa;
	}
	
	public void generujPrzychod(){
		this.przychod=r.nextDouble()*2000000;
	}
	
	public synchronized void generujZysk(){
		this.zysk = r.nextDouble()*500000;
		while(this.zysk>this.przychod) {
			this.zysk = r.nextDouble()*500000;
		}
	
	}
	
	/** Metoda, która wypuszcza akcje
	 * 
	 * @param gielda
	 */
	public void wypuscAkcje(Gielda gielda){
		new Akcja(this, gielda);
		this.liczbAkcji = this.poczatkowaLiczbaAkcji =  r.nextInt(200)+1;
	}
	
	/**Metoda, która aktualizuje spó³kê pod wzgledem wartoœci
	 * 
	 * @param aktualnyKurs
	 */
	
	public void aktualizujSpolke(double aktualnyKurs) {
		synchronized(Main.monitor) {
		this.setAktualnyKurs(aktualnyKurs);
		double min = Math.min(this.getAktualnyKurs(), this.getMinKurs());
		this.setMinKurs(min);
		double max = Math.max(this.getAktualnyKurs(), this.getMaxKurs());
		this.setMaxKurs(max);
		
		for(int i=0;i<Main.listaAkcji.size();i++) {
			if(Main.listaAkcji.get(i).getSpolka().getNazwa().equals(this.getNazwa())) {
				Main.listaAkcji.get(i).setWartosc(aktualnyKurs);
				break;
			}
		}
		
		for(int i=0;i<Main.listaAktywow.size();i++) {
			if(Main.listaAktywow.get(i) instanceof Akcja) {
				if(((Akcja) Main.listaAktywow.get(i)).getSpolka().getNazwa().equals(this.getNazwa())) {
					Main.listaAktywow.get(i).setWartosc(aktualnyKurs);
					break;
				}
			}
		}
		
		System.out.println("aktualka "+this.getNazwa());
		}
	}
	
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		
		this.data = data;
			
		
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public double getKursOtwarcia() {
		return kursOtwarcia;
	}
	public void setKursOtwarcia(double kurs_otwarcia) {
		this.kursOtwarcia = kurs_otwarcia;
	}
	
	public double getZysk() {
		return zysk;
	}
	public void setZysk(double zysk) {
		this.zysk = zysk;
	}
	public double getPrzychod() {
		return przychod;
	}
	public void setPrzychod(double przychod) {
		this.przychod += przychod;
	}
	public int getWolumen() {
		return wolumen;
	}
	public void setWolumen(int wolumen) {
		this.wolumen += wolumen;
	}
	public double getObroty() {
		return obroty;
	}
	public void setObroty(double obroty) {
		this.obroty = obroty;
	}
	public double getAktualnyKurs() {
		return aktualnyKurs;
	}


	public void setAktualnyKurs(double aktualnyKurs) {
		this.aktualnyKurs = aktualnyKurs;
	}


	public double getMinKurs() {
		return minKurs;
	}


	public void setMinKurs(double minKurs) {
		this.minKurs = minKurs;
	}


	public double getMaxKurs() {
		return maxKurs;
	}


	public void setMaxKurs(double maxKurs) {
		this.maxKurs = maxKurs;
	}


	public int getLiczbAkcji() {
		return liczbAkcji;
	}


	public void setLiczbAkcji(int liczbAkcji) {
		this.liczbAkcji = liczbAkcji;
	}
	
	public void zmniejszLiczbAkcji(int ilosc) {
		this.liczbAkcji -= ilosc;
	}


	public double getKapitalWlasny() {
		return kapitalWlasny;
	}


	public void setKapitalWlasny(double kapitalWlasny) {
		this.kapitalWlasny = kapitalWlasny;
	}


	public int getKapitalZakladowy() {
		return kapitalZakladowy;
	}


	public void setKapitalZakladowy(int kapitalZakladowy) {
		this.kapitalZakladowy = kapitalZakladowy;
	}

	public int getPoczatkowaLiczbaAkcji() {
		return poczatkowaLiczbaAkcji;
	}

	public void setPoczatkowaLiczbaAkcji(int poczatkowaLiczbaAkcji) {
		this.poczatkowaLiczbaAkcji = poczatkowaLiczbaAkcji;
	}

	public boolean isZakonczWatek() {
		return zakonczWatek;
	}

	public void setZakonczWatek(boolean zakonczWatek) {
		this.zakonczWatek = zakonczWatek;
	}
}
