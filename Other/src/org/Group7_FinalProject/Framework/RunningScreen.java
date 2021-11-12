package org.Group7_FinalProject.Framework;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.Group7_FinalProject.Runner.*;

//RunningScreen class contains all fields and methods related to a RunningScreen
public class RunningScreen extends GameScreen implements ActionListener, KeyListener {

	//Fields for a RunningScreen
    private Timer timer;
    private Runner runner;
    private Ceiling ceiling;
	private List<PlaneRight> planesRight;
	private List<PlaneLeft> planesLeft;
	private List<Obstacle> obstacles;
	private List<Halt> haltPowerups;
	private List<Invincibility> invinPowerups;
	private boolean runnerDead;
	private boolean runnerPaused;
	private int prevStuck;
	private Integer depth;
	private long gameDelay;
    
	private final int[][] posPlnsRt = { // spawn positions for right planes
	        {450, 150}, {450, 550}
    };
    private final int[][] posPlnsLft = { // spawn positions for left planes
            {0, 350}, {0, 750}
    };
    private final int[][] posObst = { // spawn positions for obstacles
    		{80, 300}, {120, 300}, {500, 100}, {580, 500}, {40, 700}, {110, 700} 
    };
    private final int[][] posHalt = { // spawn positions for halt powerups
            {80, 290}, {250, 690}, {470, 90}, {780, 90}, {780, 490}
    };
    private final int[][] posInvin = { // spawn positions invincibility powerups
            {10, 290}, {50, 690}, {550, 90}, {750, 90}, {760, 490}
    };
    

    //Default no-arg constructor
    public RunningScreen() {
    	
    	this(new Window());
    }

    //Constructor that takes one argument
    public RunningScreen(Window w) {
    	
    	//Load the background image
    	super(w, new ImageIcon("src/resources/background.jpeg"));

    	//Initialize objects
        runner = new Runner(0, 0, this);
        ceiling = new Ceiling(0, 0, this);
        planesRight = new ArrayList<>();
        planesLeft = new ArrayList<>();
        obstacles = new ArrayList<>();
        haltPowerups = new ArrayList<>();
        invinPowerups = new ArrayList<>();
        depth = 0;
        gameDelay = 0;
        
        //The timer handles animation with ActionListener, the KeyListener handles keyboard input
        timer = new Timer(15, this); //every 15 ms, actionPerformed() executed
        addKeyListener(this);
    }
    
    /*
     * Method that draws everything on the screen, including background image and sprites
     * This method is called at the end of actionPerformed() via repaint()
     * actionPerformed() is tied to a 15ms timer, so this method will execute every 15ms
     */
    @Override
    public void paintComponent(Graphics g) {
    	
    	//Draw the background image
        super.paintComponent(g);
        
        //Draw all the sprites as long as the player is not dead
        if (!runnerDead)
            drawObjects(g);
        else
            drawGameOver(g);

        Toolkit.getDefaultToolkit().sync();
        
    }

    /*
     * Method that starts the running game
     * Add all method calls necessary to initialize the game here
     * Set all variables to their intial values here
     */
    public void startRunning() {
    	
    	//Request focus so that the KeyListener responds
    	requestFocus();
    	
    	//Initialize the sprites
    	initSprites();
    	depth = 0;
        runnerDead = false;
        runnerPaused = false;
        Sprite.resetDifficulty();
        timer.start();
		
    }
    
    /*
     * Method that stops the running game
     * Add all method calls necessary to stop the game here
     * Set all variables to their final values here
     */
	private void stopRunning() {
		
		//Stop the animations, cleanup the sprites
		timer.stop();
		endSprites();
		
	}
	
	//Method that initializes the sprites in their starting locations
	private void initSprites() {

        for (int[] p : posPlnsRt) {
            planesRight.add(new PlaneRight(p[0], p[1], this));
        }
        for (int[] p : posPlnsLft) {
            planesLeft.add(new PlaneLeft(p[0], p[1], this));
        }
        for (int[] p : posObst) {
        	obstacles.add(new Obstacle(p[0], p[1], this));
        }
        for (int[] p : posHalt) {
        	haltPowerups.add(new Halt(p[0], p[1], this));
        }
        for (int[] p : posInvin) {
            invinPowerups.add(new Invincibility(p[0], p[1], this));
        }
        
        //Place the runner in the upper left hand corner
        runner.setPosition(40, 60);
        runner.setVisible(true);
        
    }
	
	//Method that hides or destroys the sprites at the end
	private void endSprites() {
		
		//Obstacles should simply be destroyed, new obstacles will be used in next game
		planesRight.clear();
		planesLeft.clear();
		obstacles.clear();
		haltPowerups.clear();
    	invinPowerups.clear();
		
		//The runner should only be hidden, will be used again in next game
		runner.setVisible(false);
		
	}

