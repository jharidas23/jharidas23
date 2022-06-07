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

public class Cheese extends JComponent {
	
	private Rectangle sqaure;
	
	private boolean dropped = false;
	private boolean donePlacing = false;
	
	private Cheese obj = this;
	  
	public Cheese(int x, int y, ArrayList<String> assembledBurger, ArrayList <JComponent> assembledObjs, AssemblePanel panel){
		
		setLocation(x,y);
		setSize(101, 61);
		
		sqaure = new Rectangle(0,0,80,30);
		
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if(!dropped) {
					Point p = MouseInfo.getPointerInfo().getLocation();
					int mouseX = p.x;
					int mouseY = p.y;
					
					setLocation(mouseX-75,mouseY-95);	
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
					assembledBurger.add("Cheese");
					assembledObjs.add(obj);
					donePlacing = true;
					
					//adjusting money
					BurgeriaMain.changeMoney(-0.5);
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
		
		g2.setColor(new Color(242,245,158));
		g2.fill(sqaure);
		
	}
	
	public String getName() {
		return("Cheese");
	}
}
