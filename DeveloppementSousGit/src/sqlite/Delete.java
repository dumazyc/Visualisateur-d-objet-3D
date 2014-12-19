package sqlite;

import java.sql.*;

public class Delete {

	  public static void main( String args[] )
	  {
	    Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:Database.db");
	      c.setAutoCommit(false);

	      stmt = c.createStatement();
	      String sql = "DELETE from OBJETS3D where NAME='az';";
	      //String sql = "DROP TABLE MOTSCLES;";
	      stmt.executeUpdate(sql);
	      c.commit();

	      ResultSet rs = stmt.executeQuery( "SELECT * FROM OBJETS3D;" );
	      while ( rs.next() ) {
	    	  String  name = rs.getString("name");
	          System.out.print(name + " ");
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println();
	    System.out.println();
	    System.out.println("Suppression terminée");
	  }
	}