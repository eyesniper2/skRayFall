package net.rayfall.eyesniper2.skRayFall.Holograms;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HoloPickupEvent extends Event{
	
	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private String hologramID;
	private int line;
	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	public HoloPickupEvent(Player player, String hologramID, int lineNumber) {
	    this.player = player;
	    this.hologramID = hologramID;
	    this.line = lineNumber;
	}
	 
	public Player getPlayer() {
	    return this.player;
	}
	
	public String getHoloID(){
		return this.hologramID;
	}
	
	public int getLineNumber(){
		return this.line;
	}

}
