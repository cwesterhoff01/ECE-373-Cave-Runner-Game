package org.Group7_FinalProject.Runner;

import org.Group7_FinalProject.Framework.RunningScreen;

public class Invincibility extends Powerup {
    
	public Invincibility(int x, int y, RunningScreen runningScreen) {
        super(x, y, runningScreen);
    }
	
	@Override
    public void initPowerup() {

        loadImage("src/resources/invincibility.png");
        getImageDimensions();
    }
}

	