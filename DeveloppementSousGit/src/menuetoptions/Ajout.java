package menuetoptions;
import gestiondelaffichage3d.AffichageDuModele;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

// Pour gerer l'ajout d'un objet

public class Ajout extends JFrame {
	private static final long serialVersionUID = 1L;
	AffichageDuModele aff;
	final JTextField fichier;
	final JCheckBox cbox;
	final JCheckBox cbox1;
	final JTextField tfield;
	final JTextField tfield1;
	final JFileChooser fc;

	//constructeur dela classe
	public Ajout(AffichageDuModele a) {

		//pour communiquer avec la fenetre principale
		this.aff = a;
		Dimension d = new Dimension(100, 27);

		//pour charger le fichier
		JButton charger = new JButton("Charger fichier");
		final JPanel pannelCharger = new JPanel();
		fc = new JFileChooser(".");
		charger.setPreferredSize(new Dimension(150, 27));
		fichier = new JTextField("Veuiller chosir un fichier");
		fichier.setPreferredSize(new Dimension(350, 27));
		pannelCharger.add(fichier);
		pannelCharger.add(charger);

		charger.addActionListener(new ActionListenerKarenNumero2(this));

		Container c = this.getContentPane();
		c.setLayout(new GridLayout(12,1,7,7));
		c.add(pannelCharger);

		c.add(new JLabel("  VEUILLEZ ENTRER LES INFORMATIONS RELATIVES A VOTRE OBJET:     "));

		//gestion des checkboxs

		final JPanel contentPane = new JPanel();
		tfield = new JTextField(15);
		tfield.setEnabled(false);

		cbox = new JCheckBox("Nom de l'objet: ", false);

		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				tfield.setEnabled(ie.getStateChange() == ItemEvent.SELECTED);
			}
		};
		cbox.addItemListener(itemListener);

		contentPane.add(cbox);
		contentPane.add(tfield);

		c.add(contentPane);

		final JPanel contentPane1 = new JPanel();

		tfield1 = new JTextField(15);

		tfield1.setEnabled(false);

		cbox1 = new JCheckBox("Auteur:    ", false);

		ItemListener itemListener1 = new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				tfield1.setEnabled(ie.getStateChange() == ItemEvent.SELECTED);

			}
		};
		cbox1.addItemListener(itemListener1);

		contentPane1.add(cbox1);
		contentPane1.add(tfield1);
		c.add(contentPane1);



		contentPane.setPreferredSize(d);
		contentPane1.setPreferredSize(d);
		contentPane1.setPreferredSize(d);

		JPanel pa1 = new JPanel();
		final JButton valider = new JButton("Valider ");
		valider.addActionListener(new ActionListenerKaren(this));

		valider.setPreferredSize(d);
		pa1.add(valider);
		c.add(pa1);

		JPanel pa2 = new JPanel();
		JButton annuler = new JButton("Annuler ");
		annuler.setPreferredSize(d);
		pa2.add(annuler);
		c.add(pa2);

		//pour annuler l'ajout

		annuler.addActionListener(new ActionListenerKarenNumero3(this));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(640,650);
		this.setResizable(false);
		this.setVisible(true);

	}
	public class ActionListenerKarenNumero3 implements ActionListener {
		private Ajout a;
		public ActionListenerKarenNumero3(Ajout ajout) {
			this.a = ajout;
		}
		public void actionPerformed(ActionEvent e) {
			a.dispose();
		}

	}
	public class ActionListenerKarenNumero2 implements ActionListener {

		private Ajout a;

		public ActionListenerKarenNumero2(Ajout ajout) {
			this.a = ajout;
		}

		//choix du fichier

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
			fc.showOpenDialog(a);
			try {
				fichier.setText(fc.getSelectedFile().getAbsolutePath());
			} catch (NullPointerException ex) {
				if (fichier.getText().isEmpty()) {
					fichier.setText("Veuiller chosir un fichier");

				}
			}

		}
	}

	public class ActionListenerKaren implements ActionListener {
		Ajout a;

		public ActionListenerKaren(Ajout a) {
			this.a = a;
		}

		public void actionPerformed(ActionEvent e) {
			if (a.fichier.getText().equals(("Veuiller chosir un fichier"))) {
				JOptionPane.showMessageDialog(a, "Veuillez choisir un fichier",
						"Attention", JOptionPane.WARNING_MESSAGE);
			}

			if (!a.cbox1.isSelected() || !a.cbox.isSelected()) {
				JOptionPane.showMessageDialog(a,
						"Tous les champs sont obligatoire", "Attention",
						JOptionPane.WARNING_MESSAGE);

			} else if ((cbox1.isSelected() && tfield1.getText().isEmpty())
					|| (cbox.isSelected() && tfield.getText().isEmpty())) {
				JOptionPane.showMessageDialog(a,
						"Un champ coche ne peut etre vide.", "Attention",
						JOptionPane.WARNING_MESSAGE);
			}

			// sinon insertion dans la base


			else {
				if (fichier.getText().equals("Veuiller chosir un fichier")) {
					JOptionPane.showMessageDialog(a,
							"Veuillez choisr un fichier", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else {
					// si verification gts valide sinon popup d'erreur

					String ligne;
					int cpt = 0;
					FileReader flux;
					BufferedReader entree;
					PrintWriter sortie;
					ArrayList<String> liste = new ArrayList<String>();
					try {

						flux = new FileReader(fc.getSelectedFile());
						entree = new BufferedReader(flux);
						sortie = new PrintWriter("./ressources/modeles/"
								+ tfield.getText() + ".gts");



						while ((ligne = entree.readLine()) != null) {

							liste.add(ligne);
							sortie.println(liste.get(cpt++));

						}

						entree.close();
						sortie.close();
					} catch (Exception e1) {
						System.err.println(e.toString());
					}

					String complexite ="";
					FileReader flux1;
					BufferedReader entree1;
					try {

						flux1 = new FileReader("./ressources/modeles/"
								+ tfield.getText() + ".gts");
						entree1 = new BufferedReader(flux1);
						String tmp=entree1.readLine();
						int space=0;


						for(int i=0;i<tmp.length();++i){
							if(space==2){
								complexite+=tmp.charAt(i);
							}else{
								if(Character.isWhitespace(tmp.charAt(i)))
									space++;
							}

						}

						entree1.close();

					} catch (Exception e1) {
						System.err.println(e.toString());
					}

					Connection c = null;
					Statement stmt = null;
					try {
						Class.forName("org.sqlite.JDBC");
						c = DriverManager
								.getConnection("jdbc:sqlite:Database.db");
						c.setAutoCommit(false);
						stmt = c.createStatement();
						String sql;
						String name;
						String auteur;


						if (cbox.isSelected() && !tfield.getText().equals(null)
								&& !tfield1.getText().equals(null)) {

							name = tfield.getText();
							auteur = tfield1.getText();
							sql = "INSERT INTO OBJETS3D (NAME,AUTEUR,COMPLEXITE,LIEN) "
									+ "VALUES ('"
									+ name
									+ "', '"
									+ auteur
									+ "', '"
									+ Integer.parseInt(complexite)+ "', '" + fichier.getText() + "' );";
							stmt.executeUpdate(sql);
							a.setEnabled(false);


						}
						//popup de confirmation

						Object[] options = {"Ok,visualiser",
						"Annuler l'ajout"};
						JOptionPane confirme=new JOptionPane();
						int n = JOptionPane.showOptionDialog(a,
								"Objet bien ajoute",
								"A Silly Question",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,     //do not use a custom Icon
								options,  //the titles of buttons
								options[0]); //default button title
						
						//si on confirme l'ajout
						if (n == JOptionPane.YES_OPTION) {
							confirme.setVisible(false);
							aff.nouvelOnglet(tfield.getText());
							a.dispose();
							AffichageDuModele.modifierInfos.setEnabled(true);
							
							//si on annule l'ajout
						} else if (n == JOptionPane.NO_OPTION) {
							confirme.setVisible(false);
							Statement stmt1;
							stmt1 = c.createStatement();
							 String sql1= "DELETE from OBJETS3D where NAME='"+ tfield.getText() +"';";
						     stmt1.executeUpdate(sql1);
						     JOptionPane.showMessageDialog(a, "Ajout annule",
										" ", JOptionPane.WARNING_MESSAGE);
							a.dispose();


						}
						//fermeture de connection

						stmt.close();
						c.commit();
						c.close();
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": "
								+ e1.getMessage());
						System.exit(0);

					}
				}
			}

		}
	}
}
