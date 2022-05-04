import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Barriere extends Obstacle implements Comparable<Obstacle> {
	protected Route voiebloq;
	public Barriere(int uneposx, int uneposy, Route unevoiebloq) {
		super(uneposx, uneposy, unevoiebloq);
		number = 3;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (Route.getOrientation() == 270) {
			g2d.drawImage(new ImageIcon("Images/barriere.png").getImage(), 351 - 15, y - 20, 62, 40, null); // route 3
		} else if (Route.getOrientation() == 90) {
			g2d.drawImage(new ImageIcon("Images/barriere.png").getImage(), 459 - 60, y - 20, 62, 40, null); // route 4
		} else {
			File file = new File("Images/barriere.png");
			BufferedImage pic = null;
			try {
				pic = ImageIO.read(file);
			} catch (IOException e) {
			}
			if (Route.getOrientation() == 0) { // route 2
				double rotationRequired = Math.toRadians(90);
				AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, 20, 20);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
				g2d.drawImage(op.filter(pic, null), x, 459 - 60, 40, 62, null);
				// rotation puis placement
			} else if (Route.getOrientation() == 180) { // route 1
				double rotationRequired = Math.toRadians(-90);
				AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, 30, 30);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
				g2d.drawImage(op.filter(pic, null), x, 351 - 10, 40, 62, null);
				// rotation puis placement
			}
		}
	}

}
