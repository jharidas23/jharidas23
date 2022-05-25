import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Lettuce extends JComponent{

	private Ellipse2D.Double bigOval;
	private Ellipse2D.Double leaf1;
	private Ellipse2D.Double leaf2;
	private Ellipse2D.Double leaf3;
	private Ellipse2D.Double leaf4;
	private Ellipse2D.Double leaf5;
	private Ellipse2D.Double leaf6;
	private Ellipse2D.Double leaf7;
	private Ellipse2D.Double leaf8;
	private Ellipse2D.Double leaf9;
	
	
	public Lettuce(int x, int y) {
		setLocation(x,y);
		setSize(115, 115);
		
		bigOval = new Ellipse2D.Double(10,10,100,45);
		leaf1 = new Ellipse2D.Double(15,40,30,20);
		leaf2 = new Ellipse2D.Double(45,50,30,20);
		leaf3 = new Ellipse2D.Double(80,30,10,30);
		leaf4 = new Ellipse2D.Double(95,35,10,15);
		leaf5 = new Ellipse2D.Double(80,10,30,20);
		leaf6 = new Ellipse2D.Double(60,0,15,30);
		leaf7 = new Ellipse2D.Double(15,5,15,30);
		
		
		
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = MouseInfo.getPointerInfo().getLocation();
				int mouseX = p.x;
				int mouseY = p.y;
				
				setLocation(mouseX-120,mouseY-95);
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,204,102));
		g2.fill(bigOval);
		g2.fill(leaf1);
		g2.fill(leaf2);
		g2.fill(leaf3);
		g2.fill(leaf4);
		g2.fill(leaf5);
		g2.fill(leaf6);
		g2.fill(leaf7);
		
		
		
	}
}
