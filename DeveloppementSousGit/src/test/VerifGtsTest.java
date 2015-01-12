package test;

import static org.junit.Assert.*;
import gestiondelaffichage3d.VerifGts;

import org.junit.Before;
import org.junit.Test;
/**
 * Classe destinee a tester la classe verifGts
 *
 */
public class VerifGtsTest {
	VerifGts verifiacteur;
	@Before
	@Test
	public void intitialisation(){
		verifiacteur=new VerifGts("./ressources/modeles/" + "cone.gts");

	}
	@Test
	public void testGtsEstCorrect() {
		assertTrue(verifiacteur.GtsEstCorrect());

	}

}
