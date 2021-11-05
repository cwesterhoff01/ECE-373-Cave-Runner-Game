package RunnerExample;


import javax.swing.*;

//GameScreen is a parent class that should be implemented by individual screen types
public class GameScreen extends JPanel {
	
	//Fields for a GameScreen
	protected JFrame window;
	
	//Default no-arg constructor
	public GameScreen() {
		this(new JFrame());
	}

	//Constructor that requires one argument
	public GameScreen(JFrame w) {
		
		//For clarity
		super();
		//Every GameScreen belongs to a Window
		this.window = w;
		
	}

}
