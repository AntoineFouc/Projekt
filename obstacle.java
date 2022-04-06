import java.awt.*;
import javax.swing.*;
import java.util.*;

public abstract class obstacle{

    protected int x;
    protected int y;
    protected Road road;
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
}
