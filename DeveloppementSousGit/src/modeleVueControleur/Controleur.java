package modeleVueControleur;

import gestiondelaffichage3d.VerifGts;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controleur {
	ModelInsertion model;
	boolean RechercheAppelle=false;
	public boolean alerteDoublon=false;
	AffichageDuModele a;
	public Controleur(AffichageDuModele a,ModelInsertion model){
		
		super();
		this.model = model;
		this.a=a;
	}
	public boolean verifieGts(String chemin){
		VerifGts verificateur=new VerifGts(chemin);
		return verificateur.GtsEstCorrect();	
	}
	public void envoieGts(JFrame f,File fichier,String nomObjet, String auteur,String chemin){
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			 String query = "SELECT * FROM OBJETS3D WHERE NAME='"+ nomObjet + "'";
			    rs = stmt.executeQuery(query);
			    
			    if(!rs.next()) 
					{
						if(verifieGts(chemin))
					model.insertion(fichier, nomObjet, auteur);
						else{
							JOptionPane.showMessageDialog(f, "fichier non valide",
									"Attention", JOptionPane.WARNING_MESSAGE);	
						}
					}else{
						alerteDoublon=true;
						JOptionPane.showMessageDialog(f, "Ce nom existe deja",
								"Attention", JOptionPane.WARNING_MESSAGE);
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
		
		
	
	public void affichageObjet(String nomObjet){
		model.appelFenetrePrincipale(nomObjet);
		
		
	}
	
}
