import java.awt.*;
import javax.swing.*;

/*
Remarques :
methode setPosition : peut etre deplacee dans la classe vehicule
 */

public class Car extends Vehicule {

	public Car(Road r, double s, double a) {
		super(r, s, a);
		size[0] = 40; // taille des voitures : longueur
		size[1] = 20; // largeur
		String pictureName = "Images/car-" + Integer.toString(road.getOrientation()) + ".png";
		picture = new ImageIcon(pictureName);
		position = -size[0] / 2;
	}

	public void move(Graphics g, int dt) {
		super.move(dt);
	}

}
