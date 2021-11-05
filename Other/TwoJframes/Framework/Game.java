package org.Group7_FinalProject.Framework;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JFrame;

import org.Group7_FinalProject.Runner.Board;
import org.Group7_FinalProject.Runner.Difficulty;
import org.Group7_FinalProject.Runner.MovingSpriteEx;
import org.Group7_FinalProject.Utilities.Account;

//Game class contains and controls all objects related to a Game
public class Game extends JFrame {
	
	//Fields for a Game
	private Window gameWindow;
	private ArrayList<Account> gameAccounts;
	private Account currAccount;

	//Default no-arg constructor
	public Game() {
		
		this.gameAccounts = new ArrayList<Account>();
		//TO DO: Load in accounts from txt file here
		this.gameAccounts.add(new Account("guest"));  //FIX ME: Should there be a guest?
		
		//Open the game with the guest account logged in
		this.currAccount = this.gameAccounts.get(0);
		
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

	/**
	 * @return the gameAccounts
	 */
	public ArrayList<Account> getGameAccounts() {
		return gameAccounts;
	}

	/**
	 * @param gameAccounts the gameAccounts to set
	 */
	public void setGameAccounts(ArrayList<Account> gameAccounts) {
		this.gameAccounts = gameAccounts;
	}

	/**
	 * @return the currAccount
	 */
	public Account getCurrAccount() {
		return currAccount;
	}

	/**
	 * @param currAccount the currAccount to set
	 */
	public void setCurrAccount(Account currAccount) {
		this.currAccount = currAccount;
	}

	//Main method for controlling the game
	public void runGame() {
		boolean loop = true;
		//Infinite while loop runs until the program termination signal is sent
		while(loop) {
//possible change the condition for while loop to a variable			
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
			/* else if (gameWindow.getCurrentScreen() == gameWindow.getGameScreenList().get("Running Screen")) {
				gameWindow.getCl().show(gameWindow.getMainPanel(), "Running Screen");
				this.updateRunningScreen();
			} */
			else if (gameWindow.getCurrentScreen() == gameWindow.getGameScreenList().get("Running Screen")) {
				loop = false;
				gameWindow.setVisible(false);
			}
			//In case the program falls off the rails, send the termination signal
			else {
				this.terminate();
			}
			
		}
		//enterGameModus();
		EventQueue.invokeLater(() -> {
            //gameWindow.enterGame();
			enterGameModus();
        });
        
        Timer timer = new Timer();
    	timer.schedule(new Difficulty(), 0, 5000);
		
	}
	
	public void enterGameModus() {
		//gameWindow.enterGame();
		add(new Board());

        setTitle("Moving sprite");
        setSize(1400, 850);
        setVisible(true);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
