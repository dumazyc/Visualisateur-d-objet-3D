package modeleVueControleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Gallerie extends JFrame implements Observer{
	private Controleur controler;
	protected ModelInsertion model;
	protected boolean sourceOfchange;
	Dimension d =new Dimension(80,80);
	 JPanel panel = new JPanel(new GridLayout(5, 10));
  public Gallerie(ModelInsertion model, AffichageDuModele a) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.model=model;
    controler = new Controleur(a, model);
	model.addObserver(this);
    for (int i = 0; i < 50; i++) {
      panel.add(new JLabel("  Label " + i));
    }
    //dans le listener de chaque button cree appeler la methode affichage du controleur

    JScrollPane scrolled = new JScrollPane(panel);
    scrolled.setRowHeaderView(new JLabel("Labels: "));

    getContentPane().add(scrolled, BorderLayout.CENTER);

    pack();
    setSize(300, 100);
    setVisible(true);
  }
  

 public void dessine(JButton jb,final String s){
	 jb= new JButton(s);
		jb.setPreferredSize(d);
		jb.setBackground(Color.red);
		panel.add(jb);
	jb.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			sourceOfchange=true;
			controler.affichageObjet(s);
			sourceOfchange=false;
		}
	});
 }

@Override
public void update(Observable o, Object arg) {
	if(!sourceOfchange&&!controler.RechercheAppelle){
		System.out.println("gallerie");
		dessine(new JButton((String)arg),(String)arg);
	
	}
	
}


}







	
