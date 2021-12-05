package org.Group7_FinalProject.Framework;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import org.Group7_FinalProject.Utilities.SoundEffect;

/*
 * The GameScreen class includes all fields and methods related to a GameScreen
 * The GameScreen class is expected to be implemented by particular subclasses
 */
public class GameScreen extends JPanel {
	
	//Fields for a GameScreen
	protected Window window;
    private Image backgroundImage;
    protected SoundEffect buttonClick;
    protected SoundEffect error;
	
	//Default no-arg constructor
	public GameScreen() {
		this(new Window());
	}

	//Constructor that requires one argument
	public GameScreen(Window w) {
		this(w, new ImageIcon());
	}
	
	//Constructor that requires two arguments
	public GameScreen(Window w, ImageIcon ii) {
		
  		super();
		this.window = w;
		this.backgroundImage = ii.getImage();
		this.buttonClick = new SoundEffect("src/resources/buttonclick.wav");
		this.error = new SoundEffect("src/resources/error.wav");
		
	}
	
	//Every GameScreen draws a background image
	@Override
    public void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);
    	g.drawImage(this.backgroundImage, 0, 0, this);
    	
    }

	/**
	 * @return the window
	 */
	public Window getWindow() {
		return window;
	}

	/**
	 * @param window the window to set
	 */
	public void setWindow(Window window) {
		this.window = window;
	}

	/**
	 * @return the backgroundImage
	 */
	public Image getBackgroundImage() {
		return backgroundImage;
	}

	/**
	 * @param backgroundImage the backgroundImage to set
	 */
	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	/**
	 * @return the buttonClick
	 */
	public SoundEffect getButtonClick() {
		return buttonClick;
	}

	/**
	 * @param buttonClick the buttonClick to set
	 */
	public void setButtonClick(SoundEffect buttonClick) {
		this.buttonClick = buttonClick;
	}

	/**
	 * @return the error
	 */
	public SoundEffect getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(SoundEffect error) {
		this.error = error;
	}
}
