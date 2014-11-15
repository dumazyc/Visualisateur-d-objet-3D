package interfaceDuMenu;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Menu extends JFrame implements ActionListener{
	 static JFrame f=new JFrame();

	public static void creation(){

		
		Container c=f.getContentPane();
		c.setLayout(new BorderLayout());
		Image wall=f.getToolkit().getImage("ressources/imageMenu/menu.gif/");
		Icon ic=new ImageIcon(wall);
		JLabel jl=new JLabel(ic);
		c.add(jl,BorderLayout.NORTH);
		JFrame f1=new JFrame();
		Container c1=f1.getContentPane();
		c1.setLayout(new GridLayout(4,1,6,3));
		Dimension d=new Dimension(200,50);
		JPanel pa1=new JPanel();
		JPanel pa2=new JPanel();
		JPanel pa3=new JPanel();


		JButton b=new JButton("Ajout d'objets");
		b.setPreferredSize(d);
		JButton e=new JButton("Recherche d'objets");
		e.setPreferredSize(d);
		JButton g=new JButton("Retour");
		g.setPreferredSize(d);

		pa1.add(b);
		pa2.add(e);
		pa3.add(g);
		
		c.add(c1,BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(640,480);
		f.setResizable(false);
		f.setVisible(true);


		e.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Recherche();
				f.setVisible(false);
			}
		});

		c1.add(pa1);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Ajout();
				f.setVisible(false);
			}
		});
		c1.add(pa2);
		/*e.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ();
				f.setVisible(false);
			}
		});*/
		c1.add(pa3);
		g.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Acceuil();
				f.dispose();;
			}
		});



		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}