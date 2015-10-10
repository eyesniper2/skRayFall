package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

public class IDScoreBoardManager {
	private HashMap<String, Score> scoreMap = new HashMap<String, Score>();
	private HashMap<String, Player> playerMap = new HashMap<String, Player>();
	
	public IDScoreBoardManager(){
	}
	
	public void dumpData(){
			scoreMap.clear();
			playerMap.clear();
	}
	
	public void setScoreID(String id, Score score, Player player){
		if (scoreMap.containsKey(id) || playerMap.containsKey(id)){
			scoreMap.remove(id);
			playerMap.remove(id);
		}
		playerMap.put(id, player);
		scoreMap.put(id, score);

		
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
			playerMap.remove(s);
		}
	}

	   
	public void removeScoreFromPlayer(Player p){
			Iterator<String> i = playerMap.keySet().iterator();
			while(i.hasNext()){
				String id = (String) i.next();
				if(playerMap.get(id) == null || playerMap.get(id) == p){
					scoreMap.remove(id);
					i.remove();
				}
			
		}
	}

}
