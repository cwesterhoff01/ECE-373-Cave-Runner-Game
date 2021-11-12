package org.Group7_FinalProject.Runner;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import org.Group7_FinalProject.Framework.RunningScreen;

//Sprite class contains all methods and fields related to a Sprite
public class Sprite {

	//Fields for a Sprite
	protected RunningScreen runningScreen;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;
    protected static int DIFFICULTY = 0;

    //Constructor that requires two arguments
    public Sprite(int x, int y, RunningScreen runningScreen) {

    	//Every sprite belongs to a runningScreen
    	this.runningScreen = runningScreen;
        this.x = x;
        this.y = y;
        visible = true;
        
    }
    
    public static void resetDifficulty() {
    	DIFFICULTY = 0;
    }
    
    public static void changeDifficulty() {
    	DIFFICULTY += 1;
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
        
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
        
    }

    public Image getImage() {
        return image;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setPosition(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
}