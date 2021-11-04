package org.Group7_FinalProject.Framework;

import java.awt.EventQueue;
import java.util.Timer;

import org.Group7_FinalProject.Runner.Difficulty;
import org.Group7_FinalProject.Runner.MovingSpriteEx;

//Main driver class creates a game and runs it
public class Main {

	public static void main(String[] args) {
		
		System.out.println("Program Starting.");
		
		//Create a new game
		Game game1 = new Game();
		//Run the game
		game1.runGame();
		
		/* EventQueue.invokeLater(() -> {
            game1.enterGameModus();
        });
		
		
        Timer timer = new Timer();
    	timer.schedule(new Difficulty(), 0, 5000); */
		
		
	}

}
