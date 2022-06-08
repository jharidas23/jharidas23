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
import java.util.ArrayList;

import javax.swing.JComponent;

public class CondimentSplat extends JComponent{
	
	private Ellipse2D.Double center;
	
	private String condiment;
	
	private boolean dropped = false;
	private boolean donePlacing = false;
	
	private CondimentSplat obj = this;
	  
	public CondimentSplat(int x, int y, String c, ArrayList<String> stackedItems, ArrayList<JComponent> assembledObjs, AssemblePanel panel){
		
		setLocation(x,y);
		setSize(100, 60);
		
		condiment = c;
		
		center = new Ellipse2D.Double(0,0,80,40);
	
		
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if(!dropped) {
					Point p = MouseInfo.getPointerInfo().getLocation();
					int mouseX = p.x;
					int mouseY = p.y;
					
					setLocation(mouseX-85,mouseY-87);
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
					stackedItems.add("BottomBun");
					assembledObjs.add(obj);
					donePlacing = true;
					
					//adjusting money
					BurgeriaMain.changeMoney(-0.5);
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
		
		Color solid = null;
		
		if(condiment.equals("Ketchup")) {
			solid = new Color(255,0,0);
			
		}
		else if(condiment.equals("Mustard")) {
			solid = new Color(255,239,0);
		}
		else if(condiment.equals("Mayo")) {
			solid = new Color(249,249,249);
		}
		else if(condiment.equals("Barbeque")) {
			solid = new Color(115,52,24);
		}
		

		g2.setColor(solid);
		g2.fill(center);
	}

}
