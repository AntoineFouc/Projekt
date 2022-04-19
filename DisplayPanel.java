import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
Regarder l'influence du pas de temps dt sur la vitesse de la simulation (theoriquement impact seulement la fluidite, pas la vitesses des vehicules)
*/

public class DisplayPanel extends JPanel implements ActionListener{

	private Timer timer;
	private int dt = 10;			// pas de tps
	private int time;				// compteur de temps
    private long realtime;
    private long tactuel;
    private long t0;
    private long tpause;
	private Frame frame;			// fenetre principale

	public DisplayPanel(Frame f){
		timer = new Timer(dt,this);
		time = 0;
        t0 = 0;
		frame = f;
	}

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(new ImageIcon("Images/map.png").getImage(), 0, 0, null);			// affichage du fond

		// dessine la voiture et la fait bouger une fois que le chrono a commence
        for(Vehicule c : frame.vehicules){
        	if(time!=0) c.move(dt);
        	c.draw(g);
        }
        for(obstacle o : frame.allObstacles){
            if(!o.equals(null)){
                o.draw(g);
            }
        }

	}

	public Timer getTimer(){
		return timer;
	}

	public void setTime(int t){
		time = t;
	}
    
	public int getDt(){
		return dt;
	}
   
    public long getTime(){
        return time;
    }
    

	// repaint et met a jour le tps tous les dt
	public void actionPerformed(ActionEvent e){
        if(e.getSource()==timer){
            time += dt;
            
            for(Road r : frame.routes){
                if(frame.howManyVehicles(r) == 0 || frame.newVehicle(r)){
                    // frame.vehicules.add(new Car(r,0.3,0.002));
                    addVehicle(r);
                }
	        }
            
            frame.interaction();           
            
            for(int i=0; i<frame.vehicules.size(); i++){
	        	frame.vehicules.get(i).move(dt);
	        	if(!frame.vehicules.get(i).isOnTheRoad()){
	        		frame.vehicules.remove(i);
	        	}
	        }
            
            for(obstacle o : frame.allObstacles){
                if(o instanceof feurouge){
                    ((feurouge)o).setTimer(((feurouge)o).getTimer()+1);
                    ((feurouge)o).update(time);
                }
            }
            repaint();
        }
	}
    
	public void addVehicle(Road r){
		if(Math.random()<0.9){
			Vehicule newCar = new Car(r, 0.2+Math.random()*frame.getRapidite()*0.001, 0.0015+Math.random()*frame.getAggressivite()*0.000005);
			frame.vehicules.add(newCar);
			newCar.setNextObstacle(frame.getObstaclesRoute(r.getOrientation()).get(1));
		}else{
			Truck newTruck = new Truck(r, 0.2+Math.random()*frame.getRapidite()*0.001, 0.0015+Math.random()*frame.getAggressivite()*0.000005);
			frame.vehicules.add(newTruck);
			newTruck.setNextObstacle(frame.getObstaclesRoute(r.getOrientation()).get(1));
		}
	}
}
