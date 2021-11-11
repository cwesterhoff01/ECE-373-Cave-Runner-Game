package org.Group7_FinalProject.Runner;

public abstract class Powerup extends Sprite {
	private boolean isActive;
	
	public Powerup(int x, int y) {
		super(x, y);
		visible = false;
		isActive = false;
		initPowerup();
	}
	
	public abstract void initPowerup();
	public void move() {

        if (y < 0) {
        	this.setVisible(false);
        	isActive = false;
            y = INITIAL_Y;
        }
        
        else if (y > INITIAL_Y) { //this prob not needed
        	y = 0;
        }

        y -= DIFFICULTY;
    }
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
