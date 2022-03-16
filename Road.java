

public class Road{

	private int[] startingPoint = new int[2];
	private int[] endingPoint = new int[2];

    public Road(int x1, int y1, int x2, int y2){
    	startingPoint[0] = x1;
    	startingPoint[1] = y1;
    	endingPoint[0] = x2;
    	endingPoint[1] = y2;
    }

    public int getx1(){
    	return startingPoint[0];
    }

    public int gety1(){
    	return startingPoint[1];
    }

    public int getx2(){
    	return endingPoint[0];
    }

    public int gety2(){
    	return endingPoint[1];
    }

}
