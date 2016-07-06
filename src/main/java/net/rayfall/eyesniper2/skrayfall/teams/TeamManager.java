package net.rayfall.eyesniper2.skrayfall.teams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

import java.util.HashMap;

@SuppressWarnings("deprecation")
public class TeamManager {

  private HashMap<String, Team> teamMap = new HashMap<String, Team>();
  // Sample Scoreboard to test all changes against before sending them out
  // to all players.
  private Scoreboard teamBoard;

  public TeamManager() {
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    teamBoard = manager.getNewScoreboard();
  }

  /**
   * Create a empty team.
   * 
   * @param teamName The reference name of the team.
   */
  public void createTeam(String teamName) {
    if (!teamMap.containsKey(teamName)) {
      Team team = teamBoard.registerNewTeam(teamName);
      teamMap.put(teamName, team);
      for (Object p : Bukkit.getServer().getOnlinePlayers().toArray()) {
        Player tempPlayer = (Player) p;
        if (!(tempPlayer.getScoreboard().getTeams().contains(teamMap.get(teamName)))) {
          // Debug
          Bukkit.broadcastMessage("Team Reg!");
          tempPlayer.getScoreboard().registerNewTeam(teamName);
        }
      }

    }
  }

  /**
   * Create a team and fill it with players.
   * 
   * @param teamName The reference name of the team.
   * @param players The players to be added to the team.
   */
  public void createTeam(String teamName, Player[] players) {
    if (!teamMap.containsKey(teamName)) {
      Team team = teamBoard.registerNewTeam(teamName);
      for (Player p : players) {
        team.addPlayer(p);
      }
      teamMap.put(teamName, team);
      for (Object p : Bukkit.getServer().getOnlinePlayers().toArray()) {
        Player tempPlayer = (Player) p;
        if (!(tempPlayer.getScoreboard().getTeams().contains(teamMap.get(teamName)))) {
          // Debug
          Bukkit.broadcastMessage("Team Reg!");
          Team newTeam = tempPlayer.getScoreboard().registerNewTeam(teamName);
          for (Player w : players) {
            newTeam.addPlayer(w);
          }
        }
      }

    }
  }

