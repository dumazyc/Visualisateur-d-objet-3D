package sqlite;
import java.sql.*;

public class Insert
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
      String sql = "INSERT INTO OBJETS3D (NAME,AUTEUR,FORME,UTILISATION,VOLUME,DATECREATION,COMPLEXITE,LIEN) " +
               "VALUES ('tie', 'Clement', 'tie', 'Mode', 'En attente', '2014-11-28', 0, 'En attente' );"; 
      stmt.executeUpdate(sql);

      stmt.close(); 
      c.commit();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Records created successfully");
  }
}