import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Frame extends JFrame{

public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


	public Frame(){
		setTitle("K-Roof");
		setLayout(null);

		setSize(screenSize.width, (int) (0.9*screenSize.height));
		setVisible(true);
	}


}


