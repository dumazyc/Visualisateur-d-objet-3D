public class Face {
	private Segment s1;
	private Segment s2;
	private Segment s3;
	private int numero;

	public Face(Segment s1, Segment s2, Segment s3, int numero) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.numero = numero;
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
		return s1 + " " + s2 + " " + s3;
	}
}
