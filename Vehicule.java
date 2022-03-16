import java.awt.*;

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

	// Permet de changer la position du vehicule et de l'afficher au bon endroit en fonction du temps
	// A generaliser selon les routes verticales ou horizontales
	public void move(Graphics g, int dt){
		position[0] = position[0] + (int) (dt*speed);
		draw(g);
	}

	// dessine le vehicule
	// A generaliser
	public void draw(Graphics g){
		g.fillRect(position[0], position[1], size[0], size[1]);
	}
	
}
