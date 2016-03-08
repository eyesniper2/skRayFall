package net.rayfall.eyesniper2.skRayFall.BossBar;

import java.util.HashMap;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarManager {
	
	private HashMap<String, BossBar> barMap = new HashMap<String, BossBar>();
	
	public BossBarManager(){

	}
	
	public void createBossBar(String id, BossBar b){
		if(barMap.containsKey(id)){
			barMap.get(id).removeAll();
			barMap.remove(id);
		}
		barMap.put(id, b);
	}
	
	public void addFlag(String id, BarFlag f){
		BossBar bar = barMap.get(id);
		if(bar != null){
			bar.addFlag(f);
			barMap.put(id, bar);
		}
	}
	
	public void removeFlag(String id, BarFlag f){
		BossBar bar = barMap.get(id);
		if(bar != null){
			bar.removeFlag(f);
			barMap.put(id, bar);
		}
	}
	
	public void addPlayers(String id, Player[] f){
		BossBar bar = barMap.get(id);
		if(bar != null){
			for(Player p : f){
				bar.addPlayer(p);
			}
			barMap.put(id, bar);
		}
	}
	
	public void removePlayers(String id, Player[] f){
		BossBar bar = barMap.get(id);
		if(bar != null){
			for(Player p : f){
				bar.removePlayer(p);
			}
			barMap.put(id, bar);
		}
	}
	
	public void removeBar(String id){
		BossBar bar = barMap.get(id);
		if(bar != null){
			bar.removeAll();
			barMap.remove(id);
		}
	}
	
	public void changeTitle(String id, String title){
		BossBar bar = barMap.get(id);
		if(bar != null){
			bar.setTitle(title);
			barMap.put(id, bar);
		}
	}
	
	public void changeValue(String id, double d){
		BossBar bar = barMap.get(id);
		if(bar != null){
			if(d>100){
				d = 100;
			}
			else if(d<0){
				d = 0;
			}
			bar.setProgress(d/100);
			barMap.put(id, bar);
		}
	}
	
	
	public void changeColor(String id, BarColor c){
		BossBar bar = barMap.get(id);
		if(bar != null){
			bar.setColor(c);
			barMap.put(id, bar);
		}
	}
	
	public void changeStyle(String id, BarStyle s){
		BossBar bar = barMap.get(id);
		if(bar != null){
			bar.setStyle(s);
			barMap.put(id, bar);
		}
	}
	
	public void hideBar(String id){
		BossBar bar = barMap.get(id);
		if(bar != null){
			bar.setVisible(false);
			barMap.put(id, bar);
		}
	}
	public void showBar(String id){
		BossBar bar = barMap.get(id);
		if(bar != null){
			bar.setVisible(true);
			barMap.put(id, bar);
		}
	}
	
	public String getBarTitle(String id){
		BossBar bar = barMap.get(id);
		if(bar != null){
			return bar.getTitle();
		}
		return null;
	}
	
	public Number getBarProgres(String id){
		BossBar bar = barMap.get(id);
		if(bar != null){
			return bar.getProgress();
		}
		return null;
	}

	public void dumpData(){
		barMap.clear();
}

}
