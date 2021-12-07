package org.Group7_FinalProject.Runner;


import java.awt.event.KeyEvent;

import org.Group7_FinalProject.Framework.RunningScreen;
import org.Group7_FinalProject.Utilities.SoundEffect;

/*
 * The Runner class includes all fields and methods related to a Runner
 */
public class Runner extends Sprite {

	//Fields for a Runner
    private final int JUMP_SPEED = 9;
    private final int VERT_SPEED = 3;
    private final int HORIZ_SPEED = 3;
    private final int JUMP_DURATION = 400;
    private int dx;
    private int dy;
    private boolean movingLeft;
    private boolean movingRight;
    private boolean jumping;
    private long jumpStart;
    private Integer depth;
    private RunningScreen runningScreen;
    private SoundEffect runnerDying;

    //Constructor that requires three arguments
    public Runner(int x, int y, RunningScreen runningScreen) {
    	
        super(x, y);
        loadImage("src/resources/runner.png");
        this.runnerDying = new SoundEffect("src/resources/oof.wav");
        this.dx = 0;
        this.dy = 0;
        this.movingLeft = false;
        this.movingRight = false;
        this.jumping = false;
        this.jumpStart = 0;
        this.depth = 0;
        this.runningScreen = runningScreen;
        
    }
	
	@Override
    public void move() {
     	
		//Determine where the user wants to move in the y direction
		if (System.currentTimeMillis() - jumpStart > JUMP_DURATION)
			jumping = false;	
		//Default movement is gravity/free-fall
		if (!jumping) {
			dy = DIFFICULTY + VERT_SPEED;
		}
		
		//Determine how the user wants to move in the x direction
		if (movingLeft) {
        	dx = -HORIZ_SPEED;
        	if ((DIFFICULTY * 3) > HORIZ_SPEED) {
        		dx = -DIFFICULTY * 3;
        	}
		}
		else if (movingRight) {
        	dx = HORIZ_SPEED;
        	if ((DIFFICULTY * 3) > HORIZ_SPEED) {
        		dx = DIFFICULTY * 3;
        	}
		}
		else {
			dx = 0;
		}
        
		/*
		 * Now, run a whole bunch of checks to determine if and where the runner
		 * has collided with other objects on the screen. This may mean the user can't
		 * move where he wants to (i.e, he is blocked). Set dx and dy accordingly
		 */
     	for (PlaneRight plnRight : runningScreen.getPlanesRight()) {       
            if (Sprite.isCollided(this, plnRight)) {
            	switch(Sprite.getCollisionPosition(this, plnRight)) {
	            	case TOP:
	            		if (dy > 0)
	            			dy = -DIFFICULTY;
	            		break;
	            	case BOTTOM:
	            		if (dy < 0) {
	            			dy = 0;
	            			jumping = false;
	            		}
	            		break;
	            	case LEFT:
	            		if (dx > 0)
	            			dx = 0;
	            		break;
	            	case RIGHT:
	            		if (dx < 0)
	            			dx = 0;
	            		break;
	            	default:
	            		//Nothing goes in default, just here to be proper
            	}
            }
        }
        for (PlaneLeft plnLeft : runningScreen.getPlanesLeft()) {         
            if (Sprite.isCollided(this, plnLeft)) {
            	switch(Sprite.getCollisionPosition(this, plnLeft)) {
	            	case TOP:
	            		if (dy > 0)
	            			dy = -DIFFICULTY;
	            		break;
	            	case BOTTOM:
	            		if (dy < 0) {
	            			dy = 0;
	            			jumping = false;
	            		}
	            		break;
	            	case LEFT:
	            		if (dx > 0)
	            			dx = 0;
	            		break;
	            	case RIGHT:
	            		if (dx < 0)
	            			dx = 0;
	            		break;
	            	default:
	            		//Nothing goes in default, just here to be proper
            	}
            }
        }
        for (Obstacle obst : runningScreen.getObstacles()) {
            if (Sprite.isCollided(this, obst) && obst.isVisible()) {
            	boolean invincible = false;
                for (Invincibility invin : runningScreen.getInvinPowerups()) {             	
                	if (invin.isActive()) {
                		invincible = true;
                		obst.setVisible(false);
                		obst.destroy();
                		break;
                	}
                }
                if (!invincible) {
	            	switch(Sprite.getCollisionPosition(this, obst)) {
		            	case TOP:
		            		if (dy > 0)
		            			dy = -DIFFICULTY;
		            		break;
		            	case BOTTOM:
		            		if (dy < 0)
		            			dy = 0;
		            		break;
		            	case LEFT:
		            		if (dx > 0)
		            			dx = 0;
		            		break;
		            	case RIGHT:
		            		if (dx < 0)
		            			dx = 0;
		            		break;
		            	default:
		            		//Nothing goes in default, just here to be proper
		        	}
                }
            }
        }
        for (Halt hpu : runningScreen.getHaltPowerups()) {
        	if (Sprite.isCollided(this, hpu) && hpu.isVisible()) {
				if(hpu.isVisible()) {
					hpu.setVisible(false);
					hpu.activate();
				}
    		}
        }
        for (Invincibility invin : runningScreen.getInvinPowerups()) {    	
        	if (Sprite.isCollided(this, invin) && invin.isVisible()) {
        		if(invin.isVisible()) {
        			invin.setVisible(false);
        			invin.activate();
				}
        	}
        }
        
        //dx and dy have been set to their proper values at this point, update the position of the runner
        x += dx;
        y += dy;
        
        //Prevent Runner from escaping the window
		if (x < 1) {
			x = 1;
        }
		else if (x > runningScreen.getWindow().getWidth() - this.getWidth()) {
			x = runningScreen.getWindow().getWidth() - this.getWidth();
		}
		//Prevent Runner from moving below window limits
		if (y > runningScreen.getWindow().getHeight() - this.getHeight() - 30) {
        	y = runningScreen.getWindow().getHeight() - this.getHeight() - 30;
        	dy = 0;
        }
		
		//Update the depth, only if runner is actively moving down the screen
		if (dy != -DIFFICULTY && dy != 0)
        	depth = depth + (dy + DIFFICULTY);
     	
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        //If the player tries to jump, make sure they are actually standing on something to jump from
        if (key == KeyEvent.VK_UP) {
        	boolean standing = false;
        	if (dy == 0) 
        		standing = true;
         	for (PlaneRight plnRight : runningScreen.getPlanesRight()) {
         		if (Sprite.isCollided(this, plnRight)) {
	                if (Sprite.getCollisionPosition(this, plnRight) == CollisionPosition.TOP) {
	                	standing = true;
	                	break;
	                }
         		}
            }
            for (PlaneLeft plnLeft : runningScreen.getPlanesLeft()) {
            	if (Sprite.isCollided(this, plnLeft)) {
	                if (Sprite.getCollisionPosition(this, plnLeft) == CollisionPosition.TOP) {
	                	standing = true;
	                	break;
	                }
            	}
            }
            for (Obstacle obst : runningScreen.getObstacles()) {
            	if (Sprite.isCollided(this, obst) && obst.isVisible()) {
	                if (Sprite.getCollisionPosition(this, obst) == CollisionPosition.TOP) {
	                	standing = true;
	                	break;
	                }
            	}
            }
            if (standing) {
	        	jumping = true;
	        	jumpStart = System.currentTimeMillis();
	        	dy = -DIFFICULTY - JUMP_SPEED;
            }
        }

        //Move left
        if (key == KeyEvent.VK_LEFT) {
        	movingLeft = true;
        }
        
        //Move right
        if (key == KeyEvent.VK_RIGHT) {
        	movingRight = true;
        }
        
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_UP) {
        	jumping = false;
        }
        
