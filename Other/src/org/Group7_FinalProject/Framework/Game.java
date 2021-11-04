package org.Group7_FinalProject.Framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.Group7_FinalProject.Utilities.Account;


//Game class contains and controls all objects related to a Game
public class Game {
	
	//Fields for a Game
	private Window gameWindow;
	private ArrayList<Account> gameAccounts;
	private Account currAccount;

	//Default no-arg constructor
	public Game() {
		
		this.gameAccounts = new ArrayList<Account>();
		
		//Load in accounts from txt file here
		this.loadAccounts();
		//Open the game with the guest account logged in
		this.currAccount = this.gameAccounts.get(0);
		
		//Create a new game window associated with this Game
		this.gameWindow = new Window(this, 1400, 850);
		
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
		//TO DO: Fill out running screen tasks
	}
	
	//Method that loads in accounts from a txt file
	private void loadAccounts() {
		
		try {
			File accData = new File("src/resources/account_data.txt");
			Scanner scanner = new Scanner(accData);
			while (scanner.hasNextLine()) {
				Account acc = new Account();
				ArrayList<Integer> accScores= new ArrayList<Integer>();
				String data = scanner.nextLine();
				acc.setName(data);
				while(scanner.hasNextInt()) {
					Integer score = scanner.nextInt();
					accScores.add(score);
				}
				String garbage = scanner.nextLine();  //Get rid of newline character
				acc.setHighscores(accScores);
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
				for(Integer i : acc.getHighscores()) {
					writer.print(i + " ");
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
