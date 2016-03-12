package net.rayfall.eyesniper2.skRayFall.BossBar;

import org.bukkit.boss.BarFlag;

public enum skRayFallBarFlag {
	
	CREATE_FOG(BarFlag.CREATE_FOG),
	DARKEN_SKY(BarFlag.DARKEN_SKY),
	PLAY_BOSS_MUSIC(BarFlag.PLAY_BOSS_MUSIC);
	
	private BarFlag key;
	
	private skRayFallBarFlag(BarFlag c){
		this.key = c;
	}
	
	public BarFlag getKey(){
		return this.key;
	}

}
