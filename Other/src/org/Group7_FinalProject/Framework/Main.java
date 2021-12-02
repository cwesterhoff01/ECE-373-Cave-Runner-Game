package org.Group7_FinalProject.Framework;
import org.Group7_FinalProject.Utilities.MusicLoop;
/*
 * Main class is the driver
 * It creates an instance of a the Game class, then runs that instance
 */
public class Main {

	public static void main(String[] args) {
		//How to get music file
		// Go to https://savenow.to/en/youtube-wav-converter.html
		// and put in a youtube link - https://www.youtube.com/watch?v=-bTpp8PQSog
		//Play music
		String filepath = "src/resources/Indiana Jones Theme Song [HD].wav";
		MusicLoop musicObject = new MusicLoop();
		musicObject.playMusic(filepath);
		
		//Create a new game
		Game game1 = new Game();
		//Run the game
		game1.runGame();
		
	}

}
