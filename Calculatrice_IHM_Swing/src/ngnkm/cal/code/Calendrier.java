package ngnkm.cal.code;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;


import com.sun.java.swing.plaf.motif.MotifBorders.BevelBorder;

/**
 * 
 * @author ngnkm
 *
 */
public class Calendrier implements ActionListener {
	
	/**
	 * Initialiser les variables de la classe
	 */
	public static final String[] listeMois = {"Januray", "February", "March", 
		      "April", "May", "Jun", 
		      "July", "August","September",
		      "October","November","December"};

	
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
	private JLabel label1 = new JLabel(" ");
	/**
	 * Lancement de l'application
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Le traitement complet de Swing se fait dans un thread appelé EDT (Event Dispatching Thread). Par conséquent, bloquer l'interface graphique si on souhaite calculer des calculs durables dans ce fil.
         * La façon d'y aller ici est de traiter notre calcul dans un fil différent, de sorte que votre GUI reste sensible. À la fin, on met à jour votre interface graphique, ce qui doit être effectué dans l'EDT. Now EventQueue.invokeLater entre en jeu. on affiche un événement (votre Runnable) à la fin de la liste des événements Swings et après les événements GUI précédents sont traités.
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
	/**
	 * Creationn de la méthode Jourferie qui permet de distinguer 
	 * les jours fériés
	 */
    public void JourFerie(String x,String y)
    {
    	if (x.equals("1")&&(y.equals("Janvier"))){label1.setText("New Year's Day");}
		else if (x.equals("14")&&(y.equals("Janvier"))){label1.setText("Revolution and Youth Day");}
		else if (x.equals("20")&&(y.equals("Mars"))){label1.setText("Independence Day");}
		else if (x.equals("9")&&(y.equals("Avril"))){label1.setText("Martyrs Day");}
		else if (x.equals("1")&&(y.equals("Mai"))){label1.setText("Labor Day");}
		else if (x.equals("25")&&(y.equals("Juillet"))){label1.setText("Republic Day");}
		else if (x.equals("10")&&(y.equals("Aout"))){label1.setText("Hegira New Year's Day");}
		else if (x.equals("13")&&(y.equals("Aout"))){label1.setText("Woman's Day");}
		else if (x.equals("15")&&(y.equals("Octobre"))){label1.setText("Evacuation party");}
		else if (x.equals("19")&&(y.equals("Octobre"))){label1.setText("Mouled");}
		else{label1.setText("No Event");}
    }
	public void actionPerformed(ActionEvent evt) {
		frame.getContentPane().repaint();
		frame.remove(calPane);
		int moisSelected = moisCbx.getSelectedIndex();
		int anneeSelected = anneeCbx.getSelectedIndex() + anneeInit;
		int jourSelected = jourCbx.getSelectedIndex() + 1;
		String x = String.valueOf(jourCbx.getSelectedItem());
		String y = String.valueOf(moisCbx.getSelectedItem());

		// Construction du panneau calendrier
		calPane = new PanelCalendrier(jourSelected, moisSelected, anneeSelected);
		frame.getContentPane().add(calPane);
		calPane.setBounds(10, 55, 452, 382);
		
		//Appel de la methode JourFerie
		JourFerie(x,y);
		
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
		frame = new JFrame("Gregorien Calendar");
		frame.setBounds(100, 100, 486, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\HP\\Desktop\background.jpg"));
		
		frame.setContentPane(lblNewLabel);

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
		moisCbx.setBounds(46, 24, 104, 20);
		frame.getContentPane().add(moisCbx);

		jourCbx = new JComboBox();
		jourCbx.setForeground(Color.BLACK);
		jourCbx.setBackground(Color.WHITE);
		jourCbx.setSelectedItem(jourCourant);
		jourCbx.setBounds(182, 24, 104, 20);

		metjour(anneeCourant, moisCourant);  //ajoute les jours dans la liste jourCbx
		jourCbx.setSelectedIndex(jourCourant - 1);
		jourCbx.addActionListener(this);
		frame.getContentPane().add(jourCbx);
		
		calPane = new PanelCalendrier(jourCourant, moisCourant, anneeCourant);
		calPane.setBackground(Color.WHITE);
		calPane.setBounds(10, 55, 452, 382);
		frame.getContentPane().add(calPane);
		
		
		dateAjourdhui = new JButton("Today");
		dateAjourdhui.setSize(new Dimension(400, 500));
		dateAjourdhui.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		//dateAjourdhui.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		dateAjourdhui.setBackground(Color.orange);
		dateAjourdhui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().repaint();
				frame.remove(calPane);
				calPane = new PanelCalendrier(jourCourant, moisCourant, anneeCourant);
				calPane.setBounds(10, 55, 452, 382);
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
		
		
		label1.setFont(new Font("Serif", Font.BOLD, 16));
		label1.setForeground(Color.magenta);
		
		//positionnment du text du jour ferie
		label1.setBounds(90, 0, 300, 20);
		label1.setHorizontalAlignment(JLabel.CENTER);
	
		String y=listeMois[moisCourant];
		String x=String.valueOf(jourCourant);
		JourFerie(x,y);
		frame.add(label1);
		
		
		
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
