import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

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
	
	private boolean dropped = false;
	private boolean donePlacing = false;
	
	private Lettuce obj = this;
	
	public Lettuce(int x, int y, ArrayList<String> stackedItems, ArrayList <JComponent> assembledObjs, AssemblePanel panel) {
		setLocation(x,y);
		setSize(115, 60);
		
		bigOval = new Ellipse2D.Double(10,10,90,35);
		leaf1 = new Ellipse2D.Double(15,30,30,20);
		leaf2 = new Ellipse2D.Double(45,33,30,20);
		leaf3 = new Ellipse2D.Double(76,19,10,30);
		leaf4 = new Ellipse2D.Double(87,30,10,15);
		leaf5 = new Ellipse2D.Double(80,10,30,20);
		leaf6 = new Ellipse2D.Double(60,0,15,30);
		leaf7 = new Ellipse2D.Double(15,5,15,30);
		
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if(!dropped) {
					Point p = MouseInfo.getPointerInfo().getLocation();
					int mouseX = p.x;
					int mouseY = p.y;
					
					setLocation(mouseX-100,mouseY-100);
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
					stackedItems.add("Lettuce");
					assembledObjs.add(obj);
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
	
	public String getName() {
		return("Lettuce");
	}
}
