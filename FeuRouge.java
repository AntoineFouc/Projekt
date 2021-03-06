import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class FeuRouge extends Obstacle implements Comparable<Obstacle> {
	protected Route voiebloq;
	protected double temps;
	private double timer;
	private int etat;

	public FeuRouge(int uneposx, int uneposy, Route unevoiebloq, int untemps, int state) {
		super(uneposx, uneposy, unevoiebloq);
		temps = (double)untemps;
		timer = 0;
		etat = state;
		number = 2;
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

		if (Route.getOrientation() == 270)
			g2d.drawImage(new ImageIcon(image).getImage(), 351 - 40, y - 135, 40, 135, null);
		else if (Route.getOrientation() == 90) {
			g2d.drawImage(new ImageIcon(image).getImage(), 459 - 10, y - 135, 40, 135, null);
		} else if (Route.getOrientation() == 0)
			g2d.drawImage(new ImageIcon(image).getImage(), x - 20, 459 - 20, 40, 135, null);
		else if (Route.getOrientation() == 180) {
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
		int time = (int)timer;
		return time;
	}

	public void setTimer(int time) {
		timer = time;
	}

	public Route getVoiebloq() {
		return voiebloq;
	}

	public void setVoiebloq(Route voiebloq) {
		this.voiebloq = voiebloq;
	}

	public double getTemps() {
		return temps;
	}

	public void setTemps(double temps) {
		this.temps = temps;
	}

	public void setTimer(double timer) {
		this.timer = timer;
	}

	public void update(int time) {
        if (etat == 0 && timer == temps*1.2) {
            etat = 1;
            timer = 0;
        } else if (etat == 1 && timer == temps*0.3) {
            etat = 2;
            timer = 0;
        } else if (etat == 2 && timer == temps*1.5){
            etat = 0;
            timer = 0;
        }
	}
}
