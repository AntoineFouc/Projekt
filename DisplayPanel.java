import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
Regarder l'influence du pas de temps dt sur la vitesse de la simulation (impacte seulement la fluidite, pas la vitesses des vehicules)
*/

public class DisplayPanel extends JPanel implements ActionListener{

	private Timer timer;
	private int dt = 5;				// pas de tps
	private int time;				// compteur de temps
	private Frame frame;			// fenetre principale

	public DisplayPanel(Frame f){
		timer = new Timer(dt,this);
		time = 0;
		frame = f;
	}

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(new ImageIcon("Images/map.png").getImage(), 0, 0, null);			// affichage du fond

		// dessine la voiture et la fait bouger une fois que le chrono a commence
        for(Vehicule v : frame.vehicules){
        	if(time!=0){
        		v.adaptSpeed(dt);
        		v.move(dt);
        	}
        	v.draw(g);
        }

	}

	public Timer getTimer(){
		return timer;
	}

	public void setTime(int t){
		time = t;
	}

	// repaint et met a jour le tps tous les dt
	public void actionPerformed(ActionEvent e){
		time += dt;
		if(frame.isAnAccident()) System.out.println("ACCIDEEEEEEEEEEEEEENT");
		repaint();
	}
}
