package org.Group7_FinalProject.Runner;

import org.Group7_FinalProject.Framework.RunningScreen;

public class PlaneLeft extends Sprite {
	
    protected final int INITIAL_Y = 850;
    private int dy;
    
    public PlaneLeft(int x, int y, RunningScreen runningScreen) {
        super(x, y, runningScreen);

        initPlaneLeft();
    }

    private void initPlaneLeft() {

        loadImage("src/resources/crystal_left.png");
        getImageDimensions();
    }

    public void move() {

        if (y < 0) {
            y = INITIAL_Y;
        }
        
        else if (y > INITIAL_Y) {
        	y = 0;
        }

        y -= DIFFICULTY;
        dy = DIFFICULTY;
    }
    
    public int getDy() {
    	return dy;
    }
}