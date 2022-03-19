/*
Remarques :
Si l'on souhaite faire tourner les voitures, faire une autre classe avec 1 point intermédiaire à l'angle (ou autre manière)
Eventuellement prévoir l'orientation de la route d'une manière différente de isVertical + goPositive
*/

public class Road{

	private int[] startingPoint = new int[2];
	private int[] endingPoint = new int[2];
    public int direction;

    public Road(int x1, int y1, int x2, int y2){            // Point 1 de depart 2 d arrivee
    	startingPoint[0] = x1;
    	startingPoint[1] = y1;
    	endingPoint[0] = x2;
    	endingPoint[1] = y2;
        direction = goPositive();
    }

    // getters permettant d'obtenir les points de la route
    public int[] getStartingPoint(){
        return startingPoint;
    }

    public int[] getEndingPoint(){
        return endingPoint;
    }

    // orientation de la route
    public boolean isVertical(){
        if(startingPoint[0] == endingPoint[0]) return true;
        return false;
    }

    // retourne 1 si la route va dans le sens positif (des x ou y) et 2 si negatif
    public int goPositive(){
        if(isVertical()) return (int) Math.round( (getEndingPoint()[1] - getStartingPoint()[1]) / Math.sqrt(getEndingPoint()[1] * getEndingPoint()[1] + getStartingPoint()[1] * getStartingPoint()[1]) );
        else return (int) Math.round( (getEndingPoint()[0] - getStartingPoint()[0]) / Math.sqrt(getEndingPoint()[0] * getEndingPoint()[0] + getStartingPoint()[0] * getStartingPoint()[0]) );
    }
}
