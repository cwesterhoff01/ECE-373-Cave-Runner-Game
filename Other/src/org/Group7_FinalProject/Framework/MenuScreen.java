package org.Group7_FinalProject.Framework;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.*;

import org.Group7_FinalProject.Runner.Difficulty;

public final class MenuScreen extends GameScreen {
	
	//Fields for the Menu Screen
	private JButton accountbtn;
	private JButton highscorebtn;
	private JButton runningbtn;
	private JButton exitbtn;
	
	//Default no-arg constructor
	public MenuScreen() {
		this(new Window());
	}

	//Constructor that requires one argument
	public MenuScreen(Window w) {
		
		super(w);
		
		setBackground(Color.GREEN);
				
		//Create a button to go to the account screen
		this.accountbtn = new JButton("Change Account");
		this.accountbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setCurrentScreen(w.getGameScreenList().get("Account Screen"));
			}
		});
		add(accountbtn, BorderLayout.SOUTH);
		
		//Create a button to go to the highscore screen
		this.highscorebtn = new JButton("View Highscores");
		this.highscorebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setCurrentScreen(w.getGameScreenList().get("Highscore Screen"));
			}
		});
		add(highscorebtn, BorderLayout.NORTH);
		
		//Create a button to go to the running screen
		this.runningbtn = new JButton("Start Game");
		this.runningbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setCurrentScreen(w.getGameScreenList().get("Running Screen"));
			}
		});
		add(runningbtn, BorderLayout.CENTER);
		
		//Create a button to exit the game
		this.exitbtn = new JButton("Exit");
		this.exitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Confirm the user's choice of action with a yes/no popup
				int result = JOptionPane.showConfirmDialog(window, "Are you sure you want to exit the game?", "Exit Cave Runner", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					window.getGame().terminate();
				}
			}
		});
		add(exitbtn, BorderLayout.WEST);
		
	}

}
