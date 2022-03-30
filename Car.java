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
		Graphics2D g2d = (Graphics2D) g;
		if(road.isVertical()){
			if(road.goPositive() == 1) g2d.drawImage(new ImageIcon("Images/car4.png").getImage(), position[0] - size[1]/2, position[1] - size[0]/2, size[1], size[0], null);
			else g2d.drawImage(new ImageIcon("Images/car2.png").getImage(), position[0] - size[1]/2, position[1] - size[0]/2, size[1], size[0], null);


		}else{
			if(road.goPositive() == 1) g2d.drawImage(new ImageIcon("Images/car.png").getImage(), position[0] - size[0]/2, position[1] - size[1]/2, size[0], size[1], null);
			else g2d.drawImage(new ImageIcon("Images/car3.png").getImage(), position[0] - size[0]/2, position[1] - size[1]/2, size[0], size[1], null);

		}
	}

	// initialise la position de la voiture
	public void setPosition(int distance){
		super.setPosition(distance);
	}

}

