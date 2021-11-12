package org.Group7_FinalProject.Runner;

import org.Group7_FinalProject.Framework.RunningScreen;

public class Obstacle extends Sprite {
	
    protected final int INITIAL_Y = 850;
    
    public Obstacle(int x, int y, RunningScreen runningScreen) {
        super(x, y, runningScreen);
        visible = false;
        initObstacle();
    }

    private void initObstacle() {

        loadImage("src/resources/obstacle2.png");
        getImageDimensions();
    }

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
}