import java.awt.*;
import javax.swing.*;

/*
Remarques :
methode move -> !isOnTheRoad() : il faudrait faire repartir le vehicule de plus loin (avant point de depart de la route)
*/


public abstract class Vehicule {
	protected int[] position = new int[2];		// x y (point en haut a gauche)  A generaliser de maniere a definir cette position comme le centre du vehicule
	protected double speed;						// en pixel par ms
	protected int[] size = new int[2];			// taille longueur puis largeur
	protected Road road;						// on assigne a une route a un vehicule

	
	
	public Vehicule(double s, Road r){
		speed = s;			// vitesse initiale
		road = r;
	}

	// methode abstraite permettant ensuite de positionner initialement le vehicule
	public void setPosition(){}

	public int[] getPosition(){
		return position;
	}

	public double getSpeed(){
		return speed;
	}

	public Road getRoad(){
		return road;
	}

	// Permet de changer la position du vehicule en fonction du temps
	public void move(int dt){
		if(!isOnTheRoad()){
			position[0] = road.getStartingPoint()[0];
			position[1] = road.getStartingPoint()[1];
		}else if(road.isVertical()){
			position[1] += dt * speed * road.direction;
		}else{
			position[0] += dt * speed * road.direction;
		}
	}

	// dessine le vehicule
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		if(road.isVertical()){
			if(road.goPositive() == 1) g2d.drawImage(new ImageIcon("car4.png").getImage(), position[0] - size[0]/2, position[1] - size[1], size[1], size[0], null);
			else g2d.drawImage(new ImageIcon("car2.png").getImage(), position[0] - size[0]/2, position[1] - size[1], size[1], size[0], null);


		}else{
			if(road.goPositive() == 1) g2d.drawImage(new ImageIcon("car.png").getImage(), position[0] - size[0]/2, position[1] - size[1], size[0], size[1], null);
			else g2d.drawImage(new ImageIcon("car3.png").getImage(), position[0] - size[0]/2, position[1] - size[1], size[0], size[1], null);

		}
	}

	// renvoie false si le vamex a depasse le point d arrivee de la route (sers a faire repartir le véhicule au point de depart)
	public boolean isOnTheRoad(){
		if(road.goPositive()==1){
			if(road.isVertical()){
				return position[1]-size[0]/2 < road.getEndingPoint()[1];
			}else{
				return position[0]-size[0]/2 < road.getEndingPoint()[0];
			}
		}else{
			if(road.isVertical()){
				return position[1]+size[0]/2 > road.getEndingPoint()[1];
			}else{
				return position[0]+size[0]/2 > road.getEndingPoint()[0];
			}
		}
	}
	
}
