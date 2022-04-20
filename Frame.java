import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*
Remarques :
Faire l'initialisation des vamex en fonction du trafic

Faire une méthode dans vehicule qui permet de les reset
*/


public class Frame extends JFrame implements ActionListener, MouseListener, KeyListener{

	public Road[] routes = {new Road(800,370,0,370), new Road(0,430,800,430), new Road(370,0,370,800), new Road(430,800,430,0)};


	public ArrayList<Vehicule> allVehicules = new ArrayList<Vehicule>();
	public ArrayList<Vehicule> vehicules = new ArrayList<Vehicule>();		// liste des vehicules PRESENTS
    public ArrayList<obstacle> obstacles = new ArrayList<obstacle>();       // liste des obstacles PRESENTS

    boolean appuyé=false;       //état de l'action clic souris
    boolean select=false;       //variable qqchose selectionné
    String quoi;                //type d'obstacle à placer
    
	private DisplayPanel p1;
	private JButton start;
	private JButton pause;
	private JButton restart;
    private JButton trash;
    private JSlider traffic;
	private JSlider rapidite;
	private JSlider aggressivite;
    private JLabel labelTime;
    boolean démarré;
    private javax.swing.Timer timer;
    
    // bouton feu rouge
    private JButton boutonFeu;
    private JTextField valeurFeu;
    private int valfeu;
    private double time;
    
    //bouton limitation
    private JButton boutonLimite;
    private JTextField valeurLimite;
    private int vallim;
    
    //bouton barriere
    private JButton boutonBarriere;
    
    //bouton panneau stop
    private JButton panneauStop;
    
    //timer
    private JButton unite;
    boolean unit;
    
    private long startTime;
    private long elapsed;
    private boolean running;
    private long pauseTime;

