package menuetoptions;

import gestiondelaffichage3d.AffichageDuModele;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Recherche extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AffichageDuModele frame;


	public Recherche(final AffichageDuModele a) {
		this.frame = a;
		Dimension d = new Dimension(100, 27);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// premier contentPane recherche.
		JPanel contentPane1 = new JPanel();
		JLabel recherche = new JLabel("Recherche : ");
		final JTextField tfield = new JTextField("Mots Cles", 8);
		contentPane1.add(recherche);
		contentPane1.add(tfield);
		contentPane1.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2,
				Color.black));
		this.add(contentPane1);
		contentPane1.setPreferredSize((new Dimension(30, 0)));

		// 2eme contentPane resultat.
		final JPanel contentPane2 = new JPanel();
		contentPane2.setPreferredSize((new Dimension(50, 100)));
		JLabel resultat = new JLabel("Resultat : ");
		// final JList liste_gts = new JList(fichier_gts.toArray());
		final DefaultListModel listModel_gts = new DefaultListModel();
		final JList liste_gts = new JList(listModel_gts);
		// liste_gts.setVisibleRowCount(10);
		// liste_gts.setEnabled(false);
		// liste_gts.setPreferredSize(new Dimension(120,150));
		JScrollPane jsp = new JScrollPane(liste_gts);
		jsp.setPreferredSize(new Dimension(120, 150));
		contentPane2.setBorder(BorderFactory.createMatteBorder(0, 2, 3, 2,
				Color.black));
		contentPane2.add(resultat);
		contentPane2.add(jsp);
		this.add(contentPane2);

		// panel text
		JPanel contentPane5 = new JPanel();
		JLabel label_modifier = new JLabel(
				"<html><b><font size=4>Modifier les mots cles : </font></b></html>");
		contentPane5.add(label_modifier);
		contentPane5.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2,
				Color.black));
		// contentPane5.setSize(new Dimension(50, 50));
		this.add(contentPane5);

		// 3eme contentpane liste des mots cles.
		JPanel contentPane3 = new JPanel();
		JLabel label_motscles = new JLabel("Mots Cles : ");
		final DefaultListModel listModel_motscles = new DefaultListModel();
		final JList liste_mots_clef = new JList(listModel_motscles);
		// liste_mots_clef.setVisibleRowCount(10);
		// liste_mots_clef.setPreferredSize(new Dimension(120,150));
		JScrollPane jsp2 = new JScrollPane(liste_mots_clef);
		jsp2.setPreferredSize(new Dimension(120, 150));
		contentPane3.add(label_motscles);
		contentPane3.add(jsp2);
		final JButton supprimer = new JButton("Supprimer");
		supprimer.setEnabled(false);
		liste_mots_clef.setEnabled(false);
		contentPane3.add(supprimer);
		contentPane3.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2,
				Color.black));
		this.add(contentPane3);
		contentPane3.setPreferredSize(new Dimension(10, 180));

		// 4eme contentpane modification des mots cles.

		JPanel contentPane4 = new JPanel();
		final JTextField tfield2 = new JTextField("New mots cles", 10);
		final JButton modifier = new JButton("modifier");
		final JButton ajouter = new JButton("Ajouter");
		tfield2.setEnabled(false);
		ajouter.setEnabled(false);
		modifier.setEnabled(false);
		contentPane4.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2,
				Color.black));
		contentPane4.add(tfield2);
		contentPane4.add(modifier);
		contentPane4.add(ajouter);
		this.add(contentPane4);
		this.setVisible(true);

		// Permet le traitement automatique des la saisie d'un nouveau caract�re
		// dans le textfield.
		tfield.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}

			public void removeUpdate(DocumentEvent e) {
				warn();
			}

			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				
				listModel_gts.removeAllElements();
				liste_gts.clearSelection();
				liste_gts.removeAll();
				

				if (!tfield.getText().equals("")
						&& !tfield.getText().equals("Mots Cles")) {
					Connection c = null;
					Statement stmt = null;
					try {
						Class.forName("org.sqlite.JDBC");
						c = DriverManager
								.getConnection("jdbc:sqlite:Database.db");
						c.setAutoCommit(false);
						stmt = c.createStatement();
						String requete = "SELECT DISTINCT OBJETS3D.NAME FROM OBJETS3D INNER JOIN MOTSCLES ON OBJETS3D.ID = MOTSCLES.ID_M WHERE MOTCLE LIKE '"
								+ tfield.getText()
								+ "%' OR NAME LIKE '"
								+ tfield.getText()
								+ "%' OR AUTEUR LIKE '"
								+ tfield.getText()
								+ "%' OR ID LIKE '"
								+ tfield.getText()
								+ "%' OR DATECREATION LIKE '"
								+ tfield.getText() + "%';";
						ResultSet rs = stmt.executeQuery(requete);
						while (rs.next()) {
							listModel_gts.addElement((rs.getString("NAME")));

						}
						liste_gts.setEnabled(true);

					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": "
								+ e1.getMessage());
						System.exit(0);
					}
				}

			}
		});

		// Action quand on selectionne un element de la liste des .gts.
		liste_gts.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (e.getValueIsAdjusting()) {
					return;
				}
				liste_gts.getSelectedValue();
				if((String) liste_gts.getSelectedValue()!=null){
				a.nouvelOnglet((String) liste_gts.getSelectedValue());
				}
				Connection c = null;
				Statement stmt = null;
				try {
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:Database.db");
					c.setAutoCommit(false);

					stmt = c.createStatement();
					String requete = "SELECT MOTCLE FROM OBJETS3D INNER JOIN MOTSCLES ON OBJETS3D.ID = MOTSCLES.ID_M WHERE NAME = '"
							+ ((String) liste_gts.getSelectedValue()) + "'";

					ResultSet rs = stmt.executeQuery(requete);
					listModel_motscles.removeAllElements();
					while (rs.next()) {
						listModel_motscles.addElement((rs.getString("MOTCLE")));
					}
					liste_mots_clef.setEnabled(true);

				} catch (Exception e1) {
					System.err.println(e1.getClass().getName() + ": "
							+ e1.getMessage());
					System.exit(0);
				}

			}
		});

		// Action quand on selectionne un element de la liste des mots clef.
		liste_mots_clef.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				supprimer.setEnabled(true);
				modifier.setEnabled(true);
				ajouter.setEnabled(true);
				tfield2.setText((String) liste_mots_clef.getSelectedValue());
				tfield2.setEnabled(true);
			}
		});

		// Action du bouton Suprrime.

		supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listModel_motscles.removeElement(liste_mots_clef
						.getSelectedValue());
			}
		});

		// Action du bouton Modifier.

		modifier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = 0;
				index = liste_mots_clef.getSelectedIndex();
				if (index != 0) {
					listModel_motscles.insertElementAt(tfield2.getText(), index);
					listModel_motscles.remove(index + 1);
				}
			}
		});

		// Action du bouton Ajouter.
		ajouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tfield2.getText().equals("New mots cles")) {
					listModel_motscles.addElement(tfield2.getText());

				} else {
					System.out
							.println("vous n'avez pas entrer de nouveau mots cles");
				}

			}
		});
	}

}

