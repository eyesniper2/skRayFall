package net.rayfall.eyesniper2.skrayfall.general.events;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class StoreListener implements Listener {

  private ArrayList<Player> storePossible = new ArrayList<>();
  private ArrayList<Player> unstorePossible = new ArrayList<>();

  public StoreListener(Plugin core) {
    core.getServer().getPluginManager().registerEvents(this, core);
  }

  /**
   * See if a store/unstore event is possible through items being moved into an inventory. Then
   * Storing the value in a array list while the inventory is open.
   * 
   * @param evt The InventoryClickEvent used to check item movement
   */
  @EventHandler
  public void onStoringFilter(InventoryClickEvent evt) {
    if (evt.getAction() == InventoryAction.SWAP_WITH_CURSOR
        || evt.getAction() == InventoryAction.COLLECT_TO_CURSOR
        || evt.getAction() == InventoryAction.PICKUP_SOME
        || evt.getAction() == InventoryAction.PICKUP_HALF
        || evt.getAction() == InventoryAction.PICKUP_ALL
        || evt.getAction() == InventoryAction.PICKUP_ONE) {
      if (evt.getClickedInventory().getType() != null
          && evt.getView().getBottomInventory().getType() == InventoryType.PLAYER
          && (evt.getView().getTopInventory().getType() == InventoryType.CHEST
              || evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
              || evt.getView().getTopInventory().getType() == InventoryType.HOPPER
              || evt.getView().getTopInventory().getType() == InventoryType.DISPENSER
              || evt.getView().getTopInventory().getType() == InventoryType.DROPPER)
          && evt.getClickedInventory().getType() == InventoryType.PLAYER
          && (!(storePossible.contains(evt.getWhoClicked())))) {
        storePossible.add((Player) evt.getWhoClicked());
        if (unstorePossible.contains(evt.getWhoClicked())) {
          unstorePossible.remove(evt.getWhoClicked());
        }
      }
      if (evt.getClickedInventory().getType() != null
          && evt.getView().getBottomInventory().getType() == InventoryType.PLAYER
          && (evt.getView().getTopInventory().getType() == InventoryType.CHEST
              || evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
              || evt.getView().getTopInventory().getType() == InventoryType.HOPPER
              || evt.getView().getTopInventory().getType() == InventoryType.DISPENSER
              || evt.getView().getTopInventory().getType() == InventoryType.DROPPER)
          && (evt.getClickedInventory().getType() == InventoryType.CHEST
              || evt.getClickedInventory().getType() == InventoryType.ENDER_CHEST
              || evt.getClickedInventory().getType() == InventoryType.HOPPER
              || evt.getClickedInventory().getType() == InventoryType.DISPENSER
              || evt.getClickedInventory().getType() == InventoryType.DROPPER)
          && (!(unstorePossible.contains(evt.getWhoClicked())))) {
        unstorePossible.add((Player) evt.getWhoClicked());
        if (storePossible.contains(evt.getWhoClicked())) {
          storePossible.remove(evt.getWhoClicked());
        }
      }
    }
  }

  /**
   * Remove the store/unstore event possibility when an inventory is closed.
   * 
   * @param evt The InventoryCloseEvent used to remove the possibility of a store/unstore
   *       event
   */
  @EventHandler
  public void onClose(InventoryCloseEvent evt) {
    if (evt.getView().getBottomInventory().getType() == InventoryType.PLAYER
        && (evt.getView().getTopInventory().getType() == InventoryType.CHEST
            || evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
            || evt.getView().getTopInventory().getType() == InventoryType.HOPPER
            || evt.getView().getTopInventory().getType() == InventoryType.DISPENSER
            || evt.getView().getTopInventory().getType() == InventoryType.DROPPER)) {
      if (storePossible.contains(evt.getPlayer())) {
        storePossible.remove(evt.getPlayer());
      } else if (unstorePossible.contains(evt.getPlayer())) {
        unstorePossible.remove(evt.getPlayer());
      }
    }
  }

  /**
   * Generate the store event based on conditions through the InventoryClickEvent.
   * 
   * @param evt The InventoryClickEvent used to generate the store event
   */
  @EventHandler
  public void onStore(InventoryClickEvent evt) {
    if ((evt.getAction() == InventoryAction.PLACE_ONE
        || evt.getAction() == InventoryAction.PLACE_SOME
        || evt.getAction() == InventoryAction.PLACE_ALL
        || evt.getAction() == InventoryAction.SWAP_WITH_CURSOR)
        && evt.getClickedInventory() != null) {
      if (evt.getClickedInventory().getType() == InventoryType.CHEST
          || evt.getClickedInventory().getType() == InventoryType.ENDER_CHEST
          || evt.getClickedInventory().getType() == InventoryType.HOPPER
          || evt.getClickedInventory().getType() == InventoryType.DISPENSER
          || evt.getClickedInventory().getType() == InventoryType.DROPPER) {
        if (evt.isLeftClick() && storePossible.contains(evt.getWhoClicked())) {
          StoreEvent event = new StoreEvent((Player) evt.getWhoClicked(), evt.getCursor().clone(),
              evt.getInventory());
          Bukkit.getPluginManager().callEvent(event);
          if (event.isCancelled()) {
            evt.setCancelled(true);
            return;
          }
          storePossible.remove(evt.getWhoClicked());
        }
        if (evt.isRightClick() && storePossible.contains(evt.getWhoClicked())) {
          ItemStack item = evt.getCursor().clone();
          item.setAmount(1);
          StoreEvent event = new StoreEvent((Player) evt.getWhoClicked(), item, evt.getInventory());
          Bukkit.getPluginManager().callEvent(event);
          if (event.isCancelled()) {
            evt.setCancelled(true);
          }
        }
      }
    }
    if (evt.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
      if (evt.isShiftClick() && evt.getClickedInventory().getType() == InventoryType.PLAYER) {
        if ((evt.getView().getTopInventory().getType() == InventoryType.CHEST
            || evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
            || evt.getView().getTopInventory().getType() == InventoryType.HOPPER
            || evt.getView().getTopInventory().getType() == InventoryType.DISPENSER
            || evt.getView().getTopInventory().getType() == InventoryType.DROPPER)
            && evt.getView().getBottomInventory().getType() == InventoryType.PLAYER) {
          StoreEvent event = new StoreEvent((Player) evt.getWhoClicked(),
              evt.getCurrentItem().clone(), evt.getInventory());
          Bukkit.getPluginManager().callEvent(event);
          if (event.isCancelled()) {
            evt.setCancelled(true);
            return;
          }
          storePossible.remove(evt.getWhoClicked());
        }
      }
    }
  }

  /**
   * Handle the store/unstore events based on click drags through the InventoryDragEvent.
   * 
   * @param evt The InventoryDragEvent used to generate the store/unstore event
   */
  @EventHandler
  public void onStoreDrag(InventoryDragEvent evt) {
    if ((evt.getInventory().getType() == InventoryType.CHEST
        || evt.getInventory().getType() == InventoryType.ENDER_CHEST
        || evt.getInventory().getType() == InventoryType.HOPPER
        || evt.getInventory().getType() == InventoryType.DISPENSER
        || evt.getInventory().getType() == InventoryType.DROPPER)
        && storePossible.contains(evt.getWhoClicked())) {
      int chestSize = evt.getView().getTopInventory().getSize();
      int num = 0;
      ItemStack temp = new ItemStack(Material.AIR, 0);
      for (int i : evt.getNewItems().keySet()) {
        if (!(i >= chestSize)) {
          ItemStack item = evt.getNewItems().get(i);
          if (num == 0) {
            temp = item.clone();
          }
          if (evt.getInventory().getItem(i) != null) {
            num += item.getAmount() - evt.getInventory().getItem(i).getAmount();
          } else {
            num += item.getAmount();
          }
        }
      }
      if (temp.getType() == Material.AIR) {
        return;
      }
      temp.setAmount(num);
      StoreEvent event = new StoreEvent((Player) evt.getWhoClicked(), temp, evt.getInventory());
      Bukkit.getPluginManager().callEvent(event);
      if (event.isCancelled()) {
        evt.setCancelled(true);
      }
    }
    if ((evt.getView().getBottomInventory().getType() == InventoryType.PLAYER
        && (evt.getView().getTopInventory().getType() == InventoryType.CHEST
            || evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
            || evt.getView().getTopInventory().getType() == InventoryType.HOPPER
            || evt.getView().getTopInventory().getType() == InventoryType.DISPENSER
            || evt.getView().getTopInventory().getType() == InventoryType.DROPPER))) {
      if (unstorePossible.contains(evt.getWhoClicked())) {
        if ((evt.getInventory().getType() == InventoryType.CHEST
            || evt.getInventory().getType() == InventoryType.ENDER_CHEST
            || evt.getInventory().getType() == InventoryType.HOPPER
            || evt.getInventory().getType() == InventoryType.DISPENSER
            || evt.getInventory().getType() == InventoryType.DROPPER)) {
          int chestSize = evt.getView().getTopInventory().getSize();
          int num = 0;
          ItemStack temp = new ItemStack(Material.AIR, 0);
          for (int i : evt.getNewItems().keySet()) {
            if (!(i < chestSize)) {
              ItemStack item = evt.getNewItems().get(i);
              if (num == 0) {
                temp = item.clone();
              }
              if (evt.getView().getBottomInventory()
                  .getItem(evt.getView().convertSlot(i)) != null) {
                num += item.getAmount() - evt.getView().getBottomInventory()
                    .getItem(evt.getView().convertSlot(i)).getAmount();
              } else {
                num += item.getAmount();
              }
            }
          }
          if (temp.getType() == Material.AIR) {
            return;
          }
          temp.setAmount(num);
          UnstoreEvent event =
              new UnstoreEvent((Player) evt.getWhoClicked(), temp, evt.getInventory());
          Bukkit.getPluginManager().callEvent(event);
          if (event.isCancelled()) {
            evt.setCancelled(true);
          }
        }
      }
    }

  }

  /**
   * Generate the unstore event based on conditions through the InventoryClickEvent.
   * 
   * @param evt The InventoryClickEvent used to generate the unstore event
   */
  @EventHandler
  public void onUnstore(InventoryClickEvent evt) {
    if ((evt.getAction() == InventoryAction.PLACE_ONE
        || evt.getAction() == InventoryAction.PLACE_SOME
        || evt.getAction() == InventoryAction.PLACE_ALL
        || evt.getAction() == InventoryAction.SWAP_WITH_CURSOR)
        && evt.getClickedInventory() != null) {
      if (evt.getClickedInventory().getType() == InventoryType.PLAYER) {
        if (evt.isLeftClick() && unstorePossible.contains(evt.getWhoClicked())) {
          UnstoreEvent event =
              new UnstoreEvent((Player) evt.getWhoClicked(), evt.getCursor(), evt.getInventory());
          Bukkit.getPluginManager().callEvent(event);
          if (event.isCancelled()) {
            evt.setCancelled(true);
            return;
          }
          unstorePossible.remove(evt.getWhoClicked());
        }
        if (evt.isRightClick() && unstorePossible.contains(evt.getWhoClicked())) {
          ItemStack item = evt.getCursor().clone();
          item.setAmount(1);
          UnstoreEvent event =
              new UnstoreEvent((Player) evt.getWhoClicked(), item, evt.getInventory());
          Bukkit.getPluginManager().callEvent(event);
          if (event.isCancelled()) {
            evt.setCancelled(true);
          }
        }
      }
    }
    if (evt.getAction() == InventoryAction.DROP_ALL_CURSOR
        || evt.getAction() == InventoryAction.DROP_ALL_SLOT
        || evt.getAction() == InventoryAction.DROP_ONE_CURSOR
        || evt.getAction() == InventoryAction.DROP_ONE_SLOT) {
      if ((evt.getView().getTopInventory().getType() == InventoryType.CHEST
          || evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
          || evt.getView().getTopInventory().getType() == InventoryType.HOPPER
          || evt.getView().getTopInventory().getType() == InventoryType.DISPENSER
          || evt.getView().getTopInventory().getType() == InventoryType.DROPPER)
          && evt.getView().getBottomInventory().getType() == InventoryType.PLAYER) {
        if (evt.isLeftClick() && unstorePossible.contains(evt.getWhoClicked())) {
          UnstoreEvent event =
              new UnstoreEvent((Player) evt.getWhoClicked(), evt.getCursor(), evt.getInventory());
          Bukkit.getPluginManager().callEvent(event);
          if (event.isCancelled()) {
            evt.setCancelled(true);
            return;
          }
          unstorePossible.remove(evt.getWhoClicked());
        }
        if (evt.isRightClick() && unstorePossible.contains(evt.getWhoClicked())) {
          ItemStack item = evt.getCursor().clone();
          item.setAmount(1);
          UnstoreEvent event =
              new UnstoreEvent((Player) evt.getWhoClicked(), item, evt.getInventory());
          Bukkit.getPluginManager().callEvent(event);
          if (event.isCancelled()) {
            evt.setCancelled(true);
          }
        }
      }

    }
    if (evt.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
      if (evt.isShiftClick() && (evt.getClickedInventory().getType() == InventoryType.CHEST
          || evt.getClickedInventory().getType() == InventoryType.ENDER_CHEST
          || evt.getClickedInventory().getType() == InventoryType.HOPPER
          || evt.getClickedInventory().getType() == InventoryType.DISPENSER
          || evt.getClickedInventory().getType() == InventoryType.DROPPER)) {
        if ((evt.getView().getTopInventory().getType() == InventoryType.CHEST
            || evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
            || evt.getView().getTopInventory().getType() == InventoryType.HOPPER
            || evt.getView().getTopInventory().getType() == InventoryType.DISPENSER
            || evt.getView().getTopInventory().getType() == InventoryType.DROPPER)
            && evt.getView().getBottomInventory().getType() == InventoryType.PLAYER) {
          UnstoreEvent event = new UnstoreEvent((Player) evt.getWhoClicked(), evt.getCurrentItem(),
              evt.getInventory());
          Bukkit.getPluginManager().callEvent(event);
          if (event.isCancelled()) {
            evt.setCancelled(true);
          }
        }
      }
    }
  }

}
