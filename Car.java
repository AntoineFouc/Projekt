import java.awt.*;
import javax.swing.*;

/*
Remarques :
methode setPosition : peut etre deplacee dans la classe vehicule
*/

public class Car extends Vehicule{
    
	public Car(double s, Road r, int distance){
		super(s,r, distance);
		size[0] = 40;				// taille des voitures : longueur
		size[1] = 20;				// largeur
	}

	public void move(Graphics g, int dt){
		super.move(dt);
	}

	public void draw(Graphics g){
		super.draw(g);
	}

	// initialise la position de la voiture
	public void setPosition(int distance){
		super.setPosition(distance);
	}

}
