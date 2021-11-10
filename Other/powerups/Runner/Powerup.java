package org.Group7_FinalProject.Runner;

public abstract class Powerup extends Sprite {

	public Powerup(int x, int y) {
		super(x, y);
	}
	
	private boolean checkCollision() {
		boolean collision = false;
		
		return collision;
	}
	
	
	public abstract void initPowerup();
	
}
