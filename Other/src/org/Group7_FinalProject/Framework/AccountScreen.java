package org.Group7_FinalProject.Framework;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AccountScreen extends GameScreen {
	
	//Fields for the Account Screen
	JButton menubtn;
	JButton selectacctbtn;
	JButton createacctbtn;
	
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
		
		//Create a select account button
		this.selectacctbtn = new JButton("Select Account");
		this.selectacctbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(selectacctbtn, BorderLayout.NORTH);
		
		//Create a create account button
		this.createacctbtn = new JButton("Create Account");
		this.createacctbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(createacctbtn, BorderLayout.EAST);
		
	}
	

}
