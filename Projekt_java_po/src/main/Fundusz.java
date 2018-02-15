package main;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/** Klasa odpowiadajaca za fundusz
 * 
 * @author Patryk
 *
 */
public class Fundusz extends Inwestor implements Runnable {
	private String nazwa;
	
	/** Konstruktor klasy
	 * 
	 * @param nazwa
	 */
	public Fundusz(String nazwa){
		super();
		this.nazwa = nazwa;
		Main.getListaFunduszy().add(this);
	}
	/** Nadpisana metoda odpowiadaj¹ca za kupno Akcji/Waluty/Surowca
	 * 
	 */
	@Override
	public void kupAktywa(SkladnikAktywow skladnik, double marza, int ilosc){
		super.kupAktywa(skladnik, marza, ilosc);
		synchronized(Main.monitor) {
		if(skladnik instanceof Akcja) {
			((Akcja) skladnik).getSpolka().zmniejszLiczbAkcji(ilosc);
			double kasa = ((Akcja) skladnik).getSpolka().getAktualnyKurs()*ilosc;
			((Akcja) skladnik).getSpolka().setObroty(((Akcja) skladnik).getSpolka().getObroty()+kasa);
		}
		}
		
		
	}
	
	/** Nadpisana metoda odpowiadaj¹ca za sprzeda¿ Akcji/Waluty/Surowca
	 * 
	 */
	public void sprzedajAktywa(SkladnikAktywow skladnik, int ilosc){  //j/w
		super.sprzedajAktywa(skladnik, ilosc);
	}
	public String toString() {
		return nazwa;
	}
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	/** Metoda odpowiadaj¹ca za dzia³anie w¹tku
	 * 
	 */
	@Override
	public void run() {
		while(this.getBudzet()>0) {
			if(!isZakonczWatek()) {
			
			try {
				TimeUnit.SECONDS.sleep(r.nextInt(8)+2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(Main.monitor) {
			List<SkladnikAktywow> tmpLista = new LinkedList<>();
			//wyznaczamy indeks aktualnego rynku
			int index = 0;
			for(int i=0;i<Main.getListaRynkow().size();i++) {
				if(MyFrame.aktywnyRynek.equals(Main.getListaRynkow().get(i).getNazwa())) {
					index=i;
					break;
				}
			}
			
			kupAktywa(Main.getListaAktywow().get(r.nextInt(Main.getListaAktywow().size())),Main.getListaRynkow().get(index).getMarza(),r.nextInt(10)+1);
			
			System.out.println("kupilem "+this.getNazwa());
			MyFrame.textField.setText("Kupilem "+this.getNazwa());
			
			for(int i=0;i<Main.getListaAktywow().size();i++) {
				if(this.getLista().containsKey(Main.getListaAktywow().get(i))) {
					tmpLista.add(Main.getListaAktywow().get(i));
					
				}
			}
			for(int i=0;i<tmpLista.size();i++) {
				System.out.println(tmpLista.get(i));
			}
			sprzedajAktywa(tmpLista.get(r.nextInt(tmpLista.size())),r.nextInt(10)+1);
			
			System.out.println("sprzedalem "+this.getNazwa());
			MyFrame.textField.setText("Sprzedalem "+this.getNazwa());
			
			MyFrame.listaFunduszy.clear();
			for(int i=0;i<Main.getListaFunduszy().size();i++) {
				MyFrame.listaFunduszy.addElement(Main.getListaFunduszy().get(i));
			}
			
			}
			}else {
				break;
			}
			}
	}
	
}
