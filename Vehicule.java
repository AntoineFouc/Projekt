import java.awt.*;
import javax.swing.*;
import java.util.*;

/*
Remarques :
Mettre une seule valeur en position (car route connue)
Vitesse reelle : 1 px/ms = 600 km/h
*/


public abstract class Vehicule {
	// protected int[] initPosition = new int[2];
	protected int[] position = new int[2];		// x y (point en haut a gauche)  A generaliser de maniere a definir cette position comme le centre du vehicule
	protected double maxSpeed;
	protected double speed;						// en pixel par ms
	protected int[] size = new int[2];			// taille longueur puis largeur
	protected Road road;						// on assigne a une route a un vehicule
	public ArrayList<Vehicule> vehicules;



	public Vehicule(double s, Road r, int distance){
		speed = s;			// vitesse initiale
		road = r;
		setPosition(distance);
		// initPosition[0] = position[0];
		// initPosition[1] = position[1];
	}

	// methode abstraite permettant ensuite de positionner initialement le vehicule
	public void setPosition(int distance){
		if(road.isVertical()){
			position[1] = road.getStartingPoint()[1] + distance*road.goPositive();
			position[0] = road.getStartingPoint()[0];
		}else{
			position[0] = road.getStartingPoint()[0] + distance*road.goPositive();
			position[1] = road.getStartingPoint()[1];
		}
	}

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
			if(road.isVertical()){
				position[0] = road.getStartingPoint()[0];
				position[1] = road.getStartingPoint()[1] - size[0]*road.goPositive();
			}else{
				position[0] = road.getStartingPoint()[0]- size[0]*road.goPositive();
				position[1] = road.getStartingPoint()[1];
			}
		}else if(road.isVertical()){
			position[1] += dt * speed * road.goPositive();
		}else{
			position[0] += dt * speed * road.goPositive();
		}
	}

	// dessine le vehicule
	public void draw(Graphics g){
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

	public void setVehicules(ArrayList<Vehicule> v){
		vehicules = v;
	}
}