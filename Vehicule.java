import java.awt.*;
import javax.swing.*;
import java.util.*;

/*
Remarques :
Vitesse reelle : 1 px/ms = 600 km/h
*/


public abstract class Vehicule {
	protected double position;
	protected double maxSpeed;
	protected double speed;						// en pixel par ms
	protected double maxAccel;
	protected double accel;						// acceleration

	protected int safeDistance;					// cte pour le moment
	protected int[] size = new int[2];			// taille longueur puis largeur
	protected Road road;						// on assigne a une route a un vehicule
	protected ImageIcon picture;

	public Vehicule(Road r, double s, double a){
		speed = s;			// vitesse initiale
		maxSpeed = s;
		maxAccel = a;
		accel = 0;
		road = r;
	}

	// Permet de changer la position du vehicule en fonction du temps
	public void move(int dt){
			speed += dt*accel;
			position += dt*speed;
			setSafeDistance();
	}

	public void accel(){
		if(speed>maxSpeed){
			speed=maxSpeed;
			accel=0;
		}else{
			accel = maxAccel;
		}
	}

	public void stopAt(double x){
		accel = - Math.pow(speed,2) / (x-position);
	}

	public void deccel(){
			accel = - maxAccel;
	}

	public void deccelTo(double v){
		if(speed < v){
			accel = 0;
		}else{
			accel = - maxAccel;
		}
	}

	public void noAccel(){
		accel = 0;
	}


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

	// renvoie false si le vamex a depasse le point d arrivee de la route (sers a faire repartir le véhicule au point de depart)
	public boolean isOnTheRoad(){
		return position < 800 + size[0]/2;			// ou plutôt "longueur de la route" pour pouvoir adapter
	}

	public boolean equals(Vehicule v){
		return ( v.getPosition() == position && road.equals(v.getRoad()) );
	}

	public double getPosition(){
		return position;
	}

	public void setPosition(double p){
		position = p;
	}

	public double getSpeed(){
		return speed;
	}

	public void setSpeed(double s){
		speed = s;
	}

	public double getAccel(){
		return accel;
	}

	public void setAccel(double a){
		accel = a;
	}

	public double getMaxAccel(){
		return maxAccel;
	}

	public int[] getSize(){
		return size;
	}

	public Road getRoad(){
		return road;
	}

	public void setPicture(ImageIcon i){
		picture = i;
	}

	public void setSafeDistance(){
		safeDistance = 50;
	}

	public int getSafePosition(){
		return (int) position - safeDistance - size[0]/2;
	}

	public int getFront(){
		return (int) position + size[1]/2;
	}

	public int getBack(){
		return (int) position - size[1]/2;
	}

	public Rectangle getRectangle(){
		Rectangle r = new Rectangle();
		switch(road.getOrientation()){
			case 0:
				r = new Rectangle((int) (road.getStartingPoint()[0] + (int) position - size[0]/2.0), (int) (road.getStartingPoint()[1] - size[1]/2.0), size[0], size[1]);
				break;
			case 90:
				r = new Rectangle((int) (road.getStartingPoint()[0] - size[1]/2.0), (int) (road.getStartingPoint()[1] - (int) position - size[0]/2.0), size[1], size[0]);
				break;
			case 180:
				r = new Rectangle((int) (road.getStartingPoint()[0] - (int) position - size[0]/2.0), (int) (road.getStartingPoint()[1] - size[1]/2.0), size[0], size[1]);
				break;
			case 270:
				r = new Rectangle((int) (road.getStartingPoint()[0] - size[1]/2.0), (int) (road.getStartingPoint()[1] + (int) position - size[0]/2.0), size[1], size[0]);
				break;
		}
		return r;
	}

	public void aChange(){
		if(accel!=0) System.out.println("A = " + accel);
	}

}
