import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import becker.robots.Direction;

public class ControlPanel extends JPanel{

	IOManager io;
	
	public ControlPanel(final IOManager io){
        
		this.io = io;
		
        // Set the layout of the control panel.
        setLayout(new GridLayout(0, 5,15,15));
        
        // Create the buttons
        Button upBut = new Button("UP");
        Button leftBut = new Button("LEFT");
        Button pickBut = new Button("PICK");
        Button rightBut = new Button("RIGHT");
        Button downBut = new Button("DOWN");
        
        /*
         * Set the action listeners
         */
        upBut.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		if(io.toggle){
        			io.action = "move";
        			io.direction = Direction.NORTH;
        		}
        	}
        });
        
        leftBut.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		if(io.toggle){
        			io.action = "move";
        			io.direction = Direction.WEST;
        		}
        	}
        });
        
        rightBut.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		if(io.toggle){
        			io.action = "move";
        			io.direction = Direction.EAST;
        		}
        	}
        });
        
        downBut.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		if(io.toggle){
        			io.action = "move";
        			io.direction = Direction.SOUTH;
        		}
        	}
        });
        pickBut.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		if(io.toggle){
        			io.action = "pick";
        		}
        	}
        });
        
        // Add the buttons to the control panel view
        // Use empty panels to fill up the grid so we push the buttons to the correct position
        
        add(new JPanel());
        add(new JPanel());
        
        add(upBut);
        
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        
        add(leftBut);
        add(pickBut);
        
        add(rightBut);
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(downBut);
        
    }
	
}
