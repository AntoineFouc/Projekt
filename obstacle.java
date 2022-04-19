import java.awt.*;
import javax.swing.*;
import java.util.*;

public abstract class obstacle implements Comparable{

    protected int x;
    protected int y;
    protected Road road;
    protected double position;
    public ArrayList<obstacle> obstacles;
    
    public obstacle(int uneposx,int uneposy, Road route){
        x=uneposx;
        y=uneposy;
        road=route;
        position=position=(x-road.getStartingPoint()[0])*Math.cos(road.getOrientation()*Math.PI/180)+(road.getStartingPoint()[1]-y)*Math.sin(road.getOrientation()*Math.PI/180);
    }
    
    public void draw(Graphics g){
    }
    public void setObstacles(ArrayList<obstacle> o){
	obstacles = o;
    }
    public double getPosition(){
	return position;
    }
    
    public Road getRoad(){
	return road;
    }
    
    public int compareTo(obstacle obs){
        int res;
        if(this.getPosition()>obs.getPosition()){
            res=1;
        } else if (this.getPosition()==obs.getPosition()){
            res=0;
        } else {
            res=-1;
        }
        return res;
    }
	
}
