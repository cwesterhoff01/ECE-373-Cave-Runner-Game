package org.Group7_FinalProject.Framework;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AccountScreen extends GameScreen {
	
	//Fields for the Account Screen
	JButton menubtn;
	
	//Default no-arg constructor
	public AccountScreen() {
		this(new Window());
	}

	//Constructor that requires one argument
	public AccountScreen(Window w) {
		
		super(w);
		
		setBackground(Color.BLUE);
				
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
