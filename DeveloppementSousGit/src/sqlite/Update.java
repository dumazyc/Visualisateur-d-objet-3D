package sqlite;

import java.sql.*;

/*Classe pour mettre à jour une table*/

public class Update
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Database.db");
      c.setAutoCommit(false);

      stmt = c.createStatement();
     // String modif = "tie";
     // String sql = "UPDATE OBJETS3D set COMPLEXITE = 0 where NAME='"+modif+"';";
     //String sql = "UPDATE MOTSCLES SET ID=17 WHERE NAME ='HarryPotter';";
      //String sql ="ALTER TABLE OBJETS3D AUTOINCREMENT=16;";
     String sql ="ALTER TABLE OBJETS3D MODIFY COLUMN ID auto_increment INT;";
      stmt.executeUpdate(sql);
      c.commit();

      ResultSet rs = stmt.executeQuery( "SELECT * FROM OBJETS3D;" );
 
      while (rs.next()) {
    	 String  name = rs.getString("name");
         String  auteur = rs.getString("auteur");
         String datecreation  = rs.getString("datecreation");
         String complexite  = rs.getString("complexite");
         String lien  = rs.getString("lien");
        // if (modif.equals(name)) {
	         System.out.println( "NAME = " + name );
	         System.out.println( "AUTEUR = " + auteur );
	         System.out.println( "DATECREATION = " + datecreation );
	         System.out.println( "COMPLEXITE = " + complexite );
	         System.out.println( "LIEN = " + lien );
	         System.out.println();
        // }
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
  }
}