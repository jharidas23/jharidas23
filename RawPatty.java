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

public class RawPatty extends JComponent implements ActionListener{
	
	private Ellipse2D.Double top;
	private Ellipse2D.Double bottom;
	private Rectangle middle;
	
	private Timer t;
	private Cook cPanel; 
	private ArrayList<String> assembledItems; 
	private char screen; 
	
	private RawPatty obj = this; 
	
	private boolean dropped = false;
	private boolean donePlacing = false;

	
	  
	public RawPatty(int x, int y, ArrayList<String> stackedItems, char sc, Cook cookPanel, AssemblePanel aPanel){
				
		cPanel = cookPanel;
		
		assembledItems = stackedItems; 
		
		screen = sc;
		
		setLocation(x,y);
		setSize(101, 61);
		
		t = new Timer(5000,this); 
		
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
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("mouse released");
				if(screen == 'C')
				{
					System.out.println("cook screen"); 
					if((getX()>250 && getX()<950)&&(getY()>155 && getY()<703))
					{
						System.out.println("inside stove"); 
						t.start(); 
						System.out.println("timer started"); 
					}
					if((getX()>1000 && getX()<1150) && (getY()>510 && getY()<585))
					{
						screen = 'A'; 
						BurgeriaMain.getCompeltePatties().add(obj); 
						cPanel.remove(obj); 
						System.out.println("item has been removed"); 
						cPanel.revalidate(); 
						cPanel.repaint(); 
					}
				}
				if(screen == 'A')
				{
					if(!donePlacing) {
						dropped = true;
						assembledItems.add("Patty");
						donePlacing = true;
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
		
		g2.setColor(new Color(255,102,102));
		g2.fill(bottom);
		
		g2.setColor(new Color(255,102,102));
		g2.fill(middle);
		
		g2.setColor(new Color(255,153,153));
		g2.fill(top);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cPanel.add(new CookedPatty(getX(), getY(), cPanel.getOrder(), 'C', cPanel)); 
		ArrayList<JComponent> rp = cPanel.getArray(); 
		int index = rp.indexOf(this); 
		JComponent r = rp.set(index, new RawPatty(50,450,order,'C', cPanel)); 
		cPanel.remove(r);  
		t.stop();
		cPanel.repaint(); 
		cPanel.revalidate(); 
	}
	
	public String getName()
	{
		return "RawPatty"; 
	}
	
}
	
	