  /**
   * Check if a team exists by team name.
   * 
   * @param teamName The reference name of the team.
   */
  public boolean teamExists(String teamName) {
    if (teamMap.containsKey(teamName)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Check if a player is on a team by team name.
   * 
   * @param teamName The reference name of the team.
   * @param player The player to check.
   */
  public boolean isPlayerOnTeam(String teamName, Player player) {
    if (teamMap.containsKey(teamName) && teamMap.get(teamName).getPlayers().contains(player)) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * Add players to a team by team name.
   * 
   * @param teamName The reference name of the team.
   * @param players The players to add to the team.
   */
  public void addPlayesrToTeam(String teamName, Player[] players) {
    if (teamMap.containsKey(teamName)) {
      for (Player player : players) {
        teamMap.get(teamName).addPlayer(player);
      }
      for (Object e : Bukkit.getServer().getOnlinePlayers().toArray()) {
        Player tempPlayer = (Player) e;
        // Debug
        Bukkit.broadcastMessage("Player set!");
        for (Player player : players) {
          tempPlayer.getScoreboard().getTeam(teamName).addPlayer(player);
        }
      }
    }
  }

  /**
   * Remove players from a team by team name.
   * 
   * @param teamName The reference name of the team.
   * @param players The players to be removed from the team.
   */
  public void removePlayersFromTeam(String teamName, Player[] players) {
    if (teamMap.containsKey(teamName)) {
      for (Player player : players) {
        teamMap.get(teamName).removePlayer(player);
      }
      for (Object e : Bukkit.getServer().getOnlinePlayers().toArray()) {
        // Debug
        Bukkit.broadcastMessage("Removed!");
        for (Player player : players) {
          ((Player) e).getScoreboard().getTeam(teamName).removePlayer(player);
        }
      }
    }
  }

  /**
   * Delete a team by team name.
   * 
   * @param teamName The reference name of the team.
   */
  public void removeTeam(String teamName) {
    if (teamMap.containsKey(teamName)) {
      teamMap.remove(teamName);
      for (Object e : Bukkit.getServer().getOnlinePlayers().toArray()) {
        Player tempPlayer = (Player) e;
        if (tempPlayer.getScoreboard().getTeam(teamName) != null) {
          tempPlayer.getScoreboard().getTeam(teamName).unregister();
        }
      }
    }
  }

  /**
   * Set the prefix of a team by team name.
   * 
   * @param team The reference name of the team.
   * @param prefix The prefix to be set.
   */
  public void setPrefix(String team, String prefix) {
    if (teamMap.containsKey(team)) {
      teamMap.get(team).setPrefix(prefix);
      for (Object p : Bukkit.getServer().getOnlinePlayers().toArray()) {
        // Debug
        Bukkit.broadcastMessage("Prefix set!");
        ((Player) p).getScoreboard().getTeam(team).setPrefix(prefix);
      }
    }
  }

  /**
   * Set the suffix of a team by team name.
   * 
   * @param team The reference name of the team.
   * @param suffix The suffix to be set.
   */
  public void setSuffix(String team, String suffix) {
    if (teamMap.containsKey(team)) {
      teamMap.get(team).setSuffix(suffix);
      for (Object p : Bukkit.getServer().getOnlinePlayers().toArray()) {
        // Debug
        Bukkit.broadcastMessage("Suffix set!");
        ((Player) p).getScoreboard().getTeam(team).setSuffix(suffix);
      }
    }
  }

  /**
   * Set the friendly fire state of a team by team name.
   * 
   * @param team The reference name of the team.
   * @param result If friendly fire is enabled or disabled.
   */
  public void setFriendlyFire(String team, boolean result) {
    if (teamMap.containsKey(team)) {
      teamMap.get(team).setAllowFriendlyFire(result);
      for (Object p : Bukkit.getServer().getOnlinePlayers().toArray()) {
        // Debug
        Bukkit.broadcastMessage("Friendly fire set!");
        ((Player) p).getScoreboard().getTeam(team).setAllowFriendlyFire(result);
      }
    }
  }

  /**
   * Set the friendly invisible state of a team by team name.
   * 
   * @param team The reference name of the team.
   * @param result If friendly invisibility is enabled or disabled.
   */
  public void setFriendlyInvisibles(String team, boolean result) {
    if (teamMap.containsKey(team)) {
      teamMap.get(team).setAllowFriendlyFire(result);
      for (Object p : Bukkit.getServer().getOnlinePlayers().toArray()) {
        // Debug
        Bukkit.broadcastMessage("Friendly Invisibles set!");
        ((Player) p).getScoreboard().getTeam(team).setCanSeeFriendlyInvisibles(result);
      }
    }
  }

  /**
   * Set the team name tag visibility of a team by team name.
   * 
   * @param team The reference name of the team.
   * @param vis The NameTagVisibility object to be set.
   */
  public void setTeamNameTagVisibility(String team, NameTagVisibility vis) {
    if (teamMap.containsKey(team)) {
      teamMap.get(team).setNameTagVisibility(NameTagVisibility.ALWAYS);
      for (Object p : Bukkit.getServer().getOnlinePlayers().toArray()) {
        // Debug
        Bukkit.broadcastMessage("Friendly Invisibles set!");
        ((Player) p).getScoreboard().getTeam(team).setNameTagVisibility(NameTagVisibility.ALWAYS);
      }
    }
  }

  /**
   * Set the a teams option by team name.
   * 
   * @param team The reference name of the team.
   * @param option The option to be set.
   * @param status The options value to be set.
   */
  public void setTeamOption(String team, Option option, OptionStatus status) {
    if (teamMap.containsKey(team)) {
      teamMap.get(team).setOption(option, status);
      for (Object p : Bukkit.getServer().getOnlinePlayers().toArray()) {
        // Debug
        Bukkit.broadcastMessage("Team option set!");
        ((Player) p).getScoreboard().getTeam(team).setOption(option, status);
      }
    }
  }

}
