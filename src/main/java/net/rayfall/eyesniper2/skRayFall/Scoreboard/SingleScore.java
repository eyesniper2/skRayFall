package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

public class SingleScore {
	private Score score;
	private Player player;
	public SingleScore(Score s, Player p){
		this.score = s;
		this.player = p;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Score getScore(){
		return this.score;
	}
	
	public boolean isPlayer(Player p){
		return (p == this.player);
	}
	
}
