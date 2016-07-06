package net.rayfall.eyesniper2.skrayfall.generalevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StoreEvent extends Event implements Cancellable {
  private static final HandlerList handlers = new HandlerList();
  private Player player;
  private boolean isCancelled;
  private ItemStack item;
  private Inventory inv;

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  /**
   * Called when a player stores an item in a inventory. 
   * 
   * @param player The player who stored the items
   * @param item The items stored
   * @param inv The inventory interacted with
   */
  public StoreEvent(Player player, ItemStack item, Inventory inv) {
    this.player = player;
    this.item = item;
    this.inv = inv;
    this.isCancelled = false;
  }

  public Player getPlayer() {
    return this.player;
  }

  public ItemStack getItem() {
    return this.item;
  }

  public Inventory getInventory() {
    return this.inv;
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
