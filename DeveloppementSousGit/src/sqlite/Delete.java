package sqlite;

import java.sql.*;

/**
 * Classe pour effectuer les suppressions dans la base
 *
 */

public class Delete {

	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			String sql = "DELETE from objets3d where name='maison';";
			stmt.executeUpdate(sql);
			c.commit();

			rs = stmt.executeQuery("SELECT * FROM OBJETS3D;");
			while (rs.next()) {
				String name = rs.getString("name");
				System.out.print(name + " ");
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
		System.out.println();
		System.out.println();
		System.out.println("Suppression terminee");
	}
}