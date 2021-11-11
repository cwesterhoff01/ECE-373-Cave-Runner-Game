package org.Group7_FinalProject.Runner;


public class Obstacle extends Sprite {

    private final int INITIAL_Y = 850;

    public Obstacle(int x, int y) {
        super(x, y);

        initObstacle();
    }

    private void initObstacle() {

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

        y -= (int) (DIFFICULTY - 0.5);
    }
}