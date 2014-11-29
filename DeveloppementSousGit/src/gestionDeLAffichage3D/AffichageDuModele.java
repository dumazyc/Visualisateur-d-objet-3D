package gestionDeLAffichage3D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AffichageDuModele extends JFrame {
	PanelAffichage p;
	JMenuBar jmenubar;

	public AffichageDuModele(boolean b) {
		p = new PanelAffichage(b);
		jmenubar = new JMenuBar();
		
		
		int X =700;
		int Y = 700;
		//this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setSize(X, Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setBackground(Color.WHITE);
		
		this.setTitle(p.nomDeLObjet);
		JMenu j1 = new JMenu("Fichier");
		JMenu j2 = new JMenu("Aide");
		JMenuItem ajout = new JMenuItem("Ajouter un objet");
		JMenuItem recherche = new JMenuItem("Rechercher un objet");
		JMenuItem enregistre = new JMenuItem("Enregistrer sous..");
		JMenuItem fermer = new JMenuItem("Fermer");
		JMenuItem aide = new JMenuItem("?");
		JMenu resolution = new JMenu("Resolution");
		JRadioButtonMenuItem full= new JRadioButtonMenuItem("Plein ecran");
		JRadioButtonMenuItem full2= new JRadioButtonMenuItem("700*700", true);
		JRadioButtonMenuItem full3= new JRadioButtonMenuItem("xxx*xxx");
		ButtonGroup bg = new ButtonGroup();
	    bg.add(full);
	    bg.add(full2);
	    bg.add(full3);

	    
		// raccourci clavier
		ajout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
		recherche.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
		fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		
		j1.add(ajout);
		j1.add(recherche);
		j1.add(enregistre);
		j1.add(fermer);
		j2.add(aide);
		j2.add(resolution);
		resolution.add(full);
		resolution.add(full2);
		resolution.add(full3);
		jmenubar.add(j1);
		jmenubar.add(j2);
		this.setJMenuBar(jmenubar);
		this.add(p);
		
		
		
		
		// ouvre l'ajout d'objet
		ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AffichageDuModele(false);
			}
		});
		
		// ferme l'application  
		fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}        
		});
		
		//Ajout de l'aide
		aide.addActionListener(new ActionListener(){
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();      
				String mess = "Ctrl+A -> Ajouter objet\n";
				mess += "Ctrl+F -> Recherche objet\n";
				mess += "Alt+F4 -> Ferme l'application";
				jop.showMessageDialog(null, mess, "��� propos", JOptionPane.INFORMATION_MESSAGE);        
			}            
		});
		
			
		
		// si case cocher alors plein ecran
		full.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
				int Y = (int)tailleEcran.getHeight();
				int X = (int)tailleEcran.getWidth();
				AffichageDuModele.this.setSize(X, Y);
				AffichageDuModele.this.setLocationRelativeTo(null);
			}
		});
			
		// si case cocher alors taille fenetre = 700/700
		full2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AffichageDuModele.this.setSize(700, 700);
				
			}
		});
		// si case cocher alors taille fenetre = xxx
		full3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AffichageDuModele.this.setSize(1240, 720);
			}
		});
		
		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		new AffichageDuModele(true);
		
	}
}
