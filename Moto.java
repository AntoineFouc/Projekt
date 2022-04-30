import java.awt.*;
import javax.swing.*;

public class Moto extends Vehicule {

	public Moto(Road r, double s, double a) {
		super(r, s, a);
		size[0] = 43; // taille des motos : longueur
		size[1] = 20; // largeur
		String pictureName = "Images/moto-" + Integer.toString(road.getOrientation()) + ".png";
		picture = new ImageIcon(pictureName);
		position = -size[0] / 2;
	}

	public void move(Graphics g, int dt) {
		super.move(dt);
	}

}
