import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
Regarder l'influence du pas de temps dt sur la vitesse de la simulation (theoriquement impact seulement la fluidite, pas la vitesses des vehicules)
*/

public class DisplayPanel extends JPanel implements ActionListener{

	private Timer timer;
	private int dt = 10;			// pas de tps
	protected int time;				// compteur de temps
	private Frame frame;			// fenetre principale

	public DisplayPanel(Frame f){
		timer = new Timer(dt,this);
		time = 0;
		frame = f;
	}

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(new ImageIcon("map.png").getImage(), 0, 0, null);			// affichage du fond

		// dessine la voiture et la fait bouger une fois que le chrono a commence
        for(Vehicule c : frame.vehicules){
        	if(time!=0) c.move(dt);
        	c.draw(g);
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
