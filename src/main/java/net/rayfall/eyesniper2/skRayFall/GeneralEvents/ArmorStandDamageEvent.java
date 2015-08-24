package net.rayfall.eyesniper2.skRayFall.GeneralEvents;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArmorStandDamageEvent extends Event implements Cancellable{
	private static final HandlerList handlers = new HandlerList();
	private Player damager;
	private boolean isCancelled;
	private Entity armorStand;
	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	public ArmorStandDamageEvent(Player damager, Entity armorStand) {
	    this.damager = damager;
	    this.armorStand = armorStand;
	    this.isCancelled = false;
	}
	 
	public Player getDamager() {
	    return this.damager;
	}
	
	public Entity getArmorStand(){
		return this.armorStand;
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
