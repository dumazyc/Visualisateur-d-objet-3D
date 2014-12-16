package menuetoptions;

import java.awt.Color;

import gestiondelaffichage3d.AffichageDuModele;

import javax.swing.JColorChooser;
import javax.swing.JFrame;


public class CouleurFond{
	AffichageDuModele a;
	public static Color couleur = Color.WHITE;

	public CouleurFond(AffichageDuModele a){
		couleur = JColorChooser.showDialog(null, "Choisir une couleur pour le fond", couleur);
        if(couleur == null){
            couleur = Color.WHITE;
        }
	}
}
