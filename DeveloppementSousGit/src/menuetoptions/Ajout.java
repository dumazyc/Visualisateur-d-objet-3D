package menuetoptions;
import gestiondelaffichage3d.AffichageDuModele;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
		final JPanel pannelCharger=new JPanel();
		final JFileChooser fc = new JFileChooser(".");
		charger.setPreferredSize(new Dimension(150,27) );
		final JTextField fichier=new JTextField("Veuiller chosir un fichier");
		fichier.setPreferredSize(new Dimension(350,27));
		pannelCharger.add(fichier);
		pannelCharger.add(charger);

		charger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
		final JButton valider=new JButton("Valider ");
		valider.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
				if(!cbox1.isSelected()||!cbox.isSelected()){
					JOptionPane.showMessageDialog(frame,
							"Tous les champs sont obligatoire",
							"Attention",
							JOptionPane.WARNING_MESSAGE);

				}
				else
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
						if(fichier.getText().equals("Veuiller chosir un fichier")){
							JOptionPane.showMessageDialog(frame,
									"Veuillez choisr un fichier",
									"Attention",
									JOptionPane.WARNING_MESSAGE);
						}else{
							//si verification gts valide sinon popup d'erreur 

							String ligne;
							int cpt = 0;
							FileReader flux;
							BufferedReader entree;
							PrintWriter sortie;
							ArrayList<String> liste = new ArrayList<String>();
							try {
								
								flux = new FileReader(fc.getSelectedFile());
								entree = new BufferedReader(flux);
								sortie = new PrintWriter("./ressources/modeles/" + tfield.getText() + ".gts");

								while ((ligne = entree.readLine()) != null) {
									liste.add(ligne);
									sortie.println(liste.get(cpt++));
								}
								
								entree.close();
								sortie.close();
							} catch (Exception e1) {
								System.err.println(e.toString());
							}


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
								
								if (cbox.isSelected()&&!tfield.getText().equals(null)&&!tfield1.getText().equals(null)) {
									//nous avons besoin de l'objet pour recuperer sa complexite il s'agira d'un objet qu'on dont-on a teste l'appartenance a la communaute gts
									//complexite a modifier lors de l'insertion appeler la methode getface size sur le fichier charge!!!!!!!!!!!!!
									name = tfield.getText();
									auteur = tfield1.getText();
									sql = "INSERT INTO OBJETS3D (NAME,AUTEUR,COMPLEXITE,LIEN) " +
											"VALUES ('"+name+"', '"+auteur+"', 0, '"+fichier.getText()+"' );";
									stmt.executeUpdate(sql);
									frame.setEnabled(false);

									Object[] options = {" Ok,Visualiser Objet",
									"Modifier les informations"};

									final JOptionPane optionPane=new JOptionPane();

									int n = JOptionPane.showOptionDialog(frame,
											"Objet bien ajoute",
											"",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE,
											null,     //do not use a custom Icon
											options,  //the titles of buttons
											options[0]); //default button title

									if (n == JOptionPane.YES_OPTION) {
										frame.dispose();
										AffichageDuModele aff = null;
										aff.nouvelOnglet(tfield.getText());


									} else if (n== JOptionPane.NO_OPTION) {
										frame.dispose();
										/*nouvelle fenetre ajout avec tous ce qu'il y a dans ajout sans le jtextfield et dont les info serviront 
					a modifier l'objet dans la base de donnees vendredi*/ 


									}


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



			}
		});

		valider.setPreferredSize(d);
		pa1.add(valider);
		c.add(pa1);


		JPanel pa2=new JPanel();
		JButton annuler=new JButton("Annuler ");
		annuler.setPreferredSize(d);
		pa2.add(annuler);
		c.add(pa2);

		// ferme l'application
		annuler.addActionListener(new ActionListener() {
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