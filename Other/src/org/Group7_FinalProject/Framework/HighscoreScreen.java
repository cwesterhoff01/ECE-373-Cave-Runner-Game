package org.Group7_FinalProject.Framework;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import org.Group7_FinalProject.Utilities.Account;

public final class HighscoreScreen extends GameScreen {

	//Fields for the Highscore Screen
	private JButton menubtn;
	private JButton togglebtn;
	private JTable scoretable;
	
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
		
		//Add a table to display highscores
		displayPersonal();
		
		//Create a toggle button to switch between personal and all-time highscores display
		this.togglebtn = new JButton("Personal Highscores");
		this.togglebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (togglebtn.getText().equals("All-time Highscores")) {
					togglebtn.setText("Personal Highscores");
					remove(scoretable);
					displayPersonal();
				}
				else {
					togglebtn.setText("All-time Highscores");
					remove(scoretable);
					displayAllTime();
				}
			}
		});
		add(togglebtn, BorderLayout.SOUTH);
		
	}
	
	private void displayPersonal() {
		//gets the current account top scores then displays
		String[] title = {"Personal Highscores"};
		Integer[][] scores = new Integer[10][10];
		for (int i = 0; i < 10; i++) {
				if(i < window.getGame().getCurrAccount().getHighscores().size()) {
					scores[i][0] =  window.getGame().getCurrAccount().getHighscores().get(i);
				}
				else {
					scores[i][0] = 0;
				}
		}
		this.scoretable = new JTable(scores, title);

		add(scoretable, BorderLayout.SOUTH);
	}
	
	private void displayAllTime() {
		//Collects top highscores then displays
		String[] title = {"All-Time Highscores"};
		Integer[][] scores = new Integer[10][10];
		ArrayList<Integer> allscores = new ArrayList<Integer>();
		for(Account acc : window.getGame().getGameAccounts()) {
			allscores.addAll(acc.getHighscores());
		}
		Collections.sort(allscores);
		Collections.reverse(allscores);
		for(int i = 0; i < 10; i++) {
			if(i < allscores.size()) {
				scores[i][0] = allscores.get(i);
			}
			else {
				scores[i][0] = 0;
			}
			
		}
		this.scoretable = new JTable(scores, title);
		add(scoretable, BorderLayout.SOUTH);
	}

}
