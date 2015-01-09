package sqlite;

import java.sql.*;

/*Classe pour afficher les objets de la base*/

public class Select {
	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			// ResultSet rs = stmt.executeQuery( "SELECT * FROM OBJETS3D;" );
			rs = stmt
					.executeQuery("SELECT * FROM OBJETS3D INNER JOIN MOTSCLES ON OBJETS3D.ID = MOTSCLES.ID_M ORDER BY ID_M;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String auteur = rs.getString("auteur");
				String datecreation = rs.getString("datecreation");
				String complexite = rs.getString("complexite");
				String lien = rs.getString("lien");
				String motcle = rs.getString("motcle");
				System.out.println("MOTCLE = " + motcle);
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("AUTEUR = " + auteur);
				System.out.println("DATECREATION = " + datecreation);
				System.out.println("COMPLEXITE = " + complexite);
				System.out.println("LIEN = " + lien);

				System.out.println();
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				rs.close();
				stmt.close();
				c.close();

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}
}
