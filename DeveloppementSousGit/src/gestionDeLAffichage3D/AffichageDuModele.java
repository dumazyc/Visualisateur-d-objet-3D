package gestionDeLAffichage3D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.filechooser.FileFilter;

@SuppressWarnings("serial")
public class AffichageDuModele extends JFrame {
	PanelAffichage p;
	JMenuBar jmenubar;

	public AffichageDuModele() {
		p = new PanelAffichage();
		jmenubar = new JMenuBar();
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setTitle(p.nomDeLObjet);
		JMenu j1 = new JMenu("Fichier");
		JMenu j2 = new JMenu("Aide");
		jmenubar.add(j1);
		jmenubar.add(j2);
		this.setJMenuBar(jmenubar);
		this.add(p);
	}

	public static void main(String[] args) {
		new AffichageDuModele();
	}
}