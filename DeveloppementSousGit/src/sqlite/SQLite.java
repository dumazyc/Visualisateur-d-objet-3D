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

      stmt = c.createStatement();
      String sql = "CREATE TABLE OBJETS3D " +
                   "(ID serial PRIMARY KEY     NOT NULL," +
                   " NAME           TEXT    NOT NULL, " + 
                   " AUTEUR           TEXT    NOT NULL, " + 
                   " FORME          TEXT    NOT NULL, " + 
                   " UTILISATION    TEXT    NOT NULL, " + 
                   " VOLUME         TEXT    NOT NULL, " + 
                   " DATECREATION   DATE    NOT NULL, " + 
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