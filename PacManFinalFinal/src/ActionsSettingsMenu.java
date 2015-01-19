import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;


public class ActionsSettingsMenu extends JMenuBar {

	IOManager io;
	
	public ActionsSettingsMenu(final IOManager io) {
		
		this.io = io;
		
		JMenu actions = new JMenu("Actions");
		JMenu settings = new JMenu("Settings");
		
		JMenuItem pause = new JMenuItem("pause");
		JMenuItem restart = new JMenuItem("restart");
		
		actions.add(pause);
		actions.add(restart);
		
		restart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				io.restart = true;
			}
			
		});
		
		pause.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JMenuItem menuItem = (JMenuItem) e.getSource();
				
				String s = (menuItem.getText() == "pause") ? "continue" : "pause";
				
				menuItem.setText(s);
				
				io.toggle();
			}
			
		});		
		
		JRadioButtonMenuItem easy = new JRadioButtonMenuItem("easy");
		JRadioButtonMenuItem medium = new JRadioButtonMenuItem("medium");
		JRadioButtonMenuItem hard = new JRadioButtonMenuItem("hard");
		
		ButtonGroup group = new ButtonGroup();
		group.add(easy);
		group.add(medium);
		group.add(hard);
		
		// set selected given the speed
		if(io.speed == 0.3){
			easy.setSelected(true);
		}
		else if(io.speed == 0.7){
			medium.setSelected(true);
		}
		else{
			hard.setSelected(true);
		}
		
		settings.add(easy);
		settings.add(medium);
		settings.add(hard);
		
		easy.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				io.speed = 0.3;
				io.restart = true;
			}
		});
		
		medium.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				io.speed = 0.7;
				io.restart = true;
			}
			
		});
		
		hard.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				io.speed = 1;
				io.restart = true;
			}
			
		});
		
		this.add(actions);
		this.add(settings);
		
	}	

}
