import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.*;


public class Frame extends JFrame implements ActionListener, MouseListener, KeyListener{

	final ImageIcon icon = new ImageIcon("map.png");
	private Timer timer;
	private int dt = 20;
	private int time;

	private JButton start;


	public Frame(){
		setTitle("K-Roof");
		setLayout(null);
		setSize(1100,800);
		// setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// Panneau affichage
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(0,0,800,800);

		JLabel map = new JLabel(new ImageIcon("map.png"));			// image du carrefour
		map.setBounds(0,0,800,800);
		p1.add(map);


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

		timer = new Timer(dt,this);
		time=0;




	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == timer){
			time += dt;
		}else if(e.getSource() == start){
			timer.start();
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




