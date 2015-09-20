package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import java.util.HashMap;

import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Score;

public class IDScoreBoardManager {
	private HashMap<String, Score> scoreMap = new HashMap<String, Score>();
	@SuppressWarnings("unused")
	private Plugin plugin; 
	
	public IDScoreBoardManager(Plugin p){
		this.plugin = p;
	}
	
	public void dumpData(){
			scoreMap.clear();
	}
	
	public boolean setScoreID(String id, Score score){
		if (scoreMap.containsKey(id)){
			return false;
		}
		else{
			scoreMap.put(id, score);
			return true;
		}
	}
	
	public boolean isIdSet(String id){
		if(scoreMap.containsKey(id)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Score getScore(String id){
		if(scoreMap.containsKey(id)){
			Score s = scoreMap.get(id);
			return s;
		}
		else{
			return null;
		}
	}
	
	public void deleteScoreID(String s){
		if(scoreMap.containsKey(s)){
			scoreMap.remove(s);
		}
	}

}
