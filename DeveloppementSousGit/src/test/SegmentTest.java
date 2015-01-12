package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class SegmentTest {
		gestiondelaffichage3d.Point p1 = new gestiondelaffichage3d.Point(1.1,2.2,3.3,4);
		gestiondelaffichage3d.Point p2 = new gestiondelaffichage3d.Point(1.1,2.2,3.3,4);
		gestiondelaffichage3d.Segment s1 = new gestiondelaffichage3d.Segment(p1, p2, 5);

	@Test
	public void testToString() {
		assertEquals(p1+" "+p2,s1.toString());
	}

	@Test
	public void testGetNumero() {

		assertEquals(5,s1.getNumero());
	}

	@Test
	public void testGetP1() {
		assertEquals(p1,s1.getP1());
	}

	@Test
	public void testGetP2() {
		assertEquals(p2,s1.getP2());
	}

}
