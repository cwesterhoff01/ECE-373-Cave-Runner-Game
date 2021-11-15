package org.Group7_FinalProject.Utilities;

public class Highscore {
	
	private Integer value;
	private String accountName;
	
	public Highscore(){
		this(0, "");
	}
	
	public Highscore(Integer scoreNum, String accountName) {
		this.value = scoreNum;
		this.accountName = accountName;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer score) {
		this.value = score;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String name) {
		this.accountName = name;
	}
	
}
