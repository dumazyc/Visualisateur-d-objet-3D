package gestiondelaffichage3d;
/**
 * Classe d'un point dans un espace tridimensionnel.
 */
public class Point {
	private Double x;
	private Double y;
	private Double z;
	private int numero;

	/**Constructeur de la classe Point.
	 * @param x coordonnee du point dans l'axe x.
	 * @param y coordonnee du point dans l'axe y.
	 * @param z coordonnee du point dans l'axe z.
	 * @param numero numero du point
	 */
	public Point(Double x, Double y, Double z, int numero) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return x + " " + y + " " + z;
	}
	/** Permet de recuperer la coordonnee du point dans l'axe x.
	 * @return coordonnee du point dans l'axe x.
	 */
	public Double getX() {
		return x;
	}
	/** Permet de recuperer la coordonnee du point dans l'axe y.
	 * @return coordonnee du point dans l'axe y.
	 */
	public Double getY() {
		return y;
	}
	/** Permet de recuperer la coordonnee du point dans l'axe z.
	 * @return coordonnee du point dans l'axe z.
	 */
	public Double getZ() {
		return z;
	}
	/** Permet de recuperer le numero du point.
	 * @return numero du point
	 */
	public int getNumero() {
		return numero;
	}

}
