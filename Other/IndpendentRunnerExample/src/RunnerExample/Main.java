package RunnerExample;
import java.awt.EventQueue;
import java.util.Timer;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		//Create a new JFrame to hold our example
		JFrame frame = new JFrame();
        frame.setTitle("Moving sprite");
        frame.setSize(1400, 850);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create a running screen connected to this JFrame
    	RunningScreen runningScreen = new RunningScreen(frame);
    	frame.add(runningScreen);
    	
    	//This is like saying "Start the Game!"
        runningScreen.startRunning();
        //Create a timer to increase difficulty
        Timer timer = new Timer();
    	timer.schedule(new Difficulty(), 0, 5000);
    	
    	//Must call setVisible after starting the game, for some reason...
    	frame.setVisible(true);
    	
	}

}
