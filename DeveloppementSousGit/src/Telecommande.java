import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Telecommande extends JFrame {
	private EntierObjetTest coeffZ = new EntierObjetTest(0);
	private EntierObjetTest rotationRX = new EntierObjetTest(0);
	private EntierObjetTest rotationRY = new EntierObjetTest(0);
	private EntierObjetTest rotationRZ = new EntierObjetTest(0);
	private EntierObjetTest coeffXetY = new EntierObjetTest(1);
	private EntierObjetTest decalageX = new EntierObjetTest(700/2);
	private EntierObjetTest decalageY = new EntierObjetTest(700/2);
	private JSlider sZ = new JSlider(0, 1000, coeffZ.entier);
	private JSlider sXY = new JSlider(0, 1000, coeffXetY.entier);
	private JSlider sX = new JSlider(0, 4000, decalageX.entier);
	private JSlider sY = new JSlider(0, 4000, decalageY.entier);
	private JSlider sRX = new JSlider(0, 360, rotationRX.entier);
	private JSlider sRY = new JSlider(0, 360, rotationRY.entier);
	private JSlider sRZ = new JSlider(0, 360, rotationRZ.entier);
	
	private JPanel panel1 = new JPanel(new GridLayout(6*2, 1));
	AffichageDuModele affichageDuModele;

	public Telecommande(AffichageDuModele affichageDuModele,double max) {
		this.affichageDuModele = affichageDuModele;
		coeffXetY = new EntierObjetTest((int) max);
		sXY = new JSlider(0,coeffXetY.entier*2, coeffXetY.entier);
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setBackground(Color.GRAY);
		panel1.setBackground(Color.GRAY);
		sX.addChangeListener(new SliderListener(decalageX,
				this.affichageDuModele));
		sZ.addChangeListener(new SliderListener(coeffZ, this.affichageDuModele));
		sRX.addChangeListener(new SliderListener(rotationRX, this.affichageDuModele));
		sRY.addChangeListener(new SliderListener(rotationRY, this.affichageDuModele));
		sRZ.addChangeListener(new SliderListener(rotationRZ, this.affichageDuModele));
		sXY.addChangeListener(new SliderListener(coeffXetY,
				this.affichageDuModele));
		sY.addChangeListener(new SliderListener(decalageY,
				this.affichageDuModele));
		panel1.add(new JLabel("Decalage horizontal : "));
		panel1.add(sX);
		panel1.add(new JLabel("Decalage vertical : "));
		panel1.add(sY);
		panel1.add(new JLabel("Zoom : "));
		panel1.add(sXY);
		/*panel1.add(new JLabel(""));
		panel1.add(sZ);*/
		panel1.add(new JLabel("Rotation par rapport a l'axe des x : "));
		panel1.add(sRX);
		panel1.add(new JLabel("Rotation par rapport a l'axe des y : "));
		panel1.add(sRY);
		panel1.add(new JLabel("Rotation par rapport a l'axe des z : "));
		panel1.add(sRZ);

		this.add(panel1);
		this.setVisible(true);

	}
	public int getCoeffXetY() {
		return coeffXetY.entier;
	}

	public int getCoeffZ() {
		return coeffZ.entier;
	}
	
	public int getDecalageX() {
		return decalageX.entier;
	}

	public int getDecalageY() {
		return decalageY.entier;
	}
	public int getRotationX(){
		return rotationRX.entier;
	}
	public int getRotationY(){
		return rotationRY.entier;
	}
	public int getRotationZ(){
		return rotationRZ.entier;
	}
	
	
	private class SliderListener implements ChangeListener {
		EntierObjetTest value;
		AffichageDuModele affichageDuModele;

		public SliderListener(EntierObjetTest value, AffichageDuModele affichageDuModele) {
			this.value = value;
			this.affichageDuModele = affichageDuModele;
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider) e.getSource();
			this.value.entier = (int) source.getValue();
			//this.affichageDuModele.update(this.affichageDuModele.getGraphics());
			this.affichageDuModele.repaint();
		}

	}
}
