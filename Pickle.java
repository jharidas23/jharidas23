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
import java.awt.geom.Ellipse2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Pickle extends JComponent{
	
	private Rectangle middleRectangle;
	private Rectangle bottomRectangle;
	
	private Ellipse2D.Double topLeft;
	private Ellipse2D.Double bottomLeft;
	private Rectangle middleLeft;
	
	private Ellipse2D.Double topRight;
	private Ellipse2D.Double bottomRight;
	private Rectangle middleRight;
	
	private boolean dropped = false;
	private boolean donePlacing = false;
	
	private Pickle obj = this;
	  
	public Pickle(int x, int y, ArrayList<String> stackedItems, AssemblePanel panel){
		
		setLocation(x,y);
		setSize(101, 61);
		
		middleRectangle = new Rectangle(30,0,50,20);
		bottomRectangle = new Rectangle(30,20,50,5);
		
		bottomLeft = new Ellipse2D.Double(15,15,30,10);
		middleLeft = new Rectangle(15,10,30,10);
		topLeft = new Ellipse2D.Double(15,0,30,20);
		
		bottomRight = new Ellipse2D.Double(65,15,30,10);
		middleRight = new Rectangle(65,10,30,10);
		topRight = new Ellipse2D.Double(65,0,30,20);
		
		
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = MouseInfo.getPointerInfo().getLocation();
				int mouseX = p.x;
				int mouseY = p.y;
				
				setLocation(mouseX-100,mouseY-75);
				
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
					stackedItems.add("Pickle");
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
		
		g2.setColor(new Color(51,102,0));
		g2.fill(bottomRectangle);
		g2.fill(bottomLeft);
		g2.fill(middleLeft);
		g2.fill(bottomRight);
		g2.fill(middleRight);
		
		g2.setColor(new Color(187,230,46));
		g2.fill(topRight);
		g2.fill(middleRectangle);
		g2.fill(topLeft);
		g2.fill(topRight);
		
	}
	
	
}


