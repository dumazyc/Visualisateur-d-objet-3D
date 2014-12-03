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
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM OBJETS3D;" );
      while ( rs.next() ) {
         int id = rs.getInt("id");
         String  name = rs.getString("name");
         String forme  = rs.getString("forme");
         String utilisation  = rs.getString("utilisation");
         String volume  = rs.getString("volume");
         String datecreation  = rs.getString("datecreation");
         String complexite  = rs.getString("complexite");
         String lien  = rs.getString("lien");
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "FORME = " + forme );
         System.out.println( "UTILISATION = " + utilisation );
         System.out.println( "VOLUME = " + volume );
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
    System.out.println("Operation done successfully");
  }
}
