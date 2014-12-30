package menuetoptions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gestiondecouleur.ModifieurCouleur;
import gestiondelaffichage3d.PanelAffichage;

@SuppressWarnings("serial")
public class OptionCouleur extends JFrame {
	private JButton oKButton = new JButton("OK");
	private ModifieurCouleur modifieurFond;
	private ModifieurCouleur modifieurObjet;

	public OptionCouleur(PanelAffichage a) {
		this.setTitle("Modifier les couleurs");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setSize(420, (int) (255 * 2.5));
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		JPanel principal = new JPanel();
		principal.setSize(420, (int) (225 * 2.1));
		principal.setLayout(new GridLayout(2, 1));
		JPanel panel1 = new JPanel(new BorderLayout());
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(50, 35));
		panel2.add(new JLabel("Couleur du fond :"));
		panel2.setBackground(Color.WHITE);
		panel1.add(panel2, BorderLayout.NORTH);
		modifieurFond = new ModifieurCouleur(a, true);
		panel1.add(modifieurFond, BorderLayout.CENTER);
		principal.add(panel1);

		JPanel panel3 = new JPanel(new BorderLayout());
		JPanel panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(50, 35));
		panel4.add(new JLabel("Couleur de l'objet :"));
		panel4.setBackground(Color.WHITE);

		panel3.add(panel4, BorderLayout.NORTH);
		modifieurObjet = new ModifieurCouleur(a, false);
		panel3.add(modifieurObjet, BorderLayout.CENTER);
		principal.add(panel3);
		this.add(principal, BorderLayout.CENTER);
		this.add(oKButton, BorderLayout.SOUTH);
		oKButton.addActionListener(new ButtonListener(this));
	}

	private class ButtonListener implements ActionListener {

		private OptionCouleur option;

		public ButtonListener(OptionCouleur optionCouleur) {
			this.option = optionCouleur;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			option.modifieurObjet.modifieCouleur();
			option.modifieurFond.modifieCouleur();
			option.dispose();
		}

	}
}
