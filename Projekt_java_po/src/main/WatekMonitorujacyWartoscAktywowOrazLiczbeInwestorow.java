package main;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/** Klasa, której obiekt ma za zadanie monitorowac wartoœc aktywow oraz dodawanie Inwestorów
 * 
 * @author Patryk
 *
 */

public class WatekMonitorujacyWartoscAktywowOrazLiczbeInwestorow implements Runnable, Serializable {

	
	private static final long serialVersionUID = 2922451487785586929L;
	private boolean zakonczWatek = false; 
	Random r = new Random();
	
	/**Metoda odpowiadaj¹ca za dzia³anie w¹tku
	 * 
	 */
	@Override
	public void run() {
		while(true) {
			if(!Main.isZakonczWatek()) {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized(Main.monitor) {
			for(int i=0;i<Main.listaAktywow.size();i++) {
				if(Main.listaAktywow.get(i) instanceof Waluta) {
					if(Main.listaAktywow.get(i).getNazwa().equals("PLN")) {
						continue;
					}else {
					Main.listaAktywow.get(i).setWartosc(r.nextDouble()*12);
					System.out.println("zaktualizowano walute");
					MyFrame.textField.setText("zaktualizowano walute");
					}
				}else if(Main.listaAktywow.get(i) instanceof Surowiec) {
					aktualizujSurowiec((Surowiec)Main.getListaAktywow().get(i),r.nextDouble()*12);
				}
			}
			
			for(int i=0;i<Main.listaIndeksow.size();i++) {
				Main.listaIndeksow.get(i).setWartosc();
			}
			
				
				
			for(int i=0;i<Main.getListaInwestorow().size();i++) {
				if(Main.getListaInwestorow().get(i).getBudzet() <=0) {
					Main.getListaInwestorow().remove(i);
					InwestorIndywidualny x = new InwestorIndywidualny();
					Thread t = new Thread(x);
					t.start();
				}
			}
			
			if(Main.listaAktywow.size()>5) {
				int liczba = r.nextInt(25)+1;
				if(liczba%5 == 0) {
				InwestorIndywidualny x = new InwestorIndywidualny();
				Thread t = new Thread(x);
				t.start();
				System.out.println("stworzylem inwestora");
				MyFrame.textField.setText("stworzylem inwestora");
				}
				}
			}
			
			
			
		}else {
			break;
		}
		}
	}
	
	
	public void aktualizujSurowiec(Surowiec surowiec, double aktualnyKurs) {
		surowiec.setWartosc(aktualnyKurs);
		double min = Math.min(surowiec.getWartosc(), surowiec.getMinimalnyKurs());
		surowiec.setMinimalnyKurs(min);
		double max = Math.max(surowiec.getWartosc(), surowiec.getMaxymalnyKurs());
		surowiec.setMaxymalnyKurs(max);
		System.out.println("zaktualizowany surowiec");
		MyFrame.textField.setText("zaktualizowany surowiec");
	}


	public boolean isZakonczWatek() {
		return zakonczWatek;
	}


	public void setZakonczWatek(boolean zakonczWatek) {
		this.zakonczWatek = zakonczWatek;
	}

}
