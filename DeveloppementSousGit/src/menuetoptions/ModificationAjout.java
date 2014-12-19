package menuetoptions;

import gestiondelaffichage3d.AffichageDuModele;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModificationAjout {
	String nomObjet;
	final JFrame frame = new JFrame("Veuillez ajouter les informations a modifier:   ");
	public ModificationAjout(final String nomObjet){
		this.nomObjet=nomObjet;
		Dimension d=new Dimension(100,27);
		Container c=frame.getContentPane();
		c.setLayout(new GridLayout(8,1,7,7));


		c.add(new JLabel("  VEUILLEZ ENTRER LES INFORMATIONS RELATIVES A VOTRE OBJET:     "));

		final JPanel contentPane = new JPanel();

		final JTextField tfield = new JTextField(15);
		tfield.setEnabled(false);

		final JCheckBox cbox = new JCheckBox("Nom de l'objet: ", false);

		ItemListener itemListener = new ItemListener()
		{
			public void itemStateChanged(ItemEvent ie)
			{
				tfield.setEnabled(ie.getStateChange() == ItemEvent.SELECTED);
			}
		};
		cbox.addItemListener(itemListener);

		contentPane.add(cbox);
		contentPane.add(tfield);

		c.add(contentPane);

		final JPanel contentPane1 = new JPanel();

		final JTextField tfield1 = new JTextField(15);

		tfield1.setEnabled(false);

		final JCheckBox cbox1 = new JCheckBox("Auteur:    ", false);

		ItemListener itemListener1 = new ItemListener()
		{
			public void itemStateChanged(ItemEvent ie)
			{
				tfield1.setEnabled(ie.getStateChange() == ItemEvent.SELECTED);

			}
		};
		cbox1.addItemListener(itemListener1);

		contentPane1.add(cbox1);
		contentPane1.add(tfield1);
		c.add(contentPane1);



		final JPanel contentPane2  = new JPanel();

		contentPane.setPreferredSize(d);
		contentPane1.setPreferredSize(d);
		contentPane1.setPreferredSize(d);
		c.add(contentPane2);
		JPanel pa1=new JPanel();
		final JButton valider=new JButton("Valider ");
		valider.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
				//tester ce qui a ete coche
				if(!cbox1.isSelected()||!cbox.isSelected()){
					JOptionPane.showMessageDialog(frame,
							"Tous les champs sont obligatoire",
							"Attention",
							JOptionPane.WARNING_MESSAGE);

				}
				else
					if((cbox1.isSelected()&&tfield1.getText().isEmpty())||(cbox.isSelected()&&tfield.getText().isEmpty())){
						JOptionPane.showMessageDialog(frame,
								"Un champ coche ne peut etre vide.",
								"Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					else{
						System.out.println("coucou");

						String sql = "";
						String  name;
						String  auteur;
						name = tfield.getText();
						auteur = tfield1.getText();

						if(cbox.isSelected()&&!tfield.getText().equals(null))
							sql ="UPDATE OBJETS3D  SET NAME ="+ "'"+ name +"'" + "WHERE NAME =" + "'"+ ModificationAjout.this.nomObjet +"';";


						if(cbox1.isSelected()&&!tfield1.getText().equals(null))
							sql ="UPDATE OBJETS3D  SET AUTEUR ="+ "'"+ auteur +"'" + "WHERE NAME =" + "'"+ ModificationAjout.this.nomObjet +"';";

						if(cbox1.isSelected()&&!tfield1.getText().equals(null)&&cbox.isSelected()&&!tfield.getText().equals(null)){
							System.out.println("ma grand est decedee");
							//UPDATE `membres` SET `email`="jacques@monfai.fr", `pseudo`="Jacques" WHERE `id`=1;
							sql ="UPDATE OBJETS3D  SET NAME ="+ "'"+ auteur +"'" + ", AUTEUR ="+ "'"+ auteur +"'" + "WHERE NAME =" + "'"+ nomObjet +"';";
							System.out.println(sql);
							//sql ="UPDATE OBJETS3D  SET NAME = '"+name+"' WHERE condition"
						}



						Connection c = null;
						Statement stmt = null;
						try {
							Class.forName("org.sqlite.JDBC");
							c = DriverManager.getConnection("jdbc:sqlite:Database.db");
							c.setAutoCommit(false);
							stmt = c.createStatement();
							System.out.println("je suis la");

							//String sql1="INSERT INTO OBJETS3D (NAME,AUTEUR) " +
							//	"VALUES ('"+name+"', '"+auteur+"' );";



							//nous avons besoin de l'objet pour recuperer sa complexite il s'agira d'un objet qu'on dont-on a teste l'appartenance a la communaute gts
							//complexite a modifier lors de l'insertion appeler la methode getface size sur le fichier charge!!!!!!!!!!!!!


							stmt.executeUpdate(sql);
							Object[] options = {" Ok,Visualiser Objet",
							"Modifier les informations"};

							@SuppressWarnings("unused")
							final JOptionPane optionPane=new JOptionPane();

							int n = JOptionPane.showOptionDialog(frame,
									"Objet bien ajoute",
									"",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null,     //do not use a custom Icon
									options,  //the titles of buttons
									options[0]); //default button title

							if (n == JOptionPane.YES_OPTION) {
								frame.dispose();
								AffichageDuModele aff = null;
								aff.nouvelOnglet(ModificationAjout.this.nomObjet);


							} else if (n== JOptionPane.NO_OPTION) {
								frame.dispose();
								/*nouvelle fenetre ajout avec tous ce qu'il y a dans ajout sans le jtextfield et dont les info serviront 
		a modifier l'objet dans la base de donnees vendredi*/ 
								new ModificationAjout(ModificationAjout.this.nomObjet);
							}
							




							stmt.close(); 
							c.commit();
							c.close();
						} catch ( Exception e1 ) {
							System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
							System.exit(0);


						}
					}
			}





		});

		valider.setPreferredSize(d);
		pa1.add(valider);
		c.add(pa1);


		JPanel pa2=new JPanel();
		JButton annuler=new JButton("Annuler ");
		annuler.setPreferredSize(d);
		pa2.add(annuler);
		c.add(pa2);

		// ferme l'application
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(640,480);
		frame.setResizable(false);
		frame.setVisible(true);





	}
}
