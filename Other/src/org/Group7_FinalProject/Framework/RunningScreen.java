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
	private List<Invincibility> invinPowerups; //need to set up obstacles before use
	private boolean runnerDead;
	private int prevStuck;
	private Integer depth;
	private Game currGame;
	private long gameDelay;
    
	private final int[][] posPlnsRt = { // spawn positions
	        {450, 150}, {450, 550}
    };
    private final int[][] posPlnsLft = { // spawn positions
            {0, 350}, {0, 750}
    };
    private final int[][] posObst = { // spawn positions
    		{80, 350}, {120, 350}, {500, 150}, {580, 550}, {40, 750}, {110, 750} 
    };
    private final int[][] posHalt = { // spawn positions
            {100, 350}, {150, 750}, {470, 150}, {580, 150} 
    };
    private final int[][] posInvin = { // spawn positions
            {10, 350}, {50, 750}, {550, 150}, {750, 150} 
    };
    

    //Default no-arg constructor
    public RunningScreen() {
    	
    	this(new Window());
    }

    //Constructor that takes one argument
    public RunningScreen(Window w) {
    	
    	//Load the background image
    	super(w, new ImageIcon("src/resources/background.jpeg"));
    	//Allows to set currScreen to menu
    	currGame = w.getGame(); 
    	//Initialize objects
        runner = new Runner(0, 0);
        ceiling = new Ceiling(0, 0);
        planesRight = new ArrayList<>();
        planesLeft = new ArrayList<>();
        obstacles = new ArrayList<>();
        haltPowerups = new ArrayList<>();
        invinPowerups = new ArrayList<>();
        depth = 0;
        gameDelay = 0;
        //The timer handles animation with ActionListener, the KeyListener handles keyboard input
        timer = new Timer(15, this); //every 15 ms call action performed
        

        addKeyListener(this);
    }
    
    //In addition to drawing a background image, a RunningScreen must draw sprites
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

    //Method that starts the running process
    public void startRunning() {
    	
	//makes it so last runs powerups dont carry over //really need all of this?
    	runner = new Runner(0, 0);
        ceiling = new Ceiling(0, 0);
        planesRight = new ArrayList<>();
        planesLeft = new ArrayList<>();
        haltPowerups = new ArrayList<>();
    	invinPowerups = new ArrayList<>();
    	Sprite.resetDifficulty();
    	
    	//Request focus so that the KeyListener responds
    	requestFocus();
    	//We are now in the game, so start the animations
    	initSprites();
    	depth = 0;
        runnerDead = false;
        timer.start();
		
    }
    
    //Method that stops the running process
	private void stopRunning() {
		
		//Stop the animations
		timer.stop();
		
		//Clear the lists of obstacles
		planesRight.clear();
		planesLeft.clear();
		haltPowerups.clear();
    	invinPowerups.clear();
		
		//Hide the runner
		runner.setVisible(false);
	}
	
	//Method that initializes the sprites in their starting locations
	private void initSprites() {

        for (int[] p : posPlnsRt) {
            planesRight.add(new PlaneRight(p[0], p[1]));
        }
        for (int[] p : posPlnsLft) {
            planesLeft.add(new PlaneLeft(p[0], p[1]));
        }
        
        //Place the runner in the upper left hand corner
        runner.setPosition(40, 60);
        runner.setVisible(true);
        
        for (int[] p : posObst) {
        	obstacles.add(new Obstacle(p[0], p[1]));
        }
        for (int[] p : posHalt) {
        	haltPowerups.add(new Halt(p[0], p[1]));
        }
        for (int[] p : posInvin) {
            invinPowerups.add(new Invincibility(p[0], p[1]));
        }
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

    //Method that draws the "Game Over" message on the screen //TODO delete this?
    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (window.getWidth() - fm.stringWidth(msg)) / 2, window.getHeight() / 2);
    }
	
    //Method that implements the ActionListener
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
        		if((System.currentTimeMillis() - gameDelay) >= 1000){
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
	        System.out.println("Difficulty increased to " + diff + "!");
        }
        checkCollisions();
        repaint();
        
    	depth = depth + 1; //this adds depth for the score based on time
        prevStuck -= 1;
        
    }
    
    //These three methods implement the KeyListener
	@Override
	public void keyTyped(KeyEvent e) {
		//TODO //Need this??
	}
	@Override
	public void keyPressed(KeyEvent e) {
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
		if(runner.getIsPaused()) {
			timer.stop();
			int result = JOptionPane.showConfirmDialog(null, "Game is paused would you like to resume (yes to resume) (no to go back to the menu)", "Pause Menu", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				timer.start();
				runner.setIsPaused(false);
			}
			else {
				stopRunning();
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
	
	private void generatePowerUp() { //Will show a powerup n% of the time, will have a 50-50 chance of what powerup will be selected
		
		double occurance = 1; //This means 5% of the time a powerup will be shown (1% is alot btw)
		Random rand = new Random();
		double rand_double = rand.nextDouble(); //Generates a float between 0.0 -> 1.0
		if(rand_double <= (occurance / 100)) {
			//set visible a powerup
			if (rand_double > 0.6) {
				
				int i = rand.nextInt(posObst.length); 
				obstacles.get(i).setVisible(true);
			}
			else if (rand_double < 0.3) {
				
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
			if(hpu.isVisible()) {
				hpu.move();
			}
		}
		
		for(Invincibility invin : invinPowerups) {
			if(invin.isVisible()) {
				invin.move();
			}
		}
	}
	
	private void updateObstacles() {
		for (int i = 0; i < obstacles.size(); i++) {

            Obstacle a = obstacles.get(i);

            if (a.isVisible()) {
                a.move();
            }
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
            	
            	runner.setMovement(false);
            	prevStuck = 2;
            }
            else if (prevStuck < 1) {
            	
            	runner.setMovement(true);
            }
        }
        
        for (PlaneLeft plnLeft : planesLeft) {
            
            Rectangle plnLftBnds = plnLeft.getBounds();

            if (rnrBnds.intersects(plnLftBnds)) {
            	
            	runner.setMovement(false);
                prevStuck = 2;
            }
            else if (prevStuck < 1) {
            	
            	runner.setMovement(true);
            }
        }
        
        for (Obstacle obst : obstacles) {
            
            Rectangle obstBnds = obst.getBounds();

            if (rnrBnds.intersects(obstBnds)) {
            	
            	runner.setMovement(false);
                prevStuck = 2;
                for (Invincibility invin : invinPowerups) {
                	
                	if (invin.isActive()) {
                		
                		obst.setVisible(false);
                		runner.setMovement(true);
                	}
                }
            	//TODO if invincibility on, obstacle.remove(obstacle) + setVisible(false)?
            	//obstacle.setVisible(false);
                
            }
            else if (prevStuck < 1) {
            	
            	runner.setMovement(true);
            }
        }
        
        for (Halt hpu : haltPowerups) {
        	
        	Rectangle hpuBnds = hpu.getBounds();
        	
        	if (rnrBnds.intersects(hpuBnds)) {
				if(hpu.isVisible()) {
					hpu.setVisible(false);
					hpu.setActive(true);
					gameDelay = System.currentTimeMillis();
				}
    		}
        }
        
        for (Invincibility invin : invinPowerups) {
        	
        	Rectangle invinBnds = invin.getBounds();
        	
        	if (rnrBnds.intersects(invinBnds)) {
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
	 * @return the pos
	 */
	public int[][] getPos() {
		return posPlnsRt;
	}

	/**
	 * @return the posLeft
	 */
	public int[][] getPosLeft() {
		return posPlnsLft;
	}

	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
    
}
