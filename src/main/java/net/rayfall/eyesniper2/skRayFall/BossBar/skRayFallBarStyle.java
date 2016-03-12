package net.rayfall.eyesniper2.skRayFall.BossBar;

import org.bukkit.boss.BarStyle;

public enum skRayFallBarStyle {
	SEGMENTED_10(BarStyle.SEGMENTED_10),
	SEGMENTED_12(BarStyle.SEGMENTED_12),
	SEGMENTED_20(BarStyle.SEGMENTED_20),
	SEGMENTED_6(BarStyle.SEGMENTED_6),
	SOLID(BarStyle.SOLID);
	
	private BarStyle key;
	
	private skRayFallBarStyle(BarStyle c){
		this.key = c;
	}
	
	public BarStyle getKey(){
		return this.key;
	}

}
