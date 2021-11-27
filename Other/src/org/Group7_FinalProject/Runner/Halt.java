package org.Group7_FinalProject.Runner;

import java.util.Timer;
import java.util.TimerTask;

import org.Group7_FinalProject.Framework.RunningScreen;

public final class Halt extends Powerup {
	
	private Timer haltTimer;
	private int HALT_DURATION = 3000;
	
	public Halt(int x, int y, RunningScreen runningScreen) {
		
		super(x, y, runningScreen);
		loadImage("src/resources/halt.png");
		haltTimer = new Timer();
		
	}
	
	public void activate() {
		setActive(true);
		Sprite.LAST_DIFFICULTY = Sprite.DIFFICULTY;
		Sprite.setDifficulty(0);
		haltTimer.schedule(new DeactivateHalt(), HALT_DURATION);
		runningScreen.getDifficultyTimer().stop();
	}
	
	private class DeactivateHalt extends TimerTask {
		@Override
		public void run() {
			setActive(false);
			haltTimer.cancel();
			Sprite.setDifficulty(Sprite.LAST_DIFFICULTY);
			runningScreen.getDifficultyTimer().start();
		}
	}

}
