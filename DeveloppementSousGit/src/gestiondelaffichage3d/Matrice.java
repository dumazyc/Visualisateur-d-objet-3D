package gestiondelaffichage3d;
import java.awt.Polygon;

public class Matrice {
	double[][] m;

	public Matrice(double[] x, double[] y, double[] z) {
		m = new double[3][];
		m[0] = x;
		m[1] = y;
		m[2] = z;
	}

	public double[] getTabX() {
		return m[0];

	}

	public double[] getTabY() {
		return m[1];

	}

	public double[] getTabZ() {
		return m[2];

	}

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

	public Matrice rotateX(double angle) {
		angle = Math.toRadians(angle);
		Matrice mx = new Matrice(new double[] { 1, 0, 0 }, new double[] { 0,
				Math.cos(angle), -Math.sin(angle) }, new double[] { 0,
				Math.sin(angle), Math.cos(angle) });
		return mx.multiplicate(this);
	}

	public Matrice rotateY(double angle) {
		angle = Math.toRadians(angle);
		Matrice my = new Matrice(new double[] { Math.cos(angle), 0,
				Math.sin(angle) }, new double[] { 0, 1, 0 }, new double[] {
				-Math.sin(angle), 0, Math.cos(angle) });
		return my.multiplicate(this);
	}

	public Matrice rotateZ(double angle) {
		// TODO
		angle = Math.toRadians(angle);
		Matrice mz = new Matrice(new double[] { Math.cos(angle),
				-Math.sin(angle), 0 }, new double[] { Math.sin(angle),
				Math.cos(angle), 0 }, new double[] { 0, 0, 1 });
		return mz.multiplicate(this);
	}

	public Polygon PolygonGeneratorFromMatrice() {
		int[] x = new int[] { (int) m[0][0], (int) m[0][1], (int) m[0][2] };
		int[] y = new int[] { (int) m[1][0], (int) m[1][1], (int) m[1][2] };
		return new Polygon(x, y, 3);
	}
	public double getProfondeur(){
		return (m[2][0]+m[2][1]+m[2][2])/3;
	}
	@Override
	public String toString() {
		return "|" + m[0][0] + "|" + m[0][1] + "|" + m[0][2] + "|" + "\n" + "|"
				+ m[1][0] + "|" + m[1][1] + "|" + m[1][2] + "|" + "\n" + "|"
				+ m[2][0] + "|" + m[2][1] + "|" + m[2][2] + "|" + "\n";
	}
}
