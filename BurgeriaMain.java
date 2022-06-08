import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BurgeriaMain extends JFrame{
	
	private static ArrayList <Orders> theOrders;
	private static double money;
	private static ArrayList<JComponent> completePatties;
	
	private static AssemblePanel assemblePanel;
	private static Cook cookPanel;
	private static IntroPanel introPanel;
	
	private static CardLayout cl;
	private static JPanel BurgeriaMainPanel;
	private static OrderPanel orderPanel;
	
	public BurgeriaMain() {
		//making overall panel
		cl = new CardLayout();
		BurgeriaMainPanel = new JPanel();
		BurgeriaMainPanel.setLayout(cl);
		setResizable(false);
		
		

		//bounds for jframe
		setBounds(25,25,1200,650);
		
		//initialize fields
		theOrders = new ArrayList<Orders>();
		money = 5;
		completePatties = new ArrayList<JComponent>();
		assemblePanel = new AssemblePanel(theOrders);
		cookPanel = new Cook(theOrders);
		introPanel = new IntroPanel(); 
		orderPanel = new OrderPanel(theOrders);


		//making menu
		JMenuBar menubar = new JMenuBar();

		JMenuItem menItOrder = new JMenuItem("                                              Take Orders");
		JMenuItem menItCook = new JMenuItem("                                                     Cook");
		JMenuItem menItAssemble = new JMenuItem("                                                   Assemble");

		//borders/colors
		menItOrder.setBorder(BorderFactory.createLineBorder(Color.black));
		menItCook.setBorder(BorderFactory.createLineBorder(Color.black));
		menItAssemble.setBorder(BorderFactory.createLineBorder(Color.black));
		menItOrder.setBackground(Color.GREEN);
		menItCook.setBackground(Color.ORANGE);
		menItAssemble.setBackground(Color.CYAN);

		menubar.add(menItOrder);
		menubar.add(menItCook);
		menubar.add(menItAssemble);

		add(menubar);
		setJMenuBar(menubar);
		add(BurgeriaMainPanel);

			
		//add each panel to main panel
		BurgeriaMainPanel.add(introPanel, "Intro Panel");
		cl.show(BurgeriaMainPanel, "Intro Panel");
		BurgeriaMainPanel.add(orderPanel, "Order Panel");
		BurgeriaMainPanel.add(assemblePanel, "Assemble Panel");
		BurgeriaMainPanel.add(cookPanel, "Cook Panel");	
						
		//action listeners

		menItOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(BurgeriaMainPanel, "Order Panel");
				//orderPanel.showTickets();
			}

		});

		menItCook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(BurgeriaMainPanel, "Cook Panel");
				//cookPanel.showTickets();
			}

		});

		menItAssemble.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(BurgeriaMainPanel, "Assemble Panel");
				assemblePanel.addButtons();
				assemblePanel.drawPatties(completePatties);
				assemblePanel.showTickets();
			}

		});
			
			
			
			
			
			
			
			
			
			
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args)
	{
		new BurgeriaMain();
	}
	
	public static ArrayList<Orders> getTheOrders(){
		return theOrders;
	}
	public static double getMoney() {
		return money;
	}
	public static int getNumOrders() {
		return theOrders.size();
	}
	public static void changeMoney(double amt) {
		money += amt;
	}
	public static void addCompletePatty(JComponent patty){
		completePatties.add(patty);
	}
	
	public static double getPrice(int i) {
		double price = 0.50 + 0.80; //the buns and condiments (all orders contain condiments) 
		if(theOrders.get(i).getCheese()) {
			price += 0.50; 
		}
		if(theOrders.get(i).getTomatoes()) {
			price += 0.25; 
		}
		if(theOrders.get(i).getPickles()) {
			price += 0.25; 
		}
		if(theOrders.get(i).getLettuce()) {
			price += 0.25; 
		}
		if(theOrders.get(i).getOnions()) {
			price += 0.25; 
		}
		price += theOrders.get(i).getPatties(); 
		return price; 
	}
	
	public static AssemblePanel getAssemblePanel() {
		return assemblePanel;
	}
	public static Cook getCookPanel() {
		return cookPanel;
	}
	
	public static void removeOrder(int index) {
		theOrders.remove(index);
	}
	
	public static void showOrderPanel() {
		cl.show(BurgeriaMainPanel, "Order Panel");
	}
	
	//this is for you to copy into your panel classes to make the tickets reappear each time
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
