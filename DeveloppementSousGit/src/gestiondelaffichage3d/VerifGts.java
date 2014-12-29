package gestiondelaffichage3d;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VerifGts {

	
	private List<Point> list_points = new ArrayList<Point>();
	private List<Segment> list_segments = new ArrayList<Segment>();
	private List<Face> list_faces = new ArrayList<Face>();
	
	
	public VerifGts(String name){
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
}
	
}