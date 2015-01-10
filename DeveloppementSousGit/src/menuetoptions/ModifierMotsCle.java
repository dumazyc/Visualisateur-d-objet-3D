package menuetoptions;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ModifierMotsCle extends JFrame {
	public ModifierMotsCle() {
		this.setLayout(new GridLayout(2,1));
		this.setSize(700,700);
		// 3eme contentpane liste des mots cles.
		JPanel contentPane3 = new JPanel();
		JLabel label_motscles = new JLabel("Mots Cles : ");
		final DefaultListModel listModel_motscles = new DefaultListModel();
		final JList liste_mots_clef = new JList(listModel_motscles);
		//liste_mots_clef.setVisibleRowCount(10);
		// liste_mots_clef.setPreferredSize(new Dimension(120,150));
		JScrollPane jsp2 = new JScrollPane(liste_mots_clef);
		jsp2.setPreferredSize(new Dimension(120, 150));
		contentPane3.add(label_motscles);
		contentPane3.add(jsp2);
		final JButton supprimer = new JButton("Supprimer");
		supprimer.setEnabled(false);
		liste_mots_clef.setEnabled(false);
		contentPane3.add(supprimer);
		contentPane3.setPreferredSize(new Dimension(10, 180));
		this.add(contentPane3);

		// 4eme contentpane modification des mots cles.

		JPanel contentPane4 = new JPanel();
		final JTextField tfield2 = new JTextField("New mots cles", 10);
		final JButton modifier = new JButton("modifier");
		final JButton ajouter = new JButton("Ajouter");
		tfield2.setEnabled(false);
		ajouter.setEnabled(false);
		modifier.setEnabled(false);
		contentPane4.add(tfield2);
		contentPane4.add(modifier);
		contentPane4.add(ajouter);
		this.add(contentPane4);
		this.setVisible(true);
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
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			String requete = "SELECT MOTCLE FROM OBJETS3D INNER JOIN MOTSCLES ON OBJETS3D.ID = MOTSCLES.ID_M WHERE NAME = 'space_station'";

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

	public static void main(String[] args) {
		new ModifierMotsCle();
	}
}