// String ac = perso.getSelectedItem().toString()

/*
 * bu.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent e) { if ((cbox1.isSelected() &&
 * tfield1.getText().isEmpty()) || tfield.getText().isEmpty()) {
 * JOptionPane.showMessageDialog(frame, "Un champ coche ne peut etre vide.",
 * "Attention", JOptionPane.WARNING_MESSAGE); } else { Connection c = null;
 * Statement stmt = null; try { Class.forName("org.sqlfalseite.JDBC"); c =
 * DriverManager .getConnection("jdbc:sqlite:Database.db");
 * c.setAutoCommit(false);
 * 
 * stmt = c.createStatement(); ResultSet rs = stmt
 * .executeQuery("SELECT * FROM OBJETS3D;"); String name = rs.getString("name");
 * String auteur = rs.getString("auteur");
 * 
 * while (!name.equals(tfield.getText()) && rs.next()) { name =
 * rs.getString("name"); } while (!auteur.equals(tfield1.getText()) &&
 * rs.next()) { auteur = rs.getString("auteur"); } if (
 * name.equals(tfield.getText())) { //
 * JOptionPane.showMessageDialog(frame,"L'objet existe."
 * ,"Attention",JOptionPane.WARNING_MESSAGE); a.nouvelOnglet(name);
 * //frame.dispose(); } else { JOptionPane.showMessageDialog(frame,
 * "L'auteur n'existe pas.", "Attention", JOptionPane.WARNING_MESSAGE);
 * 
 * } if (cbox1.isSelected() && auteur.equals(tfield1.getText())) {
 * JOptionPane.showMessageDialog(frame, "Clement est l'auteur des objets ...",
 * "Attention", JOptionPane.WARNING_MESSAGE); } else if (cbox1.isSelected()) {
 * JOptionPane.showMessageDialog(frame, "L'auteur n'existe pas.", "Attention",
 * JOptionPane.WARNING_MESSAGE); } rs.close(); stmt.close(); c.close(); } catch
 * (Exception e1) { System.err.println(e1.getClass().getName() + ": " +
 * e1.getMessage()); System.exit(0); } } }
 * 
 * });
 * 
 * bu.setPreferredSize(d); pa1.add(bu); this.add(pa1);
 * 
 * JPanel pa2 = new JPanel(); JButton e = new JButton("Annuler ");
 * e.setPreferredSize(d); pa2.add(e); this.add(pa2);
 */
