import java.awt.Color;
import java.util.Random;

import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Robot;


public class Computer extends Robot implements Runnable{
	
	public boolean active;
	public boolean toggle;
	private Random r = new Random();
	
	IOManager io;
	
	public Computer(City arg0, int street, int avenue, Direction arg3, Color color, IOManager io) {
		super(arg0, street, avenue, arg3);
		setColor(color);
		this.io = io;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		
		while(io.active){

			// If not pause
			if(io.toggle){

				// Check for collision
				io.collide();
				
				// Move it
				moveRandomly();
				
				// Check for collision
				io.collide();
				
				// pick up item and set to game over if it's over it
				if(canPickThing()){
					pickThing();
					io.prizeStolen = true;
					io.gameOver = true;
				}
				
			}
			
			// I feel so sleepy!
			if(io.active){
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					
				}	
			}
			
			if(io.prizeAcquired)
				destroy();
			
			
		}

	}
	
	public void moveRandomly(){
		
		int foobar = r.nextInt(3) + 1;
		
		// Randomly make between 1 and 3 turns
		for(int i = 0 ; i < foobar ; i++){
			this.turnLeft();
		}
		
		// If there's a wall, turn left
		while(!this.frontIsClear()){
			this.turnLeft();
		}
		// Make a move
		this.move();
	}
		
	public void destroy() {
		
		try {
			breakRobot("");
		} catch (Exception e){
			
		}
	}
	
}
