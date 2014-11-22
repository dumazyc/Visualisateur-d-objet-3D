package interfaceDuMenu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Acceuil extends JFrame implements ActionListener {

	protected static JFrame f=new JFrame();
	protected static Container c=f.getContentPane();

	public Acceuil(){
		final JFrame f=new JFrame();
		Container c=f.getContentPane();
		c.setLayout(new BorderLayout());


		Image wall=f.getToolkit().getImage("ressources/imageMenu/ruban.gif/");
		Icon ic=new ImageIcon(wall);
		JLabel jl=new JLabel(ic);
		c.add(jl,BorderLayout.CENTER);
		Image wallpi=f.getToolkit().getImage("ressources/imageMenu/acceuil.png/");
		Icon ico=new ImageIcon(wallpi);
		JLabel jla=new JLabel(ico);
		c.add(jla,BorderLayout.NORTH);
		JButton bu=(new JButton(" Cliquer ici pour acc�der au menu "));

		bu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Menu.creation();
				f.setVisible(false);

			}
		});


		JPanel toto=new JPanel();
		Dimension d1=new Dimension(10,30);
		toto.setPreferredSize(d1);
		toto.add(bu);
		JPanel tata=new JPanel();
		tata.add(toto);
		c.add(toto,BorderLayout.SOUTH);
		f.setTitle("");
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
	}



	public static void main(String [] args){
		new Acceuil();
	}




	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
//teste egit damien