import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.Timer;

public class CookedPatty extends JComponent implements ActionListener{
	

	private Ellipse2D.Double top;
	private Ellipse2D.Double bottom;
	private Rectangle middle;
	
	private Timer t;
	private Cook cPanel; 
	private ArrayList<String> assembledItems; 
	private ArrayList<JComponent> assembledObjs;
	private char screen; 
	private AssemblePanel aPanel; 
	
	private CookedPatty obj = this; 
	
	private boolean dropped = false;
	private boolean donePlacing = false;
	
	public CookedPatty(int x, int y, ArrayList<String> stackedItems, ArrayList<JComponent> assembledObjs, char sc, Cook cookPanel, AssemblePanel assemblePanel){
		
		cPanel = cookPanel;
		
		assembledItems = stackedItems; 
		this.assembledObjs = assembledObjs;
		
		screen = sc; 
		
		aPanel = assemblePanel; 
		
		setLocation(x,y);
		setSize(101, 61);
		
		t = new Timer(5000,this); 
		
		t.start(); 
		
		bottom = new Ellipse2D.Double(0,15,100,15);
		middle = new Rectangle(0,10,100,15);
		top = new Ellipse2D.Double(0,0,100,20);
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				aPanel.reorder(obj);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("mouse released");
				if(screen == 'C')
				{
					if((getX()>1000 && getX()<1150) && (getY()>510 && getY()<585))
					{
						//BurgeriaMain.getCompletePatties().add(obj); 
						BurgeriaMain.addCompletePatty(obj); 
						cPanel.remove(obj); 
						t.stop(); 
						System.out.println("item has been removed"); 
						screen = 'A'; 
						cPanel.revalidate(); 
						cPanel.repaint();
					}
					if((getX()>50 && getX()<200) && (getY()>155 && getY()<230)) 
					{
						t.stop(); 
						ArrayList<JComponent> rp = cPanel.getArray(); 
						int index = rp.indexOf(obj);
						int y = 0; 
						if(index == 0)
							y = 550; 
						else if(index == 1)
							y = 520; 
						else if(index == 2)
							y = 490; 
						else if(index == 3)
							y = 460; 
						JComponent r = rp.set(index, new RawPatty(50,y,cPanel.getOrder(), assembledObjs,'C', cPanel, aPanel)); 
						cPanel.remove(r);  
						cPanel.add(rp.get(index));   
						cPanel.repaint(); 
						cPanel.revalidate();
						
					}
				}
				if(screen == 'A')
				{
					if(!donePlacing) {
						dropped = true;
						assembledItems.add("Patty");
						assembledObjs.add(obj);
						donePlacing = true;
						
						//adjusting buttons
						assemblePanel.enableButtons();
				}
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
		
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if(!dropped && screen == 'A') {
					Point p = MouseInfo.getPointerInfo().getLocation();
					int mouseX = p.x;
					int mouseY = p.y;
					
					setLocation(mouseX-100,mouseY-75);
				}
				if(screen == 'C') {
					Point p = MouseInfo.getPointerInfo().getLocation();
					int mouseX = p.x;
					int mouseY = p.y;
					
					setLocation(mouseX-100,mouseY-75);
				}
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(102,51,0));
		g2.fill(bottom);
		
		g2.setColor(new Color(102,51,0));
		g2.fill(middle);
		
		g2.setColor(new Color(153,76,0));
		g2.fill(top);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ArrayList<JComponent> rp = cPanel.getArray(); 
		int index = rp.indexOf(obj);
		JComponent r = rp.set(index, new BurntPatty(getX(), getY(), cPanel.getOrder(), assembledObjs, 'C', cPanel, aPanel)); 
		cPanel.remove(r); 
		t.stop();
		cPanel.add(rp.get(index)); 
		cPanel.repaint(); 
		cPanel.revalidate(); 
	}
	
	public String getName(){
		return "CookedPatty";
	}

}
