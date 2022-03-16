import java.awt.*;

public class Car extends Vehicule{
    
	public Car(double s, Road r){
		super(s,r);
		size[0] = 40;				// taille des voitures : longueur
		size[1] = 20;				// largeur
		setPosition();
	}

	public void move(Graphics g, int dt){
		super.move(g, dt);
	}

	public void draw(Graphics g){
		super.draw(g);
	}

	// initialise la position de la voiture
	public void setPosition(){
		position[0] = road.getStartingPoint()[0];							// place le véhicule en abcisse (modifier cette methode pour generaliser	- size[0]
		position[1] = road.getStartingPoint()[1];				// a toutes les situations, ici que route horiz de gauche a droite	 - size[1]
	}

}
