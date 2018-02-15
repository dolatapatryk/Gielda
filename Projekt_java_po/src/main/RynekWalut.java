package main;

import java.util.List;
/** Klasa odpowiadaj¹ca za Rynek Walut
 * 
 * @author Patryk
 *
 */
public class RynekWalut extends Rynek {
	private List<Waluta> listaWalut;
	
	/** Konstruktor klasy
	 * 
	 * @param nazwa
	 */
	public RynekWalut(String nazwa) {
		super(nazwa);
		
	}
	
	public void dodajWalute(Waluta waluta){
		listaWalut.add(waluta);
		}
	
	
	
	public List<Waluta> getLista_walut() {
		return listaWalut;
	}
	public void setLista_walut(List<Waluta> lista_walut) {
		this.listaWalut = lista_walut;
	}
	
	public String toString() {
		return this.getNazwa()+" - Rynek Walut";
	}
	
}
