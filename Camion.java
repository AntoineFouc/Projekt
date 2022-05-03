import java.awt.*;
import javax.swing.*;

public class Camion extends Vehicule {

	public Camion(Route r, double s, double a) {
		super(r, s, a);
		size[0] = 141; // taille des camions : longueur
		size[1] = 20; // largeur
		String pictureName = "Images/truck-" + Integer.toString(route.getOrientation()) + ".png";
		picture = new ImageIcon(pictureName);
		position = -size[0] / 2;
	}

	public void move(Graphics g, int dt) {
		super.move(dt);
	}

}