	//Method that draws all visible sprite objects on the screen
    private void drawObjects(Graphics g) {

        if (runner.isVisible()) {
            g.drawImage(runner.getImage(), runner.getX(), runner.getY(), this);
        }
        if (ceiling.isVisible()) {
            g.drawImage(ceiling.getImage(), ceiling.getX(), ceiling.getY(), this);
        }
        
        for (PlaneRight plnRt : planesRight) {
            if (plnRt.isVisible()) {
                g.drawImage(plnRt.getImage(), plnRt.getX(), plnRt.getY(), this);
            }
        }
        
        for (PlaneLeft plnLft : planesLeft) {
            if (plnLft.isVisible()) {
                g.drawImage(plnLft.getImage(), plnLft.getX(), plnLft.getY(), this);
            }
        }
        
    	for (Obstacle obst : obstacles) {
            if (obst.isVisible()) {
                g.drawImage(obst.getImage(), obst.getX(), obst.getY(), this);
            }
        }
        
    	for(Halt hpu : haltPowerups) {
        	if(hpu.isVisible()) {
        		g.drawImage(hpu.getImage(), hpu.getX(), hpu.getY(), this);
        	}
    	}
        
    	for(Invincibility invin : invinPowerups) {
        	if(invin.isVisible()) {
        		g.drawImage(invin.getImage(), invin.getX(), invin.getY(), this);
        	}
    	}
        
        g.setColor(Color.WHITE);
        g.drawString("Depth: " + depth.toString(), 5, 15);
        
    }

