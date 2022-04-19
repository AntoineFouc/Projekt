import java.awt.*;
import javax.swing.*;

public class stop extends obstacle implements Comparable<obstacle>{
    
    protected Road voiebloq;
    
    public stop(int uneposx,int uneposy,Road unevoiebloq){
        super(uneposx,uneposy,unevoiebloq);
        voiebloq=unevoiebloq;
    }
    
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        if(road.getOrientation() == 270) g2d.drawImage(new ImageIcon("Images/panneau stop.png").getImage(), 351-40, y-30, 50, 50, null);
			else if (road.getOrientation()==90){ g2d.drawImage(new ImageIcon("Images/panneau stop.png").getImage(), 459-20, y-30, 50, 50, null);


		}else if(road.getOrientation() == 0) g2d.drawImage(new ImageIcon("Images/panneau stop.png").getImage(), x-30, 459-20, 50, 50, null);
			else if (road.getOrientation() ==180){
                g2d.drawImage(new ImageIcon("Images/panneau stop.png").getImage(), x-30, 351-40, 50, 50, null);
}
    }
}
 
