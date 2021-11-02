package org.Group7_FinalProject.Utilities;

import java.util.ArrayList;
import java.util.Collections;

//Account class contains and controls all objects related to an Account
public class Account {
	
	//Fields for an Account
	private String name;
	private ArrayList<Integer> highscores;

	//Default no-arg constructor
	public Account() {
		this("unknown");
	}
	
	//Constructor that accepts one argument
	public Account(String name) {
		this(name, new ArrayList<Integer>());
	}
	
	//Constructor that accepts two arguments
	public Account(String name, ArrayList<Integer> highscores) {
		
		this.name = name;
		this.highscores = highscores;
		//The highscores for an account should always be sorted highest to lowest
		Collections.sort(this.highscores);
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the highscores
	 */
	public ArrayList<Integer> getHighscores() {
		return highscores;
	}

	/**
	 * @param highscores the highscores to set
	 */
	public void setHighscores(ArrayList<Integer> highscores) {
		this.highscores = highscores;
	}
	
	//Method to check if two accounts are equal by comparing names
	public Boolean equals(Account acct) {
		if (this.name == acct.getName()) {
			return true;
		}
		else {
			return false;
		}
	}

}
