package net.rayfall.eyesniper2.skrayfall.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

public class SingleScore {
  private Score score;
  private Player player;

  public SingleScore(Score score, Player player) {
    this.score = score;
    this.player = player;
  }

  public Player getPlayer() {
    return this.player;
  }

  public Score getScore() {
    return this.score;
  }

  public boolean isPlayer(Player player) {
    return (player == this.player);
  }

}
