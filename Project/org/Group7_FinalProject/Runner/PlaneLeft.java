package org.Group7_FinalProject.Runner;

import org.Group7_FinalProject.Framework.RunningScreen;

public class PlaneLeft extends Sprite {
	
    private RunningScreen runningScreen;
    
    public PlaneLeft(int x, int y, RunningScreen runningScreen) {
    	
        super(x, y);
        this.runningScreen = runningScreen;
        loadImage("/resources/plateau_left.png");
    }

    @Override
    public void move() {

        if (y < 0) {
            y = runningScreen.getWindow().getHeight() + 130;
        }
        y -= DIFFICULTY;
    }

	/**
	 * @return the runningScreen
	 */
	public RunningScreen getRunningScreen() {
		return runningScreen;
	}

	/**
	 * @param runningScreen the runningScreen to set
	 */
	public void setRunningScreen(RunningScreen runningScreen) {
		this.runningScreen = runningScreen;
	}
	
}