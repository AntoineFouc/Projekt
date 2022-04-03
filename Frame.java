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

	private Road[] listOfRoads = {new Road(800,370,0,370), new Road(0,430,800,430), new Road(370,0,370,800), new Road(430,800,430,0)};
	
	private Vehicule car1 = new Car(0.1,listOfRoads[1],200);
	private Vehicule car2 = new Truck(0.2,listOfRoads[1],0);


	public ArrayList<Vehicule> allVehicules = new ArrayList<Vehicule>();
	public ArrayList<Vehicule> vehicules = new ArrayList<Vehicule>();		// liste des vehicules PRESENTS



	private DisplayPanel p1;
	private JButton start;
	private JButton pause;
	private JButton restart;
	private JButton init;
	private JSlider traffic;

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
		p1.setBounds(0,0,800,800);
		add(p1);

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

		// bouton pause
		pause = new JButton(new ImageIcon("Images/pause.png"));
		pause.setBounds(155,680,135,50);
		pause.setBackground(new Color(250,20,20));
		pause.addActionListener(this);
		p2.add(pause);
		pause.setEnabled(false); 				// desactive le bouton au debut

		// bouton restart
		restart = new JButton("Recommencer");
		restart.setBounds(10,740,280,50);
		restart.addActionListener(this);
		p2.add(restart);
		restart.setEnabled(false); 				// desactive le bouton au debut

		init = new JButton("Initialiser");
		init.setBounds(10,620,280,50);
		init.addActionListener(this);
		p2.add(init);

		traffic = new JSlider(0,64,20);
		traffic.setBounds(10,10,280,100);
		traffic.setMinorTickSpacing(4);
		traffic.setMajorTickSpacing(16);
		traffic.setPaintTicks(true);
		traffic.setPaintLabels(true);
		p2.add(traffic);


		/*for(Road r : listOfRoads){
			for(int j=0; j<800; j+=50){
				allVehicules.add(new Car(0.3, r, j));
			}
		}*/

		vehicules.add(car1);
		vehicules.add(car2);


	}



	// A completer (que sur la même route pour le moment)
	public boolean isAnAccident(){
		boolean accident=false;

		for(Vehicule v1 : vehicules){
			for(Vehicule v2 : vehicules){
				if(!v1.equals(v2)){
					if(v2.getPosition()-v2.getSize()[0]/2.0-v1.getPosition()-v1.getSize()[0]/2.0 == 0 && v1.getRoad().equals(v2.getRoad())) accident = true;
				}

			}
		}


		return accident;

	}







	public void actionPerformed(ActionEvent e){

		if(e.getSource() == start){
			start.setEnabled(false);
			pause.setEnabled(true);
			restart.setEnabled(true);
			init.setEnabled(false);

			for(Vehicule v : vehicules){
				v.setVehicules(vehicules);
			}

			p1.getTimer().start(); // commence le chrono dans p1 (DisplayPanel) lorsqu'on appuie sur le bouton
		}

		if(e.getSource() == pause){				// met en pause la simulation
			p1.getTimer().stop();
			pause.setEnabled(false);
			start.setEnabled(true);
			init.setEnabled(true);
		}

		if(e.getSource() == restart){
			pause.setEnabled(false);
			restart.setEnabled(false);
			init.setEnabled(true);

			for(Vehicule v : vehicules){
				v.resetPosition();
			}
			// vehicules.clear();			// A changer

			p1.repaint();

			p1.getTimer().stop();
			p1.setTime(0);


			start.setEnabled(true);
		}

		if(e.getSource() == init){
			int NumberOfVehicles = (int) traffic.getValue();
			for(Road r : listOfRoads){

			}


			p1.repaint();
		}
	}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void mouseClicked(MouseEvent e){}

	public void keyPressed(KeyEvent e){}

	public void keyReleased(KeyEvent e){}

	public void keyTyped(KeyEvent e){}

}




