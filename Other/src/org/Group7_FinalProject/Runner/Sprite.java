package org.Group7_FinalProject.Runner;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

//Sprite class contains all methods and fields related to a Sprite
public class Sprite {

	//Fields for a Sprite
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected static double DIFFICULTY = 1.0;
    protected boolean visible;
    protected Image image;

    //Constructor that requires two arguments
    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
        
    }

    public static double changeDifficulty() {
    	DIFFICULTY += 0.1; //TODO uncomment
    	return DIFFICULTY;
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