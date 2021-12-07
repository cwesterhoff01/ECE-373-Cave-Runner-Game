package org.Group7_FinalProject.Runner;

import java.util.Timer;
import java.util.TimerTask;

import org.Group7_FinalProject.Framework.RunningScreen;
import org.Group7_FinalProject.Utilities.SoundEffect;

public final class Halt extends Powerup {
	
	private static int HALT_DURATION = 5000;
	private static double SPAWN_FREQUENCY = 0.5;
	private Timer haltTimer;
	
	public Halt(int x, int y, RunningScreen runningScreen) {
		
		super(x, y, runningScreen);
		loadImage("/resources/halt.png");
		this.soundEffect = new SoundEffect("/resources/halt.wav");
		haltTimer = new Timer();
		
	}
	
	//Method that generates a Halt powerup on the running screen
	public static void generateHaltPowerups() {
		//Get another random double between 0 - 1
		double randDouble = rand.nextDouble();
		//Set visible a power up if screen not halted (difficulty > 0 -> looks better and less cluttered)
		if (randDouble <= (SPAWN_FREQUENCY / 100.0) && Sprite.getDifficulty() > 0) {
			int numHalt = 0;
			for (Halt h : runningScreen.getHaltPowerups()) {
				if (h.isVisible()) 
					numHalt ++;
			}
			//No more than 1 halt powerups on map
			if (numHalt < 1) {
				int i = rand.nextInt(runningScreen.getPosHalt().length); 
				if (runningScreen.getHaltPowerups().get(i).getY() > runningScreen.getWindow().getHeight() - 50) {
					runningScreen.getHaltPowerups().get(i).setVisible(true);
				}
			}
		}
	}
	
	@Override
	public void activate() {
		soundEffect.play();
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
			Sprite.setDifficulty(Sprite.LAST_DIFFICULTY);
			runningScreen.getDifficultyTimer().start();
		}
	}

	/**
	 * @return the hALT_DURATION
	 */
	public static int getHALT_DURATION() {
		return HALT_DURATION;
	}

	/**
	 * @param hALT_DURATION the hALT_DURATION to set
	 */
	public static void setHALT_DURATION(int hALT_DURATION) {
		HALT_DURATION = hALT_DURATION;
	}

	/**
	 * @return the sPAWN_FREQUENCY
	 */
	public static double getSPAWN_FREQUENCY() {
		return SPAWN_FREQUENCY;
	}

	/**
	 * @param sPAWN_FREQUENCY the sPAWN_FREQUENCY to set
	 */
	public static void setSPAWN_FREQUENCY(double sPAWN_FREQUENCY) {
		SPAWN_FREQUENCY = sPAWN_FREQUENCY;
	}

	/**
	 * @return the haltTimer
	 */
	public Timer getHaltTimer() {
		return haltTimer;
	}

	/**
	 * @param haltTimer the haltTimer to set
	 */
	public void setHaltTimer(Timer haltTimer) {
		this.haltTimer = haltTimer;
	}

}
