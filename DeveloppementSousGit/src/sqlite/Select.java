package sqlite;
import java.sql.*;

public class Select
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
      ResultSet rs = stmt.executeQuery( "SELECT * FROM OBJETS3D;" );
      //ResultSet rs = stmt.executeQuery( "SELECT * FROM OBJETS3D INNER JOIN MOTSCLES ON OBJETS3D.ID = MOTSCLES.ID_M;" );
      while ( rs.next() ) {
    	 int id = rs.getInt("id");
         String  name = rs.getString("name");
         String  auteur = rs.getString("auteur");
         String datecreation  = rs.getString("datecreation");
         String complexite  = rs.getString("complexite");
         String lien  = rs.getString("lien");
       //  String  motcle = rs.getString("motcle");
        // System.out.println( "MOTCLE = " + motcle );
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "AUTEUR = " + auteur );
         System.out.println( "DATECREATION = " + datecreation );
         System.out.println( "COMPLEXITE = " + complexite );
         System.out.println( "LIEN = " + lien );
         
         System.out.println();
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
