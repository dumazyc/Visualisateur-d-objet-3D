package menuetoptions;

import gestiondelaffichage3d.AffichageDuModele;

import java.awt.Color;

import javax.swing.JColorChooser;

public class CouleurObjet{
	AffichageDuModele a;
	public static Color couleur = new Color(255,112,0);

	public CouleurObjet(AffichageDuModele a){
		couleur = JColorChooser.showDialog(null, "Choisir une couleur pour l'objet", couleur);
        if(couleur == null){
            couleur = Color.WHITE;
        }
	}
}
