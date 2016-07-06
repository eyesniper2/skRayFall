package net.rayfall.eyesniper2.skrayfall.bossbarapi;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.inventivetalent.bossbar.BossBarAPI;

public class EffDeleteBossBar extends Effect {

  private Expression<Player> player;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    player = (Expression<Player>) exp[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void execute(Event evt) {
    BossBarAPI.removeBar(player.getSingle(evt));
    // BossBarAPI.removeAllBars(player.getSingle(evt));
  }
}
