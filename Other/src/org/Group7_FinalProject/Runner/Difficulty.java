package org.Group7_FinalProject.Runner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Difficulty implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Sprite.changeDifficulty();
		
	}
    
}