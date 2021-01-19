package ngnkm.cal.code;
import java.sql.*;

import java.util.TimeZone;
public class BDevent {
	
	 public static void main(String[] args)
	  {
	    try
	    {
	      // create a mysql database connection
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/event?serverTimezone=" + TimeZone.getDefault().getID();
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "0000");
	    
	      // create a sql date object so we can use it in our INSERT statement
	      

	      // the mysql insert statement
	      String query = " INSERT INTO gestionevents (idevent, titre,date_de_debut,date_de_fin)"
	        + " VALUES (?, ?, ?, ?)";

	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	     
	     

	      // execute the preparedstatement
	      preparedStmt.execute();
	      
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	  }
	}

