import java.awt.*;
import javax.swing.*;
import java.util.*;

public class feurouge extends obstacle implements Comparable<obstacle> {
	protected Road voiebloq;
	protected int temps;
	private int timer;
	private int etat;

	public feurouge(int uneposx, int uneposy, Road unevoiebloq, int untemps) {
		super(uneposx, uneposy, unevoiebloq);
		temps = untemps;
		timer = 0;
		etat = 0;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		String image;
		if (etat == 0) {
			image = "Images/feuvert.png";
		} else if (etat == 1) {
			image = "Images/feuorange.png";
		} else {
			image = "Images/feurouge.png";
		}

		if (road.getOrientation() == 270)
			g2d.drawImage(new ImageIcon(image).getImage(), 351 - 40, y - 135, 40, 135, null);
		else if (road.getOrientation() == 90) {
			g2d.drawImage(new ImageIcon(image).getImage(), 459 - 10, y - 135, 40, 135, null);
		} else if (road.getOrientation() == 0)
			g2d.drawImage(new ImageIcon(image).getImage(), x - 20, 459 - 20, 40, 135, null);
		else if (road.getOrientation() == 180) {
			g2d.drawImage(new ImageIcon(image).getImage(), x - 10, 351 - 135, 40, 135, null);
		}
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getTimer() {
		int time = timer;
		return time;
	}

	public void setTimer(int time) {
		timer = time;
	}

	public void update(int time) {
		if (timer == temps) {
			if (etat == 0) {
				etat = 1;
			} else if (etat == 1) {
				etat = 2;
			} else {
				etat = 0;
			}
			timer = 0;
		}
	}
}
