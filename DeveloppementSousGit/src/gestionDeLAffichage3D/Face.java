package gestionDeLAffichage3D;
import java.awt.Color;
import java.util.Random;

public class Face implements Comparable<Face>{
	private Segment s1;
	private Segment s2;
	private Segment s3;
	private Point p1;
	private Point p2;
	private Point p3;
	
	
	
	private int numero;
	private Color color;

	public Face(Segment s1, Segment s2, Segment s3, int numero) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.numero = numero;
		Random r = new Random();
		this.color = new Color(r.nextInt(255) + 1, r.nextInt(255) + 1,
				r.nextInt(255) + 1);
		p1 = this.initialisePoint(1);
		p2 = this.initialisePoint(2);
		p3 = this.initialisePoint(3);
	}

	public int getNumero() {
		return numero;
	}

	public Segment getS1() {
		return s1;
	}

	public Segment getS2() {
		return s2;
	}

	public Segment getS3() {
		return s3;
	}

	@Override
	public String toString() {
		//return s1 + " " + s2 + " " + s3;
		return numero+"";
	}

	public Color getColor() {
		return color;
	}
	public double getProfondeur(){
		return (this.getPoint(1).getZ()+this.getPoint(2).getZ()+this.getPoint(3).getZ())/3;
	}
	public void setPoint(int numero, Point p){
		if (numero == 1) {
			p1 = p;
		}else if (numero == 2) {
			p2 = p;
		}else if (numero == 3) {
			p3 = p;
		}
	}
	
	public Point getPoint(int numero){
		if (numero == 1) {
			return p1;
		}else if (numero == 2) {
			return p2;
		}else if (numero == 3) {
			return p3;
		}else{
			return null;
		}
	}
	private Point initialisePoint(int numero){
		boolean dernierPoint = this.getS1().getP1().getNumero() != this.getS2().getP1().getNumero()&&this.getS1().getP2().getNumero()!= this.getS2().getP1().getNumero();
		if (numero==1) {
			return this.getS1().getP1();
		}else if (numero==2) {
			return this.getS1().getP2();
		}else if (dernierPoint){
			return this.getS2().getP1();
		}else{
			return this.getS2().getP2();
		}
	}

	@Override
	public int compareTo(Face f) {
		if (this.getProfondeur() - f.getProfondeur()<0) {
			return -1;
		}else if (this.getProfondeur() - f.getProfondeur()>0) {
			return 1;
		}else{
			return 0;
		}
		
	}
}
