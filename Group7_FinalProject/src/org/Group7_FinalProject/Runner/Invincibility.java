package org.Group7_FinalProject.Runner;

import java.util.Timer;
import java.util.TimerTask;
import org.Group7_FinalProject.Framework.RunningScreen;
import org.Group7_FinalProject.Utilities.SoundEffect;

public final class Invincibility extends Powerup {
	
	private static int INVINCIBILITY_DURATION = 5000;
	private static int SPAWN_FREQUENCY = 2;
	private Timer invincibilityTimer;
    
	public Invincibility(int x, int y, RunningScreen runningScreen) {
        
		super(x, y, runningScreen);
        loadImage("src/resources/invincibility.png");
        this.soundEffect = new SoundEffect("src/resources/invincibility.wav");
        this.invincibilityTimer = new Timer();
        
    }
	
	//Method that generates Invincibility powerups on the running screen
	public static void generateInvincibilityPowerups() {
		//Get another random double between 0 - 1
		double randDouble = rand.nextDouble();
		//Set visible a power up if screen not halted (difficulty > 0 -> looks better and less cluttered)
		if (randDouble <= (SPAWN_FREQUENCY / 100.0) && Sprite.getDifficulty() > 0) {
			int numInvin = 0;
			for (Invincibility i : runningScreen.getInvinPowerups()) {
				if (i.isVisible()) 
					numInvin ++;
			}
			//No more than 2 invincibility powerups on map
			if (numInvin < 3) {
				int i = rand.nextInt(runningScreen.getPosInvin().length); 
				if (runningScreen.getInvinPowerups().get(i).getY() > runningScreen.getWindow().getHeight() - 50) {
					runningScreen.getInvinPowerups().get(i).setVisible(true);
				}
			}
		}
	}
	
	@Override
	public void activate() {
		soundEffect.play();
		setActive(true);
		invincibilityTimer.schedule(new DeactivateInvincibility(), INVINCIBILITY_DURATION);
	}
	
	private class DeactivateInvincibility extends TimerTask {
		@Override
		public void run() {
			setActive(false);
		}
	}

	/**
	 * @return the iNVINCIBILITY_DURATION
	 */
	public static int getINVINCIBILITY_DURATION() {
		return INVINCIBILITY_DURATION;
	}

	/**
	 * @param iNVINCIBILITY_DURATION the iNVINCIBILITY_DURATION to set
	 */
	public static void setINVINCIBILITY_DURATION(int iNVINCIBILITY_DURATION) {
		INVINCIBILITY_DURATION = iNVINCIBILITY_DURATION;
	}

	/**
	 * @return the sPAWN_FREQUENCY
	 */
	public static int getSPAWN_FREQUENCY() {
		return SPAWN_FREQUENCY;
	}

	/**
	 * @param sPAWN_FREQUENCY the sPAWN_FREQUENCY to set
	 */
	public static void setSPAWN_FREQUENCY(int sPAWN_FREQUENCY) {
		SPAWN_FREQUENCY = sPAWN_FREQUENCY;
	}

	/**
	 * @return the invincibilityTimer
	 */
	public Timer getInvincibilityTimer() {
		return invincibilityTimer;
	}

	/**
	 * @param invincibilityTimer the invincibilityTimer to set
	 */
	public void setInvincibilityTimer(Timer invincibilityTimer) {
		this.invincibilityTimer = invincibilityTimer;
	}

}

	
