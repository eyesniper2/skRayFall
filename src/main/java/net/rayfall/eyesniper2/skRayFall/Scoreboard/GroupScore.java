package net.rayfall.eyesniper2.skRayFall.Scoreboard;

public class GroupScore {
	private String scoreName;
	private int scoreValue;
	
	public GroupScore(String scoreName, int scoreValue){
		this.scoreName = scoreName;
		this.scoreValue = scoreValue;
	}
	
	public int getValue(){
		return this.scoreValue;
	}
	
	public String getName(){
		return this.scoreName;
	}

}