/*
 * e.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent arg0) { frame.dispose();
 * 
 * } });
 */

// ancien panel
/*
 * package menuetoptions;
 * 
 * import gestiondelaffichage3d.AffichageDuModele;
 * 
 * import java.awt.Dimension; import java.awt.GridLayout; import
 * java.awt.event.*; import java.sql.*; import java.util.ArrayList; import
 * java.util.List;
 * 
 * import javax.swing.*;
 * 
 * @SuppressWarnings("serial") public class Recherche extends JPanel {
 * AffichageDuModele frame; List<String> listeRecherche = new
 * ArrayList<String>();
 * 
 * public Recherche(final AffichageDuModele a) { this.frame = a; Dimension d =
 * new Dimension(100, 27); this.setLayout(new GridLayout(5, 1));
 * 
 * final JPanel contentPane = new JPanel();
 * 
 * final JTextField tfield = new JTextField(15); tfield.setEnabled(false);
 * 
 * final JCheckBox cbox = new JCheckBox("Nom de l'objet: ", false);
 * 
 * ItemListener itemListener = new ItemListener() { public void
 * itemStateChanged(ItemEvent ie) { tfield.setEnabled(ie.getStateChange() ==
 * ItemEvent.SELECTED); } }; cbox.addItemListener(itemListener);
 * 
 * contentPane.add(cbox); contentPane.add(tfield);
 * 
 * this.add(contentPane);
 * 
 * final JPanel contentPane1 = new JPanel();
 * 
 * final JTextField tfield1 = new JTextField(15);
 * 
 * tfield1.setEnabled(false);
 * 
 * final JCheckBox cbox1 = new JCheckBox("Auteur:    ", false);
 * 
 * ItemListener itemListener1 = new ItemListener() { public void
 * itemStateChanged(ItemEvent ie) { tfield1.setEnabled(ie.getStateChange() ==
 * ItemEvent.SELECTED);
 * 
 * } }; cbox1.addItemListener(itemListener1);
 * 
 * contentPane1.add(cbox1); contentPane1.add(tfield1); this.add(contentPane1);
 * 
 * final JPanel contentPane2 = new JPanel();
 * 
 * final JComboBox<String> perso = new JComboBox<String>();
 * perso.setEnabled(false); perso.setBounds(100, 120, 100, 25);
 * 
 * perso.addItem("1"); perso.addItem("3"); perso.addItem("3");
 * perso.addItem("4"); perso.addItem("5"); perso.addItem("6");
 * 
 * // String ac = perso.getSelectedItem().toString()
 * 
 * final JCheckBox cbox2 = new JCheckBox("Nombre de trinangles:    ", false);
 * 
 * ItemListener itemListener2 = new ItemListener() { public void
 * itemStateChanged(ItemEvent ie) { perso.setEnabled(ie.getStateChange() ==
 * ItemEvent.SELECTED); } }; cbox2.addItemListener(itemListener2);
 * 
 * contentPane2.add(cbox2); contentPane2.add(perso);
 * contentPane.setPreferredSize(d); contentPane1.setPreferredSize(d);
 * contentPane1.setPreferredSize(d); this.add(contentPane2); JPanel pa1 = new
 * JPanel(); JButton bu = new JButton("Valider "); bu.addActionListener(new
 * ActionListener() { public void actionPerformed(ActionEvent e) { if
 * ((cbox1.isSelected() && tfield1.getText().isEmpty()) || (cbox.isSelected() &&
 * tfield.getText().isEmpty())) { JOptionPane.showMessageDialog(frame,
 * "Un champ coche ne peut etre vide.", "Attention",
 * JOptionPane.WARNING_MESSAGE); } else { Connection c = null; Statement stmt =
 * null; try { Class.forName("org.sqlite.JDBC"); c = DriverManager
 * .getConnection("jdbc:sqlite:Database.db"); c.setAutoCommit(false);
 * 
 * stmt = c.createStatement(); ResultSet rs =
 * stmt.executeQuery("SELECT * FROM OBJETS3D;"); String name =
 * rs.getString("name"); String auteur = rs.getString("auteur");
 * 
 * while (rs.next()) { name = rs.getString("name"); if
 * (name.equals(tfield.getText())) { listeRecherche.add(name);
 * System.out.println(listeRecherche); } }
 * 
 * while (rs.next()) { auteur = rs.getString("auteur"); }
 * 
 * if (cbox.isSelected() && !listeRecherche.isEmpty()) { //
 * JOptionPane.showMessageDialog
 * (frame,"L'objet existe.","Attention",JOptionPane.WARNING_MESSAGE);
 * ListAfterSearch l = new ListAfterSearch(listeRecherche);
 * //a.nouvelOnglet(name); // frame.dispose(); } else if (cbox.isSelected()) {
 * // le cas auteur et le cas complexite devrait etre // gere
 * JOptionPane.showMessageDialog(frame, "L'objet n'existe pas.", "Attention",
 * JOptionPane.WARNING_MESSAGE);
 * 
 * } if (cbox1.isSelected() && auteur.equals(tfield1.getText())) {
 * JOptionPane.showMessageDialog(frame, "Clement est l'auteur des objets ...",
 * "Attention", JOptionPane.WARNING_MESSAGE); } else if (cbox1.isSelected()) {
 * JOptionPane.showMessageDialog(frame, "L'auteur n'existe pas.", "Attention",
 * JOptionPane.WARNING_MESSAGE); } rs.close(); stmt.close(); c.close(); } catch
 * (Exception e1) { System.err.println(e1.getClass().getName() + ": " +
 * e1.getMessage()); System.exit(0); } } }
 * 
 * });
 * 
 * bu.setPreferredSize(d); pa1.add(bu); this.add(pa1);
 * 
 * JPanel pa2 = new JPanel(); JButton e = new JButton("Annuler ");
 * e.setPreferredSize(d); pa2.add(e); this.add(pa2);
 * 
 * /*e.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent arg0) { frame.dispose();
 * 
 * } });
 * 
 * this.setSize(640, 480); this.setVisible(true);
 * 
 * } public void ListeRecherche(List<String>l){ new ListAfterSearch(l); } }
 */