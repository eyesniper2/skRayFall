package net.rayfall.eyesniper2.skrayfall.scoreboard;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffAddPlayerToGroupId extends Effect {
  // add %player% to group score [with id] %string%

  Expression<Player> player;
  Expression<String> id;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    player = (Expression<Player>) exp[0];
    id = (Expression<String>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    Core.sbManager.addPlayerToGroupId(id.getSingle(evt).replace("\"", ""), player.getSingle(evt));
  }

}
