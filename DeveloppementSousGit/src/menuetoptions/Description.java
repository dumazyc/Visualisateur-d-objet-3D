package menuetoptions;

import java.sql.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

/*Classe pour afficher la description de l'objets courant
 * 
 * */

@SuppressWarnings("serial")
public class Description extends JPanel
{
	//objet a decrire et frame pour popup
	String nomObjet;
	

	//constructeur de la classe
	public Description(final String nomObjet){
		this.nomObjet=nomObjet;
		{
			Connection c = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:Database.db");
				c.setAutoCommit(false);

				stmt = c.createStatement();
				//on interroge la base
				
				 rs = stmt.executeQuery( "SELECT * FROM OBJETS3D WHERE NAME =" + "'"+ Description.this.nomObjet +"';" );
				while ( rs.next() ) {
					String  name = rs.getString("name");
					String  auteur = rs.getString("auteur");
					String datecreation  = rs.getString("datecreation");
					String complexite  = rs.getString("complexite");

					//la popup custom title, no icon
					
							this.add(new JLabel("NOM : " + name+".\n"
							+ "   AUTEUR : " + auteur +".\n"
							+ "   DATECREATION : " + datecreation +".\n" 
							+ "   COMPLEXITE : " + complexite +".\n" ));
									
				}
				
				//en cas d'erreur
				
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}finally{
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
}

