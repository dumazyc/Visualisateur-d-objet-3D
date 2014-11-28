package interfaceDuMenu;
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

@SuppressWarnings("serial")
public class Ajout extends JFrame {
	
	private boolean RecupDonneeFichier(){
		JFileChooser fc = new JFileChooser("ressources/modeles/");
		fc.setAcceptAllFileFilterUsed(false);
		fc.addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				 return f.getName().endsWith(".gts");
			}
			@Override
			public String getDescription() {
				return "Fichier GNU Triangulated Surface Library (.gts)";
			}
		});
		fc.addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				 return f.getName().endsWith(".txt");
			}
			@Override
			public String getDescription() {
				return "Fichier texte (.txt)";
			}
		});
		fc.showOpenDialog(getParent());
		this.setTitle("Affichage de " + fc.getSelectedFile().getName());
		return rootPaneCheckingEnabled;
		
	}

	
	public Ajout() {
			RecupDonneeFichier();
			this.setSize(700, 700);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setResizable(true);
			this.setBackground(Color.WHITE);
			this.setVisible(true);
	}
	
	
	
	/*
	public void paint (Graphics ga){
		Graphics2D g = (Graphics2D)ga;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0,3000, 3000);		
		Face tableau[] = new Face[list_faces.size()];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i]=list_faces.get(i);
		}
		quickSort(tableau,0,tableau.length-1);
		for (int i = tableau.length-1; i >-1; i--) {
			g.setColor(new Color(255-(i*255)/tableau.length,255-(i*255)/tableau.length,255-(i*255)/tableau.length));
			//g.setColor(tableau[i].getColor());
			//g.setColor(new Color(r.nextInt(255)+1, r.nextInt(255)+1, r.nextInt(255)+1));
			g.fill(generatePolygon(tableau[i],t.getCoeffXetY(),t.getDecalageX(),t.getDecalageY()));
		}
		System.out.println("Zoom = "+t.getCoeffXetY());
	}
	*/
	
	
}
