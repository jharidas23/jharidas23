import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BurgeriaMain extends JFrame{
	
	private static ArrayList <Orders> theOrders;
	private ArrayList<Boolean> completedOrders;//booleans appear in same order as theOrders and is true if
	//order has been completed and sent out.
	private static double money;
	private static int numOrders;
	private static ArrayList<JComponent> completePatties;
	
	public BurgeriaMain() {
		//making overall panel
		CardLayout cl = new CardLayout();
		JPanel BurgeriaMainPanel = new JPanel();
		BurgeriaMainPanel.setLayout(cl);
		setResizable(false);

		//bounds for jframe
		setBounds(25,25,1200,650);
		
		//initialize fields
		theOrders = new ArrayList<Orders>();
		money = 5;
		numOrders = 0;
		completePatties = new ArrayList<JComponent>();


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
			OrderPanel orderPanel = new OrderPanel(theOrders);
			BurgeriaMainPanel.add(orderPanel, "Order Panel");
//			
			AssemblePanel assemblePanel = new AssemblePanel(theOrders);
			BurgeriaMainPanel.add(assemblePanel, "Assemble Panel");
		
//			Cook cookPanel = new Cook(assemblePanel);
//			BurgeriaMainPanel.add(cookPanel, "Cook Panel");
//			
			
//			
			
			
		//action listeners

		menItOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(BurgeriaMainPanel, "Order Panel");
			}

		});

		menItCook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(BurgeriaMainPanel, "Cook Panel");
			}

		});

		menItAssemble.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(BurgeriaMainPanel, "Assemble Panel");
				//assemblePanel.add(comp)
				assemblePanel.addButtons();
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
	public static ArrayList<JComponent>getCompeltePatties(){
		return completePatties;
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
	

		
}
