import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Frame extends JFrame implements ActionListener, MouseListener, KeyListener{

	// Initialisation vehicules et routes (a mettre ailleurs plus tard)
	private Road road1 = new Road(800,375,0,375);
	private Road road2 = new Road(0,450,800,450);
	private Road road3 = new Road(375,0,375,800);
	private Road road4 = new Road(450,800,450,0);
	private Vehicule car1 = new Car(0.13,road1);
	private Vehicule car2 = new Car(0.17,road2);
	private Vehicule car3 = new Car(0.15,road3);
	private Vehicule car4 = new Car(0.1,road4);

	public ArrayList<Vehicule> vehicules = new ArrayList<Vehicule>();		// liste des vehicules PRESENTS (cochés)

	private DisplayPanel p1;
	private JButton start;
	// case pour cocher la presence des voiture 1 a 4
	private JCheckBox cb1; private boolean cb1On=false;
	private JCheckBox cb2; private boolean cb2On=false;
	private JCheckBox cb3; private boolean cb3On=false;
	private JCheckBox cb4; private boolean cb4On=false;

	public Frame(){
		setTitle("K-Roof");
		setLayout(null);
		setSize(1100,840);
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
		start = new JButton("Commencer");
		start.setBounds(50,700,200,50);
		start.addActionListener(this);
		p2.add(start);

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
		cb4.setBounds(20,105,100,25);
		cb4.addActionListener(this);
		p2.add(cb4);

	}

	public void actionPerformed(ActionEvent e){

		if(e.getSource() == start){
			p1.getTimer().start(); // commence le chrono dans p1 (DisplayPanel) lorsqu'on appuie sur le bouton

			// ne permet pas d enlever ou rajouter des voitures une fois l animation lancee
			cb1.removeActionListener(this);
			cb2.removeActionListener(this);
			cb3.removeActionListener(this);
			cb4.removeActionListener(this);
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




