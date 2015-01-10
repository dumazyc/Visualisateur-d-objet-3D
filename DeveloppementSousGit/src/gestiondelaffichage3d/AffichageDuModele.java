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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import menuetoptions.Ajout;
import menuetoptions.Description;
import menuetoptions.Enregistrer;
import menuetoptions.ModificationAjout;
import menuetoptions.OptionCouleur;
import menuetoptions.Recherche;

import java.awt.Toolkit;

/**
 * Fenetre principale du programme
 * 
 */
@SuppressWarnings("serial")
public class AffichageDuModele extends JFrame {
	private JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

	private JMenuBar jmenubar;
	public JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	public static JMenuItem modifierInfos = new JMenuItem("Modifier les infos");

	private boolean recherche = false;
	private Recherche r = new Recherche(this);
	private ImageIcon closeXIcon = new ImageIcon(
			"./ressources/imageMenu/close.gif");
	private Dimension closeButtonSize = new Dimension(
			closeXIcon.getIconWidth() + 2, closeXIcon.getIconHeight() + 2);
	private boolean musiqueActive = true;
	Description description;

	/**
	 * Constructeur de AffichageDuModele
	 */
	public AffichageDuModele() {
		jmenubar = new JMenuBar();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		int X = 900;
		int Y = 700;
		this.setSize(X, Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setBackground(Color.WHITE);
		this.setTitle("ballin-octo-computing-machine");
		JMenu j1 = new JMenu("Fichier");
		JMenu j2 = new JMenu("Options");
		JMenu j3 = new JMenu("Aide");
		JMenuItem ajout = new JMenuItem("Ajouter un objet");
		JMenuItem recherche = new JMenuItem("Rechercher un objet");
		JMenuItem enregistre = new JMenuItem("Enregistrer sous..");
		JMenuItem fermer = new JMenuItem("Fermer");
		JMenuItem aide = new JMenuItem("?");
		JMenuItem Zoom = new JMenuItem("Zoom par Default");
		JMenuItem color = new JMenuItem("Modifier les couleurs");
		JMenu resolution = new JMenu("Resolution");
		JMenuItem music = new JMenuItem("Activer/Desactiver la musique");

		JRadioButtonMenuItem full = new JRadioButtonMenuItem("Plein ecran");
		JRadioButtonMenuItem full2 = new JRadioButtonMenuItem("900*700", true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(full);
		bg.add(full2);

		// raccourci clavier
		ajout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				KeyEvent.CTRL_MASK));
		recherche.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				KeyEvent.CTRL_MASK));
		fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				KeyEvent.ALT_DOWN_MASK));
		aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		
		j1.add(ajout);
		j1.add(recherche);
		j1.add(enregistre);
		j1.add(fermer);
		j2.add(resolution);
		j2.add(Zoom);
		j2.add(music);
		j2.add(modifierInfos);
		j2.add(color);
		j3.add(aide);
		resolution.add(full);
		resolution.add(full2);
		jmenubar.add(j1);
		jmenubar.add(j2);
		jmenubar.add(j3);
		this.setJMenuBar(jmenubar);

		this.add(tabbedPane, BorderLayout.CENTER);
		

		sp.add(tabbedPane, JSplitPane.RIGHT);
		sp.setVisible(true);
		this.add(sp);

		// ouvre l'ajout d'objet
		ajout.addActionListener(new ActionListenerMaison(this));

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
				String mess = "Clique Gauche -> Bouge la figure \n";
				mess += "Clique droit -> Tourne la figure";
				jop.showMessageDialog(null, mess, "A propos",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// Option enregistrer
		enregistre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Enregistrer(tabbedPane.getTitleAt(tabbedPane
						.getSelectedIndex()));
			}
		});

		// ouvre la recherche d'objet

		recherche.addActionListener(new RechercheListener(this));

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

		// si case cocher alors taille fenetre = 900/700
		full2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AffichageDuModele.this.setSize(900, 700);

			}
		});

		// Si on clique sur le bouton zoom par default, le zoom se remet par
		// default
		Zoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PanelAffichage p = (PanelAffichage) tabbedPane
						.getComponentAt(tabbedPane.getSelectedIndex());
				p.remettreZoomParDefaut();
			}
		});

		music.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < tabbedPane.getTabCount(); i++) {
					PanelAffichage p = (PanelAffichage) tabbedPane
							.getComponentAt(i);
					p.desactiverMusique();
					if (musiqueActive) {
						musiqueActive = false;
					} else {
						musiqueActive = true;
					}
				}

			}
		});

		// pour modifier les informations associees a l'objet courant
		modifierInfos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ModificationAjout(tabbedPane.getTitleAt(tabbedPane
						.getSelectedIndex()));
			}
		});
		nouvelOnglet("space_station");

		// Gestion des couleurs
		
		color.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new OptionCouleur((PanelAffichage) tabbedPane.getComponentAt(tabbedPane
						.getSelectedIndex()));
			}
		});
		description = new Description(tabbedPane.getTitleAt(tabbedPane
				.getSelectedIndex()));
		add(description,BorderLayout.SOUTH); 
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (description!=null) {
					remove(description);
				}	
				description = new Description(tabbedPane.getTitleAt(tabbedPane
						.getSelectedIndex()));
				add(description,BorderLayout.SOUTH);
				invalidate();
				validate();
				repaint();
				
			}
		});
		this.setVisible(true);

	}

	/**
	 * Permet de faire un nouvel onglet.
	 * 
	 * @param name
	 *            nom de l'objet .gts a afficher (sans l'extension)
	 */
	public void nouvelOnglet(final String name) {
		final PanelAffichage p = new PanelAffichage(this, name, musiqueActive);
		JPanel tab = new JPanel();
		tab.setOpaque(false);

		JLabel tabLabel = new JLabel(name);

		JButton tabCloseButton = new JButton(closeXIcon);
		tabCloseButton.setPreferredSize(closeButtonSize);
		tabCloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int closeTabNumber = tabbedPane.indexOfComponent(p);
				tabbedPane.removeTabAt(closeTabNumber);
			}
		});
		

		tab.add(tabLabel, BorderLayout.WEST);
		tab.add(tabCloseButton, BorderLayout.EAST);	
		tabbedPane.addTab(name, p);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
		if (description!=null) {
			this.remove(description);
		}	
		description = new Description(name);
		add(description,BorderLayout.SOUTH);
		this.invalidate();
		this.validate();
		this.repaint();
	}

	/**
	 * Listener qui permet d'enlever d'afficher la fenetre recherche.
	 * 
	 */
	public class RechercheListener implements ActionListener {
		AffichageDuModele a;

		public RechercheListener(AffichageDuModele a) {
			this.a = a;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (recherche) {
				sp.remove(r);
				recherche = false;
			} else {
				sp.add(r, JSplitPane.LEFT);
				recherche = true;
			}
		}
	}


	/**
	 * Permet de connaitre la hauteur de de l'onglet courant.
	 * 
	 * @return hauteur de de l'onglet courant.
	 */
	public double getHauteur() {
		return tabbedPane.getSize().getHeight();
	}

	/**
	 * Permet de connaitre la largeur de de l'onglet courant.
	 * 
	 * @return largeur de de l'onglet courant.
	 */
	public double getLargeur() {
		return tabbedPane.getSize().getWidth();
	}

	/**
	 * Listener qui permet de faire appel a la fenetre d'ajout
	 * 
	 */
	public class ActionListenerMaison implements ActionListener {

		private AffichageDuModele a;

		public ActionListenerMaison(AffichageDuModele a) {
			this.a = a;
		}

		public void actionPerformed(ActionEvent e) {
			new Ajout(a);
		}

	}

	public static void main(String[] args) {
		new AffichageDuModele();
	}
}
