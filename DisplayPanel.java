import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DisplayPanel extends JPanel implements ActionListener{

	private Timer timer;
	private int dt = 20;			// pas de tps
	private int time;				// compteur de temps
	private Frame frame;			// fenetre principale

	public DisplayPanel(Frame f){
		timer = new Timer(dt,this);
		time = 0;
		frame = f;
	}

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(new ImageIcon("map.png").getImage(), 0, 0, null);			// affichage du fond

        g.setColor(Color.red);
        frame.car1.draw(g);										// dessine la voiture en sa position initiale


        if(time!=0){
        	frame.car1.move(g, dt);								// fait bouger la voiture
        }

	}

	public Timer getTimer(){
		return timer;
	}

	// repaint et met a jour le tps tous les dt
	public void actionPerformed(ActionEvent e){
		time += dt;
		repaint();


	}
}
