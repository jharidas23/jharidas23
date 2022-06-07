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

public class Tomato extends JComponent{
	
	private Ellipse2D.Double top;
	private Ellipse2D.Double bottom;
	private Rectangle middle;
	
	private boolean dropped = false;
	private boolean donePlacing = false;
	
	private Tomato obj = this;
	  
	public Tomato(int x, int y, ArrayList<String> stackedItems, ArrayList <JComponent> assembledObjs, AssemblePanel panel){
		
		setLocation(x,y);
		setSize(101, 61);
		
		bottom = new Ellipse2D.Double(0,12,100,15);
		middle = new Rectangle(0,9,100,12);
		top = new Ellipse2D.Double(0,0,100,20);
		
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if(!dropped) {
					Point p = MouseInfo.getPointerInfo().getLocation();
					int mouseX = p.x;
					int mouseY = p.y;
					
					setLocation(mouseX-90,mouseY-80);
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
					stackedItems.add("Tomato");
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
		
		g2.setColor(new Color(204,0,0));
		g2.fill(bottom);
		
		g2.setColor(new Color(204,0,0));
		g2.fill(middle);
		
		g2.setColor(new Color(255,0,0));
		g2.fill(top);
		
	}
	
	public String getName() {
		return("Tomato");
	}
	
	
}


