package net.rayfall.eyesniper2.skrayfall.generalevents;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ArmorStandListener implements Listener {

  public ArmorStandListener(Core core) {
    core.getServer().getPluginManager().registerEvents(this, core);
  }

  /**
   * Listener used to generate the ArmorStandDamageEvent. 
   * 
   * @param evt The initial EntityDamageByEntityEvent event 
   *     used to generate the ArmorStandDamageEvent
   */
  @EventHandler
  public void onArmorStandDamage(EntityDamageByEntityEvent evt) {
    if (evt.getEntity().getType().equals(EntityType.ARMOR_STAND)
        && evt.getDamager().getType().equals(EntityType.PLAYER)) {
      ArmorStandDamageEvent event =
          new ArmorStandDamageEvent((Player) evt.getDamager(), evt.getEntity());
      Bukkit.getPluginManager().callEvent(event);
      if (event.isCancelled()) {
        evt.setCancelled(true);
        return;
      }
    }
  }

}
