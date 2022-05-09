import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class BurgeriaMain extends JFrame{

		//making overall panel
		CardLayout cl = new CardLayout();
		JPanel BurgeriaMainPanel = new JPanel();
		BurgeriaMainPanel.setLayout(cl);
		
		//bounds for jframe
		setBounds(850,10,400,400);

		
		//making menu
		JMenuBar menubar = new JMenuBar();
		
		JMenuItem menItDashboard = new JMenuItem("Take Orders");
		JMenuItem menItCook = new JMenuItem("Cook");
		JMenuItem menItAssemble = new JMenuItem("Assemble");
		
		menubar.add(menItDashboard);
		menubar.add(menItCook);
		menubar.add(menItAssemble);
		
		add(menubar);
		setJMenuBar(menubar);
		add(BurgeriaMainPanel);
}
