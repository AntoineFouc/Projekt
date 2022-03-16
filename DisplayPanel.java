import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DisplayPanel extends JPanel implements ActionListener{

	private Timer timer;
	private int dt = 20;
	private int time;
	private Frame frame;

	public DisplayPanel(Frame f){
		timer = new Timer(dt,this);
		time = 0;
		frame = f;
	}

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(new ImageIcon("map.png").getImage(), 0, 0, null);
        g.setColor(Color.red);
        frame.car1.draw(g);


        if(time!=0){
        	// g.drawRect(0,0,500,500);
        	frame.car1.move(g, dt);
        }

	}

	public Timer getTimer(){
		return timer;
	}

	public void actionPerformed(ActionEvent e){
		time += dt;
		repaint();


	}
}
