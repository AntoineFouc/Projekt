import java.awt.*;
import javax.swing.*;
import java.util.*;

public abstract class obstacle{

    protected int x;
    protected int y;
    protected Road road;
	protected double position;
    public ArrayList<obstacle> obstacles;
    
    public obstacle(int uneposx,int uneposy, Road route){
        x=uneposx;
        y=uneposy;
        road=route;
    }
    
    public void draw(Graphics g){
	}

	public void setObstacles(ArrayList<obstacle> o){
		obstacles = o;
	}
	public double getPosition(){
        position=(x-road.getStartingPoint()[0])*Math.cos(road.getOrientation()*Math.PI/180)+(road.getStartingPoint()[1]-y)*Math.sin(road.getOrientation()*Math.PI/180);
		return position;
	}
	public Road getRoad(){
		return road;
	}
}
