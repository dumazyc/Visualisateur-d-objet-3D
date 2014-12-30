package menuetoptions;

import java.awt.Color;

import gestiondelaffichage3d.PanelAffichage;

import javax.swing.JColorChooser;
import javax.swing.JTabbedPane;

public class CouleurFond {

	public static Color couleur = Color.WHITE;

	public CouleurFond(PanelAffichage a) {

		couleur = JColorChooser.showDialog(null,
				"Choisir une couleur pour le fond", couleur);
		if (couleur == null) {
			couleur = Color.WHITE;
		}
		a.repaint();
	}
}
