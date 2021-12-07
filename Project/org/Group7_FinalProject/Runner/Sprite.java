package org.Group7_FinalProject.Runner;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import org.Group7_FinalProject.Framework.Main;
import org.Group7_FinalProject.Framework.RunningScreen;

//Sprite class contains all methods and fields related to a Sprite
public abstract class Sprite {

	//Fields for a Sprite
    protected static int DIFFICULTY = 0;
    protected static int MAX_DIFFICULTY = 4;
	protected static int LAST_DIFFICULTY = 0;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;
    public static enum CollisionPosition { TOP, BOTTOM, LEFT, RIGHT, NONE };

    //Constructor that requires two arguments
    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
        
    }
    
    //Method that resets the speed at which a sprite moves
    public static void setDifficulty(Integer DIF) {
    	DIFFICULTY = DIF;
    }
    
    //Method that increases the speed at which a sprite moves
    public static void incrementDifficulty() {
    	if (DIFFICULTY < MAX_DIFFICULTY)
    		DIFFICULTY += 1;
    }
    
    //Getter for static DIFFICULTY value
    public static Integer getDifficulty() {
    	return DIFFICULTY;
    }
    
    //Method that checks if two sprites overlap each other on the screen
    public static boolean isCollided(Sprite s1, Sprite s2) {
    	Rectangle s1bounds = s1.getBounds();
    	Rectangle s2bounds = s2.getBounds();
    	
    	if (s1bounds.intersects(s2bounds)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /*
     * Method that determines the relative location of two sprites when they collide
     * Note that this method assumes that a collision actually exists, so it should
     * only be called after verifying a collision using the isCollided() method
     * Otherwise, the method will return NONE
     */
    public static CollisionPosition getCollisionPosition(Sprite s1, Sprite s2) {
    	
    	if ((s1.getY() + s1.getHeight() > s2.getY() + 12) && (s1.getY() < s2.getY() + s2.getHeight() - 12)) {
    		if (s1.getX() < s2.getX()) {
	    		return CollisionPosition.LEFT;
    		}
    		else {
    			return CollisionPosition.RIGHT;
    		}
    	}
    	else if ((s1.getX() + s1.getWidth() > s2.getX() + 12) && (s1.getX() < s2.getX() + s2.getWidth() - 12)) {
    		if (s1.getY() < s2.getY()) {
    			return CollisionPosition.TOP;
    		}
    		else {
    			return CollisionPosition.BOTTOM;
    		}
    	}
    	else {
    		return CollisionPosition.NONE;
    	}
    	
    }
    
    protected abstract void move();

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(Main.class.getResource(imageName));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        
    }
    
    public void setPosition(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

	/**
	 * @return the dIFFICULTY
	 */
	public static int getDIFFICULTY() {
		return DIFFICULTY;
	}

	/**
	 * @param dIFFICULTY the dIFFICULTY to set
	 */
	public static void setDIFFICULTY(int dIFFICULTY) {
		DIFFICULTY = dIFFICULTY;
	}

	/**
	 * @return the mAX_DIFFICULTY
	 */
	public static int getMAX_DIFFICULTY() {
		return MAX_DIFFICULTY;
	}

	/**
	 * @param mAX_DIFFICULTY the mAX_DIFFICULTY to set
	 */
	public static void setMAX_DIFFICULTY(int mAX_DIFFICULTY) {
		MAX_DIFFICULTY = mAX_DIFFICULTY;
	}

	/**
	 * @return the lAST_DIFFICULTY
	 */
	public static int getLAST_DIFFICULTY() {
		return LAST_DIFFICULTY;
	}

	/**
	 * @param lAST_DIFFICULTY the lAST_DIFFICULTY to set
	 */
	public static void setLAST_DIFFICULTY(int lAST_DIFFICULTY) {
		LAST_DIFFICULTY = lAST_DIFFICULTY;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}
    
}