package test;

import static org.junit.Assert.*;

import gestiondelaffichage3d.Matrice;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe destinee a tester la classe Matrice
 *
 */
public class MatriceTest {
	Matrice matrice1;
	Matrice matrice2;
	Matrice matrice3;
	double[] x;
	double[] y;
	double[] z;
	double[] z1;
	double[] y1;	
	double[] x1;
	double[] y11;
	double[] z11;
	double[] x11;
	double angle;
	Matrice mz;
	Matrice my;
	Matrice mx;
	@Test
	@Before
	public void intitialisation(){
		angle = Math.toRadians(angle);
		mz = new Matrice(new double[] { Math.cos(angle),
				-Math.sin(angle), 0 }, new double[] { Math.sin(angle),
				Math.cos(angle), 0 }, new double[] { 0, 0, 1 });
		my= new Matrice(new double[] { Math.cos(angle), 0,
				Math.sin(angle) }, new double[] { 0, 1, 0 }, new double[] {
				-Math.sin(angle), 0, Math.cos(angle) });
		angle = Math.toRadians(angle);
		mx = new Matrice(new double[] { 1, 0, 0 }, new double[] { 0,
				Math.cos(angle), -Math.sin(angle) }, new double[] { 0,
				Math.sin(angle), Math.cos(angle) });

		x = new double[]{2,4,1};
		y = new double[]{3,5,1};
		z = new double[]{1,1,1};
		x1 = new double[]{1,1,1};
		y1 = new double[]{1,3,1};
		z1 = new double[]{1,1,1};
		x11 = new double[]{7,15,7};
		y11 = new double[]{9,19,9};
		z11 = new double[]{3,5,3};


		matrice1=new Matrice(x, y, z);
		matrice2=new Matrice(x1, y1, z1);
		matrice3=new Matrice(x11, y11, z11);
	}


	@Test
	public void testGetTabX() {
		assertEquals(x,matrice1.getTabX());
		assertNotEquals(y, matrice1.getTabX());
		assertNotEquals(2, matrice1.getTabX());
		assertNotEquals("dsrt", matrice1.getTabX());
	}

	@Test
	public void testGetTabY() {
		assertEquals(y1,matrice2.getTabY());
		assertNotEquals(y, matrice2.getTabY());
		assertNotEquals(3, matrice2.getTabY());
		assertNotEquals("gt", matrice2.getTabY());
	}

	@Test
	public void testGetTabZ() {
		assertEquals(z,matrice1.getTabZ());
		assertNotEquals(y, matrice1.getTabZ());
		assertNotEquals(5, matrice1.getTabZ());
		assertNotEquals("gtmo", matrice1.getTabZ());
	}

	@Test
	public void testMultiplicate() {

		assertEquals(matrice3.toString(), matrice1.multiplicate(matrice2).toString());
		assertNotEquals(matrice2.toString(), matrice1.multiplicate(matrice2).toString());
	}

	@Test
	public void testRotateX() {
		assertEquals(mx.multiplicate(matrice1).toString(),matrice1.rotateZ(angle).toString());
		assertNotEquals(mx.multiplicate(matrice3).toString(),matrice1.rotateZ(angle).toString());
	}

	@Test
	public void testRotateY() {
		assertEquals(my.multiplicate(matrice1).toString(),matrice1.rotateZ(angle).toString());
		assertNotEquals(my.multiplicate(matrice2).toString(),matrice1.rotateZ(angle).toString());
	}

	@Test
	public void testRotateZ() {
		assertEquals(mz.multiplicate(matrice1).toString(),matrice1.rotateZ(angle).toString());
		assertNotEquals(mz.multiplicate(matrice2).toString(),matrice1.rotateZ(angle).toString());
	}


	@Test
	public void testToString(){
		assertEquals("|7.0|15.0|7.0|"+"\n"
				+"|9.0|19.0|9.0|"+"\n"
				+"|3.0|5.0|3.0|"+"\n",matrice1.multiplicate(matrice2).toString());
	}

}
