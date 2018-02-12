package net.rayfall.eyesniper2.skrayfall.general.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArmorStandDamageEvent extends Event implements Cancellable {
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

  /**
   * Called when a player damages an armor stand. 
   * 
   * @param damager The player who damaged the armor stand
   * @param armorStand The armor stand which was damaged
   */
  public ArmorStandDamageEvent(Player damager, Entity armorStand) {
    this.damager = damager;
    this.armorStand = armorStand;
    this.isCancelled = false;
  }

  public Player getDamager() {
    return this.damager;
  }

  public Entity getArmorStand() {
    return this.armorStand;
  }

  @Override
  public boolean isCancelled() {
    return this.isCancelled;
  }

  @Override
  public void setCancelled(boolean bool) {
    this.isCancelled = bool;
  }
}
