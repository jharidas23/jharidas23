import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class TopBun extends JComponent{

	private Ellipse2D.Double bun;
	private Ellipse2D.Double seed1;
	private Ellipse2D.Double seed2;
	private Ellipse2D.Double seed3;
	
	public TopBun(int x, int y) {
		setLocation(x,y);
		setSize(101, 100);
		
		bun = new Ellipse2D.Double(0,70,100,60);
		seed1 = new Ellipse2D.Double(20,40,10,5);
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = MouseInfo.getPointerInfo().getLocation();
				int mouseX = p.x;
				int mouseY = p.y;
				
				setLocation(mouseX-100,mouseY-140);
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(255,204,153));
		g2.fill(bun);
		
		g2.setColor(new Color(240,240,224));
		g2.rotate(45);
		g2.fill(seed1);
		
		
		
	}
}
