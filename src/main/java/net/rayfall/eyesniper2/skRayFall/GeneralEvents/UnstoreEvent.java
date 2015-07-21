package net.rayfall.eyesniper2.skRayFall.GeneralEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class UnstoreEvent extends Event implements Cancellable{
	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private boolean isCancelled;
	private ItemStack item;
	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	public UnstoreEvent(Player player, ItemStack item) {
	    this.player = player;
	    this.item = item;
	    this.isCancelled = false;
	}
	 
	public Player getPlayer() {
	    return this.player;
	}
	
	public ItemStack getItem(){
		return this.item;
	}
	 
	@Override
	public boolean isCancelled() {
	    return this.isCancelled;
	}
	 
	@Override
	public void setCancelled(boolean b) {
	    this.isCancelled = b;
	}
	}
