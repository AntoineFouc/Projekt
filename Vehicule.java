import java.awt.*;
import javax.swing.*;
import java.util.*;

/*
Remarques :
Vitesse reelle : 1 px/ms = 600 km/h
*/


public abstract class Vehicule {
	protected double position;
	protected int initialPosition;
	protected double maxSpeed;
	protected double speed;						// en pixel par ms
	protected double accel;						// acceleration

	protected int[] size = new int[2];			// taille longueur puis largeur
	protected Road road;						// on assigne a une route a un vehicule
	protected ImageIcon picture;
	public ArrayList<Vehicule> vehicules;



	public Vehicule(double s, Road r, int distance){
		speed = s;			// vitesse initiale
		maxSpeed = s;
		road = r;
		accel = 0.002;
		initialPosition=distance;
		position = distance;
	}

	// Permet de changer la position du vehicule en fonction du temps
	public void move(int dt){
		if(!isOnTheRoad()){
			position = - size[0]/2;
		}else{
			position += dt*speed;
		}
	}




	public void stopAtX (double X){
		double d   =  X - this.position;
		this.accel = -Math.pow(speed,2)/d;
	}







	/*
	Methode interaction a completer : créer des méthode pour freiner, accélérer en dessous pour s'en servir dans interaction
	*/
	public void interaction(int dt){
		for(Vehicule v : vehicules){
			if(!equals(v)){


				/*
					Chaque cas d'interaction :
						Trop près du véhicule devant
						Priorité à droite
						puis obstacle
				*/




				//adaptation de la vitesse du véhicule en fonction des autres véhicules

				// pas parfait, juste test, a changer
				if(road.equals(v.getRoad()) && position < v.getPosition() && v.getPosition()-v.getSize()[0]/2.0-position-size[0]/2.0 < 50){
					speed += - dt*accel;
				}else if(speed<maxSpeed){
					if(speed+dt*accel > maxSpeed){
						speed = maxSpeed;
					}
					else{
						speed += dt*accel;
					}
				}







			}
		}
	}

	//Ralentir jusqu'à une certaine vitesse
	/*public void deccel(speed s){
		if(speed+dt*accel < s){

		}


	}*/


























	// dessine le vehicule
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;

		switch(road.getOrientation()){
			case 0:
				g2d.drawImage(picture.getImage(), (int) (road.getStartingPoint()[0] + (int) position - size[0]/2.0), (int) (road.getStartingPoint()[1] - size[1]/2.0), size[0], size[1], null);
				break;
			case 90:
				g2d.drawImage(picture.getImage(), (int) (road.getStartingPoint()[0] - size[1]/2.0), (int) (road.getStartingPoint()[1] - (int) position - size[0]/2.0), size[1], size[0], null);
				break;
			case 180:
				g2d.drawImage(picture.getImage(), (int) (road.getStartingPoint()[0] - (int) position - size[0]/2.0), (int) (road.getStartingPoint()[1] - size[1]/2.0), size[0], size[1], null);
				break;
			case 270:
				g2d.drawImage(picture.getImage(), (int) (road.getStartingPoint()[0] - size[1]/2.0), (int) (road.getStartingPoint()[1] + (int) position - size[0]/2.0), size[1], size[0], null);
				break;
		}
	}

	public void resetPosition(){
		position = initialPosition;
	}

	// renvoie false si le vamex a depasse le point d arrivee de la route (sers a faire repartir le véhicule au point de depart)
	public boolean isOnTheRoad(){
		return position < 800 + size[0]/2;			// ou plutôt "longueur de la route" pour pouvoir adapter
	}

	public boolean equals(Vehicule v){
		return ( v.getInitialPosition() == initialPosition && road.equals(v.getRoad()) );
	}


	public double getInitialPosition(){
		return initialPosition;
	}

	public double getPosition(){
		return position;
	}



	public double getSpeed(){
		return speed;
	}

	public int[] getSize(){
		return size;
	}

	public Road getRoad(){
		return road;
	}

	public void setVehicules(ArrayList<Vehicule> v){
		vehicules = v;
	}

	public void setPicture(ImageIcon i){
		picture = i;
	}
}
