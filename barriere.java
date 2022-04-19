import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Object;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class barriere extends obstacle implements Comparable<obstacle>{
    
    protected Road voiebloq;
    
    public barriere(int uneposx,int uneposy,Road unevoiebloq){
        super(uneposx,uneposy,unevoiebloq);
    }
    
    public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		if(road.getOrientation() == 270){
                g2d.drawImage(new ImageIcon("Images/barriere.png").getImage(), 351-15, y-20, 62, 40, null);   //route 3
        } else if (road.getOrientation()==90){
                g2d.drawImage(new ImageIcon("Images/barriere.png").getImage(), 459-60, y-20, 62, 40, null);   //route 4
		}else {
            File file = new File("Images/barriere.png");
            BufferedImage pic=null;
            try {
                pic = ImageIO.read(file);
            } catch (IOException e) {
            }
			if(road.getOrientation() == 0){                           //route 2
                double rotationRequired = Math.toRadians (90);
                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, 20, 20);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                g2d.drawImage(op.filter(pic, null), x, 459-60, 40, 62, null);
                //rotation puis placement
			} else if (road.getOrientation() ==180){                    //route 1
                double rotationRequired = Math.toRadians (-90);
                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, 30, 30);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                g2d.drawImage(op.filter(pic, null), x, 351-10, 40, 62, null);
                //rotation puis placement
            }

		}
	}
  
}
