package org.Group7_FinalProject.Runner;


import java.awt.event.KeyEvent;

import org.Group7_FinalProject.Framework.RunningScreen;

public class Runner extends Sprite {

    private int dx;
    private int dy;
    private final int SPEED = 3;
    private int lastKey;
    private boolean jump;
    private int depth;

    //Constructor that requires two arguments
    public Runner(int x, int y) {
    	
        super(x, y);
        loadImage("src/resources/runner5.png");
        
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
        depth += y + DIFFICULTY;
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
    public int getDepth() {
    	return depth;
    }
    public int getDy() {
    	return dy;
    }
}
