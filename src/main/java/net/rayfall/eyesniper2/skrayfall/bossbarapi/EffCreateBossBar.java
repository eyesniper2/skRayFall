package net.rayfall.eyesniper2.skrayfall.bossbarapi;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.inventivetalent.bossbar.BossBarAPI;

public class EffCreateBossBar extends Effect {

  // set bossbar named %string% for %player% to %number%
  private Expression<String> title;
  private Expression<Player> player;
  private Expression<Number> per;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    title = (Expression<String>) exp[0];
    player = (Expression<Player>) exp[1];
    per = (Expression<Number>) exp[2];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void execute(Event evt) {
    BossBarAPI.setMessage(player.getSingle(evt), title.getSingle(evt).toString().replace("\"", ""),
        per.getSingle(evt).floatValue());
    // BossBarAPI.addBar(player.getSingle(evt), new TextComponent(t.getSingle(evt).replace("\"",
    // "")),null,null, per.getSingle(evt).floatValue()/100);
  }
}
