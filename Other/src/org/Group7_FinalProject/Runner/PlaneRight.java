package org.Group7_FinalProject.Runner;


public class PlaneRight extends Sprite {

    private final int INITIAL_Y = 850;

    public PlaneRight(int x, int y) {
        super(x, y);

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