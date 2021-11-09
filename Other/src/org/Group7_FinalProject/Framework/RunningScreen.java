package org.Group7_FinalProject.Framework;

import java.awt.Color;
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
	private List<Obstacle> obstacles;
	private List<ObstacleLeft> obstaclesLeft;
	private boolean runnerDead;
	private int prevStuck;
	private Integer depth;
    
    private final int[][] pos = { // spawn positions
        {450, 250}, {450, 750}
    };
    private final int[][] posLeft = { // spawn positions
            {0, 500} //, {0, 0}
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
        runner = new Runner(0, 0);
        ceiling = new Ceiling(0, 0);
        obstacles = new ArrayList<>();
        obstaclesLeft = new ArrayList<>();
        depth = 0;
        
        //The timer handles animation with ActionListener, the KeyListener handles keyboard input
        timer = new Timer(15, this);
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
		obstacles.clear();
		obstaclesLeft.clear();
		
		//Hide the runner
		runner.setVisible(false);
		
	}
	
	//Method that initializes the sprites in their starting locations
	private void initSprites() {

        for (int[] p : pos) {
            obstacles.add(new Obstacle(p[0], p[1]));
        }
        for (int[] p : posLeft) {
            obstaclesLeft.add(new ObstacleLeft(p[0], p[1]));
        }
        
        //Place the runner in the upper left hand corner
        runner.setPosition(40, 60);
        runner.setVisible(true);
        
    }

	//Method that draws all visible sprite objects on the screen
    private void drawObjects(Graphics g) {

        if (runner.isVisible()) {
            g.drawImage(runner.getImage(), runner.getX(), runner.getY(), this);
        }
        if (ceiling.isVisible()) {
            g.drawImage(ceiling.getImage(), ceiling.getX(), ceiling.getY(), this);
        }
        
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isVisible()) {
                g.drawImage(obstacle.getImage(), obstacle.getX(), obstacle.getY(), this);
            }
        }
        
        for (ObstacleLeft obstacleLeft : obstaclesLeft) {
            if (obstacleLeft.isVisible()) {
                g.drawImage(obstacleLeft.getImage(), obstacleLeft.getX(), obstacleLeft.getY(), this);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Depth: " + depth.toString(), 5, 15);
        
    }

    //Method that draws the "Game Over" message on the screen
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
        updateRunner();
        updateCeiling();
        updateObstacles();
        updateObstaclesLeft();
        checkCollisions();
        repaint();
        
    	depth = depth + 1;
        prevStuck -= 1;
        
    }
    
    //These three methods implement the KeyListener
	@Override
	public void keyTyped(KeyEvent e) {
		//TO DO
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

    private void updateRunner() {
        if (runner.isVisible())
            runner.move();
    }
    
    private void updateCeiling() {
        if (ceiling.isVisible())
            ceiling.move();
    }

	private void updateObstacles() {

        for (int i = 0; i < obstacles.size(); i++) {

            Obstacle a = obstacles.get(i);
            
            if (a.isVisible()) {
                a.move();
            } else {
                obstacles.remove(i);
            }
        }
        
    }
	
	private void updateObstaclesLeft() {

        for (int i = 0; i < obstaclesLeft.size(); i++) {

            ObstacleLeft a = obstaclesLeft.get(i);
            
            if (a.isVisible()) {
                a.move();
            } else {
                obstaclesLeft.remove(i);
            }
        }
        
    }

    public void checkCollisions() {

        Rectangle r3 = runner.getBounds();
        Rectangle r4 = ceiling.getBounds();
        
        if (r3.intersects(r4)) {
        	
        	runnerDead = true;
        	
        }
        
        for (Obstacle obstacle : obstacles) {
            
            Rectangle r2 = obstacle.getBounds();

            if (r3.intersects(r2)) {
            	
            	runner.setMovement(false);
            	prevStuck = 2;
            	//obstacle.setVisible(false);
            }
            else if (prevStuck < 1) {
            	runner.setMovement(true);
            }
        }
        
        for (ObstacleLeft obstacleLeft : obstaclesLeft) {
            
            Rectangle r2 = obstacleLeft.getBounds();

            if (r3.intersects(r2)) {
            	
            	runner.setMovement(false);
                prevStuck = 2;
            	//obstacle.setVisible(false);
            }
            else if (prevStuck < 1) {
            	runner.setMovement(true);
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
	public List<Obstacle> getObstacles() {
		return obstacles;
	}

	/**
	 * @param obstacles the obstacles to set
	 */
	public void setObstacles(List<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}

	/**
	 * @return the obstaclesLeft
	 */
	public List<ObstacleLeft> getObstaclesLeft() {
		return obstaclesLeft;
	}

	/**
	 * @param obstaclesLeft the obstaclesLeft to set
	 */
	public void setObstaclesLeft(List<ObstacleLeft> obstaclesLeft) {
		this.obstaclesLeft = obstaclesLeft;
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
		return pos;
	}

	/**
	 * @return the posLeft
	 */
	public int[][] getPosLeft() {
		return posLeft;
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