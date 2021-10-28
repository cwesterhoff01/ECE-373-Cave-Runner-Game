package org.Group7_FinalProject.Framework;

import javax.swing.*;

//GameScreen is an abstract class that should be implemented by individual screen types
public abstract class GameScreen extends JPanel {
	
	//Fields for a GameScreen
	protected Window window;

	//Default constructor
	public GameScreen(Window w) {
		
		//Every GameScreen belongs to a Window
		this.window = w;
		
	}

}
