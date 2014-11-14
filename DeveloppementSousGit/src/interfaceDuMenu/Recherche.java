package interfaceDuMenu;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Recherche extends JFrame implements ActionListener {

	protected static JFrame f=new JFrame();
	protected static Container c=f.getContentPane();

	public Recherche(){
		final JFrame f=new JFrame();
		Container c=f.getContentPane();
		c.setLayout(new GridLayout(5,1,2,2));


		Image wall=f.getToolkit().getImage("ressources/imageMenu/Recherche.png/");
		Icon ic=new ImageIcon(wall);
		JLabel jl=new JLabel(ic);
		c.add(jl);
		JButton bu=(new JButton("Recherche par forme"));
		bu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 RechercheForme.creation();;
				f.setVisible(false);
			}
		});

		JPanel toto=new JPanel();
		Dimension d1=new Dimension(10,30);
		toto.setPreferredSize(d1);
		toto.add(bu);
		JPanel tata=new JPanel();
		tata.add(toto);
		c.add(toto);
		JButton bu1=(new JButton("Recherche par type d'utilsation"));
		bu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 RechercheUtilsation.creation();
				f.setVisible(false);
			}
		});

		JPanel toto1=new JPanel();
		Dimension d11=new Dimension(10,30);
		toto1.setPreferredSize(d11);
		toto1.add(bu1);
		JPanel tata1=new JPanel();
		tata1.add(toto1);
		c.add(toto1);
		JButton bu11=(new JButton("Recherche par type de volume"));
		bu11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RechercheVolume.creation();
				f.setVisible(false);

			}
		});

		JPanel toto11=new JPanel();
		Dimension d111=new Dimension(10,30);
		toto11.setPreferredSize(d111);
		toto11.add(bu11);
		JPanel tata11=new JPanel();
		tata11.add(toto11);
		c.add(toto11);
		c.add(toto1);
		JButton bu111=(new JButton("Recherche par complexite du modele"));
		bu111.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					 RechercheComplexite.creation();;
					f.dispose();
			}
		});

		JPanel toto111=new JPanel();
		Dimension d1111=new Dimension(10,30);
		toto111.setPreferredSize(d1111);
		toto111.add(bu111);
		JPanel tata111=new JPanel();
		tata111.add(toto111);
		c.add(toto111);
		f.setTitle("");
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
	}


	public static void main(String [] args){
		new Recherche();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

} 