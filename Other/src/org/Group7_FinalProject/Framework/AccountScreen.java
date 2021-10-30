package org.Group7_FinalProject.Framework;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AccountScreen extends GameScreen {
	
	//Fields for the Account Screen
	JButton menubtn;
	JButton selectacctbtn;
	JButton createacctbtn;
	JTextField accnttxtbox;
	
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
				//If the user has entered a new account name in the textbox but not saved it to create a new account, display a warning
				if (!accnttxtbox.getText().equals("")) {
					//The user can continue without saving the changes, or return to the account screen
					int result = JOptionPane.showConfirmDialog(window.getFrame(), "The account name you entered has not been saved. Continue to menu anyway?", "Return to Menu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.NO_OPTION) {
						return;
					}
					//If the user does decide to continue, empty the textbox
					accnttxtbox.setText(null);
				}
				window.setCurrentScreen(w.getGameScreenList().get("Menu Screen"));
			}
		});
		add(menubtn, BorderLayout.SOUTH);
		
		//Create a select account button
		this.selectacctbtn = new JButton("Select Account");
		this.selectacctbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TO DO:
				//Fill out the select account behavior here
			}
		});
		add(selectacctbtn, BorderLayout.NORTH);
		
		//Create a create account button
		this.createacctbtn = new JButton("Create Account");
		this.createacctbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accnttxtbox.setText("");
				//TO DO:
				//Fill out the account creation behavior here
			}
		});
		add(createacctbtn, BorderLayout.EAST);
		
		//Create a textbox for the user to enter a new account name in
		this.accnttxtbox = new JTextField("", 20);
		add(accnttxtbox, BorderLayout.WEST);
		
	}
	

}
