package net.rayfall.eyesniper2.skrayfall.bossbarapi;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.Nullable;
import org.inventivetalent.bossbar.BossBarAPI;

public class ExprBaseBossBar extends SimplePropertyExpression<Player, Number> {

  @Override
  public Class<? extends Number> getReturnType() {
    return Number.class;
  }

  @SuppressWarnings("deprecation")
  @Override
  @Nullable
  public Number convert(Player evt) {
    return BossBarAPI.getBossBar(evt).getMaxHealth();
    /*
     * for(BossBar bar : BossBarAPI.getBossBars(evt)){ return ((bar.getProgress()-1) * 100); }
     * return null;
     */
  }

  @Override
  protected String getPropertyName() {
    return null;
  }
}
