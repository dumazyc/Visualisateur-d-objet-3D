package menuetoptions;

import gestiondelaffichage3d.PanelAffichage;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JTabbedPane;

public class CouleurObjet {
	public static Color couleur = new Color(255, 112, 0);

	public CouleurObjet(PanelAffichage a) {
		couleur = JColorChooser.showDialog(null,
				"Choisir une couleur pour l'objet", couleur);
		if (couleur == null) {
			couleur = Color.WHITE;
		}
		a.repaint();
	}
}
