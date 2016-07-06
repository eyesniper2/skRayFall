package net.rayfall.eyesniper2.skrayfall.scoreboard;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class IdScoreBoardManager implements Listener {
  private HashMap<String, SingleScore> singleScoreMap = new HashMap<String, SingleScore>();
  private HashMap<String, ArrayList<Player>> groupMap = new HashMap<String, ArrayList<Player>>();
  private HashMap<String, GroupScore> groupScoreMap = new HashMap<String, GroupScore>();

  public IdScoreBoardManager(Core core) {
    core.getServer().getPluginManager().registerEvents(this, core);
  }

  /**
   * Dump the current data stored within the IdScoreBoardManager.
   */
  public void dumpData() {
    singleScoreMap.clear();
    groupMap.clear();
    groupScoreMap.clear();
  }

  public void setScoreId(String id, Score score, Player player) {
    singleScoreMap.put(id, new SingleScore(score, player));
  }

  public void adjustGroupIdScore(String id, String text, int value) {
    groupScoreMap.put(id, new GroupScore(text, value));
    groupMap.put(id, new ArrayList<Player>());
  }

  /**
   * Add a player to a group score by ID.
   * 
   * @param id The ID text for the group score.
   * @param player The player to be added.
   */
  public void addPlayerToGroupId(String id, Player player) {
    if (groupScoreMap.containsKey(id) && player != null) {
      groupMap.get(id).add(player);
      GroupScore score = groupScoreMap.get(id);
      Score newScore =
          player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(score.getName());
      newScore.setScore(score.getValue());
    }

  }


  /**
   * Remove a player from a group score by ID.
   * 
   * @param id The ID text for the group score.
   * @param player The player to be removed.
   */
  public void removePlayerFromGroupScore(String id, Player player) {
    if (groupScoreMap.containsKey(id)) {
      if (groupMap.get(id).contains(player)) {
        GroupScore score = groupScoreMap.get(id);
        player.getScoreboard().resetScores(score.getName());
        groupMap.get(id).remove(player);
      }
    }
  }

  /**
   * Update group scores by ID. This will replace the old scores for all players in the group.
   * 
   * @param id The ID text for the group score.
   * @param newName New name of the score.
   * @param newValue New value of the score.
   */
  public void groupUpdateScore(String id, String newName, int newValue) {
    if (groupScoreMap.containsKey(id) && groupMap.get(id) != null) {
      ArrayList<Player> ls = groupMap.get(id);
      GroupScore old = groupScoreMap.get(id);
      for (Player p : ls) {
        Objective obj = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
        obj.getScoreboard().resetScores(old.getName());
        Score newScore = obj.getScore(newName);
        newScore.setScore(newValue);
      }
      groupScoreMap.put(id, new GroupScore(newName, newValue));
    }
  }

  /**
   * Update a single score by ID. This will replace the old score for the player.
   * 
   * @param id The ID text for the single score.
   * @param newName New name of the score.
   * @param newValue New value of the score.
   */
  public void updateSingleScore(String id, String newName, int newValue) {
    if (singleScoreMap.containsKey(id)) {
      SingleScore old = singleScoreMap.get(id);
      Player player = old.getPlayer();
      Objective obj = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
      Score newScore = obj.getScore(newName);
      obj.getScoreboard().resetScores(old.getScore().getEntry());
      newScore.setScore(newValue);
      singleScoreMap.put(id, new SingleScore(newScore, player));
    }

  }

  public boolean isIdSet(String id) {
    return singleScoreMap.containsKey(id);
  }

  /**
   * Get a single score by ID.
   * 
   * @param id The ID text for the single score.
   */
  public Score getScore(String id) {
    if (singleScoreMap.containsKey(id)) {
      return singleScoreMap.get(id).getScore();
    } else {
      return null;
    }
  }

  public void deleteScoreId(String id) {
    singleScoreMap.remove(id);
  }

  /**
   * Remove all single scores from a player.
   * 
   * @param player The players who's single scores are to be removed.
   */
  public void removeSingleScoresFromPlayer(Player player) {
    Iterator<String> iterator = singleScoreMap.keySet().iterator();
    while (iterator.hasNext()) {
      String id = iterator.next();
      if (singleScoreMap.get(id).getPlayer() == player) {
        iterator.remove();
      }
    }
  }

  /**
   * Remove all group scores from a player.
   * 
   * @param player The players who's group scores are to be removed.
   */
  public void removeGroupScoresFromPlayer(Player player) {
    Iterator<String> iterator = groupMap.keySet().iterator();
    while (iterator.hasNext()) {
      String id = iterator.next();
      if (groupMap.get(id).contains(player)) {
        groupMap.get(id).remove(player);
      }
    }
  }

  /**
   * Delete a group score by ID and remove it for all assigned players.
   * 
   * @param id The ID text for the group score.
   */
  public void deleteGroupScore(String id) {
    if (groupScoreMap.containsKey(id)) {
      ArrayList<Player> ls = groupMap.get(id);
      GroupScore old = groupScoreMap.get(id);
      for (Player p : ls) {
        p.getScoreboard().resetScores(old.getName());
      }
    }
  }

  /**
   * Get the title of a single score by ID.
   * 
   * @param id The ID text for the single score.
   */
  public String getNameOfSingleScore(String id) {
    SingleScore score = singleScoreMap.get(id);
    if (score != null) {
      return score.getScore().getEntry();
    } else {
      return null;
    }
  }

  /**
   * Get the value of a single score by ID.
   * 
   * @param id The ID text for the single score.
   */
  public int getValueOfSingleScore(String id) {
    SingleScore score = singleScoreMap.get(id);
    if (score != null) {
      return score.getScore().getScore();
    } else {
      return 0;
    }
  }

  /**
   * Get the title of a group score by ID.
   * 
   * @param id The ID text for the group score.
   */
  public String getNameOfGroupScore(String id) {
    GroupScore score = groupScoreMap.get(id);
    if (score != null) {
      return score.getName();
    } else {
      return null;
    }
  }

  /**
   * Get the value of a single score by ID.
   * 
   * @param id The ID text for the single score.
   */
  public int getValueOfGroupScore(String id) {
    GroupScore score = groupScoreMap.get(id);
    if (score != null) {
      return score.getValue();
    } else {
      return 0;
    }
  }



  // Remove player from all hashMap and deletes all scores for that player
  @EventHandler
  public void autoRemovePlayersScores(PlayerQuitEvent evt) {
    removeSingleScoresFromPlayer(evt.getPlayer());
    removeGroupScoresFromPlayer(evt.getPlayer());
  }

}
