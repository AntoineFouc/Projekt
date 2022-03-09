import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Frame extends JFrame implements ActionListener, MouseListener, KeyListener{

public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


	public Frame(){
		setTitle("K-Roof");
		setLayout(null);

		setSize(screenSize.width, (int) (0.9*screenSize.height));
		setVisible(true);
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




