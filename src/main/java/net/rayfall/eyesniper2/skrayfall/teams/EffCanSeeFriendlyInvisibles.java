package net.rayfall.eyesniper2.skrayfall.teams;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffCanSeeFriendlyInvisibles extends Effect {
  
  // set friendly team invisibility for [team] %string% to %boolean%
  
  private Expression<String> team;
  private Expression<Boolean> invisibilityState;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
    team = (Expression<String>) expr[0];
    invisibilityState = (Expression<Boolean>) expr[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event evt, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    if (team != null && invisibilityState != null) {
      Core.teamManager.setFriendlyInvisibles(team.getSingle(evt).replace("\"", ""),
          invisibilityState.getSingle(evt));
    }
  }

}
