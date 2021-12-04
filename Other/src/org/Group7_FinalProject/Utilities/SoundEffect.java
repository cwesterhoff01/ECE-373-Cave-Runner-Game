package org.Group7_FinalProject.Utilities;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
	
	//Fields for a SoundEffect
	String soundLocation;
	File soundFile;
	AudioInputStream soundInputStream;
	Clip soundClip;
	
	//Constructor that requires one argument
	public SoundEffect(String filepath) {
		
		this.soundLocation = filepath;
		this.soundFile = new File(soundLocation);
		
	}
	
	//Method that plays the sound effect
	public void play() {
		try {
			if (this.soundFile.exists()) {
				this.soundInputStream = AudioSystem.getAudioInputStream(soundFile);
				this.soundClip = AudioSystem.getClip();
				this.soundClip.open(this.soundInputStream);
				this.soundClip.loop(0);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
