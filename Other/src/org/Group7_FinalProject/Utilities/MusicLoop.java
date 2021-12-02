package org.Group7_FinalProject.Utilities;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class MusicLoop {
	
	public void playMusic(String musicLocation) {
		try {
			File musicPath = new File(musicLocation);
			if(musicPath.exists()) { //Checking if file exists
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(clip.LOOP_CONTINUOUSLY);
			}else {
				System.out.println("Can't find file location");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
