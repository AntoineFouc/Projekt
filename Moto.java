import java.awt.*;
import javax.swing.*;

public class Moto extends Vehicule {

	public Moto(Route r, double s, double a) {
		super(r, s, a);
		size[0] = 32; // taille des motos : longueur
		size[1] = 14; // largeur
		String pictureName = "Images/moto-" + Integer.toString(Route.getOrientation()) + ".png";
		picture = new ImageIcon(pictureName);
		position = -size[0] / 2;
	}

	public void move(Graphics g, int dt) {
		super.move(dt);
	}

}
