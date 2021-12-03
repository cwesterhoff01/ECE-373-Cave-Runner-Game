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
        soundEffectFilePath = "src/resources/powerupSound.wav";
        soundEffectObject = new MusicPlayOnce();
        invincibilityTimer = new Timer();
        
    }
	
	@Override
	public void activate() {
		soundEffectObject.playMusic(soundEffectFilePath);
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

	
