package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
/** Klasa modeluj¹ca okno tworzenia Inwestorów/Gie³d/Spó³ek/Walut/Surowców/Indeksów oraz tworzaca te sk³adniki
 * 
 * @author Patryk
 *
 */
public class Tworzenie extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList list;
	private JList list_1;
	public static DefaultListModel<Spolka> listaSpolek = new DefaultListModel<>();
	Random r = new Random();

	/**Konstruktor klasy
	 * 
	 */
	public Tworzenie() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Gie\u0142da");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				for(i=0;i<Main.getListaAktywow().size();i++) {
					if(Main.getListaAktywow().get(i) instanceof Waluta) {
						i++;
					}
				}
				int[] tablicaIndeksow = new int[i];
				int j=0;
				for(i=0;i<Main.getListaAktywow().size();i++) {
					if(Main.getListaAktywow().get(i) instanceof Waluta) {
						tablicaIndeksow[j]=i;
						j++;
					}
				}
				int m = r.nextInt(tablicaIndeksow.length);
				Gielda x = new Gielda(GeneratorStringow.generujStringa(5),(Waluta)Main.getListaAktywow().get(tablicaIndeksow[m]));
				MyFrame.btnStart.doClick();
				MyFrame.btnPoka.doClick();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Waluta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Waluta x = new Waluta(GeneratorStringow.generujStringa(3));
				MyFrame.btnPoka.doClick();  
			}
		});
		
		JButton btnNewButton_2 = new JButton("Indeks");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] tab = new int[Main.listaSpolek.size()];
				tab = list_1.getSelectedIndices();
				Indeks x = new Indeks();
				for(int i=0;i<tab.length;i++) {
				x.getLista().add(Main.listaSpolek.get(tab[i]));
				}
				x.setWartosc();
				MyFrame.btnPoka.doClick();
			}
		});
		
		JButton btnNewButton_3 = new JButton("Surowiec");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Surowiec x = new Surowiec(GeneratorStringow.generujStringa(5));
				MyFrame.btnPoka.doClick();
				
			}
		});
		
		JButton btnSpka = new JButton("Sp\u00F3\u0142ka");
		btnSpka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				Spolka x = new Spolka(GeneratorStringow.generujStringa(5),(Gielda) Main.getListaRynkow().get(index));
				MyFrame.btnPoka.doClick();
				Thread s = new Thread(x); s.setName(x.getNazwa()); 
				s.start(); 
			}
		});
		
		JButton btnInwestor = new JButton("Inwestor");
		btnInwestor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InwestorIndywidualny x = new InwestorIndywidualny();
				MyFrame.btnPoka.doClick();
				Thread t = new Thread(x); t.setName(x.getPesel()); 
				t.start(); 
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnFundusz = new JButton("Fundusz");
		btnFundusz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fundusz x = new Fundusz(GeneratorStringow.generujStringa(5));
				MyFrame.btnPoka.doClick();
				Thread f = new Thread(x); f.setName(x.getNazwa()); 
				f.start();
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton_3)
								.addComponent(btnNewButton)
								.addComponent(btnFundusz))
							.addGap(68)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
							.addGap(24))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnInwestor)
							.addGap(119)
							.addComponent(btnSpka)
							.addPreferredGap(ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
							.addComponent(btnNewButton_2)
							.addGap(97))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFundusz)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnInwestor))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnNewButton_2))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnSpka)))))
					.addContainerGap())
		);
		
		list_1 = new JList(listaSpolek);
		scrollPane_1.setViewportView(list_1);
		
		list = new JList(MyFrame.listaRynkow);
		scrollPane.setViewportView(list);
		contentPane.setLayout(gl_contentPane);
	}
}
