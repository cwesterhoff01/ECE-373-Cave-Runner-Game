package org.Group7_FinalProject.Runner;


public class Obstacle extends Sprite {
	
    public Obstacle(int x, int y) {
        super(x, y);
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