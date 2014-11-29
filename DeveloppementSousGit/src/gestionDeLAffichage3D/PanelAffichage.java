package gestionDeLAffichage3D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

@SuppressWarnings("serial")
public class PanelAffichage extends JPanel {
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
	int zoom = 1;
	
	private boolean RecupDonneeFichier(boolean firstLaunch){
		if (firstLaunch) {
			List<String> fichier = new ArrayList<String>();
			try {
				String ligne;
				FileReader flux;
				BufferedReader entree;
				flux = new FileReader("ressources/modeles/icosa.gts");
				entree = new BufferedReader(flux);
				while ((ligne = entree.readLine()) != null) {
					fichier.add(ligne);
				}
				entree.close();
			} catch (Exception e) {
				System.err.println(e.toString());
				return false;
			}
			nomDeLObjet = "icosa.gts";
			String first = fichier.get(0);
			int nbPoints = Integer.parseInt(first.substring(0,first.indexOf(' ')));
			int nbSegments = Integer.parseInt(first.substring(first.indexOf(' ')+1,first.indexOf(' ')+1+first.substring(first.indexOf(' ')+1).indexOf(' ')));
			int nbFaces = Integer.parseInt(first.substring(first.indexOf(' ')+1+first.substring(first.indexOf(' ')+1).indexOf(' ')+1));
			int parcoursDeLigne = 1;
			for (int i = parcoursDeLigne; i < parcoursDeLigne+nbPoints; i++) {
				list_points.add(new Point(Double.parseDouble(fichier.get(i).substring(0,fichier.get(i).indexOf(' '))), Double.parseDouble(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1,fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' '))), Double.parseDouble(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' ')+1)),(i-parcoursDeLigne+1)));
			}
			parcoursDeLigne+=nbPoints;
			for (int i = parcoursDeLigne; i < parcoursDeLigne+nbSegments; i++) {
				list_segments.add(new Segment(list_points.get(Integer.parseInt(fichier.get(i).substring(0,fichier.get(i).indexOf(' ')))-1), list_points.get(Integer.parseInt(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1))-1),(i-parcoursDeLigne+1)));
			}
			parcoursDeLigne+=nbSegments;
			for (int i = parcoursDeLigne; i < parcoursDeLigne+nbFaces; i++) {
				list_faces.add(new Face(list_segments.get(Integer.parseInt(fichier.get(i).substring(0,fichier.get(i).indexOf(' ')))-1), list_segments.get(Integer.parseInt(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1,fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' ')))-1), list_segments.get(Integer.parseInt(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' ')+1))-1),(i-parcoursDeLigne+1)));
			}
			Double max = list_points.get(0).getX();
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
			firstLaunch = false;
			return true;
			
			
		}else{
		List<String> fichier = new ArrayList<String>();
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
		String first = fichier.get(0);
		int nbPoints = Integer.parseInt(first.substring(0,first.indexOf(' ')));
		int nbSegments = Integer.parseInt(first.substring(first.indexOf(' ')+1,first.indexOf(' ')+1+first.substring(first.indexOf(' ')+1).indexOf(' ')));
		int nbFaces = Integer.parseInt(first.substring(first.indexOf(' ')+1+first.substring(first.indexOf(' ')+1).indexOf(' ')+1));
		int parcoursDeLigne = 1;
		for (int i = parcoursDeLigne; i < parcoursDeLigne+nbPoints; i++) {
			list_points.add(new Point(Double.parseDouble(fichier.get(i).substring(0,fichier.get(i).indexOf(' '))), Double.parseDouble(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1,fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' '))), Double.parseDouble(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' ')+1)),(i-parcoursDeLigne+1)));
		}
		parcoursDeLigne+=nbPoints;
		for (int i = parcoursDeLigne; i < parcoursDeLigne+nbSegments; i++) {
			list_segments.add(new Segment(list_points.get(Integer.parseInt(fichier.get(i).substring(0,fichier.get(i).indexOf(' ')))-1), list_points.get(Integer.parseInt(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1))-1),(i-parcoursDeLigne+1)));
		}
		parcoursDeLigne+=nbSegments;
		for (int i = parcoursDeLigne; i < parcoursDeLigne+nbFaces; i++) {
			list_faces.add(new Face(list_segments.get(Integer.parseInt(fichier.get(i).substring(0,fichier.get(i).indexOf(' ')))-1), list_segments.get(Integer.parseInt(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1,fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' ')))-1), list_segments.get(Integer.parseInt(fichier.get(i).substring(fichier.get(i).indexOf(' ')+1+fichier.get(i).substring(fichier.get(i).indexOf(' ')+1).indexOf(' ')+1))-1),(i-parcoursDeLigne+1)));
		}
		Double max = list_points.get(0).getX();
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
	}

	
	public PanelAffichage(boolean b) {
			RecupDonneeFichier(b);
			this.setBackground(Color.WHITE);
			this.setVisible(true);
			this.addMouseMotionListener(new MouseListenerMaison(this));
			this.addMouseWheelListener(new MouseWheelListenerMaison(this));
			Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			decalageY = 350;
			decalageX = 350;
	}
	
	
	Graphics buffer;
	Image image; 
	 public void paint( Graphics g ){
	     if(buffer==null){
	        image = createImage(getWidth(),getHeight());
	        buffer = image.getGraphics();
	      }
	     buffer.setColor(Color.BLACK);
	     buffer.fillRect(0, 0,3000, 3000);
	     for (int i = 0; i <  list_faces.size(); i++) {
	    	 rotation(list_faces.get(i));
		}
	
			
			
			Collections.sort(list_faces);
			
			
	     rotationX=0;
			rotationY =0;
			rotationZ=0;
			for (int i = list_faces.size()-1; i >-1; i--) {
				buffer.setColor(new Color(255-(i*255)/list_faces.size(),255-(i*255)/list_faces.size(),255-(i*255)/list_faces.size()));
				//g.setColor(tableau[i].getColor());
				//g.setColor(new Color(r.nextInt(255)+1, r.nextInt(255)+1, r.nextInt(255)+1));
				buffer.fillPolygon(generatePolygon(list_faces.get(i),zoom,decalageX,decalageY));
				
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
	
	private Polygon generatePolygon(Face f,int cXY, int dX, int dY) {
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
			
			zoom -= e.getWheelRotation();
			if (zoom<=0) {
				zoom -= e.getWheelRotation();
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
			if (SwingUtilities.isLeftMouseButton (e)) {
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
		
	}
}
