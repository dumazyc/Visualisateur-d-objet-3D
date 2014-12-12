package menuetoptions;

import gestiondelaffichage3d.AffichageDuModele;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class Recherche extends JPanel {
	AffichageDuModele frame;
	List<String> listeRecherche = new ArrayList<String>();

	public Recherche(final AffichageDuModele a) {
		this.frame = a;
		Dimension d = new Dimension(100, 27);
		this.setLayout(new GridLayout(5, 1));

		final JPanel contentPane = new JPanel();

		final JTextField tfield = new JTextField(15);
		tfield.setEnabled(false);

		final JCheckBox cbox = new JCheckBox("Nom de l'objet: ", false);

		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				tfield.setEnabled(ie.getStateChange() == ItemEvent.SELECTED);
			}
		};
		cbox.addItemListener(itemListener);

		contentPane.add(cbox);
		contentPane.add(tfield);

		this.add(contentPane);

		final JPanel contentPane1 = new JPanel();

		final JTextField tfield1 = new JTextField(15);

		tfield1.setEnabled(false);

		final JCheckBox cbox1 = new JCheckBox("Auteur:    ", false);

		ItemListener itemListener1 = new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				tfield1.setEnabled(ie.getStateChange() == ItemEvent.SELECTED);

			}
		};
		cbox1.addItemListener(itemListener1);

		contentPane1.add(cbox1);
		contentPane1.add(tfield1);
		this.add(contentPane1);

		final JPanel contentPane2 = new JPanel();

		final JComboBox<String> perso = new JComboBox<String>();
		perso.setEnabled(false);
		perso.setBounds(100, 120, 100, 25);

		perso.addItem("1");
		perso.addItem("3");
		perso.addItem("3");
		perso.addItem("4");
		perso.addItem("5");
		perso.addItem("6");

		// String ac = perso.getSelectedItem().toString()

		final JCheckBox cbox2 = new JCheckBox("Nombre de trinangles:    ",
				false);

		ItemListener itemListener2 = new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				perso.setEnabled(ie.getStateChange() == ItemEvent.SELECTED);
			}
		};
		cbox2.addItemListener(itemListener2);

		contentPane2.add(cbox2);
		contentPane2.add(perso);
		contentPane.setPreferredSize(d);
		contentPane1.setPreferredSize(d);
		contentPane1.setPreferredSize(d);
		this.add(contentPane2);
		JPanel pa1 = new JPanel();
		JButton bu = new JButton("Valider ");
		bu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((cbox1.isSelected() && tfield1.getText().isEmpty())
						|| (cbox.isSelected() && tfield.getText().isEmpty())) {
					JOptionPane.showMessageDialog(frame,
							"Un champ coche ne peut etre vide.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Connection c = null;
					Statement stmt = null;
					try {
						Class.forName("org.sqlite.JDBC");
						c = DriverManager
								.getConnection("jdbc:sqlite:Database.db");
						c.setAutoCommit(false);

						stmt = c.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM OBJETS3D;");
						String name = rs.getString("name");
						String auteur = rs.getString("auteur");

						while (rs.next()) {
							name = rs.getString("name");
							if (name.equals(tfield.getText())) {
							listeRecherche.add(name);
							System.out.println(listeRecherche);
							}
						}
						
						while (!auteur.equals(tfield1.getText()) && rs.next()) {
							auteur = rs.getString("auteur");
						}
						
						if (cbox.isSelected()) {
							// JOptionPane.showMessageDialog(frame,"L'objet existe.","Attention",JOptionPane.WARNING_MESSAGE);

							ListAfterSearch l = new ListAfterSearch(
									listeRecherche);
							//a.nouvelOnglet(name);
							// frame.dispose();
						} else if (cbox.isSelected()) {
							// le cas auteur et le cas complexite devrait etre
							// gere
							JOptionPane.showMessageDialog(frame,
									"L'objet n'existe pas.", "Attention",
									JOptionPane.WARNING_MESSAGE);

						}
						if (cbox1.isSelected()
								&& auteur.equals(tfield1.getText())) {
							JOptionPane.showMessageDialog(frame,
									"Clement est l'auteur des objets ...",
									"Attention", JOptionPane.WARNING_MESSAGE);
						} else if (cbox1.isSelected()) {
							JOptionPane.showMessageDialog(frame,
									"L'auteur n'existe pas.", "Attention",
									JOptionPane.WARNING_MESSAGE);
						}
						rs.close();
						stmt.close();
						c.close();
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": "
								+ e1.getMessage());
						System.exit(0);
					}
				}
			}

		});

		bu.setPreferredSize(d);
		pa1.add(bu);
		this.add(pa1);

		JPanel pa2 = new JPanel();
		JButton e = new JButton("Annuler ");
		e.setPreferredSize(d);
		pa2.add(e);
		this.add(pa2);

		/*
		 * e.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { frame.dispose();
		 * 
		 * } });
		 */

		this.setSize(640, 480);
		this.setVisible(true);

	}

	public void ListeRecherche(List<String> l) {
		new ListAfterSearch(l);
	}
}