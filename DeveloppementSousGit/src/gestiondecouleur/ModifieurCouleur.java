package gestiondecouleur;

import gestiondelaffichage3d.PanelAffichage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * La classe Modifieur permet de generer une fenetre avec des JSlider et un
 * CarreDeCouleur pour permettre une modification de la couleur selectionnee
 * 
 */
@SuppressWarnings("serial")
public class ModifieurCouleur extends JPanel {
	private JSlider sliderR;
	private JSlider sliderG;
	private JSlider sliderB;
	private CarreDeCouleur carre;
	private JLabel labelR;
	private JLabel labelG;
	private JLabel labelB;
	private Color couleurDuPanel;
	private boolean couleurDeFond;
	PanelAffichage panel;

	/**
	 * Constructeur de la classe Modifieur
	 * 
	 * @param panel
	 *            JPanel dont on veut modifier la couleur
	 * @param couleurDeFond
	 *            true si c'est pour modifier la couleur du fond, false si c'est
	 *            pour modifier la couleur de l'objet
	 */
	public ModifieurCouleur(PanelAffichage panel, boolean couleurDeFond) {
		this.couleurDeFond = couleurDeFond;
		this.panel = panel;

		
		this.setSize(420, 225);
		this.setVisible(true);
	
		this.setLayout(new GridLayout(1, 2));
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.setSize(250, 135);
		this.add(panel1);
		if (couleurDeFond) {
			couleurDuPanel = panel.getCouleurDeFond();
		} else {
			couleurDuPanel = panel.getCouleurObjet();
		}

		sliderR = new JSlider(0, 255, couleurDuPanel.getRed());
		sliderR.setBackground(Color.WHITE);
		sliderG = new JSlider(0, 255, couleurDuPanel.getGreen());
		sliderG.setBackground(Color.WHITE);
		sliderB = new JSlider(0, 255, couleurDuPanel.getBlue());
		sliderB.setBackground(Color.WHITE);
		carre = new CarreDeCouleur(couleurDuPanel.getRed(),
				couleurDuPanel.getGreen(), couleurDuPanel.getBlue());
		panel1.add(new JLabel("Nuance de rouge :"));
		labelR = new JLabel("" + couleurDuPanel.getRed());
		panel1.add(labelR);
		panel1.add(sliderR);
		panel1.add(new JLabel("Nuance de vert :"));
		labelG = new JLabel("" + couleurDuPanel.getGreen());
		panel1.add(labelG);
		panel1.add(sliderG);
		panel1.add(new JLabel("Nuance de bleu :"));
		labelB = new JLabel("" + couleurDuPanel.getBlue());
		panel1.add(labelB);
		panel1.add(sliderB);
		panel1.setBackground(Color.WHITE);
		this.add(carre);
		sliderR.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				labelR.setText(sliderR.getValue() + "");
				carre.setR(sliderR.getValue());

			}
		});
		sliderG.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				labelG.setText(sliderG.getValue() + "");
				carre.setG(sliderG.getValue());

			}
		});
		sliderB.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				labelB.setText(sliderB.getValue() + "");
				carre.setB(sliderB.getValue());

			}
		});
	}
	public void modifieCouleur(){
		if (couleurDeFond) {
			panel.setCouleurDeFond(new Color(getR(),getG(), getB()));
		} else {
			panel.setCouleurObjet(new Color(getR(),getG(), getB()));
		}

		panel.repaint();
	}
	private int getR() {
		return sliderR.getValue();
	}

	private int getB() {
		return sliderB.getValue();
	}

	private int getG() {
		return sliderG.getValue();
	}
}