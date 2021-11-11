package org.Group7_FinalProject.Framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Timer;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import org.Group7_FinalProject.Utilities.Account;

import org.Group7_FinalProject.Runner.*;


//Game class contains and controls all objects related to a Game
public class Game {
	
	//Fields for a Game
	private Window gameWindow;
	private ArrayList<Account> gameAccounts;
	private Account currAccount;

	//Default no-arg constructor
	public Game() {
		
		//Load in accounts from txt file here
		this.gameAccounts = new ArrayList<Account>();
		this.loadAccounts();
		
		//Open the game with the guest account logged in
		this.currAccount = this.gameAccounts.get(0);
		
		//Create a new game window associated with this Game
		this.gameWindow = new Window(this, 1200, 860);
		
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
		
		//Start the runner game
		((RunningScreen)gameWindow.getGameScreenList().get("Running Screen")).startRunning();
    	
    	//Continue running until the player dies
    	while(((RunningScreen)gameWindow.getGameScreenList().get("Running Screen")).isRunnerDead() == false);
    	if((((RunningScreen)gameWindow.getGameScreenList().get("Running Screen")).getRunner().getIsPaused())) {//Game was paused go back to menu screen
    		gameWindow.setCurrentScreen(gameWindow.getGameScreenList().get("Menu Screen"));
    	}
    	else {//runner died normally
			//If the current account has set a new highscore, record it
	    	Integer depth = ((RunningScreen)gameWindow.getGameScreenList().get("Running Screen")).getDepth();
			boolean newHighscore = false;
			for (Integer s : gameWindow.getGame().getCurrAccount().getHighscores()) {
				if (depth > s) {
					gameWindow.getGame().getCurrAccount().getHighscores().add(depth);
					Collections.sort(gameWindow.getGame().getCurrAccount().getHighscores());
					Collections.reverse(gameWindow.getGame().getCurrAccount().getHighscores());
					newHighscore = true;
					break;
				}
			}
			
			//Give the user the option to return to the Menu Screen or play again
			int result;
			if (newHighscore == false)
				result = JOptionPane.showConfirmDialog(gameWindow, "Ouch! You died at a depth of " + depth.toString() + "ft. Do you wish to play again?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			else
				result = JOptionPane.showConfirmDialog(gameWindow, "Ouch! You died at a depth of " + depth.toString() + "ft.\n Congratulations, this is a new top 10 score for your account!\n Do you wish to play again?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				gameWindow.setCurrentScreen(gameWindow.getGameScreenList().get("Running Screen"));
			}
			else {
				gameWindow.setCurrentScreen(gameWindow.getGameScreenList().get("Menu Screen"));
			}
    	}
		
	}
	
	//Method that loads in accounts from a txt file
	private void loadAccounts() {
		
		try {
			File accData = new File("src/resources/account_data.txt");
			Scanner scanner = new Scanner(accData);
			while (scanner.hasNextLine()) {
				ArrayList<Integer> accScores= new ArrayList<Integer>();
				String name = scanner.nextLine();
				while(scanner.hasNextInt()) {
					Integer score = scanner.nextInt();
					accScores.add(score);
				}
				String garbage = scanner.nextLine();  //Get rid of newline character
				Account acc = new Account(name, accScores);
				gameAccounts.add(acc);
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			System.out.println("An error occurred in loading the account data");
			e.printStackTrace();
		}
		
	}
	
	//Method that saves accounts to a txt file
	private void saveAccounts() {
		
		try {
			File accData = new File("src/resources/account_data.txt");
			PrintWriter writer = new PrintWriter(accData);
			for(Account acc : gameAccounts) {
				writer.println(acc.getName());
				int count = 0;
				for(Integer i : acc.getHighscores()) {
					if(count < 10) {
					writer.print(i.toString() + " ");
					}
					else {
						break;
					}
					count++;
				}
				writer.println();
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occured in saving the account data");
			e.printStackTrace();
		}
		
	}
	
	//Method that sends the termination signal to the program
	public void terminate() {
		this.saveAccounts();
		System.exit(0);
	}

}