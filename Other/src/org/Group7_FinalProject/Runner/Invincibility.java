package org.Group7_FinalProject.Runner;

public class Invincibility extends Powerup {
    
	public Invincibility(int x, int y) {
        super(x, y);
    }
	
	@Override
    public void initPowerup() {

        loadImage("src/resources/invincibility.png");
        getImageDimensions();
    }
}

	