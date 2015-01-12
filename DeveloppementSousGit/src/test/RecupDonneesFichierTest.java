package test;

import static org.junit.Assert.*;
import gestiondelaffichage3d.RecupDonneesFichier;
import org.junit.Before;
import org.junit.Test;

public class RecupDonneesFichierTest {
	RecupDonneesFichier test;
	
	@Before
	public void intitialisation(){
		test=new RecupDonneesFichier("cone", null);
	}

	@Test
	public void testGetName() {
		assertEquals("cone", test.getName());
		assertNotEquals("faux", test.getName());
			}

}
