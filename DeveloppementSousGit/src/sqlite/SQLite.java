package sqlite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLite
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Database.db");
      System.out.println("Opened database successfully");
      //supprimer les parties liens des objets le lien n'ai pas encore au point a cause des tests sur le fichier charge qui manquent 

      stmt = c.createStatement();
      String sql = "CREATE TABLE OBJETS3D " +
                   "(ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
                   " NAME           TEXT    NOT NULL, " + 
                   " AUTEUR         TEXT    NOT NULL, " + 
                   " DATECREATION   DATE    DEFAULT CURRENT_DATE, " +
                   " COMPLEXITE     INT    	NOT NULL," +
                   " LIEN 			TEXT 	NOT NULL)"; 
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
  }
}