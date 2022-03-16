import java.awt.*;

public abstract class Vehicule {
	protected int[] position = new int[2];		// x y (point en haut a gauche)
	protected double speed;		// en pixel par ms
	protected int[] size = new int[2];			// taille longueur puis largeur
	protected Road road;

	
	
	public Vehicule(double s, Road r){
		speed = s;			// vitesse initiale
		road = r;
	}

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

	// pas finie
	public void move(Graphics g, int dt){
		position[0] = position[0] + (int) (dt*speed);
		g.fillRect(position[0], position[1], size[0], size[1]);
	}

	public void draw(Graphics g){
		g.fillRect(position[0], position[1], size[0], size[1]);
	}
	
}
