package menuetoptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Enregistrer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Enregistrer(String name) {

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			String ligne;
			int cpt = 0;
			FileReader flux;
			BufferedReader entree;
			PrintWriter sortie;
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
				entree.close();
				sortie.close();
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}

	}

}

