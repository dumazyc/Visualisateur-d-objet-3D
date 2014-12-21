package menuetoptions;

import java.awt.Color;

import gestiondelaffichage3d.PanelAffichage;

import javax.swing.JColorChooser;

public class CouleurFond {

	public static Color couleur = Color.WHITE;

	public CouleurFond(PanelAffichage p) {

		couleur = JColorChooser.showDialog(null,
				"Choisir une couleur pour le fond", couleur);
		if (couleur == null) {
			couleur = Color.WHITE;
		}
		p.repaint();
	}
}
