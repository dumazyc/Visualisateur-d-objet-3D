package menuetoptions;

import gestiondelaffichage3d.AffichageDuModele;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ModifiAjout {
	String nomObjet;
	final JFrame frame = new JFrame();
	private int id = 0;
	public ModifiAjout(final String nomObjet, final AffichageDuModele a) {
		
		Dimension d = new Dimension(100, 27);
		JPanel boxf = new JPanel();
		boxf.setLayout(new GridLayout(8, 1, 9, 9));
		this.nomObjet = nomObjet;
		final DefaultListModel<String> listModel_motscles = new DefaultListModel<String>();
		final JList<String> list = new JList<String>(listModel_motscles);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 150));
		JPanel jp = new JPanel();
		jp.add(new JLabel("  VEUILLEZ ENTRER LES INFORMATIONS A MODIFIER :  "));
		jp.add(listScroller);
		final JButton jb1 = new JButton("ajouter");
		final JButton jb2 = new JButton("supprimer mot cle");
		jb2.setPreferredSize(new Dimension(200, 30));
		JPanel Pjb2 = new JPanel();
		Pjb2.add(jb2);
		final JTextField jt = new JTextField();
		jt.setPreferredSize(new Dimension(170, 40));
		jp.add(jb1);
		jp.add(jt);
		boxf.add(Pjb2);
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {// Ajout mots cles actuel dans la base
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			String requete = "SELECT MOTCLE FROM OBJETS3D INNER JOIN MOTSCLES ON OBJETS3D.ID = MOTSCLES.ID_M WHERE NAME = '"
					+ nomObjet + "'";

			rs = stmt.executeQuery(requete);
			listModel_motscles.removeAllElements();
			while (rs.next()) {
				listModel_motscles.addElement((rs.getString("MOTCLE")));
			}
			list.setEnabled(true);
		} catch (Exception e1) {
			System.err
					.println(e1.getClass().getName() + ": " + e1.getMessage());
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
		jb2.addActionListener(new ActionListener() {// Suppression

			@Override
			public void actionPerformed(ActionEvent e) {
				String name="";
				if (!listModel_motscles.isEmpty()) {
					name = listModel_motscles.get(list.getSelectedIndex());
					int index = list.getSelectedIndex();
					listModel_motscles.remove(index);

					if (index == listModel_motscles.getSize()) {
						// removed item in last position
						index--;

						list.setSelectedIndex(index);
						list.ensureIndexIsVisible(index);
					}
				}
				Connection con = null;
				Statement stmt = null;
				
				try {
					Class.forName("org.sqlite.JDBC");
					con = DriverManager
							.getConnection("jdbc:sqlite:Database.db");
					con.setAutoCommit(false);

					stmt = con.createStatement();
					stmt.executeUpdate("DELETE FROM MOTSCLES WHERE ID_M = "+id+" AND MOTCLE = '"+name+"';");
					con.commit();
				} catch (Exception ea) {
					System.err.println(ea.getClass().getName() + ": "
							+ ea.getMessage());
					System.exit(0);
				} finally {
					try {
						stmt.close();
						con.close();

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		jb1.addActionListener(new ActionListener() {// Ajout

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = jt.getText();

				// User did not type in a unique name...
				if (name.equals("") || alreadyInList(name)) {
					Toolkit.getDefaultToolkit().beep();
					jt.requestFocusInWindow();
					jt.selectAll();
					return;
				}

				int index = list.getSelectedIndex(); // get selected index
				if (index == -1) { // no selection, so insert at beginning
					index = 0;
				} else { // add after the selected item
					index++;

				}

				listModel_motscles.addElement(jt.getText());
				Connection con = null;
				Statement stmt = null;
				try {
					Class.forName("org.sqlite.JDBC");
					con = DriverManager
							.getConnection("jdbc:sqlite:Database.db");
					con.setAutoCommit(false);

					stmt = con.createStatement();
					stmt.executeUpdate("INSERT INTO MOTSCLES (ID_M,MOTCLE) "
									+ "VALUES ("+id+", '"+name+"' );");
					con.commit();
				} catch (Exception ea) {
					System.err.println(ea.getClass().getName() + ": "
							+ ea.getMessage());
					System.exit(0);
				} finally {
					try {
						stmt.close();
						con.close();

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}

			private boolean alreadyInList(String name) {
				return listModel_motscles.contains(name);

			}
		});
		Connection con = null;
		String name = "null";
		String auteur = "null";
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:Database.db");
			con.setAutoCommit(false);

			stmt = con.createStatement();
			// ResultSet rs = stmt.executeQuery( "SELECT * FROM OBJETS3D;" );
			rs = stmt
					.executeQuery("SELECT * FROM OBJETS3D INNER JOIN MOTSCLES ON OBJETS3D.ID = MOTSCLES.ID_M WHERE NAME = '"
							+ nomObjet + "' ORDER BY ID_M ;");
			while (rs.next()) {
				name = rs.getString("name");
				auteur = rs.getString("auteur");
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		final JTextField tfield = new JTextField(15);
		tfield.setText(name);
		tfield.setEnabled(false);

		final JCheckBox cbox = new JCheckBox("Nom de l'objet: ", false);

		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				tfield.setEnabled(ie.getStateChange() == ItemEvent.SELECTED);
			}
		};
		cbox.addItemListener(itemListener);

		boxf.add(cbox);
		boxf.add(tfield);
		jp.add(boxf);

		final JTextField tfield1 = new JTextField(15);
		tfield1.setText(auteur);
		tfield1.setEnabled(false);

		final JCheckBox cbox1 = new JCheckBox("Auteur:    ", false);

		ItemListener itemListener1 = new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				tfield1.setEnabled(ie.getStateChange() == ItemEvent.SELECTED);

			}
		};
		cbox1.addItemListener(itemListener1);

		boxf.add(cbox1);
		boxf.add(tfield1);

		JPanel pa1 = new JPanel();
		final JButton valider = new JButton("Valider ");

		// quand on valide
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// tester ce qui a ete coche

				if ((cbox1.isSelected() && tfield1.getText().isEmpty())
						|| (cbox.isSelected() && tfield.getText().isEmpty())) {
					JOptionPane.showMessageDialog(frame,
							"Un champ coche ne peut etre vide.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String sql = "";
					String name;
					String auteur;
					name = tfield.getText();
					auteur = tfield1.getText();

					// on interroge la base de donnees
					if (cbox.isSelected() && !tfield.getText().equals(null))
						sql = "UPDATE OBJETS3D  SET NAME =" + "'" + name + "'"
								+ "WHERE NAME =" + "'" + nomObjet + "';";

					if (cbox1.isSelected() && !tfield1.getText().equals(null))
						sql = "UPDATE OBJETS3D  SET AUTEUR =" + "'" + auteur
								+ "'" + "WHERE NAME =" + "'" + nomObjet + "';";

					if (cbox1.isSelected() && !tfield1.getText().equals(null)
							&& cbox.isSelected()
							&& !tfield.getText().equals(null)) {

						sql = "UPDATE OBJETS3D  SET NAME =" + "'" + name + "'"
								+ ", AUTEUR =" + "'" + auteur + "'"
								+ "WHERE NAME =" + "'" + nomObjet + "';";

					}

					Connection c = null;
					Statement stmt = null;
					try {
						Class.forName("org.sqlite.JDBC");
						c = DriverManager
								.getConnection("jdbc:sqlite:Database.db");
						c.setAutoCommit(false);
						stmt = c.createStatement();
						stmt.executeUpdate(sql);

						c.commit();

						if (cbox.isSelected() && !tfield.getText().equals(null)) {
							String ligne;
							int cpt = 0;
							FileReader flux;
							BufferedReader entree = null;
							PrintWriter sortie = null;
							ArrayList<String> liste = new ArrayList<String>();
							try {
								flux = new FileReader("./ressources/modeles/"
										+ nomObjet + ".gts");
								entree = new BufferedReader(flux);
								sortie = new PrintWriter(
										"./ressources/modeles/" + name + ".gts");
								while ((ligne = entree.readLine()) != null) {
									liste.add(ligne);
									sortie.println(liste.get(cpt++));
								}
							} catch (Exception exception) {
								System.err.println(exception.toString());
							} finally {
								try {
									entree.close();
									entree=null;
									sortie.flush();
									sortie.close();
									sortie=null;
									System.gc();
								} catch (Exception e1) {
									e1.printStackTrace();
								}

							}
							File MyFile = new File("./ressources/modeles/"
									+ nomObjet + ".gts");
							MyFile.delete();

						}
						a.mettreAJourRecherche();
						a.mettreAJourDescription(name);
						a.changerNomOngletCourant(name);
						// pour confirmer
						// custom title, no icon
						JOptionPane.showMessageDialog(frame,
								"Modification bien prise en compte",
								"Modification", JOptionPane.PLAIN_MESSAGE);
						frame.dispose();
						// en cas d'erreur
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": "
								+ e1.getMessage());
						System.exit(0);

					} finally {
						try {
							stmt.close();
							c.commit();
							c.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					}
				}
			}

		});

		valider.setPreferredSize(d);
		pa1.add(valider);
		boxf.add(pa1);

		JPanel pa2 = new JPanel();
		JButton annuler = new JButton("Annuler ");
		annuler.setPreferredSize(d);
		pa2.add(annuler);
		boxf.add(pa2);

		// ferme la fenetre
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		frame.add(jp);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(540, 600);
		frame.setResizable(false);
		frame.setVisible(true);

	}



}
