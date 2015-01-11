package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Classe pour creer des tables
 *
 */

public class SQLite {
	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			System.out.println("Ouverture de la base de donnee");

			stmt = c.createStatement();
			/*
			 * String sql = "CREATE TABLE OBJETS3D " +
			 * "(ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
			 * " NAME           TEXT    NOT NULL, " +
			 * " AUTEUR         TEXT    NOT NULL, " +
			 * " DATECREATION   DATE    DEFAULT CURRENT_DATE, " +
			 * " COMPLEXITE     INT    	NOT NULL)";
			 */
			String sql = "CREATE TABLE MOTSCLES " + "(ID_M INTEGER   NOT NULL,"
					+ " MOTCLE 		TEXT 	NOT NULL)";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {

				stmt.close();
				c.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		System.out.println("Table cree avec succes");
	}
}