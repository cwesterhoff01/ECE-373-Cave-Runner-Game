package org.Group7_FinalProject.Runner;

import java.util.Timer;
import java.util.TimerTask;
import org.Group7_FinalProject.Framework.RunningScreen;
import org.Group7_FinalProject.Utilities.MusicPlayOnce;

public final class Invincibility extends Powerup {
	
	private Timer invincibilityTimer;
	private int INVINCIBILITY_DURATION = 3000;
    
	public Invincibility(int x, int y, RunningScreen runningScreen) {
        
		super(x, y, runningScreen);
        loadImage("src/resources/invincibility.png");
        invincibilityTimer = new Timer();
        
    }
	
	@Override
	public void activate() {
		String filepath = "src/resources/powerupSound.wav";
		MusicPlayOnce musicObject = new MusicPlayOnce();
		musicObject.playMusic(filepath);
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

	
