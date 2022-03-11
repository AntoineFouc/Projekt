import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Frame extends JFrame implements ActionListener, MouseListener, KeyListener{

	public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	// taille de l ecran
	final ImageIcon icon = new ImageIcon("map.png");



	public Frame(){
		setTitle("K-Roof");
		setLayout(null);
		setExtendedState(this.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// Panneau affichage
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(0, 0, 800, getSize().height);

		JLabel map = new JLabel(new ImageIcon("map.png"));			// image du carrefour
		map.setBounds(0,0,800,800);
		p1.add(map);


    	add(p1);

		// Panneau interface
		JPanel p2 = new JPanel();
		p2.setLayout(null);
		p2.setBackground(new Color(60, 60, 230));
		p2.setBounds(800, 0, getSize().width-800, getSize().height);
		add(p2);

	}



	public void actionPerformed(ActionEvent e){}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void mouseClicked(MouseEvent e){}

	public void keyPressed(KeyEvent e){}

	public void keyReleased(KeyEvent e){}

	public void keyTyped(KeyEvent e){}

}




