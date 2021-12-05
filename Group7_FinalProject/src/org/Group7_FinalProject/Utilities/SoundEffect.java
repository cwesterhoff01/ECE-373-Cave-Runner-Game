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

	/**
	 * @return the soundLocation
	 */
	public String getSoundLocation() {
		return soundLocation;
	}

	/**
	 * @param soundLocation the soundLocation to set
	 */
	public void setSoundLocation(String soundLocation) {
		this.soundLocation = soundLocation;
	}

	/**
	 * @return the soundFile
	 */
	public File getSoundFile() {
		return soundFile;
	}

	/**
	 * @param soundFile the soundFile to set
	 */
	public void setSoundFile(File soundFile) {
		this.soundFile = soundFile;
	}

	/**
	 * @return the soundInputStream
	 */
	public AudioInputStream getSoundInputStream() {
		return soundInputStream;
	}

	/**
	 * @param soundInputStream the soundInputStream to set
	 */
	public void setSoundInputStream(AudioInputStream soundInputStream) {
		this.soundInputStream = soundInputStream;
	}

	/**
	 * @return the soundClip
	 */
	public Clip getSoundClip() {
		return soundClip;
	}

	/**
	 * @param soundClip the soundClip to set
	 */
	public void setSoundClip(Clip soundClip) {
		this.soundClip = soundClip;
	}
	
}
