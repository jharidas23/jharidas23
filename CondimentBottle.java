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

public class CondimentBottle extends JComponent{
	
	private String condiment;
	
	private Ellipse2D.Double top;
	private Ellipse2D.Double bottom;
	private Rectangle middle;
	private Ellipse2D.Double nozzleBase;
	private Rectangle nozzleRectangle;
	private Ellipse2D.Double nozzleTop;
	
	private boolean dropped = false;
	private boolean donePlacing = false;
	
	private CondimentBottle obj = this;
	  
	public CondimentBottle(int x, int y, String c,ArrayList<String> stackedItems, ArrayList <JComponent> assembledObjs, AssemblePanel panel){
		
		setLocation(x,y);
		setSize(40, 100);
		
		condiment = c;
		
		bottom = new Ellipse2D.Double(0,0,40,20);
		middle = new Rectangle(0,10,40,60);
		top = new Ellipse2D.Double(0,60,40,20);
		nozzleBase = new Ellipse2D.Double(16,67,8,4);
		nozzleRectangle = new Rectangle(16,70,8,15);
		nozzleTop = new Ellipse2D.Double(16,83,8,4);
	
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if(!dropped) {
					Point p = MouseInfo.getPointerInfo().getLocation();
					int mouseX = p.x;
					int mouseY = p.y;
					
					setLocation(mouseX-55,mouseY-110);
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
					stackedItems.add(condiment);
					donePlacing = true;
					
					//adjusting money
					BurgeriaMain.changeMoney(-0.5);
					panel.updateMoney();
					
					
					//create splat
					CondimentSplat splat = new CondimentSplat(obj.getX()+20, panel.getYValUnderneath(obj.getX()), condiment);
					panel.add(splat);
					
					//remove bottle from panel
					panel.remove(obj);
					panel.revalidate();
					panel.repaint();
					
					//add condimentSplat to assembledObjs
					assembledObjs.add(splat);
					
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
		
		Color light = null;
		Color dark = null;
		
		if(condiment.equals("Ketchup")) {
			light = new Color(255,0,0);
			dark = new Color(204,0,0);
			
		}
		else if(condiment.equals("Mustard")) {
			light = new Color(255,239,0);
			dark = new Color(232,216,0);
		}
		else if(condiment.equals("Mayo")) {
			light = new Color(249,249,249);
			dark = new Color(230,230,230);
		}
		else if(condiment.equals("Barbeque")) {
			light = new Color(115,52,24);
			dark = new Color(80,49,14);
		}
		

		g2.setColor(light);
		g2.fill(bottom);
		g2.fill(middle);
		g2.setColor(dark);
		g2.fill(top);
		g2.setColor(light);
		g2.fill(nozzleBase);
		g2.fill(nozzleRectangle);
		g2.setColor(dark);
		g2.fill(nozzleTop);
	}

}
