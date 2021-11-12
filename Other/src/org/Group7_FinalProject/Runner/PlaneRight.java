package org.Group7_FinalProject.Runner;

import org.Group7_FinalProject.Framework.RunningScreen;

public class PlaneRight extends Sprite {

    protected final int INITIAL_Y = 850;
    
    public PlaneRight(int x, int y, RunningScreen runningScreen) {
        super(x, y, runningScreen);

        initPlaneRight();
    }

    private void initPlaneRight() {

        loadImage("src/resources/crystal_right.png");
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
    }
}