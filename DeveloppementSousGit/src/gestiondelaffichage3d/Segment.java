package gestiondelaffichage3d;

/**
 * Classe segment qui est constitue de 2 points.
 * 
 */
public class Segment {
	private Point p1;
	private Point p2;
	private int numero;

	/**
	 * Constructeur de la classe Segment.
	 * 
	 * @param p1
	 *            Premier point
	 * @param p2
	 *            Deuxieme point
	 * @param numero
	 *            numero du segment
	 */
	public Segment(Point p1, Point p2, int numero) {
		this.p1 = p1;
		this.p2 = p2;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return p1 + " " + p2;
	}

	/**
	 * Permet de recuperer le numero du segment.
	 * 
	 * @return numero du segment
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Permet de recuperer le premier point du segment.
	 * 
	 * @return premier point du segment
	 */
	public Point getP1() {
		return p1;
	}

	/**
	 * Permet de recuperer le deuxieme point du segment.
	 * 
	 * @return deuxieme point du segment
	 */
	public Point getP2() {
		return p2;
	}
}
