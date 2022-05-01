import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Limitation extends Obstacle implements Comparable<Obstacle> {
	protected Route voiebloq;
	protected double valeur;

	public Limitation(int uneposx, int uneposy, Route unevoiebloq, int unevaleur) {
		super(uneposx, uneposy, unevoiebloq);
		valeur = unevaleur;
		number = 4;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Marianne", Font.PLAIN, 24);
		g2d.setFont(font);
		if (Route.getOrientation() == 270) {
			g2d.drawImage(new ImageIcon("Images/limitation.png").getImage(), 351 - 38, y - 20, 40, 40, null); // route 3
			g2d.drawString(String.valueOf(valeur), 351 - 38 + 10, y - 20 + 28);
		} else if (Route.getOrientation() == 90) {
			g2d.drawImage(new ImageIcon("Images/limitation.png").getImage(), 459 - 20, y - 20, 40, 40, null); // route 4
			g2d.drawString(String.valueOf(valeur), 459 - 20 + 10, y - 20 + 28);
		} else if (Route.getOrientation() == 0) {
			g2d.drawImage(new ImageIcon("Images/limitation.png").getImage(), x - 30, 459 - 18, 40, 40, null); // route 2
			g2d.drawString(String.valueOf(valeur), x - 30 + 8, 459 - 18 + 28);
		} else if (Route.getOrientation() == 180) {
			g2d.drawImage(new ImageIcon("Images/limitation.png").getImage(), x - 30, 351 - 30, 40, 40, null); // route 1
			g2d.drawString(String.valueOf(valeur), x - 30 + 8, 351 - 30 + 28);
		}
	}

	public double getLimite() {
		double limite = valeur;
		return limite;
	}

	public Route getVoiebloq() {
		return voiebloq;
	}

	public void setVoiebloq(Route voiebloq) {
		this.voiebloq = voiebloq;
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

}
