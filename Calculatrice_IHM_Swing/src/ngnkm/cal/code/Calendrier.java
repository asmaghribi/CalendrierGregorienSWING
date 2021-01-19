package ngnkm.cal.code;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * 
 * @author ngnkm
 *
 */
public class Calendrier implements ActionListener {
	
	/**
	 * Initialiser les variables de la classe
	 */
	public static final String[] listeMois = {"Janvier", "Fevrier", "Mars", 
										      "Avril", "Mai", "Juin", 
										      "Juillet", "Aout","Septembre",
										      "Octobre","Novembre","Decembre"};
	
	public static int nombreDeJour[] = {
									31, 28, 31, 30, /* Jan, fev, mars, avr*/
									31, 30, 31, 31, /*mai, juin, juil, aout*/
									30, 31, 30, 31, /*sep, oct, nov, dec */
									};
	
	//annee de debut et de fin du calendrier
	private int anneeInit = 1900;
	private int anneeFin = 2200;
	
	/**
	 * Declaration des attributs
	 */
	private JFrame frame;
	private PanelCalendrier calPane;
	
	@SuppressWarnings("rawtypes")
	private JComboBox moisCbx, anneeCbx, jourCbx;
	private JButton dateAjourdhui;
	private JButton nextMonth;
	private JButton lastMonth;
	private JButton evennement;
	/**
	 * Lancement de l'application
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Le traitement complet de Swing se fait dans un thread appel� EDT (Event Dispatching Thread). Par cons�quent, bloquer l'interface graphique si on souhaite calculer des calculs durables dans ce fil.
         * La fa�on d'y aller ici est de traiter notre calcul dans un fil diff�rent, de sorte que votre GUI reste sensible. � la fin, on met � jour votre interface graphique, ce qui doit �tre effectu� dans l'EDT. Now EventQueue.invokeLater entre en jeu. on affiche un �v�nement (votre Runnable) � la fin de la liste des �v�nements Swings et apr�s les �v�nements GUI pr�c�dents sont trait�s.
         */
		
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					Calendrier window = new Calendrier();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calendrier() {
		initialize();
	}

