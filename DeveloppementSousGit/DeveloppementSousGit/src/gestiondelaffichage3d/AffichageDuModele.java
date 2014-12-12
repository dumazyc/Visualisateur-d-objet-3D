package gestiondelaffichage3d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import menuetoptions.Recherche;

import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AffichageDuModele extends JFrame {
	JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JMenuBar jmenubar;
	JTabbedPane j = new JTabbedPane(JTabbedPane.TOP);
	boolean recherche = false;
	Recherche r = new Recherche(this);
	private ImageIcon closeXIcon = new ImageIcon(
			"C:/Users/Cl�ment/Desktop/bite.png");

	private Dimension closeButtonSize = new Dimension(closeXIcon.getIconWidth() + 2,
			closeXIcon.getIconHeight() + 2);
	
	public AffichageDuModele() {
		
		jmenubar = new JMenuBar();
		j.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		int X = 700;
		int Y = 700;
		// this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setSize(X, Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setBackground(Color.WHITE);
		
		this.setTitle("ballin-octo-computing-machine");
		JMenu j1 = new JMenu("Fichier");
		JMenu j2 = new JMenu("Aide");
		JMenuItem ajout = new JMenuItem("Ajouter un objet");
		JMenuItem recherche = new JMenuItem("Rechercher un objet");
		JMenuItem enregistre = new JMenuItem("Enregistrer sous..");
		JMenuItem fermer = new JMenuItem("Fermer");
		JMenuItem aide = new JMenuItem("?");
		JMenu resolution = new JMenu("Resolution");
		JRadioButtonMenuItem full = new JRadioButtonMenuItem("Plein ecran");
		JRadioButtonMenuItem full2 = new JRadioButtonMenuItem("700*700", true);
		JRadioButtonMenuItem full3 = new JRadioButtonMenuItem("xxx*xxx");
		ButtonGroup bg = new ButtonGroup();
		bg.add(full);
		bg.add(full2);
		bg.add(full3);

		// raccourci clavier
		ajout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				KeyEvent.CTRL_MASK));
		recherche.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				KeyEvent.CTRL_MASK));
		fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				KeyEvent.ALT_DOWN_MASK));

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
		

		this.add(j, BorderLayout.CENTER);
		nouvelOnglet("icosa");
		
		sp.add(j,JSplitPane.RIGHT);
		sp.setVisible(true);
		this.add(sp);
		// ouvre l'ajout d'objet
		ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nouvelOnglet("cube");
				//new Ajout();
			}
		});

		// ferme l'application
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// Ajout de l'aide
		aide.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();
				String mess = "Ctrl+A -> Ajouter objet\n";
				mess += "Ctrl+F -> Recherche objet\n";
				mess += "Alt+F4 -> Ferme l'application";
				jop.showMessageDialog(null, mess, "A propos",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// ouvre la recherceh d'objet
		recherche.addActionListener(new tmpListener(this));
		// ferme l'application
		// si case cocher alors plein ecran
		full.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Dimension tailleEcran = Toolkit.getDefaultToolkit()
						.getScreenSize();
				int Y = (int) tailleEcran.getHeight();
				int X = (int) tailleEcran.getWidth();
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

	public void nouvelOnglet(String name) {
		final PanelAffichage p = new PanelAffichage(this,name);
		JPanel tab = new JPanel();
		tab.setOpaque(false);

		JLabel tabLabel = new JLabel(name);

		JButton tabCloseButton = new JButton(closeXIcon);
		tabCloseButton.setPreferredSize(closeButtonSize);
		tabCloseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int closeTabNumber = j.indexOfComponent(p);
				j.removeTabAt(closeTabNumber);
			}
		});

		tab.add(tabLabel, BorderLayout.WEST);
		tab.add(tabCloseButton, BorderLayout.EAST);

		j.addTab(p.nomDeLObjet, p);
		j.setTabComponentAt(j.getTabCount() - 1, tab);
		j.setSelectedIndex(j.getTabCount() - 1);
	}

	public class tmpListener implements ActionListener{
		AffichageDuModele a;
		public tmpListener(AffichageDuModele a) {
			this.a =a;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (recherche){
				sp.remove(r);
				recherche = false;
			}else{
			sp.add(r, JSplitPane.LEFT);
			recherche = true;
			}
			
			
		}
		
	}
	
	public static void main(String[] args) {
		new AffichageDuModele();

	}

	public double getHauteur() {
		
		return j.getSize().getHeight();
	}
public double getLargeur() {
		
		return j.getSize().getWidth();
	}
}