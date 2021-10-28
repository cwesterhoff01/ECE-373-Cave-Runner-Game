package org.Group7_FinalProject.Framework;

//Game class contains and controls all objects related to a Game
public class Game {
	
	//Fields for a Game
	private Window gameWindow;

	//Default constructor
	public Game() {
		
		//Create a new game window associated with this Game
		this.gameWindow = new Window(this, 700, 500);
		
	}
	
	/**
	 * @return the gameWindow
	 */
	public Window getGameWindow() {
		return gameWindow;
	}

	/**
	 * @param gameWindow the gameWindow to set
	 */
	public void setGameWindow(Window gameWindow) {
		this.gameWindow = gameWindow;
	}

	//Main method for controlling the game
	public void runGame() {
		
		//Infinite while loop runs until the program termination signal is sent
		while(true) {
			
			//Display the appropriate screen and call the appropriate update function based on currentScreen
			if (gameWindow.getCurrentScreen() == gameWindow.getGameScreenList().get("Menu Screen")) {
				gameWindow.getCl().show(gameWindow.getMainPanel(), "Menu Screen");
				this.updateMenuScreen();
			}
			else if (gameWindow.getCurrentScreen() == gameWindow.getGameScreenList().get("Account Screen")) {
				gameWindow.getCl().show(gameWindow.getMainPanel(), "Account Screen");
				this.updateAccountScreen();
			}
			else if (gameWindow.getCurrentScreen() == gameWindow.getGameScreenList().get("Highscore Screen")) {
				gameWindow.getCl().show(gameWindow.getMainPanel(), "Highscore Screen");
				this.updateHighscoreScreen();
			}
			else if (gameWindow.getCurrentScreen() == gameWindow.getGameScreenList().get("Running Screen")) {
				gameWindow.getCl().show(gameWindow.getMainPanel(), "Running Screen");
				this.updateRunningScreen();
			}
			//In case the program falls off the rails, send the termination signal
			else {
				this.terminate();
			}
			
		}
		
	}
	
	private void updateMenuScreen() {
		//TO DO: Fill out menu screen tasks
	}
	
	private void updateAccountScreen() {
		//TO DO: Fill out account screen tasks
	}
	
	private void updateHighscoreScreen() {
		//TO DO: Fill out highscore screen tasks
	}
	
	private void updateRunningScreen() {
		//TO DO: Fill out running screen tasks
	}
	
	//Method that sends the termination signal to the program
	public void terminate() {
		System.exit(0);
	}

}
