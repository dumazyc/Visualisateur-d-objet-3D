package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {
	gestiondelaffichage3d.Point p = new gestiondelaffichage3d.Point(1.1,2.2,3.3,4);
	final double PRECISION = 1e-15;
	
 
	@Test
	public void testToString() {
		assertEquals(p.toString(),"1.1 2.2 3.3");
		
	}

	@Test
	public void testGetX() {

		 assertEquals(1.1, p.getX(),PRECISION);
	}

	@Test
	public void testGetY() {

		 assertEquals(2.2, p.getY(),PRECISION);	
		 }

	@Test
	public void testGetZ() {
		 assertEquals(3.3, p.getZ(),PRECISION);
	}

	@Test
	public void testGetNumero() {
		 assertEquals(4, p.getNumero());
	}

}
