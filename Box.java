import java.awt.*;
import javax.swing.*;





public class Box extends JLabel{


	public Box(Frame frame, int x, int y, ImageIcon p){
		super(p);
		this.setBounds(x*frame.getBoxSize(),y*frame.getBoxSize(),frame.getBoxSize(),frame.getBoxSize());
	}

}
