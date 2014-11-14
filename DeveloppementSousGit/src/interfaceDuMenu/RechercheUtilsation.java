package interfaceDuMenu;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RechercheUtilsation extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTextField jt=new JTextField("");

	public static void creation(){
		final JFrame f=new JFrame();
		Container c=f.getContentPane();
		c.setLayout(new BorderLayout());
		Image wall=f.getToolkit().getImage("ressources/imageMenu/utilisation.png/");
		Icon ic=new ImageIcon(wall);
		JLabel jl=new JLabel(ic);
		c.add(jl,BorderLayout.NORTH);

		JPanel pa1=new JPanel();
		final JTextField jt=new JTextField("");
		jt.setPreferredSize(new Dimension(250,50));
		pa1.add(jt);
		c.add(pa1,BorderLayout.CENTER); //on l'ajoute à la fenêtre
		JPanel pa11=new JPanel();
		JButton bu=new JButton("Valider");
		bu.setPreferredSize(new Dimension(150,45));
		pa11.add(bu);
		c.add(pa11,BorderLayout.SOUTH);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(850,400);
		f.setResizable(false);
		f.setVisible(true);
		bu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(jt.getText().isEmpty()){
					Erreur.creation();

				}else{
					searchInDataBase();
				}
			}
		});
	}
	static class Erreur extends JFrame{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public static final JFrame jf=new JFrame();
		public static void creation(){
			Container c=jf.getContentPane();
			c.setLayout(new BorderLayout());
			Image wall=jf.getToolkit().getImage("ressources/imageMenu/erreur.gif/");
			Icon ic=new ImageIcon(wall);
			JLabel jl=new JLabel(ic);
			c.add(jl,BorderLayout.CENTER);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setSize(400,250);
			jf.setResizable(false);
			jf.setVisible(true);


		}

	}



	public static void searchInDataBase(){
		/*
		 Connection c = null;
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
		      while ( rs.next() ) {
		         int id = rs.getInt("id");
		         String  name = rs.getString("name");
		         int age  = rs.getInt("age");
		         String  address = rs.getString("address");
		         float salary = rs.getFloat("salary");
		         System.out.println( "ID = " + id );
		         System.out.println( "NAME = " + name );
		         System.out.println( "AGE = " + age );
		         System.out.println( "ADDRESS = " + address );
		         System.out.println( "SALARY = " + salary );
		         System.out.println();
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Operation done successfully");

*/
	}
	

}
