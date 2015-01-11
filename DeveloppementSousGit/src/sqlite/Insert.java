package sqlite;

import java.sql.*;

/**
 * Classe pour inserer un objet et/ou un mot cle dans la base
 *
 */

public class Insert {
	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			/*
			 * String sql =
			 * "INSERT INTO OBJETS3D (NAME,AUTEUR,COMPLEXITE) " +
			 * "VALUES ('HarryPotter', 'Damien', 0);";
			 */
			String sql2 = "INSERT INTO MOTSCLES (ID_M,MOTCLE) "
					+ "VALUES (28, 'cuisine' );";
			String sql3 = "INSERT INTO MOTSCLES (ID_M,MOTCLE) "
					+ "VALUES (28, 'verre' );";
			// stmt.executeUpdate(sql);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				stmt.close();
				c.commit();
				c.close();

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		System.out.println("Objet créé avec succès");
	}
}
