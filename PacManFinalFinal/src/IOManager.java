import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Thing;


// This is from where the application starts 
public class IOManager {

	public Random r = new Random();
	
	public double speed;
	
	public boolean active = false;
	public boolean restart = false;
	public boolean gameOver = false;
	public boolean toggle = false; // for pausing
	public boolean collided = false; // when robots are in the same intersection
	public boolean prizeStolen = false;
	public boolean prizeAcquired = false;
	
	// Store human interaction data
	public String action = ""; // can be "pick" or "move". If it's "move", we make use of direction
	public Direction direction;	
	
	private PacManTown theCity;
	private CityFrame cityFrame;
	
	public Computer computer;
	public Human human;
	
	public Thread roThread;
	public Thread huThread;
	
	public IOManager(double speed){
		// Set the speed multiplier of the computer
		this.speed = speed;
		
		// Have to use this to not show the default city frame
		City.showFrame(false);
		
		// initialize the city
		theCity = new PacManTown(11);

		// Add thing with custom icon on random position between 1-10
		new Thing(theCity, r.nextInt(9)+1, r.nextInt(9)+1,Direction.NORTH, true, new CustomIcon("pic/cheese.png"));
	
		// initialize the robot and human robots with random positions between 1 and 10. Also set the colors and send in the IOManager
		computer = new Computer(theCity, r.nextInt(9)+1, r.nextInt(9)+1, Direction.EAST, Color.red, this);
		human = new Human(theCity, r.nextInt(9)+1, r.nextInt(9)+1, Direction.EAST, Color.green, this);
		
		// Change icons of the robots
		computer.setIcon(new CustomIcon("pic/tom.png"));
		human.setIcon(new CustomIcon("pic/jerry.png"));
		
		// Set speed of robot
		computer.setSpeed(computer.getSpeed() * speed);
				
		// Create the city frame, send in the robot that will be controlled by buttons in control panel
		cityFrame = new CityFrame(theCity, 0, 0, 11, 11, this);
		
		// Initialize the threads with the robots
		roThread = new Thread(computer);
		huThread = new Thread(human);
		
		// set active to true so the while loops runs (it is turned to false when we terminate the threads)
		active = true;
		
		// Start the threads to loop
		roThread.start();
		huThread.start();
		
		// Resume the game
		toggle();
	}
	
	// This method is called when we want to terminate the game (when we press restart or click yes on the gameover dialog)
	// First try to extinguish the loop. If the thread is sleeping, then just interrupt it.
	public void terminate(){
		
		active = false;
		
		while(roThread.isAlive() || huThread.isAlive()){
			
			if(roThread.isAlive())
				roThread.interrupt();
			
			if(huThread.isAlive())
				huThread.interrupt();
		
		}
		
		// Destroy the robots and the threads 
		computer = null;
		human = null;
		roThread = null;
		huThread = null;
		
		// Hide the current frame
		cityFrame.hideFrame();

	}	
	
	// Pauses or resumes the game. 
	public void toggle(){
		
		if(toggle){
			toggle = false;
			// Pause the game using becker's start stop button
			cityFrame.getUi().getStartStopButton().doClick();
			// Set toggle state to false

		}
		else if(!toggle){
			
			cityFrame.getUi().getStartStopButton().doClick();
			toggle = true;
			
		}
		
	}
	
	// Checks if the robots collide. If they do, human robot goes kaboom and the two booleans are set to true
	public void collide(){
		
		Point computerPos = new Point(computer.getAvenue(),computer.getStreet());
		Point humanPos = new Point(human.getAvenue(),human.getStreet());
		
		if(computerPos.equals(humanPos)){
			human.destroy();
			collided = true;
			gameOver = true;
		}
		
	}
	
	

}
