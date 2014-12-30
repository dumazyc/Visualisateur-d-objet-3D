package gestiondecouleur;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * La classe CarreDeCouleur Permet de creer un JPanel qui contient une couleur.
 * 
 */
@SuppressWarnings("serial")
public class CarreDeCouleur extends JPanel {
	Color color;

	/**
	 * Constructeur de la classe CarreDeCouleur
	 * 
	 * @param nb1
	 *            Nuance de Rouge
	 * @param nb2
	 *            Nuance de Vert
	 * @param nb3
	 *            Nuance de Bleu
	 */
	public CarreDeCouleur(int nb1, int nb2, int nb3) {
		this.color = new Color(nb1, nb2, nb3);
		//this.setBackground(new Color(255, 255, 255));
	}

	/**
	 * Permet de modifier la nuance du rouge
	 * 
	 * @param nb
	 *            nouvelle nuance de rouge
	 */
	public void setR(int nb) {
		this.color = new Color(nb, this.color.getGreen(), this.color.getBlue());
		this.repaint();
	}

	/**
	 * Permet de modifier la nuance du vert
	 * 
	 * @param nb
	 *            nouvelle nuance de vert
	 */
	public void setG(int nb) {
		this.color = new Color(this.color.getRed(), nb, this.color.getBlue());
		this.repaint();
	}

	/**
	 * Permet de modifier la nuance du bleu
	 * 
	 * @param nb
	 *            nouvelle nuance de bleu
	 */
	public void setB(int nb) {
		this.color = new Color(this.color.getRed(), this.color.getGreen(), nb);
		this.repaint();
	}

	/**
	 * Permet de recuperer la nuance de rouge
	 * 
	 * @return nuance de rouge
	 */
	public int getR() {
		return this.color.getRed();
	}

	/**
	 * Permet de recuperer la nuance de vert
	 * 
	 * @return nuance de vert
	 */
	public int getG() {
		return this.color.getGreen();
	}

	/**
	 * Permet de recuperer la nuance de bleu
	 * 
	 * @return nuance de bleu
	 */
	public int getB() {
		return this.color.getBlue();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(this.color);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 500, 500);

	}
}
