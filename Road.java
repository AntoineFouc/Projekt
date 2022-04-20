/*
Remarques :
*/

public class Road{

	private int[] startingPoint = new int[2];
	private int[] endingPoint = new int[2];
    private int orientation;    // en degré : 0=horizontal de gauche à droite (compris entre 0 et 360)
    private int[] croisement = new int[2]; 

    public Road(int x1, int y1, int x2, int y2){            // Point 1 de depart 2 d arrivee
    	startingPoint[0] = x1;
    	startingPoint[1] = y1;
    	endingPoint[0] = x2;
    	endingPoint[1] = y2;

        // définition de l'orientation et des croisements 
        croisement[0]=370;
        croisement[1]=430;
        if(x2<=x1){
            orientation = 180 + (int) ( 180/3.14159*Math.atan((1.0*y2-y1)/(x2-x1)) ); // division avec des "double"
            croisement[0]=430;
            croisement[1]=370;
        }else{
            orientation = (int) ( 180/3.14159*Math.atan((1.0*y2-y1)/(x2-x1)) );
        }
        if(orientation<0) orientation += 360;
        
        if (y2<=y1){
            croisement[0]=430;
            croisement[1]=370;
        }
    }

    public boolean equals(Road r){
        return (startingPoint.equals(r.getStartingPoint()) && endingPoint.equals(r.getEndingPoint()));
    }

    // getters permettant d'obtenir les points de la route
    public int[] getStartingPoint(){
        return startingPoint;
    }

    public int[] getEndingPoint(){
        return endingPoint;
    }

    public int getOrientation(){
        return orientation;
    }
    public int[] getCroisement(){
        return croisement;
    }
}
