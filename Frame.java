import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Frame extends JFrame implements ActionListener, MouseListener, KeyListener{

	public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public int screenWidth = screenSize.width;
	public int screenHeight = (int) (0.9*screenSize.height);
	private int boxSize = 10;			// taille d une case en pixels
	private int p1Size = screenHeight - screenHeight%10;			// taille panneau affichage


	public Frame(){
		setTitle("K-Roof");
		setLayout(null);

		setSize(screenWidth, screenHeight);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Panneau affichage
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(20, 150, 20));
		p1.setBounds(0, 0, p1Size, p1Size);
		add(p1);

		// Panneau interface
		JPanel p2 = new JPanel();
		p2.setLayout(null);
		p2.setBackground(new Color(230, 230, 230));
		p2.setBounds(p1Size, 0, screenWidth-p1Size, p1Size);
		add(p2);

	}

	// Affiche la case d'une certaine couleur
	public void setBoxColor(Color c){

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




