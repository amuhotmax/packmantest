import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import becker.robots.icons.Icon;


public class CustomIcon extends Icon {

	private String location = "";
	
	CustomIcon(String location){
		super();
		this.location = location;
	}
	
	@Override
	public void paintIcon(Graphics g){
		
	ImageIcon i = new ImageIcon(location); 

	g.drawImage(i.getImage(), 0,0, null, null);
	 
	}
	
}
