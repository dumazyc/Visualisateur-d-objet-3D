package menuetoptions;

import java.sql.*;

import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * Classe permettant d'afficher la description de l'objet courant
 *
 */
@SuppressWarnings("serial")
public class Description extends JPanel {
	// objet a decrire
	private String nomObjet;

	/**constructeur de la classe
	 * @param nomObjet
	 * Permet de connaitre le nom de l'objet pour interroger la base
	 */
	public Description(final String nomObjet) {
		this.nomObjet = nomObjet;
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();

			// on interroge la base

			rs = stmt.executeQuery("SELECT * FROM OBJETS3D WHERE NAME =" + "'"
					+ Description.this.nomObjet + "';");
			while (rs.next()) {
				String name = rs.getString("name");
				String auteur = rs.getString("auteur");
				String datecreation = rs.getString("datecreation");
				String complexite = rs.getString("complexite");

				//Un label pour contenir les informations receuillies
				this.add(new JLabel("Nom : " + name + ".\n" + "   Auteur : "
						+ auteur + ".\n" + "   Date de creation : "
						+ datecreation + ".\n" + " Nombre de triangles : "
						+ complexite + ".\n"));

			}

			// en cas d'erreur

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
