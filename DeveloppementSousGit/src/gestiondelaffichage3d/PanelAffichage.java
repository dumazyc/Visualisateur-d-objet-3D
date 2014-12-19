package gestiondelaffichage3d;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import menuetoptions.CouleurFond;
import menuetoptions.CouleurObjet;

@SuppressWarnings("serial")
public class PanelAffichage extends JPanel {
	AffichageDuModele a;
	private List<Point> list_points = new ArrayList<Point>();
	private List<Segment> list_segments = new ArrayList<Segment>();
	private List<Face> list_faces = new ArrayList<Face>();
	String nomDeLObjet;
	int decalageX =0;
	int decalageY=0;
	int rotationX =0;
	int rotationY=0;
	int rotationZ =0;
	int mouseX = 0;
	int mouseY = 0;
	double zoom = 1;
	Double max;
	boolean ligneOrNot = false;
	
	private boolean RecupDonneeFichier(String name){
		/*List<String> fichier = new ArrayList<String>();
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
		try {
			String ligne;
			FileReader flux;
			BufferedReader entree;
			flux = new FileReader(fc.getSelectedFile().getAbsolutePath());
			entree = new BufferedReader(flux);
			while ((ligne = entree.readLine()) != null) {
				fichier.add(ligne);
			}
			entree.close();
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
		nomDeLObjet = fc.getSelectedFile().getName();
		name = nomDeLObjet;*/
		Scanner s = null;
		try {
			s = new Scanner(new File("./ressources/modeles/"+name+".gts"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int nbPoints = s.nextInt();
		int nbSegments = s.nextInt();
		int nbFaces = s.nextInt();
		int parcoursDeLigne = 1;
		for (int i = parcoursDeLigne; i < parcoursDeLigne+nbPoints; i++) {
				list_points.add(new Point(Double.parseDouble(s.next()),Double.parseDouble(s.next()),Double.parseDouble(s.next()),i));		
		}
		parcoursDeLigne+=nbPoints;
		for (int i = parcoursDeLigne; i < parcoursDeLigne+nbSegments; i++) {
			list_segments.add(new Segment(list_points.get(s.nextInt()-1),list_points.get(s.nextInt()-1), i));
		}
		parcoursDeLigne+=nbSegments;
		for (int i = parcoursDeLigne; i < parcoursDeLigne+nbFaces; i++) {
			
			list_faces.add(new Face(list_segments.get(s.nextInt()-1), list_segments.get(s.nextInt()-1), list_segments.get(s.nextInt()-1), i));
		}
		max = list_points.get(0).getX();
		
		for (int i = 0; i < list_points.size(); i++) {
			if (list_points.get(i).getX() > max) {
				max = list_points.get(i).getX();
			} else if (list_points.get(i).getX() < -max) {
				max = -list_points.get(i).getX();
			}
			if (list_points.get(i).getY() > max) {
				max = list_points.get(i).getY();
			} else if (list_points.get(i).getY() < -max) {
				max = -list_points.get(i).getY();
			}
		}
		zoom = (int) (250/max);
		
		return true;

	}
	
	
	
	public PanelAffichage(AffichageDuModele a, String name) {
		this.a = a;
			RecupDonneeFichier(name);
			this.setVisible(true);
			this.addMouseListener(new MouseListenerDeuxiemeMaison(this));
			this.addMouseMotionListener(new MouseListenerMaison(this));
			this.addMouseWheelListener(new MouseWheelListenerMaison(this));
			//Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			if (a.getHauteur()==0) {
				decalageX = 672/2;
				decalageY = 637/2;
			}else{
				decalageY = (int) (a.getHauteur()/2);
				decalageX = (int) (a.getLargeur()/2);
			}
	}
	
	
	Graphics buffer;
	Image image;

	public void paint(Graphics g) {
		
		//if (buffer == null) {
			image = createImage((int)a.getLargeur(),(int)a.getHauteur());
			buffer = image.getGraphics();
		//}
		buffer.setColor(CouleurFond.couleur);
		buffer.fillRect(0, 0, 10000, 10000);
		for (int i = 0; i < list_faces.size(); i++) {
			rotation(list_faces.get(i));
		}

		Collections.sort(list_faces);

		rotationX = 0;
		rotationY = 0;
		rotationZ = 0;
		int couleur1 = CouleurObjet.couleur.getRed();
		int couleur2 = CouleurObjet.couleur.getGreen();
		int couleur3 = CouleurObjet.couleur.getBlue();
		
		for (int i = 0; i < list_faces.size(); i++) {
			
			// Coeef blanc : coeffLuminosite = 255 - (i * 255) / list_faces.size();
			// coeef couleur -> blanc  255 - (i * (255-couleur1)) / list_faces.size()

			buffer.setColor(new Color((i * couleur1) / list_faces.size(), (i * couleur2) / list_faces.size(),(i * couleur3) / list_faces.size()));
			if (ligneOrNot) {
				buffer.drawPolygon(generatePolygon(list_faces.get(i), zoom,
						decalageX, decalageY));
			}else{
				buffer.fillPolygon(generatePolygon(list_faces.get(i), zoom,
						decalageX, decalageY));
			}
			

		}

		g.drawImage(image, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(getGraphics());
	}

	private void rotation(Face f){
		double[] x = new double[3];
		double[] y = new double[3];
		double[] z = new double[3];
		for (int i = 0; i < x.length; i++) {
			x[i] = f.getPoint(i + 1).getX();
			y[i] = f.getPoint(i + 1).getY();
			z[i] = f.getPoint(i + 1).getZ();
		}
		Matrice m = new Matrice(x, y, z);
		m = m.rotateX(rotationX).rotateY(rotationY).rotateZ(rotationZ);
		Point a = new Point(m.getTabX()[0], m.getTabY()[0], m.getTabZ()[0], f.getPoint(1).getNumero());
		Point b = new Point(m.getTabX()[1], m.getTabY()[1], m.getTabZ()[1], f.getPoint(2).getNumero());
		Point c = new Point(m.getTabX()[2], m.getTabY()[2], m.getTabZ()[2], f.getPoint(3).getNumero());
		f.setPoint(1, a);
		f.setPoint(2, b);
		f.setPoint(3, c);
		
	}
	
	private Polygon generatePolygon(Face f,Double cXY, int dX, int dY) {
		double[] x = new double[3];
		double[] y = new double[3];
		double[] z = new double[3];
		for (int i = 0; i < x.length; i++) {
			x[i] = f.getPoint(i + 1).getX();
			y[i] = f.getPoint(i + 1).getY();
			z[i] = f.getPoint(i + 1).getZ();
		}
		Matrice m = new Matrice(x, y, z);
		
		
		
		
		for (int i = 0; i < 3; i++) {
			x[i]=m.getTabX()[i]* cXY + dX;
			y[i]=m.getTabY()[i] * cXY + dY;
			z[i]=m.getTabZ()[i]* cXY;
		}
		m = new Matrice(x, y, z);
		return m.PolygonGeneratorFromMatrice();
	}

	public class MouseWheelListenerMaison implements MouseWheelListener {
		private PanelAffichage p;

		public MouseWheelListenerMaison(PanelAffichage p) {
			this.p = p;
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			zoom = zoom -  Math.pow(10, (-Math.log10(max)+1))*e.getWheelRotation();
			if (zoom<=0.5) {
				zoom = zoom + Math.pow(10, (-Math.log10(max)+1))*e.getWheelRotation();
			}
			p.repaint();
		}

	}
	public class MouseListenerMaison implements MouseMotionListener {
		private PanelAffichage p;
		
		public MouseListenerMaison(PanelAffichage p) {
			this.p = p;
			

		}
		@Override
		public void mouseDragged(MouseEvent e) {
			ligneOrNot = true;
			if (SwingUtilities.isLeftMouseButton (e)&&SwingUtilities.isRightMouseButton (e)) {
				decalageX += e.getX() - mouseX;
				decalageY += e.getY() - mouseY;
				rotationY -=  e.getX() - mouseX ;
				rotationX +=  e.getY() - mouseY ;
			}else if (SwingUtilities.isLeftMouseButton (e)) {
				decalageX += e.getX() - mouseX;
				decalageY += e.getY() - mouseY;
				
			}else if (SwingUtilities.isRightMouseButton (e)) {
					rotationY -=  e.getX() - mouseX ;
					rotationX +=  e.getY() - mouseY ;
					
			}
			mouseX = e.getX();
			mouseY = e.getY();
			p.repaint();
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
		}
		/*public void setTaille(Graphics buffer ){
			Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			int Y = (int)tailleEcran.getHeight();
			int X = (int)tailleEcran.getWidth();
			buffer.clipRect(0, 0, X, Y);
		}*/
		
	}
	public class MouseListenerDeuxiemeMaison implements MouseListener {
private PanelAffichage p;
		
		public MouseListenerDeuxiemeMaison(PanelAffichage p) {
			this.p = p;
			

		}
		
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			ligneOrNot = false;
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			ligneOrNot = true;
			if (SwingUtilities.isLeftMouseButton (e)&&SwingUtilities.isRightMouseButton (e)) {
				decalageX += e.getX() - mouseX;
				decalageY += e.getY() - mouseY;
				rotationY -=  e.getX() - mouseX ;
				rotationX +=  e.getY() - mouseY ;
			}else if (SwingUtilities.isLeftMouseButton (e)) {
				decalageX += e.getX() - mouseX;
				decalageY += e.getY() - mouseY;
				
			}else if (SwingUtilities.isRightMouseButton (e)) {
					rotationY -=  e.getX() - mouseX ;
					rotationX +=  e.getY() - mouseY ;
					
			}
			mouseX = e.getX();
			mouseY = e.getY();
			p.repaint();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			ligneOrNot = false;
			if (SwingUtilities.isLeftMouseButton (e)&&SwingUtilities.isRightMouseButton (e)) {
				decalageX += e.getX() - mouseX;
				decalageY += e.getY() - mouseY;
				rotationY -=  e.getX() - mouseX ;
				rotationX +=  e.getY() - mouseY ;
			}else if (SwingUtilities.isLeftMouseButton (e)) {
				decalageX += e.getX() - mouseX;
				decalageY += e.getY() - mouseY;
				
			}else if (SwingUtilities.isRightMouseButton (e)) {
					rotationY -=  e.getX() - mouseX ;
					rotationX +=  e.getY() - mouseY ;
					
			}
			mouseX = e.getX();
			mouseY = e.getY();
			p.repaint();
			
		}
		
		
	}
}
