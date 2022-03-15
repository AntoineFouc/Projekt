import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DisplayPanel extends JPanel implements ActionListener{

	private Timer timer;
	private int dt = 20;
	private int time;

	public DisplayPanel(){
		timer = new Timer(dt,this);
		time = 0;
	}

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(new ImageIcon("map.png").getImage(), 0, 0, null);
        g.setColor(Color.red);
        g.drawRect(355,50,20,40);
	}

	public Timer getTimer(){
		return timer;
	}

	public void actionPerformed(ActionEvent e){
		time += dt;
		repaint();
	}
}
