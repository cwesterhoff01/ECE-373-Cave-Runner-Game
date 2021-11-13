package org.Group7_FinalProject.Framework;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.border.LineBorder;

import org.Group7_FinalProject.Utilities.Account;
import org.Group7_FinalProject.Utilities.HighscoreTable;
import org.Group7_FinalProject.Utilities.Highscores;

public final class HighscoreScreen extends GameScreen {

	//Fields for the Highscore Screen
	private JButton menubtn;
	private JButton togglebtn;
	private JTable scoretable;
	private HighscoreTable highscoreTable;
	//Comparator must be used when sorting object values to sort list
	static private Comparator<Highscores> myComparator;
	static {
		myComparator = new Comparator<Highscores>() {
			public int compare(Highscores hs1, Highscores hs2) {
				return Integer.compare(hs1.getScore(), hs2.getScore());
			}
		};
	}
	
	//Default no-arg constructor
	public HighscoreScreen() {
		this(new Window());
	}
	
	//Constructor that requires one argument
	public HighscoreScreen(Window w) {
		
		super(w, new ImageIcon("src/resources/background_highscore2.jpeg"));
		
		//Create a menu button
		menubtn = new JButton("Menu");
		menubtn.setFont(new Font("Arial", Font.BOLD, 20));
		menubtn.setBounds(462,435,80,50); 
		menubtn.setContentAreaFilled(false);
		menubtn.setBorderPainted(false);
		menubtn.setBorder(new LineBorder(Color.white));
		menubtn.setForeground(Color.white);
		menubtn.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	menubtn.setBorderPainted(true);
		    }
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	menubtn.setBorderPainted(false);
		    }
		});
		menubtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setCurrentScreen(w.getGameScreenList().get("Menu Screen"));
			}
		});
		add(menubtn);
		
		//Displays Alltime scores first
		String title[] = {"Scores"};
		//load in highscores into a double array & create Jtable
		highscoreTable = new HighscoreTable(title, getAlltimeScores());
		highscoreTable.setFont(new Font("Arial", Font.BOLD, 26));
		highscoreTable.setBounds(585,240,300,180); 
		highscoreTable.setBorder(new LineBorder(Color.white));
		highscoreTable.setForeground(Color.white);
		
		//loading in account names for alltime
		highscoreTable.addAccountNames(convertToHighscoreList());
		add(highscoreTable);
		//Create a toggle button to switch between personal and all-time highscores display
		togglebtn = new JButton("Personal Highscores");
		togglebtn.setFont(new Font("Arial", Font.BOLD, 24));
		togglebtn.setBounds(185,265,260,80); 
		togglebtn.setContentAreaFilled(false);
		togglebtn.setBorderPainted(true);
		togglebtn.setBorder(new LineBorder(Color.white, 2));
		togglebtn.setForeground(Color.white);
		togglebtn.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	//togglebtn.setBorderPainted(true);
		    	togglebtn.setBorder(new LineBorder(Color.black, 2));
		    }
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	//togglebtn.setBorderPainted(false);
		    	togglebtn.setBorder(new LineBorder(Color.white, 2));
		    }
		});
		togglebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (togglebtn.getText().equals("All-time Highscores")) {
					togglebtn.setText("Personal Highscores");
					//Show all-time scores
					String alltimeTitle[] = {"All-time Highscores"};
					highscoreTable.replaceData(getAlltimeScores(), alltimeTitle);
					highscoreTable.addAccountNames(convertToHighscoreList());
				}
				else {
					togglebtn.setText("All-time Highscores");
					//show personal scores
					String personalTitle[] = {"Personal Highscores"};
					highscoreTable.replaceData(getPersonalScores(), personalTitle);
					highscoreTable.removeEmptyCol();
				}
			}
		});
		add(togglebtn);
		setLayout(null);
		
	}

	private Integer[][] getPersonalScores() { //returns a double array of order personal scores
		Integer scores[][] = new Integer[10][1];
		ArrayList<Integer> scoreList = new ArrayList<Integer>();
		for(Integer i : window.getGame().getCurrAccount().getHighscores()) {
			scoreList.add(i);
		}
		Collections.sort(scoreList);
		Collections.reverse(scoreList);
		for(int i = 0; i < 10; i++) {
			if(i < scoreList.size()) {
				scores[i][0] = scoreList.get(i);
			}
			else {
				scores[i][0] = 0;
			}
		}
		return scores;
	}
	
	private Integer[][] getAlltimeScores(){ //returns a double array of odered all time scores
		Integer scores[][] = new Integer[10][1];
		for(int i = 0; i < 10; i++) {
			if(i < convertToHighscoreList().size()) {
				scores[i][0] = convertToHighscoreList().get(i).getScore();
			}
			else {
				scores[i][0] = 0;
			}
		}
		return scores;
	}
	private ArrayList<Highscores> convertToHighscoreList() { //puts all the scores into type alltime & orderes them based off score
		//Adds all scores into highscore objeccts
		ArrayList<Highscores> highscores = new ArrayList<Highscores>();
		for(Account acc : window.getGame().getGameAccounts()) {
			for(Integer i : acc.getHighscores()) {
				Highscores temp = new Highscores(i, acc.getName());
				highscores.add(temp);
			}
		}
		//Have to use a comparator to sort objects
		Collections.sort(highscores, myComparator);
		Collections.reverse(highscores);
		return highscores;
	}
	

}
