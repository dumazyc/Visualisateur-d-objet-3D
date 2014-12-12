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

      stmt = c.createStatement();
      String sql = "INSERT INTO OBJETS3D (NAME,AUTEUR,COMPLEXITE,LIEN) " +
               "VALUES ('tie', 'clement', 0, 'En attente' );"; 
      stmt.executeUpdate(sql);

      stmt.close(); 
      c.commit();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Objet créé avec succès");
  }
}
