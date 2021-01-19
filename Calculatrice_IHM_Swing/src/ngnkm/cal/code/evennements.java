package ngnkm.cal.code;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import java.sql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;

import java.awt.Label;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.TimeZone;
import java.awt.event.ActionEvent;


public class evennements extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn=null;
	PreparedStatement ps;
	                ResultSet rs;
	private JPanel contentPane;
	private JTextField txtAjouterUnTitre;
	private JTextField txtAjouterUnId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					evennements frame = new evennements();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public evennements() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 524, 417);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtAjouterUnTitre = new JTextField();
		txtAjouterUnTitre.setBounds(87, 58, 313, 43);
		txtAjouterUnTitre.setText("Ajouter un titre");
		txtAjouterUnTitre.setForeground(Color.LIGHT_GRAY);
		txtAjouterUnTitre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(txtAjouterUnTitre);
		txtAjouterUnTitre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ajouter un titre :");
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		lblNewLabel.setBounds(87, 24, 194, 26);
		panel.add(lblNewLabel);
		
		 final JDateChooser dateChooser = new JDateChooser();
		 dateChooser.setDateFormatString("dd MMM yyyy");
		dateChooser.setBounds(87, 205, 313, 37);
		panel.add(dateChooser);
		
		Label label = new Label("Date de debut :");
		label.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		label.setBounds(87, 173, 135, 26);
		panel.add(label);
		
		Label label_1 = new Label("Date de fin :");
		label_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		label_1.setBounds(87, 248, 135, 26);
		panel.add(label_1);
		
		 final JDateChooser dateChooser_1 = new JDateChooser();
		 dateChooser_1.setDateFormatString("dd MMM yyyy");
		dateChooser_1.setBounds(87, 280, 313, 37);
		panel.add(dateChooser_1);
		
		Button button = new Button("Cr\u00E9er");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				 try
				    {
					 // create a mysql database connection
				      String myDriver = "com.mysql.jdbc.Driver";
				      String myUrl = "jdbc:mysql://localhost:3306/event?serverTimezone=" + TimeZone.getDefault().getID();
				      Class.forName(myDriver);
				      Connection conn = (Connection) DriverManager.getConnection(myUrl, "root", "0000");
				    
				      // create a sql date object so we can use it in our INSERT statement
				    

				      // the mysql insert statement
				      String query = " INSERT INTO gestionevents (idevent, titre,date_de_debut ,date_de_fin )"
				        + " VALUES (?, ?, ?, ?)";

				      // create the mysql insert preparedstatement
				      PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
				      preparedStmt.setString (1,txtAjouterUnId.getText() );
				     
				      preparedStmt.setString (2, txtAjouterUnTitre.getText());
				      preparedStmt.setString(3,dateChooser.getDateFormatString());
				      preparedStmt.setString(4,dateChooser_1.getDateFormatString());

				      // execute the preparedstatement
				      preparedStmt.execute();
				      
				      conn.close();
				    }
				    catch (Exception e)
				    {
				      System.err.println("Got an exception!");
				      System.err.println(e.getMessage());
				    }
			}});

		button.setFont(new Font("Yu Gothic Medium", Font.BOLD, 22));
		button.setBounds(187, 335, 114, 43);
		panel.add(button);
		
		txtAjouterUnId = new JTextField();
		txtAjouterUnId.setText("Ajouter un id");
		txtAjouterUnId.setForeground(Color.LIGHT_GRAY);
		txtAjouterUnId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAjouterUnId.setColumns(10);
		txtAjouterUnId.setBounds(87, 112, 313, 43);
		panel.add(txtAjouterUnId);
	}
		}


