package RunnerExample;


import java.util.Timer;
import java.util.TimerTask;

public class Difficulty extends TimerTask {
	
    public void run() {
    	
       int diff = Sprite.changeDifficulty();
       System.out.println("Difficulty increased to " + diff + "!");
       
    }
    
}