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

public class TopBun extends JComponent{

	private Ellipse2D.Double bun;
	
	private boolean dropped = false;
	private boolean donePlacing = false;
	
	private TopBun obj = this;
	
	public TopBun(int x, int y, ArrayList<String> stackedItems, ArrayList <JComponent> assembledObjs, AssemblePanel panel) {
		setLocation(x,y);
		setSize(101, 100);
		
		bun = new Ellipse2D.Double(0,70,100,60);
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if(!dropped) {
					Point p = MouseInfo.getPointerInfo().getLocation();
					int mouseX = p.x;
					int mouseY = p.y;
					
					setLocation(mouseX-85,mouseY-160);
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
					stackedItems.add("TopBun");
					assembledObjs.add(obj);
					donePlacing = true;
					
					//adjusting money
					BurgeriaMain.changeMoney(-0.40);
					panel.updateMoney();
					
					//adjusting buttons
					panel.enableButtons();
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
		g2.setColor(new Color(255,204,153));
		g2.fill(bun);
		
		
		
	}
	
	public String getName() {
		return("TopBun");
	}
}
