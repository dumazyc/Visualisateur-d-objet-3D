package test;

import static org.junit.Assert.*;
import gestiondelaffichage3d.Face;

import org.junit.Test;
/**
 * Classe destinee a tester la classe face
 *
 */
public class FaceTest {
	
	gestiondelaffichage3d.Point p1 = new gestiondelaffichage3d.Point(1.1,2.2,3.3,4);
	gestiondelaffichage3d.Point p2 = new gestiondelaffichage3d.Point(1.1,2.3,3.4,4);
	gestiondelaffichage3d.Point p3 = new gestiondelaffichage3d.Point(2.1,3.2,2.4,3);
	gestiondelaffichage3d.Point p4 = new gestiondelaffichage3d.Point(-1.1,-2.2,-3.3,4);
	gestiondelaffichage3d.Point p5 = new gestiondelaffichage3d.Point(-10.1,-20.3,-30.40,4);
	gestiondelaffichage3d.Point p6 = new gestiondelaffichage3d.Point(0.0,0.0,0.0,3);
	gestiondelaffichage3d.Segment s1 = new gestiondelaffichage3d.Segment(p1, p2, 5);
	gestiondelaffichage3d.Segment s2 = new gestiondelaffichage3d.Segment(p2, p3, 6);
	gestiondelaffichage3d.Segment s3 = new gestiondelaffichage3d.Segment(p3, p1, 7);
	gestiondelaffichage3d.Segment s4 = new gestiondelaffichage3d.Segment(p3, p1, 7);
	gestiondelaffichage3d.Segment s5 = new gestiondelaffichage3d.Segment(p3, p1, 7);
	gestiondelaffichage3d.Segment s6 = new gestiondelaffichage3d.Segment(p6, p6, 7);
	Face f1 = new Face(s1, s2, s3, 8);
	Face f2 = new Face(s6,s6,s6,9);
	Face f3 = new Face(s5,s5,s5,10);
	final double PRECISION = 1e-1;

	@Test
	public void testFace() {
		
	}

	@Test
	public void testGetNumero() {
		assertEquals(8,f1.getNumero());
	}

	@Test
	public void testGetS1() {
		assertEquals(s1,f1.getS1());
	}

	@Test
	public void testGetS2() {
		assertEquals(s2,f1.getS2());
	}

	@Test
	public void testGetS3() {
		assertEquals(s3,f1.getS3());
	}

	@Test
	public void testToString() {
		assertEquals( 8+"", f1.toString());
	}


	@Test
	public void testGetProfondeur() {
		assertEquals(3.0, f1.getProfondeur(),PRECISION);
	}



	@Test
	public void testCompareTo() {
		assertEquals( 1,f1.compareTo(f2));
		assertEquals( 0, f1.compareTo(f1), PRECISION);
		assertEquals( -1, f3.compareTo(f1));
	}

}
