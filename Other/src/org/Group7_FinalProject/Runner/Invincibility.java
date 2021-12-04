package org.Group7_FinalProject.Runner;

import java.util.Timer;
import java.util.TimerTask;
import org.Group7_FinalProject.Framework.RunningScreen;
import org.Group7_FinalProject.Utilities.SoundEffect;

public final class Invincibility extends Powerup {
	
	private Timer invincibilityTimer;
	private int INVINCIBILITY_DURATION = 3000;
    
	public Invincibility(int x, int y, RunningScreen runningScreen) {
        
		super(x, y, runningScreen);
        loadImage("src/resources/invincibility.png");
        this.soundEffect = new SoundEffect("src/resources/powerupSound.wav");
        invincibilityTimer = new Timer();
        
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

}

	
