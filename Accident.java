import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Accident extends JFrame {

	public Accident() {
		setTitle("Accident");
		setLayout(null);
		setSize(800, 800);
		setResizable(false);
		setVisible(true);
		repaint();
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(new ImageIcon("Images/accident.png").getImage(), 0, 0, null); // affichage du fond
	}
}
