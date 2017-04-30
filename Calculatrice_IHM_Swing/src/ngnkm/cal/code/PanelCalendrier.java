package ngnkm.cal.code;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

/**
 * 
 * @author ngankam franck
 * @version java 1.7 minimum
 */

@SuppressWarnings("serial")
public class PanelCalendrier extends JPanel{
	
	int jour, mois, annee, dom;
	Calendar calendrier;
	
	//Constructeur
	/**
	 * 
	 * @param jour
	 * @param mois
	 * @param annee
	 */
	public PanelCalendrier(int jour, int mois, int annee){
		
		//peindre ce composant
		repaint();
		
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
		
	}
	
	public static final String[] jours = {"Lundi", "Mardi", "Mercredi", "  Jeudi", "Vendredi", "Samedi", "Dimache"};
	
	public static int DernierJour[] = {
									31, 28, 31, 30, /* Jan, fev, mars, avr*/
									31, 30, 31, 31, /*mai, juin, juil, aout*/
									30, 31, 30, 31, /*sep, oct, nov, dec */
									};
	
	private static final int TAILLE_STRING = 60;

    // personnalisation du graphique
	public void paintComponent(Graphics g){
		
		g.setColor(Color.WHITE);

		//rempli le carre
		g.fillRect(0, 0, 500, 500);
		
		/*recuperer le premier jour du mois ex: lundi, mardi, ...)*/
		int premierJourDuMois = new GregorianCalendar(annee, mois, 0).get(Calendar.DAY_OF_WEEK) - 1;
		
		int ecartX = 30;
		int ecartY = 50;

		//ajuster le panneau
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 40);
		
		//Annee bissextile & mois de fevrier
		if(annee% 4 == 0)
			DernierJour[1] = 29;
		else
			DernierJour[2] = 28;
		
		for(int i= 0; i<jours.length; i++){
			g.setColor(Color.WHITE);
			g.drawString(jours[i], i* TAILLE_STRING + ecartX, 25);	
			
		}
		
		int countJour = 1;
		g.setColor(Color.black);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < jours.length; j++) {

				if ((j >= premierJourDuMois || i > 0) && countJour <= DernierJour[mois])
					if (countJour == jour) {// si c'est le jour du mois
						if (jour < 10)// ajuste le carre noir lorsque le nombre est inferieur a 10
							g.fillRect(j * TAILLE_STRING + ecartX - 2, i * TAILLE_STRING + ecartY, 20, 30);
						else
							g.fillRect(j * TAILLE_STRING + ecartX - 2, i * TAILLE_STRING + ecartY, 30, 30);
						
						g.setColor(Color.white);
						g.drawString("" + countJour++, j * TAILLE_STRING + ecartX + 5, i * TAILLE_STRING + ecartY + 20);
					} else {// si c'est le jour normal
						g.setColor(Color.black);
						g.drawString("" + countJour++, j * TAILLE_STRING + ecartX + 5, i * TAILLE_STRING + ecartY + 20);

					}
			}
		}
		
	}
	
	

}
