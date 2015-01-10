package menuetoptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Classe permettant a l'utilisateur s'il le souhaite d'enregistrer 
 * le gts de l'ojet qu'il visualise
 * 
 *
 */
public class Enregistrer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param name
	 * Nom de l'objet a enregistrer
	 */
	public Enregistrer(String name) {
		//Choix des  fihiers
		JFileChooser fileChooser = new JFileChooser();
		//un qiquement des repetoires comme destinations
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(this);
		
		//si l'utilisateur confirme l'enregsitrement
		if (userSelection == JFileChooser.APPROVE_OPTION) {

			//Utilisation de flux pour les entrees et sorties
			String ligne;
			int cpt = 0;
			FileReader flux;
			BufferedReader entree = null;
			PrintWriter sortie = null;
			ArrayList<String> liste = new ArrayList<String>();
			try {
				flux = new FileReader("./ressources/modeles/" + name + ".gts");
				entree = new BufferedReader(flux);
				sortie = new PrintWriter(fileChooser.getSelectedFile()
						.getAbsolutePath() + "/" + name + ".gts");
				while ((ligne = entree.readLine()) != null) {
					liste.add(ligne);
					sortie.println(liste.get(cpt++));
				}
				//Gestion d'exceptions et fermeteure de connection et de flux
			} catch (Exception e) {
				System.err.println(e.toString());
			} finally {
				try {
					entree.close();
					sortie.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		}

	}

}
