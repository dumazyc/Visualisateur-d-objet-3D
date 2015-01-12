package modeleVueControleur;

import gestiondelaffichage3d.VerifGts;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe serveant de controleur aux vues de recherhce de fenetre principale et d'ajout
 *
 */
public class Controleur {
	
	ModelInsertion model;
	//pour eviter les doublons dans les noms
	public boolean alerteDoublon=false;
	AffichageDuModele a;
	/**
	 * Constructeur de la calsse
	 * @param a
	 * pour communiquer avec la fentre principale
	 * 
	 * @param model
	 * Un  nouveau modele
	 */
	public Controleur(AffichageDuModele a,ModelInsertion model){
		
		this.model = model;
		this.a=a;
	}
	
	/**
	 * @param chemin
	 * pour aller retrouver le fichier dans ressources
	 * @return
	 * indique si gts valide ou pas 
	 */
	public boolean verifieGts(String chemin){
		VerifGts verificateur=new VerifGts(chemin);
		return verificateur.GtsEstCorrect();	
	}
	/**
	 * @param f
	 * une frame pour la popup
	 * 
	 * @param fichier
	 * pour recuperer le fichier
	 * 
	 * @param nomObjet
	 * nom de l'objet
	 * 
	 * @param auteur
	 *  auteur de l'objet
	 *  
	 * @param chemin
	 * pour verifier la validite du gts
	 */
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
						if(verifieGts(chemin)) {
							rs.close();
							stmt.close();
							c.commit();
							c.close();
					model.insertion(fichier, nomObjet, auteur);
						}
						else{
							JOptionPane.showMessageDialog(f, "fichier non valide",
									"Attention", JOptionPane.WARNING_MESSAGE);
							rs.close();
							stmt.close();
							c.commit();
							c.close();
						}
					}else{
						alerteDoublon=true;
						JOptionPane.showMessageDialog(f, "Ce nom existe deja",
								"Attention", JOptionPane.WARNING_MESSAGE);
						rs.close();
						stmt.close();
						c.commit();
						c.close();
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
		
		
	
	/**
	 * pour avertir le model
	 * @param nomObjet
	 * pour avertir le model
	 */
	public void affichageObjet(String nomObjet){
		model.appelFenetrePrincipale(nomObjet);
		
		
	}
	
}
