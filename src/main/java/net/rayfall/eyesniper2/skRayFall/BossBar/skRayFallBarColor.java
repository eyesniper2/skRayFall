package net.rayfall.eyesniper2.skRayFall.BossBar;

import org.bukkit.boss.BarColor;

public enum skRayFallBarColor{
	GREEN(BarColor.GREEN),
	PINK(BarColor.PINK),
	PURPLE(BarColor.PURPLE),
	WHITE(BarColor.WHITE),
	YELLOW(BarColor.YELLOW),
	BLUE(BarColor.BLUE),
	RED(BarColor.RED);
	
	private BarColor key;
	
	private skRayFallBarColor(BarColor c){
		this.key = c;
	}
	
	public BarColor getKey(){
		return this.key;
	}
	
}
