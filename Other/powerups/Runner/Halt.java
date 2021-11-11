package org.Group7_FinalProject.Runner;

public class Halt extends Powerup {
	
	private boolean isActive;
	
	public Halt(int x, int y) {
		super(x, y);
		initPowerup();
		isActive = false;
		
	}
	//Will stop the game timer for 3 seconds

	@Override
	public void initPowerup() {
		loadImage("src/resources/star.png");
		getImageDimensions();
		this.setVisible(true);
	}
	
	public void move() {
		if(y < 0) {
			this.setVisible(false); //Powerup is out of bounds is now no longer visable
		}
		else { 
        y -= DIFFICULTY; //powerup is inn bounds can be moved with the screen and is visible
		}
    }

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	
}
