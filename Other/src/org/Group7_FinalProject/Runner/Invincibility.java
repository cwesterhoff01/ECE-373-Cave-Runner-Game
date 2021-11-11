package org.Group7_FinalProject.Runner;

public class Invincibility extends Powerup {
	private boolean isActive;
    
	public Invincibility(int x, int y) {
        super(x, y);
        initPowerup();
    	isActive = false;
    }
	
	@Override
    public void initPowerup() {

        loadImage("src/resources/invincibility.png");
        getImageDimensions();
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

	