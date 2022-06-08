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
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private Rectangle wireTop;
	private Rectangle wireMiddle; 
	private Rectangle wireBottom; 
	private ArrayList<JComponent> rp; 
	private ArrayList<String> order; 
	private Cook cookPanel = this; 
	private AssemblePanel assemblePanel; 
	private JLabel lblMoney; 
	private JLabel lblTrash; 
	private JLabel lblNext; 
	
	public Cook(ArrayList<Orders> orders)
	{ 
		
		setLayout(null); 
		
		assemblePanel = BurgeriaMain.getAssemblePanel(); 
		
		drawBackground(); 
		
		cookPanel = this; 
		
		rp = new ArrayList<JComponent>(); 
		
		rp.add(new RawPatty(50,550,order,BurgeriaMain.getAssemblePanel().getAssembledObjs(),'C', cookPanel, assemblePanel));
		rp.add(new RawPatty(50,520,order,BurgeriaMain.getAssemblePanel().getAssembledObjs(),'C', cookPanel, assemblePanel)); 
		rp.add(new RawPatty(50,490,order,BurgeriaMain.getAssemblePanel().getAssembledObjs(),'C', cookPanel, assemblePanel));
		rp.add(new RawPatty(50,460,order,BurgeriaMain.getAssemblePanel().getAssembledObjs(),'C', cookPanel, assemblePanel)); 
		
		for(int i = 0; i<rp.size(); i++)
		{
			add(rp.get(i)); 
		}
		
		lblTrash = new JLabel("Trash");
		lblTrash.setBounds(112,230,100,20);
		add(lblTrash);
		
		lblNext = new JLabel("Next");
		lblNext.setBounds(1062,490,100,20);
		add(lblNext);
		
		lblMoney = new JLabel("Balance: $"+BurgeriaMain.getMoney());
		lblMoney.setBounds(3,15,100,20);
		add(lblMoney);
	}
	
	public void drawBackground()
	{
		wall = new Rectangle (0,0,1200,150);
		counter = new Rectangle(0,150,1200,550); 
		stove = new Rectangle(250,155,700,548); 
		trash = new Ellipse2D.Double(50,155,150,75);
		next = new Ellipse2D.Double(1000,510,150,75);
		ticketCircle = new Ellipse2D.Double(990,-20,250,250); 
		wireTop = new Rectangle(0,10,1200,2);
		wireMiddle = new Rectangle(0,12,1200,2);
		wireBottom = new Rectangle(0,14,1200,3);
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
		
		g2.setColor(new Color(192,192,192));
		g2.fill(wireMiddle);
		g2.setColor(Color.BLACK);
		g2.fill(wireTop);
		g2.fill(wireBottom);

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
	public void updateMoney() {
		lblMoney.setText("Balance: $"+BurgeriaMain.getMoney());
	}
}
