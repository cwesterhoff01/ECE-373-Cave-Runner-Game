package org.Group7_FinalProject.Framework;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuScreen extends GameScreen {
	
	JButton accountbtn;
	JButton highscorebtn;
	JButton runningbtn;
	JButton exitbtn;

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
				window.getGame().terminate();
			}
		});
		add(exitbtn, BorderLayout.WEST);
		
	}

}
