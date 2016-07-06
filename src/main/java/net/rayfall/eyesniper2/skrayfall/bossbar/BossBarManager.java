package net.rayfall.eyesniper2.skrayfall.bossbar;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BossBarManager {

  private HashMap<String, BossBar> barMap = new HashMap<String, BossBar>();

  public BossBarManager() {

  }
  
  /**
   * Saves a bossbar in the BossBarManager for future use.
   * 
   * @param id The ID text for the bossbar, duplicate ID's will be over written.
   * @param bossbar The bossbar object to be stored.
   */
  public void createBossBar(String id, BossBar bossbar) {
    if (barMap.containsKey(id)) {
      barMap.get(id).removeAll();
      barMap.remove(id);
    }
    barMap.put(id, bossbar);
  }
  
  /**
   * Add a flag to a bossbar in the BossBarManager through the stored ID.
   * 
   * @param id The ID text for the bossbar.
   * @param flag The BarFlag to be added.
   */
  public void addFlag(String id, BarFlag flag) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      bar.addFlag(flag);
      barMap.put(id, bar);
    }
  }

  /**
   * Remove a flag from a bossbar in the BossBarManager through the stored ID.
   * 
   * @param id The ID text for the bossbar.
   * @param flag The BarFlag to be added.
   */
  public void removeFlag(String id, BarFlag flag) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      bar.removeFlag(flag);
      barMap.put(id, bar);
    }
  }

  /**
   * Add an array of players to a bossbar in the BossBarManager through the stored ID.
   * 
   * @param id The ID text for the bossbar.
   * @param players Array of players to be added to the bossbar
   */
  public void addPlayers(String id, Player[] players) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      for (Player p : players) {
        bar.addPlayer(p);
      }
      barMap.put(id, bar);
    }
  }

  /**
   * Remove an array of players from a bossbar in the BossBarManager through the stored ID.
   * 
   * @param id The ID text for the bossbar.
   * @param players Array of players to be removed from the bossbar
   */
  public void removePlayers(String id, Player[] players) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      for (Player p : players) {
        bar.removePlayer(p);
      }
      barMap.put(id, bar);
    }
  }

  /**
   * Remove a bossbar from the BossBarManager and remove it from all players who were assigned it.
   * 
   * @param id The ID text for the bossbar.
   */
  public void removeBar(String id) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      bar.removeAll();
      barMap.remove(id);
    }
  }

  /**
   * Changed the title of a bossbar from the BossBarManager through the stored ID.
   * 
   * @param id The ID text for the bossbar.
   * @param title The new title for the bossbar with color codes.
   */
  public void changeTitle(String id, String title) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      bar.setTitle(title);
      barMap.put(id, bar);
    }
  }

  /**
   * Changed the progress or fill of a bossbar from the BossBarManager through the stored ID.
   * 
   * @param id The ID text for the bossbar.
   * @param progress The progress or fill to be set from 0 - 100.
   */
  public void changeValue(String id, double progress) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      if (progress > 100) {
        progress = 100;
      } else if (progress < 0) {
        progress = 0;
      }
      bar.setProgress(progress / 100);
      barMap.put(id, bar);
    }
  }

  /**
   * Changed the color of a bossbar from the BossBarManager through the stored ID.
   * 
   * @param id The ID text for the bossbar.
   * @param color The BarColor to be used.
   */
  public void changeColor(String id, BarColor color) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      bar.setColor(color);
      barMap.put(id, bar);
    }
  }

  /**
   * Changed the style of a bossbar from the BossBarManager through the stored ID.
   * 
   * @param id The ID text for the bossbar.
   * @param style The BarStyle to be used.
   */
  public void changeStyle(String id, BarStyle style) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      bar.setStyle(style);
      barMap.put(id, bar);
    }
  }

  /**
   * Hide a bossbar from the BossBarManager through the stored ID for all players 
   * who were assigned it.
   * 
   * @param id The ID text for the bossbar.
   */
  public void hideBar(String id) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      bar.setVisible(false);
      barMap.put(id, bar);
    }
  }

  /**
   * Show a bossbar from the BossBarManager through the stored ID for all players 
   * who were assigned it.
   * 
   * @param id The ID text for the bossbar.
   */
  public void showBar(String id) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      bar.setVisible(true);
      barMap.put(id, bar);
    }
  }

  /**
   * Get the title of a stored bossbar from the BossBarManager through the ID.
   * 
   * @param id The ID text for the bossbar.
   * 
   * @return Title of the bossbar
   */
  public String getBarTitle(String id) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      return bar.getTitle();
    }
    return null;
  }

  /**
   * Get the progress or fill of a stored bossbar from the BossBarManager through the ID.
   * 
   * @param id The ID text for the bossbar.
   * 
   * @return The progress or fill of the bossbar from 0 - 100.
   */
  public Number getBarProgres(String id) {
    BossBar bar = barMap.get(id);
    if (bar != null) {
      return bar.getProgress();
    }
    return null;
  }

  public void dumpData() {
    barMap.clear();
  }

}
