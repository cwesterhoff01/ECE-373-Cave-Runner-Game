package org.Group7_FinalProject.Framework;

import javax.swing.*;

//GameScreen is an abstract class that should be implemented by individual screen types
public abstract class GameScreen extends JPanel {
	
	//Fields for a GameScreen
	protected Window window;
	
	//Default no-arg constructor
	public GameScreen() {
		this(new Window());
	}

	//Constructor that requires one argument
	public GameScreen(Window w) {
		
		//For clarity
		super();
		//Every GameScreen belongs to a Window
		this.window = w;
		
	}

}
