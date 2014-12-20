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
import java.util.Collections;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import menuetoptions.CouleurFond;
import menuetoptions.CouleurObjet;
import menuetoptions.MusicPlayer;

/**
 * Classe qui cree un JPanel dans lequel est dessine l'objet 3D.
 */
@SuppressWarnings("serial")
public class PanelAffichage extends JPanel {

	private AffichageDuModele fenetrePrincipale;
	private int decalageX = 0;
	private int decalageY = 0;
	private int rotationX = 0;
	private int rotationY = 0;
	private int rotationZ = 0;
	private int mouseX = 0;
	private int mouseY = 0;
	private double zoom = 1;
	private Double max;
	private boolean ligneOrNot = false;
	private Graphics buffer;
	private Image image;
	private RecupDonneesFichier fichier;
	private MusicPlayer player;

	/**
	 * Constructeur de la classe PanelAffichage.
	 * 
	 * @param fenetrePrincipale
	 *            fenetre principale du programme
	 * @param name
	 *            nom du fichier .gts sans l'extension
	 */
	public PanelAffichage(AffichageDuModele fenetrePrincipale, String name) {
		this.fenetrePrincipale = fenetrePrincipale;
		RecupDonneesFichier(name);
		this.setVisible(true);
		this.addMouseListener(new MouseListenerMaison(this));
		this.addMouseMotionListener(new MouseMotionListenerMaison(this));
		this.addMouseWheelListener(new MouseWheelListenerMaison(this));
		if (fenetrePrincipale.getHauteur() == 0) {
			decalageX = 672 / 2;
			decalageY = 637 / 2;
		} else {
			decalageY = (int) (fenetrePrincipale.getHauteur() / 2);
			decalageX = (int) (fenetrePrincipale.getLargeur() / 2);
		}
	}

	/**
	 * Lance la recuperation des donnees du fichier et regle le zoom par defaut
	 * 
	 * @param name
	 *            nom du fichier .gts sans l'extension
	 */
	private void RecupDonneesFichier(String name) {
		fichier = new RecupDonneesFichier(name, this);
		max = fichier.getList_points().get(0).getX();

		for (int i = 0; i < fichier.getList_points().size(); i++) {
			if (fichier.getList_points().get(i).getX() > max) {
				max = fichier.getList_points().get(i).getX();
			} else if (fichier.getList_points().get(i).getX() < -max) {
				max = -fichier.getList_points().get(i).getX();
			}
			if (fichier.getList_points().get(i).getY() > max) {
				max = fichier.getList_points().get(i).getY();
			} else if (fichier.getList_points().get(i).getY() < -max) {
				max = -fichier.getList_points().get(i).getY();
			}
		}
		zoom = (int) (250 / max);
		player = new MusicPlayer("./ressources/musique/nyancat.wav");
	}

	@Override
	public void paint(Graphics g) {
		image = createImage((int) fenetrePrincipale.getLargeur(),
				(int) fenetrePrincipale.getHauteur());
		buffer = image.getGraphics();
		buffer.setColor(CouleurFond.couleur);
		buffer.fillRect(0, 0, 10000, 10000);
		for (int i = 0; i < fichier.getList_faces().size(); i++) {
			rotation(fichier.getList_faces().get(i));
		}
		Collections.sort(fichier.getList_faces());
		rotationX = 0;
		rotationY = 0;
		rotationZ = 0;
		int couleur1 = CouleurObjet.couleur.getRed();
		int couleur2 = CouleurObjet.couleur.getGreen();
		int couleur3 = CouleurObjet.couleur.getBlue();
		for (int i = 0; i < fichier.getList_faces().size(); i++) {
			buffer.setColor(new Color((i * couleur1)
					/ fichier.getList_faces().size(), (i * couleur2)
					/ fichier.getList_faces().size(), (i * couleur3)
					/ fichier.getList_faces().size()));
			if (ligneOrNot) {
				buffer.drawPolygon(generatePolygon(
						fichier.getList_faces().get(i), zoom, decalageX,
						decalageY));
			} else {
				buffer.fillPolygon(generatePolygon(
						fichier.getList_faces().get(i), zoom, decalageX,
						decalageY));
			}
		}
		g.drawImage(image, 0, 0, this);
	}

