package menuetoptions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopUp extends JFrame {

	public PopUp() {
	JOptionPane jop3;
	jop3 = new JOptionPane();
	jop3.showMessageDialog(null, "Message d'erreur : Le cheveux de clement ne sont pas blond !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
