package modeleVueControleur;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;


/**
 * Classe permettant a l'utilsateur d'ajouter un objet et de completer 
 * les differentes informations associees: est une vue du ModelInsertion.
 *
 */
public class Ajout extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	AffichageDuModele aff;
	final JTextField fichier;
	final JLabel label1;
	final JLabel label2;
	final JTextField tfield;
	final JTextField tfield1;
	final JFileChooser fc;
	private Controleur controler;
	protected ModelInsertion model;
	protected boolean sourceOfchange;


	/**
	 * Constructeur de la Classe
	 * @param a
	 * Une instance de la fentre principale pour visualiser le fichier apres Ajout
	 */

	public Ajout(AffichageDuModele a,ModelInsertion model) {
		this.model=model;
		
		model.addObserver(this);

		// pour communiquer avec la fenetre principale
		this.aff = a;
this.controler = new Controleur(a,model);
		//dimension utilisee le plus souvent
		Dimension d = new Dimension(100, 27);

		// pour charger le fichier
		JButton charger = new JButton("Charger fichier");
		final JPanel pannelCharger = new JPanel();
		fc = new JFileChooser(".");
		charger.setPreferredSize(new Dimension(150, 27));
		fichier = new JTextField("Veuiller choisir un fichier");
		fichier.setPreferredSize(new Dimension(350, 27));
		pannelCharger.add(fichier);
		pannelCharger.add(charger);

		charger.addActionListener(new ActionListenerKarenNumero2(this));

		Container c = this.getContentPane();
		c.setLayout(new GridLayout(8, 1, 9, 9));
		c.add(pannelCharger);

		c.add(new JLabel(
				"  VEUILLEZ ENTRER LES INFORMATIONS RELATIVES A VOTRE OBJET:     "));

		//Differents champs a completer pour ajouter l'objet
		final JPanel contentPane = new JPanel();
		tfield = new JTextField(15);
		label1 = new JLabel("Nom de l'objet: ");

		contentPane.add(label1);
		contentPane.add(tfield);

		c.add(contentPane);
		final JPanel contentPane1 = new JPanel();
		tfield1 = new JTextField(15);
		label2 = new JLabel("Auteur:    ");

		contentPane1.add(label2);
		contentPane1.add(tfield1);
		c.add(contentPane1);

		contentPane.setPreferredSize(d);
		contentPane1.setPreferredSize(d);
		contentPane1.setPreferredSize(d);

		JPanel pa1 = new JPanel();
		final JButton valider = new JButton("Valider ");
		valider.addActionListener(new ActionListenerKaren(this));

		valider.setPreferredSize(d);
		pa1.add(valider);
		c.add(pa1);

		JPanel pa2 = new JPanel();
		JButton annuler = new JButton("Annuler ");
		annuler.setPreferredSize(d);
		pa2.add(annuler);
		c.add(pa2);

		// pour annuler l'ajout

		annuler.addActionListener(new ActionListenerKarenNumero3(this));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(540, 400);
		this.setResizable(false);
		this.setVisible(true);

	}

	/**
	 * Classe pour ecouter le bouton annuler
	 *
	 */
	private class ActionListenerKarenNumero3 implements ActionListener {
		private Ajout a;

		/**
		 * Constructeur de la classe
		 * @param a
		 * Une instance de Ajout
		 */
		private ActionListenerKarenNumero3(Ajout ajout) {
			this.a = ajout;
		}

		public void actionPerformed(ActionEvent e) {
			a.dispose();
		}

	}

	/**
	 * Classe pour ecouter le pannel qui s'occupe du chargement du gts
	 */
	private class ActionListenerKarenNumero2 implements ActionListener {

		private Ajout a;

		/**
		 * Constructeur de la classe
		 * @param a
		 * Une instance de Ajout
		 */
		private  ActionListenerKarenNumero2(Ajout ajout) {
			this.a = ajout;
		}

		// choix du fichier

		public void actionPerformed(ActionEvent e) {
			fc.addChoosableFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().endsWith(".gts");
				}

				/**
				 * @return
				 * Description du fichier a charger
				 */
				@Override
				public String getDescription() {
					return "Fichier GNU Triangulated Surface Library (.gts)";
				}
			});
			fc.showOpenDialog(a);
			try {
				fichier.setText(fc.getSelectedFile().getAbsolutePath());
			} catch (NullPointerException ex) {
				if (fichier.getText().isEmpty()) {
					fichier.setText("Veuiller chosir un fichier");

				}
			}

		}
	}

	/**
	 * Classe qui permet d'ecouter le bouton annuler
	 *
	 */
	public class ActionListenerKaren implements ActionListener {
		Ajout a;

		/**
		 * Constructeur de la classe
		 * @param a
		 * Une instance de Ajout
		 */
		public ActionListenerKaren(Ajout a) {
			this.a = a;
		}

		public void actionPerformed(ActionEvent e) {

//Pour les utilisateurs malins
			if (tfield.getText().isEmpty() || tfield1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(a,
						"Tous les champs sont obligatoire", "Attention",
						JOptionPane.WARNING_MESSAGE);

			}


			else {
				if (fichier.getText().equals("Veuiller choisir un fichier")) {
					JOptionPane.showMessageDialog(a,
							"Veuillez choisir un fichier", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else {
					// on appelle le controleur
					System.out.println(tfield1.getText());
					controler.envoieGts(a,fc.getSelectedFile(),tfield.getText() , tfield1.getText(), fichier.getText());
					//si le conroleur ne dectetecte pas de doublons dans la base 
					if(!controler.alerteDoublon)
					a.dispose();
				}
			}
		}


	}
	//methode heritee de Obsrever
	@Override
	public void update(Observable o, Object arg) {
		
	}


}
