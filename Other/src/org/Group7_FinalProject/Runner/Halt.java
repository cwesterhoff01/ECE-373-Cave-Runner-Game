package org.Group7_FinalProject.Runner;

import org.Group7_FinalProject.Framework.RunningScreen;

public class Halt extends Powerup {
	
	public Halt(int x, int y, RunningScreen runningScreen) {
		super(x, y, runningScreen);
	}

	@Override
	public void initPowerup() {
		
		loadImage("src/resources/halt.png");
		getImageDimensions();
	}	
}
