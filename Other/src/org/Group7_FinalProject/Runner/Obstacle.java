package org.Group7_FinalProject.Runner;

import java.util.Random;

import org.Group7_FinalProject.Framework.RunningScreen;
import org.Group7_FinalProject.Utilities.SoundEffect;

public class Obstacle extends Sprite {
	
    private final int INITIAL_Y = 850;
    private static int SPAWN_FREQUENCY = 5;
    private static RunningScreen runningScreen;
    private SoundEffect destroySound;
    private static Random rand;
    
    public Obstacle(int x, int y, RunningScreen runningScreen) {
    	
        super(x, y);
        visible = false;
        Obstacle.runningScreen = runningScreen;
        this.destroySound = new SoundEffect("src/resources/rockdestroy.wav");
        loadImage("src/resources/obstacle.png");
        Obstacle.rand = new Random();
        Obstacle.rand.setSeed(System.currentTimeMillis());
        
    }
    
    //Method that generates obstacles on the running screen
    public static void generateObstacles() {
		//Get another random double between 0 - 1
		double randDouble = rand.nextDouble();
		//Set visible a power up if screen not halted (difficulty > 0 -> looks better and less cluttered)
		if (randDouble <= (SPAWN_FREQUENCY * Sprite.getDifficulty() / 100.0) && Sprite.getDifficulty() > 0) {
			int numObst = 0;
			for (Obstacle o : runningScreen.getObstacles()) {
				if (o.isVisible()) 
					numObst ++;
			}
			//No more than 3 obstacles on running screen
			if (numObst < 4) {
				int i = rand.nextInt(runningScreen.getPosObst().length); 
				if (runningScreen.getObstacles().get(i).getY() > runningScreen.getWindow().getHeight() - 50) {
					runningScreen.getObstacles().get(i).setVisible(true);
				}
			}
		}
    }

    @Override
    public void move() {

        if (y < 0) {
            y = INITIAL_Y;
            visible = false;
        }
        
        else if (y > INITIAL_Y) {
        	y = 0;
        }

        y -= DIFFICULTY;
    }
    
    //Method that "destroys" the obstacle
    public void destroy() {
    	
    	this.setVisible(false);
    	destroySound.play();
    	
    }
    
}