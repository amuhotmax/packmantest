import becker.robots.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CityFrame {
	
	// the main frame
    private JFrame frame;
    // used to get the city view
    private RobotUIComponents ui;
    
	public CityFrame(City model, int firstVisibleStreet,
                    int firstVisibleAvenue, int numVisibleStreets, int numVisibleAvenues, IOManager ioManager) {
            
    		super();

    		// initialize the main frame
            frame = new JFrame("PACMAN GAME");
            
            // Get the view of the city with robots
            ui = new RobotUIComponents(model, firstVisibleStreet,
                            firstVisibleAvenue, numVisibleStreets, numVisibleAvenues);
            
            //Add the top drop down menu bar
            frame.setJMenuBar(new ActionsSettingsMenu(ioManager));
            
            // Wrapper to contain the city view and the control panel view 
            JPanel wrapper = new JPanel();
            
            // Sets the layout of the wrapper
            wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.PAGE_AXIS));
            
            // Add the CityView to the wrapper
            wrapper.add(ui.getCityView());
            
            // Create the control panel
            // add the control panel to the wrapper
            wrapper.add(new ControlPanel(ioManager));
            
            frame.setContentPane(wrapper);
            frame.pack();
            frame.repaint();
            frame.setVisible(true);
            
            frame.addWindowListener(new WindowAdapter(){
            	@Override
            	public void windowClosing(WindowEvent e){
            		System.exit(0);
            	}
            });
            
    }
    
    // Disposes the window.
    public void hideFrame(){
    	frame.dispose();
    }
    
    // Creates the control panel with the buttons and action listeners and returns
    // it as a JPanel
    
    public RobotUIComponents getUi() {
		return ui;
	}
}