package org.Group7_FinalProject.Framework;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HighscoreScreen extends GameScreen {

	//Fields for the Highscore Screen
	private JButton menubtn;
	
	//Default no-arg constructor
	public HighscoreScreen() {
		this(new Window());
	}
	
	//Constructor that requires one argument
	public HighscoreScreen(Window w) {
		
		super(w);
		
		setBackground(Color.RED);
		
		//Create a menu button
		this.menubtn = new JButton("Menu");
		this.menubtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setCurrentScreen(w.getGameScreenList().get("Menu Screen"));
			}
		});
		add(menubtn, BorderLayout.SOUTH);
		
	}

}
