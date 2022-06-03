import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BurgeriaMain extends JFrame{
	
	private static ArrayList <String> theOrders;
	private ArrayList<Boolean> completedOrders;//booleans appear in same order as theOrders and is true if
	//order has been completed and sent out.
	private static double money;
	private static int numOrders;
	
	public BurgeriaMain() {
		//making overall panel
		CardLayout cl = new CardLayout();
		JPanel BurgeriaMainPanel = new JPanel();
		BurgeriaMainPanel.setLayout(cl);
		setResizable(false);

		//bounds for jframe
		setBounds(25,25,1200,650);
		
		//initialize fields
		theOrders = new ArrayList<String>();
		money = 5;
		numOrders = 0;


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
//			CookPanel cookPanel = new CookPanel(/*parameters*/);
//			BurgeriaMainPanel.add(cookPanel, "Cook Panel");
//			
			AssemblePanel assemblePanel = new AssemblePanel(theOrders);
			BurgeriaMainPanel.add(assemblePanel, "Assemble Panel");
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
	
	public static ArrayList<String> getTheOrders(){
		return theOrders;
	}
	public static double getMoney() {
		return money;
	}
	public static int getNumOrders() {
		return numOrders;
	}
	public static void incrementNumOrders() {
		numOrders++;
	}
	public static void printTheOrders() {
		for(String item: theOrders) {
			System.out.println(item);
		}
	}
	public static void changeMoney(double amt) {
		money += amt;
	}

		
}