	/**
	 * Permet d'executer une rotation d'une face de l'objet 3D.
	 * 
	 * @param f
	 *            face a tourner.
	 */
	private void rotation(Face f) {
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
		Point a = new Point(m.getTabX()[0], m.getTabY()[0], m.getTabZ()[0], f
				.getPoint(1).getNumero());
		Point b = new Point(m.getTabX()[1], m.getTabY()[1], m.getTabZ()[1], f
				.getPoint(2).getNumero());
		Point c = new Point(m.getTabX()[2], m.getTabY()[2], m.getTabZ()[2], f
				.getPoint(3).getNumero());
		f.setPoint(1, a);
		f.setPoint(2, b);
		f.setPoint(3, c);

	}

	/**
	 * Permet de generer un polygone.
	 * 
	 * @param f
	 *            face a generer
	 * @param zoom
	 *            zoom de la face
	 * @param dX
	 *            decalage horizontal de la face
	 * @param dY
	 *            decalage vertical de la face
	 * @return le polygone modifie
	 */
	private Polygon generatePolygon(Face f, Double zoom, int dX, int dY) {
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
			x[i] = m.getTabX()[i] * zoom + dX;
			y[i] = m.getTabY()[i] * zoom + dY;
			z[i] = m.getTabZ()[i] * zoom;
		}
		m = new Matrice(x, y, z);
		return m.PolygonGeneratorFromMatrice();
	}

	/**
	 * Listener de la molette de la souris.
	 * 
	 * @see MouseWheelListener
	 */
	public class MouseWheelListenerMaison implements MouseWheelListener {
		private PanelAffichage p;

		/**
		 * Constructeur de la classe MouseWheelListenerMaison.
		 * 
		 * @param p
		 *            JPanel auquel s'attache le Listener.
		 */
		public MouseWheelListenerMaison(PanelAffichage p) {
			this.p = p;
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			zoom = zoom - Math.pow(10, (-Math.log10(max) + 1))
					* e.getWheelRotation();
			if (zoom <= 0.5) {
				zoom = zoom + Math.pow(10, (-Math.log10(max) + 1))
						* e.getWheelRotation();
			}
			p.repaint();
		}

	}

	/**
	 * Listener de la souris qui permet le deplacement de l'objet 3D.
	 * 
	 * @see MouseMotionListener
	 */
	public class MouseMotionListenerMaison implements MouseMotionListener {
		private PanelAffichage p;

		/**
		 * Constructeur de la classe MouseMotionListenerMaison.
		 * 
		 * @param p
		 *            JPanel auquel s'attache le Listener.
		 */
		public MouseMotionListenerMaison(PanelAffichage p) {
			this.p = p;

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			ligneOrNot = true;
			if (SwingUtilities.isLeftMouseButton(e)
					&& SwingUtilities.isRightMouseButton(e)) {
				decalageX += e.getX() - mouseX;
				decalageY += e.getY() - mouseY;
				rotationY -= e.getX() - mouseX;
				rotationX += e.getY() - mouseY;
			} else if (SwingUtilities.isLeftMouseButton(e)) {
				decalageX += e.getX() - mouseX;
				decalageY += e.getY() - mouseY;

			} else if (SwingUtilities.isRightMouseButton(e)) {
				rotationY -= e.getX() - mouseX;
				rotationX += e.getY() - mouseY;

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

	/**
	 * Listener de la souris qui permet la lecture de la musique et l'affichage
	 * en ligne.
	 * 
	 * @see MouseListener
	 */
	public class MouseListenerMaison implements MouseListener {
		private PanelAffichage p;

		public MouseListenerMaison(PanelAffichage p) {
			this.p = p;

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			ligneOrNot = false;
			p.repaint();
			player.pause();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			ligneOrNot = true;
			player.lecture();
			p.repaint();

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			ligneOrNot = false;
			player.pause();
			p.repaint();

		}

	}
}
