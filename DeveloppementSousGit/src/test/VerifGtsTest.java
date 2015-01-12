package test;

import static org.junit.Assert.*;
import gestiondelaffichage3d.VerifGts;

import org.junit.Before;
import org.junit.Test;

public class VerifGtsTest {
	VerifGts verifiacteur;
	@Before
	public void intitialisation(){
		verifiacteur=new VerifGts("./ressources/modeles/" + "cone.gts");

	}
	@Test
	public void testGtsEstCorrect() {
		assertTrue(verifiacteur.GtsEstCorrect());

	}

}
