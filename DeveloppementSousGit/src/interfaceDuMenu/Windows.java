package interfaceDuMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class Windows extends JFrame {
	
	private JMenuBar menubar;

	
	public Windows() {
		super("maquette_1");
		this.setSize(2024, 1768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JPanel zone_Mode = new JPanel();
		JPanel bas = new JPanel();
		this.menubar = new JMenuBar();
		this.setLayout(new BorderLayout());
		zone_Mode.setBackground(Color.black);
		bas.setBackground(Color.blue);
		bas.setPreferredSize(new Dimension(1000,120));

		
		this.setMenu();

		//this.add(menubar, BorderLayout.NORTH);
		this.add(zone_Mode, BorderLayout.CENTER);
		this.add(bas, BorderLayout.SOUTH);
		zone_Mode.setLayout(new GridLayout(1, 2));
		JPanel obj1 = new JPanel();
		JPanel obj2 = new JPanel();
		JPanel obj3 = new JPanel();
		JPanel obj4 = new JPanel();
		JPanel obj5 = new JPanel();		
		JPanel obj6 = new JPanel();
		JPanel obj7 = new JPanel();
		JPanel obj8 = new JPanel();
		JPanel obj9 = new JPanel();
		JPanel obj10 = new JPanel();
		obj1.setPreferredSize(new Dimension(120,110));
		obj2.setPreferredSize(new Dimension(120,110));
		obj3.setPreferredSize(new Dimension(120,110));
		obj4.setPreferredSize(new Dimension(120,110));
		obj5.setPreferredSize(new Dimension(120,110));
		obj6.setPreferredSize(new Dimension(120,110));
		obj7.setPreferredSize(new Dimension(120,110));
		obj8.setPreferredSize(new Dimension(120,110));
		obj9.setPreferredSize(new Dimension(120,110));
		obj10.setPreferredSize(new Dimension(120,110));
		bas.add(obj1);
		bas.add(obj2);
		bas.add(obj3);
		bas.add(obj4);
		bas.add(obj5);
		bas.add(obj6);
		bas.add(obj7);
		bas.add(obj8);
		bas.add(obj9);
		bas.add(obj10);
		
		}
	
	public void setMenu() {
		JMenu j1 = new JMenu("Fichier");
		JMenu j2 = new JMenu("Aide");
		JMenuItem ajout = new JMenuItem("Ajouter un objet");
		JMenuItem recherche = new JMenuItem("Rechercher un objet");
		JMenuItem enregistre = new JMenuItem("Enregistrer sous..");
		JMenuItem fermer = new JMenuItem("Fermer");
		JMenuItem aide = new JMenuItem("?");
		
		j1.add(ajout);
		j1.add(recherche);
		j1.add(enregistre);
		j1.add(fermer);
		j2.add(aide);
		menubar.add(j1);
		menubar.add(j2);
		this.setJMenuBar(menubar);
		//this.add(p);
		
	}
	
	public static void main(String[] args) {
		JFrame f = new Windows();
		f.setVisible(true);
		JFrame p = new PopUp();
		p.setVisible(true);
	}

}