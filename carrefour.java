import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class carrefour extends Obstacle implements Comparable<Obstacle> {
	
    private int x;
    private int y;
    
    public carrefour(int uneposx, int uneposy, Route uneroute){
        super(uneposx,uneposy,uneroute);
        number=8;
	}
    
    public int getx (){
        return x;
    }
    public int gety (){
        return y;
    }

}
