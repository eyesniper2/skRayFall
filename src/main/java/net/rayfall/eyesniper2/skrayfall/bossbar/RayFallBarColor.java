package net.rayfall.eyesniper2.skrayfall.bossbar;

import org.bukkit.boss.BarColor;

public enum RayFallBarColor {
  GREEN(BarColor.GREEN), PINK(BarColor.PINK), PURPLE(BarColor.PURPLE), WHITE(
      BarColor.WHITE), YELLOW(BarColor.YELLOW), BLUE(BarColor.BLUE), RED(BarColor.RED);

  private BarColor key;

  private RayFallBarColor(BarColor color) {
    this.key = color;
  }

  public BarColor getKey() {
    return this.key;
  }
}
