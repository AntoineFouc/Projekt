import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*
Remarques :
Penser à faire plutot un tableau de checkboxes, voire hashmap <JCheckBox><boolean>
Repenser la manière de créer les véhicules
Reflechir a permettre a chaque vehicule d acceder aux positions des autres afin de prevoir ensuite l'adaptation de sa vitesse en fonction de l'environnement
*/


public class Frame extends JFrame implements ActionListener, MouseListener, KeyListener{

	// Initialisation vehicules et routes (a mettre ailleurs plus tard)
	private Road road1 = new Road(800,375,0,375);
	private Road road2 = new Road(0,450,800,450);
	private Road road3 = new Road(375,0,375,800);
	private Road road4 = new Road(450,800,450,0);


	private Vehicule car1 = new Car(0.2,road1);
	private Vehicule car2 = new Car(0.35,road2);
	private Vehicule car3 = new Car(0.45,road3);
	private Vehicule car4 = new Car(0.15,road4);
	private Vehicule car5 = new Car(0.2, road1, 400);

	public ArrayList<Vehicule> vehicules = new ArrayList<Vehicule>();		// liste des vehicules PRESENTS (cochés)

	private DisplayPanel p1;
	private JButton start;
	private JButton pause;

	// case pour cocher la presence des voiture 1 a 4
	private JCheckBox cb1; private boolean cb1On=false;
	private JCheckBox cb2; private boolean cb2On=false;
	private JCheckBox cb3; private boolean cb3On=false;
	private JCheckBox cb4; private boolean cb4On=false;
	private JCheckBox cb5; private boolean cb5On=false;

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
		start = new JButton(new ImageIcon("start.png"));
		start.setBounds(10,740,135,50);
		start.setBackground(new Color(0,170,0));
		start.addActionListener(this);
		p2.add(start);

		// bouton pause
		pause = new JButton(new ImageIcon("pause.png"));
		pause.setBounds(155,740,135,50);
		pause.setBackground(new Color(250,20,20));
		p2.add(pause);
		pause.setEnabled(false); 				// desactive le bouton au debut

		// check boxes vehicules
		cb1 = new JCheckBox("Voiture 1");
		cb1.setBounds(20,10,100,25);
		cb1.addActionListener(this);
		p2.add(cb1);

		cb2 = new JCheckBox("Voiture 2");
		cb2.setBounds(20,45,100,25);
		cb2.addActionListener(this);
		p2.add(cb2);

		cb3 = new JCheckBox("Voiture 3");
		cb3.setBounds(20,80,100,25);
		cb3.addActionListener(this);
		p2.add(cb3);

		cb4 = new JCheckBox("Voiture 4");
		cb4.setBounds(20,115,100,25);
		cb4.addActionListener(this);
		p2.add(cb4);

		cb5 = new JCheckBox("Voiture 5");
		cb5.setBounds(20,150,100,25);
		cb5.addActionListener(this);
		p2.add(cb5);

	}

	public void actionPerformed(ActionEvent e){

		if(e.getSource() == start){
			start.removeActionListener(this);
			start.setEnabled(false);
			pause.addActionListener(this);
			pause.setEnabled(true);

			// ne permet pas d enlever ou rajouter des voitures une fois l animation lancee
			cb1.removeActionListener(this);
			cb2.removeActionListener(this);
			cb3.removeActionListener(this);
			cb4.removeActionListener(this);
			cb5.removeActionListener(this);

			cb1.setEnabled(false);
			cb2.setEnabled(false);
			cb3.setEnabled(false);
			cb4.setEnabled(false);
			cb5.setEnabled(false);

			p1.getTimer().start(); // commence le chrono dans p1 (DisplayPanel) lorsqu'on appuie sur le bouton
		}

		if(e.getSource() == pause){				// met en pause la simulation
			p1.getTimer().stop();
			pause.removeActionListener(this);
			pause.setEnabled(false);
			start.addActionListener(this);
			start.setEnabled(true);
		}


		if(e.getSource() == cb1){
			cb1On = !cb1On;
			if(cb1On) vehicules.add(car1);
			else vehicules.remove(car1);
			p1.repaint();
		}

		if(e.getSource() == cb2){
			cb2On = !cb2On;
			if(cb2On) vehicules.add(car2);
			else vehicules.remove(car2);
			p1.repaint();
		}

		if(e.getSource() == cb3){
			cb3On = !cb3On;
			if(cb3On) vehicules.add(car3);
			else vehicules.remove(car3);
			p1.repaint();
		}

		if(e.getSource() == cb4){
			cb4On = !cb4On;
			if(cb4On) vehicules.add(car4);
			else vehicules.remove(car4);
			p1.repaint();
		}

		if(e.getSource() == cb5){
			cb5On = !cb5On;
			if(cb5On) vehicules.add(car5);
			else vehicules.remove(car5);
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




