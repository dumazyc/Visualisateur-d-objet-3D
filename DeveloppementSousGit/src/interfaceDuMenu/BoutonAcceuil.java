package interfaceDuMenu;


import java.awt.event.*;

public class BoutonAcceuil implements ActionListener {

	public BoutonAcceuil(){}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(" Cliquer ici pour acc�der au menu ")) {
			Menu.creation();
		}
	}
}






