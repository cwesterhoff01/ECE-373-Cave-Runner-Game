package org.Group7_FinalProject.Runner;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.Group7_FinalProject.Framework.RunningScreen;
import org.Group7_FinalProject.Utilities.SoundEffect;

public abstract class Powerup extends Sprite {
	
	//Fields for a powerup
    protected final int INITIAL_Y = 850;
	protected boolean isActive;
	protected SoundEffect soundEffect;
	protected static RunningScreen runningScreen;
	protected static Random rand;
	
	//Constructor requires three arguments
	public Powerup(int x, int y, RunningScreen runningScreen) {
		
		super(x, y);
		Powerup.runningScreen = runningScreen;
		Powerup.rand = new Random();
		Powerup.rand.setSeed(System.currentTimeMillis());
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

	public abstract void activate();

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the soundEffect
	 */
	public SoundEffect getSoundEffect() {
		return soundEffect;
	}

	/**
	 * @param soundEffect the soundEffect to set
	 */
	public void setSoundEffect(SoundEffect soundEffect) {
		this.soundEffect = soundEffect;
	}

	/**
	 * @return the runningScreen
	 */
	public static RunningScreen getRunningScreen() {
		return runningScreen;
	}

	/**
	 * @param runningScreen the runningScreen to set
	 */
	public static void setRunningScreen(RunningScreen runningScreen) {
		Powerup.runningScreen = runningScreen;
	}

	/**
	 * @return the rand
	 */
	public static Random getRand() {
		return rand;
	}

	/**
	 * @param rand the rand to set
	 */
	public static void setRand(Random rand) {
		Powerup.rand = rand;
	}

	/**
	 * @return the iNITIAL_Y
	 */
	public int getINITIAL_Y() {
		return INITIAL_Y;
	}
	
}
