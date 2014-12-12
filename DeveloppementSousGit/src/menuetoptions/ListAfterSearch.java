package menuetoptions;

import gestiondelaffichage3d.AffichageDuModele;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
// affiche la listes apr�s recherche de l'utilisateur

public class ListAfterSearch {
	
	static JFrame frame = new JFrame("Resultats apres recherche");
	final static Container c=frame.getContentPane();
	 AffichageDuModele aff;
	public List <String>resultats=new ArrayList<String>();
	
	public ListAfterSearch(List<String>resultats){
		this.resultats=resultats;
		c.setLayout(new GridLayout(20,3,5,5));
		if(!resultats.isEmpty()){
			
			for(final String element:resultats){
				JButton bouton=new JButton(element);
				c.add(bouton);
				bouton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aff.nouvelOnglet(element);
					}
				});
				
			}
		}
		else
			System.out.println("La liste est vide !");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(640,480);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
}