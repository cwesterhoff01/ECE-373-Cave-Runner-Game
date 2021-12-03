package org.Group7_FinalProject.Runner;

import java.util.Timer;
import java.util.TimerTask;

import org.Group7_FinalProject.Framework.RunningScreen;

public abstract class Powerup extends Sprite {
	
	//Fields for a powerup
    protected final int INITIAL_Y = 850;
	protected boolean isActive;
	protected RunningScreen runningScreen;
	
	//Constructor requires three arguments
	public Powerup(int x, int y, RunningScreen runningScreen) {
		
		super(x, y);
		this.runningScreen = runningScreen;
		visible = false;
		isActive = false;
		
	}
	
	@Override
	public void move() {

        if (y < 0) {
        	this.setVisible(false);
        	isActive = false;
        	y = INITIAL_Y;
        }

        y -= DIFFICULTY;
    }
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public abstract void activate();
	
}
