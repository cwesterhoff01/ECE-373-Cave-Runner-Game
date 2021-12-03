package org.Group7_FinalProject.Framework;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

/*
 * Main class is the driver
 * It creates an instance of a the Game class, then runs that instance
 */
public class Main {

	public static void main(String[] args) {
		
		//Do a splash screen
		JWindow window = new JWindow();
		window.getContentPane().add(
			    new JLabel("", new ImageIcon("src/resources/Welcome.jpeg"), SwingConstants.CENTER));
		window.setBounds(500, 150, 500, 300);
		window.setVisible(true);
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		window.setVisible(false);
		
		//Create a new game
		Game game1 = new Game();
		//Run the game
		game1.runGame();
		
	}

}
