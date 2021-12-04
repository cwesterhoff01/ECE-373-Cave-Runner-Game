package org.Group7_FinalProject.Utilities;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Music {
	
	//Fields for a Music
	String musicLocation;
	File musicFile;
	AudioInputStream musicInputStream;
	Clip musicClip;
	
	//Constructor that requires one argument
	public Music(String filepath) {
		
		this.musicLocation = filepath;
		this.musicFile = new File(musicLocation);

	}
	
	//Method that plays the Music
	public void play() {
		try {
			if (musicFile.exists()) {
				this.musicInputStream = AudioSystem.getAudioInputStream(musicFile);
				this.musicClip = AudioSystem.getClip();
				this.musicClip.open(musicInputStream);
				this.musicClip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}