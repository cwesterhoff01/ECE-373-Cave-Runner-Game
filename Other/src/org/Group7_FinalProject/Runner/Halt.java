package org.Group7_FinalProject.Runner;

public class Halt extends Powerup {
	
	public Halt(int x, int y) {
		super(x, y);
	}

	@Override
	public void initPowerup() {
		
		loadImage("src/resources/halt.png");
		getImageDimensions();
	}	
}
