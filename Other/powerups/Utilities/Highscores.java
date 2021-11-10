package org.Group7_FinalProject.Utilities;

public class Highscores {
	private Integer score;
	private String name;
	
	public Highscores(){
		this(0, "");
	}
	
	public Highscores(Integer scoreNum, String accountName) {
		this.score = scoreNum;
		this.name = accountName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
