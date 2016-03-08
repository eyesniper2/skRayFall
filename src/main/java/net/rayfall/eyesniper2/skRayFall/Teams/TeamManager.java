package net.rayfall.eyesniper2.skRayFall.Teams;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

@SuppressWarnings("deprecation")
public class TeamManager {
	
	private HashMap<String,Team> teamMap = new HashMap<String,Team>();
	//Sample Scoreboard to test all changes against before sending them out
	//to all players.
	private Scoreboard teamBoard;
	
	public TeamManager(){
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		teamBoard = manager.getNewScoreboard();
	}
	
	public void createTeam(String s){
		if(!teamMap.containsKey(s)){
			Team team = teamBoard.registerNewTeam(s);
			teamMap.put(s, team);
			for(Object p : Bukkit.getServer().getOnlinePlayers().toArray()){
				Player t =(Player)p;
				if(!(t.getScoreboard().getTeams().contains(teamMap.get(s)))){
					//Debug
					Bukkit.broadcastMessage("Team Reg!");
					t.getScoreboard().registerNewTeam(s);
				}
			}
			
		}
	}
	
	public void createTeam(String s, Player[] players){
		if(!teamMap.containsKey(s)){
			Team team = teamBoard.registerNewTeam(s);
			for(Player p : players){
				team.addPlayer(p);
			}
			teamMap.put(s, team);
			for(Object p : Bukkit.getServer().getOnlinePlayers().toArray()){
				Player t =(Player)p;
				if(!(t.getScoreboard().getTeams().contains(teamMap.get(s)))){
					//Debug
					Bukkit.broadcastMessage("Team Reg!");
					Team e = t.getScoreboard().registerNewTeam(s);
					for(Player w : players){
						e.addPlayer(w);
					}
				}
			}
			
		}
	}
	
	public boolean teamExists(String s){
		if(teamMap.containsKey(s)){
			return true;
		}
		else
			return false;
	}
	
	public boolean isPlayerOnTeam(String s, Player p){
		if(teamMap.containsKey(s) && teamMap.get(s).getPlayers().contains(p)){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	
	public void addPlayesrToTeam(String s, Player[] p){
		if(teamMap.containsKey(s)){
			for(Player player: p){
				teamMap.get(s).addPlayer(player);
			}
			for(Object e : Bukkit.getServer().getOnlinePlayers().toArray()){
				Player t =(Player)e;
				//Debug
				Bukkit.broadcastMessage("Player set!");
				for(Player player: p){
					t.getScoreboard().getTeam(s).addPlayer(player);
				}
			}
		}
	}
	
	public void removePlayersFromTeam(String s, Player[] p){
		if(teamMap.containsKey(s)){
			for(Player player: p){
				teamMap.get(s).removePlayer(player);
			}
			for(Object e : Bukkit.getServer().getOnlinePlayers().toArray()){
				//Debug
				Bukkit.broadcastMessage("Removed!");
				for(Player player: p){
					((Player) e).getScoreboard().getTeam(s).removePlayer(player);
				}
			}
		}
	}
	
	
	public void removeTeam(String s){
		if(teamMap.containsKey(s)){
			teamMap.remove(s);
			for(Object e : Bukkit.getServer().getOnlinePlayers().toArray()){
				Player t =(Player)e;
				if(t.getScoreboard().getTeam(s) != null){
					t.getScoreboard().getTeam(s).unregister();
				}
			}
		}
	}
	
	public void setPrefix(String team, String prefix){
		if(teamMap.containsKey(team)){
			teamMap.get(team).setPrefix(prefix);
			for(Object p : Bukkit.getServer().getOnlinePlayers().toArray()){
				//Debug
				Bukkit.broadcastMessage("Prefix set!");
				((Player) p).getScoreboard().getTeam(team).setPrefix(prefix);
			}
		}
	}
	
	public void setSuffix(String team, String suffix){
		if(teamMap.containsKey(team)){
			teamMap.get(team).setSuffix(suffix);
			for(Object p : Bukkit.getServer().getOnlinePlayers().toArray()){
				//Debug
				Bukkit.broadcastMessage("Suffix set!");
				((Player) p).getScoreboard().getTeam(team).setSuffix(suffix);
				
			}
		}
	}
	
	public void setFriendlyFire(String team, boolean result){
		if(teamMap.containsKey(team)){
			teamMap.get(team).setAllowFriendlyFire(result);
			for(Object p : Bukkit.getServer().getOnlinePlayers().toArray()){
				//Debug
				Bukkit.broadcastMessage("Friendly fire set!");
				((Player) p).getScoreboard().getTeam(team).setAllowFriendlyFire(result);
			}
		}
	}
	
	public void setFriendlyInvisibles(String team, boolean result){
		if(teamMap.containsKey(team)){
			teamMap.get(team).setAllowFriendlyFire(result);
			for(Object p : Bukkit.getServer().getOnlinePlayers().toArray()){
				//Debug
				Bukkit.broadcastMessage("Friendly Invisibles set!");
				((Player) p).getScoreboard().getTeam(team).setCanSeeFriendlyInvisibles(result);
			}
		}
	}
	
	
	public void setTeamNameTag(String team, NameTagVisibility nameTag){
		if(teamMap.containsKey(team)){
			teamMap.get(team).setNameTagVisibility(NameTagVisibility.ALWAYS);
			for(Object p : Bukkit.getServer().getOnlinePlayers().toArray()){
				//Debug
				Bukkit.broadcastMessage("Friendly Invisibles set!");
				((Player) p).getScoreboard().getTeam(team).setNameTagVisibility(NameTagVisibility.ALWAYS);
			}
		}
	}

}
