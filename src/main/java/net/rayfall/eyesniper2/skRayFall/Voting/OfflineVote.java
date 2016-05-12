package net.rayfall.eyesniper2.skRayFall.Voting;

public class OfflineVote {
	
	private String site;
	private String time;
	
	public OfflineVote(String site, String time){
		this.site = site;
		this.time = time;
	}
	
	public String getSite(){
		return this.site;
	}
	
	public String getTime(){
		return this.time;
	}

	

}
