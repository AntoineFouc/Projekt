import java.awt.*;
import javax.swing.*;

public class limitation extends obstacle{
    
    protected Road voiebloq;
    protected int valeur;
    
    public limitation(int uneposx,int uneposy,Road unevoiebloq,int unevaleur){
        super(uneposx,uneposy,unevoiebloq);
        valeur=unevaleur;
    }
    
    public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
        Font font = new Font("Marianne", Font.PLAIN, 24);
        g2d.setFont(font);
			if(road.getOrientation() == 270){
                g2d.drawImage(new ImageIcon("Images/limitation.png").getImage(), 351-38, y-20, 40, 40, null);   //route 3
                g2d.drawString(String.valueOf(valeur), 351-38+10, y-20+28);
            } else if(road.getOrientation() == 90){
                g2d.drawImage(new ImageIcon("Images/limitation.png").getImage(), 459-20, y-20, 40, 40, null);   //route 4
                g2d.drawString(String.valueOf(valeur), 459-20+10, y-20+28);
            } else if(road.getOrientation() == 0){
                g2d.drawImage(new ImageIcon("Images/limitation.png").getImage(), x-30, 459-18, 40, 40, null);    //route 2
                g2d.drawString(String.valueOf(valeur), x-30+8, 459-18+28);
			} else if (road.getOrientation() ==180){
                g2d.drawImage(new ImageIcon("Images/limitation.png").getImage(), x-30, 351-30, 40, 40, null);    //route 1
                g2d.drawString(String.valueOf(valeur), x-30+8, 351-30+28);
            }
	}
    
    public int getLimite(){
        int limite=valeur;
        return limite;
    }
    
}
