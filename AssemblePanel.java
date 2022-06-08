
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AssemblePanel extends JLayeredPane{

	private Rectangle wall;
	private Rectangle counter;
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
	
	
	public AssemblePanel(ArrayList<Orders> orders){
		
		setLayout(null);
		
		drawBackground();
		
		assembledBurger = new ArrayList<String>();
		assembledObjs = new ArrayList<JComponent>();
		finishButtons = new ArrayList<JButton>();
		
		cookPanel = BurgeriaMain.getCookPanel();
		
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
				BurntPatty p = new BurntPatty(226,yLevel, assembledBurger, assembledObjs, 'A', cookPanel, this);
				add(p);
				reorder(p);
				yLevel -= 20;
			}
			if(patty.getName().equals("CookedPatty")) {
				CookedPatty p = new CookedPatty(226,yLevel, assembledBurger, assembledObjs, 'A', cookPanel, this);
				add(p);
				reorder(p);
				yLevel -= 20;
			}
			if(patty.getName().equals("RawPatty")) {
				RawPatty p = new RawPatty(226,yLevel, assembledBurger, assembledObjs, 'A', cookPanel, this);
				add(p);
				reorder(p);
				yLevel -= 20;
			}
		}
	}
	
	public void reorder(JComponent item) {
		moveToFront(item);
		repaint();
		revalidate();
		
	}
	
	public void enableButtons() {
		for(JButton button: finishButtons) {
			button.setEnabled(true);
		}
	}
	
	
	public void addButtons() {
		//count how many tickets have been made and make that many finish buttons
		
		int buttonYLocation = 200;
		for(int n = 1; n<=BurgeriaMain.getNumOrders(); n++) {
			JButton buttonFinish = new JButton("Finish Order "+n);
			buttonFinish.setBounds(1000,buttonYLocation, 150,40);
			buttonYLocation += 10+40;
			finishButtons.add(buttonFinish);
			add(buttonFinish);
			buttonFinish.setEnabled(false);
			
			final int ticketIndex = n-1;
			
			buttonFinish.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//when they click, give burger grade in joption pane
					int correctCount = 0;
					boolean incorrectCooking = false;
					
					ArrayList<String> strOrder = BurgeriaMain.getTheOrders().get(ticketIndex).getListIngredients();
				
					if(assembledBurger.size()>strOrder.size()) {
						JOptionPane optionPane = new JOptionPane("Too many ingredients!\nYou made $"+BurgeriaMain.getPrice(ticketIndex));
						JDialog d = optionPane.createDialog((JFrame) null, "Deposit");
						d.setLocation(500,300);
						d.setVisible(true);
						BurgeriaMain.changeMoney(BurgeriaMain.getPrice(ticketIndex));
						updateMoney();
					}
					
					else {
						
						for(int i = 0; i<assembledBurger.size(); i++) {
							
							//find correctCount
							if(assembledBurger.get(i).equals(strOrder.get(i))) {
								correctCount++;
							}
							
							else {
								if((assembledBurger.get(i).equals("RawPatty") || 
										assembledBurger.get(i).equals("CookedPatty") ||
										assembledBurger.get(i).equals("BurntPatty")) && 
										strOrder.get(i).equals("Patty")) {
									correctCount++;
								}
							}
							
							//find incorrectCooking
							if(assembledBurger.get(i).equals("RawPatty") || assembledBurger.get(i).equals("RawPatty")){
								incorrectCooking = true;
							}
							
							
						}
						
						//making jOptionPanes depending on correctCount and incorrectCooking
						if(correctCount == strOrder.size()) {
							if(incorrectCooking) {
								JOptionPane optionPane = new JOptionPane("Make sure you cook the patty properly!\nYou made $"+BurgeriaMain.getPrice(ticketIndex)*1.5);
								JDialog d = optionPane.createDialog((JFrame) null, "Deposit");
								d.setLocation(500,300);
								d.setVisible(true);
								BurgeriaMain.changeMoney(BurgeriaMain.getPrice(ticketIndex)*1.5);
								updateMoney();
							}
							else {
								JOptionPane optionPane = new JOptionPane("Bravo!\nYou made $"+BurgeriaMain.getPrice(ticketIndex)*2);
								JDialog d = optionPane.createDialog((JFrame) null, "Deposit");
								d.setLocation(500,300);
								d.setVisible(true);
								BurgeriaMain.changeMoney(BurgeriaMain.getPrice(ticketIndex)*2);
								updateMoney();
							}
						}
						else {
							if(incorrectCooking) {
								JOptionPane optionPane = new JOptionPane("You should really brush up on those chef skills!\\nYou made $"+BurgeriaMain.getPrice(ticketIndex)*0.5);
								JDialog d = optionPane.createDialog((JFrame) null, "Deposit");
								d.setLocation(500,300);
								d.setVisible(true);
								BurgeriaMain.changeMoney(BurgeriaMain.getPrice(ticketIndex)*0.5);
								updateMoney();
							}
							else {
								JOptionPane optionPane = new JOptionPane("These are the wrong ingredients!\nYou made $"+BurgeriaMain.getPrice(ticketIndex));
								JDialog d = optionPane.createDialog((JFrame) null, "Deposit");
								d.setLocation(500,300);
								d.setVisible(true);
								BurgeriaMain.changeMoney(BurgeriaMain.getPrice(ticketIndex));
								updateMoney();
							}
						}
						
					}
					
					//remove button and reset the rest to enabled false
					panel.remove(finishButtons.get(ticketIndex));
					finishButtons.remove(ticketIndex);
					panel.revalidate();
					panel.repaint();
					for(JButton button: finishButtons) {
						button.setEnabled(false);
					}
					
					//remove burger
					for(int i = assembledObjs.size()-1; i>=0; i--) {
						panel.remove(assembledObjs.get(i));
						assembledObjs.remove(assembledObjs.get(i));
						assembledBurger.remove(assembledBurger.get(i));//resetting string array list
					}
					panel.repaint();
					panel.revalidate();
					
					//reset all vars
					BurgeriaMain.removeOrder(ticketIndex);
					
				}
			});
		}
		
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
	
	public void updateMoney() {
		lblMoney.setText("Balance: $"+BurgeriaMain.getMoney());
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
