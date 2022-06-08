import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Cook extends JPanel 
{
	private Rectangle wall; 
	private Rectangle counter; 
	private Rectangle stove; 
	private Ellipse2D.Double ticketCircle; 
	private Ellipse2D.Double trash; 
	private Ellipse2D.Double next; 
	private ArrayList<JComponent> rp; 
	private ArrayList<String> order; 
	private Cook cookPanel = this; 
	private AssemblePanel assemblePanel;
	
	public Cook(ArrayList<Orders> orders)
	{ 
		
		setLayout(null); 
		
		assemblePanel = BurgeriaMain.getAssemblePanel(); 
		
		drawBackground(); 
		
		cookPanel = this; 
		
		rp = new ArrayList<JComponent>(); 
		
		rp.add(new RawPatty(50,550,order, BurgeriaMain.getAssemblePanel().getAssembledObjs(), 'C', cookPanel, assemblePanel));
		rp.add(new RawPatty(50,520,order,BurgeriaMain.getAssemblePanel().getAssembledObjs(),'C', cookPanel, assemblePanel)); 
		rp.add(new RawPatty(50,490,order,BurgeriaMain.getAssemblePanel().getAssembledObjs(), 'C', cookPanel, assemblePanel));
		rp.add(new RawPatty(50,460,order,BurgeriaMain.getAssemblePanel().getAssembledObjs(), 'C', cookPanel, assemblePanel)); 
		
		for(int i = 0; i<rp.size(); i++)
		{
			add(rp.get(i)); 
		}
	}
	
	public static void main(String[] args)
	{
		JFrame myFrame = new JFrame ("test"); 
		myFrame.setBounds(0,0,1200,650);
		
		ArrayList<Orders> o = new ArrayList<Orders>(); 
		
		
		JPanel panel = new Cook(o); 
		myFrame.add(panel); 
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}
	
	public void drawBackground()
	{
		wall = new Rectangle (0,0,1200,150);
		counter = new Rectangle(0,150,1200,550); 
		stove = new Rectangle(250,155,700,548); 
		trash = new Ellipse2D.Double(50,155,150,75);
		next = new Ellipse2D.Double(1000,510,150,75);
		ticketCircle = new Ellipse2D.Double(990,-20,250,250); 
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g; 
		
		g2.setColor(new Color(219,120,40));
		g2.fill(wall);
		
		g2.setColor(new Color(117,114,114)); 
		g2.fill(counter); 
		
		g2.setColor(new Color(234,234,234));
		g2.fill(stove);
		
		g2.setColor(new Color(57,54,54)); 
		g2.fill(trash);
		
		g2.setColor(new Color(57,54,54));
		g2.fill(next);
		
		g2.setColor(new Color(57,54,54));
		g2.fill(ticketCircle);
	}
	
	public Cook getPanel()
	{
		return cookPanel; 
	}
	
	public ArrayList<String> getOrder()
	{
		return order; 
	}
	
	public ArrayList<JComponent> getArray()
	{
		return rp; 
	}
	
	public void showTickets() {
		for(int i = 0; i<BurgeriaMain.getTheOrders().size(); i++) {
			ArrayList <String> ingredients = BurgeriaMain.getTheOrders().get(i).getListIngredientsForward();
			String combined = BurgeriaMain.getTheOrders().get(i).getTicketNumber()+"\n";
			for(String item: ingredients) {
				combined+=item+"\n";
			}

			JOptionPane optionPane = new JOptionPane(combined+"Cost to Make: $"+BurgeriaMain.getTheOrders().get(i).getPrice());
			JDialog d = optionPane.createDialog((JFrame) null, "Order");
			d.setLocation(700,100);
			d.setVisible(true);
		}
	}
}