        if (key == KeyEvent.VK_LEFT) {
        	movingLeft = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
        	movingRight = false;
        }
        
    }

	/**
	 * @return the dx
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @param dx the dx to set
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	/**
	 * @return the dy
	 */
	public int getDy() {
		return dy;
	}

	/**
	 * @param dy the dy to set
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}

	/**
	 * @return the movingLeft
	 */
	public boolean isMovingLeft() {
		return movingLeft;
	}

	/**
	 * @param movingLeft the movingLeft to set
	 */
	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	/**
	 * @return the movingRight
	 */
	public boolean isMovingRight() {
		return movingRight;
	}

	/**
	 * @param movingRight the movingRight to set
	 */
	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	/**
	 * @return the jumping
	 */
	public boolean isJumping() {
		return jumping;
	}

	/**
	 * @param jumping the jumping to set
	 */
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	/**
	 * @return the jumpStart
	 */
	public long getJumpStart() {
		return jumpStart;
	}

	/**
	 * @param jumpStart the jumpStart to set
	 */
	public void setJumpStart(long jumpStart) {
		this.jumpStart = jumpStart;
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

	/**
	 * @return the runningScreen
	 */
	public RunningScreen getRunningScreen() {
		return runningScreen;
	}

	/**
	 * @param runningScreen the runningScreen to set
	 */
	public void setRunningScreen(RunningScreen runningScreen) {
		this.runningScreen = runningScreen;
	}

	/**
	 * @return the runnerDying
	 */
	public SoundEffect getRunnerDying() {
		return runnerDying;
	}

	/**
	 * @param runnerDying the runnerDying to set
	 */
	public void setRunnerDying(SoundEffect runnerDying) {
		this.runnerDying = runnerDying;
	}

	/**
	 * @return the jUMP_SPEED
	 */
	public int getJUMP_SPEED() {
		return JUMP_SPEED;
	}

	/**
	 * @return the vERT_SPEED
	 */
	public int getVERT_SPEED() {
		return VERT_SPEED;
	}

	/**
	 * @return the hORIZ_SPEED
	 */
	public int getHORIZ_SPEED() {
		return HORIZ_SPEED;
	}

	/**
	 * @return the jUMP_DURATION
	 */
	public int getJUMP_DURATION() {
		return JUMP_DURATION;
	}
    
}
