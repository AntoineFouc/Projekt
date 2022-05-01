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
	protected double stopStartTime;
	protected boolean testStop = false;
	protected int safeDistance; // cte pour le moment
	protected int[] size = new int[2]; // taille longueur puis largeur
	protected Route Route; // on assigne a une route a un vehicule
	protected ImageIcon picture;

	public Vehicule(Route r, double s, double a) {
		speed = s; // vitesse initiale
		vitesseMax = s;
		maxAccel = a;
		accel = 0;
		Route = r;
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
		if (speed > vitesseMax) {
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

		switch (Route.getOrientation()) {
		case 0:
			g2d.drawImage(picture.getImage(), (int) (Route.getStartingPoint()[0] + (int) position - size[0] / 2.0),
					(int) (Route.getStartingPoint()[1] - size[1] / 2.0), size[0], size[1], null);
			break;
		case 90:
			g2d.drawImage(picture.getImage(), (int) (Route.getStartingPoint()[0] - size[1] / 2.0),
					(int) (Route.getStartingPoint()[1] - (int) position - size[0] / 2.0), size[1], size[0], null);
			break;
		case 180:
			g2d.drawImage(picture.getImage(), (int) (Route.getStartingPoint()[0] - (int) position - size[0] / 2.0),
					(int) (Route.getStartingPoint()[1] - size[1] / 2.0), size[0], size[1], null);
			break;
		case 270:
			g2d.drawImage(picture.getImage(), (int) (Route.getStartingPoint()[0] - size[1] / 2.0),
					(int) (Route.getStartingPoint()[1] + (int) position - size[0] / 2.0), size[1], size[0], null);
			break;
		}
	}

	// renvoie false si le vamex a depasse le point d arrivee de la route (sers a
	// faire repartir le véhicule au point de depart)
	public boolean isOnTheRoute() {
		return position < 800 + size[0] / 2; // ou plutôt "longueur de la route" pour pouvoir adapter
	}

	public boolean equals(Vehicule v) {
		return (v.getPosition() == position && Route.equals(v.getRoute()));
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
		return Route;
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
			safeDistance = size[0]*2 + (int) speed * 2; // 1 sec de réaction + 1 sec de freinage
	}

	public int getSafeDistance() {
		return safeDistance;
	}

	public int getSafePosition() {
		return (int) getBack() - safeDistance;
	}

	public int getVision() {
		return (int) getFront() + safeDistance;
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
		switch (Route.getOrientation()) {
		case 0:
			r = new Rectangle((int) (Route.getStartingPoint()[0] + (int) position - size[0] / 2.0),
					(int) (Route.getStartingPoint()[1] - size[1] / 2.0), size[0], size[1]);
			break;
		case 90:
			r = new Rectangle((int) (Route.getStartingPoint()[0] - size[1] / 2.0),
					(int) (Route.getStartingPoint()[1] - (int) position - size[0] / 2.0), size[1], size[0]);
			break;
		case 180:
			r = new Rectangle((int) (Route.getStartingPoint()[0] - (int) position - size[0] / 2.0),
					(int) (Route.getStartingPoint()[1] - size[1] / 2.0), size[0], size[1]);
			break;
		case 270:
			r = new Rectangle((int) (Route.getStartingPoint()[0] - size[1] / 2.0),
					(int) (Route.getStartingPoint()[1] + (int) position - size[0] / 2.0), size[1], size[0]);
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
}
