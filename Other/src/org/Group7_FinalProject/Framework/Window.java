package org.Group7_FinalProject.Framework;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.*;

//Window class contains and controls all objects related to a Window
public class Window {

	//Fields for a Window
	private Game game;
	private JFrame frame;
	private JPanel mainPanel;
   	private CardLayout cl;
   	private HashMap<String, GameScreen> gameScreenList;
   	private GameScreen currentScreen;

   	//Constructor requires a few parameters
   	public Window(Game g, Integer width, Integer height) {
	   
		//Every window belongs to a Game
		this.game = g;
		//Initialize the list of GameScreens
		this.gameScreenList = new HashMap<String, GameScreen>();
		
		//Initialize JFrame
		this.frame = new JFrame("Cave Runner");
		this.frame.setSize(width, height);
		  
		//Initialize GameScreens
		this.mainPanel = new JPanel();
		gameScreenList.put("Menu Screen", new MenuScreen(this));
		gameScreenList.put("Account Screen", new AccountScreen(this));
		gameScreenList.put("Highscore Screen", new HighscoreScreen(this));
		gameScreenList.put("Running Screen", new RunningScreen(this));
		
		//Create the card layout
		this.cl = new CardLayout();
		this.mainPanel.setLayout(cl);
		  
		//Add game screens to the card layout
		this.mainPanel.add(gameScreenList.get("Menu Screen"), "Menu Screen");
		this.mainPanel.add(gameScreenList.get("Account Screen"), "Account Screen");
		this.mainPanel.add(gameScreenList.get("Highscore Screen"), "Highscore Screen");
		this.mainPanel.add(gameScreenList.get("Running Screen"), "Running Screen");
		  
		//Start by showing the account screen
		this.currentScreen = gameScreenList.get("Account Screen");
		
		//Setup and show the JFrame
		this.frame.add(mainPanel);
		this.frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                game.terminate();
            }
        });
		this.frame.setVisible(true);
		
   	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * @return the mainPanel
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * @param mainPanel the mainPanel to set
	 */
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	/**
	 * @return the cl
	 */
	public CardLayout getCl() {
		return cl;
	}

	/**
	 * @param cl the cl to set
	 */
	public void setCl(CardLayout cl) {
		this.cl = cl;
	}

	/**
	 * @return the gameScreenList
	 */
	public HashMap<String, GameScreen> getGameScreenList() {
		return gameScreenList;
	}

	/**
	 * @param gameScreenList the gameScreenList to set
	 */
	public void setGameScreenList(HashMap<String, GameScreen> gameScreenList) {
		this.gameScreenList = gameScreenList;
	}

	/**
	 * @return the currentScreen
	 */
	public GameScreen getCurrentScreen() {
		return currentScreen;
	}

	/**
	 * @param currentScreen the currentScreen to set
	 */
	public void setCurrentScreen(GameScreen currentScreen) {
		this.currentScreen = currentScreen;
	}


}