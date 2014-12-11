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
import javax.swing.JCheckBox;
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

import menuetoptions.Ajout;
import menuetoptions.Recherche;

import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AffichageDuModele extends JFrame {
	JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JMenuBar jmenubar;
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	boolean recherche = false;
	Recherche r = new Recherche(this);
	private ImageIcon closeXIcon = new ImageIcon("./ressources/imageMenu/close.gif");
	private Dimension closeButtonSize = new Dimension(closeXIcon.getIconWidth() + 2,
			closeXIcon.getIconHeight() + 2);
	
	
	public AffichageDuModele() {
		
		jmenubar = new JMenuBar();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
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
		JMenu j2 = new JMenu("Options");
		JMenu j3 = new JMenu("Aide");
		JMenuItem ajout = new JMenuItem("Ajouter un objet");
		JMenuItem recherche = new JMenuItem("Rechercher un objet");
		JMenuItem enregistre = new JMenuItem("Enregistrer sous..");
		JMenuItem fermer = new JMenuItem("Fermer");
		JMenuItem aide = new JMenuItem("?");
		JMenuItem Zoom = new JMenuItem("Zoom par Default");
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
		aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));

		j1.add(ajout);
		j1.add(recherche);
		j1.add(enregistre);
		j1.add(fermer);
		j2.add(resolution);
		j2.add(Zoom);
		j3.add(aide);
		resolution.add(full);
		resolution.add(full2);
		resolution.add(full3);
		jmenubar.add(j1);
		jmenubar.add(j2);
		jmenubar.add(j3);
		this.setJMenuBar(jmenubar);
		

		this.add(tabbedPane, BorderLayout.CENTER);
		nouvelOnglet("space_station");
		
		sp.add(tabbedPane,JSplitPane.RIGHT);
		sp.setVisible(true);
		this.add(sp);
		
		// ouvre l'ajout d'objet
		ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//nouvelOnglet("cube");
				new Ajout();
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
				String mess = "Clique Gauche -> Bouge la figure \n";
				mess += "Clique droit -> Tourne la figure";
				jop.showMessageDialog(null, mess, "A propos",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// ouvre la recherche d'objet
		recherche.addActionListener(new tmpListener(this));

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
		
		//Si on clic sur bouton zoom par default, le zoom ce remet par default
		Zoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
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
				int closeTabNumber = tabbedPane.indexOfComponent(p);
				tabbedPane.removeTabAt(closeTabNumber);
			}
		});
		 
	    
		tab.add(tabLabel, BorderLayout.WEST);
		tab.add(tabCloseButton, BorderLayout.EAST);

		tabbedPane.addTab(p.nomDeLObjet, p);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);

		/*tabCloseButton.setMnemonic( 'N' );
		
		tabCloseButton.getActionMap().put( "clickButton", tabCloseButton.getAction() );
		tabCloseButton.getInputMap().put(KeyStroke.getKeyStroke( "control N" ), "clickButton" );*/
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
	
	public double getHauteur() {		
		return tabbedPane.getSize().getHeight();
	}
	
	public double getLargeur() {		
		return tabbedPane.getSize().getWidth();
	}
	
	
	public static void main(String[] args) {
		new AffichageDuModele();
	}
}