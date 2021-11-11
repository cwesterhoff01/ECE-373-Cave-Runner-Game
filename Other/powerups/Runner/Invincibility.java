package org.Group7_FinalProject.Runner;

public class Invincibility extends Sprite {

	    public Invincibility(int x, int y) {
	        super(x, y);

	        initObstacle();
	    }

	    private void initObstacle() {

	        loadImage("src/resources/invincibility.png");
	        getImageDimensions();
	        
	    }

	    public void move() {
	    	
	        y -= DIFFICULTY;
	    
	    }
}