	public Frame(){
		setTitle("K-Roof");
		setLayout(null);
		setSize(1115,840);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Panneau affichage
		p1 = new DisplayPanel(this);
		p1.setLayout(null);
        p1.setTime(0);
		p1.setBounds(0,0,800,800);
        p1.addMouseListener(this);
		add(p1);
        
        timer = new javax.swing.Timer(10,this);
        timer.addActionListener(this);
        timer.start();

		// Panneau interface
		JPanel p2 = new JPanel();
		p2.setLayout(null);
		p2.setBackground(new Color(230,230,230));
		p2.setBounds(800,0,300,800);
		add(p2);
        
		// bouton start
		start = new JButton(new ImageIcon("Images/start.png"));
		start.setBounds(10,680,135,50);
		start.setBackground(new Color(0,170,0));
		start.addActionListener(this);
		p2.add(start);
        start.setVisible(false);
        start.setVisible(true);

		// bouton pause
		pause = new JButton(new ImageIcon("Images/pause.png"));
		pause.setBounds(155,680,135,50);
		pause.setBackground(new Color(250,20,20));
		pause.addActionListener(this);
		p2.add(pause);
		pause.setEnabled(false); 				// desactive le bouton au debut
        pause.setVisible(false);
        pause.setVisible(true);

		// bouton restart
		restart = new JButton(new ImageIcon("Images/stop.png"));
		restart.setBounds(155,740,135,50);
        restart.setBackground(new Color(20,20,20));
		restart.addActionListener(this);
		p2.add(restart);
		restart.setEnabled(false); 				// desactive le bouton au debut
        restart.setVisible(false);
        restart.setVisible(true);
        
        // curseur trafic
		traffic = new JSlider(0,100,20);
		traffic.setBounds(10,10,280,50);
		traffic.setMinorTickSpacing(5);
		traffic.setMajorTickSpacing(20);
		traffic.setPaintTicks(true);
		traffic.setPaintLabels(true);
		p2.add(traffic);
        traffic.setVisible(false);
        traffic.setVisible(true);

		// curseur conduite rapide
		rapidite = new JSlider(0,100,20);
		rapidite.setBounds(10,70,280,50);
		rapidite.setMinorTickSpacing(5);
		rapidite.setMajorTickSpacing(20);
		rapidite.setPaintTicks(true);
		rapidite.setPaintLabels(true);
		p2.add(rapidite);
        rapidite.setVisible(false);
        rapidite.setVisible(true);

		// curseur aggressivite
		aggressivite = new JSlider(0,100,20);
		aggressivite.setBounds(10,130,280,50);
		aggressivite.setMinorTickSpacing(5);
		aggressivite.setMajorTickSpacing(20);
		aggressivite.setPaintTicks(true);
		aggressivite.setPaintLabels(true);
		p2.add(aggressivite);
        aggressivite.setVisible(false);
        aggressivite.setVisible(true);
        
        //timer
        labelTime = new JLabel();
        labelTime.setBounds(36,585,400,30);
        labelTime.setBackground(new Color(230,230,230));
        labelTime.setFont(new Font("Arial", Font.PLAIN, 20));
        labelTime.setText("Temps écoulé : "+p1.getTime()+" ms");
        p2.add(labelTime);
        labelTime.setVisible(false);
        labelTime.setVisible(true);
        
        //unité
        unite = new JButton(new ImageIcon("Images/seconde.png"));
 		unite.setBounds(10,590,20,20);
		unite.setBackground(new Color(0,0,255));
		unite.addActionListener(this);
		p2.add(unite);
		unite.setEnabled(true);
        unite.setVisible(false);
        unite.setVisible(true);
        unit=true;
        
        //bouton trash
        trash = new JButton(new ImageIcon("Images/corbeille.png"));
		trash.setBounds(155,620,135,50);
		trash.setBackground(new Color(191,191,191));
		trash.addActionListener(this);
		p2.add(trash);
		trash.setEnabled(true);
        trash.setVisible(false);
        trash.setVisible(true);
        
        //bouton feu rouge
		boutonFeu = new JButton(new ImageIcon("Images/feurouge.png"));
		boutonFeu.setBounds(20,295,60,135);
		boutonFeu.addMouseListener(this);
		p2.add(boutonFeu);
        boutonFeu.setVisible(false);
        boutonFeu.setVisible(true);        
        
        
        //valeur feu rouge
        valeurFeu = new JTextField("Intervalle");
        valeurFeu.setBounds(20,440,60,30);
        p2.add(valeurFeu);
        valeurFeu.setVisible(false);
        valeurFeu.setVisible(true);
        
        // bouton limites
		boutonLimite = new JButton(new ImageIcon("Images/limitation.png"));
		boutonLimite.setBounds(100,295,70,70);
		boutonLimite.addMouseListener(this);
		p2.add(boutonLimite);
        boutonLimite.setVisible(false);
        boutonLimite.setVisible(true);
        
        //valeur limites
        valeurLimite = new JTextField("Limite");
        valeurLimite.setBounds(100,440,70,30);
        p2.add(valeurLimite);
        valeurLimite.setVisible(false);
        valeurLimite.setVisible(true);
        
        //bouton barrière
        boutonBarriere = new JButton(new ImageIcon("Images/barriere.png"));
        boutonBarriere.setBounds(190,295,62,40);
		boutonBarriere.addMouseListener(this);
        p2.add(boutonBarriere);
        boutonBarriere.setVisible(false);
        boutonBarriere.setVisible(true);
        
        //panneau stop
        panneauStop = new JButton(new ImageIcon("Images/Panneau stop.png"));
        panneauStop.setBounds(190,350,61,61);
        panneauStop.addMouseListener(this);
        panneauStop.setVisible(true);
        p2.add(panneauStop);
        panneauStop.setVisible(false);
        panneauStop.setVisible(true);


		/*for(Road r : routes){
			for(int j=0; j<800; j+=50){
				allVehicules.add(new Car(0.3, r, j));
				vehicules.add(new Car(0.3, r, j));
			}
		}*/

        démarré=true;
	}


