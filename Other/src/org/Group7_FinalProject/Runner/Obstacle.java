package org.Group7_FinalProject.Runner;

import org.Group7_FinalProject.Framework.RunningScreen;
import org.Group7_FinalProject.Utilities.SoundEffect;

public class Obstacle extends Sprite {
	
    private final int INITIAL_Y = 850;
    private RunningScreen runningScreen;
    private SoundEffect destroySound;
    
    public Obstacle(int x, int y, RunningScreen runningScreen) {
    	
        super(x, y);
        visible = false;
        this.runningScreen = runningScreen;
        this.destroySound = new SoundEffect("src/resources/rockdestroy.wav");
        loadImage("src/resources/obstacle.png");
        
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