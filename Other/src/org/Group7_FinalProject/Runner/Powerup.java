package org.Group7_FinalProject.Runner;

import org.Group7_FinalProject.Framework.RunningScreen;

public abstract class Powerup extends Sprite {
	
    protected final int INITIAL_Y = 850;
	private boolean isActive;
	
	public Powerup(int x, int y, RunningScreen runningScreen) {
		super(x, y, runningScreen);
		visible = false;
		isActive = false;
		initPowerup();
	}
	
	public abstract void initPowerup();
	
	public void move() {

        if (y < 0) {
        	this.setVisible(false);
        	isActive = false;
            y = INITIAL_Y;
        }
        
        else if (y > INITIAL_Y) { //this prob not needed
        	y = 0;
        }

        y -= DIFFICULTY;
    }
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
