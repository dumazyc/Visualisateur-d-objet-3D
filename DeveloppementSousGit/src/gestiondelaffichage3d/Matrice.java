package gestiondelaffichage3d;

import java.awt.Polygon;

/**
 * Classe qui simule une matrice.
 *
 */
public class Matrice {
	private double[][] m;

	/**
	 * Constructeur qui permet de creer une matrice 3x3
	 * 
	 * @param x
	 *            coordonnees en x
	 * @param y
	 *            coordonnees en y
	 * @param z
	 *            coordonnees en z
	 */
	public Matrice(double[] x, double[] y, double[] z) {
		m = new double[3][3];
		m[0] = x;
		m[1] = y;
		m[2] = z;
	}

	/**
	 * Permet de recuperer un tableau contenant toutes les coordonnees en x.
	 * 
	 * @return toutes les cordonnees en x
	 */
	public double[] getTabX() {
		return m[0];

	}

	/**
	 * Permet de recuperer un tableau contenant toutes les coordonnees en y.
	 * 
	 * @return toutes les cordonnees en y
	 */
	public double[] getTabY() {
		return m[1];

	}

	/**
	 * Permet de recuperer un tableau contenant toutes les coordonnees en z.
	 * 
	 * @return toutes les cordonnees en z
	 */
	public double[] getTabZ() {
		return m[2];

	}

	/**
	 * Permet de multiplier une matrice avec une autre.
	 * 
	 * @param m
	 *            deuxieme matrice du produit
	 * @return le matrice resultat de la multiplication
	 */
	public Matrice multiplicate(Matrice m) {
		double[] m1 = new double[] {
				(m.m[0][0] * this.m[0][0] + m.m[1][0] * this.m[0][1] + m.m[2][0]
						* this.m[0][2]),
				(m.m[0][1] * this.m[0][0] + m.m[1][1] * this.m[0][1] + m.m[2][1]
						* this.m[0][2]),
				(m.m[0][2] * this.m[0][0] + m.m[1][2] * this.m[0][1] + m.m[2][2]
						* this.m[0][2]) };
		double[] m2 = new double[] {
				(m.m[0][0] * this.m[1][0] + m.m[1][0] * this.m[1][1] + m.m[2][0]
						* this.m[1][2]),
				(m.m[0][1] * this.m[1][0] + m.m[1][1] * this.m[1][1] + m.m[2][1]
						* this.m[1][2]),
				(m.m[0][2] * this.m[1][0] + m.m[1][2] * this.m[1][1] + m.m[2][2]
						* this.m[1][2]) };
		double[] m3 = new double[] {
				(m.m[0][0] * this.m[2][0] + m.m[1][0] * this.m[2][1] + m.m[2][0]
						* this.m[2][2]),
				(m.m[0][1] * this.m[2][0] + m.m[1][1] * this.m[2][1] + m.m[2][1]
						* this.m[2][2]),
				(m.m[0][2] * this.m[2][0] + m.m[1][2] * this.m[2][1] + m.m[2][2]
						* this.m[2][2]) };

		return new Matrice(m1, m2, m3);
	}

	/**
	 * Permet de faire la rotation par rapport a l'axe des x.
	 * 
	 * @param angle
	 *            angle en degre dont il faut effectuer la rotation
	 * @return la matrice avec les nouvelles coordonnees
	 */
	public Matrice rotateX(double angle) {
		angle = Math.toRadians(angle);
		Matrice mx = new Matrice(new double[] { 1, 0, 0 }, new double[] { 0,
				Math.cos(angle), -Math.sin(angle) }, new double[] { 0,
				Math.sin(angle), Math.cos(angle) });
		return mx.multiplicate(this);
	}

	/**
	 * Permet de faire la rotation par rapport a l'axe des y.
	 * 
	 * @param angle
	 *            angle en degre dont il faut effectuer la rotation
	 * @return la matrice avec les nouvelles coordonnees
	 */
	public Matrice rotateY(double angle) {
		angle = Math.toRadians(angle);
		Matrice my = new Matrice(new double[] { Math.cos(angle), 0,
				Math.sin(angle) }, new double[] { 0, 1, 0 }, new double[] {
				-Math.sin(angle), 0, Math.cos(angle) });
		return my.multiplicate(this);
	}

	/**
	 * Permet de faire la rotation par rapport a l'axe des z.
	 * 
	 * @param angle
	 *            angle en degre dont il faut effectuer la rotation
	 * @return la matrice avec les nouvelles coordonnees
	 */
	public Matrice rotateZ(double angle) {
		angle = Math.toRadians(angle);
		Matrice mz = new Matrice(new double[] { Math.cos(angle),
				-Math.sin(angle), 0 }, new double[] { Math.sin(angle),
				Math.cos(angle), 0 }, new double[] { 0, 0, 1 });
		return mz.multiplicate(this);
	}

	/**
	 * Permet de generer un polygon dans un espace 2D.
	 * 
	 * @return un polygon en 2D
	 */
	public Polygon PolygonGeneratorFromMatrice() {
		int[] x = new int[] { (int) m[0][0], (int) m[0][1], (int) m[0][2] };
		int[] y = new int[] { (int) m[1][0], (int) m[1][1], (int) m[1][2] };
		return new Polygon(x, y, 3);
	}

	@Override
	public String toString() {
		return "|" + m[0][0] + "|" + m[0][1] + "|" + m[0][2] + "|" + "\n" + "|"
				+ m[1][0] + "|" + m[1][1] + "|" + m[1][2] + "|" + "\n" + "|"
				+ m[2][0] + "|" + m[2][1] + "|" + m[2][2] + "|" + "\n";
	}
}
