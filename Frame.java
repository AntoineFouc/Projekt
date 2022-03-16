import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Frame extends JFrame implements ActionListener, MouseListener, KeyListener{

	// Initialisation vehicules et routes (a mettre ailleurs plus tard)
	public Road road1 = new Road(0,375,800,375);
	public Car car1 = new Car(0.3,road1);

	private DisplayPanel p1;
	private JButton start;


	public Frame(){
		setTitle("K-Roof");
		setLayout(null);
		setSize(1100,800);
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

		start = new JButton("Commencer");
		start.setBounds(50,700,200,50);
		start.addActionListener(this);
		p2.add(start);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == start) p1.getTimer().start(); // commence le chrono dans p1 (DisplayPanel) lorsqu'on appuie sur le bouton
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




