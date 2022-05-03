import java.awt.*;
import javax.swing.*;

public class Stop extends Obstacle implements Comparable<Obstacle> {
	protected Route voiebloq;

	public Stop(int uneposx, int uneposy, Route unevoiebloq) {
		super(uneposx, uneposy, unevoiebloq);
		voiebloq = unevoiebloq;
		number = 5;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (route.getOrientation() == 270)
			g2d.drawImage(new ImageIcon("Images/panneau stop.png").getImage(), 351 - 40, y - 30, 50, 50, null);
		else if (route.getOrientation() == 90) {
			g2d.drawImage(new ImageIcon("Images/panneau stop.png").getImage(), 459 - 20, y - 30, 50, 50, null);

		} else if (route.getOrientation() == 0)
			g2d.drawImage(new ImageIcon("Images/panneau stop.png").getImage(), x - 30, 459 - 20, 50, 50, null);
		else if (route.getOrientation() == 180) {
			g2d.drawImage(new ImageIcon("Images/panneau stop.png").getImage(), x - 30, 351 - 40, 50, 50, null);
		}
	}
}