	public void actionPerformed(ActionEvent evt) {
		frame.getContentPane().repaint();
		frame.remove(calPane);
		int moisSelected = moisCbx.getSelectedIndex();
		int anneeSelected = anneeCbx.getSelectedIndex() + anneeInit;
		int jourSelected = jourCbx.getSelectedIndex() + 1;

		// Construction du panneau calendrier
		calPane = new PanelCalendrier(jourSelected, moisSelected, anneeSelected);
		frame.getContentPane().add(calPane);
		calPane.setBounds(10, 55, 452, 382);
		
		if (evt.getSource() == moisCbx || evt.getSource() == anneeCbx) {
			jourCbx.removeAllItems();
			metjour(anneeSelected, moisSelected);
			jourCbx.setSelectedItem(jourSelected);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		System.out.println("init");
		Calendar calendar = new GregorianCalendar();

		final int moisCourant = calendar.get(Calendar.MONTH);
		final int jourCourant = calendar.get(Calendar.DAY_OF_MONTH);
		final int anneeCourant = calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);

		//Configuration du Frame
		frame = new JFrame("Calendrier Gregorien");
		frame.setBounds(100, 100, 490, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.RED);

		//initalisation des controles
		anneeCbx = new JComboBox();
		anneeCbx.setBackground(Color.WHITE);
		for (int i = anneeInit; i <= anneeFin; i++) {
			anneeCbx.addItem(i);
		}
		anneeCbx.setSelectedItem(anneeCourant);
		anneeCbx.addActionListener(this);
		anneeCbx.setBounds(322, 24, 104, 20);
		frame.getContentPane().add(anneeCbx);

		moisCbx = new JComboBox(listeMois);
		moisCbx.setBackground(Color.WHITE);
		moisCbx.setSelectedItem(listeMois[moisCourant]);
		moisCbx.addActionListener(this);
		moisCbx.setBounds(182, 24, 104, 20);
		frame.getContentPane().add(moisCbx);

		jourCbx = new JComboBox();
		jourCbx.setForeground(Color.BLACK);
		jourCbx.setBackground(Color.WHITE);
		jourCbx.setSelectedItem(jourCourant);
		jourCbx.setBounds(46, 24, 104, 20);

		metjour(anneeCourant, moisCourant);  //ajoute les jours dans la liste jourCbx
		jourCbx.setSelectedIndex(jourCourant - 1);
		jourCbx.addActionListener(this);
		frame.getContentPane().add(jourCbx);
		
		calPane = new PanelCalendrier(jourCourant, moisCourant, anneeCourant);
		calPane.setBackground(Color.WHITE);
		calPane.setBounds(10, 55, 452, 382);
		frame.getContentPane().add(calPane);
		
		
		dateAjourdhui = new JButton("Aujourd'hui");
		dateAjourdhui.setBackground(Color.WHITE);
		dateAjourdhui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().repaint();
				frame.remove(calPane);
				calPane = new PanelCalendrier(jourCourant, moisCourant, anneeCourant);
				calPane.setBounds(10, 55, 50, 50);
				jourCbx.setSelectedItem(jourCourant);
				moisCbx.setSelectedItem(listeMois[moisCourant]);
				anneeCbx.setSelectedItem(anneeCourant);
				frame.getContentPane().add(calPane);
			}
		});
		//positionnment du bouton
		dateAjourdhui.setBounds(175, 445, 127, 30);
		frame.getContentPane().add(dateAjourdhui);
		
		
		nextMonth = new JButton("Next Month");
		nextMonth.setBackground(Color.blue);
		nextMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().repaint();
				frame.remove(calPane);
				calPane = new PanelCalendrier(jourCourant, moisCourant, anneeCourant);
				calPane.setBounds(10, 55, 50, 50);
				jourCbx.setSelectedItem(jourCourant);
				if (moisCourant == 11) {
				moisCbx.setSelectedItem(listeMois[0]);
				anneeCbx.setSelectedItem(anneeCourant + 1);
				}
				else {
					moisCbx.setSelectedItem(listeMois[moisCourant + 1]);
					anneeCbx.setSelectedItem(anneeCourant);
				}
				frame.getContentPane().add(calPane);
			}
		});
		//positionnment du bouton
		nextMonth.setBounds(300, 445, 127, 30);
		frame.getContentPane().add(nextMonth);
		
		
		lastMonth = new JButton("Last Month");
		lastMonth.setBackground(Color.green);
		lastMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().repaint();
				frame.remove(calPane);
				calPane = new PanelCalendrier(jourCourant, moisCourant, anneeCourant);
				calPane.setBounds(10, 55, 50, 50);
				jourCbx.setSelectedItem(jourCourant);
				if (moisCourant == 0) {
					moisCbx.setSelectedItem(listeMois[11]);
					anneeCbx.setSelectedItem(anneeCourant - 1);}
					else {
						moisCbx.setSelectedItem(listeMois[moisCourant - 1]);
						anneeCbx.setSelectedItem(anneeCourant);
					}
				frame.getContentPane().add(calPane);
			}
		});
		//positionnment du bouton
		lastMonth.setBounds(50, 445, 127, 30);
		frame.getContentPane().add(lastMonth);
		
	}
	
	// mettre anneeSelected jour et distinction des annees bissextiles
	@SuppressWarnings("unchecked")
	public void metjour(int anneeSelected, int moisSelected) {
		if (anneeSelected % 4 == 0)
			nombreDeJour[1] = 29;
		else
			nombreDeJour[1] = 28;
		for (int i = 1; i <= nombreDeJour[moisSelected]; i++) {
			jourCbx.addItem(i);

		}
		  evennement = new JButton("Evennements");
			evennement.setBackground(Color.black);
			evennement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					frame.getContentPane().repaint();
				     frame.add(evennement);
					calPane.setBounds(10, 55, 452, 382);
					frame.getContentPane().add(calPane);
					
				evennements ev = new evennements();
				ev.setVisible(true);
					
				
			}

			});
			evennement.setBounds(175, 500, 127, 30);
			frame.getContentPane().add(evennement);
			}}
