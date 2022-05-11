import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BurgeriaMain extends JFrame{
	
	public BurgeriaMain() {
		//making overall panel
		CardLayout cl = new CardLayout();
		JPanel BurgeriaMainPanel = new JPanel();
		BurgeriaMainPanel.setLayout(cl);
		setResizable(false);

		//bounds for jframe
		setBounds(50,50,1200,600);


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
//		OrderPanel orderPanel = new OrderPanel(/*parameters*/);
//		BurgeriaMainPanel.add(orderPanel, "Order Panel");
//			
//		CookPanel cookPanel = new CookPanel(/*parameters*/);
//		BurgeriaMainPanel.add(cookPanel, "Cook Panel");
//			
//		AssemblePanel assemblePanel = new AssemblePanel(/*parameters*/);
//		BurgeriaMainPanel.add(assemblePanel, "Assemble Panel");







		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args)
	{
		new BurgeriaMain();
	}

		
}
