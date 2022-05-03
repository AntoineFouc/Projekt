/*
Remarques :
 */
 
import java.util.LinkedList;

public class Route {

	private int[] startingPoint = new int[2];
	private int[] endingPoint = new int[2];
	private int orientation; // en degré : 0=horizontal de gauche à droite (compris entre 0 et 360)
	private int[] croisement = new int[2];
    private LinkedList<Vehicule> vehicules;

	public Route(int x1, int y1, int x2, int y2, LinkedList<Vehicule> liste) { // Point 1 de depart 2 d arrivee
		startingPoint[0] = x1;
		startingPoint[1] = y1;
		endingPoint[0] = x2;
		endingPoint[1] = y2;
        vehicules=liste;

		// définition de l'orientation
		croisement[0] = 370;
		croisement[1] = 430;
		if (x2 <= x1) {
			orientation = 180 + (int) (180 / 3.14159 * Math.atan((1.0 * y2 - y1) / (x2 - x1))); // division avec des
																								// "double"
		} else {
			orientation = (int) (180 / 3.14159 * Math.atan((1.0 * y2 - y1) / (x2 - x1)));
		}
		if (orientation < 0)
			orientation += 360;
	}

	public boolean equals(Route r) {
		return (startingPoint.equals(r.getStartingPoint()) && endingPoint.equals(r.getEndingPoint()));
	}

	// getters permettant d'obtenir les points de la route
	public int[] getStartingPoint() {
		return startingPoint;
	}

	public int[] getEndingPoint() {
		return endingPoint;
	}

	public int getOrientation() {
		return orientation;
	}

	public int[] getCroisement() {
		return croisement;
	}

	public void setCroisement(int[] croisement) {
		this.croisement = croisement;
	}
    
    public LinkedList<Vehicule> getVehicules (){
        return vehicules;
    }
    
    public int getSecond(){
        int val;
        if (getOrientation()%180==0){
            val=getStartingPoint()[1];
        } else {
            val=getStartingPoint()[0];
        }
        return val;
    }
    
}
