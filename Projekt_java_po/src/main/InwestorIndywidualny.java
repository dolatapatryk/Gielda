package main;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/** Klasa reprezentuj¹ca indywidualnego inwestora, dziedzicz¹ca z klasy Inwestor
	 * 
	 */
public class InwestorIndywidualny extends Inwestor implements Runnable {
	
	private String pesel;
	Random r = new Random();
	Pesel p = new Pesel();
	
	/** Konstruktor klasy
	 * 
	 */
	public InwestorIndywidualny(){
		super();
		this.pesel = p.wylosuj();
		Main.getListaInwestorow().add(this);
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
	/** Nadpisana metoda odpowiadaj¹ca za sprzeda¿ aktywa
	 * 
	 */
	@Override
	public void sprzedajAktywa(SkladnikAktywow skladnik, int ilosc){
		super.sprzedajAktywa(skladnik, ilosc);
	}
	/** Metoda odpowiadaj¹ca za dzia³anie w¹tku
	 * 
	 */
	public void run(){
		
		while(this.getBudzet()>0) {
		if(!isZakonczWatek()) {
		try {
			TimeUnit.SECONDS.sleep(r.nextInt(8)+2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(Main.monitor) {
		List<SkladnikAktywow> tmpLista = new LinkedList<>();
		int index = 0;
		for(int i=0;i<Main.getListaRynkow().size();i++) {
			if(MyFrame.aktywnyRynek.equals(Main.getListaRynkow().get(i).getNazwa())) {
				index=i;
				break;
			}
		}
		
		kupAktywa(Main.getListaAktywow().get(r.nextInt(Main.getListaAktywow().size())),Main.getListaRynkow().get(index).getMarza(),r.nextInt(10)+1);
		
		System.out.println("kupilem "+this.getImie()+" "+this.getNazwisko());
		MyFrame.textField.setText("Kupilem "+this.getImie()+" "+this.getNazwisko());
		
		for(int i=0;i<Main.getListaAktywow().size();i++) {
			if(this.getLista().containsKey(Main.getListaAktywow().get(i))) {
				tmpLista.add(Main.getListaAktywow().get(i));
			}
		}
		if(tmpLista.size()>0) {
		sprzedajAktywa(tmpLista.get(r.nextInt(tmpLista.size())),r.nextInt(10)+1);
		}
		
		
		System.out.println("sprzedalem "+this.getImie()+" "+this.getNazwisko());
		MyFrame.textField.setText("Sprzedalem "+this.getImie()+" "+this.getNazwisko());
		
		this.setBudzet(this.getBudzet()+r.nextDouble()*10000);
		}
		}else {
			break;
		}
		}
			
		
		
	} 
	
	public String toString() {
		return getImie()+" "+getNazwisko()+" "+ /*getPesel()*/ + Main.round(getBudzet(), 2);
	}
	
	
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
}
