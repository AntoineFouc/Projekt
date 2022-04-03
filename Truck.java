import java.awt.*;
import javax.swing.*;



public class Truck extends Vehicule{
    
	public Truck(double s, Road r, int distance){
		super(s,r, distance);
		size[0] = 141;				// taille des camions : longueur
		size[1] = 20;				// largeur
		String pictureName = "Images/truck-" + Integer.toString(road.getOrientation()) + ".png";
		picture = new ImageIcon(pictureName);
	}

	public void move(Graphics g, int dt){
		super.move(dt);
	}

}

