package gestiondelaffichage3d;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import org.ibex.nestedvm.UnixRuntime.ForkedProcess;

// classe qui permet de verifier si un point gts est corecte.
// pour savoir si il est correct, il faut utiliser la methode public GtsEstCorrect() qui return true si il est correct
public class VerifGts {

	private List<Point> list_points = new ArrayList<Point>();
	private List<Segment> list_segments = new ArrayList<Segment>();
	private List<Face> list_faces = new ArrayList<Face>();
	private int nbPoints;
	private int nbSegments;
	private int nbFaces;

	public VerifGts(String name) {
		Scanner s = null;
		try {
			s = new Scanner(new File("./ressources/modeles/" + name + ".gts"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nbPoints = s.nextInt();
		nbSegments = s.nextInt();
		nbFaces = s.nextInt();
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

	// verifie que pour chaques segments a bien des points qui existe
	private boolean segment_pointOK() {

		Point segmentP1;
		Point segmentP2;
		int nb = 0;
		int nb2 = 0;

		for (int i = 0; i < nbSegments - 1; i++) {
			segmentP1 = list_segments.get(i).getP1();
			segmentP2 = list_segments.get(i).getP2();

			for (int j = 0; j < nbPoints - 1; j++) {
				if (segmentP1.equals(list_points.get(j))) {
					nb++;
				}
				if (segmentP2.equals(list_points.get(j))) {
					nb2++;
				}
			}
			if (nb == 0 || nb2 == 0) {
				return false;
			}
		}
		return true;
	}

	// verifie que pour chaques faces que les segments existe
	private boolean face_segmentOk() {

		Segment faceS1;
		Segment faceS2;
		Segment faceS3;
		int nb = 0;
		int nb2 = 0;
		int nb3 = 0;

		for (int i = 0; i < nbFaces - 1; i++) {
			faceS1 = list_faces.get(i).getS1();
			faceS2 = list_faces.get(i).getS2();
			faceS3 = list_faces.get(i).getS3();

			for (int j = 0; j < nbSegments - 1; j++) {
				if (faceS1.equals(list_segments.get(j))) {
					nb++;
				}
				if (faceS2.equals(list_segments.get(j))) {
					nb2++;
				}
				if (faceS3.equals(list_segments.get(j))) {
					nb3++;
				}
			}
		}
		if (nb == 0 || nb2 == 0 || nb3 == 0) {
			return false;
		}
		return true;
	}

	// verifie que pour chaques faces les trois segment ne sont pas identique
	private boolean facePasMemeSegmentok() {

		Segment faceS1;
		Segment faceS2;
		Segment faceS3;
		int nb = 0;
		int nb2 = 0;
		int nb3 = 0;

		for (int i = 0; i < nbFaces - 1; i++) {
			faceS1 = list_faces.get(i).getS1();
			faceS2 = list_faces.get(i).getS2();
			faceS3 = list_faces.get(i).getS3();

			if (faceS1.equals(faceS3) || faceS1.equals(faceS3)
					|| faceS2.equals(faceS3)) {
				return false;
			}
		}
		return true;
	}

	// verifie que pour chaques segments les deux points ne sont pas identique
	private boolean segmentPasMemePoint() {

		Point segmentP1;
		Point segmentP2;
		int nb = 0;

		for (int i = 0; i < nbSegments - 1; i++) {
			segmentP1 = list_segments.get(i).getP1();
			segmentP2 = list_segments.get(i).getP2();

			if (segmentP1.equals(segmentP2)) {
				return false;
			}
		}

		return true;
	}

	// return true si le point gts est correct sinon false.
	public boolean GtsEstCorrect() {
		if (segment_pointOK() && segmentPasMemePoint() && face_segmentOk()
				&& facePasMemeSegmentok()) {
			return true;
		} else {
			return false;
		}
	}
}