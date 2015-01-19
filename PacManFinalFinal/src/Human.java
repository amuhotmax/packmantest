import java.awt.Color;
import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Robot;



public class Human extends Robot implements Runnable {
	
	public boolean active;
	public boolean toggle;
	
	private IOManager io;
	
	public Human(City arg0, int arg1, int arg2, Direction arg3, Color color, IOManager io) {
		
		super(arg0, arg1, arg2, arg3);
		
		setColor(color);
		
		this.io = io;

	}
	
	@Override
	public void run() {
		
		while(io.active){
			
			io.collide();
			
			if(io.toggle && !io.collided){
				executeAction();
			}

			io.collide();
			
			// If it's not active anymore, no point in sleeping.
			if(io.active){
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					
				}	
			}
			
		} // end of while
	}	// end of run()
	
	// Executes any given actions from the buttons. The buttons change the values of IOManager.action and/or IOManager.direction
	// After we execute an action we must set these empty to avoid unwanted action looping
	private void executeAction() {

		if(io.action == "move")
			moveTo(io.direction);
		else if(io.action == "pick")
			pickPrize();
	
		io.action = "";
		io.direction = null;
	}
	
	public void pickPrize() {
		// If can pick the thing (its over the thing), then player wins
		if(canPickThing()){
			pickThing();
			io.prizeAcquired = true;
			io.gameOver = true;
		}
	}

	public void moveTo(Direction direction) {
		
		// Turn until we match the target direction
			
		while(direction.toString() != getDirection().toString()){
			turnLeft();
		}
		
		// If there's no wall in front, move a unit
		if(frontIsClear())
			move();
	}
	
	public void destroy() {
		
		try {
			breakRobot("");
		} catch (Exception e){
			
		}
	}

}
