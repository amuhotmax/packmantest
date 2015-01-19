import javax.swing.JOptionPane;


public class TheMain {
	
	// Initial speed. If modified, ActionsSettingsMenu has to be updated in regards to which one is selected. Could have used enum tho'
	private static double speed = 300;
	
	private static IOManager io;

	public static void main(String[] args) {
		
		// The constructor of IOManager creates the game. When we want to restart, we will instantiate IOManager again (after io.terminate() of course)
		io = new IOManager(speed);
		
		while(true){
		
			// If it's over, pause the game, then show the gameover dialog
			if(io.gameOver){
				io.toggle();
				gameOverDialog();
			}
			
			// If we restart the game, we first terminate the threads, set the speed to the previous one or the newly selected one
			if(io.restart){
				io.terminate();
				
				speed = io.speed;
				
				io = new IOManager(speed);
			}
			
			// Sleeping does good to your CPU, did you know that?
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				
			}
			
		} // End of while
	} // End of main method
	
	public static void gameOverDialog(){

		String message = "";
		
		if(io.collided){
			message = "You lost the game! The computer devoured you! Restart the game?";
		}
		else if(io.prizeStolen){
			message = "You lost the game! The computer ate the cheese!!! Restart the game?";
		}
		else if(io.prizeAcquired){
			message = "You won the game! You're enjoying the best cheese in the city! Nom, nom, nom! Restart the game?";
		}
		
		int confirm = -1;
		
		// If its game over, then tell the user the game state (message) and ask him for re-match
		
		confirm = JOptionPane.showOptionDialog(null, message, "GAME OVER", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    	
    	if(confirm == 0){
    		io.restart = true;
    	}
    	else if(confirm == 1)
    		System.exit(0);
	}

}
