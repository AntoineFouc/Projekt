import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
Regarder l'influence du pas de temps dt sur la vitesse de la simulation (theoriquement impact seulement la fluidite, pas la vitesses des vehicules)
 */

public class DisplayPanel extends JPanel implements ActionListener {

	private Timer timer;
	private int dt = 10; // pas de tps
	private int time; // compteur de temps
	private long realtime;
	private long tactuel;
	private long t0;
	private long tpause;
	private Frame frame; // fenetre principale

	public DisplayPanel(Frame f) {
		timer = new Timer(dt, this);
		time = 0;
		t0 = 0;
		frame = f;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(new ImageIcon("Images/map.png").getImage(), 0, 0, null); // affichage du fond

		// dessine la voiture et la fait bouger une fois que le chrono a commence
		for (Vehicule c : frame.vehicules) {
			if (time != 0)
				c.move(dt);
			c.draw(g);
		}
		for (Obstacle o : frame.obstacles) {
			if (!o.equals(null)) {
				o.draw(g);
			}
		}

	}

	// repaint et met a jour le tps tous les dt
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			
			if(time%4000 == 0){
				flux /= 4.0;
				frame.flux.setText("Flux (véhicules/s) : " + flux);
				flux = 0;
			}

			if(frame.isAnAccident()) {
				// frame.restart.doClick();
			}

			time += dt;

			for (Route r : frame.routes) {
				if (frame.howManyVehicles(r) == 0 || frame.newVehicle(r)) {
					if(Math.random()<(frame.getTrafic()*0.01 + 0.1)) addVehicle(r);
				}
			}
			// frame.classementEntites();
			frame.updateNextObstacle();
			frame.interaction();
			for (int i = 0; i < frame.vehicules.size(); i++) {
				frame.vehicules.get(i).move(dt);
				if (!frame.vehicules.get(i).isOnTheRoute()) {
					frame.vehicules.remove(i);
				}
			}

			for (Obstacle o : frame.obstacles) {
				if (o instanceof FeuRouge) {
					((FeuRouge) o).setTimer(((FeuRouge) o).getTimer() + 1);
					((FeuRouge) o).update(time);
				}
			}
			repaint();
		}
	}
	
	public void addVehicle(Route r) {
		if (Math.random() < 0.85) {
			Vehicule newVoiture = new Voiture(r, 0.13, 0.0015 + Math.random() * frame.getAggressivite() * 0.00005);
			frame.vehicules.add(newVoiture);
			frame.vehiculesParRoute.get((int) r.getOrientation() / 90).addFirst(newVoiture);
			if (frame.vehiculesParRoute.get(r.getOrientation() / 90).size() > 1) {
				newVoiture.setNextVehicule(frame.vehiculesParRoute.get(r.getOrientation() / 90).get(1));
			} else {
				newVoiture.setNextVehicule(null);
			}
			if (frame.sortObstaclesRoute(r.getOrientation()).size() != 0) {
				newVoiture.setNextObstacle(frame.sortObstaclesRoute(r.getOrientation()).get(0));
			}
		} else if (Math.random() < 0.86) {
            Camion newCamion = new Camion(r, 0.09, 0.0015 + Math.random() * frame.getAggressivite() * 0.00005);
           	frame.vehicules.add(newCamion);
       		frame.vehiculesParRoute.get((int) r.getOrientation() / 90).addFirst(newCamion);
       		if (frame.vehiculesParRoute.get(r.getOrientation() / 90).size() > 1) {
           		newCamion.setNextVehicule(frame.vehiculesParRoute.get(r.getOrientation() / 90).get(1));
           	} else {
       			newCamion.setNextVehicule(null);
      		}
      		if (frame.sortObstaclesRoute(r.getOrientation()).size() != 0) {
        		newCamion.setNextObstacle(frame.sortObstaclesRoute(r.getOrientation()).get(0));
          	}
        } else {
        	Moto newMoto = new Moto(r, 0.15, 0.0015 + Math.random() * frame.getAggressivite() * 0.00005);
        	frame.vehicules.add(newMoto);
            frame.vehiculesParRoute.get((int) r.getOrientation() / 90).addFirst(newMoto);
            if (frame.vehiculesParRoute.get(r.getOrientation() / 90).size() > 1) {
            		newMoto.setNextVehicule(frame.vehiculesParRoute.get(r.getOrientation() / 90).get(1));
            } else {
            		newMoto.setNextVehicule(null);
            }
            if (frame.sortObstaclesRoute(r.getOrientation()).size() != 0) {
            	newMoto.setNextObstacle(frame.sortObstaclesRoute(r.getOrientation()).get(0));
            }
		}
	}

	
	// getter & setters
	public Timer getTimer() {
		return timer;
	}

	public void setTime(int t) {
		time = t;
	}

	public int getDt() {
		return dt;
	}

	public long getTime() {
		return time;
	}

	public long getRealtime() {
		return realtime;
	}

	public void setRealtime(long realtime) {
		this.realtime = realtime;
	}

	public long getTactuel() {
		return tactuel;
	}

	public void setTactuel(long tactuel) {
		this.tactuel = tactuel;
	}

	public long getT0() {
		return t0;
	}

	public void setT0(long t0) {
		this.t0 = t0;
	}

	public long getTpause() {
		return tpause;
	}

	public void setTpause(long tpause) {
		this.tpause = tpause;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void setDt(int dt) {
		this.dt = dt;
	}

}
