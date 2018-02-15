package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
/**Klasa odpowiadaj¹ca za wygl¹d okna i dzialanie przyciskow
 * 
 * @author Patryk
 *
 */
public class MyFrame extends JFrame {
	
	
	
	private static final long serialVersionUID = -1751427211593841390L;
	public static JButton btnPoka;
	public static JButton btnStart;
	public static JMenuBar menuBar;
	public static JMenu mnRynki;
	private JList list;
	private JList list_1;
	private JList list_2;
	private JList list_3;
	private JList list_4;
	public static DefaultListModel<Spolka> listaSpolek = new DefaultListModel<>();
	public static DefaultListModel<Inwestor> listaInwestorow = new DefaultListModel<>();
	public static DefaultListModel<Inwestor> listaFunduszy = new DefaultListModel<>();
	public static  DefaultListModel<SkladnikAktywow> listaAktywow = new DefaultListModel<>();
	public static DefaultListModel<Rynek> listaRynkow = new DefaultListModel<>();
	private DefaultListModel<Indeks> listaIndeksow = new DefaultListModel<>();
	private Stan stan;
	public static String aktywnyRynek = null;
	public static JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea_1;
	
	public MyFrame(){
		super("Gie³da");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,600);
		setLocation(50,50);
		
		
		
		JButton btnNewButton = new JButton("Stw\u00F3rz");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tworzenie tworzenie = new Tworzenie();
				tworzenie.setVisible(true);
				
			}
		});
		
		JLabel lblFundusze = new JLabel("Fundusze");
		
		JLabel lblSpki = new JLabel("Sp\u00F3\u0142ki");
		
		btnPoka = new JButton("Od\u015Bwie\u017C");
		btnPoka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			listaSpolek.removeAllElements();
			listaInwestorow.removeAllElements();
			listaFunduszy.removeAllElements();
			listaAktywow.removeAllElements();
			listaRynkow.removeAllElements();
			listaIndeksow.removeAllElements();
			Tworzenie.listaSpolek.removeAllElements();
			//wyswietlanie rynkow
				for(int i=0;i<Main.getListaRynkow().size();i++) {
					listaRynkow.addElement(Main.getListaRynkow().get(i));

				}
				int aktualny = 0; //wyznaczamy indeks aktualnego wybranego rynku
				for(int i=0;i<Main.getListaRynkow().size();i++) {
					if(aktywnyRynek.equals(Main.getListaRynkow().get(i).getNazwa())) {
						aktualny=i;
						break;
					}
				}
				//wyswietlanie spolek aktualnej gieldy
				if(Main.getListaRynkow().get(aktualny) instanceof Gielda) {
					for(int i=0;i<Main.getListaSpolek().size();i++) {
						for(int j=0;j<((Gielda)Main.getListaRynkow().get(aktualny)).getLista_spolek().size();j++) {
						if(((Gielda)Main.getListaRynkow().get(aktualny)).getLista_spolek().get(j).getNazwa().equals(Main.getListaSpolek().get(i).getNazwa())) {
							listaSpolek.addElement(Main.getListaSpolek().get(i));
							break;
						}
						}
					}
				}
				
				//wyswietlanie wszystkich spolek (do tworzenia indeksów w drugim oknie)
				for(int i=0;i<Main.listaSpolek.size();i++) {
					Tworzenie.listaSpolek.addElement(Main.listaSpolek.get(i));
				}
				
				
				//wyswietlanie inwestorow
				for(int i=0;i<Main.getListaInwestorow().size();i++) {
					listaInwestorow.addElement(Main.getListaInwestorow().get(i));
				}
				//wyswietlanie funduszy
				for(int i=0;i<Main.getListaFunduszy().size();i++) {
					listaFunduszy.addElement(Main.getListaFunduszy().get(i));
				}
				
				//wyswietlanie aktywow
				for(int i=0;i<Main.getListaAktywow().size();i++) {
					if(Main.getListaRynkow().get(aktualny) instanceof RynekWalut) {
						if(Main.getListaAktywow().get(i) instanceof Waluta) {
							listaAktywow.addElement(Main.getListaAktywow().get(i));
						}
					}else if(Main.getListaRynkow().get(aktualny) instanceof RynekSurowcow) {
						if(Main.getListaAktywow().get(i) instanceof Surowiec) {
							listaAktywow.addElement(Main.getListaAktywow().get(i));
						}
					}else if(Main.getListaRynkow().get(aktualny) instanceof Gielda) {	
						if(Main.getListaAktywow().get(i) instanceof Akcja) {
							for(int j=0;j<Main.listaRynkow.get(aktualny).getListaAktywow().size();j++) {
								if(Main.getListaRynkow().get(aktualny).getListaAktywow().get(j).getNazwa().equals(Main.getListaAktywow().get(i).getNazwa())) {
									listaAktywow.addElement(Main.getListaAktywow().get(i));
									
									}
							}
						}else {
							listaAktywow.addElement(Main.getListaAktywow().get(i));
						}
					}
				}
				
				//wyswietlanie indeksow
				for(int i=0;i<Main.listaIndeksow.size();i++) {
					listaIndeksow.addElement(Main.listaIndeksow.get(i));
				}
				
				//wyswietlanie informacji o gie³dzie
				if(Main.listaRynkow.get(aktualny) instanceof Gielda) {
				textArea_1.setText(null);
				textArea_1.append("Nazwa: "+Main.listaRynkow.get(aktualny).getNazwa()+"\n");
				textArea_1.append("Kraj: "+((Gielda) Main.listaRynkow.get(aktualny)).getKraj()+"\n");
				textArea_1.append("Miasto: "+((Gielda) Main.listaRynkow.get(aktualny)).getMiasto()+"\n");
				textArea_1.append("Adres siedziby: "+((Gielda) Main.listaRynkow.get(aktualny)).getAdres_siedziby()+"\n");
				textArea_1.append("Waluta: "+((Gielda) Main.listaRynkow.get(aktualny)).getWaluta().getNazwa()+"\n");
				textArea_1.append("Mar¿a: "+Main.round(Main.listaRynkow.get(aktualny).getMarza(), 2));
				}else {
				textArea_1.setText(null);
				textArea_1.append("Nazwa: "+Main.listaRynkow.get(aktualny).getNazwa());
				}
			}
			
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		JScrollPane scrollPane_4 = new JScrollPane();
		
		JButton btnUsu = new JButton("Usu\u0144");
		btnUsu.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				switch(stan) {
				case AKTYWO:
					int index = list_3.getSelectedIndex();
					listaAktywow.remove(index);
					Main.getListaAktywow().remove(index);
					break;
				case SPOLKA:
					int index1 = list.getSelectedIndex();
					int aktualny = 0; //wyznaczamy indeks aktualnego wybranego rynku
					int aktualnaSpolka = 0;
					for(int i=0;i<Main.getListaRynkow().size();i++) {
						if(aktywnyRynek.equals(Main.getListaRynkow().get(i).getNazwa())) {
							aktualny=i;
							break;
						}
					}
					for(int i=0;i<Main.getListaAktywow().size();i++) {
						System.out.println(Main.getListaAktywow().get(i));
					}
					System.out.println("");
					for(int i=0;i<Main.getListaRynkow().get(aktualny).getListaAktywow().size();i++) {
						System.out.println(Main.getListaRynkow().get(aktualny).getListaAktywow().get(i));
					}
					
					System.out.println("");
					for(int i=0;i<listaAktywow.size();i++) {
						System.out.println(listaAktywow.get(i));
					}
				
					for(int i=0;i<Main.getListaAktywow().size();i++) {
						if(Main.getListaAktywow().get(i) instanceof Akcja) {
							for(int j=0;j<Main.getListaRynkow().get(aktualny).getListaAktywow().size();j++) {
							if(Main.getListaRynkow().get(aktualny).getListaAktywow().get(j).getNazwa().equals(Main.getListaAktywow().get(i).getNazwa())) {
							if(((Akcja) Main.getListaAktywow().get(i)).getSpolka().getNazwa().equals(listaSpolek.get(index1).getNazwa())) {
								Main.listaRynkow.get(aktualny).getListaAktywow().remove(j);
								for(int m=0;m<listaAktywow.size();m++) {
									if(listaAktywow.get(m).getNazwa().equals(Main.getListaAktywow().get(i))) {
										listaAktywow.remove(m);
										Main.getListaRynkow().get(aktualny).getListaAktywow().remove(m);
										break;
									}
								}
								
								Main.getListaAktywow().remove(i);
								break;
							}
							}
							}
						}
					}
					
					
					for(int i=0;i<Main.getListaAkcji().size();i++) {
						if(((Akcja) Main.getListaAkcji().get(i)).getSpolka().getNazwa().equals(Main.getListaSpolek().get(index1).getNazwa())) {
							Main.listaAkcji.remove(i);
							break;
						}
					}
					
					for(int i=0;i<Main.listaIndeksow.size();i++) {
						for(int j=0;j<Main.listaIndeksow.get(i).getLista().size();j++) {
						if(Main.listaIndeksow.get(i).getLista().get(j).getNazwa().equals(Main.getListaSpolek().get(index1).getNazwa())) {
							Main.listaIndeksow.get(i).getLista().remove(j);
							break;
						}
						}
					}
					
					
					for(int i=0;i<Main.getListaSpolek().size();i++) {
						if(Main.getListaSpolek().get(i).getNazwa().equals(listaSpolek.get(index1).getNazwa())) {
							aktualnaSpolka = i;
							break;
						}
					}
					Main.getListaSpolek().get(index1).setZakonczWatek(true);
					listaSpolek.remove(index1);
					Main.getListaSpolek().remove(aktualnaSpolka);
					btnPoka.doClick();
					break;
				case FUNDUSZ:
					int index2 = list_1.getSelectedIndex();
					Main.getListaFunduszy().get(index2).setZakonczWatek(true);
					listaFunduszy.remove(index2);
					Main.getListaFunduszy().remove(index2);
					break;
				case INWESTOR:
					int index3 = list_2.getSelectedIndex();
					Main.listaInwestorow.get(index3).setZakonczWatek(true);
					listaInwestorow.remove(index3);
					Main.getListaInwestorow().remove(index3);
					break;
				case INDEKS:
					int index4 = list_4.getSelectedIndex();
					listaIndeksow.remove(index4);
					Main.listaIndeksow.remove(index4);
					break;
				}
			}
		});
		
		btnStart = new JButton("Start");
		btnStart.setVisible(false);
		btnStart.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				MyFrame.mnRynki.removeAll();
				
				
				for(int i=0;i<Main.getListaRynkow().size();i++) {
					Rynek tmp = Main.getListaRynkow().get(i);
					if(tmp instanceof Gielda) {
					JMenuItem x = new JMenuItem(Main.getListaRynkow().get(i).getNazwa());
					MyFrame.mnRynki.add(x);
					x.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							aktywnyRynek = x.getText();
							System.out.println(aktywnyRynek);
							btnPoka.doClick();
						}
					});
					
					}else if(tmp instanceof RynekWalut){
						JMenuItem x = new JMenuItem(Main.getListaRynkow().get(i).getNazwa());
						MyFrame.mnRynki.add(x);
						x.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								aktywnyRynek = x.getText();
								System.out.println(aktywnyRynek);
								btnPoka.doClick();
							}
						});
						
					}else {
						JMenuItem x = new JMenuItem(Main.getListaRynkow().get(i).getNazwa());
						MyFrame.mnRynki.add(x);
						x.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								aktywnyRynek = x.getText();
								System.out.println(aktywnyRynek);
								btnPoka.doClick();
							}
						});
						
					}
					
				}
				
				
				
			}
		});
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<Main.listaFunduszy.size();i++) {
					Main.listaFunduszy.get(i).setZakonczWatek(true);
				}
				for(int i=0;i<Main.listaInwestorow.size();i++) {
					Main.listaInwestorow.get(i).setZakonczWatek(true);
				}
				for(int i=0;i<Main.listaSpolek.size();i++) {
					Main.listaSpolek.get(i).setZakonczWatek(true);
				}
				Main.setZakonczWatek(true);
			}
		});
		
		JLabel lblAktywa = new JLabel("Aktywa");
		
		JScrollPane scrollPane_5 = new JScrollPane();
		
		JLabel lblIndeksy = new JLabel("Indeksy");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		
		
		JTextArea textArea =  new JTextArea();
		textArea.setAutoscrolls(false);
		textArea.setEditable(false);
		textArea.setFont(new Font("Dialog", Font.BOLD, 14));
		scrollPane_4.setViewportView(textArea);
		
		list = new JList(listaSpolek);
		
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(null);
				int index = list.getSelectedIndex();
				Spolka tmp = listaSpolek.get(index);
				textArea.append("Nazwa: "+tmp.getNazwa()+"\n");
				textArea.append("Data wejscia: "+tmp.getData()+"\n");
				textArea.append("Akt. kurs: "+Float.toString(Main.round(tmp.getAktualnyKurs(), 2))+"\n");
				textArea.append("Kurs otwarcia: "+Float.toString(Main.round(tmp.getKursOtwarcia(), 2))+"\n");
				textArea.append("Min. kurs: "+Float.toString(Main.round(tmp.getMinKurs(), 2))+"\n");
				textArea.append("Max. kurs: "+Float.toString(Main.round(tmp.getMaxKurs(), 2))+"\n");
				textArea.append("Liczba akcji: "+Integer.toString(tmp.getLiczbAkcji())+"\n");
				textArea.append("Zysk: "+Float.toString(Main.round(tmp.getZysk(), 2))+"\n");
				textArea.append("Przychod: "+Float.toString(Main.round(tmp.getPrzychod(), 2))+"\n");
				textArea.append("Kapita³ w³asny: "+Float.toString(Main.round(tmp.getKapitalWlasny(), 2))+"\n");
				textArea.append("Kapita³ zak³adowy: "+Integer.toString(tmp.getKapitalZakladowy())+"\n");
				textArea.append("Wolumen: "+Integer.toString(tmp.getWolumen())+"\n");
				textArea.append("Obroty: "+Float.toString(Main.round(tmp.getObroty(), 2))+"\n");
				stan = Stan.SPOLKA;
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_3.setViewportView(list);
		
		list_1 = new JList(listaFunduszy);
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(null);
				int index = list_1.getSelectedIndex();
				Fundusz tmp = (Fundusz) listaFunduszy.get(index);
				Set<Entry<SkladnikAktywow,Integer>> entrySet = tmp.getLista().entrySet();
				textArea.append("Nazwa: "+tmp.getNazwa()+"\n");
				textArea.append("Wlasciciel: "+tmp.getImie()+" "+tmp.getNazwisko()+"\n");
				textArea.append("Budzet: "+Float.toString(Main.round(tmp.getBudzet(), 2))+"\n");
				for(Entry<SkladnikAktywow, Integer> entry: entrySet) {
		            textArea.append(entry.getKey()+" : "+entry.getValue()+"\n");
		        }
				tmp = null;
				stan = Stan.FUNDUSZ;
			}
		});
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(list_1);
		
		list_2 = new JList(listaInwestorow);
		list_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(null);
				int index = list_2.getSelectedIndex();
				InwestorIndywidualny tmp = (InwestorIndywidualny) listaInwestorow.get(index);
				Set<Entry<SkladnikAktywow,Integer>> entrySet = tmp.getLista().entrySet();
				textArea.append("Imie: "+tmp.getImie()+"\n");
				textArea.append("Nazwisko: "+tmp.getNazwisko()+"\n");
				textArea.append("Pesel: "+tmp.getPesel()+"\n");
				textArea.append("Budzet: "+Float.toString(Main.round(tmp.getBudzet(), 2))+"\n");
				 for(Entry<SkladnikAktywow, Integer> entry: entrySet) {
			            textArea.append(entry.getKey()+" : "+entry.getValue()+"\n");
			        }
				 tmp=null;
				stan = Stan.INWESTOR;
				
			}
		});
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(list_2);
		
		list_3 = new JList(listaAktywow);
		list_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textArea.setText(null);
				int index = list_3.getSelectedIndex();
				SkladnikAktywow tmp = listaAktywow.get(index);
				if(tmp instanceof Akcja) {
					textArea.append("Nazwa: "+tmp.getNazwa()+"\n");
					textArea.append("Wartosc: "+Float.toString(Main.round(tmp.getWartosc(), 2)));
					stan = Stan.AKTYWO;
				}else if(tmp instanceof Surowiec) {
					Surowiec tmp1 = (Surowiec) listaAktywow.get(index);
					textArea.append("Nazwa: "+tmp1.getNazwa()+"\n");
					textArea.append("Wartosc: "+Float.toString(Main.round(tmp1.getWartosc(), 2))+"\n");
					textArea.append("Waluta: "+tmp1.getWaluta().getNazwa()+"\n");
					textArea.append("Jednostka: "+tmp1.getJednostka()+"\n");
					textArea.append("Min. kurs: "+Float.toString(Main.round(tmp1.getMinimalnyKurs(), 2))+"\n");
					textArea.append("Max. kurs: "+Float.toString(Main.round(tmp1.getMaxymalnyKurs(), 2))+"\n");
					tmp1 = null;
					stan = Stan.AKTYWO;
				}else { 	//gdy waluta
					textArea.append("Nazwa: "+tmp.getNazwa()+"\n");
					textArea.append("Wartosc: "+Float.toString(Main.round(tmp.getWartosc(), 2))+"\n");
					textArea.append("Lista krajów: "+"\n");
					for(String kraj: ((Waluta) tmp).getListaKrajow()) {
			            textArea.append(kraj+"\n");
			        }
					stan = Stan.AKTYWO;
				}
				tmp = null;
				
			}
		});
		list_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list_3);
		
		
		list_4 = new JList(listaIndeksow);
		list_4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				synchronized(Main.monitor) {
				textArea.setText(null);
				int index = list_4.getSelectedIndex();
				Indeks tmp  = listaIndeksow.get(index);
				textArea.append("Nazwa: "+tmp.getNazwa()+"\n");
				textArea.append("Notowania: "+Main.round(tmp.getWartosc(), 2)+"\n");
				textArea.append("Spo³ki: \n");
				for(int i=0;i<tmp.getLista().size();i++) {
					for(int j=0;j<Main.listaSpolek.size();j++) {
						if(Main.listaSpolek.get(j).getNazwa().equals(tmp.getLista().get(i).getNazwa())){
							textArea.append(Main.listaSpolek.get(j).getNazwa()+" "+Main.round(Main.listaSpolek.get(j).getAktualnyKurs(), 2)+"\n");
						}
					}
				}
				stan = Stan.INDEKS;
				
				
			}
			}
		});
		scrollPane_5.setViewportView(list_4);
		
		JLabel lblInwestorzy = new JLabel("Inwestorzy");
		
		JButton btnWykupAkcje = new JButton("Wykup akcje");
		btnWykupAkcje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized(Main.monitor) {
				int index = list.getSelectedIndex();
				double kurs = listaSpolek.get(index).getAktualnyKurs();
				double kwota = Double.parseDouble(textField_1.getText());
				int ilosc = (int) (kwota/kurs);
				if(ilosc<=listaSpolek.get(index).getLiczbAkcji()) {
				listaSpolek.get(index).zmniejszLiczbAkcji(ilosc);
				}else {
					JFrame x = new JFrame("BLAD");
					x.setVisible(true);
					x.setBounds(300, 300, 300, 200);
					
					JButton button = new JButton("Za ma³o akcji, ¿eby wykupic za tak¹ kwote \n Kliknij ");
					button.setSize(20,20);
					button.setVisible(true);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							x.setVisible(false);
					}
					
					});
					x.getContentPane().add(button);
					
					
					
				}
				textField_1.setText("");
			}
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblInfo = new JLabel("Info");
		
		JButton btnKontynuuj = new JButton("Kontynuuj");
		btnKontynuuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0;i<Main.listaSpolek.size();i++) {
               	 Main.listaSpolek.get(i).setZakonczWatek(false);
               	 Thread t = new Thread(Main.listaSpolek.get(i));
               	 t.start();
                }
                
                for(int i=0;i<Main.listaInwestorow.size();i++) {
               	 Main.listaInwestorow.get(i).setZakonczWatek(false);
               	 Thread t = new Thread((InwestorIndywidualny)Main.listaInwestorow.get(i));
               	 t.start();
                }
                
                for(int i=0;i<Main.listaFunduszy.size();i++) {
               	 Main.listaFunduszy.get(i).setZakonczWatek(false);
               	 Thread t = new Thread((Fundusz)Main.listaFunduszy.get(i));
               	 t.start();
                }
                Main.setZakonczWatek(false);
                WatekMonitorujacyWartoscAktywowOrazLiczbeInwestorow w = new WatekMonitorujacyWartoscAktywowOrazLiczbeInwestorow();
                Thread w1 = new Thread(w);
                w1.start();
			}
		});
		
		JScrollPane scrollPane_6 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(41)
											.addComponent(btnNewButton)
											.addGap(6)
											.addComponent(btnPoka))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblInwestorzy, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)))
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(10)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblFundusze)
														.addGap(43)
														.addComponent(btnStop)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnKontynuuj)
														.addGap(21))
													.addGroup(groupLayout.createSequentialGroup()
														.addGap(65)
														.addComponent(lblSpki)
														.addGap(27)))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(scrollPane_6, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
													.addPreferredGap(ComponentPlacement.RELATED))))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnWykupAkcje))
											.addGap(18)))
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblAktywa)
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
											.addGap(15)
											.addComponent(lblIndeksy))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnStart)
											.addGap(21))))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
									.addGap(47)
									.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
									.addGap(25)
									.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)))
					.addGap(86))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInfo)
					.addContainerGap(854, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(251)
					.addComponent(btnUsu)
					.addContainerGap(576, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(lblInfo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(87)
									.addComponent(btnUsu)
									.addGap(122)
									.addComponent(lblFundusze))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(7)
									.addComponent(lblAktywa)
									.addGap(3)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(232)
									.addComponent(lblIndeksy))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(105)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnStop)
										.addComponent(btnKontynuuj))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnWykupAkcje)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblSpki)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(btnPoka))
							.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
							.addComponent(lblInwestorzy)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnStart))
					.addContainerGap(398, Short.MAX_VALUE))
		);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setAutoscrolls(false);
		textArea_1.setFont(new Font("Dialog", Font.BOLD, 14));
		scrollPane_6.setViewportView(textArea_1);
		getContentPane().setLayout(groupLayout);
		
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnRynki = new JMenu("Rynki");
		
		menuBar.add(mnRynki);
		
		JMenu mnZapisz = new JMenu("Zapisz/Wczytaj");
		
		menuBar.add(mnZapisz);
		
		JMenuItem mntmZapisz = new JMenuItem("Zapisz");
		mntmZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 ObjectOutputStream rynkiOut = null;
                 try {
                     rynkiOut = new ObjectOutputStream(new FileOutputStream("rynek.save"));
                     rynkiOut.writeObject(Main.getListaRynkow());
                     rynkiOut.close();
                 } catch (IOException ex) {
                    System.out.println("blad zapisu "+ ex.getMessage());
                 }
                 
                 ObjectOutputStream aktywaOut = null;
                 try {
                     aktywaOut = new ObjectOutputStream(new FileOutputStream("aktywa.save"));
                     aktywaOut.writeObject(Main.getListaAktywow());
                     aktywaOut.close();
                 } catch (IOException ex) {
                	 System.out.println("blad zapisu "+ ex.getMessage());           
                }
                 
                 ObjectOutputStream spolkiOut = null;
                 try {
                     spolkiOut = new ObjectOutputStream(new FileOutputStream("spolka.save"));
                     spolkiOut.writeObject(Main.getListaSpolek());
                     spolkiOut.close();
                 } catch (IOException ex) {
                	 System.out.println("blad zapisu "+ ex.getMessage());
                 }
                 
                 ObjectOutputStream inwestorzyOut = null;
                 try {
                     inwestorzyOut = new ObjectOutputStream(new FileOutputStream("inwestor.save"));
                     inwestorzyOut.writeObject(Main.getListaInwestorow());
                     inwestorzyOut.close();
                 } catch (IOException ex) {
                	 System.out.println("blad zapisu "+ ex.getMessage());
                 }
                 
                 ObjectOutputStream funduszeOut = null;
                 try {
                     funduszeOut = new ObjectOutputStream(new FileOutputStream("fundusz.save"));
                     funduszeOut.writeObject(Main.getListaFunduszy());
                     funduszeOut.close();
                 } catch (IOException ex) {
                	 System.out.println("blad zapisu "+ ex.getMessage());
                 }
                 
                 ObjectOutputStream akcjeOut = null;
                 try {
                     akcjeOut = new ObjectOutputStream(new FileOutputStream("akcja.save"));
                     akcjeOut.writeObject(Main.listaAkcji);
                     akcjeOut.close();
                 } catch (IOException ex) {
                	 System.out.println("blad zapisu "+ ex.getMessage());
                 }
                 
                 ObjectOutputStream indeksyOut = null;
                 try {
                     indeksyOut = new ObjectOutputStream(new FileOutputStream("indeks.save"));
                     indeksyOut.writeObject(Main.listaIndeksow);
                     indeksyOut.close();
                 } catch (IOException ex) {
                	 System.out.println("blad zapisu "+ ex.getMessage());
                 }
                 
                 
                
			}
		});
		mnZapisz.add(mntmZapisz);
		
		JMenuItem mntmWczytaj = new JMenuItem("Wczytaj");
		mntmWczytaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ObjectInputStream rynkiIn = null;
                 try {
                     rynkiIn = new ObjectInputStream(new FileInputStream("rynek.save"));
                     Main.getListaRynkow().clear();
                     Main.listaRynkow = (List) rynkiIn.readObject();
                     rynkiIn.close();
                 } catch (IOException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 ObjectInputStream aktywaIn = null;
                 try {
                     aktywaIn = new ObjectInputStream(new FileInputStream("aktywa.save"));
                     Main.getListaAktywow().clear();
                     Main.listaAktywow = (List) aktywaIn.readObject();
                     aktywaIn.close();
                 } catch (IOException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 ObjectInputStream spolkiIn = null;
                 try {
                     spolkiIn = new ObjectInputStream(new FileInputStream("spolka.save"));
                     Main.listaSpolek.clear();
                     Main.listaSpolek = (List) spolkiIn.readObject();
                     spolkiIn.close();
                 } catch (IOException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 ObjectInputStream inwestorzyIn = null;
                 try {
                     inwestorzyIn = new ObjectInputStream(new FileInputStream("inwestor.save"));
                     Main.listaInwestorow.clear();
                     Main.listaInwestorow = (List) inwestorzyIn.readObject();
                     inwestorzyIn.close();
                 } catch (IOException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 ObjectInputStream funduszeIn = null;
                 try {
                     funduszeIn = new ObjectInputStream(new FileInputStream("fundusz.save"));
                     Main.listaFunduszy.clear();
                     Main.listaFunduszy = (List) funduszeIn.readObject();
                     funduszeIn.close();
                 } catch (IOException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 ObjectInputStream akcjeIn = null;
                 try {
                     akcjeIn = new ObjectInputStream(new FileInputStream("akcja.save"));
                     Main.listaAkcji.clear();
                     Main.listaAkcji = (List) akcjeIn.readObject();
                     akcjeIn.close();
                 } catch (IOException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 ObjectInputStream indeksyIn = null;
                 try {
                     indeksyIn = new ObjectInputStream(new FileInputStream("indeks.save"));
                     Main.listaIndeksow.clear();
                     Main.listaIndeksow = (List) indeksyIn.readObject();
                     indeksyIn.close();
                 } catch (IOException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
                 btnStart.doClick();
                 aktywnyRynek = "Gie³da";
                 btnPoka.doClick();
                 
                 
                 for(int i=0;i<Main.listaSpolek.size();i++) {
                	 Main.listaSpolek.get(i).setZakonczWatek(false);
                	 Thread t = new Thread(Main.listaSpolek.get(i));
                	 t.start();
                 }
                 
                 for(int i=0;i<Main.listaInwestorow.size();i++) {
                	 Main.listaInwestorow.get(i).setZakonczWatek(false);
                	 Thread t = new Thread((InwestorIndywidualny)Main.listaInwestorow.get(i));
                	 t.start();
                 }
                 
                 for(int i=0;i<Main.listaFunduszy.size();i++) {
                	 Main.listaFunduszy.get(i).setZakonczWatek(false);
                	 Thread t = new Thread((Fundusz)Main.listaFunduszy.get(i));
                	 t.start();
                 }
                 Main.setZakonczWatek(false);
                 WatekMonitorujacyWartoscAktywowOrazLiczbeInwestorow w = new WatekMonitorujacyWartoscAktywowOrazLiczbeInwestorow();
                 Thread w1 = new Thread(w);
                 w1.start();
                 
                 
			}
		});
		mnZapisz.add(mntmWczytaj);
		setVisible(true);
	}


	public String getAktywnyRynek() {
		return aktywnyRynek;
	}


	public void setAktywnyRynek(String aktywnyRynek) {
		this.aktywnyRynek = aktywnyRynek;
	}
}
