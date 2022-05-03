import java.awt.*;
import javax.swing.*;
import java.util.*;

/*
Remarques :
Vitesse reelle : 1 px/ms = 600 km/h
 */

public abstract class Vehicule implements Comparable<Vehicule> {
	protected double position;
	protected double vitesseMax;
	protected double speed; // en pixel par ms
	protected double maxAccel;
	protected double accel; // acceleration
	protected Obstacle nextObstacle;
	protected int prio = 0;
	protected Vehicule nextVehicule;
	protected int ObstaclesCompteur;
    protected int second;
	protected double stopStartTime;
	protected boolean testStop = false;
	protected double safeDistance; // cte pour le moment
	protected int[] size = new int[2]; // taille longueur puis largeur
	protected Route route; // on assigne a une route a un vehicule
	protected ImageIcon picture;

	public Vehicule(Route r, double s, double a) {
		speed = s; // vitesse initiale
		vitesseMax = s*1.3;
		maxAccel = a;
		accel = 0;
		route = r;
        if(route.getStartingPoint()[0]==route.getEndingPoint()[0]){
            second=route.getStartingPoint()[0];
        } else {
            second=route.getStartingPoint()[1];
        }
	}

	// Permet de changer la position du vehicule en fonction du temps
	public void move(int dt) {
		if (speed < 0.03 && accel<0) {
			speed = 0.000;
		}
        speed += dt * accel;
        position += dt * speed;
		setSafeDistance();
	}

	public void accel() {
		if (speed > vitesseMax||accel>maxAccel) {
			speed = vitesseMax;
			accel = 0;
		} else {
			accel = maxAccel;
		}
	}

	public void stopAt(double x) {
		accel = -Math.pow(speed, 2) / (x - position);
	}

	public void deccel() {
        if (speed < 0) {
			speed = 0;
		}
		accel = -maxAccel;
	}

	public void deccelTo(double v) {
		speed = v;
		accel = 0;
	}

	public void noAccel() {
		accel = 0;
	}

	// dessine le vehicule
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		switch (route.getOrientation()) {
		case 0:
			g2d.drawImage(picture.getImage(), (int) (route.getStartingPoint()[0] + (int) position - size[0] / 2.0),
					(int) (route.getStartingPoint()[1] - size[1] / 2.0), size[0], size[1], null);
			break;
		case 90:
			g2d.drawImage(picture.getImage(), (int) (route.getStartingPoint()[0] - size[1] / 2.0),
					(int) (route.getStartingPoint()[1] - (int) position - size[0] / 2.0), size[1], size[0], null);
			break;
		case 180:
			g2d.drawImage(picture.getImage(), (int) (route.getStartingPoint()[0] - (int) position - size[0] / 2.0),
					(int) (route.getStartingPoint()[1] - size[1] / 2.0), size[0], size[1], null);
			break;
		case 270:
			g2d.drawImage(picture.getImage(), (int) (route.getStartingPoint()[0] - size[1] / 2.0),
					(int) (route.getStartingPoint()[1] + (int) position - size[0] / 2.0), size[1], size[0], null);
			break;
		}
	}

	// renvoie false si le vamex a depasse le point d arrivee de la route (sers a
	// faire repartir le véhicule au point de depart)
	public boolean isOnTheRoute() {
		return position < 800 + size[0] / 2; // ou plutôt "longueur de la route" pour pouvoir adapter
	}

	public boolean equals(Vehicule v) {
		return (v.getPosition() == position && route.equals(v.getRoute()));
	}

	public double getPosition() {
		return position;
	}

	public void setPosition(double p) {
		position = p;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double s) {
		speed = s;
	}

	public double getAccel() {
		return accel;
	}

	public void setAccel(double a) {
		accel = a;
	}

	public double getMaxAccel() {
		return maxAccel;
	}

	public int[] getSize() {
		return size;
	}

	public Route getRoute() {
		return route;
	}

	public void setPicture(ImageIcon i) {
		picture = i;
	}

	public Obstacle getNextObstacle() {
		return nextObstacle;
	}

	public void setNextObstacle(Obstacle nextObstacle) {
		this.nextObstacle = nextObstacle;
	}

	public void setSafeDistance() {
			safeDistance = (speed * 100) + size[0]*1.2 ; // variation de la distance de securite en fonction de la vitesse du vehicule
	}

	public double getSafeDistance() {
		return safeDistance;
	}

	public double getSafePosition() {
		return getBack() - safeDistance;
	}

	public double getVision() {
		return getFront() + safeDistance;
	}

	public int getFront() {
		return (int) position + size[0] / 2;
	}

	public int getBack() {
		return (int) position - size[0] / 2;
	}

	public int getPrio() {
		return prio;
	}

	public void setPrio(int prio) {
		this.prio = prio;
	}

	public int getObstaclesCompteur() {
		return ObstaclesCompteur;
	}

	public void setObstaclesCompteur(int obstaclesCompteur) {
		ObstaclesCompteur = obstaclesCompteur;
	}

	public Vehicule getNextVehicule() {
		return nextVehicule;
	}

	public void setNextVehicule(Vehicule nextVehicule) {
		this.nextVehicule = nextVehicule;
	}

	public double getVitesseMax() {
		return vitesseMax;
	}

	public void setVitesseMax(double vitesseMax) {
		this.vitesseMax = vitesseMax;
	}

	public double getStopStartTime() {
		return stopStartTime;
	}

	public void setStopStartTime(double stopStartTime) {
		this.stopStartTime = stopStartTime;
	}

	public boolean isTestStop() {
		return testStop;
	}

	public void setTestStop(boolean testStop) {
		this.testStop = testStop;
	}

	public Rectangle getRectangle() {
		Rectangle r = new Rectangle();
		switch (route.getOrientation()) {
		case 0:
			r = new Rectangle((int) (route.getStartingPoint()[0] + (int) position - size[0] / 2.0),
					(int) (route.getStartingPoint()[1] - size[1] / 2.0), size[0], size[1]);
			break;
		case 90:
			r = new Rectangle((int) (route.getStartingPoint()[0] - size[1] / 2.0),
					(int) (route.getStartingPoint()[1] - (int) position - size[0] / 2.0), size[1], size[0]);
			break;
		case 180:
			r = new Rectangle((int) (route.getStartingPoint()[0] - (int) position - size[0] / 2.0),
					(int) (route.getStartingPoint()[1] - size[1] / 2.0), size[0], size[1]);
			break;
		case 270:
			r = new Rectangle((int) (route.getStartingPoint()[0] - size[1] / 2.0),
					(int) (route.getStartingPoint()[1] + (int) position - size[0] / 2.0), size[1], size[0]);
			break;
		}
		return r;
	}

	public void aChange() {
		if (accel != 0)
			System.out.println("A = " + accel);
	}

	public int compareTo(Vehicule v) {
		int res;
		if (this.getPosition() > v.getPosition()) {
			res = 1;
		} else if (this.getPosition() == v.getPosition()) {
			res = 0;
		} else {
			res = -1;
		}
		return res;
	}
    
    public double getDistToKroof(carrefour kroof){
        double kpos=0;
        if((this.route).getOrientation()%180==0){ // route horizontale
            kpos=kroof.getx();
        } else { //route verticale
            kpos=kroof.gety();
        }
        double dist=kpos-(int)position;
        return dist;
    }
    
    public double getDistToObstacle(Obstacle obs){
        return(Math.abs(obs.getPosition()-this.getPosition()));
    }
    
    public int getSecond(){
        return second;
    }
}
