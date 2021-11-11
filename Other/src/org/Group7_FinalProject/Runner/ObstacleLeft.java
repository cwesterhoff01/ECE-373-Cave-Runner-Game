package org.Group7_FinalProject.Runner;


public class ObstacleLeft extends Sprite {

    private final int INITIAL_Y = 850;

    public ObstacleLeft(int x, int y) {
        super(x, y);

        initObstacleLeft();
    }

    private void initObstacleLeft() {

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

        y -= (int) (DIFFICULTY - 0.5);
    }
}