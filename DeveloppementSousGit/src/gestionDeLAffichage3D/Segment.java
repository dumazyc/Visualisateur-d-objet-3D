package gestionDeLAffichage3D;
public class Segment {
	private Point p1;
	private Point p2;
	private int numero;

	public Segment(Point p1, Point p2, int numero) {
		this.p1 = p1;
		this.p2 = p2;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return p1 + " " + p2;
	}

	public int getNumero() {
		return numero;
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}
}