    //Method that draws the "Game Over" message on the screen //TODO delete this? Maybe modify to display somewhere else
    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (window.getWidth() - fm.stringWidth(msg)) / 2, window.getHeight() / 2);
    }
	
    /*
     * Implement the actionPerformed() method as part of the ActionListener
     * actionPerformed() is tied to a 15ms timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	//Run all methods that should occur every timer period
        checkRunnerDead();
        checkPaused();
        updateRunner();
        updateCeiling();
        
        
        //loop to see if have to update planes, obstacles, runner, etc. based on Halt power up
        boolean update = true;
        for(Halt hpu : haltPowerups) {
        	if(hpu.isActive() == true) {
        		update = false;
        		//Will stop the game timer for 3 seconds
        		if((System.currentTimeMillis() - gameDelay) >= 3000){
        			hpu.setActive(false);
        			update = true;
        		}
        	}
        }
        if(update == true) {
	        updatePlanesRight();
	        updatePlanesLeft();
	        updateObstacles();
	        generatePowerUp(); //also has generate Obstacle
	        updatePowerUp();
	        int diff = Sprite.changeDifficulty();
	        //System.out.println("Difficulty increased to " + diff + "!");
        }
        checkCollisions();
        repaint();
        
        depth += runner.getDy();
        prevStuck -= 1;
        
    }
    
    /*
     * Implement all three methods that are part of the KeyListener
     * The appropriate method is fired whenever the game receives keyboard input
     */
	@Override
	public void keyTyped(KeyEvent e) {
		//TODO //Need this??
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
        if(key == KeyEvent.VK_ESCAPE) {
        	runnerPaused = true;
        }
        
		runner.keyPressed(e);
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		runner.keyReleased(e);
	}
	
	private void checkRunnerDead() {
        if (runnerDead)
        	stopRunning(); 
    }
	
	private void checkPaused() {
		if(runnerPaused) {
			timer.stop();
			int result = JOptionPane.showConfirmDialog(null, "Game is paused would you like to resume (yes to resume) (no to go back to the menu)", "Pause Menu", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				timer.start();
				runnerPaused = false;
			}
			else {
				runnerDead = true;
			}
		}
	}
	
    private void updateRunner() {
        if (runner.isVisible())
            runner.move();
    }
    
    private void updateCeiling() {
        if (ceiling.isVisible())
            ceiling.move();
    }

	private void updatePlanesRight() {

        for (int i = 0; i < planesRight.size(); i++) {

            PlaneRight a = planesRight.get(i);
            
            if (a.isVisible()) {
                a.move();
            } else {
                planesRight.remove(i);
            }
        }
    }
	
	private void updatePlanesLeft() {

        for (int i = 0; i < planesLeft.size(); i++) {

            PlaneLeft a = planesLeft.get(i);
            
            if (a.isVisible()) {
                a.move();
            } else {
                planesLeft.remove(i);
            }
        }
    }
	
	private void generatePowerUp() { //Will show a power up n% of the time, will have a 50-50 chance of what power up will be selected
		
		double occurance = 1; //This means 5% of the time a power up will be shown (1% is a lot btw)
		Random rand = new Random();
		double rand_double = rand.nextDouble(); //Generates a float between 0.0 -> 1.0
		System.out.println("rand double = " + rand_double);
		if(rand_double <= (occurance / 100)) {
			//set visible an obstacle or power up
			if (rand_double > 0.004) {
				
				int i = rand.nextInt(posObst.length); 
				obstacles.get(i).setVisible(true);
			}
			else if (rand_double > 0.003) {
				
				int i = rand.nextInt(posHalt.length); 
				haltPowerups.get(i).setVisible(true);
			}
			else {
				
				int i = rand.nextInt(posInvin.length); 
				invinPowerups.get(i).setVisible(true);
			}
		}
		//else does not show a power up
	}
	
	private void updatePowerUp() { //updates the position of the powerups so that it moves with the screen
		for(Halt hpu : haltPowerups) {
			
			hpu.move();
		}
		
		for(Invincibility invin : invinPowerups) {
			
			invin.move();
		}
	}
	
	private void updateObstacles() {
		for (Obstacle obst : obstacles) {

            obst.move();
        }
    }
	
    public void checkCollisions() {

        Rectangle rnrBnds = runner.getBounds();
        Rectangle ceilBnds = ceiling.getBounds();
        
        if (rnrBnds.intersects(ceilBnds)) {
        	
        	runnerDead = true;
        }
        
        for (PlaneRight plnRight : planesRight) {
            
            Rectangle plnRtBnds = plnRight.getBounds();

            if (rnrBnds.intersects(plnRtBnds)) {
            	
            	runner.setYMovement(false);
            	prevStuck = 2;
            }
            else if (prevStuck < 1) {
            	
            	runner.setYMovement(true);
            }
        }
        
        for (PlaneLeft plnLeft : planesLeft) {
            
            Rectangle plnLftBnds = plnLeft.getBounds();

            if (rnrBnds.intersects(plnLftBnds)) {
            	
            	runner.setYMovement(false);
                prevStuck = 2;
            }
            else if (prevStuck < 1) {
            	
            	runner.setYMovement(true);
            }
        }
        
        for (Obstacle obst : obstacles) {
            
            Rectangle obstBnds = obst.getBounds();

            if (rnrBnds.intersects(obstBnds) && obst.isVisible()) {
            	
            	runner.setXMovement(false);
                prevStuck = 2;
                for (Invincibility invin : invinPowerups) {
                	
                	if (invin.isActive()) {
                		
                		obst.setVisible(false);
                		runner.setXMovement(true);
                		break;
                	}
                }
            }
            /* else if (prevStuck < 1) { //need this to get unstuck again after collision with obstacle I think
            	
            	runner.setMovement(true);
            } */
        }
        
        for (Halt hpu : haltPowerups) {
        	
        	Rectangle hpuBnds = hpu.getBounds();
        	
        	if (rnrBnds.intersects(hpuBnds) && hpu.isVisible()) {
				if(hpu.isVisible()) {
					hpu.setVisible(false);
					hpu.setActive(true);
					gameDelay = System.currentTimeMillis();
				}
    		}
        }
        
        for (Invincibility invin : invinPowerups) {
        	
        	Rectangle invinBnds = invin.getBounds();
        	
        	if (rnrBnds.intersects(invinBnds) && invin.isVisible()) {
        		if(invin.isVisible()) {
        			invin.setVisible(false);
        			invin.setActive(true);
				}
        	}
        }
        
    }

	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	/**
	 * @return the runner
	 */
	public Runner getRunner() {
		return runner;
	}

	/**
	 * @param runner the runner to set
	 */
	public void setRunner(Runner runner) {
		this.runner = runner;
	}

	/**
	 * @return the ceiling
	 */
	public Ceiling getCeiling() {
		return ceiling;
	}

	/**
	 * @param ceiling the ceiling to set
	 */
	public void setCeiling(Ceiling ceiling) {
		this.ceiling = ceiling;
	}

	/**
	 * @return the obstacles
	 */
	public List<PlaneRight> getObstacles() {
		return planesRight;
	}

	/**
	 * @param obstacles the obstacles to set
	 */
	public void setObstacles(List<PlaneRight> obstacles) {
		this.planesRight = obstacles;
	}

	/**
	 * @return the obstaclesLeft
	 */
	public List<PlaneLeft> getObstaclesLeft() {
		return planesLeft;
	}

	/**
	 * @param obstaclesLeft the obstaclesLeft to set
	 */
	public void setObstaclesLeft(List<PlaneLeft> obstaclesLeft) {
		this.planesLeft = obstaclesLeft;
	}

	/**
	 * @return the runnerDead
	 */
	public boolean isRunnerDead() {
		return runnerDead;
	}

	/**
	 * @param runnerDead the runnerDead to set
	 */
	public void setRunnerDead(boolean runnerDead) {
		this.runnerDead = runnerDead;
	}

	/**
	 * @return the runnerPaused
	 */
	public boolean isRunnerPaused() {
		return runnerPaused;
	}

	/**
	 * @param runnerPaused the runnerPaused to set
	 */
	public void setRunnerPaused(boolean runnerPaused) {
		this.runnerPaused = runnerPaused;
	}

	/**
	 * @return the prevStuck
	 */
	public int getPrevStuck() {
		return prevStuck;
	}

	/**
	 * @param prevStuck the prevStuck to set
	 */
	public void setPrevStuck(int prevStuck) {
		this.prevStuck = prevStuck;
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

}
