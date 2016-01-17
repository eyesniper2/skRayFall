package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class IDScoreBoardManager implements Listener{
	private HashMap<String, SingleScore> singleScoreMap = new HashMap<String, SingleScore>();
	private HashMap<String, ArrayList<Player>> groupMap = new HashMap<String, ArrayList<Player>>();
	private HashMap<String, GroupScore> groupScoreMap = new HashMap<String, GroupScore>();
	
	public IDScoreBoardManager(skRayFall core){
		core.getServer().getPluginManager().registerEvents(this, core);
	}
	
	public void dumpData(){
			singleScoreMap.clear();
			groupMap.clear();
			groupScoreMap.clear();
	}
	
	public void setScoreID(String id, Score score, Player player){
		singleScoreMap.put(id, new SingleScore(score,player));
	}
	
	public void adjustGroupIDScore(String id, String text, int value){
		groupScoreMap.put(id, new GroupScore(text, value));
		groupMap.put(id, new ArrayList<Player>());
	}

	
	public void addPlayerToGroupID(String id, Player p){
		if(groupScoreMap.containsKey(id) && p != null){
			groupMap.get(id).add(p);
			GroupScore s = groupScoreMap.get(id);
			Score newScore = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(s.getName());
			newScore.setScore(s.getValue());
		}
		
	}
	
	
	public void removePlayerFromGroupScore(String id, Player p){
		if(groupScoreMap.containsKey(id)){
			if(groupMap.get(id).contains(p)){
				GroupScore s = groupScoreMap.get(id);
				p.getScoreboard().resetScores(s.getName());
				groupMap.get(id).remove(p);
			}
		}
	}
	
	// Will update all scores for people in a group
	public void groupUpdateScore(String id, String newName, int newValue){
		if(groupScoreMap.containsKey(id) && groupMap.get(id) != null){
			ArrayList<Player> ls = groupMap.get(id);
			GroupScore old = groupScoreMap.get(id);
			groupScoreMap.put(id, new GroupScore(newName, newValue));
			for(Player p : ls){
				Objective obj = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
				obj.getScoreboard().resetScores(old.getName());
				Score newScore = obj.getScore(newName);
				newScore.setScore(newValue);
			}
		}	
	}
	
	public void updateSingleScore(String id, String newName, int newValue){
		if(singleScoreMap.containsKey(id)){
			SingleScore old = singleScoreMap.get(id);
			Player p = old.getPlayer();
			Objective obj = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
			obj.getScoreboard().resetScores(old.getScore().getEntry());
			Score newScore = obj.getScore(newName);
			newScore.setScore(newValue);
			singleScoreMap.put(id, new SingleScore(newScore,p));
		}
		
	}
	
	public boolean isIdSet(String id){
		if(singleScoreMap.containsKey(id)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Score getScore(String id){
		if(singleScoreMap.containsKey(id)){
			return singleScoreMap.get(id).getScore();
		}
		else{
			return null;
		}
	}
	
	public void deleteScoreID(String s){
		singleScoreMap.remove(s);
	}

	   
	public void removeSingleScoresFromPlayer(Player p){
			Iterator<String> i = singleScoreMap.keySet().iterator();
			while(i.hasNext()){
				String id = (String) i.next();
				if(singleScoreMap.get(id).getPlayer() == p){
					i.remove();
				}
		}
	}
	

	public void removeGroupScoresFromPlayer(Player p){
		Iterator<String> i = groupMap.keySet().iterator();
		while(i.hasNext()){
			String id = (String) i.next();
			if(groupMap.get(id).contains(p)){
				i.remove();
			}
		}
	}
	
	public void deleteGroupScore(String id){
		if(groupScoreMap.containsKey(id)){
			ArrayList<Player> ls = groupMap.get(id);
			GroupScore old = groupScoreMap.get(id);
			for(Player p : ls){
				p.getScoreboard().resetScores(old.getName());
			}
		}
	}
	
	public String getNameOfSingleScore(String id){
		SingleScore s = singleScoreMap.get(id);
		if(s != null){
			return s.getScore().getEntry();
		}
		else{
			return null;
		}
	}
	
	public int getValueOfSingleScore(String id){
		SingleScore s = singleScoreMap.get(id);
		if(s != null){
			return s.getScore().getScore();
		}
		else{
			return 0;
		}
	}
	
	public String getNameOfGroupScore(String id){
		GroupScore s = groupScoreMap.get(id);
		if(s != null){
			return s.getName();
		}
		else{
			return null;
		}
	}
	
	public int getValueOfGroupScore(String id){
		GroupScore s = groupScoreMap.get(id);
		if(s != null){
			return s.getValue();
		}
		else{
			return 0;
		}
	}
	
	

	// Remove player from all hashMap and deletes all scores for that player
	@EventHandler
	public void AutoRemovePlayersScores(PlayerQuitEvent evt){
		removeSingleScoresFromPlayer(evt.getPlayer());
		removeGroupScoresFromPlayer(evt.getPlayer());
	}

}
