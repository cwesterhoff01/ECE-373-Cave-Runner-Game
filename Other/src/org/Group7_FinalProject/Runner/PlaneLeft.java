package org.Group7_FinalProject.Runner;


public class PlaneLeft extends Sprite {
	
    private int dy;
    
    public PlaneLeft(int x, int y) {
        super(x, y);

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