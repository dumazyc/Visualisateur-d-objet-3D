package menuetoptions;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class Ajout extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame frame = new JFrame("Veuillez charger le fichier et ajouter les infos necessaires :   ");
	
	public Ajout(){
		Dimension d=new Dimension(100,27);
		JButton charger=new JButton("Charger fichier");
		JPanel pannelCharger=new JPanel();
		charger.setPreferredSize(new Dimension(150,27) );
		final JTextField fichier=new JTextField("Veuiller chosir un fichier");
		fichier.setPreferredSize(new Dimension(350,27));
		pannelCharger.add(fichier);
		pannelCharger.add(charger);
		charger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(".");
				fc.addChoosableFileFilter(new FileFilter() {
					@Override
					public boolean accept(File f) {
						 return f.getName().endsWith(".gts");
					}
					@Override
					public String getDescription() {
						return "Fichier GNU Triangulated Surface Library (.gts)";
					}
				});
				fc.addChoosableFileFilter(new FileFilter() {
					@Override
					public boolean accept(File f) {
						 return f.getName().endsWith(".txt");
					}
					@Override
					public String getDescription() {
						return "Fichier texte (.txt)";
					}
				});
				fc.showOpenDialog(frame);
				try{
				fichier.setText(fc.getSelectedFile().getAbsolutePath());
				}catch(NullPointerException ex){
					if(fichier.getText().isEmpty()){
					fichier.setText("Veuiller chosir un fichier");
					}
				}
			}
		});

		 
		Container c=frame.getContentPane();
		c.setLayout(new GridLayout(8,1,7,7));
		c.add( pannelCharger);
		
		
		c.add(new JLabel("  VEUILLEZ ENTRER LES INFORMATIONS RELATIVES A VOTRE OBJET:     "));
		
		final JPanel contentPane = new JPanel();

		final JTextField tfield = new JTextField(15);
		tfield.setEnabled(false);

		final JCheckBox cbox = new JCheckBox("Nom de l'objet: ", false);

		ItemListener itemListener = new ItemListener()
		{
			public void itemStateChanged(ItemEvent ie)
			{
				tfield.setEnabled(ie.getStateChange() == ItemEvent.SELECTED);
			}
		};
		cbox.addItemListener(itemListener);

		contentPane.add(cbox);
		contentPane.add(tfield);

		c.add(contentPane);

		final JPanel contentPane1 = new JPanel();

		final JTextField tfield1 = new JTextField(15);

		tfield1.setEnabled(false);

		final JCheckBox cbox1 = new JCheckBox("Auteur:    ", false);

		ItemListener itemListener1 = new ItemListener()
		{
			public void itemStateChanged(ItemEvent ie)
			{
				tfield1.setEnabled(ie.getStateChange() == ItemEvent.SELECTED);

			}
		};
		cbox1.addItemListener(itemListener1);

		contentPane1.add(cbox1);
		contentPane1.add(tfield1);
		c.add(contentPane1);



		final JPanel contentPane2  = new JPanel();

		contentPane.setPreferredSize(d);
		contentPane1.setPreferredSize(d);
		contentPane1.setPreferredSize(d);
		c.add(contentPane2);
		JPanel pa1=new JPanel();
		JButton bu=new JButton("Valider ");
		bu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((cbox1.isSelected()&&tfield1.getText().isEmpty())||(cbox.isSelected()&&tfield.getText().isEmpty())){
					JOptionPane.showMessageDialog(frame,
							"Un champ coche ne peut etre vide.",
							"Attention",
							JOptionPane.WARNING_MESSAGE);
				}
				//else insertion dans la base
				/*clement devra s'occcuper du reste de l'ajout dans les ressources ert de la verification du fichier charge
				 * en se servant de fiochier.getText();
				*/
				// String ac = perso.getSelectedItem().toString()
				// la date de l'objet sera ajout�e directement � la base avec une fonction qui renvoie la date du jour
				else { 
				    Connection c = null;
				    Statement stmt = null;
				    try {
				    	Class.forName("org.sqlite.JDBC");
				        c = DriverManager.getConnection("jdbc:sqlite:Database.db");
				        c.setAutoCommit(false);
				        stmt = c.createStatement();
				        String sql;
				        String  name;
				        String  auteur;
				      //verifier aussi que le test de corruption que fera clement passe avnt de faire l'insertion la date sera gen�r�e comme celle du jour 
				        //commentaire laisser dans SQLITE.java et le nombre de triangles(complexite) sera ajoute � partir de la fonction dont a parle clemtn
					    	 if (cbox.isSelected()&&!tfield.getText().equals(null)&&!tfield1.getText().equals(null)) {
					    		 name = tfield.getText();
					    		 auteur = tfield1.getText();
					    		 sql = "INSERT INTO OBJETS3D (NAME,AUTEUR,FORME,UTILISATION,VOLUME,DATECREATION,COMPLEXITE,LIEN) " +
							               "VALUES ('"+name+"', '"+auteur+"', '"+name+"', 'Mode', 'En attente', '2014-11-28', 0, '"+fichier.getText()+"' );";
					    		 stmt.executeUpdate(sql);
					    		 JOptionPane.showMessageDialog(frame,"L'objet a bien ete integre.","Attention",JOptionPane.WARNING_MESSAGE);
					   //triangle, complexité à ajouter automatiquement  !!!!!!!!!!!!!
				         	} 
					    	else if (cbox.isSelected()){ 
				        	 JOptionPane.showMessageDialog(frame,
										"Le nom n'est pas complete.",
										"Attention",
										JOptionPane.WARNING_MESSAGE);
				        } else if (cbox1.isSelected()){ 
				        	 JOptionPane.showMessageDialog(frame,
										"L'auteur n'est pas complete.",
										"Attention",
										JOptionPane.WARNING_MESSAGE);
				         }
					  stmt.close(); 
					  c.commit();
					  c.close();
				    } catch ( Exception e1 ) {
				      System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
				      System.exit(0);
				    }
				  }



			}
		});

		bu.setPreferredSize(d);
		pa1.add(bu);
		c.add(pa1);


		JPanel pa2=new JPanel();
		JButton e=new JButton("Retour ");
		e.setPreferredSize(d);
		pa2.add(e);
		c.add(pa2);
		
		// ferme l'application
		e.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640,480);
		frame.setResizable(false);
		frame.setVisible(true);
		


	}
}