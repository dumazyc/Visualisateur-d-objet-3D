package modeleVueControleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;

/**

 * Classe serveant de modele aux vues de recherhce de fenetre principale et d'ajout
 *
 */
public class ModelInsertion extends Observable {
	
	Connection c = null;
	Statement stmt = null;

	/**
	 * Constructeur de la classe
	 */
	public ModelInsertion(){
		super();
	
	}

	/**
	 * pour inserer l'objet dans la base
	 * @param fichier
	 *  pour recuperer le fichier
	 *  
	 * @param nomObjet
	 *  nom de l'objet
	 *  
	 * @param auteur
	 *  auteur de l'objet
	 */
	public void insertion(File fichier,String nomObjet, String auteur){
		String ligne;
		int nombreTriangles = 0 ;
		int cpt = 0;
		FileReader flux;
		BufferedReader entree = null;
		PrintWriter sortie = null;
		ArrayList<String> liste = new ArrayList<String>();
		try {
			
			flux = new FileReader(fichier);
			System.out.println(fichier);
			entree = new BufferedReader(flux);
			sortie = new PrintWriter("./ressources/modeles/"
					+ nomObjet + ".gts");

			while ((ligne = entree.readLine()) != null) {

				liste.add(ligne);
				sortie.println(liste.get(cpt++));

			}
		} catch (Exception e1) {
			System.err.println(e1.toString());
		} finally {
			try {
				entree.close();
				sortie.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		String complexite = "";
		FileReader flux1;
		BufferedReader entree1 = null;
		try {
			//On recupere la complexite
			flux1 = new FileReader("./ressources/modeles/"
					+ nomObjet + ".gts");
			entree1 = new BufferedReader(flux1);
			String tmp = entree1.readLine();
			int space = 0;

			for (int i = 0; i < tmp.length(); ++i) {
				if (space == 2) {
					complexite += tmp.charAt(i);
				} else {
					if (Character.isWhitespace(tmp.charAt(i)))
						space++;
				}

			}
		 nombreTriangles+=Integer.parseInt(complexite);

			entree1.close();

		} catch (Exception e) {
			System.err.println(e.toString());
		} finally {
			try {
				entree1.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		try {
			//insertion des informations dans la base
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();

			
			String sql = "INSERT INTO OBJETS3D (NAME,AUTEUR,COMPLEXITE) "
					+ "VALUES ('"
					+ nomObjet
					+ "', '"
					+ auteur
					+ "', '"
					+ nombreTriangles
					+  "' );";
			stmt.executeUpdate(sql);
	
		//en cas d'erreur
	} catch (Exception e1) {
		System.err.println(e1.getClass().getName() + ": "
				+ e1.getMessage());
		System.exit(0);

	} finally {

		//fermeture de connection
		try {
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
		this.setChanged();
		this.notifyObservers(nomObjet);

}

	public void appelFenetrePrincipale(String objet){
		this.setChanged();
		this.notifyObservers(objet);
		
		
	}
}
