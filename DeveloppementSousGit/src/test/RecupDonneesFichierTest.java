package test;

import static org.junit.Assert.*;
import gestiondelaffichage3d.RecupDonneesFichier;
import org.junit.Before;
import org.junit.Test;
/**
 * Classe destinee a tester la classe RecupererDonneesFichier
 *
 */
public class RecupDonneesFichierTest {
	RecupDonneesFichier test;
	
	@Before
	@Test
	public void intitialisation(){
		test=new RecupDonneesFichier("cone", null);
	}

	@Test
	public void testGetName() {
		assertEquals("cone", test.getName());
		assertNotEquals("faux", test.getName());
		assertNotEquals("123445666", test.getName());
		assertNotEquals("fau)))))1&&:", test.getName());
		
			}

}
