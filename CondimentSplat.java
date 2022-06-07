import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.ArrayList;

import javax.swing.JComponent;

public class CondimentSplat extends JComponent{
	
	private Ellipse2D.Double center;
	private Ellipse2D.Double side1;
	private Ellipse2D.Double side2;
	private Ellipse2D.Double side3;
	private Ellipse2D.Double side4;
	private Ellipse2D.Double side5;
	
	
	private String condiment;
	
	private CondimentSplat obj = this;
	  
	public CondimentSplat(int x, int y, String c){
		
		setLocation(x,y);
		setSize(100, 60);
		
		condiment = c;
		
		center = new Ellipse2D.Double(0,0,80,40);
	
		
		
		
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		Color solid = null;
		
		if(condiment.equals("Ketchup")) {
			solid = new Color(255,0,0);
			
		}
		else if(condiment.equals("Mustard")) {
			solid = new Color(255,239,0);
		}
		else if(condiment.equals("Mayo")) {
			solid = new Color(249,249,249);
		}
		else if(condiment.equals("Barbeque")) {
			solid = new Color(115,52,24);
		}
		

		g2.setColor(solid);
		g2.fill(center);
	}

}
