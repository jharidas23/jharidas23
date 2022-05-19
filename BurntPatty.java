import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class BurntPatty extends JComponent{
	
	private Ellipse2D.Double top;
	private Ellipse2D.Double bottom;
	private Rectangle middle;
	  
	public BurntPatty(int x, int y){
		
		setLocation(x,y);
		setSize(101, 61);
		
		bottom = new Ellipse2D.Double(0,15,100,15);
		middle = new Rectangle(0,10,100,15);
		top = new Ellipse2D.Double(0,0,100,20);
		
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(51,0,0));
		g2.fill(bottom);
		
		g2.setColor(new Color(51,0,0));
		g2.fill(middle);
		
		g2.setColor(new Color(71,7,7));
		g2.fill(top);
		
	}
	
}