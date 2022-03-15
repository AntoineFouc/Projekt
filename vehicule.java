public class Vehicule {
	private int[] position;		// x y
	private double speed;
	private int[] size;			// taille largeur puis longueur
	private Road road;

	
	
	public Vehicule(double s, Road r){
		speed = s;			// vitesse initiale
		road = r;
		// position initiale dépendant de la voie

	}

	public int[] getPosition(){
		return position;
	}

	public double getSpeed(){
		return speed;
	}
	
}