	public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==timer&&démarré){
            if(unit){
                labelTime.setText("Temps écoulé : "+(this.getElapsed()/1000)+" s");
            } else {
                labelTime.setText("Temps écoulé : "+(this.getElapsed())+" ms");
            }

        }
        
        if(e.getSource() == unite){
            if(unit){
                unite.setIcon(new ImageIcon("Images/milliseconde.png"));
                unit=!unit;
            } else {
                unite.setIcon(new ImageIcon("Images/seconde.png"));
                unit=!unit;
            }

		}
        
		if(e.getSource() == start){
            startTime = System.currentTimeMillis();
            running=true;
            démarré=true;
			start.setEnabled(false);
			pause.setEnabled(true);
			restart.setEnabled(true);
			//init.setEnabled(false);
			p1.getTimer().start(); // commence le chrono dans p1 (DisplayPanel) lorsqu'on appuie sur le bouton
            
		}

		if(e.getSource() == pause){
            // met en pause la simulation
            pauseTime=System.currentTimeMillis();
            elapsed=elapsed+System.currentTimeMillis()-startTime;
            running=false;
			p1.getTimer().stop();
			pause.setEnabled(false);
			start.setEnabled(true);
			//init.setEnabled(true);
		}

		if(e.getSource() == restart){
            elapsed=0;
            running = false; 
			pause.setEnabled(false);
			restart.setEnabled(false);
			//init.setEnabled(true);

			vehicules.clear();			// A changer

			p1.repaint();

			p1.getTimer().stop();
			p1.setTime(0);
            
            valeurFeu.setText("Intervalle");
            valeurLimite.setText("Limite");

			start.setEnabled(true);
		}

		//if(e.getSource() == init){
		//	int NumberOfVehicles = (int) traffic.getValue();
		//}
        
        if(e.getSource() == trash){
            ArrayList<obstacle> supprimer = new ArrayList<>();
            for(obstacle o : obstacles){
				supprimer.add(o);
			}
            obstacles.removeAll(supprimer);
            p1.repaint();
        }
	}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){
        if(obstacles.contains(e.getSource())){ 
            if(SwingUtilities.isRightMouseButton(e)){
                //Supprimer l'obstacle
                obstacles.remove(e.getSource());
                p1.repaint();
            }
        } else if (SwingUtilities.isLeftMouseButton(e)){
            if(e.getSource()==boutonFeu){
                appuyé=true;
                quoi="feurouge"; //coiffeur haha
            } else if(e.getSource()==boutonLimite){
                appuyé=true;
                quoi="limitation";
            } else if(e.getSource()==boutonBarriere){
                appuyé=true;
                quoi="barriere";
            } else if (e.getSource()==panneauStop){
                appuyé=true;
                quoi="stop";
            }
        }
        }

	public void mouseReleased(MouseEvent e){
        if(appuyé){
            ajouterElement(quoi);
        }
    }

	public void mouseClicked(MouseEvent e){
        if(e.getSource()==boutonFeu&&SwingUtilities.isLeftMouseButton(e)&&!select){ //feu rouge
            select=true;
            quoi="feurouge";
        } else if (e.getSource()==boutonLimite&&SwingUtilities.isLeftMouseButton(e)&&!select){ //limitation
            select=true;
            quoi="limitation";
        } else if (e.getSource()==boutonBarriere&&SwingUtilities.isLeftMouseButton(e)&&!select){ //barriere
            select=true;
            quoi="barriere";
        } else if (e.getSource()==panneauStop&&SwingUtilities.isLeftMouseButton(e)&&!select){ //panneau stop
            select=true;
            quoi="stop";
        } else if (select&&quoi.equals("feurouge")){
            if(e.getSource()==p1&&SwingUtilities.isLeftMouseButton(e)){
                ajouterElement("feurouge");
                select=false;
            } else {
                select=false;
            }
        } else if (select&&quoi.equals("limitation")){
            if(e.getSource()==p1&&SwingUtilities.isLeftMouseButton(e)){
                ajouterElement("limitation");
                select=false;
            } else {
                select=false;
            }
        } else if (select&&quoi.equals("barriere")){
            if(e.getSource()==p1&&SwingUtilities.isLeftMouseButton(e)){
                ajouterElement("barriere");
                select=false;
            } else {
                select=false;
            }
        } else if (select&&quoi.equals("stop")){
            if(e.getSource()==p1&&SwingUtilities.isLeftMouseButton(e)){
                ajouterElement("stop");
                select=false;
            } else {
                select=false;
            }
        }
    }

	public void keyPressed(KeyEvent e){}

	public void keyReleased(KeyEvent e){}

	public void keyTyped(KeyEvent e){}

    public void ajouterElement(String valeur){
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX()-this.getLocationOnScreen().x;
            int y = (int) b.getY()-this.getLocationOnScreen().y-38;
            if((x<=p1.getWidth())&&(y<=p1.getHeight())&&(x>=0)&&(y>=0)){ //Check si relaché dans la map
                int pos = getRoute(x,y,routes);
                if(pos!=-1){ //check si le point choisi est dans une route et pas un carrefour
                    //ajouter obstacle
                    if(valeur.equals("feurouge")){   //cas feu rouge
                        if(isInt(valeurFeu.getText())){
                            valfeu=Integer.valueOf(valeurFeu.getText());
                        } else {
                            valfeu=0;
                        }
                        feurouge feu=new feurouge(x,y,routes[pos],valfeu);
                        obstacles.add(feu);
                        p1.repaint();
                    } else if (valeur.equals("limitation")){   //cas limitation
                        if(isInt(valeurLimite.getText())){
                            vallim=Integer.valueOf(valeurLimite.getText());
                        } else {
                            vallim=0;
                        }
                        limitation limite=new limitation(x,y,routes[pos],vallim);
                        obstacles.add(limite);
                        p1.repaint();
                    } else if (valeur.equals("barriere")){   //cas barriere
                        barriere bar=new barriere(x,y,routes[pos]);
                        obstacles.add(bar);
                        p1.repaint();
                    } else if (valeur.equals("stop")){   //panneau stop
                        stop pstop=new stop(x,y,routes[pos]);
                        obstacles.add(pstop);
                        p1.repaint();
                    }
                }
            }
        }
        
    public boolean checkPos(int x,int y, ArrayList<Road> routes){
        boolean verif=true;
        return verif;
    }
    
    public int getRoute(int x, int y, Road[] routes){
        int rep=-1;
        int liste=0;
        for(int i=0;i<routes.length;i++){
            int x1=routes[i].getStartingPoint()[0];
            int x2=routes[i].getEndingPoint()[0];
            int y1=routes[i].getStartingPoint()[1];
            int y2=routes[i].getEndingPoint()[1];
            if((x>=Math.min(x1,x2)-29)&&(x<=Math.max(x1,x2)+29)&&y>=(Math.min(y1,y2)-29)&&(y<=Math.max(y1,y2)+29)){
                rep=i;
                liste++;
            }
        }
        if(liste>1){
            rep=-1;
        }
        return rep;
    }
    
        public static boolean isInt(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        int d = Integer.parseInt(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}

    public long getElapsed() {
        if (running) {
            return elapsed + System.currentTimeMillis() - startTime;
        } else {
            return elapsed;
        }
    }
    
    	public int getTraffic(){
		return traffic.getValue();
	}

	public int getRapidite(){
		return rapidite.getValue();
	}

	public int getAggressivite(){
		return aggressivite.getValue();
	}

	public int howManyVehicles(Road r){
		int i=0;
		for(Vehicule v : vehicules){
			if(v.getRoad().equals(r)) i++;
		}
		return i;
	}

	public boolean newVehicle(Road r){
		for(Vehicule v : vehicules){
			if(v.getRoad().equals(r) && v.getSafePosition()<getTraffic()) return false;
		}
		return true;
	}

	public boolean isAnAccident(){
		for(Vehicule v1 : vehicules){
			for(Vehicule v2 : vehicules){
				if(v1.getRectangle().intersects(v2.getRectangle()) && !v1.equals(v2)) return true;
			}
		}
		return false;
	}
	public void interaction(){

		for(Vehicule v1 : vehicules){
            for(obstacle o : obstacles){
                if (o instanceof limitation){
                    if(v1.getRoad()==o.getRoad() && v1.getSpeed()>o.getLimite()){
                        v1.deccelTo(o.getLimite());
                    }
                }
                if (o instanceof feurouge){
                    if(v1.getRoad()==o.getRoad() && v1.getPosition()<o.getPosition()){
                        if (o.getEtat()==1 || o.getEtat()==2){
                            v1.stopAt(o.getPosition()-v1.getSize()[0]);
                        }else{
                            v1.accel();
                        }
                        
                    }
                }
                if (o instanceof stop){
                    if(v1.getRoad()==o.getRoad()){
                        v1.stopAt(o.getPosition()-v1.getSize()[0]/2);
                    }
                }
                if (o instanceof barriere ){
                    if(v1.getRoad()==o.getRoad()){
                        if(o.getPosition()>o.getRoad().getCroisement()[1]){
                            v1.stopAt(o.getRoad().getCroisement()[1]-v1.getSize()[0]*2.0);
                        }else{
                            v1.stopAt(o.getPosition()-v1.getSize()[0]*2.0);
                        }
                    }
                }
            }
			for(Vehicule v2 : vehicules){
				if(!v1.equals(v2)){
                    if(v1.getRoad()==(v2.getRoad()) && v1.getPosition()<v2.getPosition() && v2.getSafePosition()<v1.getFront()){
                        v1.deccelTo(v2.getSpeed());
                    }                 
				}
            }
		}
	}
}




