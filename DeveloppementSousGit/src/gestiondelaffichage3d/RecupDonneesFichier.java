package gestiondelaffichage3d;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * Cette classe permet de recuperer les donnees d'un fichier .gts
 *
 */
public class RecupDonneesFichier {

	private List<Point> list_points = new ArrayList<Point>();
	private List<Segment> list_segments = new ArrayList<Segment>();
	private List<Face> list_faces = new ArrayList<Face>();
	private String name;

	/**
	 * Constructeur de la classe RecupDonneesFichier. Permet de stocker les
	 * points, les segments et les faces dans des listes.
	 * 
	 * @param name
	 *            nom du fichier qui contient les donnees a recuperer.
	 *            Attention, ne pas mettre l'extension .gts dans le nom.
	 * @param component
	 *            Component actuel
	 */
	public RecupDonneesFichier(String name, Component component) {
		this.name = name;
		/*if (this.name == null) {

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
			fc.showOpenDialog(component);
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
			}
			this.name = fc.getSelectedFile().getName();
		}*/
		Scanner s = null;
		try {
			s = new Scanner(new File("./ressources/modeles/" + this.name
					+ ".gts"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int nbPoints = s.nextInt();
		int nbSegments = s.nextInt();
		int nbFaces = s.nextInt();
		int parcoursDeLigne = 1;
		for (int i = parcoursDeLigne; i < parcoursDeLigne + nbPoints; i++) {
			list_points.add(new Point(Double.parseDouble(s.next()), Double
					.parseDouble(s.next()), Double.parseDouble(s.next()), i));
		}
		parcoursDeLigne += nbPoints;
		for (int i = parcoursDeLigne; i < parcoursDeLigne + nbSegments; i++) {
			list_segments.add(new Segment(list_points.get(s.nextInt() - 1),
					list_points.get(s.nextInt() - 1), i));
		}
		parcoursDeLigne += nbSegments;
		for (int i = parcoursDeLigne; i < parcoursDeLigne + nbFaces; i++) {

			list_faces.add(new Face(list_segments.get(s.nextInt() - 1),
					list_segments.get(s.nextInt() - 1), list_segments.get(s
							.nextInt() - 1), i));
		}

	}

	/**
	 * Permet de recuperer la liste des points
	 * 
	 * @return la liste des points
	 */
	public List<Point> getList_points() {
		return list_points;
	}

	/**
	 * Permet de recuperer la liste des segments
	 * 
	 * @return la liste des segments
	 */
	public List<Segment> getList_segments() {
		return list_segments;
	}

	/**
	 * Permet de recuperer la liste des faces
	 * 
	 * @return la liste des faces
	 */
	public List<Face> getList_faces() {
		return list_faces;
	}

	/**
	 * Permet de recuperer le nom du fichier
	 * 
	 * @return le nom du fichier
	 */
	public String getName() {
		return name;
	}

}
