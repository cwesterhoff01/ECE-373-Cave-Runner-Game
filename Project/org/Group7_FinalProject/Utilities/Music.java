package org.Group7_FinalProject.Utilities;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import org.Group7_FinalProject.Framework.Main;

public class Music {
	
	//Fields for a Music
	String musicLocation;
	File musicFile;
	AudioInputStream musicInputStream;
	Clip musicClip;
	Long currentPos;
	
	//Constructor that requires one argument
	public Music(String filepath) {
		this.musicLocation = filepath;
		
	}
	
	//Method that plays the Music
	public void play() {
		URL url = this.getClass().getResource(musicLocation);
			try {
				this.musicInputStream = AudioSystem.getAudioInputStream(url);
				this.musicClip = AudioSystem.getClip();
				this.musicClip.open(musicInputStream);
				this.musicClip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public void pause() {
		this.currentPos = this.musicClip.getMicrosecondPosition();
        musicClip.stop();	
	}
	
	public void resume() {
		try {
			if (musicFile.exists()) {
				musicClip.close();
				this.musicInputStream = AudioSystem.getAudioInputStream(musicFile);
				this.musicClip.open(musicInputStream);
				this.musicClip.loop(Clip.LOOP_CONTINUOUSLY);
				musicClip.setMicrosecondPosition(currentPos);
				musicClip.start();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			if (musicFile.exists()) {
				this.musicClip.stop();
				this.musicClip.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the musicLocation
	 */
	public String getMusicLocation() {
		return musicLocation;
	}

	/**
	 * @param musicLocation the musicLocation to set
	 */
	public void setMusicLocation(String musicLocation) {
		this.musicLocation = musicLocation;
	}

	/**
	 * @return the musicFile
	 */
	public File getMusicFile() {
		return musicFile;
	}

	/**
	 * @param musicFile the musicFile to set
	 */
	public void setMusicFile(File musicFile) {
		this.musicFile = musicFile;
	}

	/**
	 * @return the musicInputStream
	 */
	public AudioInputStream getMusicInputStream() {
		return musicInputStream;
	}

	/**
	 * @param musicInputStream the musicInputStream to set
	 */
	public void setMusicInputStream(AudioInputStream musicInputStream) {
		this.musicInputStream = musicInputStream;
	}

	/**
	 * @return the musicClip
	 */
	public Clip getMusicClip() {
		return musicClip;
	}

	/**
	 * @param musicClip the musicClip to set
	 */
	public void setMusicClip(Clip musicClip) {
		this.musicClip = musicClip;
	}
	
}