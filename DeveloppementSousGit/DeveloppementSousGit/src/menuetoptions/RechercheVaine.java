package menuetoptions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class RechercheVaine extends JFrame implements ActionListener{
	 static JFrame f=new JFrame();

	public static void main(String []args){
		
		Container c=f.getContentPane();
		c.setLayout(new GridLayout(5,1,1,1));
		//changer le smiley apres
		Image wall=f.getToolkit().getImage("ressources/imageMenu/imageIhm/confus.gif/");
		Icon ic=new ImageIcon(wall);
		JLabel jl=new JLabel(ic);
		c.add(jl);
		
		JPanel pa1=new JPanel();
		JPanel pa2=new JPanel();
		
		JButton b=new JButton(" Modifier vos criteres de recherche ");
		
		JButton e=new JButton(" Avoir des suggestions ");
	
		pa1.add(b);
		pa2.add(e);
	
		
		Image wall1=f.getToolkit().getImage("ressources/imageMenu/imageIhm/not.png/");
		Icon ic1=new ImageIcon(wall1);
		JLabel j2=new JLabel(ic1);
		c.add(j2);


		c.add(pa1);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Ajout();
				f.setVisible(false);
			}
		});
	
		c.add(pa2);
		e.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new ();
				f.setVisible(false);
			}
		});
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(640,480);
		f.setResizable(false);
		f.setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
