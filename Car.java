import java.awt.*;

public class Car extends Vehicule{
    
	public Car(double s, Road r){
		super(s,r);
		size[0] = 40;				// taille des voitures
		size[1] = 20;
		setPosition();
	}

	public void move(Graphics g, int dt){
		super.move(g, dt);
	}

	public void draw(Graphics g){
		super.draw(g);
	}

	public void setPosition(){
		position[0] = road.getx1();							// place le véhicule en abcisse (modifier cette methode pour generaliser	- size[0]
		position[1] = road.gety1() - size[1];				// a toutes les situations, ici que route horiz de gauche a droite	 - size[1]
	}

}
