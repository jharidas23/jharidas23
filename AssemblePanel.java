
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AssemblePanel extends JLayeredPane{

	private Rectangle wall;
	private Rectangle counter;
	private Ellipse2D.Double trash;
	private ArrayList<Rectangle> shelfTops;
	private ArrayList<Rectangle> shelfSides;
	private Rectangle wireTop;
	private Rectangle wireMiddle;
	private Rectangle wireBottom;
	private Ellipse2D.Double ticketCircle;
	
	private JLabel lblMoney;
	
	private ArrayList<String> assembledBurger;
	private ArrayList<JComponent> assembledObjs;
	private ArrayList<JButton> finishButtons;
	
	private AssemblePanel panel = this;
	private Cook cookPanel;
	
	private boolean burnt;
	private boolean raw;
	
	private double price;
	
	
	public AssemblePanel(ArrayList<Orders> orders){
		
		setLayout(null);
		
		drawBackground();
		
		assembledBurger = new ArrayList<String>();
		assembledObjs = new ArrayList<JComponent>();
		finishButtons = new ArrayList<JButton>();
		
		cookPanel = BurgeriaMain.getCookPanel();
		
		burnt = false;
		raw = false;
		
		price = 0;
		
		for(int i = 0; i<30; i++) {
			add(new TopBun(10,0, assembledBurger, assembledObjs, this));
			add(new Tomato(10,160, assembledBurger, assembledObjs,this));
			add(new Lettuce(7,213, assembledBurger, assembledObjs, this));
			add(new Cheese(20,455, assembledBurger, assembledObjs, this));
			add(new Onion(15,300, assembledBurger, assembledObjs, this));
			add(new Pickle(7,385, assembledBurger, assembledObjs, this));
			add(new BottomBun(10,530, assembledBurger,assembledObjs, this));
			add(new CondimentBottle(950,480,"Barbeque", assembledBurger, assembledObjs, this));
			add(new CondimentBottle(900,480,"Mayo", assembledBurger, assembledObjs, this));
			add(new CondimentBottle(850,480,"Mustard", assembledBurger, assembledObjs, this));
			add(new CondimentBottle(800,480,"Ketchup", assembledBurger, assembledObjs, this));
			
			
		}
		
		//prices
		JLabel lblTopBun = new JLabel("$0.40");
		lblTopBun.setBounds(3,107,100,20);
		add(lblTopBun);
		
		JLabel lblTomato = new JLabel("$0.25");
		lblTomato.setBounds(3,182,100,20);
		add(lblTomato);
		
		JLabel lblLettuce = new JLabel("$0.25");
		lblLettuce.setBounds(3,257,100,20);
		add(lblLettuce);
		
		JLabel lblOnion = new JLabel("$0.25");
		lblOnion.setBounds(3,332,100,20);
		add(lblOnion);
		
		JLabel lblPickle = new JLabel("$0.25");
		lblPickle.setBounds(3,407,100,20);
		add(lblPickle);
		
		JLabel lblCheese = new JLabel("$0.50");
		lblCheese.setBounds(3,482,100,20);
		add(lblCheese);
		
		JLabel lblBottomBun = new JLabel("$0.40");
		lblBottomBun.setBounds(3,557,120,20);
		add(lblBottomBun);
		
		//balance label
		lblMoney = new JLabel("Balance: $"+BurgeriaMain.getMoney());
		lblMoney.setBounds(3,15,100,20);
		add(lblMoney);
		

	}
	
	
	public void drawBackground() {
		wall = new Rectangle(0,0,1200,400);
		counter = new Rectangle(0,400,1200,200);
		trash = new Ellipse2D.Double(200,475,150,75);
		
		shelfTops = new ArrayList<Rectangle>();
		for(int i = 0; i<7; i++) {
			shelfTops.add(new Rectangle(0,75+75*i,125,50));
		}
		
		shelfSides = new ArrayList<Rectangle>();
		for(int i = 0; i<7; i++) {
			shelfSides.add(new Rectangle(0,125+75*i,125,5));
		}
		
		wireTop = new Rectangle(0,10,1200,2);
		wireMiddle = new Rectangle(0,12,1200,2);
		wireBottom = new Rectangle(0,14,1200,3);
		
		ticketCircle = new Ellipse2D.Double(1000,-80,250,250);
		
		
	}
	
	public void drawPatties(ArrayList<JComponent> completePatties) {
		int yLevel = 490;
		for(JComponent patty: completePatties) {
			if(patty.getName().equals("BurntPatty")) {
				add(new BurntPatty(226,yLevel, assembledBurger, assembledObjs, 'a', cookPanel, this));
				yLevel -= 50;
			}
			if(patty.getName().equals("CookedPatty")) {
				add(new BurntPatty(226,yLevel, assembledBurger, assembledObjs, 'a', cookPanel, this));
				yLevel -= 50;
			}
			if(patty.getName().equals("BurntPatty")) {
				add(new BurntPatty(226,yLevel, assembledBurger, assembledObjs, 'a', cookPanel, this));
				yLevel -= 50;
			}
		}
	}
	
	public void reorder(JComponent item) {
		moveToFront(item);
		repaint();
		revalidate();
		
	}
	
	public void addButtons() {
		System.out.println("addButtons method running");
		//count how many tickets have been made and make that many finish buttons
		
		
		int buttonYLocation = 200;
		System.out.println(BurgeriaMain.getNumOrders());
		for(int i = 1; i<=BurgeriaMain.getNumOrders(); i++) {
			JButton buttonFinish = new JButton("Finish Order "+i);
			buttonFinish.setBounds(1000,buttonYLocation, 150,40);
			buttonYLocation += 10+40;
			add(buttonFinish);
			
			//grading system
			buttonFinish.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//when they click, give burger grade in joption pane
					
//					for(int i = 1; i<assembledBurger.size()-1; i++) {
//						if(assembledBurger.get(i).equals(/*order item*/)) {
//							if(burnt || raw) {
//								JOptionPane.showMessageDialog(null, "Make sure you cook the patty properly!\nYou made $"+price*1.5, "Deposit", JOptionPane.PLAIN_MESSAGE);
//								BurgeriaMain.changeMoney(price*1.5);
//								updateMoney();
//							}
//							else {
//								JOptionPane.showMessageDialog(null, "Bravo!\nYou made $"+price*2, "Deposit", JOptionPane.PLAIN_MESSAGE);
//								BurgeriaMain.changeMoney(price*2);
//								updateMoney();
//							}
//						}
//						else {
//							if(burnt || raw) {
//								JOptionPane.showMessageDialog(null, "You should really brush up on those chef skills!\nYou made $"+price*0.5, "Deposit", JOptionPane.PLAIN_MESSAGE);
//								BurgeriaMain.changeMoney(price*0.5);
//								updateMoney();
//							}
//							else {
//								JOptionPane.showMessageDialog(null, "These are the wrong ingredients!\nYou made $"+price, "Deposit", JOptionPane.PLAIN_MESSAGE);
//								BurgeriaMain.changeMoney(price);
//								updateMoney();
//							}
//						}
//						
//					}
				}
				
			});
		}
		
	}
	
	public void updateMoney() {
		lblMoney.setText("Balance: $"+BurgeriaMain.getMoney());
	}
	
	public void addToPrice(double amt) {
		price+=amt;
	}
	
	public ArrayList<JComponent> getAssembledObjs(){
		return assembledObjs;
	}
	
	public int getYValUnderneath(int x) {
		int largestY = 400;
		for(JComponent item: assembledObjs) {
			if(Math.abs(item.getX()-x)<=100 || Math.abs(item.getX()-x)<=140) {
				if(getCenterY(item)<largestY) {
					largestY = getCenterY(item);
				}
			}
		}
		return largestY;
	}
	
	public int getCenterX(JComponent item) {
		int newX = 0;
		if(item.getName().equals("TopBun")) {
			newX = item.getX()+50;
		}
		if(item.getName().equals("Tomato")) {
			newX = item.getX()+50;
		}
		if(item.getName().equals("Lettuce")) {
			newX = item.getX()+45;
		}
		if(item.getName().equals("Onion")) {
			newX = item.getX()+45;
		}
		if(item.getName().equals("Pickle")) {
			newX = item.getX()+40;
		}
		if(item.getName().equals("Cheese")) {
			newX = item.getX()+40;
		}
		if(item.getName().equals("BottomBun")) {
			newX = item.getX()+50;
		}
		return newX;
	}
	
	public int getCenterY(JComponent item) {
		int newY = 0;
		if(item.getName().equals("TopBun")) {
			newY = item.getY()+85;
		}
		if(item.getName().equals("Tomato")) {
			newY = item.getY()+15;
		}
		if(item.getName().equals("Lettuce")) {
			newY = item.getY()+23;
		}
		if(item.getName().equals("Onion")) {
			newY = item.getY()+20;
		}
		if(item.getName().equals("Pickle")) {
			newY = item.getY()+13;
		}
		if(item.getName().equals("Cheese")) {
			newY = item.getY()+15;
		}
		if(item.getName().equals("BottomBun")) {
			newY = item.getY()+15;
		}
		return newY;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(0,102,204));//blue
		g2.fill(wall);
		
		g2.setColor(new Color(153,153,153));//grey
		g2.fill(counter);
		
		g2.setColor(Color.BLACK);
		g2.fill(trash);
		
		g2.setColor(new Color(102,102,102));//dark grey
		for(Rectangle top:shelfTops) {
			g2.fill(top);
		}
		
		g2.setColor(new Color(204,204,204));//light grey
		for(Rectangle side:shelfSides) {
			g2.fill(side);
		}
		
		g2.setColor(new Color(192,192,192));
		g2.fill(wireMiddle);
		g2.setColor(Color.BLACK);
		g2.fill(wireTop);
		g2.fill(wireBottom);
		
		g2.setColor(new Color(64,64,64));
		g2.fill(ticketCircle);
	}
	
	
	
}
