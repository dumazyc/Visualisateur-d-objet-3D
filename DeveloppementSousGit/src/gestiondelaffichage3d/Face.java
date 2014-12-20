package gestiondelaffichage3d;

/**
 * Classe face qui est constitue de 3 segments et de 3 points.
 */
public class Face implements Comparable<Face> {
	private Segment s1;
	private Segment s2;
	private Segment s3;
	private Point p1;
	private Point p2;
	private Point p3;
	private int numero;

	/**
	 * Constructeur de la classe Face.
	 * 
	 * @param s1
	 *            Premier segment
	 * @param s2
	 *            Deuxieme segment
	 * @param s3
	 *            Troisieme segment
	 * @param numero
	 *            numero de la face
	 */
	public Face(Segment s1, Segment s2, Segment s3, int numero) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.numero = numero;
		p1 = this.initialisePoint(1);
		p2 = this.initialisePoint(2);
		p3 = this.initialisePoint(3);
	}

	/**
	 * Permet de recuperer le numero de la face.
	 * 
	 * @return numero de la face
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Permet de recuperer le premier segment de la face
	 * 
	 * @return premier segment de la face
	 */
	public Segment getS1() {
		return s1;
	}

	/**
	 * Permet de recuperer le deuxieme segment de la face
	 * 
	 * @return deuxieme segment de la face
	 */
	public Segment getS2() {
		return s2;
	}

	/**
	 * Permet de recuperer le troisieme segment de la face
	 * 
	 * @return troisieme segment de la face
	 */
	public Segment getS3() {
		return s3;
	}

	@Override
	public String toString() {
		return numero + "";
	}

	/**
	 * Permet de recuperer la profondeur de la face.
	 * 
	 * @return profondeur de la face.
	 */
	public double getProfondeur() {
		return (this.getPoint(1).getZ() + this.getPoint(2).getZ() + this
				.getPoint(3).getZ()) / 3;
	}

	/**
	 * Permet de modifier l'un des points de la face
	 * 
	 * @param numero
	 *            numero du point a modifier
	 * @param p
	 *            nouveau point
	 */
	public void setPoint(int numero, Point p) {
		if (numero == 1) {
			p1 = p;
		} else if (numero == 2) {
			p2 = p;
		} else if (numero == 3) {
			p3 = p;
		}
	}

	/**
	 * Permet de recuperer l'un des points de la face.
	 * 
	 * @param numero
	 *            numero du point que l'on veut recuperer
	 * @return le point que l'on eut recuperer
	 */
	public Point getPoint(int numero) {
		if (numero == 1) {
			return p1;
		} else if (numero == 2) {
			return p2;
		} else if (numero == 3) {
			return p3;
		} else {
			return null;
		}
	}

	/**
	 * Permet d'associer un numero a un point pour cette face
	 * 
	 * @param numero
	 *            numero que l'on veut donner au point
	 * @return le point qui sera associe a ce point
	 */
	private Point initialisePoint(int numero) {
		boolean dernierPoint = this.getS1().getP1().getNumero() != this.getS2()
				.getP1().getNumero()
				&& this.getS1().getP2().getNumero() != this.getS2().getP1()
						.getNumero();
		if (numero == 1) {
			return this.getS1().getP1();
		} else if (numero == 2) {
			return this.getS1().getP2();
		} else if (dernierPoint) {
			return this.getS2().getP1();
		} else {
			return this.getS2().getP2();
		}
	}

	@Override
	public int compareTo(Face f) {
		if (this.getProfondeur() - f.getProfondeur() < 0) {
			return -1;
		} else if (this.getProfondeur() - f.getProfondeur() > 0) {
			return 1;
		} else {
			return 0;
		}

	}
}
