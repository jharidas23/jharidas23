import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Onion extends JComponent{
	
	private Ellipse2D.Double ellipse1;
	private Ellipse2D.Double ellipse2;
	private Ellipse2D.Double ellipse3;
	private Ellipse2D.Double ellipse4;
	private Ellipse2D.Double ellipse5;
	
	private boolean dropped = false;
	private boolean donePlacing = false;
	
	private Onion obj = this;
	  
	public Onion(int x, int y, ArrayList<String> stackedItems, AssemblePanel panel){
		
		setLocation(x,y);
		setSize(101, 61);
		
		ellipse1 = new Ellipse2D.Double(0,0,90,40);
		ellipse2 = new Ellipse2D.Double(13,5,70,30);
		ellipse3 = new Ellipse2D.Double(25,10,40,15);
		ellipse4 = new Ellipse2D.Double(37,15,15,5);
	
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if(!dropped) {
					Point p = MouseInfo.getPointerInfo().getLocation();
					int mouseX = p.x;
					int mouseY = p.y;
					
					setLocation(mouseX-80,mouseY-95);
				}
				
				
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				panel.reorder(obj);
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				if(!donePlacing) {
					dropped = true;
					stackedItems.add("Onion");
					donePlacing = true;
					
					//adjusting money
					BurgeriaMain.changeMoney(-0.25);
					panel.updateMoney();
					
				}				
			
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		Color light = new Color(240,236,240);
		Color dark = new Color(223, 220,223);
		
		g2.setColor(light);
		g2.fill(ellipse1);
		
		g2.setColor(dark);
		g2.fill(ellipse2);
		
		g2.setColor(light);
		g2.fill(ellipse3);
		
		g2.setColor(dark);
		g2.fill(ellipse4);
		
	}
	
	public String getName() {
		return("Onion");
	}
	
}