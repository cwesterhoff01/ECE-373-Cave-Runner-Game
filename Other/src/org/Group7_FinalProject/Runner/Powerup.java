package org.Group7_FinalProject.Runner;

public abstract class Powerup extends Sprite {

	public Powerup(int x, int y) {
		super(x, y);
		visible = false;
	}
	
	public abstract void initPowerup();
	
}
