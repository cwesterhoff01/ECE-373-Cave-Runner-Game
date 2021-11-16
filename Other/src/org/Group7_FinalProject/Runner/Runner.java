package org.Group7_FinalProject.Runner;


import java.awt.event.KeyEvent;

import org.Group7_FinalProject.Framework.RunningScreen;

public class Runner extends Sprite {

    private int dx;
    private int dy;
    private final int SPEED = 3;
    private int lastKey;
    private boolean jump;
    private Integer depth;

    //Constructor that requires two arguments
    public Runner(int x, int y) {
    	
        super(x, y);
        loadImage("src/resources/runner.png");
        depth = 0;
    }
	
	public void setYMovement(boolean move) {	
		if (!move) {
			//blocked by obstacle
			//dx = 0; //maybe need this to not get head stuck in obstacle...
			dy = -DIFFICULTY;
		}
		else if (jump) {
			//do nothing
		}
		else { 
			//gravity/free-fall
			dy = SPEED;
			depth = depth + 1;
			if ((DIFFICULTY * 2) > SPEED) {
				dy = DIFFICULTY * 2;
			}
		}
	}
	
	public void setXMovement(boolean move) {	
		if (!move) {
			//blocked by obstacle
			//dx = 0; //maybe need this to not get head stuck in obstacle...
			dx = 0;
		}
		else if (jump) {
			//do nothing
		}
		else { 
			//?
			
		}
	}
	
	@Override
    public void move() {
    	
        x += dx;
        y += dy;
        
		if (x < 1) {
            x = 1200;
        }
		else if (x > 1200) {
			x = 0;
		}
		
     	if (y > 770) {
        	y = 770;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if ((key == KeyEvent.VK_SPACE) && (lastKey == KeyEvent.VK_LEFT)) {
        	jump = true;
        	dy = -SPEED;
        	if ((DIFFICULTY * 2) > SPEED) {
        		dy = -DIFFICULTY * 2;
        	}
        	dx = -SPEED;
        	if ((DIFFICULTY * 3) > SPEED) {
        		dx = -DIFFICULTY * 3;
        	}
        	
        }
        else if ((key == KeyEvent.VK_SPACE) && (lastKey == KeyEvent.VK_RIGHT)) {
        	jump = true;
        	dy = -SPEED;
        	if ((DIFFICULTY * 2) > SPEED) {
        		dy = -DIFFICULTY * 2;
        	}
        	dx = SPEED;
        	if ((DIFFICULTY * 3) > SPEED) {
        		dx = DIFFICULTY * 3;
        	}
        }
        
        if (key == KeyEvent.VK_SPACE) {
        	jump = true;
        	dy = -SPEED;
        	if ((DIFFICULTY * 2) > SPEED) {
        		dy = -(DIFFICULTY * 2);
        	}
        }

        if (key == KeyEvent.VK_LEFT) {
        	dx = -SPEED;
        	if ((DIFFICULTY * 3) > SPEED) {
        		dx = -DIFFICULTY * 3;
        	}
            lastKey = key;
        }

        if (key == KeyEvent.VK_RIGHT) {
        	dx = SPEED;
        	if ((DIFFICULTY * 3) > SPEED) {
        		dx = DIFFICULTY * 3;
        	}
            lastKey = key;
        }
        
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_SPACE) && (lastKey == KeyEvent.VK_LEFT)) {
        	jump = false;
        	dy = SPEED;
        	if ((DIFFICULTY * 2) > SPEED) {
        		dy = DIFFICULTY * 2;
        	}
        	dx = -SPEED;
        	if ((DIFFICULTY * 3) > SPEED) {
        		dx = -DIFFICULTY * 3;
        	}
        }
        
        else if ((key == KeyEvent.VK_SPACE) && (lastKey == KeyEvent.VK_RIGHT)) {
        	jump = false;
        	dy = SPEED;
        	if ((DIFFICULTY * 2) > SPEED) {
        		dy = DIFFICULTY * 2;
        	}
        	dx = SPEED;
        	if ((DIFFICULTY * 3) > SPEED) {
        		dx = DIFFICULTY * 3;
        	}
        }
        
        /* if (key == KeyEvent.VK_SPACE) {
            dx = 0;
            dy = DIFFICULTY * 2;
        } */ 
        
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        
    }
    
    public int getLastKey() {
    	return lastKey;
    }

    public int getDy() {
    	return dy;
    }

	/**
	 * @return the depth
	 */
	public Integer getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public boolean checkMovement(KeyEvent e) {
		boolean collision = true;
		int key = e.getKeyCode();
		//checks if next key movement will get play unstuck from obstacle
		if(key == KeyEvent.VK_SPACE) {
			this.setPosition(getX(), getY() - 50);
        	collision = false; //let the user jump over obstacle
        }
		else if (key == KeyEvent.VK_LEFT) { //check if going left will get out of obstacle
			this.setPosition(getX() - 20, getY());
			collision = false;
        }
		else if (key == KeyEvent.VK_RIGHT) { //check if going right will get out of obstacle
			this.setPosition(getX() + 20, getY());
			collision = false;
        }
        
		
		return collision;
	}
	public void undoMovement(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			this.setPosition(getX(), getY() - 20);
        }
		//checks if next key movement will get play unstuck from obstacle
		if (key == KeyEvent.VK_LEFT) { //check if going left will get out of obstacle
			this.setPosition(getX() + 20, getY());
        }
		else if (key == KeyEvent.VK_RIGHT) { //check if going right will get out of obstacle
			this.setPosition(getX() - 20, getY());
        }
        
	}
    
    
}
