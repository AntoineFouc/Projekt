import java.awt.*;
import javax.swing.*;



public class Truck extends Vehicule{
    
	public Truck(double s, Road r, int distance){
		super(s,r, distance);
		size[0] = 141;				// taille des camions : longueur
		size[1] = 20;				// largeur
	}

	public void move(Graphics g, int dt){
		super.move(dt);
	}

	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
        if(road.isVertical()){
			if(road.goPositive() == 1) g2d.drawImage(new ImageIcon("truckbas.png").getImage(), position[0] - size[1]/2, position[1] - size[0]/2, size[1], size[0], null);
			else g2d.drawImage(new ImageIcon("truckhaut.png").getImage(), position[0] - size[1]/2, position[1] - size[0]/2, size[1], size[0], null);


		}else{
			if(road.goPositive() == 1) g2d.drawImage(new ImageIcon("truckdroite.png").getImage(), position[0] - size[0]/2, position[1] - size[1]/2, size[0], size[1], null);
			else g2d.drawImage(new ImageIcon("truckgauche.png").getImage(), position[0] - size[0]/2, position[1] - size[1]/2, size[0], size[1], null);

		}
	}

	// initialise la position du camion
	public void setPosition(int distance){
		super.setPosition(distance);
	}

}

