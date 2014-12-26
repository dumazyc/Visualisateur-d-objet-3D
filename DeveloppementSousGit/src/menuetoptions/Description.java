package menuetoptions;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*Classe pour afficher la description de l'objets courant
 * 
 * */

public class Description
{
	//objet a decrire et frame pour popup
	String nomObjet;
	final JFrame frame = new JFrame("Description");

	//constructeur de la classe
	public Description(final String nomObjet){
		this.nomObjet=nomObjet;
		{
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:Database.db");
				c.setAutoCommit(false);

				stmt = c.createStatement();
				//on interroge la base
				
				ResultSet rs = stmt.executeQuery( "SELECT * FROM OBJETS3D WHERE NAME =" + "'"+ Description.this.nomObjet +"';" );
				while ( rs.next() ) {
					String  name = rs.getString("name");
					String  auteur = rs.getString("auteur");
					String datecreation  = rs.getString("datecreation");
					String complexite  = rs.getString("complexite");

					//la popup custom title, no icon
					JOptionPane.showMessageDialog(frame,
							"NOM : " + name+".\n"
							+ "AUTEUR : " + auteur +".\n"
							+ "DATECREATION : " + datecreation +".\n" 
							+ "COMPLEXITE : " + complexite +".\n" 
									,"A plain message",
									JOptionPane.PLAIN_MESSAGE);

				}
				rs.close();
				stmt.close();
				c.close();
				//en cas d'erreur
				
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}

	}
}

